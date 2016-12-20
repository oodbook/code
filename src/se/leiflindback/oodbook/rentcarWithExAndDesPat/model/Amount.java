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
package se.leiflindback.oodbook.rentcarWithExAndDesPat.model;

/**
 * Represents an amount of money
 */
public final class Amount {
    private final int amount;

    /**
     * Creates a new instance, representing the amount 0.
     */
    public Amount() {
        this(0);
    }

    /**
     * Creates a new instance, representing the specified amount.
     *
     * @param amount The amount represented by the newly created instance.
     */
    public Amount(int amount) {
        this.amount = amount;
    }

    /**
     * Subtracts the specified <code>Amount</code> from this object and returns
     * an <code>Amount</code> instance with the result. The operation will
     * overflow if the result is smaller than <code>Integer.MIN_VALUE</code>.
     *
     * @param other The <code>Amount</code> to subtract.
     * @return The result of the subtraction.
     */
    public Amount minus(Amount other) {
        return new Amount(amount - other.amount);
    }

    /**
     * Adds the specified <code>Amount</code> to this object and returns an
     * <code>Amount</code> instance with the result. The operation will
     * overflow if the result is larger than <code>Integer.MAX_VALUE</code>.
     *
     * @param other The <code>Amount</code> to add.
     * @return The result of the addition.
     */
    public Amount plus(Amount other) {
        return new Amount(amount + other.amount);
    }

    /**
     * Two <code>Amount</code>s are equal if they represent the same amount.
     *
     * @param other The <code>Amount</code> to compare with this amount.
     * @return <code>true</code> if the specified amount is equal to this
     *         amount, <code>false</code> if it is not.
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Amount)) {
            return false;
        }
        Amount otherAmount = (Amount) other;
        return amount == otherAmount.amount;
    }

    @Override
    public String toString() {
        return Integer.toString(amount);
    }
}
