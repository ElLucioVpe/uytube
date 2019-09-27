/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dt;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import logica.Canal;
import logica.ListaDeReproduccion;
import logica.Usuario;
import logica.Valoracion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luciano
 */
public class UsuarioDt {
    private Integer id;
    private String nombre;
    private String apellido;
    private String nickname;
    private String mail;
    private Date fechanac;
    private String imagen;
    private Canal canal;
    private List<String> suscripciones;


    public UsuarioDt() {
    }

    public UsuarioDt(Integer id) {
        this.id = id;
    }

    public UsuarioDt(Integer id, String nickname, String nombre, String apellido, String mail, Date fechanac, String imagen, Canal canal, List<String> suscripciones) {
        this.id = id;
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.fechanac = fechanac;
        this.imagen = imagen;
        this.canal = canal;
    }
    
    public UsuarioDt(Usuario u) {
        this.id = u.getId();
        this.nickname = u.getNickname();
        this.nombre = u.getNombre();
        this.apellido = u.getApellido();
        this.mail = u.getMail();
        this.imagen = u.getImagen();
        this.fechanac = u.getFechanac();
        this.canal = u.getCanal();
    }
    
    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNickname() {
        return nickname;
    }

    public String getMail() {
        return mail;
    }
    
    public List<String> getSuscripciones() {
        return suscripciones;
    }
    
    public Date getFechanac() {
        return fechanac;
    }

    public String getImagen() {
        return imagen;
    }

    public Canal getCanal() {
        return canal;
    }
   
}
