/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Esteban
 */
@Entity
@Table(name = "VALORACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Valoracion.findAll", query = "SELECT v FROM Valoracion v"),
    @NamedQuery(name = "Valoracion.findByUserId", query = "SELECT v FROM Valoracion v WHERE v.valoracionPK.userId = :userId"),
    @NamedQuery(name = "Valoracion.findByVideoId", query = "SELECT v FROM Valoracion v WHERE v.valoracionPK.videoId = :videoId"),
    @NamedQuery(name = "Valoracion.findByGustar", query = "SELECT v FROM Valoracion v WHERE v.gustar = :gustar")})
public class Valoracion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ValoracionPK valoracionPK;
    @Column(name = "GUSTAR")
    private Boolean gustar;
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "VIDEO_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Video video;

    public Valoracion() {
    }

    public Valoracion(ValoracionPK valoracionPK) {
        this.valoracionPK = valoracionPK;
    }

    public Valoracion(int userId, int videoId, boolean gustar) {
        this.valoracionPK = new ValoracionPK(userId, videoId);
        this.gustar = gustar;
    }

    public ValoracionPK getValoracionPK() {
        return valoracionPK;
    }

    public void setValoracionPK(ValoracionPK valoracionPK) {
        this.valoracionPK = valoracionPK;
    }

    public Boolean getGustar() {
        return gustar;
    }

    public void setGustar(Boolean gustar) {
        this.gustar = gustar;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (valoracionPK != null ? valoracionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Valoracion)) {
            return false;
        }
        Valoracion other = (Valoracion) object;
        if ((this.valoracionPK == null && other.valoracionPK != null) || (this.valoracionPK != null && !this.valoracionPK.equals(other.valoracionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "logica.Valoracion[ valoracionPK=" + valoracionPK + " ]";
    }
    
}
