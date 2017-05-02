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
package se.leiflindback.oodbook.prog.smell;

/**
 * This class has a method who's body is not very easy to understand.
 */
public class ClassWithLongMethod {

    /**
     * Counts the number of upper case letters in the specified string.
     *
     * @param source The string in which uppercase letters are counted.
     * @return The number of uppercase letters in the specified string.
     */
    public int countUpperCaseLetters(String source) {
        int noOfUpperCaseLetters = 0;
        for (char letter : source.toCharArray()) {
            if (letter >= 65 && letter <= 90) { 
                noOfUpperCaseLetters++;
            }
        }
        return noOfUpperCaseLetters;
    }

    public static void main(String[] args) {
        String strWithUCLetters = "dAknhsdZkjsdK";
        int uCLetterCount = new ClassWithLongMethod().countUpperCaseLetters(
                strWithUCLetters);
        System.out.println(
                "There were " + uCLetterCount + " upper case letters in '"
                + strWithUCLetters + "'");
    }

}
