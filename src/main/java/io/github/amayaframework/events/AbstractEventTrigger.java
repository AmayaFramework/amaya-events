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

    protected AbstractEventTrigger(EventProvider provider) {
        this.provider = Objects.requireNonNull(provider);
    }

    protected abstract <T> Future<Event<T>> execute(Event<T> event, Runnable1<T> body, T context);

    @Override
    @SuppressWarnings("unchecked")
    public <T> Future<Event<T>> fire(Event<T> event, T context) {
        var handler = provider.get(event);
        if (handler == null) {
            return (Future<Event<T>>) CompletedFuture.EMPTY;
        }
        return execute(event, handler, context);
    }

    @Override
    public <T> List<Future<Event<T>>> fire(Iterable<Event<T>> events, T context) {
        var ret = new LinkedList<Future<Event<T>>>();
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
