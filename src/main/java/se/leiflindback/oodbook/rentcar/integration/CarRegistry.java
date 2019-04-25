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
package se.leiflindback.oodbook.rentcar.integration;

import java.util.ArrayList;
import java.util.List;
import se.leiflindback.oodbook.rentcar.model.Amount;

/**
 * Contains all calls to the data store with cars that may be rented.
 */
public class CarRegistry {
    private List<CarData> cars = new ArrayList<>();

    CarRegistry() {
        addCars();
    }

    /**
     * Search for a car matching the specified search criteria.
     *
     * @param searchedCar This object contains the search criteria. Fields in
     *                    the object that are set to <code>null</code> or
     *                    <code>0</code> are ignored.
     * @return A car matching the searched car's description if a car
     *         with the same features as <code>searchedCar</code> was found,
     *         <code>null</code> if no such car was found.
     */
    public CarDTO findAvailableCar(CarDTO searchedCar) {
        for (CarData car : cars) {
            if (matches(car, searchedCar) && !car.booked) {
                return new CarDTO(car.regNo, new Amount(car.price), car.size,
                                  car.AC, car.fourWD, car.color);
            }
        }
        return null;
    }

    /**
     * If there is an existing car with the registration number of the specified car, set its booked
     * property to the specified value. Nothing is changed if the car's booked property already had
     * the specified value.
     *
     * @param car         The car that shall be marked as booked.
     * @param bookedState The new value of the booked property.
     */
    public void setBookedStateOfCar(CarDTO car, boolean bookedState) {
        CarData carToBook = findCarByRegNo(car);
        carToBook.booked = bookedState;
    }

    private void addCars() {
        cars.add(new CarData("abc123", 1000, "medium", true, true, "red"));
        cars.add(new CarData("abc124", 2000, "large", false, true, "blue"));
        cars.add(new CarData("abc125", 500, "medium", false, false, "red"));
    }

    private boolean matches(CarData found, CarDTO searched) {
        if (searched.getPrice() != null && !searched.getPrice().
                equals(new Amount(found.price))) {
            return false;
        }
        if (searched.getSize() != null && !searched.getSize().equals(found.size)) {
            return false;
        }
        if (searched.getColor() != null && !searched.getColor().equals(
                found.color)) {
            return false;
        }
        if (searched.isAC() != found.AC) {
            return false;
        }
        if (searched.isFourWD() != found.fourWD) {
            return false;
        }
        return true;
    }

    private CarData findCarByRegNo(CarDTO searchedCar) {
        for (CarData car : cars) {
            if (car.regNo.equals(searchedCar.getRegNo())) {
                return car;
            }
        }
        return null;
    }

    private static class CarData {
        private String regNo;
        private int price;
        private String size;
        private boolean AC;
        private boolean fourWD;
        private String color;
        private boolean booked;

        public CarData(String regNo, int price, String size, boolean AC,
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
