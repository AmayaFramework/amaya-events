package io.github.amayaframework.events;

import java.util.concurrent.TimeUnit;

final class BlockingEventManager extends AbstractEventManager {

    BlockingEventManager(EventRegistry registry, EventTrigger trigger) {
        super(registry, trigger);
    }

    @Override
    public void stop(long timeout, TimeUnit unit) {
    }

    @Override
    public void stop() {
    }

    @Override
    public void halt() {
    }
}
