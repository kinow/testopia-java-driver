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
import org.mozilla.testopia.model.TestPlan;
import org.mozilla.testopia.service.TestPlanService;


/**
 * XML-RPC implementation of TestPlanService.
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @see TestPlanService
 * @since 0.1
 */
public class XmlRpcTestPlanService implements TestPlanService {
    // Attributes 
    // ------------------------------------------------------------------------
    private final XmlRpcClient client;
    /**
     * Create a XmlRpcTestPlanService.
     * @param xmlRpcClient XML-RPC client
     */
    public XmlRpcTestPlanService(XmlRpcClient xmlRpcClient) {
        client = xmlRpcClient;
    }
    // Methods
    // ------------------------------------------------------------------------
    /* (non-Javadoc)
     * @see org.mozilla.testopia.service.TestPlanService#get(java.lang.Integer)
     */
    @SuppressWarnings("unchecked")
    public TestPlan get(Integer id) {
        TestPlan plan = null;
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("id", id);
        try {
            Map<String, Object> r = (Map<String, Object>)this.client.execute("TestPlan.get", new Object[]{map});
            plan = new TestPlan(Integer.parseInt(""+r.get("plan_id")), Integer.parseInt(""+r.get("product_id")), ""+r.get("name"), ""+r.get("type"), ""+r.get("default_product_version"), Boolean.parseBoolean(""+r.get("isactive")));
            /*
             *  (java.util.HashMap<K,V>) {creation_date=2012-05-29 18:59:18, test_run_count=1, isactive=1, product_id=3, name=my plan, author_id=1, test_case_count=1, default_product_version=unspecified, type_id=5, plan_id=1}
             */
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return plan;
    }

}
