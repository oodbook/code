package se.kth.ict.oodbook.javaessentials.references;

/**
 * Contains the main method.
 */
public class Startup {

    /**
     * The main method of the application. Contains the entire start sequence.
     *
     * @param args The program has no command line parameters.
     */
    public static void main(String[] args) {
        Bowl bowl = new Bowl();
        Person person = new Person(bowl);
        for (int i = 0; i < 9; i++) {
            person.feedDog();
        }
    }

}
