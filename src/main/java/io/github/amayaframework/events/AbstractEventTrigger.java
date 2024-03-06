package io.github.amayaframework.events;

import com.github.romanqed.jfunc.Runnable1;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Future;

public abstract class AbstractEventTrigger implements EventTrigger {
    protected final EventProvider provider;

    protected AbstractEventTrigger(EventProvider provider) {
        this.provider = Objects.requireNonNull(provider);
    }

    protected abstract Future<Event> execute(Event event, Runnable1<Object> body, Object context);

    @Override
    public Future<Event> fire(Event event, Object context) {
        var body = provider.get(event);
        if (body == null) {
            return null;
        }
        return execute(event, body, context);
    }

    @Override
    public List<Future<Event>> fire(EventGroup group, Object context) {
        var ret = new LinkedList<Future<Event>>();
        var found = provider.get(group);
        for (var entry : found.entrySet()) {
            ret.add(execute(entry.getKey(), entry.getValue(), context));
        }
        return ret;
    }
}
