package io.github.amayaframework.events;

import com.github.romanqed.jfunc.Runnable1;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Supplier;

/**
 * A class that provides a skeletal implementation of an {@link EventTrigger}
 * containing the definition of all methods.
 */
public abstract class AbstractEventTrigger implements EventTrigger {
    protected final EventProvider provider;

    /**
     * Constructs {@link AbstractEventTrigger} instance with the specified event provider.
     *
     * @param provider the specified {@link EventProvider} instance, must be non-null
     */
    protected AbstractEventTrigger(EventProvider provider) {
        this.provider = Objects.requireNonNull(provider);
    }

    private static void await(Future<?> future) throws InterruptedException {
        try {
            future.get();
        } catch (InterruptedException | Error | RuntimeException e) {
            throw e;
        } catch (ExecutionException e) {
            throw new RuntimeException(e.getCause());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Executes a call to the specified event handler.
     *
     * @param event   the specified event, must be non-null
     * @param body    the specified event handler, must be non-null
     * @param context the specified event context
     * @param <T>     the type of the event context
     * @return {@link Future} instance bound with event execution
     */
    protected abstract <T> Future<Event<T>> execute(Event<T> event, Runnable1<T> body, T context);

    @Override
    public <T> Future<Event<T>> fire(Event<T> event, T context) {
        var handler = provider.get(event);
        if (handler == null) {
            return null;
        }
        return execute(event, handler, context);
    }

    @Override
    public <T> List<Future<Event<T>>> fire(Iterable<Event<T>> events, T context) {
        var ret = new LinkedList<Future<Event<T>>>();
        for (var event : events) {
            var handler = provider.get(event);
            if (handler == null) {
                continue;
            }
            ret.add(execute(event, handler, context));
        }
        return ret;
    }

    @Override
    public <T> boolean fireNow(Event<T> event, T context) throws InterruptedException {
        var future = fire(event, context);
        if (future == null) {
            return false;
        }
        await(future);
        return true;
    }

    @Override
    public <T> boolean fireNow(Iterable<Event<T>> events, T context) throws InterruptedException {
        var ret = false;
        for (var event : events) {
            var future = fire(event, context);
            if (future == null) {
                continue;
            }
            await(future);
            ret = true;
        }
        return ret;
    }

    @Override
    public <T> Future<Event<T>> fire(Event<T> event, Supplier<T> supplier) {
        var handler = provider.get(event);
        if (handler == null) {
            return null;
        }
        return execute(event, handler, supplier.get());
    }

    @Override
    public <T> boolean fireNow(Event<T> event, Supplier<T> supplier) throws InterruptedException {
        var future = fire(event, supplier);
        if (future == null) {
            return false;
        }
        await(future);
        return true;
    }

    @Override
    public <T> List<Future<Event<T>>> fire(Iterable<Event<T>> events, Supplier<T> supplier) {
        var ret = new LinkedList<Future<Event<T>>>();
        var context = (T) null;
        for (var event : events) {
            var handler = provider.get(event);
            if (handler == null) {
                continue;
            }
            if (context == null) {
                context = supplier.get();
            }
            ret.add(execute(event, handler, context));
        }
        return ret;
    }

    @Override
    public <T> boolean fireNow(Iterable<Event<T>> events, Supplier<T> supplier) throws InterruptedException {
        var ret = false;
        var context = (T) null;
        for (var event : events) {
            var handler = provider.get(event);
            if (handler == null) {
                continue;
            }
            if (context == null) {
                context = supplier.get();
            }
            await(execute(event, handler, context));
            ret = true;
        }
        return ret;
    }
}
