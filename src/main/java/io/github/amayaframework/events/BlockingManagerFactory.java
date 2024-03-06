package io.github.amayaframework.events;

public final class BlockingManagerFactory implements EventManagerFactory {

    @Override
    public EventManager create() {
        var registry = new TableEventRegistry();
        var trigger = new BlockingEventTrigger(registry);
        return new BlockingEventManager(registry, trigger);
    }
}
