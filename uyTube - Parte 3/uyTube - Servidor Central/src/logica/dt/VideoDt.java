/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dt;

import java.util.Collection;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import logica.Valoracion;
import logica.Video;

/**
 *
 * @author antus
 */
@XmlRootElement
public class VideoDt {
    private Integer id;
    private String codigo;
    private String nombre;
    private float duracion;
    private String url;
    private String descripcion;
    private Date fechaPublicacion;
    private String categoria;
    private Boolean privacidad;
    private int canal_user_id;
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
        this.codigo = v.getCodigo();
        this.nombre = v.getNombre();
        this.duracion = v.getDuracion();
        this.url = v.getUrl();
        this.descripcion = v.getDescripcion();
        this.fechaPublicacion = v.getFechaPublicacion();
        this.privacidad  = v.getPrivacidad();
        this.valoraciones = v.getValoraciones();
        this.canal_user_id = v.getIdUsuario();
        this.categoria = v.getCategoria();
        //por cosas de la vida asi se queda
        this.likes = v.getLikes();
        this.dislikes = v.getDislikes();
    }

    public Integer getId() {
        return id;
    }
    
    public int getIdCanal() {
        return canal_user_id;
    }
    
    public String getCodigo() {
        return codigo;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nom) {
    	this.nombre = nom;
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
    
    public String getThumbnail() {
        String _url = "";
        if(this.url.length() > 17) {
            if(this.url.contains("https://youtu.be/")) {
            	String video_id = url.substring(17);
                _url = "https://img.youtube.com/vi/" + video_id + "/3.jpg";
            } else if (this.url.contains("https://www.youtube.com/")) {
            	String video_id = url.substring(32);
                _url = "https://img.youtube.com/vi/" + video_id + "/3.jpg";
            }
        }
        return _url;
    }
    
    public String getEmbedded() {
    	String _url = "";
    	String video_id = "";
        if(this.url.length() > 17) {
            if(this.url.contains("https://youtu.be/")) {
                video_id = url.substring(17);
            } else if (this.url.contains("https://www.youtube.com/")) {
                video_id = url.substring(32);
            }
            _url = "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/"+ video_id +"\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>";
        }
        return _url;
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