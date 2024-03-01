/*
 * Copyright (c) 2024, Leif Lindbäck
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
package se.leiflindback.oodbook.records;

public class BookDTOWithoutRecords {

    private final String title;
    private final String author;
    private final String publisher;
    private final String year;
    private final String ISBN;

    public BookDTOWithoutRecords(String title, String author,
                                 String publisher, String year,
                                 String ISBN) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.ISBN = ISBN;
    }

    String title() {
        return this.title;
    }

    String author() {
        return this.author;
    }

    String publisher() {
        return this.publisher;
    }

    String year() {
        return this.year;
    }

    String ISBN() {
        return this.ISBN;
    }

    // Implementation of equals, which specify that two objects are equal if they are of the same 
    // type, and all their attributes have the same value.. 
    @Override
    public boolean equals(Object other) {
        return false;
    }

    // Implementation of hashCode, which generates the same hash code for equal objects.
    @Override
    public int hashCode() {
        return 0;
    }

    public String toString() {
        return "BookDTO[title=" + title + ", author=" + author + ", publisher=" + publisher
                + ", year=" + year + ", ISBN=" + ISBN + "]";
    }
}
