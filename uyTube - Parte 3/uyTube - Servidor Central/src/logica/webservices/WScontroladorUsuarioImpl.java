package logica.webservices;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.jws.WebService;

import logica.dt.CanalDt;
import logica.dt.ListaDeReproduccionDt;
import logica.dt.UsuarioDt;
import logica.webservices.pojos.POJOListadrDt;
import logica.webservices.pojos.POJOString;
import logica.webservices.pojos.POJOUsuarioDt;
import logica.webservices.pojos.POJOVideoDt;
import logica.controladores.Fabrica;
import logica.controladores.IControladorUsuario;

@WebService(endpointInterface = "logica.webservices.WScontroladorUsuario")
public class WScontroladorUsuarioImpl implements WScontroladorUsuario {
 
	Fabrica fab = Fabrica.getInstance();
	final IControladorUsuario controlador = fab.getIControladorUsuario();
	
	@Override
	public String probando(String text) {
		return "probando "+text+"...";
	}
	
	@Override
	public void AltaUsuario(String nick, String pass, String nom, String apell, String mail, Date fnac, String img) {
		controlador.AltaUsuario(nick, pass, nom, apell, mail, fnac, img);
	}
	@Override
    public void AltaCanal(String nombre, boolean privado, String categoria, int user_id, String descripcion) {
    	controlador.AltaCanal(nombre, privado, categoria, user_id, descripcion);
    }
	@Override
    public void ModificarUsuario(int id_user, String nuevopass, String nuevonom, String nuevoapell, Date nuevafechaNac, String nuevonomC, String nuevacatC, String nuevadesC, boolean nuevaprivC, String nuevaImg){
    	controlador.ModificarUsuario(id_user, nuevopass, nuevonom, nuevoapell, nuevafechaNac, nuevonomC, nuevacatC, nuevadesC, nuevaprivC, nuevaImg);
    }
	@Override
    public POJOUsuarioDt ListarUsuarios() {
    	return new POJOUsuarioDt(controlador.ListarUsuarios());
    }
	@Override
    public UsuarioDt ConsultarUsuario(int id_user) {
    	//return new UsuarioDt(21, "pass", "j","juan", "perez", "1@j.com", new Date(), null, null, true, null);
		return controlador.ConsultarUsuario(id_user);
    }
    @Override
    public void AltaListaDeReproduccionPorDefecto(String nombre) {
    	controlador.AltaListaDeReproduccionPorDefecto(nombre);
    } 
    @Override
    public void AltaListaDeReproduccionParticular(String nombre, int id_propietario, boolean privacidad, String categoria) {
    	controlador.AltaListaDeReproduccionParticular( nombre,  id_propietario,  privacidad,  categoria);
    }
    @Override
    public void ModificarListaDeReproduccion(int usuario, String lista, String nuevaCat, boolean nuevaPri) {
    	controlador.ModificarListaDeReproduccion(usuario, lista, nuevaCat, nuevaPri);
    }
    @Override
    public void AgregarVideoListaDeReproduccion(int usuarioVideo, int usuarioLista, String video, String lista) {
    	controlador.AgregarVideoListaDeReproduccion(usuarioVideo, usuarioLista, video, lista);
    }
    @Override
    public void QuitarVideoListaDeReproduccion(int usuarioLista, String lista, int video) {
    	controlador.QuitarVideoListaDeReproduccion(usuarioLista, lista, video);
    }
    @Override
    public void seguirUsuario(String seguidor, String seguido) {
    	controlador.seguirUsuario(seguidor, seguido);
    }     
    @Override
    public void dejarDeSeguirUsuario(String seguidor, String seguido) {
    	controlador.dejarDeSeguirUsuario(seguidor, seguido);    
    }
    @Override
    public POJOVideoDt listarVideosDeUsuario(String usernick) {
    	return new POJOVideoDt(controlador.listarVideosDeUsuario(usernick));
    }
    @Override
    public int obtenerIdUsuario(String nick) {
    	return controlador.obtenerIdUsuario(nick);
    }
    @Override
    public int obtenerIdUsuarioMail(String mail) {
    	return controlador.obtenerIdUsuarioMail(mail);
    }
    @Override
    public String obtenerNickUsuario(int id_user) {
    	return controlador.obtenerNickUsuario(id_user);
    }
    @Override
    public void EliminarUsuario(int id_user) {
    	controlador.EliminarUsuario(id_user);
    }
    @Override
    public POJOString obtenerCategorias() {
    	return new POJOString(controlador.obtenerCategorias());
    } 
    @Override
    public String obtenerTipoLista(int propietario, String lista) {
    	return controlador.obtenerTipoLista(propietario, lista);
    }
    @Override
    public POJOString obtenerListasUsuario(int id_user) {
    	return new POJOString(controlador.obtenerListasUsuario(id_user));
    }
    @Override
    public POJOVideoDt obtenerVideosLista(int id_user, String lista) {
    	return new POJOVideoDt(controlador.obtenerVideosLista(id_user, lista));
    }
    @Override
    public ListaDeReproduccionDt obtenerListaDt(int id_user, String lista) {
    	return controlador.obtenerListaDt(id_user, lista);
    }
    @Override
    public POJOString ListarSeguidores(int userId) {
    	return new POJOString(controlador.ListarSeguidores(userId));
    }
    @Override
    public POJOString ListarSiguiendo(int userId) {
    	return new POJOString(controlador.ListarSiguiendo(userId));
    } 
    @Override
    public POJOString ListarVideos(int userId) {
    	return new POJOString(controlador.ListarVideos(userId));
    }
    @Override
    public Integer LoginUsuario(String _user, String _password) {
    	return controlador.LoginUsuario(_user, _password);
    }
    @Override
    public boolean estaSuscripto(int suscripto, int pcanal) {
    	return controlador.estaSuscripto(suscripto, pcanal);
    }
    @Override
    public POJOListadrDt obtenerListasDtPorUsuario(int id_user) {
    	return new POJOListadrDt(controlador.obtenerListasDtPorUsuario(id_user));
    }
    @Override
    public POJOListadrDt obtenerListas() {
    	return new POJOListadrDt(controlador.obtenerListas());
    }
    @Override
    public ListaDeReproduccionDt obtenerListaDtPorId(int id_lista) {
    	return controlador.obtenerListaDtPorId(id_lista);
    }
    @Override
    public CanalDt obtenerCanalDt(int id_canal) {
    	return controlador.obtenerCanalDt(id_canal);
    }
    @Override
    public void BajaUsuario(int iduser) {
    	controlador.BajaUsuario(iduser);
    }
    @Override
    public POJOUsuarioDt ListarUsuariosInactivos() {
    	return new POJOUsuarioDt(controlador.ListarUsuariosInactivos());
    }
    
    @Override
    public boolean saveFile(byte[] fileContent, String fileName) {
		OutputStream outputStream = null;
		try {
			//Obtengo datos del archivo .properties
    		File properties = new File(System.getProperty("user.home")+"/.UyTube");
    		URL[] urls = {properties.toURI().toURL()};
    		ClassLoader loader = new URLClassLoader(urls);
    		ResourceBundle bundle = ResourceBundle.getBundle("uytube_conf", Locale.getDefault(), loader);
    		
    		String path = bundle.getString("images");
    		
			File file = new File(path + File.separator + fileName);
			outputStream = new FileOutputStream(file);
			outputStream.write(fileContent);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
}
