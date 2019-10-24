/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.controladores;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import logica.Categoria;
import logica.ListaDeReproduccion;
import logica.Video;
import logica.dt.CategoriaDt;


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
            
            if(em.find(Categoria.class, nombre) != null) throw new Exception("La categoria ya existe");
            Categoria c = new Categoria(nombre);
            em.persist(c);
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
            if(c == null) throw new Exception("La categoria no existe");
            
            query = em.createNamedQuery("ListaDeReproduccion.findByCategoria",ListaDeReproduccion.class).setParameter("categoria", c).getResultList();
            
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
        return query;
     }
     
     @Override
     public List<Video> obtenerVideosCategoria(String nom){
        List<Video> query = null;
        try {
            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();

            Categoria c = em.find(Categoria.class, nom);
            if(c == null) throw new Exception("La categoria no existe");
            
            query = em.createNamedQuery("Video.findByCategoria",Video.class).setParameter("categoria", nom).getResultList();

            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
        return query;
         
         
     }
}


