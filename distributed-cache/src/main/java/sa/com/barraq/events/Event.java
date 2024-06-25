package sa.com.barraq.events;

import sa.com.barraq.models.Record;

import java.util.UUID;

public abstract class Event<K, V> {
    private final String id;
    private final Record<K, V> element;
    private final long timestamp;

    public Event(Record<K, V> element, long timestamp) {
        this.element = element;
        this.timestamp = timestamp;
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public Record<K, V> getElement() {
        return element;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
