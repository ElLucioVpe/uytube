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
import java.util.Iterator;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "Usuario.findByApellido", query = "SELECT u FROM Usuario u WHERE u.apellido = :apellido"),
    @NamedQuery(name = "Usuario.findByNickname", query = "SELECT u FROM Usuario u WHERE u.nickname = :nickname"),
    @NamedQuery(name = "Usuario.findByMail", query = "SELECT u FROM Usuario u WHERE u.mail = :mail"),
    @NamedQuery(name = "Usuario.findByFechanac", query = "SELECT u FROM Usuario u WHERE u.fechanac = :fechanac"),
    @NamedQuery(name = "Usuario.findByImagen", query = "SELECT u FROM Usuario u WHERE u.imagen = :imagen")})
public class Usuario implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<Valoracion> valoracionCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDO")
    private String apellido;

    @Basic(optional = false)
    @Column(name = "NICKNAME")
    private String nickname;

    @Basic(optional = false)
    @Column(name = "MAIL")
    private String mail;

    @Basic(optional = false)
    @Column(name = "FECHANAC")
    @Temporal(TemporalType.DATE)
    private Date fechanac;

    @Column(name = "IMAGEN")
    private String imagen;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Canal canal;

    @OneToMany(cascade = CascadeType.ALL)
    private Collection<ListaDeReproduccion> listas;

    @ManyToMany(cascade=CascadeType.PERSIST)
    @JoinTable(name="Canal_Usuario",  joinColumns={@JoinColumn(referencedColumnName="ID")}
    , inverseJoinColumns={@JoinColumn(referencedColumnName="USER_ID")})
    private Collection<Canal> suscripciones;

    @OneToMany(cascade = CascadeType.ALL)
    private Collection<Valoracion> valoraciones;

    public Usuario() {
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    public Usuario(String nickname, String nombre, String apellido, String mail, Date fechanac) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.fechanac = fechanac;
        this.listas = new ArrayList<>();
        this.valoraciones = new ArrayList<>();
        this.suscripciones = new ArrayList<>();
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getFechanac() {
        return fechanac;
    }

    public void setFechanac(Date fechanac) {
        this.fechanac = fechanac;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Canal getCanal() {
        return canal;
    }

    public void setCanal(Canal canal) {
        this.canal = canal;
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

    public boolean existeLista(String nom_lista) {
        boolean existe = false;
        Iterator<ListaDeReproduccion> it = listas.iterator();

        while(it.hasNext()){
            if(it.next().getNombre().equals(nom_lista)) existe = true;
        }

        return existe;
    }
    
    public void agregarSuscripcion(Canal c) {
        this.suscripciones.add(c);
    }
    
    public void eliminarSuscripcion(Canal c) {
        this.suscripciones.remove(c);
    }
    
    public void agregarValoracion(Valoracion v) {
        this.valoraciones.add(v);
    }
    
    public void eliminarValoracion(Valoracion v) {
        this.valoraciones.remove(v);
    }
    
    //public void modificarValoracion(Valoracion v)
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "logica.Usuario[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<Valoracion> getValoracionCollection() {
        return valoracionCollection;
    }

    public void setValoracionCollection(Collection<Valoracion> valoracionCollection) {
        this.valoracionCollection = valoracionCollection;
    }

}
