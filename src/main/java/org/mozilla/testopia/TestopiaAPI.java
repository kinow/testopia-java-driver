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
package org.mozilla.testopia;

import java.net.URL;
import java.util.List;

import org.mozilla.testopia.model.Build;
import org.mozilla.testopia.model.TestCase;
import org.mozilla.testopia.model.TestCaseRun;
import org.mozilla.testopia.model.TestPlan;
import org.mozilla.testopia.model.TestRun;
import org.mozilla.testopia.service.BuildService;
import org.mozilla.testopia.service.MiscService;
import org.mozilla.testopia.service.TestCaseRunService;
import org.mozilla.testopia.service.TestCaseService;
import org.mozilla.testopia.service.TestPlanService;
import org.mozilla.testopia.service.TestRunService;
import org.mozilla.testopia.service.xmlrpc.XmlRpcBuildService;
import org.mozilla.testopia.service.xmlrpc.XmlRpcMiscService;
import org.mozilla.testopia.service.xmlrpc.XmlRpcTestCaseRunService;
import org.mozilla.testopia.service.xmlrpc.XmlRpcTestCaseService;
import org.mozilla.testopia.service.xmlrpc.XmlRpcTestPlanService;
import org.mozilla.testopia.service.xmlrpc.XmlRpcTestRunService;
import org.mozilla.testopia.transport.TestopiaXmlRpcClient;


/**
 * Facade for Testopia exposed web services.
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 0.1
 */
public class TestopiaAPI implements MiscService, 
                                    BuildService, 
                                    TestCaseService, 
                                    TestPlanService, 
                                    TestCaseRunService, 
                                    TestRunService {

    private boolean loggedIn = Boolean.FALSE;
    private final URL url;
    private final TestopiaXmlRpcClient client;
    /*
     * -------------------------------------------------------------------------
     * Le services
     * -------------------------------------------------------------------------
     */
    private MiscService miscService;
    private BuildService buildService;
    private TestCaseService testCaseService;
    private TestPlanService testPlanService;
    private TestCaseRunService testCaseRunService;
    private TestRunService testRunService;
    
    public TestopiaAPI(URL url) {
        this.url = url;
        client = new TestopiaXmlRpcClient(url);
        miscService = new XmlRpcMiscService(client);
        buildService = new XmlRpcBuildService(client);
        testCaseService = new XmlRpcTestCaseService(client);
        testPlanService = new XmlRpcTestPlanService(client);
        testCaseRunService = new XmlRpcTestCaseRunService(client);
        testRunService = new XmlRpcTestRunService(client);
    }
    
    /**
     * @return the url
     */
    public URL getUrl() {
        return url;
    }
    
    public boolean isLoggedIn() {
        return this.loggedIn;
    }
    /*
     * -------------------------------------------------------------------------
     * Miscellaneous methods
     * -------------------------------------------------------------------------
     */
    /**
     * {@inheritDoc}
     */
    public void login(String username, String password) {
        miscService.login(username, password);
        this.loggedIn = Boolean.TRUE;
    }
    /*
     * -------------------------------------------------------------------------
     * Build methods
     * -------------------------------------------------------------------------
     */
    /**
     * {@inheritDoc}
     */
    public Build checkBuild(String name, String product) {
        return buildService.checkBuild(name, product);
    }

    /**
     * {@inheritDoc}
     */
    public Build create(String product, String name, String milestone,
                        String description, Boolean isActive) {
        return buildService.create(product, name, milestone, description, isActive);
    }

    /**
     * {@inheritDoc}
     */
    public Build getBuild(Integer id) {
        return buildService.getBuild(id);
    }

    /**
     * {@inheritDoc}
     */
    public List<Object> getCaseRuns(Integer id) {
        return buildService.getCaseRuns(id);
    }

    /**
     * {@inheritDoc}
     */
    public List<Object> getRuns(Integer id) {
        return buildService.getRuns(id);
    }

    /**
     * {@inheritDoc}
     */
    public Build update(Integer id, String name, String milestone,
                        String description, Boolean isActive) {
        return buildService.update(id, name, milestone, description, isActive);
    }
    
    /*
     * -------------------------------------------------------------------------
     * Test Case methods
     * -------------------------------------------------------------------------
     */
    /* (non-Javadoc)
     * @see org.mozilla.testopia.service.TestCaseService#lookupStatusNameById(java.lang.Integer)
     */
    public String lookupStatusNameById(Integer statusId) {
        return this.testCaseService.lookupStatusNameById(statusId);
    }
    /*
     * -------------------------------------------------------------------------
     * Test Plan methods
     * -------------------------------------------------------------------------
     */
    /*
     * (non-Javadoc)
     * @see org.mozilla.testopia.service.TestPlanService#getTestPlan(java.lang.Integer)
     */
    public TestPlan getTestPlan(Integer id) {
        return this.testPlanService.getTestPlan(id);
    }
    /*
     * -------------------------------------------------------------------------
     * Test Run methods
     * -------------------------------------------------------------------------
     */
    /*
     * (non-Javadoc)
     * @see org.mozilla.testopia.service.TestRunService#getTestRun(java.lang.Integer)
     */
    public TestRun getTestRun(Integer id) {
        return this.testRunService.getTestRun(id);
    }
    /*
     * (non-Javadoc)
     * @see org.mozilla.testopia.service.TestRunService#getTestCases(java.lang.Integer)
     */
    public TestCase[] getTestCases(Integer id) {
        return this.testRunService.getTestCases(id);
    }

    /* (non-Javadoc)
     * @see org.mozilla.testopia.service.TestCaseRunService#update(org.mozilla.testopia.model.TestCase, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    public TestCaseRun update(TestCase testCase, Integer runId, Integer buildId, Integer envId) {
        return this.testCaseRunService.update(testCase, runId, buildId, envId);
    }
}
