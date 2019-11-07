
package logica.webservices;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "WScontroladorUsuarioImplService", targetNamespace = "http://webservices.logica/", wsdlLocation = "http://192.168.1.21:9990/ws/cUsuario?wsdl")
public class WScontroladorUsuarioImplService
    extends Service
{

    private final static URL WSCONTROLADORUSUARIOIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException WSCONTROLADORUSUARIOIMPLSERVICE_EXCEPTION;
    private final static QName WSCONTROLADORUSUARIOIMPLSERVICE_QNAME = new QName("http://webservices.logica/", "WScontroladorUsuarioImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
        	//Obtengo datos del archivo .properties
    		File properties = new File(System.getProperty("user.home")+"/.UyTube");
    		URL[] urls = {properties.toURI().toURL()};
    		ClassLoader loader = new URLClassLoader(urls);
    		ResourceBundle bundle = ResourceBundle.getBundle("uytube_webservices", Locale.getDefault(), loader);
    		
    		url = new URL("http://"+bundle.getString("serviceIP")+bundle.getString("serviceUsuario")+"?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        WSCONTROLADORUSUARIOIMPLSERVICE_WSDL_LOCATION = url;
        WSCONTROLADORUSUARIOIMPLSERVICE_EXCEPTION = e;
    }

    public WScontroladorUsuarioImplService() {
        super(__getWsdlLocation(), WSCONTROLADORUSUARIOIMPLSERVICE_QNAME);
    }

    public WScontroladorUsuarioImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), WSCONTROLADORUSUARIOIMPLSERVICE_QNAME, features);
    }

    public WScontroladorUsuarioImplService(URL wsdlLocation) {
        super(wsdlLocation, WSCONTROLADORUSUARIOIMPLSERVICE_QNAME);
    }

    public WScontroladorUsuarioImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, WSCONTROLADORUSUARIOIMPLSERVICE_QNAME, features);
    }

    public WScontroladorUsuarioImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WScontroladorUsuarioImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns WScontroladorUsuario
     */
    @WebEndpoint(name = "WScontroladorUsuarioImplPort")
    public WScontroladorUsuario getWScontroladorUsuarioImplPort() {
        return super.getPort(new QName("http://webservices.logica/", "WScontroladorUsuarioImplPort"), WScontroladorUsuario.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WScontroladorUsuario
     */
    @WebEndpoint(name = "WScontroladorUsuarioImplPort")
    public WScontroladorUsuario getWScontroladorUsuarioImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://webservices.logica/", "WScontroladorUsuarioImplPort"), WScontroladorUsuario.class, features);
    }

    private static URL __getWsdlLocation() {
        if (WSCONTROLADORUSUARIOIMPLSERVICE_EXCEPTION!= null) {
            throw WSCONTROLADORUSUARIOIMPLSERVICE_EXCEPTION;
        }
        return WSCONTROLADORUSUARIOIMPLSERVICE_WSDL_LOCATION;
    }

}
