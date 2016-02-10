package se.kth.ict.oodbook.design.casestudy.controller;

import se.kth.ict.oodbook.design.casestudy.dbhandler.CarDTO;
import se.kth.ict.oodbook.design.casestudy.dbhandler.CarRegistry;

/**
 * This is the application's only controller class. All calls to the model pass
 * through here.
 */
public class Controller {
    private CarRegistry carRegistry;
    
    public Controller(CarRegistry carRegistry) {
        this.carRegistry = carRegistry;
    }
    
    public CarDTO searchMatchingCar(CarDTO searchedCar) {
        return carRegistry.findCar(searchedCar);
    }
}
