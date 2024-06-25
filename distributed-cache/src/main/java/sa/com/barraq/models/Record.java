package sa.com.barraq.models;

public class Record<K, V> {
    private final K key;
    private final V value;
    private final long insertionTime;
    private AccessDetails accessDetails;

    public Record(K key, V value, long insertionTime) {
        this.key = key;
        this.value = value;
        this.insertionTime = insertionTime;
        this.accessDetails = new AccessDetails(insertionTime);
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public long getInsertionTime() {
        return insertionTime;
    }

    public AccessDetails getAccessDetails() {
        return accessDetails;
    }

    public void setAccessDetails(AccessDetails accessDetails) {
        this.accessDetails = accessDetails;
    }

    @Override
    public String toString() {
        return "Record { " +
                "key = " + key +
                ", value = " + value +
                ", insertionTime = " + insertionTime +
                ", accessDetails = " + accessDetails +
                " }";
    }
}
