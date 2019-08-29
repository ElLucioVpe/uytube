/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.controladores;
import java.util.Date;

/**
 *
 * @author Esteban
 */
public interface IControladorUsuario {
    
    public abstract void AltaUsuario(String nick, String nom, String apell, String mail, String fnac, String img);
    public abstract void AltaCanal(String nombre, boolean privado, int user_id, String descripcion);
    public abstract int obtenerIdUsuario(String nick);
    public abstract void EliminarUsuario(int id);
}
