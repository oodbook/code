package se.kth.ict.oodbook.design.intImp;

public class TheClass {

    private int var;

    public TheClass(int var) {
        this.var = var;
    }

    public void doSomething(String s) {
        anotherMethod(s);
    }

    private void anotherMethod(String s) {
        //Some code
    }
}
