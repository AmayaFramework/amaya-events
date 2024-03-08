package io.github.amayaframework.events;

import com.github.romanqed.jfunc.Runnable1;

import java.util.concurrent.Future;

/**
 * A blocking single-threaded implementation of an {@link EventTrigger} that
 * executes a handler call in the calling thread.
 * Returns instances of the {@link CompletedFuture}.
 */
public final class BlockingEventTrigger extends AbstractEventTrigger {

    /**
     * Constructs {@link BlockingEventTrigger} instance with the specified event provider.
     *
     * @param provider the specified {@link EventProvider} instance, must be non-null
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
