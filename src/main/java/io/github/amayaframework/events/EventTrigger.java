package io.github.amayaframework.events;

import java.util.List;
import java.util.concurrent.Future;

/**
 * An interface describing an abstract mechanism for calling events by name or group.
 */
public interface EventTrigger {

    <T> Future<Event<T>> fire(Event<T> event, T context);

    <T> List<Future<Event<T>>> fire(Iterable<Event<T>> events, T context);
}
