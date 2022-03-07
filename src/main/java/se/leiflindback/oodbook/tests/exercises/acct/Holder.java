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

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

public class Holder {
    private SecureRandom randomNoGenerator = new SecureRandom();
    private Set<Account> accounts = new HashSet<>();
    private String name;
    private long holderNo;

    /**
     * Creates a new instance with the specified name. A unique holder number
     * will be set on the newly created instance.
     *
     * @param name The holder's name.
     */
    public Holder(String name) {
        this.name = name;
        holderNo = generateHolderNo();
    }

    /**
     * @return A set containing all accounts owned by this account holder.
     */
    public Set<Account> getAccounts() {
        Set<Account> copyOfAccts = new HashSet<>();
        copyOfAccts.addAll(accounts);
        return copyOfAccts;
    }

    /**
     * Adds the specified account to the set of accounts owned by this holder.
     * There is no limit on the number of accounts that can be owned by the same
     * holder.
     *
     * @param acct The account to add to this holder's accounts.
     */
    public void addAccount(Account acct) {
        accounts.add(acct);
    }

    /**
     * @return This holder's name. 
     */
    public String getName() {
        return name;
    }

    /**
     * Changes this holder's name.
     * 
     * @param hldName The new name of this holder.
     */
    public void setName(String hldName) {
        this.name = hldName;
    }

    /**
     * @return This holder's holder number. 
     */
    public long getHolderNo() {
        return holderNo;
    }

    @Override
    public int hashCode() {
        return Long.valueOf(holderNo).hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Holder)) {
            return false;
        }
        Holder other = (Holder) object;
        return this.holderNo == other.holderNo;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Holder");
        builder.append("[");
        addFieldToStringRep(builder, "name", name);
        addFieldSeparatorToStringRep(builder);
        addFieldToStringRep(builder, "holderNo", Long.toString(holderNo));
        addFieldSeparatorToStringRep(builder);
        builder.append("accounts: [");
        for (Account acct : accounts) {
            builder.append(acct);
            addFieldSeparatorToStringRep(builder);
        }
        builder.append("]]");
        return builder.toString();
    }
        
    private long generateHolderNo() {
        return randomNoGenerator.nextLong();
    }

    private void addFieldToStringRep(StringBuilder builder, String name, String value) {
        builder.append(name);
        builder.append(":");
        builder.append(value);
    }
    
    private void addFieldSeparatorToStringRep(StringBuilder builder) {
        builder.append(", ");
    }
}
