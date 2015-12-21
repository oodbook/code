package se.kth.ict.oodbook.design.cohesion;

/**
 * Represents a car.
 */
public class BadDesignCar {
    private String regNo;
    private Person owner;
    private String ownersPreferredRadioStation;
    
    /**
     * Returns the registration number of this car.
     */
    public String getRegNo() {
        return regNo;
    }
    
    /**
     * Accelerates the car.
     */
    public void accelerate() {
        
    }
    
    /**
     * Breaks the car.
     */
    public void brake() {
        
    }
    
    /**
     * Sets the car's radio to the specified station.
     * @param station The station to which to listen.
     */
    public void changeRadioStation(String station)  {
        
    }
}
