/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dt;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import logica.Comentario;
import logica.Valoracion;
import logica.Video;

/**
 *
 * @author antus
 */
public class VideoDt {
    private Integer id;
    private String nombre;
    private float duracion;
    private String url;
    private String descripcion;
    private Date fechaPublicacion;
    private String categoria;
    private Boolean privacidad;
    private Collection<Valoracion> valoraciones;
    private int likes;
    private int dislikes;

    public VideoDt() {
    }

    public VideoDt(Integer id) {
        this.id = id;
    }
    
    public VideoDt(Video v){
        this.id= v.getId();
        this.nombre = v.getNombre();
        this.duracion = v.getDuracion();
        this.url = v.getUrl();
        this.descripcion = v.getDescripcion();
        this.fechaPublicacion = v.getFechaPublicacion();
        this.privacidad  = v.getPrivacidad();
        this.valoraciones = v.getValoraciones();
        this.categoria = v.getCategoria();
        //por cosas de la vida asi se queda
        this.likes = v.getLikes();
        this.dislikes = v.getDislikes();
    }

    public Integer getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public float getDuracion() {
        return duracion;
    }

    public String getUrl() {
        return url;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public Boolean getPrivacidad() {
        return privacidad;
    }
    
    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }
    
    public Collection<Valoracion> getValoraciones() {
        return valoraciones;
    }
}