/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dt;

import java.util.Date;
import logica.Canal;
import logica.Usuario;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luciano
 */

@XmlRootElement
public class UsuarioDt {
	
    private Integer id;
    private String nombre;
    private String password;
    private String apellido;
    private String nickname;
    private String mail;
    private Date fechanac;
    private String imagen;
    private Canal canal;
    private boolean activo;
    private List<String> suscripciones;


    public UsuarioDt() {
    }

    public UsuarioDt(Integer id) {
        this.id = id;
    }

    public UsuarioDt(Integer id, String pass, String nickname, String nombre, String apellido, String mail, Date fechanac, String imagen, Canal canal, boolean activo, List<String> suscripciones) {
        this.id = id;
        this.nickname = nickname;
        this.nombre = nombre;
        this.password = pass;
        this.apellido = apellido;
        this.mail = mail;
        this.fechanac = fechanac;
        this.imagen = imagen;
        this.canal = canal;
        this.activo = activo;
        this.suscripciones = suscripciones;
    }
    
    public UsuarioDt(Usuario u) {
        this.id = u.getId();
        this.nickname = u.getNickname();
        this.nombre = u.getNombre();
        this.password = u.getPassword();
        this.apellido = u.getApellido();
        this.mail = u.getMail();
        this.imagen = u.getImagen();
        this.fechanac = u.getFechanac();
        this.canal = u.getCanal();
        this.activo = u.getActivo();
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
    
    public boolean getActivo() {
    	return activo;
    }
    
    public String getPassword() {
        return password;
    }
    
    /****************************************
     * Estos son sets que sin estar SOAP 
     * no reconoce las variables.
     *     
     ****************************************/
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setNombre(String nom) {
    	this.nombre = nom;
    }
    
    public void setApellido(String apell) {
    	apellido = apell;
    }

    public void setNickname(String nick) {
        nickname = nick;
    }

    public void setMail(String nuevoMail) {
        mail = nuevoMail;
    }
    
    public void setSuscripciones(List<String> sus) {
        suscripciones = sus;
    }
    
    public void setFechanac(Date fecha) {
        fechanac = fecha;
    }

    public void setImagen(String nuevaImg) {
        imagen = nuevaImg;
    }

    public void setCanal(Canal nuevoCanal) {
        canal = nuevoCanal;
    }
    
    public void setActivo(boolean nuevoEstado) {
    	activo = nuevoEstado;
    }
    
    public void setPassword(String pass) {
        password = pass;
    }
}