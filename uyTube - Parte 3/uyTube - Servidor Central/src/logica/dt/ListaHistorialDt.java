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
            
    public ListaHistorialDt(Usuario u, List<VisitaDt> visitas,int id) {
            //public ListaDeReproduccionDt(int id, String nombre, String tipo, boolean privada, String categoria, int usuario, Date fechaUV) {

        super(id,"Historial","particular",true,"Historial",id,null);
        this.userId = u.getId();
        this.visitas=visitas;
    }
    
    public ListaHistorialDt() {

    }
    
     public int getUserId() {
        return userId;
    }

    public void setUserId(Integer u) {
        this.userId = u;
    }
    
     public List<VisitaDt> getVisitas() {
        return visitas;
    }

    public void setVisitas(List<VisitaDt> visitas) {
        this.visitas = visitas;
    }
    
}
