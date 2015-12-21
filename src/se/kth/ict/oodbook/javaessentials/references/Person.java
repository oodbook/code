package se.kth.ict.oodbook.javaessentials.references;

/**
 * A person. The only task of the person is to feed a dog.
 */
public class Person {
    private Bowl bowl;
    private int gramsToAdd = 200;

    /**
     * Constructs a new <code>Person</code> instance.
     *
     * @param bowl The bowl used to feed the dog.
     */
    public Person(Bowl bowl) {
        this.bowl = bowl;
    }

    /**
     * Adds food to the dog's bowl.
     */
    public void feedDog() {
        bowl.addFood(gramsToAdd);
    }

}
