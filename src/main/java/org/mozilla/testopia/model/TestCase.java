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
import java.util.Date;


/**
 * Test Case.
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 0.1
 */
public class TestCase implements Serializable {

    private static final long serialVersionUID = 3896038134433964565L;
    
    private Integer id;
    private Integer statusId;
    private Integer categoryId;
    private Integer priorityId;
    private Integer authorId;
    private Integer defaultTesterId;
    private Date creationDate;
    private Date estimatedTime;
    private Boolean automated;
    private String sortKey;
    private String script;
    private String arguments;
    private String summary;
    private String requirement;
    private String alias;
    
    /**
     * Default constructor. 
     */
    public TestCase() {
        super();
    }
    /**
     * Constructor with parameters.
     * @param id
     * @param statusId
     * @param categoryId
     * @param priorityId
     * @param authorId
     * @param defaultTesterId
     * @param creationDate
     * @param estimatedTime
     * @param automated
     * @param sortKey
     * @param script
     * @param arguments
     * @param summary
     * @param requirement
     * @param alias
     */
    public TestCase(Integer id, Integer statusId, Integer categoryId,
                    Integer priorityId, Integer authorId,
                    Integer defaultTesterId, Date creationDate,
                    Date estimatedTime, Boolean automated, String sortKey,
                    String script, String arguments, String summary,
                    String requirement, String alias) {
        super();
        this.id = id;
        this.statusId = statusId;
        this.categoryId = categoryId;
        this.priorityId = priorityId;
        this.authorId = authorId;
        this.defaultTesterId = defaultTesterId;
        this.creationDate = creationDate;
        this.estimatedTime = estimatedTime;
        this.automated = automated;
        this.sortKey = sortKey;
        this.script = script;
        this.arguments = arguments;
        this.summary = summary;
        this.requirement = requirement;
        this.alias = alias;
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
     * @return the statusId
     */
    public Integer getStatusId() {
        return statusId;
    }
    
    /**
     * @param statusId the statusId to set
     */
    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }
    
    /**
     * @return the categoryId
     */
    public Integer getCategoryId() {
        return categoryId;
    }
    
    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    
    /**
     * @return the priorityId
     */
    public Integer getPriorityId() {
        return priorityId;
    }
    
    /**
     * @param priorityId the priorityId to set
     */
    public void setPriorityId(Integer priorityId) {
        this.priorityId = priorityId;
    }
    
    /**
     * @return the authorId
     */
    public Integer getAuthorId() {
        return authorId;
    }
    
    /**
     * @param authorId the authorId to set
     */
    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }
    
    /**
     * @return the defaultTesterId
     */
    public Integer getDefaultTesterId() {
        return defaultTesterId;
    }
    
    /**
     * @param defaultTesterId the defaultTesterId to set
     */
    public void setDefaultTesterId(Integer defaultTesterId) {
        this.defaultTesterId = defaultTesterId;
    }
    
    /**
     * @return the creationDate
     */
    public Date getCreationDate() {
        return creationDate;
    }
    
    /**
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    
    /**
     * @return the estimatedTime
     */
    public Date getEstimatedTime() {
        return estimatedTime;
    }
    
    /**
     * @param estimatedTime the estimatedTime to set
     */
    public void setEstimatedTime(Date estimatedTime) {
        this.estimatedTime = estimatedTime;
    }
    
    /**
     * @return the automated
     */
    public Boolean getAutomated() {
        return automated;
    }
    
    /**
     * @param automated the automated to set
     */
    public void setAutomated(Boolean automated) {
        this.automated = automated;
    }
    
    /**
     * @return the sortKey
     */
    public String getSortKey() {
        return sortKey;
    }
    
    /**
     * @param sortKey the sortKey to set
     */
    public void setSortKey(String sortKey) {
        this.sortKey = sortKey;
    }
    
    /**
     * @return the script
     */
    public String getScript() {
        return script;
    }
    
    /**
     * @param script the script to set
     */
    public void setScript(String script) {
        this.script = script;
    }
    
    /**
     * @return the arguments
     */
    public String getArguments() {
        return arguments;
    }
    
    /**
     * @param arguments the arguments to set
     */
    public void setArguments(String arguments) {
        this.arguments = arguments;
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
     * @return the requirement
     */
    public String getRequirement() {
        return requirement;
    }
    
    /**
     * @param requirement the requirement to set
     */
    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }
    
    /**
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }
    
    /**
     * @param alias the alias to set
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

}
