/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.controladores;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
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
     public List<String> ListarCategorias(){
         List<String> list = null;
        try {
          
            EntityManager em = emFactory.createEntityManager();
            List categorias = em.createQuery("SELECT Nombre FROM Categorias c").getResultList();
            Iterator it = categorias.iterator();
            while(it.hasNext()) {
                Categoria c = (Categoria) it.next();
                list.add(c.getNombre());
            }
            em.getTransaction().commit();
            em.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
        return list;
}
     
     
     @Override
     public Categoria ConsultarCategorias(String c){
         Categoria dt = null;
        try {
            EntityManager em = emFactory.createEntityManager();
            TypedQuery<Categoria> query = em.createQuery("SELECT * FROM Categoria c WHERE c.Nombre = :c", Categoria.class);
            Categoria u = query.setParameter("Nombre", c).getSingleResult();
            dt = new Categoria(c);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
        return dt;
         
     }
}


