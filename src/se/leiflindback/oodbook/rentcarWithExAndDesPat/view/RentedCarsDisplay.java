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
package se.leiflindback.oodbook.rentcarWithExAndDesPat.view;

import java.util.HashMap;
import java.util.Map;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.integration.CarDTO;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.model.RentalObserver;

/**
 * Shows a running total of rented cars of each type.
 */
class RentedCarsDisplay implements RentalObserver {
    private Map<CarDTO.CarType, Integer> noOfRentedCars = new HashMap<>();

    /**
     * Creates a new instance, with the all counters of rented cars set to zero.
     */
    public RentedCarsDisplay() {
        for (CarDTO.CarType type : CarDTO.CarType.values()) {
            noOfRentedCars.put(type, 0);
        }
    }

    @Override
    public void newRental(CarDTO rentedCar) {
        addNewRental(rentedCar);
        printCurrentState();
    }

    private void addNewRental(CarDTO rentedCar) {
        int noOfRentedCarsOfThisType = noOfRentedCars.get(rentedCar.getSize()) + 1;
        noOfRentedCars.put(rentedCar.getSize(), noOfRentedCarsOfThisType);
    }

    private void printCurrentState() {
        System.out.println("#### We have now rented out ####");
        for (CarDTO.CarType type : CarDTO.CarType.values()) {
            System.out.print(noOfRentedCars.get(type));
            System.out.print(" ");
            System.out.print(type.toString().toLowerCase());
            System.out.println(" cars.");
        }
        System.out.println("################################");
    }
}
