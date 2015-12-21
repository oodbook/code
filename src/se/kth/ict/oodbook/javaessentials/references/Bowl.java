package se.kth.ict.oodbook.javaessentials.references;

/**
 * Contains dog food.
 */
public class Bowl {
    private int gramsOfFoodInBowl;

    /**
     * Adds the specified number of grams to the dog's bowl.
     *
     * @param grams The number of grams to add to the bowl.
     */
    public void addFood(int grams) {
        gramsOfFoodInBowl = gramsOfFoodInBowl + grams;
    }

}
