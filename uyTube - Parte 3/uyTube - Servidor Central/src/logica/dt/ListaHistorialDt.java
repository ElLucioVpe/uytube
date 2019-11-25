/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dt;

import java.util.Date;
import java.util.List;
import logica.Usuario;
import logica.Visita;

/**
 *
 * @author Xavel
 */
public class ListaHistorialDt extends ListaDeReproduccionDt{
    private Integer userId;
    private List<VisitaDt> visitas;
            
    public ListaHistorialDt(Integer id, Usuario u, String nombre, String categoria, List<VisitaDt> visitas, Date fechaUV) {
        //public ListaDeReproduccionDt(int id, String nombre, String tipo, boolean privada, String categoria, int usuario, Date fechaUV) {

        super(id, "Historial", "Automatica", true, "Ninguna", u.getId(), fechaUV);
        this.userId = u.getId();
        visitas.sort((v1, v2) -> v1.getCantidad().compareTo(v2.getCantidad())); //Ordeno
        this.visitas = visitas;
    }
    
    public ListaHistorialDt() {

    }
    
     public int getUserId() {
        return userId;
    }

    public void setUserId(Integer uid) {
        this.userId = uid;
    }
    
     public List<VisitaDt> getVisitas() {
        return visitas;
    }

    public void setVisitas(List<VisitaDt> visitas) {
        this.visitas = visitas;
    }
    
}
