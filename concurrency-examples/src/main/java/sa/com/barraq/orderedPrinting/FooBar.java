package sa.com.barraq.orderedPrinting;

public class FooBar {
    private final int n;
    private int c;

    public FooBar(int n) {
        this.c = 0;
        this.n = n;
    }

    public synchronized void printFoo() throws InterruptedException {
        for (int i = 1; i <= n;  i++) {
            while (c % 2 == 1) wait();
            c++;
            System.out.println("Foo");
            notify();
        }
    }

    public synchronized void printBar() throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            while (c % 2 == 0) wait();
            c++;
            System.out.println("Bar");
            notify();
        }
    }
}
