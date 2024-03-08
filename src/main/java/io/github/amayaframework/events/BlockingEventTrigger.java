package io.github.amayaframework.events;

import com.github.romanqed.jfunc.Runnable1;

import java.util.concurrent.Future;

/**
 *
 */
public final class BlockingEventTrigger extends AbstractEventTrigger {

    /**
     * @param provider
     */
    public BlockingEventTrigger(EventProvider provider) {
        super(provider);
    }

    @Override
    protected <T> Future<Event<T>> execute(Event<T> event, Runnable1<T> body, T context) {
        try {
            body.run(context);
        } catch (Error | RuntimeException e) {
            throw e;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return new CompletedFuture<>(event);
    }
}
