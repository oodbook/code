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
package se.kth.ict.oodbook.rentcar.model;

import org.junit.Test;
import static org.junit.Assert.*;
import se.kth.ict.oodbook.rentcar.integration.CarDTO;
import se.kth.ict.oodbook.rentcar.integration.RegistryCreator;

public class CashPaymentTest {
    @Test
    public void testGetTotalCost() {
        Amount price = new Amount(100);
        CashPayment instance = new CashPayment(null);
        CarDTO rentedCar = new CarDTO("abc123", price, "medium", true, false, "red");
        Rental paidRental = new Rental(null, new RegistryCreator().getCarRegistry());
        paidRental.setRentedCar(rentedCar);
        instance.calculateTotalCost(paidRental);
        Amount expResult = price;
        Amount result = instance.getTotalCost();
        assertEquals("Wrong total cost.", expResult, result);
    }
    
    @Test
    public void testGetChange() {
        Amount price = new Amount(100);
        Amount paidAmt = new Amount(500);
        CashPayment instance = new CashPayment(paidAmt);
        CarDTO rentedCar = new CarDTO("abc123", price, "medium", true, false, "red");
        Rental paidRental = new Rental(null, new RegistryCreator().getCarRegistry());
        paidRental.setRentedCar(rentedCar);
        instance.calculateTotalCost(paidRental);
        Amount expResult = paidAmt.minus(price);
        Amount result = instance.getChange();
        assertEquals("Wrong total cost.", expResult, result);
    }    
}
