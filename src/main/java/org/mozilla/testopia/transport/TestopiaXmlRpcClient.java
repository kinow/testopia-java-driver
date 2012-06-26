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
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConversionException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.apache.xmlrpc.XmlRpcRequest;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfig;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcClientException;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;
import org.apache.xmlrpc.client.XmlRpcSunHttpTransport;
import org.apache.xmlrpc.client.XmlRpcTransport;

/**
 * XML-RPC client for Testopia. This client stores cookies, as Testopia expects 
 * that you first log in in order to be able to call other methods.
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 0.1
 */
public class TestopiaXmlRpcClient extends XmlRpcClient {
    /*
     * Constants for properties
     */
    private static final String XMLRPC_BASIC_ENCODING = "xmlrpc.basicEncoding";
    private static final String XMLRPC_BASIC_PASSWORD = "xmlrpc.basicPassword";
    private static final String XMLRPC_BASIC_USERNAME = "xmlrpc.basicUsername";
    private static final String XMLRPC_CONNECTION_TIMEOUT = "xmlrpc.connectionTimeout";
    private static final String XMLRPC_CONTENT_LENGTH_OPTIONAL = "xmlrpc.contentLengthOptional";
    private static final String XMLRPC_ENABLED_FOR_EXCEPTIONS = "xmlrpc.enabledForExceptions";
    private static final String XMLRPC_ENCODING = "xmlrpc.encoding";
    private static final String XMLRPC_GZIP_COMPRESSION = "xmlrpc.gzipCompression";
    private static final String XMLRPC_GZIP_REQUESTING = "xmlrpc.gzipRequesting";
    private static final String XMLRPC_REPLY_TIMEOUT = "xmlrpc.replyTimeout";
    private static final String XMLRPC_USER_AGENT = "xmlrpc.userAgent";
    /*
     * Logger
     */
    private static final Logger LOGGER = Logger.getLogger(TestopiaXmlRpcClient.class.getName());
    
    private URL url;
    private Set<HttpCookie> cookies;

    public TestopiaXmlRpcClient(String url) throws MalformedURLException {
        this(new URL(url));
    }

    public TestopiaXmlRpcClient(URL url) {
        super();
        
        this.url = url;
        this.cookies = new HashSet<HttpCookie>();

        final CompositeConfiguration configuration = this.createCompositeConfiguration();
        final XmlRpcClientConfig config = this.createXmlRpcClientConfiguration(configuration);
        ((XmlRpcClientConfigImpl) config).setServerURL(url);
        this.setConfig(config);

        // TODO: break into other classes
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
    }
    
    /**
     * Creates a composite configuration from testopiajavadriver.properties and 
     * from the system configuration. Properties from the properties file 
     * override the system properties.
     * @return CompositeConfiguration
     */
    private CompositeConfiguration createCompositeConfiguration() {
        CompositeConfiguration configuration = new CompositeConfiguration();
        configuration.addConfiguration(new SystemConfiguration());
        try {
            configuration.addConfiguration(new PropertiesConfiguration("testopiajavadriver.properties"));
        } catch (ConfigurationException ce) {
            this.debug(ce);
        }
        return configuration;
    }
    
    /**
     * Creates XML-RPC client configuration.
     * @param configuration
     * @return XmlRpcClientConfig
     */
    private XmlRpcClientConfig createXmlRpcClientConfiguration(CompositeConfiguration configuration) {
        final XmlRpcClientConfigImpl xmlRpcClientConfig = new XmlRpcClientConfigImpl();
        // basic settings
        xmlRpcClientConfig.setEnabledForExtensions(true);
        // xmlrpc basic encoding
        xmlRpcClientConfig.setBasicEncoding(configuration.getString(XMLRPC_BASIC_ENCODING));
        xmlRpcClientConfig.setBasicPassword(configuration.getString(XMLRPC_BASIC_PASSWORD));
        xmlRpcClientConfig.setBasicUserName(configuration.getString(XMLRPC_BASIC_USERNAME));
        // connectino timeout
        try {
            xmlRpcClientConfig.setConnectionTimeout(configuration.getInt(XMLRPC_CONNECTION_TIMEOUT));
        } catch (ConversionException ce) {
            this.debug(ce);
        } catch (NoSuchElementException nsee) {
            this.debug(nsee);
        }
        // content length optional
        try {
            xmlRpcClientConfig.setContentLengthOptional(configuration.getBoolean(XMLRPC_CONTENT_LENGTH_OPTIONAL));
        } catch (ConversionException ce) {
            this.debug(ce);
        } catch (NoSuchElementException nsee) {
            this.debug(nsee);
        }
        // xmlrpc enabled for exceptions
        try {
            xmlRpcClientConfig.setEnabledForExceptions(configuration.getBoolean(XMLRPC_ENABLED_FOR_EXCEPTIONS));
        } catch (ConversionException ce) {
            this.debug(ce);
        } catch (NoSuchElementException nsee) {
            this.debug(nsee);
        }
        // xmlrpc encoding
        xmlRpcClientConfig.setEncoding(configuration.getString(XMLRPC_ENCODING));
        // gzip compression
        try {
            xmlRpcClientConfig.setGzipCompressing(configuration.getBoolean(XMLRPC_GZIP_COMPRESSION));
        } catch (ConversionException ce) {
            this.debug(ce);
        } catch (NoSuchElementException nsee) {
            this.debug(nsee);
        }
        // gzip request
        try {
            xmlRpcClientConfig.setGzipRequesting(configuration.getBoolean(XMLRPC_GZIP_REQUESTING));
        } catch (ConversionException ce) {
            this.debug(ce);
        } catch (NoSuchElementException nsee) {
            this.debug(nsee);
        }
        // timeout
        try {
            xmlRpcClientConfig.setReplyTimeout(configuration.getInt(XMLRPC_REPLY_TIMEOUT));
        } catch (ConversionException ce) {
            this.debug(ce);
        } catch (NoSuchElementException nsee) {
            this.debug(nsee);
        }
        // user agent
        xmlRpcClientConfig.setUserAgent(configuration.getString(XMLRPC_USER_AGENT));
        return xmlRpcClientConfig;
    }
    
    /**
     * @return the url
     */
    public URL getUrl() {
        return url;
    }
    
    /**
     * Logs a throwable object in debug level. Before outputting the message it
     * checks if debug is enabled or not. If it is not enabled the message is
     * not displayed and the String object is not created/concatenated, etc.
b     * @param throwable Throwable object.
     */
    private void debug(Throwable throwable) {
    if (LOGGER.isLoggable(Level.FINEST)) {
        LOGGER.log(Level.FINEST, throwable.getMessage(), throwable);
    }
    }
    
}
