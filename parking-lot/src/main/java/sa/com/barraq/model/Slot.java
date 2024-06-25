package sa.com.barraq.model;

import lombok.Getter;

@Getter
public class Slot {
    private Car parkedCar;
    private final Integer slotNumber;

    public Slot(final Integer slotNumber) {
        this.slotNumber = slotNumber;
    }

    public boolean isSlotFree() {
        return parkedCar == null;
    }

    public void assignCar(Car car) {
        this.parkedCar = car;
    }

    public void unassignCar() {
        this.parkedCar = null;
    }
}
