/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.controladores;

/**
 *
 * @author Esteban
 */
public interface IControladorVideo {
    
    public abstract void AltaVideo(String nombre, String duracion, String url, String desc, String fpub,int user);
    public abstract void ModificarVideo(int id, String nuevoNom, String nuevaDur, String nuevaUrl, String nuevaDesc, String nuevaFpub, boolean nuevaPriv);

}
