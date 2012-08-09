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
package org.mozilla.testopia.service.xmlrpc;

import java.util.HashMap;
import java.util.Map;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.mozilla.testopia.model.TestCase;
import org.mozilla.testopia.model.TestCaseRun;
import org.mozilla.testopia.service.TestCaseRunService;


/**
 * XML-RPC implementation of Test Case Run.
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 0.1
 */
public class XmlRpcTestCaseRunService implements TestCaseRunService {
    // Attributes 
    // ------------------------------------------------------------------------
    private final XmlRpcClient client;
    // Methods
    // ------------------------------------------------------------------------
    /**
     * Create a XmlRpcTestRunService.
     * @param xmlRpcClient XML-RPC client
     */
    public XmlRpcTestCaseRunService(XmlRpcClient xmlRpcClient) {
        client = xmlRpcClient;
    }
    /* (non-Javadoc)
     * @see org.mozilla.testopia.service.TestRunService#update(org.mozilla.testopia.model.TestCase, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    public TestCaseRun update(TestCase tc, Integer runId, Integer buildId, Integer envId) {
        TestCaseRun testCase = null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("run_id", runId);
        map.put("case_id", tc.getId());
        map.put("build", buildId);
        map.put("build_id", buildId);
        map.put("environment", envId);
        map.put("env_id", envId);
        
        map.put("status", tc.getStatusId());
        
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> r = (Map<String, Object>)this.client.execute("TestCaseRun.update", new Object[]{map});
            testCase = new TestCaseRun();
            testCase.setTestCaseId((Integer)r.get("case_id"));
            testCase.setPriorityId((Integer)r.get("priority_id"));
            testCase.setId((Integer)r.get("case_run_id"));
            testCase.setAssignee((Integer)r.get("assignee"));
            testCase.setTestedBy((Integer)r.get("testedby"));
            testCase.setRunId((Integer)r.get("run_id"));
            testCase.setBuildId((Integer)r.get("build_id"));
            testCase.setEnvironmentId((Integer)r.get("environment_id"));
            testCase.setNotes(r.get("notes") != null ? r.get("notes").toString() : "");
            
//            Object o = r[i];
//            if(o instanceof Map<?, ?>) {
//                final Map<String, Object> map = (Map<String, Object>)o;
//                final TestCase testCase = new TestCase();
//                testCase.setId(Integer.parseInt(""+map.get("case_id")));
//                testCase.setSummary(""+map.get("summary"));
//                testCase.setRequirement(""+map.get("requirement"));
//                testCase.setPriorityId(Integer.parseInt(""+map.get("priority_id")));
//                testCase.setAlias(""+map.get("alias"));
//                testCase.setAutomated((""+map.get("isautomated")).equals("1"));
//                testCase.setCategoryId(Integer.parseInt(""+map.get("category_id")));
//                testCase.setScript(""+map.get("script"));
//                testCase.setArguments(""+map.get("arguments"));
//                testCase.setDefaultTesterId(Integer.parseInt(""+map.get("default_tester_id")));
//                testCase.setAuthorId(Integer.parseInt(""+map.get("author_id")));
//                testCases[i] = testCase;
//            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return testCase;
    }
}
