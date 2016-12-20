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
package se.leiflindback.oodbook.rentcarWithExAndDesPat.integration;

import org.junit.Test;
import static org.junit.Assert.*;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.model.Amount;

public class CarRegistryTest {
    @Test
    public void testGetCarByRegNo() {
        CarDTO searchedCar = new CarDTO("abc123", new Amount(1000), "medium",
                                        true, true, "red", false);
        CarRegistry instance = new CarRegistry();
        CarDTO expResult = searchedCar;
        CarDTO result = instance.getCarByRegNo(searchedCar);
        assertEquals("Existing car was not found", expResult, result);
    }

    @Test
    public void testGetNonExistingCarByRegNo() {
        CarDTO searchedCar = new CarDTO("wrong", new Amount(1000), "medium",
                                        true, true, "red", false);
        CarRegistry instance = new CarRegistry();
        try {
            instance.getCarByRegNo(searchedCar);
            fail("Nonexisting car was found.");
        } catch (CarRegistryException exc) {
            assertTrue("Wrong exception message, does not contain specified car: " + exc.
                    getMessage(),
                       exc.getMessage().contains(searchedCar.toString()));
        }
    }

    @Test
    public void testFindAvailableCarMatch() {
        CarDTO searchedCar = new CarDTO("abc123", new Amount(1000), "medium",
                                        true, true, "red", false);
        CarRegistry instance = new CarRegistry();
        CarDTO expResult = searchedCar;
        CarDTO result = instance.findAvailableCar(searchedCar);
        assertEquals("Available car was not found", expResult, result);
    }

    @Test
    public void testFindAvailableCarRegNoNotChecked() {
        CarDTO searchedCar = new CarDTO("wrong", new Amount(1000), "medium",
                                        true, true, "red", false);
        CarRegistry instance = new CarRegistry();
        CarDTO expResult = new CarDTO("abc123", searchedCar.getPrice(),
                                      searchedCar.getSize(), searchedCar.isAC(),
                                      searchedCar.isFourWD(), searchedCar.getColor(),
                                      searchedCar.isBooked());
        CarDTO result = instance.findAvailableCar(searchedCar);
        assertEquals("Available car with wrong regNo was not found", expResult,
                     result);
    }

    @Test
    public void testFindAvailableCarBookedNotChecked() {
        CarDTO searchedCar = new CarDTO("abc123", new Amount(1000), "medium",
                                        true, true, "red", true);
        CarRegistry instance = new CarRegistry();
        CarDTO expResult = new CarDTO(searchedCar.getRegNo(), searchedCar.getPrice(),
                                      searchedCar.getSize(), searchedCar.isAC(),
                                      searchedCar.isFourWD(), searchedCar.getColor(),
                                      false);
        CarDTO result = instance.findAvailableCar(searchedCar);
        assertEquals("Available car with wrong booked property value was not found", expResult,
                     result);
    }

    @Test
    public void testFindAvailableCarNoMatch() {
        CarDTO searchedCar = new CarDTO("abc123", new Amount(1000), "wrong",
                                        true, true, "red", false);
        CarRegistry instance = new CarRegistry();
        CarDTO expResult = null;
        CarDTO result = instance.findAvailableCar(searchedCar);
        assertEquals("Unavailable car was found", expResult, result);
    }

    @Test
    public void testFindAvailableCarNullIsIgnored() {
        CarDTO searchedCar = new CarDTO(null, null, null,
                                        true, true, null, false);
        CarRegistry instance = new CarRegistry();
        CarDTO expResult = new CarDTO("abc123", new Amount(1000), "medium",
                                      true, true, "red", false);
        CarDTO result = instance.findAvailableCar(searchedCar);
        assertEquals("Unavailable car was found", expResult, result);
    }

    @Test
    public void testChangeBookedState() {
        boolean unbooked = false;
        CarDTO car = new CarDTO("abc123", new Amount(1000), "medium",
                                true, true, "red", unbooked);
        CarRegistry instance = new CarRegistry();
        instance.setBookedStateOfCar(car, !unbooked);
        boolean expResult = !unbooked;
        boolean result = instance.getCarByRegNo(car).isBooked();
        assertEquals("Booked state was not changed.", expResult, result);
        instance.setBookedStateOfCar(car, unbooked);
        expResult = unbooked;
        result = instance.getCarByRegNo(car).isBooked();
        assertEquals("Booked state was not changed.", expResult, result);
    }

    @Test
    public void testChangeBookedStateOfNonExistingCar() {
        CarDTO nonExistingCar = new CarDTO("wrong", new Amount(1000), "medium",
                                           true, true, "red", false);
        CarRegistry instance = new CarRegistry();
        try {
            instance.setBookedStateOfCar(nonExistingCar, true);
            fail("Could change booked state of nonexisting car.");
        } catch (CarRegistryException exc) {
            assertTrue("Wrong exception message, does not contain specified car: "
                       + exc.getMessage(), exc.getMessage().contains(nonExistingCar.toString()));
        }
    }
}
