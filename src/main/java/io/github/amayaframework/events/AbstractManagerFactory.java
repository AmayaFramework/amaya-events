package io.github.amayaframework.events;

import java.util.Objects;

public abstract class AbstractManagerFactory implements EventManagerFactory {
    protected abstract EventManager uncheckedCreate(EventRegistry registry);

    @Override
    public EventManager create(EventRegistry registry) {
        Objects.requireNonNull(registry);
        return uncheckedCreate(registry);
    }

    @Override
    public EventManager create() {
        return uncheckedCreate(new TableEventRegistry());
    }
}
