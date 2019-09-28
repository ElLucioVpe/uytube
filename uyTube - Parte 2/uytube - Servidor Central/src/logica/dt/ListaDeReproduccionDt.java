/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dt;

/**
 *
 * @author Esteban
 */
public class ListaDeReproduccionDt {
    private int id;
    private String nombre;
    private boolean privada;
    private String tipo;
    private String categoria;
    private int user_id;

    public ListaDeReproduccionDt(){
    }

    public ListaDeReproduccionDt(int id, String nombre, String tipo, boolean privada, String categoria, int usuario) {
        this.id = id;
        this.nombre = nombre;
        this.privada = privada;
        this.tipo = tipo;
        this.categoria = categoria;
        this.user_id = usuario;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean getPrivada() {
        return privada;
    }

    public String getTipo() {
        return tipo;
    }
    
    public String getCategoria() {
        return categoria;
    }

    public int getIdUsuario() {
        return user_id;
    }
}