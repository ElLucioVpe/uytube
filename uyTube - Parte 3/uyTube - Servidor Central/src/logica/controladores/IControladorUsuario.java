/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.controladores;

import java.util.Date;
import java.util.List;
import logica.dt.CanalDt;
import logica.dt.ListaDeReproduccionDt;
import logica.dt.UsuarioDt;
import logica.dt.VideoDt;

/**
 *
 * @author Esteban
 */
public interface IControladorUsuario {

    public abstract void AltaUsuario(String nick, String pass, String nom, String apell, String mail, Date fnac, String img); /////
    public abstract void AltaCanal(String nombre, boolean privado, String categoria, int user_id, String descripcion);  /////
    public abstract void ModificarUsuario(int id_user, String nuevopass, String nuevonom, String nuevoapell, Date nuevafechaNac, String nuevonomC, String nuevacatC, String nuevadesC, boolean nuevaprivC, String nuevaImg);  /////
    public abstract List<UsuarioDt> ListarUsuarios(); /////
    public abstract UsuarioDt ConsultarUsuario(int id_user); ////

    //Listas de Reproduccion
    public abstract void AltaListaDeReproduccionPorDefecto(String nombre); /////
    public abstract void AltaListaDeReproduccionParticular(String nombre, int id_propietario, boolean privacidad, String categoria); ////
    public abstract void ModificarListaDeReproduccion(int usuario, String lista, String nuevaCat, boolean nuevaPri);
    public abstract void AgregarVideoListaDeReproduccion(int usuarioVideo, int usuarioLista, String video, String lista);
    public abstract void QuitarVideoListaDeReproduccion(int usuarioLista, String lista, int video);
    public abstract void AltaListaHistorial(String nombre ,String idVideo, String idUser, String fecha, int cantVisitas);
    public abstract void agregarVideoListaHisotrial(String idVideo, String idUsuario, int cant,String fecha);
     public abstract void actualizarVisitaListahistorial(String idVideo, String idUsuario);
     public abstract ListaHistorialDt obtenerListaHistorial(String idUsuario);
    //SeguirUsuario
    public abstract void seguirUsuario(String seguidor, String seguido); ////
    public abstract void dejarDeSeguirUsuario(String seguidor, String seguido); ////
    
    //listar video
    public abstract List<VideoDt> listarVideosDeUsuario(String usernick);
    //Auxiliares
    public abstract int obtenerIdUsuario(String nick); ////
    public abstract int obtenerIdUsuarioMail(String mail); ////
    public abstract String obtenerNickUsuario(int id_user); ////
    public abstract void EliminarUsuario(int id_user);  /////
    public abstract List<String> obtenerCategorias(); 
    public abstract String obtenerTipoLista(int propietario, String lista);  ////
    public abstract List<String> obtenerListasUsuario(int id_user);  ////Lc
    public abstract List<VideoDt> obtenerVideosLista(int id_user, String lista);
    public abstract ListaDeReproduccionDt obtenerListaDt(int id_user, String lista);
    
    public abstract List<String> ListarSeguidores(int userId); //////
    public abstract List<String> ListarSiguiendo(int userId);  ///////
    public abstract List<String> ListarVideos(int userId);  //////---
    
    // Para servidor web
    public abstract Integer LoginUsuario(String _user, String _password); ////
    public abstract boolean estaSuscripto(int suscripto, int pcanal); ////
    public abstract List<ListaDeReproduccionDt> obtenerListasDtPorUsuario(int id_user);
    public abstract List<ListaDeReproduccionDt> obtenerListas();
    public abstract ListaDeReproduccionDt obtenerListaDtPorId(int id_lista); //Creo esto porque es mas conveniente a nivel de web pero obtenerListaDt(id_user, nombre) es mas conveniente en la estacion
    public abstract CanalDt obtenerCanalDt(int id_canal); ////
    
    
    //Para pruebas
    public abstract void EliminarListaDeReproduccionPorDefecto(String nombre);  ///--
    public abstract void EliminarListaDeReproduccionParticular(int id_user, String nombre);  ///--

    //Parte 3
    public abstract void BajaUsuario(int iduser);
    public abstract List<UsuarioDt> ListarUsuariosInactivos();
}
