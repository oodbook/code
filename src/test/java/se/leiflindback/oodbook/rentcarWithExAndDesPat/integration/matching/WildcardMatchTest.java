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
package se.leiflindback.oodbook.rentcarWithExAndDesPat.integration.matching;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.integration.CarDTO;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.model.Amount;

public class WildcardMatchTest {
    private List<CarDTO> availableCars;
    private final CarDTO smallGreen1000Ac4wd = new CarDTO("abc123", new Amount(1000), CarDTO.CarType.SMALL, true,
                                            true, "green", false);
    private final CarDTO mediumRed2000NoacNo4wd = new CarDTO("abc124", new Amount(2000), CarDTO.CarType.MEDIUM,
                                               false, false, "red", false);
    private final CarDTO smallGreen1000Ac4wdBooked = new CarDTO("abc125", new Amount(1000), CarDTO.CarType.SMALL,
                                                  true, true, "green", true);
    private final CarDTO largeGreen1000Ac4wd = new CarDTO("abc126", new Amount(1000), CarDTO.CarType.LARGE, true,
                                            true, "green", true);
    private final CarDTO nonExisting = new CarDTO("abc127", new Amount(3000), CarDTO.CarType.SMALL, false, true,
                                    "blue", true);

    @Before
    public void createAvailableCars() {
        availableCars = new ArrayList<>();
        availableCars.add(smallGreen1000Ac4wd);
        availableCars.add(smallGreen1000Ac4wdBooked);
        availableCars.add(mediumRed2000NoacNo4wd);
        availableCars.add(largeGreen1000Ac4wd);
    }

    @After
    public void dropAvailableCars() {
        availableCars = null;
    }

    @Test
    public void testNoMatch() {
        WildCardMatch instance = new WildCardMatch();
        Assert.assertEquals("Nonexisting car was found.", null, instance.match(nonExisting,
                                                                               availableCars));
    }

    @Test
    public void testPerfectMatch() {
        WildCardMatch instance = new WildCardMatch();
        Assert.assertEquals("Wrong car was found.", smallGreen1000Ac4wd, instance.match(
                            smallGreen1000Ac4wd, availableCars));
    }

    @Test
    public void testRegNoIsIgnored() {
        WildCardMatch instance = new WildCardMatch();
        CarDTO searched = new CarDTO("wrong", smallGreen1000Ac4wd.getPrice(), smallGreen1000Ac4wd.
                                     getSize(), smallGreen1000Ac4wd.isAC(), smallGreen1000Ac4wd.
                                     isFourWD(), smallGreen1000Ac4wd.getColor(),
                                     smallGreen1000Ac4wd.isBooked());
        CarDTO result = instance.match(searched, availableCars);
        Assert.assertEquals("Reg no was not ignored.", smallGreen1000Ac4wd.getRegNo(),
                            result.getRegNo());
        Assert.assertEquals("Reg no was not ignored.", smallGreen1000Ac4wd.getPrice(),
                            result.getPrice());
        Assert.assertEquals("Reg no was not ignored.", smallGreen1000Ac4wd.getSize(),
                            result.getSize());
        Assert.assertEquals("Reg no was not ignored.", smallGreen1000Ac4wd.isAC(),
                            result.isAC());
        Assert.assertEquals("Reg no was not ignored.", smallGreen1000Ac4wd.isFourWD(),
                            result.isFourWD());
        Assert.assertEquals("Reg no was not ignored.", smallGreen1000Ac4wd.getColor(),
                            result.getColor());
        Assert.assertEquals("Reg no was not ignored.", smallGreen1000Ac4wd.isBooked(),
                            result.isBooked());
    }

    @Test
    public void testBookedIsIgnored() {
        WildCardMatch instance = new WildCardMatch();
        CarDTO result = instance.match(smallGreen1000Ac4wdBooked, availableCars);
        Assert.assertEquals("Booked was not ignored.", smallGreen1000Ac4wd.getRegNo(),
                            result.getRegNo());
        Assert.assertEquals("Booked was not ignored.", smallGreen1000Ac4wd.getPrice(),
                            result.getPrice());
        Assert.assertEquals("Booked was not ignored.", smallGreen1000Ac4wd.getSize(),
                            result.getSize());
        Assert.assertEquals("Booked was not ignored.", smallGreen1000Ac4wd.isAC(),
                            result.isAC());
        Assert.assertEquals("Booked was not ignored.", smallGreen1000Ac4wd.isFourWD(),
                            result.isFourWD());
        Assert.assertEquals("Booked was not ignored.", smallGreen1000Ac4wd.getColor(),
                            result.getColor());
        Assert.assertEquals("Booked was not ignored.", smallGreen1000Ac4wd.isBooked(),
                            result.isBooked());
    }

    @Test
    public void testNullPrice() {
        WildCardMatch instance = new WildCardMatch();
        CarDTO searched = new CarDTO(smallGreen1000Ac4wd.getRegNo(), null,
                                     smallGreen1000Ac4wd.
                                     getSize(), smallGreen1000Ac4wd.isAC(), smallGreen1000Ac4wd.
                                     isFourWD(), smallGreen1000Ac4wd.getColor(),
                                     smallGreen1000Ac4wd.isBooked());
        CarDTO result = instance.match(searched, availableCars);
        Assert.assertEquals("Null price was not ignored.", smallGreen1000Ac4wd.getRegNo(),
                            result.getRegNo());
        Assert.assertEquals("Null price was not ignored.", smallGreen1000Ac4wd.getPrice(),
                            result.getPrice());
        Assert.assertEquals("Null price was not ignored.", smallGreen1000Ac4wd.getSize(),
                            result.getSize());
        Assert.assertEquals("Null price was not ignored.", smallGreen1000Ac4wd.isAC(),
                            result.isAC());
        Assert.assertEquals("Null price was not ignored.", smallGreen1000Ac4wd.isFourWD(),
                            result.isFourWD());
        Assert.assertEquals("Null price was not ignored.", smallGreen1000Ac4wd.getColor(),
                            result.getColor());
        Assert.assertEquals("Null price was not ignored.", smallGreen1000Ac4wd.isBooked(),
                            result.isBooked());
    }

    @Test
    public void testZeroPrice() {
        WildCardMatch instance = new WildCardMatch();
        CarDTO searched = new CarDTO(smallGreen1000Ac4wd.getRegNo(), new Amount(),
                                     smallGreen1000Ac4wd.
                                     getSize(), smallGreen1000Ac4wd.isAC(), smallGreen1000Ac4wd.
                                     isFourWD(), smallGreen1000Ac4wd.getColor(),
                                     smallGreen1000Ac4wd.isBooked());
        CarDTO result = instance.match(searched, availableCars);
        Assert.assertEquals("Zero price was not ignored.", smallGreen1000Ac4wd.getRegNo(),
                            result.getRegNo());
        Assert.assertEquals("Zero price was not ignored.", smallGreen1000Ac4wd.getPrice(),
                            result.getPrice());
        Assert.assertEquals("Zero price was not ignored.", smallGreen1000Ac4wd.getSize(),
                            result.getSize());
        Assert.assertEquals("Zero price was not ignored.", smallGreen1000Ac4wd.isAC(),
                            result.isAC());
        Assert.assertEquals("Zero price was not ignored.", smallGreen1000Ac4wd.isFourWD(),
                            result.isFourWD());
        Assert.assertEquals("Zero price was not ignored.", smallGreen1000Ac4wd.getColor(),
                            result.getColor());
        Assert.assertEquals("Zero price was not ignored.", smallGreen1000Ac4wd.isBooked(),
                            result.isBooked());
    }

    @Test
    public void testNullSize() {
        WildCardMatch instance = new WildCardMatch();
        CarDTO searched = new CarDTO(smallGreen1000Ac4wd.getRegNo(), smallGreen1000Ac4wd.getPrice(),
                                     null, smallGreen1000Ac4wd.isAC(), smallGreen1000Ac4wd.
                                     isFourWD(), smallGreen1000Ac4wd.getColor(),
                                     smallGreen1000Ac4wd.isBooked());
        CarDTO result = instance.match(searched, availableCars);
        Assert.assertEquals("Null size was not ignored.", smallGreen1000Ac4wd.getRegNo(),
                            result.getRegNo());
        Assert.assertEquals("Null size was not ignored.", smallGreen1000Ac4wd.getPrice(),
                            result.getPrice());
        Assert.assertEquals("Null size was not ignored.", smallGreen1000Ac4wd.getSize(),
                            result.getSize());
        Assert.assertEquals("Null size was not ignored.", smallGreen1000Ac4wd.isAC(),
                            result.isAC());
        Assert.assertEquals("Null size was not ignored.", smallGreen1000Ac4wd.isFourWD(),
                            result.isFourWD());
        Assert.assertEquals("Null size was not ignored.", smallGreen1000Ac4wd.getColor(),
                            result.getColor());
        Assert.assertEquals("Null size was not ignored.", smallGreen1000Ac4wd.isBooked(),
                            result.isBooked());
    }

    @Test
    public void testNullColor() {
        WildCardMatch instance = new WildCardMatch();
        CarDTO searched = new CarDTO(smallGreen1000Ac4wd.getRegNo(), smallGreen1000Ac4wd.getPrice(),
                                     smallGreen1000Ac4wd.getSize(), smallGreen1000Ac4wd.isAC(),
                                     smallGreen1000Ac4wd.
                                     isFourWD(), null,
                                     smallGreen1000Ac4wd.isBooked());
        CarDTO result = instance.match(searched, availableCars);
        Assert.assertEquals("Null color was not ignored.", smallGreen1000Ac4wd.getRegNo(),
                            result.getRegNo());
        Assert.assertEquals("Null color was not ignored.", smallGreen1000Ac4wd.getPrice(),
                            result.getPrice());
        Assert.assertEquals("Null color was not ignored.", smallGreen1000Ac4wd.getSize(),
                            result.getSize());
        Assert.assertEquals("Null color was not ignored.", smallGreen1000Ac4wd.isAC(),
                            result.isAC());
        Assert.assertEquals("Null color was not ignored.", smallGreen1000Ac4wd.isFourWD(),
                            result.isFourWD());
        Assert.assertEquals("Null color was not ignored.", smallGreen1000Ac4wd.getColor(),
                            result.getColor());
        Assert.assertEquals("Null color was not ignored.", smallGreen1000Ac4wd.isBooked(),
                            result.isBooked());
    }

}
