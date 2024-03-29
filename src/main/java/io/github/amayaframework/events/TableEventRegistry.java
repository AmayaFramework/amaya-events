package io.github.amayaframework.events;

import com.github.romanqed.jfunc.Runnable1;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * An implementation of the event registry that uses {@link Map}.
 */
public class TableEventRegistry implements EventRegistry {
    private final Map<Event<?>, Runnable1<?>> events;

    /**
     * Constructs {@link TableEventRegistry} instance with the specified {@link Map}, provided by supplier.
     *
     * @param supplier the specified {@link Supplier} instance, must be non-null
     */
    @SuppressWarnings("unchecked")
    public TableEventRegistry(Supplier<Map<?, ?>> supplier) {
        this.events = (Map<Event<?>, Runnable1<?>>) Objects.requireNonNull(supplier.get());
    }

    /**
     * Constructs {@link TableEventRegistry} instance with the {@link HashMap}.
     */
    public TableEventRegistry() {
        this.events = new HashMap<>();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Runnable1<T> get(Event<T> event) {
        return (Runnable1<T>) events.get(event);
    }

    @Override
    public <T> void set(Event<T> event, Runnable1<T> body) {
        Objects.requireNonNull(event);
        Objects.requireNonNull(body);
        events.put(event, body);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> void add(Event<T> event, Runnable1<T> body) {
        Objects.requireNonNull(event);
        Objects.requireNonNull(body);
        var handler = (Runnable1<T>) events.get(event);
        if (handler == null) {
            events.put(event, body);
            return;
        }
        events.put(event, Runnable1.combine(handler, body));
    }

    @Override
    public boolean contains(Event<?> event) {
        return events.containsKey(event);
    }

    @Override
    public boolean remove(Event<?> event) {
        return events.remove(event) != null;
    }
}
