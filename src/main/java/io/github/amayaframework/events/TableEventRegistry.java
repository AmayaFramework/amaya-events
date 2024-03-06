package io.github.amayaframework.events;

import com.github.romanqed.jfunc.Runnable1;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

public class TableEventRegistry implements EventRegistry {
    private final Map<Event, RunnableImpl> events;
    private final Map<EventGroup, Map<Event, RunnableImpl>> groups;

    @SuppressWarnings("unchecked")
    public TableEventRegistry(Supplier<Map<?, ?>> supplier) {
        this.events = (Map<Event, RunnableImpl>) supplier.get();
        this.groups = (Map<EventGroup, Map<Event, RunnableImpl>>) supplier.get();
    }

    public TableEventRegistry() {
        this.events = new HashMap<>();
        this.groups = new HashMap<>();
    }

    @Override
    public Runnable1<Object> get(Event event) {
        return events.get(event);
    }

    @Override
    public Map<Event, Runnable1<Object>> get(EventGroup group) {
        var ret = groups.get(group);
        if (ret == null) {
            return null;
        }
        return Collections.unmodifiableMap(ret);
    }

    private void innerSet(Event event, Runnable1<?> body) {
        var container = events.get(event);
        if (container != null) {
            container.setBody(body);
            return;
        }
        container = new RunnableImpl(body);
        events.put(event, container);
        var enclosings = event.getGroup().getEnclosingGroups();
        for (var group : enclosings) {
            var map = groups.computeIfAbsent(group, k -> new HashMap<>());
            map.put(event, container);
        }
    }

    @Override
    public void set(Event event, Runnable1<?> body) {
        Objects.requireNonNull(event);
        Objects.requireNonNull(body);
        innerSet(event, body);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void add(Event event, Runnable1<?> body) {
        Objects.requireNonNull(event);
        Objects.requireNonNull(body);
        if (!events.containsKey(event)) {
            innerSet(event, body);
            return;
        }
        var container = events.get(event);
        var combined = Runnable1.combine(container.getBody(), (Runnable1<Object>) body);
        container.setBody(combined);
    }

    @Override
    public boolean contains(EventGroup group) {
        return groups.containsKey(group);
    }

    @Override
    public boolean contains(Event event) {
        return events.containsKey(event);
    }

    @Override
    public boolean remove(EventGroup group) {
        if (!groups.containsKey(group)) {
            return false;
        }
        var keys = groups.remove(group).keySet();
        keys.forEach(this::innerRemove);
        return true;
    }

    private void innerRemove(Event event) {
        events.remove(event);
        var enclosings = event.getGroup().getEnclosingGroups();
        for (var group : enclosings) {
            groups.get(group).remove(event);
        }
    }

    @Override
    public boolean remove(Event event) {
        if (!events.containsKey(event)) {
            return false;
        }
        innerRemove(event);
        return true;
    }
}
