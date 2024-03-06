package io.github.amayaframework.events;

/**
 *
 */
public abstract class AbstractManagerFactory implements EventManagerFactory {

    /**
     * @param registry
     * @return
     */
    protected EventRegistry configure(EventRegistry registry) {
        return registry;
    }

    /**
     * @param trigger
     * @return
     */
    protected EventTrigger configure(EventTrigger trigger) {
        return trigger;
    }
}
