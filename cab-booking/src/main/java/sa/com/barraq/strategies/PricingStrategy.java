package sa.com.barraq.strategies;

import sa.com.barraq.model.Location;

public interface PricingStrategy {
    Double findPrice(Location fromPoint, Location toPoint);
}

