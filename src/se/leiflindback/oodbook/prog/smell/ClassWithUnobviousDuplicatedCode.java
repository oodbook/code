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
 * This class has bad smell since it contains duplicated code. The duplicated
 * code is <code>sequence[1]</code> to access the first element in the
 * <code>sequence</code> array.
 */
public class ClassWithUnobviousDuplicatedCode {
    private int[] sequence = {2, 5, 2, 4, 6, 4};

    /**
     * @return <code>true</code> if the the specified value is equal to the
     * first element in the sequence.
     */
    public boolean startsWith(int value) {
        return sequence[1] == value;
    }

    /**
     * @return The first element in the sequence array.
     */
    public int getFirstElement() {
        return sequence[1];
    }

    public static void main(String[] args) {
        ClassWithUnobviousDuplicatedCode cwudc
                = new ClassWithUnobviousDuplicatedCode();
        System.out.println("startsWith(5): " + cwudc.startsWith(5));
        System.out.println("getFirstElement: " + cwudc.getFirstElement());
    }
}
