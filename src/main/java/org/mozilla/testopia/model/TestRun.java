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
 * Test Run.
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 0.1
 */
public class TestRun implements Serializable {

    private static final long serialVersionUID = 4109926057672176463L;
    
    private Integer id;
    private Integer planId;
    private String environment;
    private String build;
    private String manager;
    private String summary;
    private String productVersion;
    private Integer planTextVersion;
    private Integer targetCompletion;
    private Integer targetPass;
    private String notes;
    private Integer status;
    private Integer cases;

    /**
     * Default constructor. 
     */
    public TestRun() {
        super();
    }

    /**
     * @param id
     * @param planId
     * @param environment
     * @param build
     * @param manager
     * @param summary
     * @param productVersion
     * @param planTextVersion
     * @param targetCompletion
     * @param targetPass
     * @param notes
     * @param status
     * @param cases
     */
    public TestRun(Integer id, Integer planId, String environment,
                   String build, String manager, String summary,
                   String productVersion, Integer planTextVersion,
                   Integer targetCompletion, Integer targetPass, String notes,
                   Integer status, Integer cases) {
        super();
        this.id = id;
        this.planId = planId;
        this.environment = environment;
        this.build = build;
        this.manager = manager;
        this.summary = summary;
        this.productVersion = productVersion;
        this.planTextVersion = planTextVersion;
        this.targetCompletion = targetCompletion;
        this.targetPass = targetPass;
        this.notes = notes;
        this.status = status;
        this.cases = cases;
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
     * @return the planId
     */
    public Integer getPlanId() {
        return planId;
    }

    
    /**
     * @param planId the planId to set
     */
    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    
    /**
     * @return the environment
     */
    public String getEnvironment() {
        return environment;
    }

    
    /**
     * @param environment the environment to set
     */
    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    
    /**
     * @return the build
     */
    public String getBuild() {
        return build;
    }

    
    /**
     * @param build the build to set
     */
    public void setBuild(String build) {
        this.build = build;
    }

    
    /**
     * @return the manager
     */
    public String getManager() {
        return manager;
    }

    
    /**
     * @param manager the manager to set
     */
    public void setManager(String manager) {
        this.manager = manager;
    }

    
    /**
     * @return the summary
     */
    public String getSummary() {
        return summary;
    }

    
    /**
     * @param summary the summary to set
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    
    /**
     * @return the productVersion
     */
    public String getProductVersion() {
        return productVersion;
    }

    
    /**
     * @param productVersion the productVersion to set
     */
    public void setProductVersion(String productVersion) {
        this.productVersion = productVersion;
    }

    
    /**
     * @return the planTextVersion
     */
    public Integer getPlanTextVersion() {
        return planTextVersion;
    }

    
    /**
     * @param planTextVersion the planTextVersion to set
     */
    public void setPlanTextVersion(Integer planTextVersion) {
        this.planTextVersion = planTextVersion;
    }

    
    /**
     * @return the targetCompletion
     */
    public Integer getTargetCompletion() {
        return targetCompletion;
    }

    
    /**
     * @param targetCompletion the targetCompletion to set
     */
    public void setTargetCompletion(Integer targetCompletion) {
        this.targetCompletion = targetCompletion;
    }

    
    /**
     * @return the targetPass
     */
    public Integer getTargetPass() {
        return targetPass;
    }

    
    /**
     * @param targetPass the targetPass to set
     */
    public void setTargetPass(Integer targetPass) {
        this.targetPass = targetPass;
    }

    
    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    
    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    
    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    
    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    
    /**
     * @return the cases
     */
    public Integer getCases() {
        return cases;
    }

    
    /**
     * @param cases the cases to set
     */
    public void setCases(Integer cases) {
        this.cases = cases;
    }
    
}
