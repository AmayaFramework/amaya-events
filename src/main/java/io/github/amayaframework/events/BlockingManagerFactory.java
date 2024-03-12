package io.github.amayaframework.events;

/**
 * An implementation of a {@link EventManagerFactory} that creates managers
 * that use a {@link BlockingEventTrigger}.
 */
public final class BlockingManagerFactory extends AbstractManagerFactory {

    @Override
    protected EventManager uncheckedCreate(EventRegistry registry) {
        var trigger = new BlockingEventTrigger(registry);
        return new BlockingEventManager(registry, trigger);
    }
}
