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
package se.kth.ict.rentcar.controller;

import se.kth.ict.rentcar.integration.CarRegistry;
import se.kth.ict.rentcar.integration.CarDTO;
import se.kth.ict.rentcar.integration.RegistryCreator;
import se.kth.ict.rentcar.model.CustomerDTO;
import se.kth.ict.rentcar.model.Rental;

/**
 * This is the application's only controller class. All calls to the model pass
 * through here.
 */
public class Controller {
    private CarRegistry carRegistry;
//    private RentalRegistry rentalRegistry;
    private Rental rental;
//    private CashRegister cashRegister;
//    private Printer printer;

    /**
     * Creates a new instance.
     *
     * @param regCreator Used to get all classes that handle database calls.
//     * @param printer    Interface to printer.
     */
    public Controller(RegistryCreator regCreator/*, Printer printer*/) {
        this.carRegistry = regCreator.getCarRegistry();
//        this.rentalRegistry = regCreator.getRentalRegistry();
//        this.printer = printer;
//        this.cashRegister = new CashRegister();
    }

    /**
     * Search for a car matching the specified search criteria.
     *
     * @param searchedCar This object contains the search criteria. Fields in
     *                    the object that are set to <code>null</code> or
     *                    <code>0</code> are ignored.
     * @return The best match of the search criteria.
     */
    public CarDTO searchMatchingCar(CarDTO searchedCar) {
        return carRegistry.findCar(searchedCar);
    }

    /**
     * Registers a new customer. Only registered customers can rent cars.
     *
     * @param customer The customer that will be registered.
     */
    public void registerCustomer(CustomerDTO customer) {
        rental = new Rental(customer/*, carRegistry*/);
    }

//    /**
//     * Books the specified car. After calling this method, the car can not be
//     * booked by any other customer. This method also permanently saves
//     * information about the current rental.
//     *
//     * @param car The car that will be booked.
//     */
//    public void bookCar(CarDTO car) {
//        rental.setRentedCar(car);
//        rentalRegistry.saveRental(rental);
//    }
//
//    /**
//     * Handles rental payment. Updates the balance of the cash register where
//     * the payment was performed. Calculates change. Prints the receipt.
//     *
//     * @param paidAmt The paid amount.
//     */
//    public void pay(Amount paidAmt) {
//        CashPayment payment = new CashPayment(paidAmt);
//        rental.pay(payment);
//        cashRegister.addPayment(payment);
//        rental.printReceipt(printer);
//    }
}
