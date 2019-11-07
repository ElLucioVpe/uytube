/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dt;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Esteban
 */
@XmlRootElement
public class ListaDeReproduccionDt {
    private int id;
    private String nombre;
    private boolean privada;
    private String tipo;
    private String categoria;
    private int user_id;
    private Date fechaUV;

    public ListaDeReproduccionDt(){
    }

    public ListaDeReproduccionDt(int id, String nombre, String tipo, boolean privada, String categoria, int usuario, Date fechaUV) {
        this.id = id;
        this.nombre = nombre;
        this.privada = privada;
        this.tipo = tipo;
        this.categoria = categoria;
        this.user_id = usuario;
        this.fechaUV = fechaUV;
    }

    public Date getFechaUV() {
        return fechaUV;
    }
    
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    
    public boolean getPrivada() {
        return privada;
    }

    public String getTipo() {
        return tipo;
    }
    
    public String getCategoria() {
        return categoria;
    }

    public int getIdUsuario() {
        return user_id;
    }
    
    /****************************************
     * Estos son sets que sin estar SOAP 
     * no reconoce las variables.
     *     
     ****************************************/
    public void setFechaUV(Date fecha) {
        fechaUV = fecha;
    }
    
    public void setId(int nuevaId) {
        id = nuevaId;
    }

    public void setNombre(String nom) {
    	this.nombre = nom;
    }
    
    public void setPrivada(boolean estado) {
        privada = estado;
    }

    public void setTipo(String nuevoTipo) {
        tipo = nuevoTipo;
    }
    
    public void setCategoria(String nuevaCat) {
        categoria = nuevaCat;
    }

    public void setIdUsuario(int nuevaId) {
        user_id = nuevaId;
    }

}