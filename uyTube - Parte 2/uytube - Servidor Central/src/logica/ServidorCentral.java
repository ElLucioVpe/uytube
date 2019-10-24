package logica;

import logica.controladores.Fabrica;
import logica.controladores.IControladorUsuario;

public class ServidorCentral {
	public static void main(String[] args) {
		Fabrica f = Fabrica.getInstance();
		IControladorUsuario user = f.getIControladorUsuario();
        System.out.println("Encendiendo...");
    }
}
 