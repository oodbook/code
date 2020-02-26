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

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CountingListTest {
    @Test
    public void testAddAndGet() {
        Object elemToSave = 3;
        CountingList instance = new CountingList();
        instance.add(elemToSave);
        Object expected = elemToSave;
        Object result = instance.get(0);
        assertEquals(expected, result, "Wrong element returned");
    }

    @Test
    public void testAddAllAndGet() {
        int noOfElemsToAdd = 10;
        CountingList elemsToAdd = new CountingList();
        for (int i = 0; i < noOfElemsToAdd; i++) {
            elemsToAdd.add(i);
        }
        CountingList instance = new CountingList();
        instance.addAll(elemsToAdd);
        for (int i = 0; i < noOfElemsToAdd; i++) {
            assertEquals(elemsToAdd.get(i), instance.get(i), "Wrong element read");
        }
    }

    @Test
    public void testSize() {
        int noOfElemsToAdd = 10;
        CountingList elemsToAdd = new CountingList();
        for (int i = 0; i < noOfElemsToAdd; i++) {
            elemsToAdd.add(i);
        }
        CountingList instance = new CountingList();
        instance.addAll(elemsToAdd);
        assertEquals(noOfElemsToAdd, instance.size(), "Wrong number of elements");
    }

    @Test
    public void testAddedElemsCounter() {
        int noOfElemsToAdd = 10;
        CountingList elemsToAdd = new CountingList();
        for (int i = 0; i < noOfElemsToAdd; i++) {
            elemsToAdd.add(i);
        }
        CountingList instance = new CountingList();
        instance.addAll(elemsToAdd);
        assertEquals(noOfElemsToAdd, instance.noOfAddedElems(), "Wrong number of elements");
    }

}
