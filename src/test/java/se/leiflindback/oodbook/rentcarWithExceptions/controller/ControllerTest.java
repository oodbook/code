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
package se.leiflindback.oodbook.rentcarWithExceptions.controller;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.leiflindback.oodbook.rentcarWithExceptions.integration.CarDTO;
import se.leiflindback.oodbook.rentcarWithExceptions.integration.CarRegistryException;
import se.leiflindback.oodbook.rentcarWithExceptions.integration.Printer;
import se.leiflindback.oodbook.rentcarWithExceptions.model.AddressDTO;
import se.leiflindback.oodbook.rentcarWithExceptions.integration.RegistryCreator;
import se.leiflindback.oodbook.rentcarWithExceptions.model.AlreadyBookedException;
import se.leiflindback.oodbook.rentcarWithExceptions.model.Amount;
import se.leiflindback.oodbook.rentcarWithExceptions.model.CashPayment;
import se.leiflindback.oodbook.rentcarWithExceptions.model.CustomerDTO;
import se.leiflindback.oodbook.rentcarWithExceptions.model.DrivingLicenseDTO;
import se.leiflindback.oodbook.rentcarWithExceptions.model.Rental;

public class ControllerTest {
    private Controller instance;
    private RegistryCreator regCreator;
    ByteArrayOutputStream outContent;
    PrintStream originalSysOut;

    @BeforeEach
    public void setUp() {
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Printer printer = new Printer();
        regCreator = new RegistryCreator();
        instance = new Controller(regCreator, printer);
    }

    @AfterEach
    public void tearDown() {
        outContent = null;
        System.setOut(originalSysOut);
        instance = null;
        regCreator = null;
    }

    @Test
    public void testFindAvailableCarMatch() {
        CarDTO searchedCar = new CarDTO("abc123", new Amount(1000), "medium",
                                        true, true, "red", false);
        CarDTO expResult = searchedCar;
        CarDTO result = instance.searchMatchingCar(searchedCar);
        assertEquals(expResult, result, "Available car was not found");
    }

    @Test
    public void testFindAvailableCarRegNoNotChecked() {
        CarDTO searchedCar = new CarDTO("wrong", new Amount(1000), "medium",
                                        true, true, "red", false);
        CarDTO expResult = new CarDTO("abc123", searchedCar.getPrice(),
                                      searchedCar.getSize(), searchedCar.isAC(),
                                      searchedCar.isFourWD(), searchedCar.
                                      getColor(), false);
        CarDTO result = instance.searchMatchingCar(searchedCar);
        assertEquals(expResult, result, "Available car with wrong regNo was not found");
    }

    @Test
    public void testFindAvailableCarNoMatch() {
        CarDTO searchedCar = new CarDTO("abc123", new Amount(1000), "wrong",
                                        true, true, "red", false);
        CarDTO expResult = null;
        CarDTO result = instance.searchMatchingCar(searchedCar);
        assertEquals(expResult, result, "Unavailable car was found");
    }

    @Test
    public void testFindAvailableCarNullIsIgnored() {
        CarDTO searchedCar = new CarDTO(null, null, null,
                                        true, true, null, false);
        CarDTO expResult = new CarDTO("abc123", new Amount(1000), "medium",
                                      true, true, "red", false);
        CarDTO result = instance.searchMatchingCar(searchedCar);
        assertEquals(expResult, result, "Unavailable car was found");
    }

    @Test
    public void testBookedCarIsUnavailable() {
        CarDTO bookedCar = new CarDTO("abc123", new Amount(1000), "medium",
                                      true, true, "red", false);
        instance.registerCustomer(null);
        try {
            instance.bookCar(bookedCar);
        } catch (Exception ex) {
            fail("Got exception.");
            ex.printStackTrace();
        }
        CarDTO result = instance.searchMatchingCar(bookedCar);
        assertNull(result, "Booked car was found");
    }

    @Test
    public void testBookCarThatIsBooked() {
        CarDTO bookedCar = new CarDTO("abc123", new Amount(1000), "medium",
                                      true, true, "red", false);
        instance.registerCustomer(null);
        try {
            instance.bookCar(bookedCar);
        } catch (OperationFailedException | AlreadyBookedException ex) {
            fail("Got exception.");
            ex.printStackTrace();
        }
        try {
            instance.bookCar(bookedCar);
            fail("Managed to book a booked car.");
        } catch (OperationFailedException ex) {
            fail("Got exception.");
            ex.printStackTrace();
        } catch (AlreadyBookedException ex) {
            assertTrue(ex.getMessage().contains(bookedCar.getRegNo()),
                       "Wrong exception message, does not contain specified car: " + ex.getMessage());
            assertTrue(ex.getCarThatCanNotBeBooked().getRegNo().equals(bookedCar.getRegNo()),
                       "Wrong car is specified: " + ex.getCarThatCanNotBeBooked());
        }
    }

    @Test
    public void testBookNonexistingCar() {
        CarDTO nonexistingCar = new CarDTO("nonExisting", new Amount(1000), "medium",
                                           true, true, "red", false);
        instance.registerCustomer(null);
        try {
            instance.bookCar(nonexistingCar);
        } catch (AlreadyBookedException ex) {
            fail("Got exception.");
            ex.printStackTrace();
        } catch (OperationFailedException ex) {
            assertTrue(ex.getCause() instanceof CarRegistryException,
                       "Wrong root cause, expected CarRegistryException but got " + ex.getCause().
                               getClass().getCanonicalName());
            assertTrue(ex.getCause().getMessage().contains(nonexistingCar.toString()),
                       "Wrong exception message, does not contain specified car: " + ex.getCause().
                               getMessage());
        }
    }

    @Test
    public void testRentalWithBookedCarIsStored() {
        String customerName = "custName";
        CustomerDTO rentingCustomer = new CustomerDTO(customerName,
                                                      new AddressDTO(
                                                              "street",
                                                              "zip",
                                                              "city"),
                                                      new DrivingLicenseDTO(
                                                              "1234567"));
        String regNo = "abc123";
        CarDTO rentedCar = new CarDTO(regNo, new Amount(1000), "medium",
                                      true, true, "red", false);
        instance.registerCustomer(rentingCustomer);
        try {
            instance.bookCar(rentedCar);
        } catch (AlreadyBookedException | OperationFailedException ex) {
            fail("Got exception.");
            ex.printStackTrace();
        }
        List<Rental> savedRentals = regCreator.getRentalRegistry().
                findRentalByCustomerName(customerName);
        int expectedNoOfStoredRentals = 1;
        int noOfStoredRentals = savedRentals.size();
        assertEquals(expectedNoOfStoredRentals, noOfStoredRentals, "Wrong number of stored rentals.");
        Rental savedRental = savedRentals.get(0);
        Amount paidAmt = new Amount(5000);
        CashPayment payment = new CashPayment(paidAmt);
        savedRental.pay(payment);
        savedRental.printReceipt(new Printer());
        String result = outContent.toString();
        assertTrue(result.contains(regNo), "Saved rental does not contain rented car");
    }

    @Test
    public void testRegisterCustomer() {
        String customerName = "custName";
        CustomerDTO rentingCustomer = new CustomerDTO(customerName,
                                                      new AddressDTO(
                                                              "street",
                                                              "zip",
                                                              "city"),
                                                      new DrivingLicenseDTO(
                                                              "1234567"));
        String regNo = "abc123";
        CarDTO rentedCar = new CarDTO(regNo, new Amount(1000), "medium",
                                      true, true, "red", false);
        instance.registerCustomer(rentingCustomer);
        try {
            instance.bookCar(rentedCar);
        } catch (AlreadyBookedException | OperationFailedException ex) {
            fail("Got exception.");
            ex.printStackTrace();
        }
        List<Rental> savedRentals = regCreator.getRentalRegistry().
                findRentalByCustomerName(rentingCustomer.getName());
        int expectedNoOfStoredRentals = 1;
        int noOfStoredRentals = savedRentals.size();
        assertEquals(expectedNoOfStoredRentals, noOfStoredRentals, "Wrong number of stored rentals.");
        Rental savedRental = savedRentals.get(0);
        String expCustName = customerName;
        assertEquals(expCustName, savedRental.getRentingCustomer().getName(),
                     "Saved rental does not contain renting customer");
    }

    @Test
    public void testPay() {
        Amount price = new Amount(1000);
        String regNo = "abc123";
        String size = "medium";
        boolean AC = true;
        boolean fourWD = true;
        String color = "red";
        CarDTO rentedCar = new CarDTO(regNo, price, size, AC, fourWD, color, false);
        Amount paidAmt = new Amount(5000);
        instance.registerCustomer(null);
        try {
            instance.bookCar(rentedCar);
        } catch (AlreadyBookedException | OperationFailedException ex) {
            fail("Got exception.");
            ex.printStackTrace();
        }
        instance.pay(paidAmt);
        LocalDateTime rentalTime = LocalDateTime.now();
        String expResult = "\n\nRented car: " + regNo + "\nCost: " + price
                           + "\nChange: " + paidAmt.minus(price) + "\n\n\n";
        String result = outContent.toString();
        assertTrue(result.contains(expResult), "Wrong printout.");
        assertTrue(result.contains(Integer.toString(rentalTime.getYear())), "Wrong rental year.");
        assertTrue(result.contains(Integer.toString(rentalTime.getMonthValue())),
                   "Wrong rental month.");
        assertTrue(result.contains(Integer.toString(rentalTime.getDayOfMonth())),
                   "Wrong rental day.");
        assertTrue(result.contains(Integer.toString(rentalTime.getHour())),
                   "Wrong rental hour. " + rentalTime.getHour() + ":" + LocalDateTime.now().
                   toString() + ":" + result);
        assertTrue(result.contains(Integer.toString(rentalTime.getMinute())), "Wrong rental minute.");
    }

    @Test
    public void testBookCarWithoutCallToRegisterCustomer() throws AlreadyBookedException,
                                                                  OperationFailedException {
        assertThrows(IllegalStateException.class, () -> instance.bookCar(null));
    }

    @Test()
    public void testPayWithoutCallToRegisterCustomer() {
        assertThrows(IllegalStateException.class, () -> instance.pay(null));
    }

    @Test()
    public void testCallRegisterCustomerTwice() {
        instance.registerCustomer(null);
        assertThrows(IllegalStateException.class, () -> instance.registerCustomer(null));
    }
}
