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
    
    public abstract void AltaUsuario(String nick, String nom, String apell, Date fnac);
}
