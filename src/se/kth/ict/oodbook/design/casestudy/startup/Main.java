package se.kth.ict.oodbook.design.casestudy.startup;

import se.kth.ict.oodbook.design.casestudy.controller.Controller;
import se.kth.ict.oodbook.design.casestudy.dbhandler.CarRegistry;
import se.kth.ict.oodbook.design.casestudy.view.View;

/**
 * Contains the <code>main</code> method. Performs all startup of the
 * application.
 */
public class Main {
    public static void main(String[] args) {
        CarRegistry carRegistry = new CarRegistry();
        Controller contr = new Controller(carRegistry);
        new View(contr).sampleExecution();
    }
}
