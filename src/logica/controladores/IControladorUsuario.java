/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.controladores;

import java.util.List;
import logica.dt.UsuarioDt;
import logica.dt.VideoListaDt;

/**
 *
 * @author Esteban
 */
public interface IControladorUsuario {

    public abstract void AltaUsuario(String nick, String nom, String apell, String mail, String fnac, String img);
    public abstract void AltaCanal(String nombre, boolean privado, int user_id, String descripcion);
    public abstract void ModificarUsuario(int id, String nuevonom, String nuevoapell, String nuevafechaNac, String nuevonomC, String nuevadesC, boolean nuevaprivC);
    public abstract List<String> ListarUsuarios();
    public abstract UsuarioDt ConsultarUsuario(int id);

    //Listas de Reproduccion
    public abstract void AltaListaDeReproduccionPorDefecto(String nombre);
    public abstract void AltaListaDeReproduccionParticular(String nombre, int id_propietario, boolean privacidad, String categoria);
    public abstract void ModificarListaDeReproduccion(int usuario, String lista, String nuevaCat, boolean nuevaPri);
    public abstract void AgregarVideoListaDeReproduccion(int usuarioVideo, int usuarioLista, String video, String lista);
    public abstract void QuitarVideoListaDeReproduccion(int usuarioLista, String lista, int video);
    
    //SeguirUsuario
    public abstract void seguirUsuario(String seguidor, String seguido);
    public void dejarDeSeguirUsuario(String seguidor, String seguido);
    
    //Auxiliares
    public abstract int obtenerIdUsuario(String nick);
    public abstract void EliminarUsuario(int id);
    public abstract List obtenerCategorias();
    public abstract List<String> obtenerListasUsuario(int id);
    public abstract List<VideoListaDt> obtenerVideosLista(int id, String lista);
}
