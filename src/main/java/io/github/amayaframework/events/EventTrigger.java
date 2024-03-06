package io.github.amayaframework.events;

import java.util.List;
import java.util.concurrent.Future;

/**
 * An interface describing an abstract mechanism for calling events by name or group.
 */
public interface EventTrigger {

    /**
     * Triggers an event by name with the specified context.
     *
     * @param event   the specified event, may be null
     * @param context the specified context
     * @return {@link Future} instance, containing event execution information
     */
    Future<Event> fire(Event event, Object context);

    boolean fireNow(Event event, Object context);

    List<Future<Event>> fire(Iterable<Event> events, Object context);

    boolean fireNow(Iterable<Event> events, Object context);
}
