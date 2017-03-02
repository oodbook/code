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
package se.leiflindback.oodbook.rentcarWithExAndDesPat.view;

import java.io.IOException;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.controller.OperationFailedException;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.controller.Controller;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.integration.CarDTO;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.model.AddressDTO;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.model.AlreadyBookedException;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.model.Amount;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.model.CustomerDTO;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.model.DrivingLicenseDTO;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.util.LogHandler;

/**
 * This program has no view, instead, this class is a placeholder for the entire view.
 */
public class View {
    private Controller contr;
    private ErrorMessageHandler errorMsgHandler = new ErrorMessageHandler();
    private LogHandler logger = LogHandler.getLogger();

    /**
     * Creates a new instance.
     *
     * @param contr The controller that is used for all operations.
     */
    public View(Controller contr) throws IOException {
        this.contr = contr;
        contr.addRentalObserver(new ConsoleRentedCarsDisplay());
        contr.addRentalObserver(new GuiRentedCarsDisplay());
    }

    /**
     * Simulates a user input that generates calls to all system operations.
     */
    public void sampleExecution() {
        try {
            CarDTO unavailableCar = new CarDTO(null, new Amount(1000), CarDTO.CarType.MEDIUM, true,
                                               true, "nonExistingColor", false);
            CarDTO availableCar = new CarDTO(null, new Amount(1000), CarDTO.CarType.MEDIUM, true, true, "red",
                                             false);

            CarDTO foundCar = contr.searchMatchingCar(unavailableCar);
            System.out.println(
                    "Result of searching for unavailable car: " + foundCar);
            foundCar = contr.searchMatchingCar(availableCar);
            System.out.println("Result of searching for available car: " + foundCar);

            AddressDTO address = new AddressDTO("Storgatan 2", "12345", "Hemorten");
            DrivingLicenseDTO drivingLicense = new DrivingLicenseDTO(
                    "982193721937213");
            CustomerDTO customer = new CustomerDTO("Stina", address, drivingLicense);
            contr.registerCustomer(customer);
            System.out.println("Customer is registered");

            contr.bookCar(foundCar);
            try {
                System.out.println("Trying to rebook a booked car, should generate an error.");
                contr.bookCar(foundCar);
                errorMsgHandler.showErrorMsg("Managed to book a booked car.");
            } catch (AlreadyBookedException exc) {
                handleException("Correctly failed to book a booked car.", exc);
            } catch (OperationFailedException exc) {
                handleException("Wrong exception type.", exc);
            }
            try {
                CarDTO nonexistingCar = new CarDTO("doesNotExist", null, null, true, true, null,
                                                   false);
                System.out.println("Trying to book a non-existing car, should generate an error.");
                contr.bookCar(nonexistingCar);
                errorMsgHandler.showErrorMsg("Managed to book a nonexisting car.");
            } catch (OperationFailedException exc) {
                handleException("Correctly failed to book a nonexisting car.", exc);
            } catch (AlreadyBookedException exc) {
                handleException("Wrong exception type.", exc);
            }
            System.out.println("Car is booked");
            foundCar = contr.searchMatchingCar(availableCar);
            System.out.println(
                    "Result of searching for available, but booked, car: " + foundCar);

            Amount paidAmount = new Amount(1500);
            System.out.println("----------------- Receipt follows --------------");
            contr.pay(paidAmount);
            System.out.println("-------- End of receipt ----------------------");
        } catch (AlreadyBookedException exc) {
            handleException(exc.getCarThatCanNotBeBooked().getRegNo() + " is already booked.", exc);
        } catch (Exception exc) {
            handleException("Failed to book, please try again.", exc);
        }
    }

    private void handleException(String uiMsg, Exception exc) {
        errorMsgHandler.showErrorMsg(uiMsg);
        logger.logException(exc);
    }
}
