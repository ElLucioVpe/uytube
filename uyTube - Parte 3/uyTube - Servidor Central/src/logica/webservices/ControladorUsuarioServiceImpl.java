package logica.webservices;

import java.util.Date;
import java.util.List;

import logica.dt.CanalDt;
import logica.dt.ListaDeReproduccionDt;
import logica.dt.UsuarioDt;
import logica.dt.VideoDt;
import logica.controladores.Fabrica;
import logica.controladores.IControladorUsuario;

public class ControladorUsuarioServiceImpl {
 
	Fabrica fab = Fabrica.getInstance();
	final IControladorUsuario controlador = fab.getIControladorUsuario();
	
	public void AltaUsuario(String nick, String pass, String nom, String apell, String mail, Date fnac, String img) {
		controlador.AltaUsuario(nick, pass, nom, apell, mail, fnac, img);
	}
	
    public void AltaCanal(String nombre, boolean privado, String categoria, int user_id, String descripcion) {
    	controlador.AltaCanal(nombre, privado, categoria, user_id, descripcion);
    }
    
    public void ModificarUsuario(int id_user, String nuevopass, String nuevonom, String nuevoapell, Date nuevafechaNac, String nuevonomC, String nuevacatC, String nuevadesC, boolean nuevaprivC, String nuevaImg){
    	controlador.ModificarUsuario(id_user, nuevopass, nuevonom, nuevoapell, nuevafechaNac, nuevonomC, nuevacatC, nuevadesC, nuevaprivC, nuevaImg);
    }
    
    public List<UsuarioDt> ListarUsuarios() {
    	return controlador.ListarUsuarios();
    } /////
    public UsuarioDt ConsultarUsuario(int id_user) {
    	return controlador.ConsultarUsuario(id_user);
    } ////

    //Listas de Reproduccion
    public void AltaListaDeReproduccionPorDefecto(String nombre) {
    	controlador.AltaListaDeReproduccionPorDefecto(nombre);
    } /////
    
    public void AltaListaDeReproduccionParticular(String nombre, int id_propietario, boolean privacidad, String categoria) {
    	controlador.AltaListaDeReproduccionParticular( nombre,  id_propietario,  privacidad,  categoria);
    }
    
    public void ModificarListaDeReproduccion(int usuario, String lista, String nuevaCat, boolean nuevaPri) {
    	controlador.ModificarListaDeReproduccion(usuario, lista, nuevaCat, nuevaPri);
    }
    
    public void AgregarVideoListaDeReproduccion(int usuarioVideo, int usuarioLista, String video, String lista) {
    	controlador.AgregarVideoListaDeReproduccion(usuarioVideo, usuarioLista, video, lista);
    }
    
    public void QuitarVideoListaDeReproduccion(int usuarioLista, String lista, int video) {
    	controlador.QuitarVideoListaDeReproduccion(usuarioLista, lista, video);
    }

    public void seguirUsuario(String seguidor, String seguido) {
    	controlador.seguirUsuario(seguidor, seguido);
    } ////
    public void dejarDeSeguirUsuario(String seguidor, String seguido) {
    	controlador.dejarDeSeguirUsuario(seguidor, seguido);    
    } ////

    public List<VideoDt> listarVideosDeUsuario(String usernick) {
    	return controlador.listarVideosDeUsuario(usernick);
    }
    
    //Auxiliares
    public int obtenerIdUsuario(String nick) {
    	return controlador.obtenerIdUsuario(nick);
    } ////
    public int obtenerIdUsuarioMail(String mail) {
    	return controlador.obtenerIdUsuarioMail(mail);
    } ////
    public String obtenerNickUsuario(int id_user) {
    	return controlador.obtenerNickUsuario(id_user);
    } ////
    public void EliminarUsuario(int id_user) {
    	controlador.EliminarUsuario(id_user);
    }  /////
    public List<String> obtenerCategorias() {
    	return controlador.obtenerCategorias();
    } 
    public String obtenerTipoLista(int propietario, String lista) {
    	return controlador.obtenerTipoLista(propietario, lista);
    }  ////
    public List<String> obtenerListasUsuario(int id_user) {
    	return controlador.obtenerListasUsuario(id_user);
    }  ////Lc
    public List<VideoDt> obtenerVideosLista(int id_user, String lista) {
    	return controlador.obtenerVideosLista(id_user, lista);
    }
    public ListaDeReproduccionDt obtenerListaDt(int id_user, String lista) {
    	return controlador.obtenerListaDt(id_user, lista);
    }
    
    public List<String> ListarSeguidores(int userId) {
    	return controlador.ListarSeguidores(userId);
    }
    public List<String> ListarSiguiendo(int userId) {
    	return controlador.ListarSiguiendo(userId);
    } 
    public List<String> ListarVideos(int userId) {
    	return controlador.ListarVideos(userId);
    }
    
    public Integer LoginUsuario(String _user, String _password) {
    	return controlador.LoginUsuario(_user, _password);
    }
    public boolean estaSuscripto(int suscripto, int pcanal) {
    	return controlador.estaSuscripto(suscripto, pcanal);
    }
    public List<ListaDeReproduccionDt> obtenerListasDtPorUsuario(int id_user) {
    	return controlador.obtenerListasDtPorUsuario(id_user);
    }
    public List<ListaDeReproduccionDt> obtenerListas() {
    	return controlador.obtenerListas();
    }
    public ListaDeReproduccionDt obtenerListaDtPorId(int id_lista) {
    	return controlador.obtenerListaDtPorId(id_lista);
    }
    public CanalDt obtenerCanalDt(int id_canal) {
    	return controlador.obtenerCanalDt(id_canal);
    }

    //Parte 3
    public void BajaUsuario(int iduser) {
    	controlador.BajaUsuario(iduser);
    }
    
    public List<UsuarioDt> ListarUsuariosInactivos() {
    	return controlador.ListarUsuariosInactivos();
    }

}
