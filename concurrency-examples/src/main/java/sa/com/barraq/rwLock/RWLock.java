package sa.com.barraq.rwLock;

public class RWLock {

    private int readers = 0;
    private boolean isWrite = false;

    RWLock() {}

    public synchronized void acquireReadLock() throws InterruptedException {
        while (isWrite) wait();
        readers++;
    }

    public synchronized void releaseReadLock() {
        readers--;
        notifyAll();
    }

    public synchronized void acquireWriteLock() throws InterruptedException {
        while (isWrite || readers > 0) wait();
        isWrite = true;
    }

    public synchronized void releaseWriteLock() {
        isWrite = false;
        notifyAll();
    }
}
