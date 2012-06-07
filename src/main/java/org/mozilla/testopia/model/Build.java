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
 * Build.
 * 
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 0.1
 */
public class Build implements Serializable {

    private static final long serialVersionUID = 6461792679959786041L;

    private Integer id;
    private String name;
    private String milestone;
    private String description;
    private Boolean isActive;
    
    /**
     * Create a Build.
     */
    public Build() {
        super();
    }

    /**
     * Create a Build.
     * 
     * @param id
     * @param name
     * @param milestone
     * @param description
     * @param isActive
     */
    public Build(Integer id, String name, String milestone, String description,
                 Boolean isActive) {
        super();
        this.id = id;
        this.name = name;
        this.milestone = milestone;
        this.description = description;
        this.isActive = isActive;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the milestone
     */
    public String getMilestone() {
        return milestone;
    }

    /**
     * @param milestone the milestone to set
     */
    public void setMilestone(String milestone) {
        this.milestone = milestone;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the isActive
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * @param isActive the isActive to set
     */
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj instanceof Build) {
            Build that = (Build)obj;
            return this.id == that.id 
                    && this.name != null ? this.name.equals(that.name) : this.name == that.name
                    && this.milestone != null ? this.milestone.equals(that.milestone) : this.milestone == that.milestone
                    && this.description != null ? this.description.equals(that.description) : this.description == that.description
                    && this.isActive == that.isActive;
                                      
        }
        return false;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = "Build".hashCode();
        hash <<= 2;
        hash ^= this.id != null ? this.id : 0;
        hash <<= 2;
        hash ^= this.name != null ? this.name.hashCode() : "".hashCode();
        hash <<= 2;
        hash ^= this.milestone != null ? this.milestone.hashCode() : "".hashCode();
        hash <<= 2;
        hash ^= (this.isActive != null && this.isActive) ? 0 : 1;
        hash <<= 2;
        hash ^= this.description != null ? this.description.hashCode() : "".hashCode();
        return hash;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Build<"+this.id+","+this.name+","+this.milestone+","+this.description+">";
    }

}
