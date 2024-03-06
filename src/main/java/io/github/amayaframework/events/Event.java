package io.github.amayaframework.events;

import java.util.Objects;

public final class Event {
    private final String name;
    private final EventGroup group;
    private final FirePolicy policy;

    public Event(EventGroup group, String name, FirePolicy policy) {
        this.group = Objects.requireNonNull(group);
        this.name = Objects.requireNonNull(name);
        this.policy = Objects.requireNonNull(policy);
    }

    public String getName() {
        return name;
    }

    public EventGroup getGroup() {
        return group;
    }

    public FirePolicy getPolicy() {
        return policy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var event = (Event) o;
        return group.equals(event.group) && name.equals(event.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(group, name);
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", group='" + group.name + '\'' +
                ", policy=" + policy +
                '}';
    }
}
