package io.github.amayaframework.events;

import java.util.Iterator;

final class GroupIterator implements Iterator<EventGroup> {
    private EventGroup node;

    GroupIterator(EventGroup node) {
        this.node = node;
    }

    @Override
    public boolean hasNext() {
        return node != null;
    }

    @Override
    public EventGroup next() {
        var ret = node;
        node = node.parent;
        return ret;
    }
}
