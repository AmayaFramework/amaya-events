package io.github.amayaframework.events;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;

/**
 * An implementation of a {@link EventManagerFactory} that creates managers
 * that use a {@link ParallelEventTrigger}.
 */
public final class ParallelManagerFactory implements EventManagerFactory {
    private final Supplier<ExecutorService> supplier;

    /**
     * Constructs {@link ParallelManagerFactory} instance with the specified {@link ExecutorService} supplier.
     *
     * @param supplier the specified {@link Supplier} instance, must be non-null
     */
    public ParallelManagerFactory(Supplier<ExecutorService> supplier) {
        this.supplier = Objects.requireNonNull(supplier);
    }

    @Override
    public EventManager create() {
        var registry = new TableEventRegistry();
        var executor = supplier.get();
        var trigger = new ParallelEventTrigger(registry, executor);
        return new ParallelEventManager(registry, trigger, executor);
    }
}
