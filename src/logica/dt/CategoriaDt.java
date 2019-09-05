package logica.dt;

import logica.Categoria;

public class CategoriaDt {
     private String nombre;

public CategoriaDt() {
    }

    public CategoriaDt(String nombre) {
   
        this.nombre = nombre;
       
    }
    
    public CategoriaDt(Categoria c) {

        this.nombre = c.getNombre();

    }



    public String getNombre() {
        return nombre;
    }     
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}

