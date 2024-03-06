//package io.github.amayaframework.events;
//
//import com.github.romanqed.jfunc.Runnable1;
//
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Objects;
//import java.util.function.Supplier;
//
///**
// *
// */
//public class TableEventRegistry implements EventRegistry {
//    private final Map<Event, RunnableHolder> events;
//
//    /**
//     * @param supplier
//     */
//    @SuppressWarnings("unchecked")
//    public TableEventRegistry(Supplier<Map<?, ?>> supplier) {
//        this.events = (Map<Event, RunnableHolder>) supplier.get();
//    }
//
//    /**
//     *
//     */
//    public TableEventRegistry() {
//        this.events = new HashMap<>();
//    }
//
//    @Override
//    public Runnable1<Object> get(Event event) {
//        return events.get(event);
//    }
//
////    @Override
////    public Map<Event, Runnable1<Object>> get(EventGroup group) {
////        var ret = groups.get(group);
////        if (ret == null) {
////            return null;
////        }
////        return Collections.unmodifiableMap(ret);
////    }
//
//    private void innerSet(Event event, Runnable1<?> body) {
//        var holder = events.get(event);
//        if (holder != null) {
//            holder.setBody(body);
//            return;
//        }
//        holder = new RunnableHolder(body);
//        events.put(event, holder);
//        var enclosings = event.getGroup().getEnclosingGroups();
//        for (var group : enclosings) {
//            var map = groups.computeIfAbsent(group, k -> new HashMap<>());
//            map.put(event, holder);
//        }
//    }
//
//    @Override
//    public void set(Event event, Runnable1<?> body) {
//        Objects.requireNonNull(event);
//        Objects.requireNonNull(body);
//        innerSet(event, body);
//    }
//
//    @Override
//    @SuppressWarnings("unchecked")
//    public void add(Event event, Runnable1<?> body) {
//        Objects.requireNonNull(event);
//        Objects.requireNonNull(body);
//        if (!events.containsKey(event)) {
//            innerSet(event, body);
//            return;
//        }
//        var holder = events.get(event);
//        var combined = Runnable1.combine(holder.getBody(), (Runnable1<Object>) body);
//        holder.setBody(combined);
//    }
//
//    @Override
//    public boolean contains(EventGroup group) {
//        return groups.containsKey(group);
//    }
//
//    @Override
//    public boolean contains(Event event) {
//        return events.containsKey(event);
//    }
//
//    @Override
//    public boolean remove(EventGroup group) {
//        if (!groups.containsKey(group)) {
//            return false;
//        }
//        var keys = groups.remove(group).keySet();
//        keys.forEach(this::innerRemove);
//        return true;
//    }
//
//    private void innerRemove(Event event) {
//        events.remove(event);
//        var enclosings = event.getGroup().getEnclosingGroups();
//        for (var group : enclosings) {
//            var value = groups.get(group);
//            value.remove(event);
//            if (value.isEmpty()) {
//                groups.remove(group);
//            }
//        }
//    }
//
//    @Override
//    public boolean remove(Event event) {
//        if (!events.containsKey(event)) {
//            return false;
//        }
//        innerRemove(event);
//        return true;
//    }
//}
