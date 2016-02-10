package se.kth.ict.oodbook.design.casestudy.view;

import se.kth.ict.oodbook.design.casestudy.controller.Controller;
import se.kth.ict.oodbook.design.casestudy.dbhandler.CarDTO;

/**
 * This program has no view, instead, this class is a placeholder for the entire
 * view.
 */
public class View {

    private Controller contr;

    public View(Controller contr) {
        this.contr = contr;
    }

    public void sampleExecution() {
        CarDTO searchedCar = new CarDTO(1000, "medium", true, true, "red", null);
        CarDTO foundCar = contr.searchMatchingCar(searchedCar);
        System.out.println(foundCar);
    }

}
