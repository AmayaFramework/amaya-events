package io.github.amayaframework.events;

public interface EventFactory {
    EventGroup create(String group);

    Event create(String event, FirePolicy policy);
}
