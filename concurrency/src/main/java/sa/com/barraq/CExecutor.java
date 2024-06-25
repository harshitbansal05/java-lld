package sa.com.barraq;

import java.util.concurrent.*;

/**
 * Java provides 3 interfaces to manage thread lifecycle.
 * These are: Executor, ExecutorService, and ScheduledExecutorService.
 * The Executor interface forms the basis for the asynchronous task execution framework in Java.
 * The Executor requires implementing classes to define a method 'execute (Runnable runnable)' which takes in an object of interface Runnable.
 * Thread pools in Java are implementations of the Executor interface or any of its sub-interfaces. Thread pools allow us to decouple task submission and execution.
 * A thread pool consists of homogenous worker threads that are assigned to execute tasks. Once a worker thread finishes a task, it is returned to the pool. Usually, thread pools are bound to a queue from which tasks are dequeued for execution by worker threads.
 * Java has preconfigured thread pool implementations that can be instantiated using the factory methods of the Executors class. The important ones are listed below:
 * - newFixedThreadPool: This type of pool has a fixed number of threads and any number of tasks can be submitted for execution. Once a thread finishes a task, it can reused to execute another task from the queue.
 * - newSingleThreadExecutor: This executor uses a single worker thread to take tasks off of queue and execute them. If the thread dies unexpectedly, then the executor will replace it with a new one.
 * - newCachedThreadPool: This pool will create new threads as required and use older ones when they become available. However, it'll terminate threads that remain idle for a certain configurable period of time to conserve memory. This pool can be a good choice for short-lived asynchronous tasks.
 * - newScheduledThreadPool: This pool can be used to execute tasks periodically or after a delay.
 * An executor has the following stages in its life-cycle: Running, Shutting Down, Terminated.
 * JVM can't exit unless all non-daemon thread have terminated.
 * Executors can be made to shut down either abruptly or gracefully. When doing the former, the executor attempts to cancel all tasks in progress and doesn't work on any enqueued ones, whereas when doing the latter, the executor gives a chance for tasks already in execution to complete but also completes the enqueued tasks. If shutdown is initiated then the executor will refuse to accept new tasks and if any are submitted, they can be handled by providing a RejectedExecutionHandler.
 */
public class CExecutor {

    void receiveAndExecuteClientOrdersBest() {
        int expectedConcurrentOrders = 100;
        Executor executor = Executors.newFixedThreadPool(expectedConcurrentOrders);
        while (true) {
            /* final Order order = waitForNextOrder(); */
            executor.execute(() -> System.out.println("Processing an order"));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        /*
        create an instance of the ThreadPoolExecutor
        public ThreadPoolExecutor(
                         int corePoolSize, // When the pool has less than corePoolSize threads and a new task arrives, a new thread is instantiated even if other threads in the pool are idle.
                         int maximumPoolSize, // When the pool has more than corePoolSize threads but less than maximumPoolSize threads then a new thread is only created if the queue that holds the submitted tasks is full. The maximum number of threads that can be created is capped by maximumPoolSize.
                         long keepAliveTime, // A thread pool will eliminate threads in excess of corePoolSize after keepAliveTime has elapsed. The unit argument specifies the TimeUnit for the passed-in value of keepAliveTime, which. can be milliseconds, minutes, hours etc.
                         TimeUnit unit,
                         BlockingQueue<Runnable> workQueue,
                         RejectedExecutionHandler handler
        );
         */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 5, 1,
                TimeUnit.MINUTES, new LinkedBlockingDeque<>(3), new ThreadPoolExecutor.AbortPolicy());
        try {
            // submit six tasks
            for (int i = 0; i < 6; i++) {
                threadPoolExecutor.submit(() -> {
                    System.out.println("This is worker thread " + Thread.currentThread().getName() + " executing");
                    try {
                        // simulate work by sleeping for 1 second
                        Thread.sleep(1000);
                    } catch (InterruptedException ie) {
                        // ignore for now
                    }
                });
            }
        } finally {
            threadPoolExecutor.shutdown();
        }
    }

    private void threadExecutorWithSynchronousQueue() throws InterruptedException {
        // create a ThreadPoolExecutor with a SynchronousQueue to implement the direct handoff strategy. The pool has
        // a maximum of 5 threads. Since we aren't passing-in the RejectionHandler, the default AbortPolicy will be used.
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 5, 1,
                TimeUnit.MINUTES, new SynchronousQueue());
        int i = 0;
        try {
            // Try to submit 50 tasks
            for (; i < 50; i++) {
                threadPoolExecutor.execute(() -> {
                    try {
                        // simulate work by sleeping for 1 second
                        System.out.println("Thread " + Thread.currentThread().getName() + " at work.");
                        Thread.sleep(1000);
                    } catch (InterruptedException ie) {
                        // ignore for now
                    }
                });
            }
        } catch (RejectedExecutionException ree) {
            // Let's see which task gets rejected
            System.out.println("Task " + (i + 1) + " rejected.");
        } finally {
            // don't forget to shutdown the executor
            threadPoolExecutor.shutdown();

            // wait for the executor to shutdown
            threadPoolExecutor.awaitTermination(1, TimeUnit.HOURS);
        }
    }

    private void threadPoolWithUnboundedQueue() throws InterruptedException {
        // create a ThreadPoolExecutor with a LinkedBlockingDeque to implement the unbounded queue strategy. The pool has
        // a maximum of 5 threads. Since we aren't passing-in the RejectionHandler, the default AbortPolicy will be used.
        // Note that the maximumPoolSize setting doesn't have any effect since only corePoolSize threads are ever created
        // because the queue has indefinite (theoretically) capacity.
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5, 1,
                TimeUnit.MINUTES, new LinkedBlockingDeque<>());

        int i = 0;
        try {
            // Try to submit 20 tasks
            for (; i < 20; i++) {
                threadPoolExecutor.execute(() -> {
                    try {
                        // simulate work by sleeping for 1 second
                        System.out.println("Thread " + Thread.currentThread().getName() + " at work.");
                        Thread.sleep(1000);
                    } catch (InterruptedException ie) {
                        // ignore for now
                    }
                });
            }
        } catch (RejectedExecutionException ree) {
            // Let's see which task gets rejected
            System.out.println("Task " + (i + 1) + " rejected.");
        } finally {
            // don't forget to shutdown the executor
            threadPoolExecutor.shutdown();

            // wait for the executor to shutdown
            threadPoolExecutor.awaitTermination(1, TimeUnit.HOURS);
        }
    }

    private void threadPoolWithBoundedQueue() throws InterruptedException {
        // create a ThreadPoolExecutor with a LinkedBlockingDeque to implement the unbounded queue strategy. The pool has
        // a maximum of 5 threads. Since we aren't passing-in the RejectionHandler, the default AbortPolicy will be used.
        // The queue has a defined capacity so the setting maximumPoolSize does take effect.
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5, 1,
                TimeUnit.MINUTES, new LinkedBlockingDeque<>(5));
        int i = 0;
        try {
            // Try to submit 20 tasks
            for (; i < 20; i++) {
                threadPoolExecutor.execute(() -> {
                    try {
                        // simulate work by sleeping for 1 second
                        System.out.println("Thread " + Thread.currentThread().getName() + " at work.");
                        Thread.sleep(1000);
                    } catch (InterruptedException ie) {
                        // ignore for now
                    }
                });
            }
        } catch (RejectedExecutionException ree) {
            // Let's see which task gets rejected
            System.out.println("Task " + (i + 1) + " rejected.");
        } finally {
            // don't forget to shutdown the executor
            threadPoolExecutor.shutdown();

            // wait for the executor to shutdown
            threadPoolExecutor.awaitTermination(1, TimeUnit.HOURS);
        }
    }
}
