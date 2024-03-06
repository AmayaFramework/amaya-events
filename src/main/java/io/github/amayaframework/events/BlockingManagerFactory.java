package io.github.amayaframework.events;

/**
 *
 */
public final class BlockingManagerFactory extends AbstractManagerFactory {

    @Override
    public EventManager create() {
        var registry = configure(new TableEventRegistry());
        var trigger = configure(new BlockingEventTrigger(registry));
        return new BlockingEventManager(registry, trigger);
    }
}
