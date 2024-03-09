package io.github.amayaframework.events;

import com.github.romanqed.jfunc.Runnable1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EventRegistryTest extends Assertions {
    private static final Event<Context> EVENT1 = Event.of("event1", Context.class);
    private static final Event<Context> EVENT2 = Event.of("event2", Context.class);
    private static final Event<Context> UNKNOWN_EVENT = Event.of("unknown", Context.class);
    private static final EventRegistry TABLE_REGISTRY = new TableEventRegistry();

    public void testUnknownEvent(EventRegistry registry) {
        assertAll(
                () -> assertNull(registry.get(UNKNOWN_EVENT)),
                () -> assertFalse(registry.contains(UNKNOWN_EVENT))
        );
    }

    public void testEventHandlers(EventRegistry registry) {
        Runnable1<Context> handler1 = ctx -> ctx.value = 1;
        Runnable1<Context> handler2 = ctx -> ctx.value = 2;
        registry.set(EVENT1, handler1);
        registry.set(EVENT2, handler2);
        assertAll(
                () -> assertEquals(handler1, registry.get(EVENT1)),
                () -> assertEquals(handler2, registry.get(EVENT2)),
                () -> assertTrue(registry.contains(EVENT1)),
                () -> assertTrue(registry.contains(EVENT2)),
                () -> assertTrue(registry.remove(EVENT1)),
                () -> assertTrue(registry.remove(EVENT2))
        );
    }

    public void testAddingHandlers(EventRegistry registry) {
        registry.add(EVENT1, ctx -> ctx.value += 1);
        registry.add(EVENT1, ctx -> ctx.value += 2);
        var handler = registry.get(EVENT1);
        var context = new Context();
        assertAll(
                () -> assertNotNull(handler),
                () -> {
                    handler.run(context);
                    assertEquals(3, context.value);
                }
        );
    }

    @Test
    public void testTableUnknownEvent() {
        testUnknownEvent(TABLE_REGISTRY);
    }

    @Test
    public void testTableEventHandlers() {
        testEventHandlers(TABLE_REGISTRY);
    }

    @Test
    public void testTableAddingHandlers() {
        testAddingHandlers(TABLE_REGISTRY);
    }

    private static final class Context {
        private int value;
    }
}
