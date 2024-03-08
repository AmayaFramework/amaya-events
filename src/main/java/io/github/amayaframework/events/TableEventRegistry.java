package io.github.amayaframework.events;

import com.github.romanqed.jfunc.Runnable1;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

public class TableEventRegistry implements EventRegistry {
    private final Map<Event, Runnable1<Object>> events;

    @SuppressWarnings("unchecked")
    public TableEventRegistry(Supplier<Map<?, ?>> supplier) {
        this.events = (Map<Event, Runnable1<Object>>) supplier.get();
    }

    public TableEventRegistry() {
        this.events = new HashMap<>();
    }

    @Override
    public Runnable1<Object> get(Event event) {
        return events.get(event);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void set(Event event, Runnable1<?> body) {
        Objects.requireNonNull(event);
        Objects.requireNonNull(body);
        events.put(event, (Runnable1<Object>) body);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void add(Event event, Runnable1<?> body) {
        Objects.requireNonNull(event);
        Objects.requireNonNull(body);
        var handler = events.get(event);
        if (handler == null) {
            events.put(event, (Runnable1<Object>) body);
            return;
        }
        events.put(event, Runnable1.combine(handler, (Runnable1<Object>) body));
    }

    @Override
    public boolean contains(Event event) {
        return events.containsKey(event);
    }

    @Override
    public boolean remove(Event event) {
        return events.remove(event) != null;
    }
}
