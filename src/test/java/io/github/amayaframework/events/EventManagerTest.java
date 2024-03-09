package io.github.amayaframework.events;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

public class EventManagerTest extends Assertions {
    private static final Event<Context> EVENT = Event.of("event", Context.class);

    public void testEventManager(EventManager manager) throws InterruptedException {
        var context = new Context();
        manager.set(EVENT, ctx -> ctx.value = 5);
        manager.fireNow(EVENT, context);
        assertEquals(5, context.value);
        manager.stop();
    }

    @Test
    public void testBlockingEventManager() throws InterruptedException {
        var factory = new BlockingManagerFactory();
        testEventManager(factory.create());
    }

    @Test
    public void testParallelEventManager() throws InterruptedException {
        var factory = new ParallelManagerFactory(ForkJoinPool::commonPool);
        testEventManager(factory.create());
    }

    private static final class Context {
        private int value;
    }
}
