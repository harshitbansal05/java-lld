package sa.com.barraq.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sa.com.barraq.database.RidersManager;
import sa.com.barraq.database.TripsManager;
import sa.com.barraq.model.Location;
import sa.com.barraq.model.Rider;
import sa.com.barraq.model.Trip;

import java.util.List;

@RestController
public class RidersController {
    private final RidersManager ridersManager;
    private final TripsManager tripsManager;

    public RidersController(RidersManager ridersManager, TripsManager tripsManager) {
        this.ridersManager = ridersManager;
        this.tripsManager = tripsManager;
    }

    @RequestMapping(value = "/register/rider", method = RequestMethod.POST)
    public ResponseEntity registerRider(final String riderId, final String riderName) {
        ridersManager.createRider(new Rider(riderId, riderName));
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public ResponseEntity book(
            final String riderId,
            final Double sourceX,
            final Double sourceY,
            final Double destX,
            final Double destY) {

        tripsManager.createTrip(
                ridersManager.getRider(riderId),
                new Location(sourceX, sourceY),
                new Location(destX, destY));

        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public ResponseEntity fetchHistory(final String riderId) {
        List<Trip> trips = tripsManager.tripHistory(ridersManager.getRider(riderId));
        return ResponseEntity.ok(trips);
    }
}
