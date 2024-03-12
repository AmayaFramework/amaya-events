package io.github.amayaframework.events;

/**
 * An interface describing an abstract event manager factory.
 */
public interface EventManagerFactory {

    /**
     * Creates an instance of the {@link EventManager} implementation
     * with the specified event registry.
     *
     * @param registry the specified {@link EventRegistry} instance, must be non-null
     * @return {@link EventManager} instance
     */
    EventManager create(EventRegistry registry);

    /**
     * Creates an instance of the {@link EventManager} implementation.
     *
     * @return {@link EventManager} instance
     */
    EventManager create();
}
