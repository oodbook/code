package se.kth.ict.oodbook.javaessentials.objects;

/**
 * A person that lives at the specified address.
 */
public class Person {
    private String homeAddress;
    
    /**
     * Creates a new <code>Person</code>.
     */
    public Person() {
        this(null);
    }
    
    /**
     * Creates a new <code>Person</code> that lives at the specified address.
     * 
     * @param homeAddress The newly created <code>Person</code>'s home address.
     */
    public Person(String homeAddress) {
        this.homeAddress = homeAddress;
    }
    
    /**
     * @return The <code>Person</code>'s home address.
     */
    public String getHomeAddress() {
        return this.homeAddress;
    }
    
    /**
     * The the <code>Person</code> moves to the specified address.
     * 
     * @param newAddress The <code>Person</code>'s new home address.
     */
    public void move(String newAddress) {
        this.homeAddress = newAddress;
    }
}
