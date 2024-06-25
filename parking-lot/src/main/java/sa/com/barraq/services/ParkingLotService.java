package sa.com.barraq.services;

import sa.com.barraq.exceptions.ParkingLotException;
import sa.com.barraq.model.Car;
import sa.com.barraq.model.ParkingLot;
import sa.com.barraq.model.Slot;
import sa.com.barraq.model.parking.strategy.ParkingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParkingLotService {
    private ParkingLot parkingLot;
    private ParkingStrategy parkingStrategy;

    /**
     * Allots a parking lot into the parking service. Throws {@link ParkingLotException} if there is
     * already a parking lot allotted to the service previously.
     *
     * @param parkingLot Parking lot to be allotted.
     * @param parkingStrategy Strategy to be used while parking.
     */
    public void createParkingLot(final ParkingLot parkingLot, final ParkingStrategy parkingStrategy) {
        if (this.parkingLot != null) {
            throw new ParkingLotException("Parking lot already exists.");
        }
        this.parkingLot = parkingLot;
        this.parkingStrategy = parkingStrategy;
        for (int i = 1; i <= parkingLot.getCapacity(); i++) {
            parkingStrategy.addSlot(i);
        }
    }

    /**
     * Parks a {@link Car} into the parking lot. {@link ParkingStrategy} is used to decide the slot
     * number and then the car is parked into the {@link ParkingLot} into that slot number.
     *
     * @param car Car to be parked.
     * @return Slot number in which the car is parked.
     */
    public Integer park(final Car car) {
        validateParkingLotExists();
        final Integer nextFreeSlot = parkingStrategy.getNextSlot();
        parkingLot.park(car, nextFreeSlot);
        parkingStrategy.removeSlot(nextFreeSlot);
        return nextFreeSlot;
    }

    /**
     * Unparks a car from a slot. Freed slot number is given back to the parking strategy so that it
     * becomes available for future parkings.
     *
     * @param slotNumber Slot number to be freed.
     */
    public void makeSlotFree(final Integer slotNumber) {
        validateParkingLotExists();
        parkingLot.makeSlotFree(slotNumber);
        parkingStrategy.addSlot(slotNumber);
    }

    /**
     * Gets the list of all the slots which are occupied.
     */
    public List<Slot> getOccupiedSlots() {
        validateParkingLotExists();
        final List<Slot> occupiedSlotsList = new ArrayList<>();
        final Map<Integer, Slot> allSlots = parkingLot.getSlots();

        for (int i = 1; i <= parkingLot.getCapacity(); i++) {
            if (allSlots.containsKey(i)) {
                final Slot slot = allSlots.get(i);
                if (!slot.isSlotFree()) {
                    occupiedSlotsList.add(slot);
                }
            }
        }

        return occupiedSlotsList;
    }

    /**
     * Helper method to validate whether the parking lot exists or not. This is used to validate the
     * existence of parking lot before doing any operation on it.
     */
    private void validateParkingLotExists() {
        if (parkingLot == null) {
            throw new ParkingLotException("Parking lot does not exists to park.");
        }
    }

    /**
     * Gets all the slots in which a car with given color is parked.
     *
     * @param color Color to be searched.
     * @return All matching slots.
     */
    public List<Slot> getSlotsForColor(final String color) {
        final List<Slot> occupiedSlots = getOccupiedSlots();
        return occupiedSlots.stream()
                .filter(slot -> slot.getParkedCar().getColor().equals(color))
                .toList();
    }
}
