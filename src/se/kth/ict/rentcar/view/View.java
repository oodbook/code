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
package se.kth.ict.rentcar.view;

import se.kth.ict.rentcar.controller.Controller;
import se.kth.ict.rentcar.integration.CarDTO;
import se.kth.ict.rentcar.model.AddressDTO;
import se.kth.ict.rentcar.model.CustomerDTO;
import se.kth.ict.rentcar.model.DrivingLicenseDTO;

/**
 * This program has no view, instead, this class is a placeholder for the entire
 * view.
 */
public class View {
    private Controller contr;

    /**
     * Creates a new instance.
     *
     * @param contr The controller that is used for all operations.
     */
    public View(Controller contr) {
        this.contr = contr;
    }

    /**
     * Simulates a user input that generates calls to all system operations.
     */
    public void sampleExecution() {
        CarDTO unavailableCar = new CarDTO(1000, "nonExistingSize", true, true, "red", null);
        CarDTO availableCar = new CarDTO(1000, "medium", true, true, "red", null);

        CarDTO foundCar = contr.searchMatchingCar(unavailableCar);
        System.out.println("Result of searching for unavailable car: " + foundCar);
        foundCar = contr.searchMatchingCar(availableCar);
        System.out.println("Result of searching for available car: " + foundCar);

        AddressDTO address = new AddressDTO("Storgatan 2", "12345", "Hemorten");
        DrivingLicenseDTO drivingLicense = new DrivingLicenseDTO(
                "982193721937213");
        CustomerDTO customer = new CustomerDTO("Stina", address, drivingLicense);
        contr.registerCustomer(customer);
        System.out.println("Customer is registered");

//        contr.bookCar(foundCar);
//        System.out.println("Car is booked");
//        Amount paidAmount = new Amount(100);
//        contr.pay(paidAmount);
//        System.out.println("Rental is paid");
    }

}
