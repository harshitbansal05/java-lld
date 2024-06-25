package sa.com.barraq.orderedPrinting;

public class FizzBuzz {
    private final int n;
    private int c = 1;

    public FizzBuzz(int n) {
        this.n = n;
    }

    public synchronized void fizz() throws InterruptedException {
        while (c % 3 != 0 || c % 5 == 0) wait();
        System.out.print("fizz");
        c++;
    }

    public synchronized void buzz() throws InterruptedException {
        while (c % 5 != 0 || c % 3 == 0) wait();
        System.out.print("buzz");
        c++;
    }

    public synchronized void fizzbuzz() throws InterruptedException {
        while (c % 3 != 0 || c % 5 != 0) wait();
        System.out.print("fizzbuzz");
        c++;
    }

    public synchronized void number() throws InterruptedException {
        while (c % 3 != 0 && c % 5 != 0) wait();
        System.out.print(c);
        c++;
    }
}
