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
package se.leiflindback.oodbook.rentcarWithExAndDesPat.controller;

import java.util.ArrayList;
import java.util.List;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.model.AlreadyBookedException;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.integration.CarRegistry;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.integration.CarDTO;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.integration.CarRegistryException;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.integration.Printer;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.integration.RegistryCreator;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.integration.RentalRegistry;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.model.Amount;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.model.CashPayment;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.model.CashRegister;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.model.CustomerDTO;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.model.Rental;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.model.RentalObserver;

/**
 * This is the application's only controller class, all calls to the model pass through here.
 * <p>
 * The rental operations can only be called once each during the same rental, and must be called in
 * the following order. <code>registerCustomer</code> starts a rental, and <code>pay</code> ends a
 * rental.
 * <p>
 * <ol>
 * <li><code>registerCustomer</code>
 * <li><code>bookCar</code>
 * <li><code>pay</code>
 * </ol>
 * <p>
 * Other methods can be called in any order, and at any time.
 */
public class Controller {
    private CarRegistry carRegistry;
    private RentalRegistry rentalRegistry;
    private Rental rental;
    private CashRegister cashRegister;
    private Printer printer;
    private List<RentalObserver> rentalObservers = new ArrayList<>();

    /**
     * Creates a new instance.
     *
     * @param regCreator Used to get all classes that handle database calls.
     * @param printer    Interface to printer.
     */
    public Controller(RegistryCreator regCreator, Printer printer) {
        this.carRegistry = regCreator.getCarRegistry();
        this.rentalRegistry = regCreator.getRentalRegistry();
        this.printer = printer;
        this.cashRegister = new CashRegister();
    }

    /**
     * Search for a car matching the specified search criteria.
     *
     * @param searchedCar This object contains the search criteria. Fields in the object that are
     *                    set to <code>null</code> or <code>0</code> are ignored.
     * @return The best match of the search criteria.
     */
    public CarDTO searchMatchingCar(CarDTO searchedCar) {
        return carRegistry.findAvailableCar(searchedCar);
    }

    /**
     * Registers a new customer. Only registered customers can rent cars.
     *
     * @param customer The customer that will be registered.
     * @throws IllegalStateException if this method is called twice during the same rental. A rental
     *                               is ended when <code>pay</code> is called.
     */
    public void registerCustomer(CustomerDTO customer) {
        if (rental != null) {
            throw new IllegalStateException(
                    "Call to registerCustomer when customer was already set.");
        }
        rental = new Rental(customer, carRegistry);
        rental.addRentalObservers(rentalObservers);
    }

    /**
     * Books the specified car. After calling this method, the car can not be booked by any other
     * customer. This method also permanently saves information about the current rental.
     *
     * @param car The car that will be booked.
     * @throws AlreadyBookedException   if the car was already booked.
     * @throws OperationFailedException if unable to rent the car for any other reason than it being
     *                                  already booked.
     * @throws IllegalStateException    if this method is called before
     *                                  <code>registerCustomer</code>.
     */
    public void bookCar(CarDTO car) throws AlreadyBookedException, OperationFailedException {
        if (rental == null) {
            throw new IllegalStateException("Call to bookCar before registering customer.");
        }
        try {
            rental.rentCar(car);
            rentalRegistry.saveRental(rental);
        } catch (CarRegistryException carRegExc) {
            throw new OperationFailedException("Could not rent the car.", carRegExc);
        }
    }

    /**
     * Handles rental payment. Updates the balance of the cash register where the payment was
     * performed. Calculates change. Prints the receipt.
     *
     * @param paidAmt The paid amount.
     * @throws IllegalStateException if this method is called before <code>bookCar</code>.
     */
    public void pay(Amount paidAmt) {
        if (rental == null || rental.getRentedCar() == null) {
            throw new IllegalStateException(
                    "Call to pay before registering customer and booking car.");
        }
        CashPayment payment = new CashPayment(paidAmt);
        rental.pay(payment);
        cashRegister.addPayment(payment);
        rental.printReceipt(printer);
        rental = null;
    }

    /**
     * The specified observer will be notified when a rental has been paid. There will be
     * notifications only for rentals that are started after this method is called.
     *
     * @param obs The observer to notify.
     */
    public void addRentalObserver(RentalObserver obs) {
        rentalObservers.add(obs);
    }

}
