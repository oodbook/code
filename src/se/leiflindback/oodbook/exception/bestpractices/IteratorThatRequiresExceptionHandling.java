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
package se.leiflindback.oodbook.exception.bestpractices;

/**
 * Reads the content of an array, element by element.
 */
public class IteratorThatRequiresExceptionHandling {
    //It is not relevant how the array is filled with content.
    private Object[] theArray = {1, 2, 3};
    private int cursor = 0;

    /**
     * Reads the array element currently indicated by the cursor, and advances the cursor one step.
     *
     * @return The current element.
     * @throws ArrayIndexOutOfBoundsException If trying to read beyond the end of teh array.
     */
    public Object next() throws ArrayIndexOutOfBoundsException {
        return theArray[cursor++];
    }

    public static void main(String[] args) {
        IteratorThatRequiresExceptionHandling iter = new IteratorThatRequiresExceptionHandling();
        try {
            while (true) {
                System.out.println(iter.next());
            }
        } catch (ArrayIndexOutOfBoundsException done) {
            System.out.println("All elements are printed");
        }
    }
}
