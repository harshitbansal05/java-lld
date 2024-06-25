package sa.com.barraq.executor;

import sa.com.barraq.semaphore.CountingSemaphore;

interface Callback {
    public void done();
}

class Executor {
    public void asynchronousExecution(Callback callback) {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // swallow error
            }
            callback.done();
        });
        t.start();
    }
}

class CustomCallback implements Callback {
    CountingSemaphore semaphore = new CountingSemaphore(1, 0);

    @Override
    public void done() {
        try {
            semaphore.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void acquire() throws InterruptedException {
        semaphore.acquire();
    }
}

public class Demonstration {
    public static void main(String[] args) throws InterruptedException {
        Executor executor = new Executor();
        CustomCallback customCallback = new CustomCallback();
        executor.asynchronousExecution(customCallback);
        customCallback.acquire();
        System.out.println("I am done");
        System.out.println("main thread exiting...");
    }
}
