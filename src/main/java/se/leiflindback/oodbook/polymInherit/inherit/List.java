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
package se.leiflindback.oodbook.polyminherit.inherit;

/**
 * Represents a list of elements.
 */
public class List {
    private static final int INITIAL_SIZE = 100;
    private Object[] content = new Object[INITIAL_SIZE];
    private int firstFreeIndex = 0;
    
    /**
     * Adds an element last in the list.
     * 
     * @param element The element to add.
     */
    public void add(Object element) {
        content[firstFreeIndex++] = element;
    }

    /**
     * Adds all elements in the specified list to this list.
     * 
     * @param elemsToAdd The elements to add. 
     */
    public void addAll(List elemsToAdd) {
        for (int i=0; i<elemsToAdd.size(); i++) {
            add(elemsToAdd.get(i));
        }
    }

    /**
     * Tells how many elements there are currently in the list.
     * 
     * @return the number of elements currently in the list. 
     */
    public int size() {
        return firstFreeIndex;
    }

    /**
     * Returns the element at the specified index.
     * 
     * @param index The index of the element  to return.
     * @return the element at the specified index.
     */
    public Object get(int index) {
        return content[index];
    }
}
