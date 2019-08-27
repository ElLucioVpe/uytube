/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Esteban
 */
@Entity
@Table(name = "USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByNickname", query = "SELECT u FROM Usuario u WHERE u.nickname = :nickname")
    , @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "Usuario.findByApellido", query = "SELECT u FROM Usuario u WHERE u.apellido = :apellido")
    , @NamedQuery(name = "Usuario.findByMail", query = "SELECT u FROM Usuario u WHERE u.mail = :mail")
    , @NamedQuery(name = "Usuario.findByFechanac", query = "SELECT u FROM Usuario u WHERE u.fechanac = :fechanac")
    , @NamedQuery(name = "Usuario.findByImagen", query = "SELECT u FROM Usuario u WHERE u.imagen = :imagen")})
public class Usuario implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NICKNAME")
    private String nickname;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "APELLIDO")
    private String apellido;
    @Column(name = "MAIL")
    private String mail;
    @Column(name = "FECHANAC")
    @Temporal(TemporalType.DATE)
    private Date fechanac;
    @Basic(optional = false)
    @Column(name = "IMAGEN")
    private String imagen;

    public Usuario() {
    }

    public Usuario(String nickname) {
        this.nickname = nickname;
    }

    public Usuario(String nickname, String imagen) {
        this.nickname = nickname;
        this.imagen = imagen;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        String oldNickname = this.nickname;
        this.nickname = nickname;
        changeSupport.firePropertyChange("nickname", oldNickname, nickname);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        String oldNombre = this.nombre;
        this.nombre = nombre;
        changeSupport.firePropertyChange("nombre", oldNombre, nombre);
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        String oldApellido = this.apellido;
        this.apellido = apellido;
        changeSupport.firePropertyChange("apellido", oldApellido, apellido);
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        String oldMail = this.mail;
        this.mail = mail;
        changeSupport.firePropertyChange("mail", oldMail, mail);
    }

    public Date getFechanac() {
        return fechanac;
    }

    public void setFechanac(Date fechanac) {
        Date oldFechanac = this.fechanac;
        this.fechanac = fechanac;
        changeSupport.firePropertyChange("fechanac", oldFechanac, fechanac);
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        String oldImagen = this.imagen;
        this.imagen = imagen;
        changeSupport.firePropertyChange("imagen", oldImagen, imagen);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nickname != null ? nickname.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.nickname == null && other.nickname != null) || (this.nickname != null && !this.nickname.equals(other.nickname))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "logica.Usuario[ nickname=" + nickname + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
