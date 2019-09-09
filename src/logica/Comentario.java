/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Esteban
 */
@Entity
@Table(name = "COMENTARIO")
public class Comentario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "USER_ID")
    private Integer user_id;
    
    @Column(name = "VIDEO_ID")
    private Integer video_id;
    
    @Column(name = "FECHA")
    private Date fecha;
    
    @Column(name = "CONTENIDO")
    private String contenido;
    
    @JoinColumn(name = "ID_PADRE", referencedColumnName = "ID")
    @ManyToOne(optional = true) //puede ser comentario directo o respuesta
    private Comentario padre;
    
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;
    
    @JoinColumn(name = "VIDEO_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Video video;

    @OneToMany(cascade = CascadeType.ALL)
    private Collection<Comentario> hijos;
    
    public Comentario() {
    }
    
    public Comentario(int user_id, int video_id, String contenido, Date fecha) {
        this.user_id = user_id;
        this.video_id = video_id;
        this.contenido = contenido;
        this.fecha = fecha;
        this.hijos = new ArrayList<>();
        this.padre = null;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public Comentario getPadre() {
        return padre;
    }

    public void setPadre(Comentario padre) {
        this.padre = padre;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
    
    public Collection<Comentario> getHijos() {
        return hijos;
    }

    public void setHijos(Collection<Comentario> hijos) {
        this.hijos = hijos;
    }
    
    public void agregarHijo(Comentario hijo) {
        hijos.add(hijo);
    }
    
    public void removerHijo(Comentario hijo) {
        hijos.remove(hijo);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comentario)) {
            return false;
        }
        Comentario other = (Comentario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "logica.Comentario[ id=" + id + " ]";
    }
    
}
