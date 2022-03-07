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

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {
    private String holderNameFia = "Fia";
    private Holder holderFia = new Holder(holderNameFia);
    private int initBalance10 = 10;
    private Account acctFia10;

    @BeforeEach
    public void setUp() {
        acctFia10 = new Account(holderFia, initBalance10);
    }

    @AfterEach
    public void tearDown() {
        acctFia10 = null;
    }

    @Test
    public void testConstrWithoutBalance() {
        Account instance = new Account(holderFia);
        int expResult = 0;
        int result = instance.getBalance();
        assertEquals(expResult, result, "Wrong balance returned when acct created with no-bakance constructor");
    }

    @Test
    public void testAcctNoIsGenerated() {
        long result = acctFia10.getAcctNo();
        assertNotEquals(result, 0, "No acct no was generated");
    }

    @Test
    public void testAcctNoIsNotSameEachTime() {
        long acctNo = new Account(holderFia).getAcctNo();
        assertNotEquals(acctNo, acctFia10.getAcctNo(), "Two accounts got same acct no");
    }

    @Test
    public void testSuccessfulWithdrawal() {
        try {
            int amtToWithdraw = 3;
            int expResult = initBalance10 - amtToWithdraw;
            acctFia10.withdraw(amtToWithdraw);
            int result = acctFia10.getBalance();
            assertEquals(expResult, result, "Wrong balance after withdrawal");
        } catch (IllegalBankTransactionException ex) {
            fail("Got exception from successful withdrawal");
        }
    }

    @Test
    public void testWithdrawToBalanzeZero() {
        try {
            int expResult = 0;
            acctFia10.withdraw(initBalance10);
            int result = acctFia10.getBalance();
            assertEquals(expResult, result, "Wrong balance after withdrawal");
        } catch (IllegalBankTransactionException ex) {
            fail("Got exception from successful withdrawal");
        }
    }

    @Test
    public void testWithdrawNegAmt() {
        int amtToWithdraw = -3;
        try {
            acctFia10.withdraw(amtToWithdraw);
            fail("Managed to withdraw neg amount");
        } catch (IllegalBankTransactionException ex) {
            assertTrue(ex.getMessage().contains(Integer.toString(amtToWithdraw)), "Wrong error message");
        }
    }

    @Test
    public void testWithdrawZero() {
        int amtToWithdraw = 0;
        try {
            acctFia10.withdraw(amtToWithdraw);
            fail("Managed to withdraw zero");
        } catch (IllegalBankTransactionException ex) {
            assertTrue(ex.getMessage().contains(Integer.toString(amtToWithdraw)), "Wrong error message");
        }
    }

    @Test
    public void testOverdraft() {
        int amtToWithdraw = 11;
        try {
            acctFia10.withdraw(amtToWithdraw);
            fail("Managed to overdraft");
        } catch (IllegalBankTransactionException ex) {
            assertTrue(ex.getMessage().contains(Integer.toString(amtToWithdraw)), "Wrong error message");
            assertTrue(ex.getMessage().contains(Integer.toString(initBalance10)), "Wrong error message");
        }
    }

    @Test
    public void testSuccessfulDeposit() {
        try {
            int amtToDeposit = 3;
            int expResult = initBalance10 + amtToDeposit;
            acctFia10.deposit(amtToDeposit);
            int result = acctFia10.getBalance();
            assertEquals(expResult, result, "Wrong balance after deposit");
        } catch (IllegalBankTransactionException ex) {
            fail("Got exception from successful deposit");
        }
    }

    @Test
    public void testDepositNegAmt() {
        int amtToDeposit = -3;
        try {
            acctFia10.deposit(amtToDeposit);
            fail("Managed to deposit neg amount");
        } catch (IllegalBankTransactionException ex) {
            assertTrue(ex.getMessage().contains(Integer.toString(amtToDeposit)), "Wrong error message");
        }
    }

    @Test
    public void testDepositZero() {
        int amtToDeposit = 0;
        try {
            acctFia10.deposit(amtToDeposit);
            fail("Managed to deposit zero");
        } catch (IllegalBankTransactionException ex) {
            assertTrue(ex.getMessage().contains(Integer.toString(amtToDeposit)), "Wrong error message");
        }
    }

    @Test
    public void testHashCode() {
        assertEquals(acctFia10.hashCode(), Long.valueOf(acctFia10.getAcctNo()).hashCode(), "Wrong hash code");
    }

    @Test
    public void testAccountIsEqualToItself() {
        assertTrue(acctFia10.equals(acctFia10), "Acct is not equal to itself");
    }

    @Test
    public void testAcctIsNotEqualToNull() {
        assertFalse(acctFia10.equals(null), "Acct was equal to null");
    }

    @Test
    public void testAcctIsNotEqualToOtherType() {
        assertFalse(acctFia10.equals(new Object()), "Acct was equal to java.lang.Object");
    }

    @Test
    public void testAcctIsNotEqualToOtherAcct() {
        assertFalse(acctFia10.equals(new Account(holderFia)), "Acct was equal to other acct");
    }

    @Test
    public void testStringRepContainsState() {
        String strRep = acctFia10.toString();
        assertTrue(strRep.contains("Account"), "wrong class name in string representation");
        assertTrue(strRep.contains(Long.toString(holderFia.getHolderNo())), "wrong holder no in string representation");
        assertTrue(strRep.contains(Integer.toString(initBalance10)), "wrong balance in string representation");
        assertTrue(strRep.contains(Long.toString(acctFia10.getAcctNo())), "wrong acct no in string representation");
    }
}
