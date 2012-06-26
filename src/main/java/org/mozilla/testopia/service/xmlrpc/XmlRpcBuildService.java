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
import java.util.List;
import java.util.Map;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.mozilla.testopia.model.Build;
import org.mozilla.testopia.service.BuildService;


/**
 * XML-RPC implementation of BuildService.
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @see BuildService
 * @since 0.1
 */
public class XmlRpcBuildService implements BuildService {
    // Attributes 
    // ------------------------------------------------------------------------
    private final XmlRpcClient client;
    // Methods
    // ------------------------------------------------------------------------
    /**
     * Create a XmlRpcBuildService.
     * @param xmlRpcClient XML-RPC client
     */
    public XmlRpcBuildService(XmlRpcClient xmlRpcClient) {
        client = xmlRpcClient;
    }
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public Build checkBuild(String name, String product) {
        Build build = null;
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", name);
        map.put("product", product);
        try {
            Map<String, Object> r = (Map<String, Object>)this.client.execute("Build.check_build", new Object[]{map});
            build = new Build(Integer.parseInt(r.get("build_id").toString()), ""+r.get("name"), ""+r.get("milestone"), ""+r.get("description"), Boolean.parseBoolean(""+r.get("isactive")));
            /*
             *  (java.util.HashMap<K,V>) {milestone=---, isactive=1, product_id=3, description=Sample build, name=My build, build_id=1}
             */
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return build;
    }
    /**
     * {@inheritDoc}
     */
    public Build create(String product, String name, String milestone,
                        String description, Boolean isActive) {
        // TODO Auto-generated method stub
        return null;
    }
    /**
     * {@inheritDoc}
     */
    public Build getBuild(Integer id) {
        Build build = null;
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", Integer.toString(id));
        System.out.println(map);
        try {
            this.client.execute("Build.get", new Object[]{map});
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return build;
    }
    /**
     * {@inheritDoc}
     */
    public List<Object> getCaseRuns(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }
    /**
     * {@inheritDoc}
     */
    public List<Object> getRuns(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }
    /**
     * {@inheritDoc}
     */
    public Build update(Integer id, String name, String milestone,
                        String description, Boolean isActive) {
        // TODO Auto-generated method stub
        return null;
    }
}
