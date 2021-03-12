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
package se.leiflindback.oodbook.rentcarWithExAndDesPat.integration;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.integration.matching.MatcherFactory;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.model.Amount;

/**
 * Contains all calls to the data store with cars that may be rented.
 */
public class CarRegistry {

    private List<CarData> cars = new ArrayList<>();

    CarRegistry() {
        addCars();
    }

    /**
     * Searches for a car with the registration number of the specified car.
     *
     * @param searchedCar Searches for a car with registration number of this
     * object.
     * @return A car with the specified registration number.
     * @throws CarRegistryException if the database call failed, or if the
     * specified car did not exist.
     */
    public CarDTO getCarByRegNo(CarDTO searchedCar) {
        CarData carInReg = doGetCarByRegNo(searchedCar);
        return new CarDTO(carInReg.regNo, new Amount(carInReg.price), carInReg.size,
                carInReg.AC, carInReg.fourWD, carInReg.color, carInReg.booked);
    }

    /**
     * Search for a car that is not booked, and that matches the specified
     * search criteria.
     *
     * @param searchedCar This object contains the search criteria. Fields in
     * the object that are set to <code>null</code> or zero are ignored.
     * @return A description matching the searched car's description if an
     * unbooked car with the same features as <code>searchedCar</code> was
     * found, <code>null</code> if no such car was found.
     * @throws CarRegistryException If unable to search for a matching car.
     */
    public CarDTO findAvailableCar(CarDTO searchedCar) {
        List<CarDTO> allCars = new ArrayList<>();
        for (CarData car : cars) {
            if (!car.booked) {
                allCars.add(new CarDTO(car.regNo, new Amount(car.price), car.size,
                        car.AC, car.fourWD, car.color, car.booked));
            }
        }
        try {
            return MatcherFactory.getFactory().getDefaultMatcher().match(searchedCar, allCars);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | NoSuchMethodException | InvocationTargetException ex) {
            throw new CarRegistryException("Unable to instantiate matcher", ex);
        }
    }

    /**
     * If there is an existing car with the registration number of the specified
     * car, set its booked property to the specified value. Nothing is changed
     * if the car's booked property already had the specified value
     *
     * @param car The car that shall be marked as booked.
     * @param bookedState The new value of the booked property.
     * @throws CarRegistryException if the database call failed, or if the
     * specified car did not exist.
     */
    public void setBookedStateOfCar(CarDTO car, boolean bookedState) {
        CarData carInReg = doGetCarByRegNo(car);
        carInReg.booked = bookedState;
    }

    private CarData doGetCarByRegNo(CarDTO searchedCar) {
        for (CarData carInReg : cars) {
            if (carInReg.regNo.equals(searchedCar.getRegNo())) {
                return carInReg;
            }
        }
        throw new CarRegistryException("No such car: " + searchedCar);
    }

    private void addCars() {
        cars.add(new CarData("abc123", 1000, CarDTO.CarType.MEDIUM, true, true, "red"));
        cars.add(new CarData("abc124", 2000, CarDTO.CarType.LARGE, false, true, "blue"));
        cars.add(new CarData("abc125", 500, CarDTO.CarType.MEDIUM, false, false, "red"));
    }

    private static class CarData {

        private String regNo;
        private int price;
        private CarDTO.CarType size;
        private boolean AC;
        private boolean fourWD;
        private String color;
        private boolean booked;

        public CarData(String regNo, int price, CarDTO.CarType size, boolean AC,
                boolean fourWD, String color) {
            this.regNo = regNo;
            this.price = price;
            this.size = size;
            this.AC = AC;
            this.fourWD = fourWD;
            this.color = color;
            this.booked = false;
        }
    }
}
