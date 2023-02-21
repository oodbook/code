/*
 * Copyright (c) 2023, Leif Lindbäck
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its
 *    contributors may be used to endorse or promote products derived from
 *    this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package se.leiflindback.oodbook.prog.smell;

/**
 * An example of unnecessarily complicated flow control. The method containsChar
 * contains both a flag and an else statement that can be removed.
 *
 * This is just an example to illustrate better and worse ways of flow control.
 * In reality there's no point at all in writing such a method, since it already
 * exists in {@code java.lang.String}.
 */
public class StringSearchWithComplicatedFlowControl {

    /**
     * Tells whether the searched char exists in the specified string.
     *
     * @param src The string in which to look for the searched character.
     * @param searched The searched character.
     * @return {@code true} if the specified string contains the searched
     * character, {@code false} if it does not.
     */
    public boolean containsChar(String src, char searched) {
        boolean found = false;
        if (src != null) {
            char[] srcAsCharArray = src.toCharArray();
            for (int i = 0; i < srcAsCharArray.length; i++) {
                if (srcAsCharArray[i] == searched) {
                    found = true;
                }
            }
        }
        return found;
    }

    public static void main(String[] args) {
        System.out.println(new StringSearchWithComplicatedFlowControl().
                containsChar("abc123", '2'));
        System.out.println(new StringSearchWithComplicatedFlowControl().
                containsChar("abc123", 'g'));
    }
}
