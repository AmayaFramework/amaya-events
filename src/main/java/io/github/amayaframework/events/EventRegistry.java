package io.github.amayaframework.events;

import com.github.romanqed.jfunc.Runnable1;

/**
 * An interface describing an abstract event registry that supports a basic set of operations
 * (create, read, update, delete).
 */
public interface EventRegistry extends EventProvider {

    /**
     * Sets the handler for the specified event. The existing handler will be overwritten.
     *
     * @param event the specified event, must be non-null
     * @param body  the specified event handler, must be non-null
     * @param <T>   the type of the event context
     */
    <T> void set(Event<T> event, Runnable1<T> body);

    /**
     * Adds the handler to an existing one for the specified event.
     * If the handler has not been set yet, the specified one will be set.
     *
     * @param event the specified event, must be non-null
     * @param body  the specified event handler, must be non-null
     * @param <T>   the type of the event context
     */
    <T> void add(Event<T> event, Runnable1<T> body);

    /**
     * Checks whether the registry contains a handler for the specified event.
     *
     * @param event the specified event, may be null
     * @return true if this registry contains event, false otherwise
     */
    boolean contains(Event<?> event);

    /**
     * Removes the handler of the specified event.
     *
     * @param event the specified event, may be null
     * @return true if the handler was removed, false otherwise
     */
    boolean remove(Event<?> event);
}
