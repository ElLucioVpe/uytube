/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dt;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.xml.bind.annotation.XmlType;

import logica.Canal;
import logica.Usuario;

/**
 *
 * @author antus
 */
@XmlType
public class CanalDt {
    
    private Integer userId;
    private String nombre;
    private String descripcion;
    private Boolean privacidad;
    private String categoria;
    private Usuario usuario;
    private List<VideoDt> videos;
    private List<ListaDeReproduccionDt> listas;
    private List<String> seguidores;
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
    
    public CanalDt(Canal c, List<VideoDt> videos, List<ListaDeReproduccionDt> listas, List<String> seguidores) {
        this.userId = c.getUserId();
        this.nombre = c.getNombre();
        this.privacidad = c.getPrivacidad();
        this.categoria = "Ninguna";
        if(c.getCategoria() != null) this.categoria = c.getCategoria().getNombre();
        this.videos = videos;
        this.listas = listas;
        this.seguidores = seguidores;
        this.descripcion  = c.getDescripcion();
        this.fechaUV = fechaUltimoVideo();
    }

    private Date fechaUltimoVideo() {
        Date retorno = null;
        
        if(videos != null) {
            Iterator<VideoDt> it = videos.iterator();
            
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date ultima = sdf.parse("1990-01-01");
                
                while(it.hasNext()) {
                    VideoDt aux = it.next();
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
    
    public String getNombre() {
        return nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public Boolean getPrivacidad() {
        return privacidad;
    }
    
    public String getCategoria() {
        return categoria;
    }
    
    public Date getFechaUV() {
        return fechaUV;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public List<String> getSeguidores(){
        return seguidores;
    }
    
    public List<VideoDt> getVideos() {
        return videos;
    }
    
    public List<ListaDeReproduccionDt> getListas() {
        return listas;
    }

    /****************************************
     * Estos son sets que sin estar SOAP 
     * no reconoce las variables.
     *     
     ****************************************/
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrivacidad(Boolean privacidad) {
        this.privacidad = privacidad;
    }

    public void setCategoria(String cat) {
        this.categoria = cat;
    }
  
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setSeguidores(List<String> seg){
        seguidores = seg;
    
    }

    public void setVideos(List<VideoDt> vids){
        videos = vids;
    }

    public void setListas(List<ListaDeReproduccionDt> listas) {
        this.listas = listas;
    }

    public void setFechaUV(Date fecha) {
        fechaUV = fecha;
    }
    
    public void addLista(ListaDeReproduccionDt nuevalista) {
        this.listas.add(nuevalista);
    }

}