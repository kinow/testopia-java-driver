/*
 * The MIT License Copyright (c) <2012> <Bruno P. Kinoshita> Permission is
 * hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the "Software"), to deal in the
 * Software without restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions: The above copyright notice and this
 * permission notice shall be included in all copies or substantial portions of
 * the Software. THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO
 * EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
 * OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */
package org.mozilla.testopia.transport;

import java.io.IOException;
import java.net.HttpCookie;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.xmlrpc.XmlRpcRequest;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfig;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcClientException;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;
import org.apache.xmlrpc.client.XmlRpcSunHttpTransport;
import org.apache.xmlrpc.client.XmlRpcTransport;

/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 0.1
 */
public class TestopiaXmlRpcClient
    extends XmlRpcClient {

    private URL url;
    private Set<HttpCookie> cookies;

    public TestopiaXmlRpcClient(String url)
        throws MalformedURLException {
        this(new URL(url));
    }

    public TestopiaXmlRpcClient(URL url) {
        super();
        this.url = url;
        this.cookies = new HashSet<HttpCookie>();

        final XmlRpcClientConfig config = new XmlRpcClientConfigImpl();
        ((XmlRpcClientConfigImpl) config).setServerURL(url);
        this.setConfig(config);

        // TODO: break into inner classes
        this.setTransportFactory(new XmlRpcCommonsTransportFactory(this) {

            public XmlRpcTransport getTransport() {
                return new XmlRpcSunHttpTransport(TestopiaXmlRpcClient.this) {
                    /*
                     * Connection
                     */
                    private URLConnection connection;
                    /*
                     * (non-Javadoc)
                     * @see org.apache.xmlrpc.client.XmlRpcSunHttpTransport#
                     * newURLConnection(java.net.URL)
                     */
                    @Override
                    protected URLConnection newURLConnection(URL pURL)
                        throws IOException {
                        this.connection = super.newURLConnection(pURL);
                        return this.connection;
                    }
                    /**
                     * @{inheritDoc}
                     * 
                     * Before closing the connection, it loads the cookies in 
                     * the connection, so that they can be used later by other 
                     * methods.
                     */
                    @Override
                    protected void close() throws XmlRpcClientException {
                        final Map<String, List<String>> headerFields = connection.getHeaderFields();
                        final List<String> cookieHeaders = headerFields.get("Set-Cookie");
                        if(cookieHeaders != null) {
                            for (final String cookieHeader : cookieHeaders) {
                                final String cleanCoockieHeader = cookieHeader.replace("; HttpOnly", "");
                                final List<HttpCookie> cookieList = HttpCookie.parse("Set-Cookie: " + cleanCoockieHeader);
                                if (cookieList != null) {
                                    for (final HttpCookie cookie : cookieList) {
                                        cookies.add(cookie);
                                    }
                                }
                            }
                        }
                        // Now we can close it
                        super.close();
                    }
                    /**
                     * @{inheritDoc}
                     * 
                     * After the HTTP headers are initialized, it applies the 
                     * existing headers from this client.
                     */
                    @Override
                    protected void initHttpHeaders(XmlRpcRequest pRequest)
                        throws XmlRpcClientException {
                        super.initHttpHeaders(pRequest);
                        final StringBuilder buffer = new StringBuilder();
                        for (final HttpCookie cookie : cookies) {
                            if (buffer.length() > 0) {
                                buffer.append(";");
                            }
                            buffer.append(cookie.toString());
                        }
                        if (buffer.length() > 0) {
                            connection.setRequestProperty("Cookie", buffer.toString());
                        }
                    }
                };
            }
        });

        // TODO: log
    }
    
    
    /**
     * @return the url
     */
    public URL getUrl() {
        return url;
    }
}
