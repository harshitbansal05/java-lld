package sa.com.barraq.model.parking.strategy;

public interface ParkingStrategy {

    public void addSlot(Integer slotNumber);

    public void removeSlot(Integer slotNumber);

    public Integer getNextSlot();
}
