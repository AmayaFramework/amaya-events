package io.github.amayaframework.events;

/**
 * An interface describing an abstract event manager factory.
 */
public interface EventManagerFactory {

    /**
     * Creates an instance of the {@link EventManager} implementation.
     *
     * @return {@link EventManager} instance
     */
    EventManager create();
}
