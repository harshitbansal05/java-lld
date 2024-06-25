package sa.com.barraq.strategies;

import sa.com.barraq.model.Cab;
import sa.com.barraq.model.Location;
import sa.com.barraq.model.Rider;

import java.util.List;

public class DefaultCabMatchingStrategy implements CabMatchingStrategy {
    @Override
    public Cab matchCabToRider(Rider rider, List<Cab> candidateCabs, Location fromPoint, Location toPoint) {
        if (candidateCabs.isEmpty()) {
            return null;
        }
        return candidateCabs.getFirst();
    }
}
