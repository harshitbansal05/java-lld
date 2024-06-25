package sa.com.barraq;

public abstract class RateLimiter {
    public final long timeWindowInMillis;
    public final long maxRequests;

    public RateLimiter(long timeWindowInMillis, long maxRequests) {
        this.maxRequests = maxRequests;
        this.timeWindowInMillis = timeWindowInMillis;
    }

    public abstract boolean isRequestAllowed(String userId);
}
