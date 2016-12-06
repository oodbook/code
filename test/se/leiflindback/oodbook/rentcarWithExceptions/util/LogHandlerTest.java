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
package se.leiflindback.oodbook.rentcarWithExceptions.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import se.leiflindback.oodbook.rentcarWithExceptions.integration.CarDTO;
import se.leiflindback.oodbook.rentcarWithExceptions.model.AlreadyBookedException;

public class LogHandlerTest {
    private LogHandler instance;
    private String logFileName = "rentcar-log.txt";

    @Before
    public void createInstance() {
        try {
            instance = new LogHandler();
        } catch (IOException ex) {
            fail("Could not create log handler");
            ex.printStackTrace();
        }
    }

    @After
    public void deleteFile() {
        instance = null;
        File logFile = new File(logFileName);
        logFile.delete();
    }

    @Test
    public void testLogException() throws IOException {
        CarDTO car = new CarDTO("regNo", null, null, true, true, null, true);
        AlreadyBookedException exception = new AlreadyBookedException(car);
        instance.logException(exception);
        String expResultMsg = "AlreadyBookedException: Unable to book " + car.getRegNo()
                              + ", since it is already booked.";
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        String expResultTime = now.format(formatter);
        String expResultStack = "at se.leiflindback.oodbook.rentcarWithExceptions.util.LogHandlerTest.testLogException";
        assertTrue("Wrong printout.", fileContains(expResultMsg));
        assertTrue("Wrong printout.", fileContains(expResultTime));
        assertTrue("Wrong printout.", fileContains(expResultStack));
    }

    private boolean fileContains(String searchedString) throws IOException {
        BufferedReader file = new BufferedReader(new FileReader(logFileName));
        String line = null;
        while ((line = file.readLine()) != null) {
            if (line.contains(searchedString)) {
                return true;
            }
        }
        return false;
    }
}
