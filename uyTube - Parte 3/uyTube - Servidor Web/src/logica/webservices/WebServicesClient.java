package logica.webservices;


public class WebServicesClient {

	public static void main(String[] args) throws Exception {
	   
        WScontroladorUsuarioImplService service = new WScontroladorUsuarioImplService();
        WScontroladorUsuario controlador = service.getWScontroladorUsuarioImplPort();

        //Prueba
        System.out.println(controlador.probando("cosas randomicas"));
        
        UsuarioDt user = controlador.consultarUsuario(5);
        String usernick = controlador.obtenerNickUsuario(5);
        CanalDt canal = controlador.obtenerCanalDt(5);
        //System.out.println(user+"--nick--"+user.getNickname()+user.getMail());
        System.out.println("--nick2--"+usernick);
        System.out.println(canal+"--canal--"+canal.getNombre());
        
        ListaDeReproduccionDt lista = controlador.obtenerListaDtPorId(1);
        //System.out.println("--lista--"+lista.getNombre());


    }
}
