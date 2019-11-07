package logica.wsclient;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import logica.dt.CanalDt;
import logica.dt.ListaDeReproduccionDt;
import logica.dt.UsuarioDt;
import logica.webservices.WScontroladorCategoria;
import logica.webservices.WScontroladorUsuario;
import logica.webservices.WScontroladorVideo;

public class WStestClient {
	
	public static void main(String[] args) throws Exception {
	   
		//Obtengo datos del archivo .properties
		File properties = new File(System.getProperty("user.home")+"/.UyTube");
		URL[] urls = {properties.toURI().toURL()};
		ClassLoader loader = new URLClassLoader(urls);
		ResourceBundle bundle = ResourceBundle.getBundle("uytube_webservices", Locale.getDefault(), loader);	
		
		URL url1 = new URL("http://"+bundle.getString("serviceIP")+bundle.getString("serviceUsuario"));
        QName qname1 = new QName("http://webservices.logica/", "WScontroladorUsuarioImplService");
        
        URL url2 = new URL("http://"+bundle.getString("serviceIP")+bundle.getString("serviceVideo"));
        QName qname2 = new QName("http://webservices.logica/", "WScontroladorVideoImplService");
        
        URL url3 = new URL("http://"+bundle.getString("serviceIP")+bundle.getString("serviceCategoria"));
        QName qname3 = new QName("http://webservices.logica/", "WScontroladorCategoriaImplService");
        
        WScontroladorUsuario wsusuario = Service.create(url1, qname1).getPort(WScontroladorUsuario.class);
        WScontroladorVideo wsvideo = Service.create(url2, qname2).getPort(WScontroladorVideo.class);
        WScontroladorCategoria wscategpria = Service.create(url3, qname3).getPort(WScontroladorCategoria.class);

        //Pruebas WSusuario
        System.out.println(wsusuario.probando("webservice de usuario"));
        
        UsuarioDt user = wsusuario.ConsultarUsuario(5);
        String usernick = wsusuario.obtenerNickUsuario(5);
        //CanalDt canal = controlador.obtenerCanalDt(5);
        System.out.println(user+"--nick--"+user.getNickname()+user.getMail());
        System.out.println("--nick2--"+usernick);

        //Pruebas WSvideo
        System.out.println(wsvideo.probando("webservice de video"));
        
        //Pruebas Wcategoria
        System.out.println(wsvideo.probando("webservice de categoria"));
      

    }

}
