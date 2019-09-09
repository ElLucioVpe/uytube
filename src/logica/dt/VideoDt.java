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
    private int duracion;
    private String url;
    private String descripcion;
    private Date fechaPublicacion;
    private String categoria;
    private Boolean privacidad;
    private Integer canal_user_id;
    private Collection<Valoracion> valoraciones;
    private Collection<Comentario> comentarios;


  public VideoDt() {
    }

    public VideoDt(Integer id) {
        this.id = id;
    }

    public VideoDt(String nombre, int duracion, String url,String Desc, Date fechaPublicacion, Boolean privacidad, int user) {
        //this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.url = url;
        this.descripcion=Desc;
        this.fechaPublicacion = fechaPublicacion;
        this.privacidad = privacidad;
        this.canal_user_id=user;
          

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
}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Boolean getPrivacidad() {
        return privacidad;
    }

    public void setPrivacidad(Boolean privacidad) {
        this.privacidad = privacidad;
    }
    
    public int getLikes() {
        int likes = 0;
        Iterator<Valoracion> it = valoraciones.iterator();
        
        while(it.hasNext()) {
            if(it.next().getGustar()) likes++;
        }
        return likes;
    }

    public int getDislikes() {
        int dislikes = 0;
        Iterator<Valoracion> it = valoraciones.iterator();
        
        while(it.hasNext()) {
            if(!it.next().getGustar()) dislikes++;
        }
        return dislikes;
    }
     public void agregarValoracion(Valoracion v) {
        Valoracion opuesta =  new Valoracion(v.getUsuario().getId(), v.getVideo().getId(), !v.getGustar());
        if(valoraciones.contains(opuesta)) eliminarValoracion(opuesta);
        
        if(!valoraciones.contains(v)) valoraciones.add(v);
    }
    
    public void eliminarValoracion(Valoracion v) {
        this.valoraciones.remove(v);
    }
    
    public void agregarComentario(Comentario c) {
        this.comentarios.add(c);
    }
    public Collection<Valoracion> getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(Collection<Valoracion> valoracionCollection) {
        this.valoraciones = valoracionCollection;
    }
    }