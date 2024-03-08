package io.github.amayaframework.events;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * A stub class that implements the completed {@link Future}.
 *
 * @param <T>
 */
public final class CompletedFuture<T> implements Future<T> {
    public static final Future<?> EMPTY = new CompletedFuture<>(null);
    private final T value;

    /**
     * Constructs {@link CompletedFuture} instance with the specified resulting value.
     *
     * @param value the specified resulting value, may be null
     */
    public CompletedFuture(T value) {
        this.value = value;
    }

    /**
     * Creates {@link CompletedFuture} instance with null resulting value.
     *
     * @param <T> the type of resulting value
     * @return {@link Future} instance
     */
    @SuppressWarnings("unchecked")
    public static <T> Future<T> empty() {
        return (Future<T>) EMPTY;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return true;
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public T get(long timeout, TimeUnit unit) {
        return value;
    }
}
