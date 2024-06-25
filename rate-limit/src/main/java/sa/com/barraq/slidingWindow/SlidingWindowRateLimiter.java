package sa.com.barraq.slidingWindow;

import sa.com.barraq.RateLimiter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SlidingWindowRateLimiter extends RateLimiter {

    private final Object lock = new Object();
    private final Map<String, Map<Long, Long>> requests;
    private ScheduledExecutorService scheduler;

    public SlidingWindowRateLimiter(long timeWindowInMillis, long maxRequests) {
        super(timeWindowInMillis, maxRequests);
        this.requests = new HashMap<>();
        cleanRequestsAsync();
    }

    private void cleanRequestsAsync() {
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            long currentTime = Timestamp.valueOf(LocalDateTime.now()).getTime();
            synchronized (lock) {
                for (String userId : requests.keySet()) {
                    Set<Long> toRemoveKeys = new HashSet<>();
                    for (Long time : requests.get(userId).keySet()) {
                        if (time < currentTime - timeWindowInMillis) toRemoveKeys.add(time);
                    }
                    for (Long time : toRemoveKeys) requests.get(userId).remove(time);
                }
            }
        }, timeWindowInMillis, timeWindowInMillis, TimeUnit.MILLISECONDS);
    }

    public void closeScheduler() {
        scheduler.close();
    }

    @Override
    public boolean isRequestAllowed(String userId) {
        long currentTime = Timestamp.valueOf(LocalDateTime.now()).getTime();
        if (!requests.containsKey(userId)) {
            synchronized (lock) {
                requests.put(userId, new HashMap<>());
                requests.get(userId).put(currentTime, 1L);
            }
            return true;
        }
        Long count = 0L;
        synchronized (lock) {
            for (Long time : requests.get(userId).keySet()) {
                if (time < currentTime - timeWindowInMillis) continue;
                count += requests.get(userId).get(time);
            }
            if (requests.get(userId).containsKey(currentTime)) requests.get(userId).put(currentTime, requests.get(userId).get(currentTime) + 1);
            else requests.get(userId).put(currentTime, 1L);
        }
        return count < maxRequests;
    }
}
