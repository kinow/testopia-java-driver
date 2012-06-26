/*
 * The MIT License
 *
 * Copyright (c) <2012> <Bruno P. Kinoshita>
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
package org.mozilla.testopia.model;

import java.io.Serializable;


/**
 * Test Case Run.
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 0.1
 */
public class TestCaseRun implements Serializable {

    private static final long serialVersionUID = -1042170282013647286L;
    
    private Integer id;
    private Integer buildId;
    private Integer environmentId;
    private Integer runId;
    private Integer testCaseId;
    /**
     * Default constructor.
     */
    public TestCaseRun() {
        super();
    }
    /**
     * Construct a TestCaseRun.
     * @param id
     * @param buildId
     * @param environmentId
     * @param runId
     * @param testCaseId
     */
    public TestCaseRun(Integer id, Integer buildId, Integer environmentId,
                       Integer runId, Integer testCaseId) {
        super();
        this.id = id;
        this.buildId = buildId;
        this.environmentId = environmentId;
        this.runId = runId;
        this.testCaseId = testCaseId;
    }
    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     * @return the buildId
     */
    public Integer getBuildId() {
        return buildId;
    }
    
    /**
     * @param buildId the buildId to set
     */
    public void setBuildId(Integer buildId) {
        this.buildId = buildId;
    }
    
    /**
     * @return the environmentId
     */
    public Integer getEnvironmentId() {
        return environmentId;
    }
    
    /**
     * @param environmentId the environmentId to set
     */
    public void setEnvironmentId(Integer environmentId) {
        this.environmentId = environmentId;
    }
    
    /**
     * @return the runId
     */
    public Integer getRunId() {
        return runId;
    }
    
    /**
     * @param runId the runId to set
     */
    public void setRunId(Integer runId) {
        this.runId = runId;
    }
    
    /**
     * @return the testCaseId
     */
    public Integer getTestCaseId() {
        return testCaseId;
    }
    
    /**
     * @param testCaseId the testCaseId to set
     */
    public void setTestCaseId(Integer testCaseId) {
        this.testCaseId = testCaseId;
    }
}
