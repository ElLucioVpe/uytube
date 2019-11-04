/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dt;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.xml.bind.annotation.XmlRootElement;

import logica.Canal;
import logica.ListaDeReproduccion;
import logica.Usuario;
import logica.Video;

/**
 *
 * @author antus
 */
@XmlRootElement
public class CanalDt {
    
    private Integer userId;
    private String nombre;
    private String descripcion;
    private Boolean privacidad;
    private String categoria;
    private Usuario usuario;
    private Collection<Video> videos;
    private Collection<ListaDeReproduccion> listas;
    private Collection<Usuario> seguidores;
    private Date fechaUV; //Fecha ultimo video

    public CanalDt() {

    }
 
    public CanalDt(Integer userId, String nombre, Boolean privacidad, String categoria, String descripcion) {
        this.userId = userId;
        this.nombre = nombre;
        this.privacidad = privacidad;
        this.categoria = categoria;
        this.descripcion  = descripcion;
        this.videos = new ArrayList<>();
        this.listas = new ArrayList<>();
        this.seguidores = new ArrayList<>();
    }
    
    public CanalDt(Canal c) {
        this.userId = c.getUserId();
        this.nombre = c.getNombre();
        this.privacidad = c.getPrivacidad();
        this.categoria = "Ninguna";
        if(c.getCategoria() != null) this.categoria = c.getCategoria().getNombre();
        this.videos = c.getVideos();
        this.listas = c.getListas();
        this.seguidores = c.getSeguidores();
        this.descripcion  = c.getDescripcion();
        this.fechaUV = fechaUltimoVideo();
    }

    private Date fechaUltimoVideo() {
        Date retorno = null;
        
        if(videos != null) {
            Iterator<Video> it = videos.iterator();
            
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date ultima = sdf.parse("1990-01-01");
                
                while(it.hasNext()) {
                    Video aux = it.next();
                    if(aux.getFechaPublicacion().after(ultima)) ultima = aux.getFechaPublicacion();
                }
                retorno = ultima;
            }catch(Exception e){}
        }
        return retorno;
    }
    
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getPrivacidad() {
        return privacidad;
    }
    
    public String getCategoria() {
        return categoria;
    }

    public void setPrivacidad(Boolean privacidad) {
        this.privacidad = privacidad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Date getFechaUV() {
        return fechaUV;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Collection<Usuario> getSeguidores(){
        return seguidores;
    }
    
    public void setSeguidores(Collection<Usuario> s){
        seguidores = s;
    
}
 public Collection<Video> getVideos() {
        return videos;
    }
    
    public Collection<ListaDeReproduccion> getListas() {
        return listas;
    }

    public void setListas(Collection<ListaDeReproduccion> listas) {
        this.listas = listas;
    }

    public void addLista(ListaDeReproduccion nuevalista) {
        this.listas.add(nuevalista);
    }
    
    public void setVideos(Collection<Video> v){
        videos = v;
    
}
   
    
}