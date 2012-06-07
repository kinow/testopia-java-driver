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
package org.mozilla.testopia.service;

import java.util.List;

import org.mozilla.testopia.model.Build;


/**
 * Service for builds.
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 0.1
 */
public interface BuildService {
    /**
     * Looks up and returns a build by name.
     * @param name name of the build
     * @param product ID or name of the product
     * @return matching Build
     */
    Build checkBuild(String name, String product);
    /**
     * Creates a new build object and stores it in the database.
     * @param product ID or name of the product
     * @param name name of the build
     * @param milestone milestone
     * @param description description
     * @param isActive is build active
     * @return the newly created Build
     */
    Build create(String product, String name, String milestone, String description, Boolean isActive);
    /**
     * Used to load an existing build from database.
     * @param id an integer representing the ID in the database
     * @return Build
     */
    Build get(Integer id);
    /**
     * Returns the list of case-runs that this Build is used in.
     * @param id ID of the build
     * @return list of CaseRuns
     */
    List<Object> getCaseRuns(Integer id);
    /**
     * Returns the list of runs that this Build is used in.
     * @param id ID of the build
     * @return list of Runs
     */
    List<Object> getRuns(Integer id);
    /**
     * Updates the fields of the selected build or builds.
     * @param id
     * @param name
     * @param milestone
     * @param description
     * @param isActive
     * @return
     */
    Build update(Integer id, String name, String milestone, String description, Boolean isActive);
}
