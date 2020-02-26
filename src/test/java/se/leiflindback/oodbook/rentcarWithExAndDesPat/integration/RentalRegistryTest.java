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

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.model.AddressDTO;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.model.CustomerDTO;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.model.DrivingLicenseDTO;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.model.Rental;

public class RentalRegistryTest {
    @Test
    public void testSaveOneRental() {
        String customerName = "custName";
        CustomerDTO rentingCustomer = new CustomerDTO(customerName,
                                                      new AddressDTO(
                                                              "street",
                                                              "zip",
                                                              "city"),
                                                      new DrivingLicenseDTO(
                                                              "1234567"));
        Rental rental = new Rental(rentingCustomer, new CarRegistry());
        RentalRegistry instance = new RentalRegistry();
        instance.saveRental(rental);
        int expectedNoOfFoundRentals = 1;
        int noOfFoundRentals = instance.findRentalByCustomerName(customerName).
                size();
        assertEquals(expectedNoOfFoundRentals, noOfFoundRentals,
                     "Wrong number of rentals in registry");
        Rental expectedFoundRental = rental;
        Rental foundRental = instance.findRentalByCustomerName(customerName).
                get(0);
        assertEquals(expectedFoundRental, foundRental, "Wrong rental in registry");
    }

    @Test
    public void testRentalsMadeByDifferentCustomers() {
        String nameOfFirstCustomer = "nameOfFirstCustomer";
        String nameOfOtherCustomer = "nameOfOtherCustomer";
        CustomerDTO firstRentingCustomer = new CustomerDTO(nameOfFirstCustomer,
                                                           new AddressDTO(
                                                                   "street",
                                                                   "zip",
                                                                   "city"),
                                                           new DrivingLicenseDTO(
                                                                   "1234567"));
        CustomerDTO otherRentingCustomer = new CustomerDTO(nameOfOtherCustomer,
                                                           new AddressDTO(
                                                                   "street",
                                                                   "zip",
                                                                   "city"),
                                                           new DrivingLicenseDTO(
                                                                   "1234567"));
        CarRegistry carReg = new CarRegistry();
        RentalRegistry instance = new RentalRegistry();
        Rental rental = new Rental(firstRentingCustomer, carReg);
        instance.saveRental(rental);
        rental = new Rental(otherRentingCustomer, carReg);
        instance.saveRental(rental);
        int expectedNoOfFoundRentals = 1;
        int noOfFoundRentals
                = instance.findRentalByCustomerName(nameOfFirstCustomer).size();
        assertEquals(expectedNoOfFoundRentals, noOfFoundRentals,
                     "Wrong number of rentals in registry");
        Rental expectedFoundRental = rental;
        Rental foundRental = instance.findRentalByCustomerName(
                nameOfOtherCustomer).get(0);
        assertEquals(expectedFoundRental, foundRental, "Wrong rental in registry");
    }

    @Test
    public void testNoSavedRental() {
        String custName = "custName";
        RentalRegistry instance = new RentalRegistry();
        assertTrue(instance.findRentalByCustomerName(custName).isEmpty(),
                   "Found a rental when none was stored");
    }
}
