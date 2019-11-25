/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author antus
 */
@Entity
@Table(name = "VISITA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Visita.findVisita", query = "SELECT v FROM Visita v WHERE v.userId = :userId AND v.videoId = :videoId"),
})
//public class Canal  {
public class Visita implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "USER_ID")
    private Integer userId;
    @Id
    @Basic(optional = false)
    @Column(name = "VIDEO_ID") 
    private Integer videoId;
     
    @Column(name = "FECHA")
    private Date fecha;
    @Column(name = "CANTIDAD")
    private Integer cantidad;
    
    @JoinColumn(name = "VIDEO_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)    
    private Video video;
     @JoinColumn(name = "USER_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)    
    private Usuario usuario;
    
    public Visita() {
    } 
    
    public Visita(Integer userId, Integer videoId) {
         this.userId = userId;
        this.videoId = videoId;
    }
    
    public Visita(Integer userId, Integer videoId, Date fecha, Integer cantidad, Video vid, Usuario user) {
        this.userId = userId;
        this.videoId = videoId;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.video = vid;
        this.usuario = user;
    }
    
   // @JoinColumn(name = "USER_ID", referencedColumnName = "ID", insertable = false, updatable = false)
   // @OneToOne(optional = false)
   // private Usuario usuario;
   // @JoinColumn(name = "VIDEO_ID", referencedColumnName = "ID", insertable = false, updatable = false)
   // @OneToOne(optional = false)
   // private Video video;

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
    
    
    public void actualizarVisita() {
	   this.cantidad++;
	   this.fecha = new Date();
    }
    
    
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
     
}
