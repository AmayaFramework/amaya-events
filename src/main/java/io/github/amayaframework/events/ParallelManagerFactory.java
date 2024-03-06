package io.github.amayaframework.events;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;

public final class ParallelManagerFactory implements EventManagerFactory {
    private final Supplier<ExecutorService> supplier;

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
