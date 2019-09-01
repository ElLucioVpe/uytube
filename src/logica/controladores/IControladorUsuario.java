/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.controladores;

import java.util.List;

/**
 *
 * @author Esteban
 */
public interface IControladorUsuario {

    public abstract void AltaUsuario(String nick, String nom, String apell, String mail, String fnac, String img);
    public abstract void AltaCanal(String nombre, boolean privado, int user_id, String descripcion);
    public abstract void ModificarUsuario(int id, String nuevonom, String nuevoapell, String nuevafechaNac, String nuevonomC, String nuevadesC, boolean nuevaprivC);
    public abstract List<String> ListarUsuarios();
    public abstract void ConsultarUsuario(int id);

    //Listas de Reproduccion
    public abstract void AltaListaDeReproduccionPorDefecto(String nombre);
    public abstract void AltaListaDeReproduccionParticular(String nombre, int id_propietario, boolean privacidad, String categoria);

    //Auxiliares
    public abstract int obtenerIdUsuario(String nick);
    public abstract void EliminarUsuario(int id);
    public abstract List obtenerCategorias();
    
    //SeguirUsuario
    
  public abstract void seguirUsuario(String seguidor, String seguido);
}
