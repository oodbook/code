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
package se.leiflindback.oodbook.rentcarWithExAndDesPat.integration.matchingWithComposite;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.integration.CarDTO;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.model.Amount;

public class PerfectMatchTest {
    private List<CarDTO> availableCars;
    private CarDTO smallGreen1000Ac4wd = new CarDTO("abc123", new Amount(1000), CarDTO.CarType.SMALL,
                                                    true,
                                                    true, "green", false);
    private final CarDTO mediumRed2000NoacNo4wd = new CarDTO("abc124", new Amount(2000),
                                                             CarDTO.CarType.MEDIUM,
                                                             false, false, "red", false);
    private final CarDTO smallGreen1000Ac4wdBooked = new CarDTO("abc125", new Amount(1000),
                                                                CarDTO.CarType.SMALL,
                                                                true, true, "green", true);
    private final CarDTO largeGreen1000Ac4wd = new CarDTO("abc126", new Amount(1000),
                                                          CarDTO.CarType.LARGE, true,
                                                          true, "green", true);
    private final CarDTO nonExisting = new CarDTO("abc127", new Amount(3000), CarDTO.CarType.SMALL,
                                                  false, true,
                                                  "blue", true);

    @BeforeEach
    public void createAvailableCars() {
        availableCars = new ArrayList<>();
        availableCars.add(smallGreen1000Ac4wd);
        availableCars.add(smallGreen1000Ac4wdBooked);
        availableCars.add(mediumRed2000NoacNo4wd);
        availableCars.add(largeGreen1000Ac4wd);
    }

    @AfterEach
    public void dropAvailableCars() {
        availableCars = null;
    }

    @Test
    public void testNoMatch() {
        PerfectMatch instance = new PerfectMatch();
        assertEquals(null, instance.match(nonExisting, availableCars), "Nonexisting car was found.");
    }

    @Test
    public void testMatchingCarIsFirst() {
        PerfectMatch instance = new PerfectMatch();
        assertEquals(smallGreen1000Ac4wd, instance.match(smallGreen1000Ac4wd, availableCars),
                     "Wrong car was found.");
    }

    @Test
    public void testMatchingCarIsLast() {
        PerfectMatch instance = new PerfectMatch();
        assertEquals(largeGreen1000Ac4wd, instance.match(largeGreen1000Ac4wd, availableCars),
                     "Wrong car was found.");
    }

    @Test
    public void testMatchingCarIsNeitherFirstNorLast() {
        PerfectMatch instance = new PerfectMatch();
        assertEquals(mediumRed2000NoacNo4wd, instance.match(mediumRed2000NoacNo4wd, availableCars),
                     "Wrong car was found.");
    }

    @Test
    public void testRegNoIsIgnored() {
        PerfectMatch instance = new PerfectMatch();
        CarDTO searched = new CarDTO("wrong", smallGreen1000Ac4wd.getPrice(), smallGreen1000Ac4wd.
                                     getSize(), smallGreen1000Ac4wd.isAC(), smallGreen1000Ac4wd.
                                     isFourWD(), smallGreen1000Ac4wd.getColor(),
                                     smallGreen1000Ac4wd.isBooked());
        CarDTO result = instance.match(searched, availableCars);
        assertEquals(smallGreen1000Ac4wd.getRegNo(), result.getRegNo(), "Reg no was not ignored.");
        assertEquals(smallGreen1000Ac4wd.getPrice(), result.getPrice(), "Reg no was not ignored.");
        assertEquals(smallGreen1000Ac4wd.getSize(), result.getSize(), "Reg no was not ignored.");
        assertEquals(smallGreen1000Ac4wd.isAC(), result.isAC(), "Reg no was not ignored.");
        assertEquals(smallGreen1000Ac4wd.isFourWD(), result.isFourWD(), "Reg no was not ignored.");
        assertEquals(smallGreen1000Ac4wd.getColor(), result.getColor(), "Reg no was not ignored.");
        assertEquals(smallGreen1000Ac4wd.isBooked(), result.isBooked(), "Reg no was not ignored.");
    }

    @Test
    public void testBookedIsIgnored() {
        PerfectMatch instance = new PerfectMatch();
        CarDTO result = instance.match(smallGreen1000Ac4wdBooked, availableCars);
        assertEquals(smallGreen1000Ac4wd.getRegNo(), result.getRegNo(), "Booked was not ignored.");
        assertEquals(smallGreen1000Ac4wd.getPrice(), result.getPrice(), "Booked was not ignored.");
        assertEquals(smallGreen1000Ac4wd.getSize(), result.getSize(), "Booked was not ignored.");
        assertEquals(smallGreen1000Ac4wd.isAC(), result.isAC(), "Booked was not ignored.");
        assertEquals(smallGreen1000Ac4wd.isFourWD(), result.isFourWD(), "Booked was not ignored.");
        assertEquals(smallGreen1000Ac4wd.getColor(), result.getColor(), "Booked was not ignored.");
        assertEquals(smallGreen1000Ac4wd.isBooked(), result.isBooked(), "Booked was not ignored.");
    }

}
