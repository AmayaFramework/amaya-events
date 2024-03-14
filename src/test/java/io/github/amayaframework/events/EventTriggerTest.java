package io.github.amayaframework.events;

import com.github.romanqed.jfunc.Runnable1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class EventTriggerTest extends Assertions {
    private static final Event<Context> EVENT1 = Event.of("event1", Context.class);
    private static final Event<Context> EVENT2 = Event.of("event2", Context.class);
    private static final Event<Context> UNKNOWN_EVENT = Event.of("unknown", Context.class);
    private static final EventTrigger BLOCKING_TRIGGER = new BlockingEventTrigger(EventTriggerTest::provide);
    private static final EventTrigger PARALLEL_TRIGGER = new ParallelEventTrigger(
            EventTriggerTest::provide,
            ForkJoinPool.commonPool()
    );

    @SuppressWarnings("unchecked")
    private static <T> Runnable1<T> provide(Event<T> event) {
        if (EVENT1.equals(event)) {
            return (Runnable1<T>) (Runnable1<Context>) ctx -> ctx.value = 1;
        }
        if (EVENT2.equals(event)) {
            return (Runnable1<T>) (Runnable1<Context>) ctx -> ctx.value = 2;
        }
        return null;
    }

    public void testUnknownEvent(EventTrigger trigger) {
        assertAll(
                () -> assertNull(trigger.fire(UNKNOWN_EVENT, (Context) null)),
                () -> assertFalse(trigger.fireNow(UNKNOWN_EVENT, (Context) null)),
                () -> assertTrue(trigger.fire(List.of(UNKNOWN_EVENT), (Context) null).isEmpty()),
                () -> assertFalse(trigger.fireNow(List.of(UNKNOWN_EVENT), (Context) null))
        );
    }

    public void testFire(EventTrigger trigger) {
        var context = new Context();
        assertAll(
                () -> {
                    var future = trigger.fire(EVENT1, context);
                    assertNotNull(future);
                    assertEquals(EVENT1, future.get());
                    assertEquals(1, context.value);
                },
                () -> {
                    var future = trigger.fire(EVENT2, context);
                    assertNotNull(future);
                    assertEquals(EVENT2, future.get());
                    assertEquals(2, context.value);
                }
        );
    }

    public void testFireNow(EventTrigger trigger) {
        var context = new Context();
        assertAll(
                () -> {
                    trigger.fireNow(EVENT1, context);
                    assertEquals(1, context.value);
                },
                () -> {
                    trigger.fireNow(EVENT2, context);
                    assertEquals(2, context.value);
                }
        );
    }

    public void testFireAll(EventTrigger trigger) {
        var context = new Context();
        assertAll(() -> {
            var list = trigger.fire(List.of(EVENT1, EVENT2, UNKNOWN_EVENT), context);
            assertNotNull(list);
            assertEquals(2, list.size());
            for (var future : list) {
                assertNotNull(future);
                var v = future.get();
                assertTrue(v.equals(EVENT1) || v.equals(EVENT2));
            }
        });
    }

    public void testFireAllNow(EventTrigger trigger) {
        var context = new Context();
        assertAll(
                () -> assertTrue(trigger.fireNow(List.of(EVENT1, EVENT2, UNKNOWN_EVENT), context)),
                () -> assertTrue(context.value == 1 || context.value == 2)
        );
    }

    @Test
    public void testBlockingUnknownEvent() {
        testUnknownEvent(BLOCKING_TRIGGER);
    }

    @Test
    public void testParallelUnknownEvent() {
        testUnknownEvent(PARALLEL_TRIGGER);
    }

    @Test
    public void testBlockingFire() {
        testFire(BLOCKING_TRIGGER);
    }

    @Test
    public void testParallelFire() {
        testFire(PARALLEL_TRIGGER);
    }

    @Test
    public void testBlockingFireNow() {
        testFireNow(BLOCKING_TRIGGER);
    }

    @Test
    public void testParallelFireNow() {
        testFireNow(PARALLEL_TRIGGER);
    }

    @Test
    public void testBlockingFireAll() {
        testFireAll(BLOCKING_TRIGGER);
    }

    @Test
    public void testParallelFireAll() {
        testFireAll(PARALLEL_TRIGGER);
    }

    @Test
    public void testBlockingFireAllNow() {
        testFireAllNow(BLOCKING_TRIGGER);
    }

    @Test
    public void testParallelFireAllNow() {
        testFireAllNow(PARALLEL_TRIGGER);
    }

    private static final class Context {
        private int value;
    }
}
