/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.controladores;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import logica.Categoria;
import logica.controladores.IControladorCategoria;
/**
 *
 * @author Xavel
 */
public class ControladorCategoria implements IControladorCategoria {
     private EntityManagerFactory emFactory;
     private ControladorCategoria cs = new ControladorCategoria();
     public ControladorCategoria() {
        emFactory = Persistence.createEntityManagerFactory("UyTubePU");
    }
    
     public List<Categoria> findCategoriaEntities(){
         return findCategoriaEntities(true, -1, -1);
     }
     
     public List<Categoria> findCategoriaEntities(int maxResults, int firstResult) {
         return findCategoriaEntities(false, -1, -1);
     }
     
     public List<Categoria> findCategoriaEntities(boolean all, int maxResults, int firstResult) {
         return findCategoriaEntities(all, -1, -1);
     }
     
     public void ListarPersona(JTable tabla){
         DefaultTableModel model;
         String [] titulo = {"Nombre"};
         model = new DefaultTableModel(null, titulo);
         
         List<Categoria> datos = cs.findCategoriaEntities();
         
         String [] datosCategoria = new String[0];
         for (Categoria cat : datos){
             datosCategoria[0] = cat.getNombre();
             model.addRow(datosCategoria);
         
}
     }
}


