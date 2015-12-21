package se.kth.ict.oodbook.javaessentials.exceptions;

/**
 * Contains dog food.
 */
public class Bowl {
    private int gramsOfFoodInBowl;
    private static final int MAX_CAPACITY = 500;
    
    /**
     * Adds the specified number of grams to the dog's bowl.
     * 
     * @param grams The number of grams to add to the bowl.
     * 
     * @throws Exception Thrown if the bowl's max capacity is reached. 
     */
    public void addFood(int grams) throws Exception {
        if (gramsOfFoodInBowl + grams > MAX_CAPACITY) {
            throw new Exception("Max capacity was reached, trying to add " + grams + " grams of food" +
                                 " when there are " + gramsOfFoodInBowl + " grams in bowl.");
        }
        gramsOfFoodInBowl = gramsOfFoodInBowl + grams;
    }

}
