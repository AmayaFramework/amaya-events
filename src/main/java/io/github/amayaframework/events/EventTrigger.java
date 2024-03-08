package io.github.amayaframework.events;

import java.util.List;
import java.util.concurrent.Future;

/**
 * An interface describing an abstract mechanism for calling events by name or group.
 */
public interface EventTrigger {

    Future<Event> fire(Event event, Object context);

    List<Future<Event>> fire(Iterable<Event> events, Object context);
}
