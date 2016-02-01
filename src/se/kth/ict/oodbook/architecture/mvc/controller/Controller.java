package se.kth.ict.oodbook.architecture.mvc.controller;

import se.kth.ict.oodbook.architecture.mvc.model.OtherClassInModel;
import se.kth.ict.oodbook.architecture.mvc.model.SomeClassInModel;

/**
 * This is the application's controller. All calls from view to model pass
 * through here.
 */
public class Controller {
    private SomeClassInModel scim;
    private OtherClassInModel ocim;

    /**
     * A system operation, which means it appears in the system sequence
     * diagram.
     */
    public void systemOperation1() {
        scim.aMethod();
        ocim.aMethod();
    }

    /**
     * A system operation, which means it appears in the system sequence
     * diagram.
     */
    public void systemOperation2() {

    }

}
