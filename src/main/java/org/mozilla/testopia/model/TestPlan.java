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
 * Test Plan.
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 0.1
 */
public class TestPlan implements Serializable {

    private static final long serialVersionUID = 8866024047012370025L;
    
    private Integer id;
    private Integer productId;
    private String name;
    private String type;
    private String defaultProductVersion;
    private Boolean active;
    
    /**
     * Default constructor.
     */
    public TestPlan() {
        super();
    }

    /**
     * Constructor with parameters.
     * @param id
     * @param productId
     * @param name
     * @param type
     * @param defaultProductVersion
     * @param active
     */
    public TestPlan(Integer id, Integer productId, String name, String type,
                    String defaultProductVersion, Boolean active) {
        super();
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.type = type;
        this.defaultProductVersion = defaultProductVersion;
        this.active = active;
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
     * @return the productId
     */
    public Integer getProductId() {
        return productId;
    }
    
    /**
     * @param productId the productId to set
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
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
     * @return the type
     */
    public String getType() {
        return type;
    }
    
    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * @return the defaultProductVersion
     */
    public String getDefaultProductVersion() {
        return defaultProductVersion;
    }
    
    /**
     * @param defaultProductVersion the defaultProductVersion to set
     */
    public void setDefaultProductVersion(String defaultProductVersion) {
        this.defaultProductVersion = defaultProductVersion;
    }
    
    /**
     * @return the active
     */
    public Boolean getActive() {
        return active;
    }
    
    /**
     * @param active the active to set
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

}
