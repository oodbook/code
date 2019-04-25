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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.integration.CarDTO;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.model.Amount;

public class PromotingMatchTest {
    private static final String REG_NO_KEY = "REG_NO";
    private List<CarDTO> availableCars;
    private Map<String, String> configPropsWithExistingRegNoToPromote;
    private Map<String, String> configPropsWithNonExistingRegNoToPromote;

    private final String ExistingRegNoToPromote = "abc124";
    private final String NonExistingRegNoToPromote = "wrong";
    private final CarDTO smallGreen1000Ac4wd = new CarDTO("abc123", new Amount(1000),
                                                          CarDTO.CarType.SMALL,
                                                          true,
                                                          true, "green", false);
    private final CarDTO mediumRed2000NoacNo4wd = new CarDTO("abc124", new Amount(2000),
                                                             CarDTO.CarType.MEDIUM,
                                                             false, false, "red", false);
    private final CarDTO smallGreen1000Ac4wdBooked = new CarDTO("abc125", new Amount(1000),
                                                                CarDTO.CarType.SMALL,
                                                                true, true, "green", true);
    private final CarDTO largeGreen1000Ac4wd = new CarDTO("abc126", new Amount(1000),
                                                          CarDTO.CarType.LARGE,
                                                          true,
                                                          true, "green", true);
    private final CarDTO nonExisting = new CarDTO("abc127", new Amount(3000), CarDTO.CarType.SMALL,
                                                  false,
                                                  true,
                                                  "blue", true);

    
    private void createConfProps() {
        Map<String, String> modifyableConfPropsWithExistingRegNoToPromote = new HashMap<>();
        Map<String, String> modifyableConfPropsWithNonExistingRegNoToPromote = new HashMap<>();
        modifyableConfPropsWithExistingRegNoToPromote.put(REG_NO_KEY, ExistingRegNoToPromote);
        modifyableConfPropsWithNonExistingRegNoToPromote.put(REG_NO_KEY, NonExistingRegNoToPromote);
        configPropsWithExistingRegNoToPromote = Collections.unmodifiableMap(
                modifyableConfPropsWithExistingRegNoToPromote);
        configPropsWithNonExistingRegNoToPromote = Collections.unmodifiableMap(
                modifyableConfPropsWithNonExistingRegNoToPromote);
    }

    
    private void createAvailableCars() {
        availableCars = new ArrayList<>();
        availableCars.add(smallGreen1000Ac4wd);
        availableCars.add(mediumRed2000NoacNo4wd);
        availableCars.add(largeGreen1000Ac4wd);
    }
    
    @Before
    public void setUp() {
        createAvailableCars();
        createConfProps();
    }

    @After
    public void dropObjs() {
        availableCars = null;
        configPropsWithExistingRegNoToPromote = null;
        configPropsWithNonExistingRegNoToPromote = null;
    }

    @Test
    public void testNoCarToPromoteNoMatch() {
        PromotingMatch instance = new PromotingMatch();
        instance.init(configPropsWithNonExistingRegNoToPromote);
        Assert.assertEquals("Nonexisting car was found.", null, instance.match(nonExisting,
                                                                               availableCars));
    }

    @Test
    public void testNoCarToPromoteMatch() {
        PromotingMatch instance = new PromotingMatch();
        instance.init(configPropsWithNonExistingRegNoToPromote);
        Assert.assertEquals("Non promoted car was found.", null, instance.match(
                            smallGreen1000Ac4wd, availableCars));
    }

    @Test
    public void testCarToPromotePriceMatches() {
        PromotingMatch instance = new PromotingMatch();
        instance.init(configPropsWithExistingRegNoToPromote);
        CarDTO searched = new CarDTO(mediumRed2000NoacNo4wd.getRegNo(), mediumRed2000NoacNo4wd.
                                     getPrice(), smallGreen1000Ac4wd.
                                             getSize(), smallGreen1000Ac4wd.isAC(),
                                     smallGreen1000Ac4wd.
                                             isFourWD(), smallGreen1000Ac4wd.getColor(),
                                     smallGreen1000Ac4wd.isBooked());
        CarDTO result = instance.match(searched, availableCars);
        Assert.assertEquals("Reg no was not ignored.", mediumRed2000NoacNo4wd.getRegNo(),
                            result.getRegNo());
        Assert.assertEquals("Reg no was not ignored.", mediumRed2000NoacNo4wd.getPrice(),
                            result.getPrice());
        Assert.assertEquals("Reg no was not ignored.", mediumRed2000NoacNo4wd.getSize(),
                            result.getSize());
        Assert.assertEquals("Reg no was not ignored.", mediumRed2000NoacNo4wd.isAC(),
                            result.isAC());
        Assert.assertEquals("Reg no was not ignored.", mediumRed2000NoacNo4wd.isFourWD(),
                            result.isFourWD());
        Assert.assertEquals("Reg no was not ignored.", mediumRed2000NoacNo4wd.getColor(),
                            result.getColor());
        Assert.assertEquals("Reg no was not ignored.", mediumRed2000NoacNo4wd.isBooked(),
                            result.isBooked());
    }

    @Test
    public void testCarToPromoteSizeMatches() {
        PromotingMatch instance = new PromotingMatch();
        instance.init(configPropsWithExistingRegNoToPromote);
        CarDTO searched = new CarDTO(mediumRed2000NoacNo4wd.getRegNo(), smallGreen1000Ac4wd.
                                     getPrice(), mediumRed2000NoacNo4wd.
                                             getSize(), smallGreen1000Ac4wd.isAC(),
                                     smallGreen1000Ac4wd.
                                             isFourWD(), smallGreen1000Ac4wd.getColor(),
                                     smallGreen1000Ac4wd.isBooked());
        CarDTO result = instance.match(searched, availableCars);
        Assert.assertEquals("Reg no was not ignored.", mediumRed2000NoacNo4wd.getRegNo(),
                            result.getRegNo());
        Assert.assertEquals("Reg no was not ignored.", mediumRed2000NoacNo4wd.getPrice(),
                            result.getPrice());
        Assert.assertEquals("Reg no was not ignored.", mediumRed2000NoacNo4wd.getSize(),
                            result.getSize());
        Assert.assertEquals("Reg no was not ignored.", mediumRed2000NoacNo4wd.isAC(),
                            result.isAC());
        Assert.assertEquals("Reg no was not ignored.", mediumRed2000NoacNo4wd.isFourWD(),
                            result.isFourWD());
        Assert.assertEquals("Reg no was not ignored.", mediumRed2000NoacNo4wd.getColor(),
                            result.getColor());
        Assert.assertEquals("Reg no was not ignored.", mediumRed2000NoacNo4wd.isBooked(),
                            result.isBooked());
    }

    @Test
    public void testCarToPromoteACMatches() {
        PromotingMatch instance = new PromotingMatch();
        instance.init(configPropsWithExistingRegNoToPromote);
        CarDTO searched = new CarDTO(mediumRed2000NoacNo4wd.getRegNo(), smallGreen1000Ac4wd.
                                     getPrice(), smallGreen1000Ac4wd.
                                             getSize(), mediumRed2000NoacNo4wd.isAC(),
                                     smallGreen1000Ac4wd.
                                             isFourWD(), smallGreen1000Ac4wd.getColor(),
                                     smallGreen1000Ac4wd.isBooked());
        CarDTO result = instance.match(searched, availableCars);
        Assert.assertEquals("Reg no was not ignored.", mediumRed2000NoacNo4wd.getRegNo(),
                            result.getRegNo());
        Assert.assertEquals("Reg no was not ignored.", mediumRed2000NoacNo4wd.getPrice(),
                            result.getPrice());
        Assert.assertEquals("Reg no was not ignored.", mediumRed2000NoacNo4wd.getSize(),
                            result.getSize());
        Assert.assertEquals("Reg no was not ignored.", mediumRed2000NoacNo4wd.isAC(),
                            result.isAC());
        Assert.assertEquals("Reg no was not ignored.", mediumRed2000NoacNo4wd.isFourWD(),
                            result.isFourWD());
        Assert.assertEquals("Reg no was not ignored.", mediumRed2000NoacNo4wd.getColor(),
                            result.getColor());
        Assert.assertEquals("Reg no was not ignored.", mediumRed2000NoacNo4wd.isBooked(),
                            result.isBooked());
    }

    @Test
    public void testCarToPromote4wdMatches() {
        PromotingMatch instance = new PromotingMatch();
        instance.init(configPropsWithExistingRegNoToPromote);
        CarDTO searched = new CarDTO(mediumRed2000NoacNo4wd.getRegNo(), smallGreen1000Ac4wd.
                                     getPrice(), smallGreen1000Ac4wd.
                                             getSize(), smallGreen1000Ac4wd.isAC(),
                                     mediumRed2000NoacNo4wd.
                                             isFourWD(), smallGreen1000Ac4wd.getColor(),
                                     smallGreen1000Ac4wd.isBooked());
        CarDTO result = instance.match(searched, availableCars);
        Assert.assertEquals("Reg no was not ignored.", mediumRed2000NoacNo4wd.getRegNo(),
                            result.getRegNo());
        Assert.assertEquals("Reg no was not ignored.", mediumRed2000NoacNo4wd.getPrice(),
                            result.getPrice());
        Assert.assertEquals("Reg no was not ignored.", mediumRed2000NoacNo4wd.getSize(),
                            result.getSize());
        Assert.assertEquals("Reg no was not ignored.", mediumRed2000NoacNo4wd.isAC(),
                            result.isAC());
        Assert.assertEquals("Reg no was not ignored.", mediumRed2000NoacNo4wd.isFourWD(),
                            result.isFourWD());
        Assert.assertEquals("Reg no was not ignored.", mediumRed2000NoacNo4wd.getColor(),
                            result.getColor());
        Assert.assertEquals("Reg no was not ignored.", mediumRed2000NoacNo4wd.isBooked(),
                            result.isBooked());
    }

    @Test
    public void testCarToPromoteColorMatches() {
        PromotingMatch instance = new PromotingMatch();
        instance.init(configPropsWithExistingRegNoToPromote);
        CarDTO searched = new CarDTO(mediumRed2000NoacNo4wd.getRegNo(), smallGreen1000Ac4wd.
                                     getPrice(), smallGreen1000Ac4wd.
                                             getSize(), smallGreen1000Ac4wd.isAC(),
                                     smallGreen1000Ac4wd.
                                             isFourWD(), mediumRed2000NoacNo4wd.getColor(),
                                     smallGreen1000Ac4wd.isBooked());
        CarDTO result = instance.match(searched, availableCars);
        Assert.assertEquals("Reg no was not ignored.", mediumRed2000NoacNo4wd.getRegNo(),
                            result.getRegNo());
        Assert.assertEquals("Reg no was not ignored.", mediumRed2000NoacNo4wd.getPrice(),
                            result.getPrice());
        Assert.assertEquals("Reg no was not ignored.", mediumRed2000NoacNo4wd.getSize(),
                            result.getSize());
        Assert.assertEquals("Reg no was not ignored.", mediumRed2000NoacNo4wd.isAC(),
                            result.isAC());
        Assert.assertEquals("Reg no was not ignored.", mediumRed2000NoacNo4wd.isFourWD(),
                            result.isFourWD());
        Assert.assertEquals("Reg no was not ignored.", mediumRed2000NoacNo4wd.getColor(),
                            result.getColor());
        Assert.assertEquals("Reg no was not ignored.", mediumRed2000NoacNo4wd.isBooked(),
                            result.isBooked());
    }
}
