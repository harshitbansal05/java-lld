package sa.com.barraq.events;

import sa.com.barraq.models.Record;

public class Write<K, V> extends Event<K, V> {

    public Write(Record<K, V> element, long timestamp) {
        super(element, timestamp);
    }
}
