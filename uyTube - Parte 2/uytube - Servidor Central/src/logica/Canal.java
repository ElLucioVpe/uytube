/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Esteban
 */
@Entity
@Table(name = "CANAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Canal.findAll", query = "SELECT c FROM Canal c"),
    @NamedQuery(name = "Canal.findByUserId", query = "SELECT c FROM Canal c WHERE c.userId = :userId"),
    @NamedQuery(name = "Canal.findByNombre", query = "SELECT c FROM Canal c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Canal.findByDescripcion", query = "SELECT c FROM Canal c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Canal.findByPrivacidad", query = "SELECT c FROM Canal c WHERE c.privacidad = :privacidad"),
})
public class Canal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Basic(optional = false)
    @Column(name = "PRIVACIDAD")
    private Boolean privacidad;
    
    @JoinColumn(name = "CATEGORIA", referencedColumnName = "NOMBRE")
    @ManyToOne
    private Categoria categoria;

    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.ALL)
    private Collection<Video> videos;
    
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<ListaDeReproduccion> listas;

    @ManyToMany(mappedBy="suscripciones")
    private Collection<Usuario> seguidores;

    public Canal() {
    }

    public Canal(Integer userId) {
        this.userId = userId;
    }

    public Canal(Integer userId, String nombre, Boolean privacidad) {
        this.userId = userId;
        this.nombre = nombre;
        this.privacidad = privacidad;
        this.videos = new ArrayList<>();
        this.listas = new ArrayList<>();
        this.seguidores = new ArrayList<>();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getPrivacidad() {
        return privacidad;
    }

    public void setPrivacidad(Boolean privacidad) {
        this.privacidad = privacidad;
    }
    
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria cat) {
        this.categoria = cat;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Collection<Usuario> getSeguidores(){
        return seguidores;
    }
    
    public void setSeguidores(Collection<Usuario> s){
        seguidores = s;
    }
    
    public void agregarSeguidor(Usuario u) {
        this.seguidores.add(u);
    }
    
    public void eliminarSeguidor(Usuario u) {
        this.seguidores.remove(u);
    }
    
    public void agregarVideo(Video v) {
        this.videos.add(v);
    }
    
    public Video obtenerVideo(String nombre) {
        Video v = null;
        
        Iterator it = videos.iterator();
        boolean seguir = true;
        
        while(it.hasNext() && seguir) {
            Video auxv = (Video) it.next();
            if(auxv.getNombre().equals(nombre)) {
                v = auxv;
                seguir = false;
            }
        }
        return v;
    }
    
    public Collection<Video> getVideos() {
        return videos;
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
    
    public void agregarVideoLista(Video v, String nomlista) {
        Iterator it = listas.iterator();
        boolean seguir = true;
        
        while(it.hasNext() && seguir) {
            ListaDeReproduccion l = (ListaDeReproduccion) it.next();
            if(l.getNombre().equals(nomlista)) {
                l.agregarVideo(v);
                seguir = false;
            }
        }
    }
    
    public void quitarVideoLista(int video, String nomlista) {
        Iterator it = listas.iterator();
        boolean seguir = true;
        
        while(it.hasNext() && seguir) {
            ListaDeReproduccion l = (ListaDeReproduccion) it.next();
            if(l.getNombre().equals(nomlista)) {
                l.quitarVideo(video);
                seguir = false;
            } 
        }
    }
    
    public ListaDeReproduccion getLista(String nom) {
        Iterator it = listas.iterator();
        boolean seguir = true;
        ListaDeReproduccion retorno = null;
        
        while(it.hasNext() && seguir) {
            ListaDeReproduccion l = (ListaDeReproduccion) it.next();
            if(l.getNombre().equals(nom)) {
                retorno = l;
                seguir = false;
            } 
        }
        return retorno;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Canal)) {
            return false;
        }
        Canal other = (Canal) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "logica.Canal[ userId=" + userId + " ]";
    }

}
