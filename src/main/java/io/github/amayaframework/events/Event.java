package io.github.amayaframework.events;

import java.util.Objects;

/**
 * A class describing the event header that can be used to register and invoke events.
 */
public final class Event {
    private final String name;
    private final FirePolicy policy;

    /**
     * Constructs event with the specified name and policy.
     *
     * @param name   the specified event name, must be non-null
     * @param policy the specified fire policy, must be non-null
     */
    public Event(String name, FirePolicy policy) {
        this.name = Objects.requireNonNull(name);
        this.policy = Objects.requireNonNull(policy);
    }

    /**
     * Returns event name.
     *
     * @return event name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the fire policy, specified for this event.
     *
     * @return {@link FirePolicy} constant
     */
    public FirePolicy getPolicy() {
        return policy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var event = (Event) o;
        return name.equals(event.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", policy=" + policy +
                '}';
    }
}
