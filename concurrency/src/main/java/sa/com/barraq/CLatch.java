package sa.com.barraq;

import java.util.concurrent.CountDownLatch;

public class CLatch {

    static class Worker extends Thread {

        CountDownLatch latch;
        Worker(CountDownLatch latch, String name) {
            super(name);
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        }
    }

    static class Master extends Thread {

        Master(String name) {
            super(name);
        }

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        new Worker(latch, "Worker 1").start();
        new Worker(latch, "Worker 2").start();
        latch.await();
        new Master("Master").start();
    }
}
