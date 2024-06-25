package sa.com.barraq.uber;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Uber {
    private int d = 0, r = 0, s = 0;
    private final ReentrantLock lock;
    private final Condition cond;

    public Uber() {
        this.lock = new ReentrantLock();
        this.cond = lock.newCondition();
    }

    public void seatDemocrat(String id) throws InterruptedException {
        lock.lock();
        while (d == 4 || r >= 3) cond.await();
        d++;
        s++;
        seated(id);

        while (r + d < 4) cond.await();
        s--;
        if (s == 0) {
            r = d = 0;
            lock.unlock();
            drive(id);
        } else {
            cond.signalAll();
            lock.unlock();
        }
    }

    public void seatRepublican(String id) throws InterruptedException {
        lock.lock();
        while (r == 4 || d >= 3) cond.await();
        r++;
        s++;
        seated(id);

        while (r + d < 4) cond.await();
        s--;
        if (s == 0) {
            r = d = 0;
            cond.signalAll();
            lock.unlock();
            drive(id);
        } else {
            cond.signalAll();
            lock.unlock();
        }
    }

    private void seated(String id) {
        System.out.println("Person: {} " + id + " is seated in the cab");
    }

    public void drive(String id) {
        System.out.println("Person: {} " + id + " has begun driving the car");
    }
}
