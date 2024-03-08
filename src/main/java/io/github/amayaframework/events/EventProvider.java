package io.github.amayaframework.events;

import com.github.romanqed.jfunc.Runnable1;

/**
 * An interface describing an abstract event provider that performs a search by name or group.
 */
public interface EventProvider {

    /**
     * Gets event handler by the specified event.
     *
     * @param event the specified event, may be null
     * @return {@link Runnable1} instance or null
     */
    Runnable1<Object> get(Event event);
}
