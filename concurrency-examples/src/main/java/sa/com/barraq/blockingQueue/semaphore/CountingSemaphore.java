package sa.com.barraq.blockingQueue.semaphore;

public class CountingSemaphore {

    private int usedPermits = 0;
    private final int maxCount;

    public CountingSemaphore(int count) {
        this.maxCount = count;
    }

    public CountingSemaphore(int count, int initialPermits) {
        this.maxCount = count;
        this.usedPermits = this.maxCount - initialPermits;
    }

    public synchronized void acquire() throws InterruptedException {
        while (usedPermits == maxCount) this.wait();
        usedPermits++;
        this.notifyAll();
    }

    public synchronized void release() throws InterruptedException {
        while (usedPermits == 0) this.wait();
        usedPermits--;
        this.notifyAll();
    }
}
