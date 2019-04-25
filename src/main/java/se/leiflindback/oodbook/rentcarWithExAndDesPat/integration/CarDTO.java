/*
 * Copyright (c) 2016, Leif Lindbäck
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its
 *    contributors may be used to endorse or promote products derived from
 *    this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package se.leiflindback.oodbook.rentcarWithExAndDesPat.integration;

import java.util.Objects;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.model.Amount;

/**
 * Contains information about one particular car.
 */
public final class CarDTO {
    private final String regNo;
    private final Amount price;
    private final CarType size;
    private final boolean AC;
    private final boolean fourWD;
    private final String color;
    private boolean booked;

    /**
     * Specifies the type of a car.
     */
    public enum CarType {
        /**
         * A small car.
         */
        SMALL,
        /**
         * A medium-sized car
         */
        MEDIUM,
        /**
         * A large car.
         */
        LARGE
    };

    /**
     * Creates a new instance representing a particular car.
     *
     * @param regNo  The car's registration number.
     * @param price  The price paid to rent the car.
     * @param size   The size of the car, e.g., <code>medium hatchback</code>.
     * @param AC     <code>true</code> if the car has air condition.
     * @param fourWD <code>true</code> if the car has four wheel drive.
     * @param color  The color of the car.
     */
    public CarDTO(String regNo, Amount price, CarType size, boolean AC, boolean fourWD,
                  String color, boolean booked) {
        this.price = price;
        this.size = size;
        this.AC = AC;
        this.fourWD = fourWD;
        this.color = color;
        this.regNo = regNo;
        this.booked = booked;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("regNo: " + regNo + ", ");
        builder.append("size: " + size + ", ");
        builder.append("price: " + price + ", ");
        builder.append("AC: " + AC + ", ");
        builder.append("4WD: " + fourWD + ", ");
        builder.append("color: " + color + ", ");
        builder.append("booked: " + booked);
        return builder.toString();
    }

    /**
     * Two <code>CarDTO</code> instances are equal if all fields are equal.
     *
     * @param otherObj The <code>CarDTO</code> to compare with this <code>CarDTO</code>.
     * @return <code>true</code> if all fields in the specified <code>CarDTO</code> are equal to
     *         corresponding fields in this <code>CarDTO</code>, <code>false</code> if they are not.
     */
    @Override
    public boolean equals(Object otherObj) {
        if (otherObj == null) {
            return false;
        }
        if (getClass() != otherObj.getClass()) {
            return false;
        }
        final CarDTO other = (CarDTO) otherObj;
        if (AC != other.AC) {
            return false;
        }
        if (fourWD != other.fourWD) {
            return false;
        }
        if (!regNo.equals(other.regNo)) {
            return false;
        }
        if (!size.equals(other.size)) {
            return false;
        }
        if (!color.equals(other.color)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        if (booked != other.booked) {
            return false;
        }
        return true;
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
    public CarType getSize() {
        return size;
    }

    /**
     * Get the value of price
     *
     * @return the value of price
     */
    public Amount getPrice() {
        return price;
    }

    /**
     * Tells is this car is booked.
     *
     * @return <code>true</code> if this car is booked, <code>false</code> if it is not.
     */
    public boolean isBooked() {
        return booked;
    }

}
