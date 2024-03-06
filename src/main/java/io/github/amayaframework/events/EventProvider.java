package io.github.amayaframework.events;

import com.github.romanqed.jfunc.Runnable1;

import java.util.Map;

public interface EventProvider {
    Runnable1<Object> get(Event event);

    Map<Event, Runnable1<Object>> get(EventGroup group);
}
