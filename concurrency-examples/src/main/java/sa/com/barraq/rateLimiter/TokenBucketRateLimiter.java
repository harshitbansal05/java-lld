package sa.com.barraq.rateLimiter;

public class TokenBucketRateLimiter {

    private final int MAX_TOKENS;
    private long lastRequestTime = System.currentTimeMillis();
    long possibleTokens = 0;

    public TokenBucketRateLimiter(int maxTokens) {
        MAX_TOKENS = maxTokens;
    }

    synchronized void getToken() throws InterruptedException {
        possibleTokens += (System.currentTimeMillis() - lastRequestTime) / 1000;
        possibleTokens = Math.min(possibleTokens, MAX_TOKENS);

        if (possibleTokens == 0) {
            Thread.sleep(1000);
        } else possibleTokens--;
        lastRequestTime = System.currentTimeMillis();
    }
}
