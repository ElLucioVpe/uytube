package logica.wsclient;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import logica.webservices.ControladorUsuarioService;

public class ControladorUsuarioServiceClient{
	
	public static void main(String[] args) throws Exception {
	   
		URL url = new URL("http://localhost:9999/ws/cUsuario?wsdl");
	
        //1st argument service URI, refer to wsdl document above
		//2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://localhost:9999/ws/", "ControladorUsuarioService");

        Service service = Service.create(url, qname);

        ControladorUsuarioService controlador = service.getPort(ControladorUsuarioService.class);

        System.out.println(controlador.probando("puto taque"));

    }

}
