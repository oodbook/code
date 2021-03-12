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
package se.leiflindback.oodbook.polyminherit.logapi;

import java.lang.reflect.InvocationTargetException;

/**
 * Contains a main method of the log API client, which loads new
 * <code>Logger</code> implementations at runtime.
 */
public class LoadImplAtRuntime {

    private int msgNo = 1;
    private AnyClassThatNeedsToLogSomething client = new AnyClassThatNeedsToLogSomething();

    /**
     * @param args Each command line parameter shall be the fully qualified
     * class name of a class implementing <code>Logger</code>. This class will
     * be loaded and used.
     * @throws java.lang.InstantiationException If failed to load log classes.
     * @throws java.lang.IllegalAccessException If failed to load log classes.
     * @throws java.lang.ClassNotFoundException If failed to load log classes.
     * @throws java.lang.NoSuchMethodException If failed to load log classes.
     * @throws java.lang.reflect.InvocationTargetException If failed to load log classes.
     */
    public static void main(String[] args) throws InstantiationException, IllegalAccessException,
            ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        LoadImplAtRuntime main = new LoadImplAtRuntime();
        for (String loggerClassName : args) {
            main.loadAndUseLogger(loggerClassName);
        }
    }

    private void loadAndUseLogger(String loggerClassName) throws
            InstantiationException,
            IllegalAccessException,
            ClassNotFoundException,
            NoSuchMethodException,
            InvocationTargetException {
        Class logClass = Class.forName(loggerClassName);
        Logger logger = (Logger) logClass.getDeclaredConstructor().newInstance();
        client.setLogger(logger);
        client.anyMethod(msgNo++);
    }
}
