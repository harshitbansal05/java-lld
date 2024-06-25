package sa.com.barraq.orderedPrinting;

import java.util.concurrent.Semaphore;

public class NumberSeries {
    private final int n;
    private final Semaphore zeroSem, oddSem, evenSem;

    public NumberSeries(int n) {
        this.n = n;
        zeroSem = new Semaphore(1);
        oddSem = new Semaphore(0);
        evenSem = new Semaphore(0);
    }

    public void PrintZero() throws InterruptedException {
        for (int i = 0; i < n; ++i) {
            zeroSem.acquire();
            System.out.print("0");
            // release oddSem if i is even or else release evenSem if i is odd
            (i % 2 == 0 ? oddSem : evenSem).release();
        }
    }

    public void PrintEven() throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            evenSem.acquire();
            System.out.print(i);
            zeroSem.release();
        }
    }

    public void PrintOdd() throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            oddSem.acquire();
            System.out.print(i);
            zeroSem.release();
        }
    }
}
