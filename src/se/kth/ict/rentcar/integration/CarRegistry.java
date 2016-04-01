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
package se.kth.ict.rentcar.integration;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains all calls to the data store with cars that may be rented.
 */
public class CarRegistry {
    private List<CarDTO> cars = new ArrayList<>();

    CarRegistry() {
        addCars();
    }

    /**
     * Search for a car matching the specified search criteria.
     *
     * @param searchedCar This object contains the search criteria. Fields in
     *                    the object that are set to <code>null</code> or
     *                    <code>0</code> are ignored.
     * @return <code>true</code> if a car with the same features as
     *         <code>searchedCar</code> was found, <code>false</code> if no such
     *         car was found.
     */
    public CarDTO findCar(CarDTO searchedCar) {
        for (CarDTO car : cars) {
            if (car.matches(searchedCar)) {
                return car;
            }
        }
        return null;
    }

    /**
     * Books the specified car. After calling this method, the car can not be
     * booked by any other customer.
     *
     * @param car The car that will be booked.
     */
    public void bookCar(CarDTO car) {
    }

    private void addCars() {
        cars.add(new CarDTO(1000, "medium", true, true, "red", "abc123"));
        cars.add(new CarDTO(2000, "large", false, true, "blue", "abc124"));
        cars.add(new CarDTO(500, "medium", false, false, "red", "abc125"));
    }
}
