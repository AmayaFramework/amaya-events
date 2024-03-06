package io.github.amayaframework.events;

import java.util.Objects;

public final class EventGroup {
    final String name;
    final EventGroup parent;

    public EventGroup(String name, EventGroup parent) {
        this.name = Objects.requireNonNull(name);
        this.parent = parent;
    }

    public EventGroup(String name) {
        this(name, null);
    }

    public String getName() {
        return name;
    }

    public EventGroup getParent() {
        return parent;
    }

    public Iterable<EventGroup> getParents() {
        return () -> new GroupIterator(parent);
    }

    public Iterable<EventGroup> getEnclosingGroups() {
        return () -> new GroupIterator(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var that = (EventGroup) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "EventGroup{" +
                "name='" + name + '\'' +
                '}';
    }
}
