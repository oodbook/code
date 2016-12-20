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

import java.util.ArrayList;
import java.util.List;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.model.Rental;

/**
 * Contains all calls to the data store with performed rentals.
 */
public class RentalRegistry {
    private List<Rental> rentals = new ArrayList<>();

    RentalRegistry() {
    }

    /**
     * Saves the specified rental permanently.
     *
     * @param rental The rental that will be saved.
     */
    public void saveRental(Rental rental) {
        rentals.add(rental);
    }

    /**
     * Returns a list containing all rentals made by a customer with the
     * specified name. If there are no such rentals, the returned list is empty.
     *
     * @param custName The name of the customer who's rentals shall be
     *                 retrieved.
     * @return A list with all rentals made by the specified customer.
     */
    public List<Rental> findRentalByCustomerName(String custName) {
        List<Rental> rentalsMadeBySpecifiedCust = new ArrayList<>();
        for (Rental rental : rentals) {
            if (rental.getRentingCustomer().getName().equals(custName)) {
                rentalsMadeBySpecifiedCust.add(rental);
            }
        }
        return rentalsMadeBySpecifiedCust;
    }
}
