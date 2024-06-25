package sa.com.barraq.tokenBucket;

import sa.com.barraq.RateLimiter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TokenBucketRateLimiter extends RateLimiter {

    private final Map<String, Long> requests;
    private final Object lock = new Object();
    private ScheduledExecutorService scheduler;

    public TokenBucketRateLimiter(long timeWindowInMillis, long maxRequests) {
        super(timeWindowInMillis, maxRequests);
        this.requests = new HashMap<>();
        refillTokensAsync();
    }

    private void refillTokensAsync() {
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            synchronized (lock) {
                requests.replaceAll((i, v) -> maxRequests);
            }
        }, timeWindowInMillis, timeWindowInMillis, TimeUnit.MILLISECONDS);
    }

    public void closeScheduler() {
        scheduler.close();
    }

    @Override
    public boolean isRequestAllowed(String userId) {
        boolean isRequestAllowed = true;
        synchronized (lock) {
            if (requests.containsKey(userId)) {
                if (requests.get(userId).equals(0L)) isRequestAllowed = false;
                else requests.put(userId, requests.get(userId) - 1);
            } else requests.put(userId, maxRequests - 1);
        }
        return isRequestAllowed;
    }
}
