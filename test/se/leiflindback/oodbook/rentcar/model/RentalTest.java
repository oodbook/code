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
package se.leiflindback.oodbook.rentcar.model;

import se.leiflindback.oodbook.rentcar.model.CashPayment;
import se.leiflindback.oodbook.rentcar.model.Amount;
import se.leiflindback.oodbook.rentcar.model.Rental;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import se.leiflindback.oodbook.rentcar.integration.CarDTO;
import se.leiflindback.oodbook.rentcar.integration.CarRegistry;
import se.leiflindback.oodbook.rentcar.integration.Printer;
import se.leiflindback.oodbook.rentcar.integration.RegistryCreator;

public class RentalTest {
    ByteArrayOutputStream outContent;
    PrintStream originalSysOut;

    @Before
    public void setUpStreams() {
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        outContent = null;
        System.setOut(originalSysOut);
    }

    @Test
    public void testSetRentedCar() {
        CarRegistry carReg = new RegistryCreator().getCarRegistry();
        Rental instance = new Rental(null, carReg);
        CarDTO rentedCar = new CarDTO("abc123", new Amount(1000), "medium", true,
                                      true, "red");
        instance.setRentedCar(rentedCar);
        CarDTO expResult = null;
        CarDTO result = carReg.findAvailableCar(rentedCar);
        assertEquals("Rented car was available.", expResult, result);
    }

    @Test
    public void testPay() {
        CarRegistry carReg = new RegistryCreator().getCarRegistry();
        Rental instance = new Rental(null, carReg);
        Amount price = new Amount(1000);
        CarDTO rentedCar = new CarDTO("abc123", price, "medium", true,
                                      true, "red");
        CashPayment payment = new CashPayment(null);
        instance.setRentedCar(rentedCar);
        instance.pay(payment);
        Amount expResult = price;
        Amount result = instance.getPayment().getTotalCost();
        assertEquals("Wrong rental cost", expResult, result);
    }

    @Test
    public void testPrintReceipt() {
        Amount price = new Amount(1000);
        String regNo = "abc123";
        String size = "medium";
        boolean AC = true;
        boolean fourWD = true;
        String color = "red";
        CarDTO rentedCar = new CarDTO(regNo, price, size, AC, fourWD, color);
        Amount paidAmt = new Amount(5000);
        CashPayment payment = new CashPayment(paidAmt);
        Rental instance = new Rental(null, new RegistryCreator().
                                     getCarRegistry());
        instance.setRentedCar(rentedCar);
        instance.pay(payment);
        instance.printReceipt(new Printer());
        Date rentalTime = new Date();
        String expResult = "Car Rental\n\nRental time: " + rentalTime.toString()
                           + "\n\nRented car: " + regNo + "\nCost: " + price
                           + "\nChange: " + paidAmt.minus(price) + "\n\n\n";
        String result = outContent.toString();
        assertEquals("Wrong printout.", expResult, result);
    }
}
