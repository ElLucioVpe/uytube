/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dt;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Esteban
 * Al no necesitarlos me ahorre casi todo
 * esta hecho solo para listar en quitarVideoListaDR
 */
@XmlRootElement
public class VideoListaDt {
    private int id;
    private String nombre;
    
    public VideoListaDt (int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    public int getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
}
