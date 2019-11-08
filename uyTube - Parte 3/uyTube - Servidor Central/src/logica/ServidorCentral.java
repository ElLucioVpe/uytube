package logica;

import java.net.MalformedURLException;

import logica.controladores.Fabrica;
import logica.controladores.IControladorUsuario;
import logica.wsclient.ServicesPublisher;

public class ServidorCentral {
	public static void main(String[] args) throws MalformedURLException {
		Fabrica f = Fabrica.getInstance();
		//Main para pruebas
		IControladorUsuario user = f.getIControladorUsuario();
		ServicesPublisher.main(args);
        System.out.println("Encendiendo...");
    }
}
 