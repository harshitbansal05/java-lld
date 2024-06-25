package sa.com.barraq.blockingQueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueueWithMutex<T> {

    private final T[] array;
    public volatile int size = 0;
    public final int capacity;
    private int head = 0;
    private int tail = 0;
    private final ReentrantLock lock;
    private final Condition condition;

    @SuppressWarnings("unchecked")
    public BlockingQueueWithMutex(int capacity) {
        array = (T[]) new Object[capacity];
        this.lock = new ReentrantLock();
        this.condition = lock.newCondition();
        this.capacity = capacity;
    }

    public void enqueue(T item) throws InterruptedException {
        lock.lock();
        while (size == capacity) {
            condition.await();
        }
        array[tail] = item;
        tail = (tail + 1) % capacity;
        size++;
        lock.unlock();
        condition.signalAll();
    }

    public T dequeue() throws InterruptedException {
        lock.lock();
        while (size == capacity) {
            condition.await();
        }
        T t = array[head];
        head = (head + 1) % capacity;
        size--;
        lock.unlock();
        condition.signalAll();
        return t;
    }
}
