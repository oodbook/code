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
package se.leiflindback.oodbook.tests.firstexample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AmountTest {
    private Amount amtNoArgConstr;
    private Amount amtWithAmtThree;

    @BeforeEach
    public void setUp() {
        amtNoArgConstr = new Amount();
        amtWithAmtThree = new Amount(3);
    }

    @AfterEach
    public void tearDown() {
        amtNoArgConstr = null;
        amtWithAmtThree = null;
    }

    @Test
    public void testEqualsNull() {
        Object other = null;
        boolean expResult = false;
        boolean result = amtNoArgConstr.equals(other);
        assertEquals(expResult, result, "Amount instance equal to null.");
    }

    @Test
    public void testEqualsJavaLangObject() {
        Object other = new Object();
        boolean expResult = false;
        boolean result = amtNoArgConstr.equals(other);
        assertEquals(expResult, result, "Amount instance equal to java.lang.Object instance.");
    }

    @Test
    public void testNotEqualNoArgConstr() {
        int amountOfOther = 2;
        Amount other = new Amount(amountOfOther);
        boolean expResult = false;
        boolean result = amtNoArgConstr.equals(other);
        assertEquals(expResult, result, "Amount instances with different states are equal.");
    }

    @Test
    public void testNotEqual() {
        int amountOfOther = 2;
        Amount other = new Amount(amountOfOther);
        boolean expResult = false;
        boolean result = amtWithAmtThree.equals(other);
        assertEquals(expResult, result, "Amount instances with different states are equal.");
    }

    @Test
    public void testEqual() {
        int amountOfOther = 3;
        Amount other = new Amount(amountOfOther);
        boolean expResult = true;
        boolean result = amtWithAmtThree.equals(other);
        assertEquals(expResult, result, "Amount instances with same states are not equal.");
    }

}
