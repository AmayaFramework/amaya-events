package io.github.amayaframework.events;

import java.util.concurrent.TimeUnit;

public interface EventManager extends EventRegistry, EventTrigger {
    EventRegistry getRegistry();

    EventTrigger getTrigger();

    void stop(long timeout, TimeUnit unit);

    void stop();

    void halt();
}
