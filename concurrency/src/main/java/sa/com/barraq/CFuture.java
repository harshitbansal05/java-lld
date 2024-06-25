package sa.com.barraq;

import java.util.Random;
import java.util.concurrent.*;

public class CFuture {
    static ExecutorService executor = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println( "sum: " + findSum());
        executor.shutdown();
    }

    private static int findSum() throws ExecutionException, InterruptedException {
        Future<Integer> integerFuture = executor.submit(() -> {
            int s = 0;
            for (int i = 0; i <= 10; i++) s += i;
            return s;
        });
        return integerFuture.get();
    }

    private int findSumWithException(int n) throws InterruptedException {
        Future<Integer> integerFuture = executor.submit(() -> {
            throw new RuntimeException("something bad happened");
        });
        try {
            integerFuture.get();
        } catch (ExecutionException e) {
            System.out.println("Something went wrong. " + e.getCause());
        }
        return -1;
    }

    private int pollingStatusAndCancelTask() throws InterruptedException {
        Future<Integer> sumFuture1 = executor.submit(() -> {
            Thread.sleep(100);
            int s = 0;
            for (int i = 0; i <= 10; i++) s += i;
            return s;
        });
        Future<Void> sumFuture2 = executor.submit(() -> {
            Thread.sleep(1000 * 1000);
            return null;
        });

        int result = -1;
        try {
            sumFuture2.cancel(true);
            while (!sumFuture1.isDone()) {
                System.out.println("Waiting for first task to complete.");
            }
            result = sumFuture1.get();
        } catch (ExecutionException e) {
            System.out.println("Something went wrong. " + e.getCause());
        }
        return result;
    }

    private void futureTask() throws ExecutionException, InterruptedException {
        Future<?> voidFuture = executor.submit(new FutureTask<>(() -> {
            Thread.sleep(1000);
            return 1;
        }));
        while (!voidFuture.isDone()) {
            System.out.println("Waiting");
        }
        System.out.println((int) voidFuture.get());
        executor.shutdown();
    }

    private void executorCompletionService() throws ExecutionException, InterruptedException {
        class CustomRunnable implements Runnable {
            final int n;
            final Random random = new Random(System.currentTimeMillis());

            CustomRunnable(int i) {
                this.n = i;
            }

            @Override
            public void run() {
                try {
                    Thread.sleep(random.nextInt(101));
                    System.out.println(n*n);
                } catch (InterruptedException e) {
                    // swallow exception
                }
            }
        }

        ExecutorCompletionService<Void> executorCompletionService = new ExecutorCompletionService<>(executor);
        // Submit 10 trivial tasks.
        for (int i = 0; i < 10; i++) {
            executorCompletionService.submit(new CustomRunnable(i), null);
        }

        int count = 10;
        while (count != 0) {
            Future<Void> future = executorCompletionService.poll();
            if (future != null) {
                System.out.println("Thread " + future.get() + " got done.");
                count--;
            }
        }
        executor.shutdown();
    }

    private void performanceUsingThreadLocalRandom() {
        Runnable task = () -> {
            for (int i = 0; i < 50000; i++) {
                ThreadLocalRandom.current().nextInt();
            }
        };
        long start = System.currentTimeMillis();
        Future<Void>[] futures = new Future[4];
        for (int i = 0; i < 4; i++) {
            futures[i] = executor.submit(task, null);
        }
        try {
            for (int i = 0; i < 4; i++) futures[i].get();
            long executionTime = System.currentTimeMillis() - start;
            System.out.println("Execution time using ThreadLocalRandom : " + executionTime + " milliseconds");
        } catch (ExecutionException | InterruptedException e) {
            // swallow exception
        } finally {
            executor.shutdown();
        }

        Random random = new Random();
        Runnable task2 = () -> {
            for (int i = 0; i < 50000; i++) {
                random.nextInt();
            }
        };
        start = System.currentTimeMillis();
        futures = new Future[4];
        for (int i = 0; i < 4; i++) {
            futures[i] = executor.submit(task2, null);
        }
        try {
            for (int i = 0; i < 4; i++) futures[i].get();
            long executionTime = System.currentTimeMillis() - start;
            System.out.println("Execution time using ThreadLocalRandom : " + executionTime + " milliseconds");
        } catch (ExecutionException | InterruptedException e) {
            // swallow exception
        } finally {
            executor.shutdown();
        }
    }
}
