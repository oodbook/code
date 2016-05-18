/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.ict.oodbook.tests.firstexample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author leif
 */
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

}
