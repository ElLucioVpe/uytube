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
    private Collection<ListaDeReproduccion> listas;
    private Collection<Canal> suscripciones;
    private Collection<Valoracion> valoraciones;


    public UsuarioDt() {
    }

    public UsuarioDt(Integer id) {
        this.id = id;
    }

    public UsuarioDt(String nickname, String nombre, String apellido, String mail, Date fechanac) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.fechanac = fechanac;
        this.listas = new ArrayList<>();
        this.valoraciones = new ArrayList<>();
        this.suscripciones = new ArrayList<>();
    }
    
    public UsuarioDt(Usuario u) {
        this.id = u.getId();
        this.nickname = u.getNickname();
        this.nombre = u.getNombre();
        this.apellido = u.getApellido();
        this.mail = u.getMail();
        this.imagen = u.getImagen();
        this.fechanac = u.getFechanac();
        this.valoraciones = u.getValoraciones();
        this.suscripciones = u.getSuscripciones();
        this.canal = u.getCanal();
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
    public Collection<Canal> getSuscripciones() {
        return suscripciones;
    }
    
    public Date getFechanac() {
        return fechanac;
    }

    public void setFechanac(Date fechanac) {
        this.fechanac = fechanac;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Canal getCanal() {
        return canal;
    }

    public void setCanal(Canal canal) {
        this.canal = canal;
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
    
    public void agregarSuscripcion(Canal c) {
        this.suscripciones.add(c);
    }
    
    public void eliminarSuscripcion(Canal c) {
        this.suscripciones.remove(c);
    }
}
