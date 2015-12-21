package se.kth.ict.oodbook.design.cohesion;

/**
 * Represents a car.
 */
public class Car {

    private String regNo;
    private Person owner;
    private Engine engine;
    private Radio radio;

    /**
     * Returns the registration number of this car.
     */
    public String getRegNo() {
        return regNo;
    }
}
