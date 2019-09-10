/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.controladores;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import logica.Categoria;
import logica.ListaDeReproduccion;
import logica.Usuario;
import logica.dt.CategoriaDt;
import logica.controladores.IControladorCategoria;
import logica.dt.VideoDt;


/**
 *
 * @author Xavel
 */
public class ControladorCategoria implements IControladorCategoria {
     private EntityManagerFactory emFactory;
     //private ControladorCategoria cs = new ControladorCategoria();
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
     
     @Override
     public void AltaCategoria(String nombre){
          try {
            
            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();

            Categoria c = new Categoria(nombre);
            em.persist(c);
            em.merge(c);
            em.getTransaction().commit();
            em.close();
            
            JOptionPane.showMessageDialog(null,"La categoría se registro con exito");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
    }
     
    
     @Override
     public List<CategoriaDt> ListarCategorias(){
         List<CategoriaDt> list = new ArrayList<CategoriaDt>();
        try {
          
            EntityManager em = emFactory.createEntityManager();
            List<Categoria> categorias = em.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
            for(int i=0;i < categorias.size(); i++) {
                list.add(new CategoriaDt(categorias.get(i)));
            }
            em.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
        return list;
}
     
     
     @Override
     public CategoriaDt ConsultarCategorias(String Nombre){
         CategoriaDt dt = null;
        try {
            EntityManager em = emFactory.createEntityManager();
            TypedQuery<Categoria> query = em.createQuery("SELECT * FROM Categoria c WHERE c.nombre = :nombre", Categoria.class);
            Categoria c = query.setParameter("Nombre", Nombre).getSingleResult();
            dt = new CategoriaDt(c);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
        return dt;
         
     }
     @Override
     
     public List<ListaDeReproduccion> obtenerListasCategoria(String nom){
  
         List<ListaDeReproduccion> query = null;
        try {
            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();
            
           
            Categoria c = em.find(Categoria.class, nom);
           
            
           query = em.createNamedQuery("Listadereproduccion.findByCategoria",ListaDeReproduccion.class).setParameter("categoria", c).getResultList();
      
           
            
            em.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
        return query;
         
     }
}


