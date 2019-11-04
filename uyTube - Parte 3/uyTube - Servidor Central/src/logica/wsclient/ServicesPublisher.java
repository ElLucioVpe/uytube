package logica.wsclient;

import javax.xml.ws.Endpoint;
import logica.webservices.*;

public class ServicesPublisher{
	
	public static void main(String[] args) {
		//Publico services
		Endpoint.publish("http://localhost:9999/ws/cUsuario", new ControladorUsuarioServiceImpl());
    }
	
}