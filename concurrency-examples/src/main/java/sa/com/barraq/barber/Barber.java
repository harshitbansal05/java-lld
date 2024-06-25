package sa.com.barraq.barber;

import sa.com.barraq.semaphore.CountingSemaphore;

public class Barber {

    private final int chairs;
    private int occupied;
    private final CountingSemaphore semaphoreIn;
    private final CountingSemaphore semaphoreOut;

    @lombok.SneakyThrows
    public Barber(int chairs) {
        this.chairs = chairs;
        this.occupied = 0;
        this.semaphoreIn = new CountingSemaphore(1, 0);
        this.semaphoreOut = new CountingSemaphore(1, 0);
        Thread dt = new Thread(this::barber);
        dt.setDaemon(true);
        dt.start();
    }

    private void barber() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (occupied == 0) wait();
                occupied--;
            }
            semaphoreIn.release();
            System.out.println("Barber started the haircut");
            Thread.sleep(3000);
            System.out.println("Barber finished the haircut");
            semaphoreOut.release();
        }
    }

    public void getHaircut(String id) throws InterruptedException {
        synchronized (this) {
            if (occupied == chairs) throw new RuntimeException("Barber is full");
            occupied++;
            notify();
        }
        semaphoreIn.acquire();
        System.out.println("Person: " + id + " got seated on the chair");
        semaphoreOut.acquire();
        System.out.println("Person: " + id + " got out of the chair");
    }
}
