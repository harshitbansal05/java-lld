package sa.com.barraq.models;

import java.util.concurrent.atomic.LongAdder;

public class AccessDetails {
    private final LongAdder accessCount;
    private final long lastAccessTime;

    public AccessDetails(long lastAccessTime) {
        accessCount = new LongAdder();
        this.lastAccessTime = lastAccessTime;
    }

    public long getLastAccessTime() {
        return lastAccessTime;
    }

    public int getAccessCount() {
        return (int) accessCount.longValue();
    }

    public AccessDetails update(long lastAccessTime) {
        final AccessDetails accessDetails = new AccessDetails(lastAccessTime);
        accessDetails.accessCount.add(this.accessCount.longValue() + 1);
        return accessDetails;
    }
}
