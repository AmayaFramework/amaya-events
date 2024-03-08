package io.github.amayaframework.events;

import com.github.romanqed.jfunc.Runnable1;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Future;

/**
 *
 */
public abstract class AbstractEventManager implements EventManager {
    protected final EventRegistry registry;
    protected final EventTrigger trigger;

    /**
     * @param registry
     * @param trigger
     */
    protected AbstractEventManager(EventRegistry registry, EventTrigger trigger) {
        this.registry = Objects.requireNonNull(registry);
        this.trigger = Objects.requireNonNull(trigger);
    }

    @Override
    public EventRegistry getRegistry() {
        return registry;
    }

    @Override
    public EventTrigger getTrigger() {
        return trigger;
    }

    @Override
    public Runnable1<Object> get(Event event) {
        return registry.get(event);
    }

    @Override
    public void set(Event event, Runnable1<?> body) {
        registry.set(event, body);
    }

    @Override
    public void add(Event event, Runnable1<?> body) {
        registry.add(event, body);
    }

    @Override
    public boolean contains(Event event) {
        return registry.contains(event);
    }

    @Override
    public boolean remove(Event event) {
        return registry.remove(event);
    }

    @Override
    public Future<Event> fire(Event event, Object context) {
        return trigger.fire(event, context);
    }

    @Override
    public List<Future<Event>> fire(Iterable<Event> events, Object context) {
        return trigger.fire(events, context);
    }
}
