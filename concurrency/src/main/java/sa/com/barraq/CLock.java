package sa.com.barraq;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class CLock {

    private void lockSupport() throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        Thread childThread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " about to park.");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e ) {
                // ignore
            }
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + " un parked.");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e ) {
                // ignore
            }
        });

        childThread.start();

        // wait for the childThread to start
        cyclicBarrier.await();
        System.out.println("Main thread about to un park " + childThread.getName());
        LockSupport.unpark(childThread);
        cyclicBarrier.await();
        System.out.println("Main thread exiting");
    }

    static class FIFOLock {

        ConcurrentLinkedQueue<Thread> q = new ConcurrentLinkedQueue<>();
        AtomicBoolean locked = new AtomicBoolean(false);

        public void lock() {
            Thread thread = Thread.currentThread();
            q.add(thread);
            while (q.peek() != thread || !locked.compareAndSet(false, true)) LockSupport.park();
            q.remove();
        }

        public void unLock() {
            locked.set(false);
            LockSupport.unpark(q.peek());
        }
    }

    public void fifoLock() {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        FIFOLock lock = new FIFOLock();
        try {
            for (int i = 0; i < 5; i++) {
                executor.submit(() -> {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " acquires the lock.");
                    // simulate work by sleeping
                    try {
                        Thread.sleep(ThreadLocalRandom.current().nextInt(20));
                    } catch (InterruptedException ie) {
                        // ignore for now
                    }
                    lock.unLock();
                });
            }
        } finally {
            executor.shutdown();
        }
    }

    public void rLock() throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newFixedThreadPool(5);
        ReentrantLock lock = new ReentrantLock();
        Runnable threadA = () -> threadA(lock);
        Runnable threadB = () -> threadB(lock);
        try {
            lock.lock();
            lock.lock();
            lock.lock();

            System.out.println("Main thread lock hold count = " + lock.getHoldCount());

            // submit other threads
            Future future1 = es.submit(threadA);
            Future future2 = es.submit(threadB);
            for (int i = 0; i < 3; i++) {
                Thread.sleep(50);
                lock.unlock();
            }

            System.out.println("Main thread released lock. Lock hold count = " + lock.getHoldCount());
            future1.get();
            future2.get();
        } finally {
            // Make sure to release the locks if an exception occurs
            for (int i = 0; i < lock.getHoldCount(); i++) {
                lock.unlock();
            }
            // Shutdown the executor service
            es.shutdown();
        }
    }

    private void threadB(ReentrantLock lock) {
        lock.lock();
        lock.unlock();
    }

    private void threadA(ReentrantLock lock) {
        String name = "THREAD-A";
        Thread.currentThread().setName(name);
        boolean keepTrying = true;

        System.out.println("Is lock owned by any other thread = " + lock.isLocked());
        while (keepTrying) {
            System.out.println(name + " trying to acquire lock");
            if (lock.tryLock()) {
                try {
                    System.out.println(name + " acquired lock");
                    keepTrying = false;
                } finally {
                    lock.unlock();
                    System.out.println(name + " released lock");
                }
            } else {
                System.out.println(name + " failed to acquire lock. Other threads waiting = " + lock.getQueueLength());
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException ie) {
                // ignore exception.
            }
        }
    }
}
