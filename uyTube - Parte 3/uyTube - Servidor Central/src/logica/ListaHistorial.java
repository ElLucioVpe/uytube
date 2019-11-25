/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author antus
 */
 
@Entity
@Table(name = "LISTAHISTORIAL")
@XmlRootElement
public class ListaHistorial extends ListaDeReproduccion {
    
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<Visita> visitas;

    public ListaHistorial(Usuario u, Collection<Visita> visitas) {
        super("Historial", u, true);
        this.visitas = visitas;
    }
    public ListaHistorial(Usuario u, Visita visitas) {
        super("Historial", u, true);
        this.visitas.add(visitas);
    }
    
    public void agregarVisita(Visita v){
        this.visitas.add(v);
    }
    public void actualizar(Integer id_video, Integer id_usuario){
        
    }
    
   
    public Collection<Visita> getVisitas() {
        return visitas;
    }

    public void setVisitas(Collection<Visita> visitas) {
        this.visitas = visitas;
    }
    
    
    
}

