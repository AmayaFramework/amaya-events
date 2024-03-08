package io.github.amayaframework.events;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

final class ParallelEventManager extends AbstractEventManager {
    private final ExecutorService executor;

    ParallelEventManager(EventRegistry registry, EventTrigger trigger, ExecutorService executor) {
        super(registry, trigger);
        this.executor = Objects.requireNonNull(executor);
    }

    @Override
    public void stop(long timeout, TimeUnit unit) {
        Objects.requireNonNull(unit);
        // Code literally from the oracle documentation
        executor.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!executor.awaitTermination(timeout, unit)) {
                executor.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!executor.awaitTermination(timeout, unit)) {
                    throw new IllegalStateException("Executor did not terminate");
                }
            }
        } catch (InterruptedException e) {
            // (Re-)Cancel if current thread also interrupted
            executor.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void stop() {
        executor.shutdown();
    }
}
