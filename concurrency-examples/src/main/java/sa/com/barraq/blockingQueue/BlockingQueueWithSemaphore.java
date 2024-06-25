package sa.com.barraq.blockingQueue;

import sa.com.barraq.blockingQueue.semaphore.CountingSemaphore;

public class BlockingQueueWithSemaphore<T> {
    private final T[] array;
    public volatile int size = 0;
    public final int capacity;
    private int head = 0;
    private int tail = 0;
    private final CountingSemaphore semaphore;

    @SuppressWarnings("unchecked")
    public BlockingQueueWithSemaphore(int capacity) {
        array = (T[]) new Object[capacity];
        this.capacity = capacity;
        this.semaphore = new CountingSemaphore(capacity);
    }

    public void enqueue(T item) throws InterruptedException {
        semaphore.acquire();
        synchronized (this) {
            array[tail] = item;
            tail = (tail + 1) % capacity;
            size++;
        }
    }

    public T dequeue() throws InterruptedException {
        semaphore.release();
        T t;
        synchronized (this) {
            t = array[head];
            head = (head + 1) % capacity;
            size--;
        }
        return t;
    }
}
