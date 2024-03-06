package io.github.amayaframework.events;

import java.util.List;
import java.util.concurrent.Future;

public interface EventTrigger {
    Future<Event> fire(Event event, Object context);

    List<Future<Event>> fire(EventGroup group, Object context);
}
