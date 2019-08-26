/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.controladores;
import logica.controladores.ControladorUsuario;
import logica.controladores.ControladorVideo;

/**
 *
 * @author Esteban
 */
public class Fabrica {
    private static Fabrica instancia;

    private Fabrica() {
    };

    public static Fabrica getInstance() {
        if (instancia == null) {
            instancia = new Fabrica();
        }
        return instancia;
    }

    public IControladorUsuario getIControladorUsuario() {
        return new ControladorUsuario();
    }
    
    public ControladorVideo getIControladorVideo() {
        return new ControladorVideo();
    }
}