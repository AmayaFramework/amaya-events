package io.github.amayaframework.events;

import com.github.romanqed.jfunc.Runnable1;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * An implementation of an event trigger that uses an {@link ExecutorService} to generate calls to event handlers.
 */
public final class ParallelEventTrigger extends AbstractEventTrigger {
    private final ExecutorService executor;

    /**
     * Constructs {@link ParallelEventTrigger} instance with the specified event provider and executor.
     *
     * @param provider the specified {@link EventProvider} instance, must be non-null
     * @param executor the specified {@link ExecutorService} instance, must be non-null
     */
    public ParallelEventTrigger(EventProvider provider, ExecutorService executor) {
        super(provider);
        this.executor = Objects.requireNonNull(executor);
    }

    @Override
    protected <T> Future<Event<T>> execute(Event<T> event, Runnable1<T> body, T context) {
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
