package io.github.amayaframework.events;

import com.github.romanqed.jfunc.Exceptions;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public final class ParallelEventManager extends AbstractEventManager {
    private final ExecutorService executor;

    public ParallelEventManager(EventRegistry registry, EventTrigger trigger, ExecutorService executor) {
        super(registry, trigger);
        this.executor = Objects.requireNonNull(executor);
    }

    @Override
    public void stop(long timeout, TimeUnit unit) {
        Exceptions.suppress(() -> executor.awaitTermination(timeout, unit));
        executor.shutdown();
    }

    @Override
    public void stop() {
        executor.shutdown();
    }

    @Override
    public void halt() {
        executor.shutdownNow();
    }
}
