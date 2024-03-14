package io.github.amayaframework.events;

import java.util.List;
import java.util.concurrent.Future;
import java.util.function.Supplier;

/**
 * An interface describing an abstract mechanism for calling events by descriptor.
 */
public interface EventTrigger {

    /**
     * Executes the handler associated with the specified event, passing the specified context to it.
     *
     * @param event   the specified event, must be non-null
     * @param context the specified context
     * @param <T>     the type of the event context
     * @return {@link Future} instance bound with event execution if the handler was found, null otherwise
     */
    <T> Future<Event<T>> fire(Event<T> event, T context);

    <T> Future<Event<T>> fire(Event<T> event, Supplier<T> supplier);

    /**
     * Executes the handler associated with the specified event, passing the specified context to it,
     * and then blocks the calling thread until the handler call is completed.
     *
     * @param event   the specified event, must be non-null
     * @param context the specified context
     * @param <T>     the type of the event context
     * @return true if the handler was found, false otherwise
     * @throws InterruptedException if the current thread was interrupted while waiting
     */
    <T> boolean fireNow(Event<T> event, T context) throws InterruptedException;

    <T> boolean fireNow(Event<T> event, Supplier<T> supplier) throws InterruptedException;

    /**
     * Executes handlers associated with the specified events, passing them the specified context.
     *
     * @param events  {@link Iterable} instance, containing the specified events, must be non-null
     * @param context the specified context
     * @param <T>     the type of the event context
     * @return a list containing all {@link Future} instances associated with the execution of the found handlers
     */
    <T> List<Future<Event<T>>> fire(Iterable<Event<T>> events, T context);

    <T> List<Future<Event<T>>> fire(Iterable<Event<T>> events, Supplier<T> supplier);

    /**
     * Executes handlers associated with the specified events, passing them the specified context,
     * and then blocks the calling thread until all handler calls are completed.
     *
     * @param events  {@link Iterable} instance, containing the specified events, must be non-null
     * @param context the specified context
     * @param <T>     the type of the event context
     * @return true if at least one handler was called, false otherwise
     * @throws InterruptedException if the current thread was interrupted while waiting
     */
    <T> boolean fireNow(Iterable<Event<T>> events, T context) throws InterruptedException;

    <T> boolean fireNow(Iterable<Event<T>> events, Supplier<T> supplier) throws InterruptedException;
}
