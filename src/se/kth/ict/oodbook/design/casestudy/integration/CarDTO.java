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

package se.kth.ict.oodbook.design.casestudy.integration;

/**
 * Contains information about one particular car.
 */
public final class CarDTO {
    private final int price;
    private final String size;
    private final boolean AC;
    private final boolean fourWD;
    private final String color;
    private final String regNo;

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
