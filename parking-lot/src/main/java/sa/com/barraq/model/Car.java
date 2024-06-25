package sa.com.barraq.model;

import lombok.Getter;

@Getter
public class Car {
    private final String registrationNumber;
    private final String color;

    public Car(final String registrationNumber, final String color) {
        this.registrationNumber = registrationNumber;
        this.color = color;
    }
}
