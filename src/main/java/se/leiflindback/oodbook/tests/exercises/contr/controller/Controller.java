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

import se.leiflindback.oodbook.tests.exercises.contr.integration.ClassInInteg;
import se.leiflindback.oodbook.tests.exercises.contr.integration.FileHandler;
import se.leiflindback.oodbook.tests.exercises.contr.model.ClassInModel;

/**
 * Simulates a controller with two system operations.
 */
public class Controller {
    private ClassInInteg integObj;
    private FileHandler fileHandler;
    private ClassInModel classInModel;
    
    /**
     * Creates a new instance using the specified objects.
     * 
     * @param integObj    Some required object in the integration layer.
     * @param fileHandler Used for all file access.
     */
    public Controller(ClassInInteg integObj, FileHandler fileHandler) {
        this.integObj = integObj;
        this.fileHandler = fileHandler;
    }
        
    /**
     * Initializes the model.
     */
    public void createObjInModel() {
        classInModel = new ClassInModel();
    }
    
    /**
     * Adds two to the specified operand.
     * 
     * @param operand The number two which two will be added.
     * @return        <code>operand<code> + 2
     */
    public int addTwo(int operand) {
        return classInModel.addTwo(operand);
    }
}
