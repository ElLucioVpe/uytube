package logica.webservices;

import logica.dt.CanalDt;
import logica.dt.ListaDeReproduccionDt;
import logica.dt.UsuarioDt;
import logica.dt.VideoDt;

import java.util.ArrayList;
import java.util.Date;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
 *
 * @author tecnologo(Esteban)
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface WScontroladorUsuario {

	@WebMethod String probando(String text);
    @WebMethod void AltaUsuario(String nick, String pass, String nom, String apell, String mail, Date fnac, String img); 
    @WebMethod void AltaCanal(String nombre, boolean privado, String categoria, int user_id, String descripcion); 
    @WebMethod void ModificarUsuario(int id_user, String nuevopass, String nuevonom, String nuevoapell, Date nuevafechaNac, String nuevonomC, String nuevacatC, String nuevadesC, boolean nuevaprivC, String nuevaImg);  /////
    @WebMethod ArrayList<UsuarioDt> ListarUsuarios(); 
    @WebMethod UsuarioDt ConsultarUsuario(int id_user); 
    @WebMethod void AltaListaDeReproduccionPorDefecto(String nombre);
    @WebMethod void AltaListaDeReproduccionParticular(String nombre, int id_propietario, boolean privacidad, String categoria);
    @WebMethod void ModificarListaDeReproduccion(int usuario, String lista, String nuevaCat, boolean nuevaPri);
    @WebMethod void AgregarVideoListaDeReproduccion(int usuarioVideo, int usuarioLista, String video, String lista);
    @WebMethod void QuitarVideoListaDeReproduccion(int usuarioLista, String lista, int video);
    @WebMethod void seguirUsuario(String seguidor, String seguido); 
    @WebMethod void dejarDeSeguirUsuario(String seguidor, String seguido); 
    @WebMethod ArrayList<VideoDt> listarVideosDeUsuario(String usernick);
    @WebMethod int obtenerIdUsuario(String nick);
    @WebMethod int obtenerIdUsuarioMail(String mail); 
    @WebMethod String obtenerNickUsuario(int id_user); 
    @WebMethod void EliminarUsuario(int id_user);  
    @WebMethod ArrayList<String> obtenerCategorias(); 
    @WebMethod String obtenerTipoLista(int propietario, String lista);  
    @WebMethod ArrayList<String> obtenerListasUsuario(int id_user);  
    @WebMethod ArrayList<VideoDt> obtenerVideosLista(int id_user, String lista);
    @WebMethod ListaDeReproduccionDt obtenerListaDt(int id_user, String lista);
    @WebMethod ArrayList<String> ListarSeguidores(int userId);
    @WebMethod ArrayList<String> ListarSiguiendo(int userId);  
    @WebMethod ArrayList<String> ListarVideos(int userId);
    @WebMethod Integer LoginUsuario(String _user, String _password);
    @WebMethod boolean estaSuscripto(int suscripto, int pcanal);
    @WebMethod ArrayList<ListaDeReproduccionDt> obtenerListasDtPorUsuario(int id_user);
    @WebMethod ArrayList<ListaDeReproduccionDt> obtenerListas();
    @WebMethod ListaDeReproduccionDt obtenerListaDtPorId(int id_lista);
    @WebMethod CanalDt obtenerCanalDt(int id_canal); 
    @WebMethod void BajaUsuario(int iduser);
    @WebMethod ArrayList<UsuarioDt> ListarUsuariosInactivos();
}
