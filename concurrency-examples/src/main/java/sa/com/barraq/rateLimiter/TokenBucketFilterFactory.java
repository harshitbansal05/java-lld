package sa.com.barraq.rateLimiter;

public class TokenBucketFilterFactory {

    private TokenBucketFilterFactory() {
    }

    static public TokenBucketFilter makeTokenBucketFilter(int capacity) {
        MultithreadedTokenBucketFilter tbf = new MultithreadedTokenBucketFilter(capacity);
        tbf.initialize();
        return tbf;
    }

    private static class MultithreadedTokenBucketFilter extends TokenBucketFilter {

        private final int capacity;
        private long possibleTokens = 0;

        MultithreadedTokenBucketFilter(int capacity) {
            this.capacity = capacity;
        }

        public void initialize() {
            Thread dt = new Thread(this::daemonThread);
            dt.setDaemon(true);
            dt.start();
        }

        private void daemonThread() {
            while (true) {
                synchronized (this) {
                    possibleTokens = Math.min(capacity, ++possibleTokens);
                    this.notify();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // swallow exception
                }
            }
        }

        public void getToken() throws InterruptedException {
            synchronized (this) {
                while (possibleTokens == 0) {
                    this.wait();
                }
                possibleTokens--;
            }
        }
    }
}
