/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dt;

import logica.Valoracion;

/**
 *
 * @author pagol
 */
public class valoracionDt {
     private String user;
     private boolean like;
     
    public valoracionDt() {     
    }
    
    public valoracionDt(Valoracion v) {
        this.user=v.getUsuario().getNickname();
        this.like=v.getGustar();
    }
     
    public String getUser() {
        return user;
    }

    public void setUser(String nombre) {
        this.user = nombre;
    }
    
    public boolean getGusto(){
        return like;
    }
    
    public void setGusto(boolean gusto){
        this.like=gusto;
    }

     
     
}
