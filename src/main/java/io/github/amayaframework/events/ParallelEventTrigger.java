package io.github.amayaframework.events;

import com.github.romanqed.jfunc.Runnable1;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 *
 */
public final class ParallelEventTrigger extends AbstractEventTrigger {
    private final ExecutorService executor;

    /**
     * @param provider
     * @param executor
     */
    public ParallelEventTrigger(EventProvider provider, ExecutorService executor) {
        super(provider);
        this.executor = Objects.requireNonNull(executor);
    }

    @Override
    protected Future<Event> execute(Event event, Runnable1<Object> body, Object context) {
        if (event.getPolicy() == FirePolicy.BLOCKING) {
            try {
                body.run(context);
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
            return new CompletedFuture<>(event);
        }
        return executor.submit(() -> {
            try {
                body.run(context);
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
            return event;
        });
    }
}
