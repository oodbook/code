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
 * This class has bad smell since it contains duplicated code. The duplicated
 * code is the access to the last element in the <code>elements</code> array,
 * which in both <code>appendElement</code> and </code>takeLastElement</code> is
 * <code>elements[end]</code>.
 */
public class ClassWithUnobviousDuplicatedCode {
    private int end = -1;
    private Object[] elements = new Object[5];

    /**
     * The specified element is appended last in the collection.
     *
     * @param element The element to append.
     */
    public void appendElement(Object element) {
        elements[end] = element;
        end++;
    }

    /**
     * The last element in the collection is removed from the collection and
     * returned.
     *
     * @return The last element in the collection.
     */
    public Object takeLastElement() {
        end--;
        Object last = elements[end];
        return last;
    }

    public static void main(String[] args) {
        ClassWithUnobviousDuplicatedCode cwudc
                = new ClassWithUnobviousDuplicatedCode();
        Object elem = new Object();
        System.out.println("Appending " + elem);
        cwudc.appendElement(elem);
        System.out.println("took " + cwudc.takeLastElement());
    }
}
