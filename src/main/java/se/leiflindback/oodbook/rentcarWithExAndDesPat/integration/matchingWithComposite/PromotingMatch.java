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

import java.util.List;
import java.util.Map;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.integration.CarDTO;

/**
 * A <code>Matcher</code> that finds the car that shall be promoted, provided it has at least one
 * property, except registration number, matching the search criteria.
 */
class PromotingMatch implements Matcher {
    private static final String REG_NO_KEY = "REG_NO";
    private String regNoOfCarToPromote;

    PromotingMatch() {
    }

    @Override
    public CarDTO match(CarDTO searched, List<CarDTO> available) {
        for (CarDTO carToMatch : available) {
            if (!regNoOfCarToPromote.equals(carToMatch.getRegNo())) {
                continue;
            }
            if (carToMatch.getPrice() != null && searched.getPrice() != null
                && searched.getPrice().equals(carToMatch.getPrice())) {
                return carToMatch;
            }
            if (carToMatch.getSize() != null && searched.getSize() != null
                && searched.getSize().equals(carToMatch.getSize())) {
                return carToMatch;
            }
            if (carToMatch.getColor() != null && searched.getColor() != null
                && searched.getColor().equals(carToMatch.getColor())) {
                return carToMatch;
            }
            if (searched.isAC() == carToMatch.isAC()) {
                return carToMatch;
            }
            if (searched.isFourWD() == carToMatch.isFourWD()) {
                return carToMatch;
            }
        }
        return null;
    }

    @Override
    public void init(Map<String, String> properties) {
        regNoOfCarToPromote = properties.get(REG_NO_KEY);
    }
}
