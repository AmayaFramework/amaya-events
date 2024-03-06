package io.github.amayaframework.events;

import java.util.concurrent.TimeUnit;

/**
 *
 */
public interface EventManager extends EventRegistry, EventTrigger {

    /**
     * @return
     */
    EventRegistry getRegistry();

    /**
     * @return
     */
    EventTrigger getTrigger();

    /**
     * @param timeout
     * @param unit
     */
    void stop(long timeout, TimeUnit unit);

    /**
     *
     */
    void stop();

    /**
     *
     */
    void halt();
}
