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

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.mozilla.testopia.service.xmlrpc.XmlRpcMiscService;
import org.mozilla.testopia.service.xmlrpc.XmlRpcTestCaseRunService;
import org.mozilla.testopia.service.xmlrpc.XmlRpcTestRunService;
import org.mozilla.testopia.transport.TestopiaXmlRpcClient;


/**
 * 
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 0.1
 */
public class T {

    public static void main4(String[] args) throws Exception {
        URL url = new URL("http://localhost/bugzilla-4.2.1/xmlrpc.cgi");
        TestopiaXmlRpcClient client = new TestopiaXmlRpcClient(url);
        XmlRpcMiscService misc = new XmlRpcMiscService(client);
        misc.login("testuser@example.com", "testuser");
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("id", 1);
        
        Object o = null;
        try {
            o = client.execute("TestCaseRun.update", new Object[]{map});
            /*
             *  (java.util.HashMap<K,V>) {milestone=---, isactive=1, product_id=3, description=Sample build, name=My build, build_id=1}
             */
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("Value: " + o);
    }
    
    public static void main2(String[] args) throws Exception {
        URL url = new URL("http://localhost/bugzilla-4.2.1/xmlrpc.cgi");
        TestopiaXmlRpcClient client = new TestopiaXmlRpcClient(url);
        XmlRpcMiscService misc = new XmlRpcMiscService(client);
        misc.login("testuser@example.com", "testuser");
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("id", 1);
        
        Object o = null;
        try {
            o = client.execute("TestPlan.get_test_cases", new Object[]{map});
            /*
             *  (java.util.HashMap<K,V>) {milestone=---, isactive=1, product_id=3, description=Sample build, name=My build, build_id=1}
             */
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("Value: " + o);
    }
    
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost/bugzilla-4.2.1/xmlrpc.cgi");
        TestopiaXmlRpcClient client = new TestopiaXmlRpcClient(url);
        XmlRpcMiscService misc = new XmlRpcMiscService(client);
        misc.login("testuser@example.com", "testuser");
        XmlRpcTestRunService svc = new XmlRpcTestRunService(client);
        XmlRpcTestCaseRunService svc2 = new XmlRpcTestCaseRunService(client);

        try {
            TestRun run = svc.getTestRun(2);
            TestCase[] tcs = svc.getTestCases(2);
            for(TestCase tc : tcs) {
                System.out.println("Updating TC " + tc.getId() + " with status FAILED");
                tc.setStatusId(Status.BLOCKED.getValue());
                TestCaseRun updated = svc2.update(
                          tc, 
                          run.getId(), 
                          Integer.parseInt(run.getBuild()), 
                          Integer.parseInt(run.getEnvironment()));
                System.out.println(updated.getPriorityId());
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
    
}
