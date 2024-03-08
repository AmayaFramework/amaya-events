package io.github.amayaframework.events;

import java.util.Objects;

/**
 * A class describing the event header that can be used to register and invoke events.
 */
public final class Event<T> {
    private final String name;
    private final Class<T> contextType;

    /**
     * Constructs event with the specified name and policy.
     *
     * @param name   the specified event name, must be non-null
     */
    public Event(String name, Class<T> contextType) {
        this.name = Objects.requireNonNull(name);
        this.contextType = Objects.requireNonNull(contextType);
    }

    public static <T> Event<T> of(String name, Class<T> contextType) {
        return new Event<>(name, contextType);
    }

    /**
     * Returns event name.
     *
     * @return event name
     */
    public String getName() {
        return name;
    }

    public Class<T> getContextType() {
        return contextType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var event = (Event<?>) o;
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
                ", contextType=" + contextType +
                '}';
    }
}
