
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
@WebServiceClient(name = "WScontroladorVideoImplService", targetNamespace = "http://webservices.logica/", wsdlLocation = "http://192.168.1.21:9991/ws/cVideo?wsdl")
public class WScontroladorVideoImplService
    extends Service
{

    private final static URL WSCONTROLADORVIDEOIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException WSCONTROLADORVIDEOIMPLSERVICE_EXCEPTION;
    private final static QName WSCONTROLADORVIDEOIMPLSERVICE_QNAME = new QName("http://webservices.logica/", "WScontroladorVideoImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
        	//Obtengo datos del archivo .properties
    		File properties = new File(System.getProperty("user.home")+"/.UyTube");
    		URL[] urls = {properties.toURI().toURL()};
    		ClassLoader loader = new URLClassLoader(urls);
    		ResourceBundle bundle = ResourceBundle.getBundle("uytube_webservices", Locale.getDefault(), loader);
    		
    		url = new URL("http://"+bundle.getString("serviceIP")+bundle.getString("serviceVideo")+"?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        WSCONTROLADORVIDEOIMPLSERVICE_WSDL_LOCATION = url;
        WSCONTROLADORVIDEOIMPLSERVICE_EXCEPTION = e;
    }

    public WScontroladorVideoImplService() {
        super(__getWsdlLocation(), WSCONTROLADORVIDEOIMPLSERVICE_QNAME);
    }

    public WScontroladorVideoImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), WSCONTROLADORVIDEOIMPLSERVICE_QNAME, features);
    }

    public WScontroladorVideoImplService(URL wsdlLocation) {
        super(wsdlLocation, WSCONTROLADORVIDEOIMPLSERVICE_QNAME);
    }

    public WScontroladorVideoImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, WSCONTROLADORVIDEOIMPLSERVICE_QNAME, features);
    }

    public WScontroladorVideoImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WScontroladorVideoImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns WScontroladorVideo
     */
    @WebEndpoint(name = "WScontroladorVideoImplPort")
    public WScontroladorVideo getWScontroladorVideoImplPort() {
        return super.getPort(new QName("http://webservices.logica/", "WScontroladorVideoImplPort"), WScontroladorVideo.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WScontroladorVideo
     */
    @WebEndpoint(name = "WScontroladorVideoImplPort")
    public WScontroladorVideo getWScontroladorVideoImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://webservices.logica/", "WScontroladorVideoImplPort"), WScontroladorVideo.class, features);
    }

    private static URL __getWsdlLocation() {
        if (WSCONTROLADORVIDEOIMPLSERVICE_EXCEPTION!= null) {
            throw WSCONTROLADORVIDEOIMPLSERVICE_EXCEPTION;
        }
        return WSCONTROLADORVIDEOIMPLSERVICE_WSDL_LOCATION;
    }

}
