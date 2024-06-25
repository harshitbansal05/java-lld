package sa.com.barraq.orderedPrinting;

import sa.com.barraq.semaphore.CountingSemaphore;

public class OrderedPrinting {

    CountingSemaphore semaphore = new CountingSemaphore(1, 0);
    CountingSemaphore sem = new CountingSemaphore(1, 0);

    public void printFirst() throws InterruptedException {
        System.out.println("First");
        semaphore.release();
    }

    public void printSecond() throws InterruptedException {
        semaphore.acquire();
        System.out.println("Second");
        sem.release();
    }

    public void printThird() throws InterruptedException {
        sem.acquire();
        System.out.println("Third");
    }
}
