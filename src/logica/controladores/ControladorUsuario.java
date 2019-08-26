/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.controladores;
import java.util.Collection;
import java.util.Date;
import logica.controladores.IControladorUsuario;

/**
 *
 * @author Esteban
 */
public class ControladorUsuario implements IControladorUsuario {
    
    private Collection usuarios;
    
    public ControladorUsuario() {
    }
    
    @Override
    public void AltaUsuario(String nick, String nom, String apell, Date fnac){
        
    }
}
