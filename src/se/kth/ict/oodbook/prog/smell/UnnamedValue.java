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
package se.kth.ict.oodbook.prog.smell;

/**
 * An example of clarifying the code by introducing a method to give a name to a
 * value.
 */
public class UnnamedValue {
    private boolean isUnixEol(char character) {
        return character == 10;
    }

    /**
     * Finds the index of the first Unix EOL in the specified string.
     *
     * @param source The string in which to look for EOL.
     * @return The index of the first EOL, or -1 if there was no EOL in the
     *         specified string.
     */
    public int findIndexOfFirstEolWorse(String source) {
        char[] sourceChars = source.toCharArray();
        for (int i = 0; i < sourceChars.length; i++) {
            if (sourceChars[i] == 10) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the index of the first Unix EOL in the specified string.
     *
     * @param source The string in which to look for EOL.
     * @return The index of the first EOL, or -1 if there was no EOL in the
     *         specified string.
     */
    public int findIndexOfFirstEolBetter(String source) {
        char[] sourceChars = source.toCharArray();
        for (int i = 0; i < sourceChars.length; i++) {
            if (isUnixEol(sourceChars[i])) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        UnnamedValue unnamedVal = new UnnamedValue();
        String strWithEol = "sdasda\nasda\nsdsad";
        String strWithoutEol = "sdasdasdasad";
        System.out.println("index of first EOL (worse): " + unnamedVal.
                findIndexOfFirstEolWorse(strWithEol));
        System.out.println("index of first EOL (better): " + unnamedVal.
                findIndexOfFirstEolBetter(strWithEol));
        System.out.println("index of first EOL (worse): " + unnamedVal.
                findIndexOfFirstEolWorse(strWithoutEol));
        System.out.println("index of first EOL (better): " + unnamedVal.
                findIndexOfFirstEolBetter(strWithoutEol));
    }
}
