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

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.integration.CarDTO;
import static org.junit.Assert.assertTrue;

public class ConsoleRentedCarsDisplayTest {
    ByteArrayOutputStream outContent;
    PrintStream originalSysOut;
    ConsoleRentedCarsDisplay instance;

    @Before
    public void setUp() {
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        instance = new ConsoleRentedCarsDisplay();
    }

    @After
    public void tearDown() {
        outContent = null;
        System.setOut(originalSysOut);
        instance = null;
    }

    @Test
    public void testNewRental() {
        int noOfSmallCars = 1;
        int noOfMediumCars = 2;
        int noOfLargeCars = 3;
        callNewRental(CarDTO.CarType.SMALL, noOfSmallCars);
        callNewRental(CarDTO.CarType.MEDIUM, noOfMediumCars);
        callNewRental(CarDTO.CarType.LARGE, noOfLargeCars);
        evaluateViewOutput(CarDTO.CarType.SMALL, noOfSmallCars);
        evaluateViewOutput(CarDTO.CarType.MEDIUM, noOfMediumCars);
        evaluateViewOutput(CarDTO.CarType.LARGE, noOfLargeCars);
    }

    private void callNewRental(CarDTO.CarType type, int noOfCalls) {
        CarDTO rentedCar = new CarDTO(null, null, type, true,
                                      true, null, false);
        for (int i = 0; i < noOfCalls; i++) {
            instance.newRental(rentedCar);
        }
    }

    private void evaluateViewOutput(CarDTO.CarType type, int noOfCalls) {
        for (int i = 1; i <= noOfCalls; i++) {
            String expResult = i + " " + type.toString().toLowerCase() + " cars";
            String result = outContent.toString();
            assertTrue("Wrong rented car counter", result.contains(expResult));
        }
    }

}
