package sa.com.barraq.blockingQueue;

public class BlockingQueue<T> {

    private final T[] array;
    public volatile int size = 0;
    public final int capacity;
    private int head = 0;
    private int tail = 0;

    @SuppressWarnings("unchecked")
    public BlockingQueue(int capacity) {
        array = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

    public void enqueue(T item) throws InterruptedException {
        synchronized (this) {
            while (size == capacity) {
                this.wait();
            }
            array[tail] = item;
            tail = (tail + 1) % capacity;
            size++;
            this.notifyAll();
        }
    }

    public T dequeue() throws InterruptedException {
        T t;
        synchronized (this) {
            while (size == capacity) {
                this.wait();
            }
            t = array[head];
            head = (head + 1) % capacity;
            size--;
            this.notifyAll();
        }
        return t;
    }
}
