package se.kth.ict.oodbook.design.casestudy.dbhandler;

/**
 * Contains information about one particular car.
 */
public class CarDTO {

    private int price;
    private String size;
    private boolean AC;
    private boolean fourWD;
    private String color;
    private String regNo;

    /**
     * Creates a new instance representing a particular car.
     *
     * @param price The price paid to rent the car.
     * @param size The size of the car, e.g., <code>medium hatchback</code>.
     * @param AC      <code>true</code> if the car has air condition.
     * @param fourWD  <code>true</code> if the car has four wheel drive.
     * @param color The color of the car.
     * @param regNo The car's registration number.
     */
    public CarDTO(int price, String size, boolean AC, boolean fourWD,
            String color, String regNo) {
        this.price = price;
        this.size = size;
        this.AC = AC;
        this.fourWD = fourWD;
        this.color = color;
        this.regNo = regNo;
    }

    /**
     * Get the value of regNo
     *
     * @return the value of regNo
     */
    public String getRegNo() {
        return regNo;
    }

    /**
     * Get the value of color
     *
     * @return the value of color
     */
    public String getColor() {
        return color;
    }

    /**
     * Get the value of fourWD
     *
     * @return the value of fourWD
     */
    public boolean isFourWD() {
        return fourWD;
    }

    /**
     * Get the value of AC
     *
     * @return the value of AC
     */
    public boolean isAC() {
        return AC;
    }

    /**
     * Get the value of size
     *
     * @return the value of size
     */
    public String getSize() {
        return size;
    }

    /**
     * Get the value of price
     *
     * @return the value of price
     */
    public int getPrice() {
        return price;
    }

}
