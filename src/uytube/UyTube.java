/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uytube;

import java.awt.EventQueue;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import logica.Fabrica;
import logica.IControladorUsuario;

/**
 *
 * @author Esteban
 */

public class UyTube {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    public UyTube() {

        // Inicialización
        Fabrica fabrica = Fabrica.getInstance();
        IControladorUsuario usuarios = fabrica.getIControladorUsuario();
    
    }
    
}
