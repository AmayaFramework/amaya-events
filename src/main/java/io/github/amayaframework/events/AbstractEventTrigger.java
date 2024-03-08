package io.github.amayaframework.events;

import com.github.romanqed.jfunc.Runnable1;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Future;

/**
 *
 */
public abstract class AbstractEventTrigger implements EventTrigger {
    protected final EventProvider provider;

    /**
     * @param provider
     */
    protected AbstractEventTrigger(EventProvider provider) {
        this.provider = Objects.requireNonNull(provider);
    }

    /**
     * @param event
     * @param body
     * @param context
     * @return
     */
    protected abstract Future<Event> execute(Event event, Runnable1<Object> body, Object context);

    @Override
    @SuppressWarnings("unchecked")
    public Future<Event> fire(Event event, Object context) {
        var handler = provider.get(event);
        if (handler == null) {
            return (Future<Event>) CompletedFuture.EMPTY;
        }
        return execute(event, handler, context);
    }

    @Override
    public List<Future<Event>> fire(Iterable<Event> events, Object context) {
        var ret = new LinkedList<Future<Event>>();
        for (var event : events) {
            var handler = provider.get(event);
            if (handler == null) {
                continue;
            }
            ret.add(execute(event, handler, context));
        }
        return ret;
    }
}
