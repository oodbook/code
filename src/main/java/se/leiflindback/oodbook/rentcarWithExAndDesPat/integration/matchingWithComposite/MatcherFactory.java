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

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * A Singleton that creates instances of the algorithm used for matching a customer's wishes against
 * available cars. All such instances must implement <code>Matcher</code>.
 */
public class MatcherFactory {
    private static final MatcherFactory MATCHER_FACTORY = new MatcherFactory();
    private static final String MATCHER_CLASS_NAME_KEY = "se.leiflindback.rentcar.matcher.classname";
    private static final String MATCHER_INIT_DATA_KEY = "se.leiflindback.rentcar.matcher.props";

    /**
     * @return The only instance of this singleton.
     */
    public static MatcherFactory getFactory() {
        return MATCHER_FACTORY;
    }

    private MatcherFactory() {
    }

    /**
     * <p>
     * Returns a <code>Matcher</code> performing the default matching algorithm.The class name of
 the default <code>Matcher</code> implementation is read from the system property
    <code>se.leiflindback.rentcar.matcher.classname</code>.It is possible to specify more than
 one matcher, by setting this system property to a comma-separated string with class names of
 all desired matchers. When this is the case, all specified matching algorithms are executed,
 in the same order they were added, until an algorithm finds a car. Execution is stopped when
 an algorithm returns a non-null value.
 </p>
     *
     * <p>
     * It is possible to send key-value pairs to the matchers, by specifying these as a
     * comma-separated string in the system property
     * <code>se.leiflindback.rentcar.matcher.props</code>. All matchers will receive the same
     * key-value pairs.
     * </p>
     *
     * @return The default matcher
     * @throws ClassNotFoundException If unable to load the default matcher class.
     * @throws InstantiationException If unable to instantiate the default matcher class.
     * @throws IllegalAccessException If unable to instantiate the default matcher class.
     * @throws java.lang.NoSuchMethodException If unable to instantiate the default matcher class.
     * @throws java.lang.reflect.InvocationTargetException If unable to instantiate the default matcher class.
     */
    public Matcher getDefaultMatcher() throws ClassNotFoundException, InstantiationException,
                                              IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        String[] classNames = parseSystemProperty(MATCHER_CLASS_NAME_KEY);
        if (classNames.length > 1) {
            return createComposite(classNames);
        }
        return instantiateMatcher(classNames[0]);
    }

    private String[] parseSystemProperty(String propName) {
        return System.getProperty(propName).split(",");
    }

    private Matcher createComposite(String[] classNames) throws ClassNotFoundException,
                                                                InstantiationException,
                                                                IllegalAccessException,
                                                                NoSuchMethodException,
                                                                InvocationTargetException {
        CompositeMatcher composite = new CompositeMatcher();
        for (String className : classNames) {
            composite.addMatcher(instantiateMatcher(className));
        }
        return composite;
    }

    private Matcher instantiateMatcher(String className) throws ClassNotFoundException,
                                                                InstantiationException,
                                                                IllegalAccessException,
                                                                NoSuchMethodException,
                                                                InvocationTargetException {
        Class matcherClass = Class.forName(className);
        Matcher matcher = (Matcher) matcherClass.getDeclaredConstructor().newInstance();
        String[] initSysProps = parseSystemProperty(MATCHER_INIT_DATA_KEY);
        Map<String, String> props = new HashMap<>();
        for (String initSysProp : initSysProps) {
            String[] keyVal = initSysProp.split("=");
            props.put(keyVal[0], keyVal[1]);
        }
        matcher.init(props);
        return matcher;
    }
}
