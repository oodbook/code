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
package se.leiflindback.oodbook.rentcarWithExAndDesPat.model;

import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.integration.CarDTO;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.integration.RegistryCreator;

public class ReceiptTest {
    @Test
    public void testCreateReceiptString() {
        Amount price = new Amount(100);
        String regNo = "abc123";
        CarDTO.CarType size = CarDTO.CarType.MEDIUM;
        boolean AC = true;
        boolean fourWD = true;
        String color = "red";
        boolean booked = true;
        CarDTO rentedCar = new CarDTO(regNo, price, size, AC, fourWD, color, booked);
        Amount paidAmt = new Amount(500);
        CashPayment payment = new CashPayment(paidAmt);
        Rental paidRental = new Rental(null, new RegistryCreator().
                                       getCarRegistry());
        try {
            paidRental.rentCar(rentedCar);
        } catch (AlreadyBookedException ex) {
            fail("Got Exception.");
            ex.printStackTrace();
        }
        paidRental.pay(payment);
        Receipt instance = new Receipt(paidRental);
        Date rentalTime = new Date();
        String expResult = "Car Rental\n\nRental time: " + rentalTime.toString()
                           + "\n\nRented car: " + regNo + "\nCost: " + price
                           + "\nChange: " + paidAmt.minus(price) + "\n\n";
        String result = instance.createReceiptString();
        assertEquals("Wrong receipt content.", expResult, result);
    }
}
