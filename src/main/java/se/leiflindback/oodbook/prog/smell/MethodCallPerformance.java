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
 * Proves that the execution time of a method call is not significantly larger
 * than execution time of incrementing an int. There is duplicated code in this
 * class, but removing the duplication requires inheritance and abstract
 * methods, which are not covered when this example is given.
 */
public class MethodCallPerformance {
    private static final double NUMBER_OF_ITERATIONS = 1000000;
    private long startTime;

    private void startTimer() {
        startTime = System.nanoTime();
    }

    private long elapsedTime() {
        return System.nanoTime() - startTime;
    }

    private void timeMethodCall() {
        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
            callee(i);
        }
    }

    private void callee(int i) {
        i++;
    }

    private void timeIncrement() {
        int j = 0;
        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
            j++;
        }
    }

    public static void main(String[] args) {
        MethodCallPerformance tester = new MethodCallPerformance();
        tester.startTimer();
        tester.timeMethodCall();
        long time = tester.elapsedTime();
        System.out.println(
                "method call took " + time / NUMBER_OF_ITERATIONS + " ns.");

        tester.startTimer();
        tester.timeIncrement();
        time = tester.elapsedTime();
        System.out.println(
                "incrementing an int took " + time / NUMBER_OF_ITERATIONS + " ns.");
    }
}
