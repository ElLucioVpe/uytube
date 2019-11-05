/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Esteban
 */
@Entity
@XmlRootElement
public class ListaDeReproduccion_PorDefecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String nombre;

    public ListaDeReproduccion_PorDefecto() {
    }
    
    public ListaDeReproduccion_PorDefecto(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombre != null ? nombre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the nombre fields are not set
        if (!(object instanceof ListaDeReproduccion_PorDefecto)) {
            return false;
        }
        ListaDeReproduccion_PorDefecto other = (ListaDeReproduccion_PorDefecto) object;
        if ((this.nombre == null && other.nombre != null) || (this.nombre != null && !this.nombre.equals(other.nombre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "logica.ListaDeReproduccion_PorDefecto[ id=" + nombre + " ]";
    }
    
}
