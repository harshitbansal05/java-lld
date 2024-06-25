package sa.com.barraq.bathroom;

public class Bathroom {

    private int occupancy = 0;
    private boolean isMaleOccupied = false;

    public void maleUseBathroom(String id) throws InterruptedException {
        synchronized (this) {
            while (occupancy == 3 || (occupancy > 0 && !isMaleOccupied)) wait();
            occupancy++;
            isMaleOccupied = true;
        }
        System.out.println("Male: " + id + " is using the bathroom");
        Thread.sleep(1000);
        synchronized (this) {
            occupancy--;
            notifyAll();
        }
        System.out.println("Male: " + id + " completed using the bathroom");
    }

    public void femaleUseBathroom(String id) throws InterruptedException {
        synchronized (this) {
            while (occupancy == 3 || (occupancy > 0 && isMaleOccupied)) wait();
            occupancy++;
            isMaleOccupied = false;
        }
        System.out.println("Female: " + id + " is using the bathroom");
        Thread.sleep(1000);
        synchronized (this) {
            occupancy--;
            notifyAll();
        }
        System.out.println("Female: " + id + " completed using the bathroom");
    }
}
