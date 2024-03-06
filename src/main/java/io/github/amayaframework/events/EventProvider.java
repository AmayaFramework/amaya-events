package io.github.amayaframework.events;

import com.github.romanqed.jfunc.Runnable1;

import java.util.Map;

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

    /**
     * Gets map of event handlers by the specified event group.
     *
     * @param group the specified event group, may be null
     * @return {@link Map} instance or null
     */
    Map<Event, Runnable1<Object>> get(EventGroup group);
}
