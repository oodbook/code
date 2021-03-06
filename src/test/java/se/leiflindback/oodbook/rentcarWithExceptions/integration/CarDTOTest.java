/*
 * Copyright (c) 2016, Leif Lindbäck
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of
 * conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other materials provided
 * with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors may be used to
 * endorse or promote products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package se.leiflindback.oodbook.rentcarWithExceptions.integration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.leiflindback.oodbook.rentcarWithExceptions.model.Amount;

public class CarDTOTest {
    @Test
    public void testToString() {
        String regNo = "ABC123";
        Amount price = new Amount(1000);
        String size = "medium";
        boolean AC = true;
        boolean fourWD = false;
        String color = "red";
        boolean booked = false;
        CarDTO instance = new CarDTO(regNo, price, size, AC, fourWD, color, booked);
        String expResult = "regNo: " + regNo + ", size: " + size + ", price: " + price
                           + ", AC: " + AC + ", 4WD: " + fourWD + ", color: " + color
                           + ", booked: " + booked;
        String result = instance.toString();
        assertEquals(expResult, result, "Wrong string returned by toString");
    }

    @Test
    public void testToStringNullParam() {
        String regNo = null;
        Amount price = null;
        String size = null;
        boolean AC = false;
        boolean fourWD = true;
        String color = null;
        boolean booked = false;
        CarDTO instance = new CarDTO(regNo, price, size, AC, fourWD, color, booked);
        String expResult = "regNo: " + regNo + ", size: " + size + ", price: " + price
                           + ", AC: " + AC + ", 4WD: " + fourWD + ", color: " + color
                           + ", booked: " + booked;
        String result = instance.toString();
        assertEquals(expResult, result, "Wrong string returned by toString");
    }

    @Test
    public void testToStringEmptyStringParam() {
        String regNo = "";
        Amount price = new Amount(1000);
        String size = "";
        boolean AC = true;
        boolean fourWD = false;
        String color = "";
        boolean booked = false;
        CarDTO instance = new CarDTO(regNo, price, size, AC, fourWD, color, booked);
        String expResult = "regNo: " + regNo + ", size: " + size + ", price: " + price
                           + ", AC: " + AC + ", 4WD: " + fourWD + ", color: " + color
                           + ", booked: " + booked;
        String result = instance.toString();
        assertEquals(expResult, result, "Wrong string returned by toString");
    }

    @Test
    public void testEquals() {
        String regNo = "ABC123";
        Amount price = new Amount(1000);
        String size = "medium";
        boolean AC = true;
        boolean fourWD = false;
        String color = "red";
        boolean booked = false;
        CarDTO instance = new CarDTO(regNo, price, size, AC, fourWD, color, booked);
        CarDTO equalInstance = new CarDTO(regNo, price, size, AC, fourWD, color, booked);
        boolean expResult = true;
        boolean result = instance.equals(equalInstance);
        assertEquals(expResult, result, "CarDTO instances with same states are not equal.");
    }

    @Test
    public void testNotEqualsRegNo() {
        String regNo = "ABC123";
        Amount price = new Amount(1000);
        String size = "medium";
        boolean AC = true;
        boolean fourWD = false;
        String color = "red";
        boolean booked = false;
        CarDTO instance = new CarDTO(regNo, price, size, AC, fourWD, color, booked);
        CarDTO notEqualInstance = new CarDTO("wrong", price, size, AC, fourWD, color, booked);
        boolean expResult = false;
        boolean result = instance.equals(notEqualInstance);
        assertEquals(expResult, result, "CarDTO instances with different regNos are equal.");
    }

    @Test
    public void testNotEqualsPrice() {
        String regNo = "ABC123";
        Amount price = new Amount(1000);
        String size = "medium";
        boolean AC = true;
        boolean fourWD = false;
        String color = "red";
        boolean booked = false;
        CarDTO instance = new CarDTO(regNo, price, size, AC, fourWD, color, booked);
        CarDTO notEqualInstance = new CarDTO(regNo, new Amount(), size, AC, fourWD, color, booked);
        boolean expResult = false;
        boolean result = instance.equals(notEqualInstance);
        assertEquals(expResult, result, "CarDTO instances with different prices are equal.");
    }

    @Test
    public void testNotEqualsSize() {
        String regNo = "ABC123";
        Amount price = new Amount(1000);
        String size = "medium";
        boolean AC = true;
        boolean fourWD = false;
        String color = "red";
        boolean booked = false;
        CarDTO instance = new CarDTO(regNo, price, size, AC, fourWD, color, booked);
        CarDTO notEqualInstance = new CarDTO(regNo, price, "wrong", AC, fourWD, color, booked);
        boolean expResult = false;
        boolean result = instance.equals(notEqualInstance);
        assertEquals(expResult, result, "CarDTO instances with different sizes are equal.");
    }

    @Test
    public void testNotEqualsAC() {
        String regNo = "ABC123";
        Amount price = new Amount(1000);
        String size = "medium";
        boolean AC = true;
        boolean fourWD = false;
        String color = "red";
        boolean booked = false;
        CarDTO instance = new CarDTO(regNo, price, size, AC, fourWD, color, booked);
        CarDTO notEqualInstance = new CarDTO(regNo, price, size, !AC, fourWD, color, booked);
        boolean expResult = false;
        boolean result = instance.equals(notEqualInstance);
        assertEquals(expResult, result, "CarDTO instances with different AC values are equal.");
    }

    @Test
    public void testNotEqualsFourWD() {
        String regNo = "ABC123";
        Amount price = new Amount(1000);
        String size = "medium";
        boolean AC = true;
        boolean fourWD = false;
        String color = "red";
        boolean booked = false;
        CarDTO instance = new CarDTO(regNo, price, size, AC, fourWD, color, booked);
        CarDTO notEqualInstance = new CarDTO(regNo, price, size, AC, !fourWD, color, booked);
        boolean expResult = false;
        boolean result = instance.equals(notEqualInstance);
        assertEquals(expResult, result, "CarDTO instances with different fourWD values are equal.");
    }

    @Test
    public void testNotEqualsColor() {
        String regNo = "ABC123";
        Amount price = new Amount(1000);
        String size = "medium";
        boolean AC = true;
        boolean fourWD = false;
        String color = "red";
        boolean booked = false;
        CarDTO instance = new CarDTO(regNo, price, size, AC, fourWD, color, booked);
        CarDTO notEqualInstance = new CarDTO(regNo, price, size, AC, fourWD, "wrong", booked);
        boolean expResult = false;
        boolean result = instance.equals(notEqualInstance);
        assertEquals(expResult, result, "CarDTO instances with different colors are equal.");
    }

    @Test
    public void testNotEqualsBooked() {
        String regNo = "ABC123";
        Amount price = new Amount(1000);
        String size = "medium";
        boolean AC = true;
        boolean fourWD = false;
        String color = "red";
        boolean booked = false;
        CarDTO instance = new CarDTO(regNo, price, size, AC, fourWD, color, booked);
        CarDTO notEqualInstance = new CarDTO(regNo, price, size, AC, fourWD, color, !booked);
        boolean expResult = false;
        boolean result = instance.equals(notEqualInstance);
        assertEquals(expResult, result, "CarDTO instances with different fourWD values are equal.");
    }

    @Test
    public void testNotEqualsNullParam() {
        String regNo = "ABC123";
        Amount price = new Amount(1000);
        String size = "medium";
        boolean AC = true;
        boolean fourWD = false;
        String color = "red";
        boolean booked = false;
        CarDTO instance = new CarDTO(regNo, price, size, AC, fourWD, color, booked);
        CarDTO notEqualInstance = new CarDTO(regNo, price, size, AC, fourWD, null, booked);
        boolean expResult = false;
        boolean result = instance.equals(notEqualInstance);
        assertEquals(expResult, result, "CarDTO instances with different colors are equal.");
    }

    @Test
    public void testNotEqualsEmptyStringParam() {
        String regNo = "ABC123";
        Amount price = new Amount(1000);
        String size = "medium";
        boolean AC = true;
        boolean fourWD = false;
        String color = "red";
        boolean booked = false;
        CarDTO instance = new CarDTO(regNo, price, size, AC, fourWD, color, booked);
        CarDTO notEqualInstance = new CarDTO(regNo, price, size, AC, fourWD, "", booked);
        boolean expResult = false;
        boolean result = instance.equals(notEqualInstance);
        assertEquals(expResult, result, "CarDTO instances with different colors are equal.");
    }

    @Test
    public void testNotEqualToJavaLangObj() {
        String regNo = "ABC123";
        Amount price = new Amount(1000);
        String size = "medium";
        boolean AC = true;
        boolean fourWD = false;
        String color = "red";
        boolean booked = false;
        CarDTO instance = new CarDTO(regNo, price, size, AC, fourWD, color, booked);
        Object notEqualInstance = new Object();
        boolean expResult = false;
        boolean result = instance.equals(notEqualInstance);
        assertEquals(expResult, result, "CarDTO instance equal to instance of java.lang.Object.");
    }

    @Test
    public void testNotEqualToNull() {
        String regNo = "ABC123";
        Amount price = new Amount(1000);
        String size = "medium";
        boolean AC = true;
        boolean fourWD = false;
        String color = "red";
        boolean booked = false;
        CarDTO instance = new CarDTO(regNo, price, size, AC, fourWD, color, booked);
        Object notEqualInstance = null;
        boolean expResult = false;
        boolean result = instance.equals(notEqualInstance);
        assertEquals(expResult, result, "CarDTO instance equal to null.");
    }
}
