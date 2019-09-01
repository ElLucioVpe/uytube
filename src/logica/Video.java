/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Esteban
 */
@Entity
@Table(name = "VIDEO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Video.findAll", query = "SELECT v FROM Video v"),
    @NamedQuery(name = "Video.findById", query = "SELECT v FROM Video v WHERE v.id = :id"),
    @NamedQuery(name = "Video.findByNombre", query = "SELECT v FROM Video v WHERE v.nombre = :nombre"),
    @NamedQuery(name = "Video.findByDuracion", query = "SELECT v FROM Video v WHERE v.duracion = :duracion"),
    @NamedQuery(name = "Video.findByUrl", query = "SELECT v FROM Video v WHERE v.url = :url"),
    @NamedQuery(name = "Video.findByDescripcion", query = "SELECT v FROM Video v WHERE v.descripcion = :descripcion"),
    @NamedQuery(name = "Video.findByFechaPublicacion", query = "SELECT v FROM Video v WHERE v.fechaPublicacion = :fechaPublicacion"),
    @NamedQuery(name = "Video.findByCategoria", query = "SELECT v FROM Video v WHERE v.categoria = :categoria"),
    @NamedQuery(name = "Video.findByPrivacidad", query = "SELECT v FROM Video v WHERE v.privacidad = :privacidad")})
public class Video implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "video")
    private Collection<Valoracion> valoracionCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "DURACION")
    private int duracion;
    @Basic(optional = false)
    @Column(name = "URL")
    private String url;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "FECHA_PUBLICACION")
    @Temporal(TemporalType.DATE)
    private Date fechaPublicacion;
    @Column(name = "CATEGORIA")
    private String categoria;
    @Basic(optional = false)
    @Column(name = "PRIVACIDAD")
    private Boolean privacidad;
    
    @Column(name = "CANAL_USER_ID")
    private Integer canal_user_id;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "CANAL_USER_ID", referencedColumnName = "USER_ID", insertable = false, updatable = false)
    private Canal canal;
    
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<Valoracion> valoraciones;

    public Video() {
    }

    public Video(Integer id) {
        this.id = id;
    }

    public Video(String nombre, int duracion, String url,String Desc, Date fechaPublicacion, Boolean privacidad, int user) {
        //this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.url = url;
        this.descripcion=Desc;
        this.fechaPublicacion = fechaPublicacion;
        this.privacidad = privacidad;
        this.canal_user_id=user;
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
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Video)) {
            return false;
        }
        Video other = (Video) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "logica.Video[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<Valoracion> getValoracionCollection() {
        return valoracionCollection;
    }

    public void setValoracionCollection(Collection<Valoracion> valoracionCollection) {
        this.valoracionCollection = valoracionCollection;
    }
    
}
