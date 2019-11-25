/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dt;

import logica.Video;

/**
 *
 * @author Xavel
 */
public class VisitaDt {
    
    Integer userId;
    Integer videoId;
    String fecha;
    Integer cantidad;
    Video vid;
    
        public VisitaDt() {

    }
        public VisitaDt(Integer userId, Integer videoId, String fecha, Integer cantidad) {
        this.userId = userId;
        this.videoId = videoId;
        this.fecha = fecha;
        this.cantidad = cantidad;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
     public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }
    
     public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
     public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
    
   

