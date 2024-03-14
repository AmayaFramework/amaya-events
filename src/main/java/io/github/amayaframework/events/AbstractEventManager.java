package io.github.amayaframework.events;

import com.github.romanqed.jfunc.Runnable1;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Future;
import java.util.function.Supplier;

/**
 * A class that provides a skeletal implementation of the {@link EventManager},
 * including the implementation of the common methods, as well as
 * fields containing the {@link EventRegistry} and {@link EventTrigger} instances.
 */
public abstract class AbstractEventManager implements EventManager {
    protected final EventRegistry registry;
    protected final EventTrigger trigger;

    /**
     * Constructs {@link EventManager} instance with the specified registry and trigger.
     *
     * @param registry the specified {@link EventRegistry} instance, must be non-null
     * @param trigger  the specified {@link EventTrigger} instance, must be non-null
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
    public <T> Runnable1<T> get(Event<T> event) {
        return registry.get(event);
    }

    @Override
    public <T> void set(Event<T> event, Runnable1<T> body) {
        registry.set(event, body);
    }

    @Override
    public <T> void add(Event<T> event, Runnable1<T> body) {
        registry.add(event, body);
    }

    @Override
    public boolean contains(Event<?> event) {
        return registry.contains(event);
    }

    @Override
    public boolean remove(Event<?> event) {
        return registry.remove(event);
    }

    @Override
    public <T> Future<Event<T>> fire(Event<T> event, T context) {
        return trigger.fire(event, context);
    }

    @Override
    public <T> List<Future<Event<T>>> fire(Iterable<Event<T>> events, T context) {
        return trigger.fire(events, context);
    }

    @Override
    public <T> boolean fireNow(Event<T> event, T context) throws InterruptedException {
        return trigger.fireNow(event, context);
    }

    @Override
    public <T> boolean fireNow(Iterable<Event<T>> events, T context) throws InterruptedException {
        return trigger.fireNow(events, context);
    }

    @Override
    public <T> Future<Event<T>> fire(Event<T> event, Supplier<T> supplier) {
        return trigger.fire(event, supplier);
    }

    @Override
    public <T> boolean fireNow(Event<T> event, Supplier<T> supplier) throws InterruptedException {
        return trigger.fireNow(event, supplier);
    }

    @Override
    public <T> List<Future<Event<T>>> fire(Iterable<Event<T>> events, Supplier<T> supplier) {
        return trigger.fire(events, supplier);
    }

    @Override
    public <T> boolean fireNow(Iterable<Event<T>> events, Supplier<T> supplier) throws InterruptedException {
        return trigger.fireNow(events, supplier);
    }
}
