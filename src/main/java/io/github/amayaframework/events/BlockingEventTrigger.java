package io.github.amayaframework.events;

import com.github.romanqed.jfunc.Runnable1;

import java.util.concurrent.Future;

public final class BlockingEventTrigger extends AbstractEventTrigger {

    public BlockingEventTrigger(EventProvider provider) {
        super(provider);
    }

    @Override
    protected Future<Event> execute(Event event, Runnable1<Object> body, Object context) {
        var policy = event.getPolicy();
        if (policy == FirePolicy.PARALLEL) {
            throw new UnsupportedFirePolicy(policy);
        }
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
