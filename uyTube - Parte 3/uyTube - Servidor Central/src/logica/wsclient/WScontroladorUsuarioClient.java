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
import logica.webservices.WScontroladorUsuario;

public class WScontroladorUsuarioClient {
	
	public static void main(String[] args) throws Exception {
	   
		//Obtengo datos del archivo .properties
		File properties = new File(System.getProperty("user.home")+"/.UyTube");
		URL[] urls = {properties.toURI().toURL()};
		ClassLoader loader = new URLClassLoader(urls);
		ResourceBundle bundle = ResourceBundle.getBundle("uytube_webservices", Locale.getDefault(), loader);	
		
		URL url = new URL("http://"+bundle.getString("serviceIP")+bundle.getString("serviceUsuario"));
	
        //1st argument service URI, refer to wsdl document above
		//2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://webservices.logica/", "WScontroladorUsuarioImplService");

        Service service = Service.create(url, qname);

        WScontroladorUsuario controlador = service.getPort(WScontroladorUsuario.class);

        //Prueba
        System.out.println(controlador.probando("cosas randomicas"));
        
        UsuarioDt user = controlador.ConsultarUsuario(5);
        String usernick = controlador.obtenerNickUsuario(5);
        //CanalDt canal = controlador.obtenerCanalDt(5);
        System.out.println(user+"--nick--"+user.getNickname()+user.getMail());
        System.out.println("--nick2--"+usernick);
        //System.out.println(canal+"--canal--"+canal.getNombre());
        
        ListaDeReproduccionDt lista = controlador.obtenerListaDtPorId(1);
        System.out.println("--lista--"+lista.getNombre());


    }

}
