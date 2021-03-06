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

import se.leiflindback.oodbook.rentcar.integration.CarDTO;
import se.leiflindback.oodbook.rentcar.integration.CarRegistry;
import se.leiflindback.oodbook.rentcar.integration.Printer;

/**
 * Represents one particular rental transaction, where one particular car is
 * rented by one particular customer.
 */
public class Rental {
    private CustomerDTO rentingCustomer;
    private CarDTO rentedCar;
    private CarRegistry carRegistry; 
    private CashPayment payment;

    /**
     * Creates a new instance, representing a rental made by the specified
     * customer.
     *
     * @param customer The renting customer.
//     * @param carRegistry The data store with information about available cars.
     */
    public Rental(CustomerDTO customer, CarRegistry carRegistry) {
        this.rentingCustomer = customer;
        this.carRegistry = carRegistry;
    }
    
    /**
     * @return The customer that performed this rental.
     */
    public CustomerDTO getRentingCustomer() {
        return rentingCustomer;
    }

    /**
     * Specifies the car that was rented.
     *
     * @param rentedCar The car that was rented.
     */
    public void setRentedCar(CarDTO rentedCar) {
        this.rentedCar = rentedCar;
        carRegistry.setBookedStateOfCar(rentedCar, true);
    }
    
    /**
     * @return The rented car.
     */
    CarDTO getRentedCar() {
        return rentedCar;
    }
    
    /**
     * @return The payment info for this rental.
     */
    public CashPayment getPayment() {
        return payment;
    }
    
    /**
     * This rental is paid using the specified payment.
     * 
     * @param payment The payment used to pay this rental.
     */
    public void pay(CashPayment payment) {
        payment.calculateTotalCost(this);
        this.payment = payment;
    }
    
    /**
     * Prints a receipt for the current rental on the specified printer.
     * 
     * @param printer The printer where the receipt is printed.
     */
    public void printReceipt(Printer printer) {
        Receipt receipt = new Receipt(this);
        printer.printReceipt(receipt);
    }
}
