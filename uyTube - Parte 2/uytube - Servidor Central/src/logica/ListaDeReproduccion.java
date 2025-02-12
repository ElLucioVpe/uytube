/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Esteban
 */
@Entity
@Table(name = "ListaDeReproduccion")
@NamedQueries({
@NamedQuery(name = "ListaDeReproduccion.findByCategoria", query = "SELECT l FROM ListaDeReproduccion l where l.categoria = :categoria")})
public class ListaDeReproduccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    
    @Column(name = "NOMBRE")
    private String nombre;
    
    @Column(name = "PRIVADA")
    private boolean privada;
    
    @JoinColumn(name = "CATEGORIA", referencedColumnName = "NOMBRE")
    @ManyToOne
    private Categoria categoria;
    
    @JoinColumn(name = "ID_PROPIETARIO", referencedColumnName = "ID", updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;
    
    @ManyToMany(cascade = CascadeType.ALL)
    private Collection<Video> videos;
    
    public ListaDeReproduccion(){
    }
    
    public ListaDeReproduccion(String nombre, Usuario u, boolean privada) {
        this.nombre = nombre;
        this.usuario = u;
        this.privada = privada;
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
    
    public boolean getPrivada() {
        return privada;
    }

    public void setPrivada(boolean privada) {
        this.privada = privada;
    }
    
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Collection<Video> getVideos() {
        return videos;
    }

    public void setVideos(Collection<Video> videos) {
        this.videos = videos;
    }
    
    public void agregarVideo(Video v) {
        this.videos.add(v);
    }
    
    public void quitarVideo(int id_v) {
        Iterator it = videos.iterator();
        boolean seguir = true;
        
        while(it.hasNext() && seguir) {
            Video v = (Video) it.next();
            if(v.getId() == id_v) { 
                videos.remove(v);
                seguir = false;
            }
        }
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
        if (!(object instanceof ListaDeReproduccion)) {
            return false;
        }
        ListaDeReproduccion other = (ListaDeReproduccion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "logica.ListaDeReproduccion[ id=" + id + " ]";
    }
    
}
