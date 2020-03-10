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
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.integration.CarDTO;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.model.Amount;

/**
 *
 * @author leif
 */
public class MatcherFactoryTest {
    private static final String MATCHER_CLASS_NAME_KEY = "se.leiflindback.rentcar.matcher.classname";
    private static final String CAR_TO_PROMOTE_KEY = "se.leiflindback.rentcar.matcher.promote";

    public MatcherFactoryTest() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetDefaultMatcher() throws Exception {
        String className = "se.leiflindback.oodbook.rentcarWithExAndDesPat.integration.matching.PerfectMatch";
        MatcherFactory instance = MatcherFactory.getFactory();
        System.setProperty(MATCHER_CLASS_NAME_KEY, className);
        String expResult = className;
        String result = instance.getDefaultMatcher().getClass().getCanonicalName();
        assertEquals(expResult, result, "Wrong matcher class was loaded");
    }

    @Test
    public void testMatcherDoesNotExist() throws Exception {
        String className = "wrong";
        try {
            MatcherFactory instance = MatcherFactory.getFactory();
            System.setProperty(MATCHER_CLASS_NAME_KEY, className);
            instance.getDefaultMatcher();
            fail("managed to load non-existing class.");
        } catch (ClassNotFoundException ex) {
            assertTrue(ex.getMessage().contains(className),
                       "Wrong exception message: " + ex.getMessage());
        }
    }

    @Test
    public void testMatcherWithAdditionalData() throws Exception {
        String className = "se.leiflindback.oodbook.rentcarWithExAndDesPat.integration.matching.PromotingMatch";
        String carToPromote = "abc123";
        MatcherFactory instance = MatcherFactory.getFactory();
        System.setProperty(MATCHER_CLASS_NAME_KEY, className);
        System.setProperty(CAR_TO_PROMOTE_KEY, carToPromote);

        String expResult = className;
        Matcher matcher = instance.getDefaultMatcher();
        String result = matcher.getClass().getCanonicalName();
        assertEquals(expResult, result, "Wrong matcher class was loaded");

        CarDTO availableCar = new CarDTO(carToPromote, new Amount(1000), CarDTO.CarType.SMALL, true,
                                         true, "green", false);
        List<CarDTO> availableCars = new ArrayList<>();
        availableCars.add(availableCar);
        CarDTO searchedCar = new CarDTO(carToPromote, null, CarDTO.CarType.SMALL, false,
                                        false, null, false);
        assertEquals(availableCar, matcher.match(searchedCar, availableCars),
                     "Could not set additional data.");
    }
}
