package sa.com.barraq;

public class CThread {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ExecuteMe());
        thread.setDaemon(true); // Ensures that this thread is killed by the JVM once the creator thread exits
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
        System.out.println("Main thread exiting");
    }
}

class ExecuteMe implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Inside thread");
                Thread.sleep(1000 * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
