package io.github.amayaframework.events;

import java.util.concurrent.TimeUnit;

/**
 * An interface describing an abstract event manager that includes registry and trigger functionality.
 */
public interface EventManager extends EventRegistry, EventTrigger {

    /**
     * Returns the registry used by this manager.
     *
     * @return {@link EventRegistry} instance
     */
    EventRegistry getRegistry();

    /**
     * Returns the trigger used by this manager.
     *
     * @return {@link EventTrigger} instance
     */
    EventTrigger getTrigger();

    /**
     * Stops the event manager and waits for all running actions to complete with the specified timeout.
     *
     * @param timeout the specified timeout
     * @param unit    the specified time unit, must be non-null
     */
    void stop(long timeout, TimeUnit unit);

    /**
     * Stops the event manager, and returns control to the calling thread,
     * without waiting for all unfinished actions to be completed.
     */
    void stop();
}
