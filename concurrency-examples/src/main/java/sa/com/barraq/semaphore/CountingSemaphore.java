package sa.com.barraq.semaphore;

public class CountingSemaphore {
    private final int maxCount;
    private int usedPermits;

    public CountingSemaphore(int maxCount) {
        this.maxCount = maxCount;
        this.usedPermits = 0;
    }

    public CountingSemaphore(int maxCount, int initialPermits) {
        this.maxCount = maxCount;
        this.usedPermits = maxCount - initialPermits;
    }

    public void acquire() throws InterruptedException {
        synchronized (this) {
            while (usedPermits == maxCount) this.wait();
            usedPermits++;
            this.notifyAll();
        }
    }

    public void release() throws InterruptedException {
        synchronized (this) {
            while (usedPermits == 0) this.wait();
            usedPermits--;
            this.notifyAll();
        }
    }
}
