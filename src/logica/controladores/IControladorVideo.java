/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.controladores;

import java.util.Date;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;
import logica.dt.valoracionDt;

/**
 *
 * @author Esteban
 */
public interface IControladorVideo {

    public abstract void AltaVideo(String nombre, String duracion, String url, String desc, int user, String categoria);
    public abstract void ModificarVideo(int id, String nuevoNom, String nuevaDur, String nuevaUrl, String nuevaDesc, Date nuevaFpub, boolean nuevaPriv);
    public abstract void ValorarVideo(int user_valoracion, int id_video, boolean gusta);
    public abstract void ComentarVideo(int user_id, int video_id, long id_padre, String texto, Date fecha);

    //Auxiliares
    public abstract DefaultMutableTreeNode obtenerComentariosVideo(String videoNombre);
    public abstract List<valoracionDt>  obtenerValoracionVideo(String nomvideo);
}
