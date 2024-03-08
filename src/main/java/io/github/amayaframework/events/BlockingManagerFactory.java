package io.github.amayaframework.events;

/**
 * An implementation of a {@link EventManagerFactory} that creates managers
 * that use a {@link BlockingEventTrigger}.
 */
public final class BlockingManagerFactory implements EventManagerFactory {

    @Override
    public EventManager create() {
        var registry = new TableEventRegistry();
        var trigger = new BlockingEventTrigger(registry);
        return new BlockingEventManager(registry, trigger);
    }
}
