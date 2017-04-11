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
package se.leiflindback.oodbook.rentcarWithExAndDesPat.integration.matchingWithComposite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.integration.CarDTO;

/**
 * A <code>Matcher</code>, which performs multiple matching algorithms. All matching algorithms
 * added to this composite are executed, in the same order they were added, until an algorithm finds
 * a car. Execution is stopped when an algorithm returns a non-null value.
 */
class CompositeMatcher implements Matcher {
    private List<Matcher> matchingAlgorithms = new ArrayList<>();

    public CompositeMatcher() {
    }

    /**
     * Invokes all matching algorithms added to this composite, in the same order they were added,
     * until an algorithm finds a car. When a matching algorithm has found a car, that car is
     * returned, and no more algorithms are called.
     *
     * @param searched  Search criteria
     * @param available Available cars
     * @return A matching car, or <code>null</code> if none was found.
     */
    @Override
    public CarDTO match(CarDTO searched, List<CarDTO> available) {
        for (Matcher matcher : matchingAlgorithms) {
            CarDTO found = matcher.match(searched, available);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    /**
     * Adds a matching algorithm that will be invoked when this composite is searching for a car.
     * The newly added algorithm will be called after all previously added algorithms, provided non
     * of the previous algorithms finds a matching car.
     *
     * @param matcher The new <code>Matcher</code> to add.
     */
    void addMatcher(Matcher matcher) {
        matchingAlgorithms.add(matcher);
    }

    @Override
    public void init(Map<String, String> properties) {
    }
}
