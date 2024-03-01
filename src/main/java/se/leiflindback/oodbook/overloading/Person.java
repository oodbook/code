/*
 * Copyright (c) 2024, Leif Lindbäck
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are 
 * met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright 
 *    notice, this list of conditions and the following disclaimer in the 
 *    documentation and/or other materials provided with the distribution.
 * 
 * 3. Neither the name of the copyright holder nor the names of its 
 *    contributors may be used to endorse or promote products derived from 
 *    this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS 
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR 
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR 
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF 
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS 
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package se.leiflindback.oodbook.overloading;

/**
 * The purpose of this class is to illustrate how overloaded methods (constructors in this case) can
 * call each other to avoid duplicated code.
 */
public class Person {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String pnr;

    /**
     * Creates a new person, with no properties set at all.
     */
    public Person() {
        this(null, null);
    }

    /**
     * Creates a new person, without email address, phone number and person number.
     *
     * @param firstName The newly created person's first name.
     * @param lastName The newly created person's last name.
     */
    public Person(String firstName, String lastName) {
        this(firstName, lastName, null, null);
    }

    /**
     * Creates a new person, without person number.
     *
     * @param firstName The newly created person's first name.
     * @param lastName The newly created person's last name.
     * @param email The newly created person's email address.
     * @param phone The newly created person's phone number.
     */
    public Person(String firstName, String lastName, String email, String phone) {
        this(firstName, lastName, email, phone, null);
    }

    /**
     * Creates a new person.
     *
     * @param firstName The newly created person's first name.
     * @param lastName The newly created person's last name.
     * @param email The newly created person's email address.
     * @param phone The newly created person's phone number.
     * @param pnr The newly created person's person number.
     */
    public Person(String firstName, String lastName, String email, String phone, String pnr) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.pnr = pnr;
    }

}
