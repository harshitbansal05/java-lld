package sa.com.barraq.strategies;

import sa.com.barraq.model.Location;

public class DefaultPricingStrategy implements PricingStrategy {
    private static final Double PER_KM_RATE = 10.0;

    @Override
    public Double findPrice(Location fromPoint, Location toPoint) {
        return fromPoint.distance(toPoint) * PER_KM_RATE;
    }
}
