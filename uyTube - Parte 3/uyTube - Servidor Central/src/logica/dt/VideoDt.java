/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dt;

import java.util.Date;

import javax.xml.bind.annotation.XmlType;

import logica.Video;

/**
 *
 * @author antus
 */

@XmlType
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
    //private List<valoracionDt> valoraciones;
    private int likes;
    private int dislikes;
    private String thumbnail;
    private String embedded;

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
        //this.valoraciones = v.getValoraciones();
        this.canal_user_id = v.getIdUsuario();
        this.categoria = v.getCategoria();
        //por cosas de la vida asi se queda
        this.likes = v.getLikes();
        this.dislikes = v.getDislikes();
        
        String thumbnail = "";
        if(this.url.length() > 17) {
            if(this.url.contains("https://youtu.be/")) {
            	String video_id = url.substring(17);
                thumbnail = "https://img.youtube.com/vi/" + video_id + "/3.jpg";
            } else if (this.url.contains("https://www.youtube.com/")) {
            	String video_id = url.substring(32);
            	thumbnail = "https://img.youtube.com/vi/" + video_id + "/3.jpg";
            }
        }
        this.thumbnail = thumbnail;
        
        String embedded = "";
    	String video_id = "";
        if(this.url.length() > 17) {
            if(this.url.contains("https://youtu.be/")) {
                video_id = url.substring(17);
            } else if (this.url.contains("https://www.youtube.com/")) {
                video_id = url.substring(32);
            }
            embedded = "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/"+ video_id +"\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>";
        }
        this.embedded = embedded;
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
        return thumbnail;
    }
    
    public String getEmbedded() {
        return embedded;
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
    
    /****************************************
     * Estos son sets que sin estar SOAP 
     * no reconoce las variables.
     *     
     ****************************************/
    public void setId(Integer nuevaId) {
        id = nuevaId;
    }
    
    public void setIdCanal(int nuevaId) {
        canal_user_id = nuevaId;
    }
    
    public void setCodigo(String cod) {
        codigo = cod;
    }

    public void setNombre(String nom) {
    	this.nombre = nom;
    }
    
    public void setDuracion(float dur) {
        duracion = dur;
    }

    public void setUrl(String nuevaUrl) {
        url = nuevaUrl;
    }

    public void setDescripcion(String desc) {
        descripcion = desc;
    }

    public void setFechaPublicacion(Date fecha) {
        fechaPublicacion = fecha;
    }

    public void setCategoria(String cat) {
        categoria = cat;
    }

    public void setThumbnail(String value) {
        thumbnail = value;
    }
    
    public void setEmbedded(String value) {
        embedded = value;
    }
    
    public void setPrivacidad(Boolean estado) {
        privacidad = estado;
    }
    
    public void setLikes(int cant) {
        likes = cant;
    }

    public void setDislikes(int cant) {
        dislikes = cant;
    }
    
}