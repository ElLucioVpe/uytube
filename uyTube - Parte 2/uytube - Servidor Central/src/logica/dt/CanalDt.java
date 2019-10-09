/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dt;

import java.util.ArrayList;
import java.util.Collection;
import logica.Canal;
import logica.ListaDeReproduccion;
import logica.Usuario;
import logica.Video;

/**
 *
 * @author antus
 */
public class CanalDt {
private Integer userId;
private String nombre;
private String descripcion;
private Boolean privacidad;
private Usuario usuario;
private Collection<Video> videos;
private Collection<ListaDeReproduccion> listas;
private Collection<Usuario> seguidores;
 public CanalDt() {
     
 }
 
 public CanalDt(Integer userId, String nombre, Boolean privacidad, String descripcion) {
        this.userId = userId;
        this.nombre = nombre;
        this.privacidad = privacidad;
        this.descripcion  = descripcion;
        this.videos = new ArrayList<>();
        this.listas = new ArrayList<>();
        this.seguidores = new ArrayList<>();
    }
public CanalDt(Canal c) {
        this.userId = c.getUserId();
        this.nombre = c.getNombre();
        this.privacidad = c.getPrivacidad();
        this.videos = c.getVideos();
        this.listas = c.getListas();
        this.seguidores = c.getSeguidores();
        this.descripcion  = c.getDescripcion();
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

    public void setPrivacidad(Boolean privacidad) {
        this.privacidad = privacidad;
    }

    public Usuario getUsuario() {
        return usuario;
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