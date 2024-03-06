package io.github.amayaframework.events;

import com.github.romanqed.jfunc.Runnable1;

public interface EventRegistry extends EventProvider {

    void set(Event event, Runnable1<?> body);

    void add(Event event, Runnable1<?> body);

    boolean contains(EventGroup group);

    boolean contains(Event event);

    boolean remove(EventGroup group);

    boolean remove(Event event);
}
