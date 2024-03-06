package io.github.amayaframework.events;

import java.util.Objects;
import java.util.StringTokenizer;

public final class SplitEventFactory implements EventFactory {
    private static final EventGroup EMPTY = new EventGroup("");
    private final String delimiter;

    public SplitEventFactory(String delimiter) {
        Objects.requireNonNull(delimiter);
        if (delimiter.isBlank()) {
            throw new IllegalArgumentException("Blank delimiter");
        }
        this.delimiter = delimiter;
    }

    private String[] split(String value) {
        var tokenizer = new StringTokenizer(value, delimiter);
        var count = tokenizer.countTokens();
        if (count < 0) {
            throw new IllegalArgumentException("Invalid event group");
        }
        var ret = new String[count];
        for (var i = 0; i < count; ++i) {
            ret[i] = tokenizer.nextToken();
        }
        return ret;
    }

    @Override
    public EventGroup create(String group) {
        if (group.isBlank()) {
            return EMPTY;
        }
        var tokens = split(group);
        var builder = new StringBuilder();
        var node = (EventGroup) null;
        for (var token : tokens) {
            builder.append(delimiter).append(token);
            node = new EventGroup(builder.toString(), node);
        }
        return node;
    }

    @Override
    public Event create(String event, FirePolicy policy) {
        var index = event.lastIndexOf(delimiter);
        if (index < 0) {
            return new Event(EMPTY, event, policy);
        }
        var group = create(event.substring(0, index));
        var name = event.substring(index + 1);
        return new Event(group, name, policy);
    }
}
