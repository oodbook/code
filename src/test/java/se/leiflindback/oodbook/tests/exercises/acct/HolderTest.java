/*
 * The MIT License
 *
 * Copyright 2018 Leif Lindbäck <leifl@kth.se>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package se.leiflindback.oodbook.tests.exercises.acct;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HolderTest {

    private String holderNameNisse = "Nisse";
    private Holder holderNisse;

    @BeforeEach
    public void setUp() {
        holderNisse = new Holder(holderNameNisse);
    }

    @AfterEach
    public void tearDown() {
        holderNisse = null;
    }

    @Test
    public void testHolderNoIsGenerated() {
        long holderNo = holderNisse.getHolderNo();
        assertNotEquals(holderNo, 0, "No holder no was generated");
    }

    @Test
    public void testHolderNoIsNotSameEachTime() {
        long holderNo = new Holder(holderNameNisse).getHolderNo();
        assertNotEquals(holderNo, holderNisse.getHolderNo(), "Two holders got same holder no");
    }

    @Test
    public void testGetName() {
        String expResult = holderNameNisse;
        String result = holderNisse.getName();
        assertEquals(expResult, result, "Wrong name returned by getName");
    }
    
    @Test
    public void testAcctHandling() {
        int noOfAcctsOwnedByNisse = 12;
        Set<Account> expResult = addAcctsToHolderNisse(noOfAcctsOwnedByNisse);
        Set<Account> result = holderNisse.getAccounts();
        assertEquals(result, expResult, "Wrong accounts owned by holder.");
    }

    @Test
    public void testAcctHandlingWhenThereAreNoAccts() {
        Set<Account> expResult = new HashSet<>();
        Set<Account> result = holderNisse.getAccounts();
        assertTrue(result.equals(expResult), "Wrong accounts owned by holder.");
    }

    @Test
    public void testHashCode() {
        assertEquals(holderNisse.hashCode(), Long.valueOf(holderNisse.getHolderNo()).hashCode(), "Wrong hash code");
    }

    @Test
    public void testHolderIsEqualToItself() {
        assertTrue(holderNisse.equals(holderNisse), "Holder is not equal to itself");
    }

    @Test
    public void testHolderIsNotEqualToNull() {
        assertFalse(holderNisse.equals(null), "Holder was equal to null");
    }

    @Test
    public void testHolderIsNotEqualToOtherType() {
        assertFalse(holderNisse.equals(new Object()), "Holder was equal to java.lang.Object");
    }

    @Test
    public void testHolderIsNotEqualToOtherHolder() {
        assertFalse(holderNisse.equals(new Holder(holderNameNisse)), "Holder was equal to other holder");
    }

    @Test
    public void testStringRepContainsState() {
        int noOfAcctsOwnedByNisse = 7;
        Set<Account> acctsOwnedByNisse = addAcctsToHolderNisse(noOfAcctsOwnedByNisse);
        String strRep = holderNisse.toString();
        assertTrue(strRep.contains("Holder"), "wrong class name in string representation");
        assertTrue(strRep.contains(holderNameNisse), "wrong holder name in string representation");
        assertTrue(strRep.contains(Long.toString(holderNisse.getHolderNo())), "wrong holder no in string representation");
        for (Account acct : acctsOwnedByNisse) {
            assertTrue(strRep.contains(Long.toString(acct.getAcctNo())), "Acct missing in string representation");
        }
    }

    private Set<Account> addAcctsToHolderNisse(int noOfAcctsOwnedByNisse) {
        Set<Account> accountsOwnedByNisse = new HashSet<>();
        for (int i = 0; i < noOfAcctsOwnedByNisse; i++) {
            Account acct = new Account(holderNisse);
            holderNisse.addAccount(acct);
            accountsOwnedByNisse.add(acct);
        }
        return accountsOwnedByNisse;
    }
}
