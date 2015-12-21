package se.kth.ict.oodbook.javaessentials.objects;

/**
 * Creates a <code>Person</code> and calls it.
 */
public class Startup {
    /**
     * Creates a <code>Person</code> and calls it.
     *
     * @param args This application does not take any command line parameters.
     */
    public static void main(String args[]) {
        Person alice = new Person("Main Street 2");
        System.out.println("Alice lives at " + alice.getHomeAddress());

        Person bob = new Person("Main Street 1");
        System.out.println("Bob lives at " + bob.getHomeAddress());

        alice.move("Main Street 3");
        System.out.println("Alice now lives at " + alice.getHomeAddress());
        System.out.println("Bob still lives at " + bob.getHomeAddress());
    }
}
