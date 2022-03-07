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
package se.leiflindback.oodbook.tests.exercises.contr.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import se.leiflindback.oodbook.tests.exercises.contr.integration.ClassInInteg;
import se.leiflindback.oodbook.tests.exercises.contr.integration.FileHandler;

public class ControllerTest {
    private String fileName = "the-file-name";
    private FileHandler fileHandler;
    private ClassInInteg classInInteg;
    private Controller instance;

    @BeforeEach
    public void setUp() {
        fileHandler = new FileHandler(fileName);
        classInInteg = new ClassInInteg();
        instance = new Controller(classInInteg, fileHandler);
    }

    @AfterEach
    public void tearDown() {
        fileHandler = null;
        classInInteg = null;
        instance = null;
    }

    @Test
    public void testAddingToZero() {
        instance.createObjInModel();
        int expResult = 2;
        int result = instance.addTwo(0);
        assertEquals(expResult, result, "Wrong result when adding to zero.");
    }
    
    @Test
    public void testAddingToNegOperand() {
        instance.createObjInModel();
        int expResult = -2;
        int result = instance.addTwo(-4);
        assertEquals(expResult, result, "Wrong result when adding to negative operand.");
    }
    
    @Test
    public void testAdditionThatGivesResultZero() {
        instance.createObjInModel();
        int expResult = 0;
        int result = instance.addTwo(-2);
        assertEquals(expResult, result, "Wrong result when result is supposed to be zero.");
    }
}
