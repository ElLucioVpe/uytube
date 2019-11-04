package logica.wsclient;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.xml.ws.Endpoint;
import logica.webservices.*;

public class ServicesPublisher{
	
	public static void main(String[] args) throws MalformedURLException {
		
		//Obtengo datos del archivo .properties
		File properties = new File(System.getProperty("user.home")+"/.UyTube");
		URL[] urls = {properties.toURI().toURL()};
		ClassLoader loader = new URLClassLoader(urls);
		ResourceBundle bundle = ResourceBundle.getBundle("uytube_webservices", Locale.getDefault(), loader);
		
		String serviceIP = bundle.getString("serviceIP");
		
		//Publico services
		Endpoint.publish("http://"+serviceIP+bundle.getString("serviceUsuario"), new WScontroladorUsuarioImpl());
		//Endpoint.publish("http://"+serviceIP+bundle.getString("serviceVideo"), new WScontroladorVideoImpl());
		//Endpoint.publish("http://"+serviceIP+bundle.getString("serviceCategoria"), new WScontroladorCategoriaImpl());
		//Endpoint.publish("http://"+serviceIP+bundle.getString("serviceUploadFile"), new WSuploadFileImpl());
		
		//ep.stop();
    }
	
}