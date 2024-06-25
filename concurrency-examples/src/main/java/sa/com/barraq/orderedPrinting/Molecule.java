package sa.com.barraq.orderedPrinting;

import sa.com.barraq.semaphore.CountingSemaphore;

import java.util.Objects;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Molecule {

    private final CountingSemaphore hydrogenSemaphore;
    private final CountingSemaphore oxygenSemaphore;
    private final CyclicBarrier barrier;
    private boolean moleculeBuilt;
    private String leader;

    public Molecule() {
        hydrogenSemaphore = new CountingSemaphore(2, 2);
        oxygenSemaphore = new CountingSemaphore(1, 1);
        barrier = new CyclicBarrier(3);
        moleculeBuilt = false;
    }

    public void hydrogenAtom(String id) throws InterruptedException, BrokenBarrierException {
        hydrogenSemaphore.acquire();
        synchronized (this) {
            this.leader = id;
        }
        barrier.await();
        if (Objects.equals(this.leader, id)) {
            makeMolecule(id);
        } else {
            synchronized (this) {
                while (!moleculeBuilt) wait();
            }
        }
        hydrogenSemaphore.release();
    }

    private void makeMolecule(String id) {
        System.out.println("Atom: " + id + " is creating the molecule");
        moleculeBuilt = true;
        notifyAll();
    }

    public void oxygenAtom(String id) throws InterruptedException, BrokenBarrierException {
        oxygenSemaphore.acquire();
        synchronized (this) {
            this.leader = id;
        }
        barrier.await();
        if (Objects.equals(this.leader, id)) {
            makeMolecule(id);
        } else {
            synchronized (this) {
                while (!moleculeBuilt) wait();
            }
        }
        oxygenSemaphore.release();
    }
}
