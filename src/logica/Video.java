/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Esteban
 */
@Entity
@Table(name = "VIDEO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Video.findAll", query = "SELECT v FROM Video v")
    , @NamedQuery(name = "Video.findById", query = "SELECT v FROM Video v WHERE v.id = :id")
    , @NamedQuery(name = "Video.findByNombre", query = "SELECT v FROM Video v WHERE v.nombre = :nombre")
    , @NamedQuery(name = "Video.findByDuracion", query = "SELECT v FROM Video v WHERE v.duracion = :duracion")
    , @NamedQuery(name = "Video.findByUrl", query = "SELECT v FROM Video v WHERE v.url = :url")
    , @NamedQuery(name = "Video.findByDescripcion", query = "SELECT v FROM Video v WHERE v.descripcion = :descripcion")
    , @NamedQuery(name = "Video.findByFechaPub", query = "SELECT v FROM Video v WHERE v.fechaPub = :fechaPub")})
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
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
    @Column(name = "FECHA_PUB")
    @Temporal(TemporalType.DATE)
    private Date fechaPub;

    public Video() {
    }

    public Video(Integer id) {
        this.id = id;
    }

    public Video(Integer id, String nombre, int duracion, String url, Date fechaPub) {
        this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.url = url;
        this.fechaPub = fechaPub;
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

    public Date getFechaPub() {
        return fechaPub;
    }

    public void setFechaPub(Date fechaPub) {
        this.fechaPub = fechaPub;
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
    
}
