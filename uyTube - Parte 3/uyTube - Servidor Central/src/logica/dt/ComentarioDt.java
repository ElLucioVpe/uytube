/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dt;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Esteban
 */
@XmlType
public class ComentarioDt {
    private long id;
    private long id_padre;
    private String contenido;
    private UsuarioDt user;
    private Date fecha;
    private List hijos;
    
    public ComentarioDt() {
        
    }
    
    public ComentarioDt(long id, long id_padre, String contenido, UsuarioDt user, Date fecha, List hijos) {
        this.id = id;
        this.id_padre = id;
        this.contenido = contenido;
        this.user = user;
        this.fecha = fecha;
        this.hijos = hijos;
    }
    
    public long getId() {
        return id;
    }
  
  
    public long getIdPadre() {
        return id_padre;
    }
    
    public String getContenido() {
        return contenido;
    }

    public UsuarioDt getUsuarioDt() {
        return user;
    }

    public Date getFecha() {
        return fecha;
    }
    
    public List getHijos() {
        return hijos;
    }
    
    /****************************************
     * Estos son sets que sin estar SOAP 
     * no reconoce las variables.
     *     
     ****************************************/
    
    public void setId(long nuevaid) {
    	this.id = nuevaid;
    }
    
    public void setIdPadre(long nuevopadre) {
        id_padre = nuevopadre;
    }
    
    public void setContenido(String cont) {
        contenido = cont;
    }

    public void setUsuarioDt(UsuarioDt userdt) {
        user = userdt;
    }

    public void setFecha(Date nuevaFecha) {
        fecha = nuevaFecha;
    }
    
    public void setHijos(List nuevosHijos) {
        hijos = nuevosHijos;
    }
    
}
