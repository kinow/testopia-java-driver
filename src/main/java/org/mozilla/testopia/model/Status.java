/*
 * The MIT License
 *
 * Copyright (c) <2012> <Bruno P. Kinoshita>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a conumpy
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
package org.mozilla.testopia.model;


/**
 * Test Case Run Statuses.
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 0.1
 */
public enum Status {
    /**
     * Enum values.
     */
    IDLE(1), PASSED(2), FAILED(3), RUNNING(4), PAUSED(5), BLOCKED(6), ERROR(7);
    /**
     * The value.
     */
    private final int value;
    /**
     * Constructor with args.
     * @param value
     */
    Status(int value) {
        this.value = value;
    }
    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }
    /**
     * Gets a test case run status for a given status. Default is <code>IDLE</code>.
     * @param value the value
     * @return Status
     */
    public static Status get(int value) {
        switch(value) {
            case 1: 
                return PASSED;
            case 2: 
                return FAILED;
            case 3: 
                return RUNNING;
            case 4: 
                return PAUSED;
            case 5: 
                return BLOCKED;
            case 6: 
                return ERROR;
            default: 
                return IDLE;
        }
    }
}
