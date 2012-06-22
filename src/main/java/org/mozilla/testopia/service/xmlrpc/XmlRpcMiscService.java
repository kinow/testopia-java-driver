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

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.mozilla.testopia.service.MiscService;


/**
 * XML-RPC implementation of Misc Service.
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @see MiscService
 * @since 0.1
 */
public class XmlRpcMiscService implements MiscService {
    // Attributes 
    // ------------------------------------------------------------------------
    private final XmlRpcClient client;
    // Methods
    // ------------------------------------------------------------------------
    /**
     * Create a XmlRpcMiscService.
     * @param xmlRpcClient XML-RPC client
     */
    public XmlRpcMiscService(XmlRpcClient xmlRpcClient) {
        client = xmlRpcClient;
    }
    /* (non-Javadoc)
     * @see org.mozilla.testopia.service.MiscService#login(java.lang.String, java.lang.String)
     */
    public void login(String username, String password) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("login", username);
        map.put("password", password);
        try {
            client.execute("User.login", new Object[]{map});
        } catch (XmlRpcException e) {
            throw new RuntimeException("Failed to login!", e);
        }
    }
}
