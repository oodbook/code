package se.kth.ict.oodbook.javaessentials.exceptions;

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
     * 
     * @throws Exception Thrown if the bowl's max capacity is reached. 
     */
    public void feedDog() throws Exception {
        bowl.addFood(gramsToAdd);
    }

}
