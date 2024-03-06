package io.github.amayaframework.events;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public final class CompletedFuture<T> implements Future<T> {
    private final T value;

    public CompletedFuture(T value) {
        this.value = value;
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
