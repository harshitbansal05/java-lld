package sa.com.barraq.dining;

import java.util.concurrent.locks.ReentrantLock;

public class Dining {

    private int numPersons = 0;
    private final ReentrantLock[] locks;

    public Dining() {
        this.locks = new ReentrantLock[5];
        for (int i = 0; i < 5; i++) locks[i] = new ReentrantLock();
    }

    public void eat(int id) throws InterruptedException {
        synchronized (this) {
            while (numPersons == 4) wait();
            numPersons++;
        }
        locks[id].lock();
        locks[(id + 4) % 5].lock();
        System.out.println("Diner: {} " + id + " has acquired both the folks");
        Thread.sleep(50);
        locks[id].unlock();
        locks[(id + 4) % 5].unlock();
        synchronized (this) {
            numPersons--;
        }
        System.out.println("Diner: {} " + id + " has released both the folks");
    }
}
