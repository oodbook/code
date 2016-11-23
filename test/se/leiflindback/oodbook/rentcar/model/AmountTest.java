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
package se.leiflindback.oodbook.rentcar.model;

import se.leiflindback.oodbook.rentcar.model.Amount;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AmountTest {
    private Amount amtNoArgConstr;
    private Amount amtWithAmtThree;

    @Before
    public void setUp() {
        amtNoArgConstr = new Amount();
        amtWithAmtThree = new Amount(3);
    }

    @After
    public void tearDown() {
        amtNoArgConstr = null;
        amtWithAmtThree = null;
    }

    @Test
    public void testEqualsNull() {
        Object other = null;
        boolean expResult = false;
        boolean result = amtNoArgConstr.equals(other);
        assertEquals("Amount instance equal to null.", expResult, result);
    }

    @Test
    public void testEqualsJavaLangObject() {
        Object other = new Object();
        boolean expResult = false;
        boolean result = amtNoArgConstr.equals(other);
        assertEquals("Amount instance equal to java.lang.Object instance.",
                     expResult, result);
    }

    @Test
    public void testNotEqualNoArgConstr() {
        int amountOfOther = 2;
        Amount other = new Amount(amountOfOther);
        boolean expResult = false;
        boolean result = amtNoArgConstr.equals(other);
        assertEquals("Amount instances with different states are equal.",
                     expResult, result);
    }

    @Test
    public void testNotEqual() {
        int amountOfOther = 2;
        Amount other = new Amount(amountOfOther);
        boolean expResult = false;
        boolean result = amtWithAmtThree.equals(other);
        assertEquals("Amount instances with different states are equal.",
                     expResult, result);
    }

    @Test
    public void testEqual() {
        int amountOfOther = 3;
        Amount other = new Amount(amountOfOther);
        boolean expResult = true;
        boolean result = amtWithAmtThree.equals(other);
        assertEquals("Amount instances with same states are not equal.",
                     expResult, result);
    }

    @Test
    public void testEqualNoArgConstr() {
        int amountOfOther = 0;
        Amount other = new Amount(amountOfOther);
        boolean expResult = true;
        boolean result = amtNoArgConstr.equals(other);
        assertEquals("Amount instances with same states are not equal.",
                     expResult, result);
    }

    @Test
    public void testMinus() {
        int amountOfOperand1 = 10;
        int amountOfOperand2 = 3;
        Amount operand1 = new Amount(amountOfOperand1);
        Amount operand2 = new Amount(amountOfOperand2);
        Amount expResult = new Amount(amountOfOperand1 - amountOfOperand2);
        Amount result = operand1.minus(operand2);
        assertEquals("Wrong subtraction result", expResult, result);
    }

    @Test
    public void testMinusNegResult() {
        int amountOfOperand1 = 3;
        int amountOfOperand2 = 10;
        Amount operand1 = new Amount(amountOfOperand1);
        Amount operand2 = new Amount(amountOfOperand2);
        Amount expResult = new Amount(amountOfOperand1 - amountOfOperand2);
        Amount result = operand1.minus(operand2);
        assertEquals("Wrong subtraction result", expResult, result);
    }
    @Test
    public void testMinusZeroResultNegOperand() {
        int amountOfOperand1 = -3;
        int amountOfOperand2 = -3;
        Amount operand1 = new Amount(amountOfOperand1);
        Amount operand2 = new Amount(amountOfOperand2);
        Amount expResult = new Amount(amountOfOperand1 - amountOfOperand2);
        Amount result = operand1.minus(operand2);
        assertEquals("Wrong subtraction result", expResult, result);
    }
    
    @Test
    public void testPlus() {
        int amountOfOperand1 = 10;
        int amountOfOperand2 = 3;
        Amount operand1 = new Amount(amountOfOperand1);
        Amount operand2 = new Amount(amountOfOperand2);
        Amount expResult = new Amount(amountOfOperand1 + amountOfOperand2);
        Amount result = operand1.plus(operand2);
        assertEquals("Wrong subtraction result", expResult, result);
    }

    @Test
    public void testPlusNegResult() {
        int amountOfOperand1 = 3;
        int amountOfOperand2 = -10;
        Amount operand1 = new Amount(amountOfOperand1);
        Amount operand2 = new Amount(amountOfOperand2);
        Amount expResult = new Amount(amountOfOperand1 + amountOfOperand2);
        Amount result = operand1.plus(operand2);
        assertEquals("Wrong subtraction result", expResult, result);
    }
    @Test
    public void testPlusZeroResultNegOperand() {
        int amountOfOperand1 = -3;
        int amountOfOperand2 = 3;
        Amount operand1 = new Amount(amountOfOperand1);
        Amount operand2 = new Amount(amountOfOperand2);
        Amount expResult = new Amount(amountOfOperand1 + amountOfOperand2);
        Amount result = operand1.plus(operand2);
        assertEquals("Wrong subtraction result", expResult, result);
    }

    @Test
    public void toStringPosAmt() {
        int representedAmt = 10;
        Amount amount = new Amount(representedAmt);
        String expResult = Integer.toString(representedAmt);
        String result = amount.toString();
        assertEquals("Wrong string returned by toString", expResult, result);
    }

    @Test
    public void toStringNegAmt() {
        int representedAmt = -10;
        Amount amount = new Amount(representedAmt);
        String expResult = Integer.toString(representedAmt);
        String result = amount.toString();
        assertEquals("Wrong string returned by toString", expResult, result);
    }

    @Test
    public void toStringZeroAmt() {
        int representedAmt = 0;
        Amount amount = new Amount(representedAmt);
        String expResult = Integer.toString(representedAmt);
        String result = amount.toString();
        assertEquals("Wrong string returned by toString", expResult, result);
    }
}
