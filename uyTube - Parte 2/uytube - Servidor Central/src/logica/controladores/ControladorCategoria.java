/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.controladores;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import logica.Categoria;
import logica.ListaDeReproduccion;
import logica.ListaDeReproduccion_PorDefecto;
import logica.Video;
import logica.dt.CategoriaDt;
import logica.dt.ListaDeReproduccionDt;
import logica.dt.VideoDt;


/**
 *
 * @author Xavel
 */
public class ControladorCategoria implements IControladorCategoria {
    
    private static ControladorCategoria instancia;
    private final EntityManagerFactory emFactory;
    
    private ControladorCategoria() {
        emFactory = Persistence.createEntityManagerFactory("UyTubePU");
    }
    
    public static ControladorCategoria getInstance() {
        if (instancia == null) {
            instancia = new ControladorCategoria();
        }
        return instancia;
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
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, e);
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
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, e);
        }
        return list;
}
     
     
     @Override
     public CategoriaDt ConsultarCategorias(String Nombre){
        CategoriaDt dt = null;
        try {
            EntityManager em = emFactory.createEntityManager();
            
            Categoria c = em.find(Categoria.class, Nombre);
            if(c == null) throw new Exception("La categoria no existe");
            
            dt = new CategoriaDt(c.getNombre());
        } catch (Exception e) {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, e);
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
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, e);
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
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, e);
        }
        return query;
     }
     
    private void exceptionAux(String inv, Exception e){
        if(!inv.endsWith("_jsp.java")){
            JOptionPane.showMessageDialog(null," Error: "+e.getMessage());
        } else {
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    //Para la web
    
    @Override
    public List<ListaDeReproduccionDt> obtenerListasDtCategoria(String nom){
        List<ListaDeReproduccionDt> retorno = new ArrayList<>();
        try {
            EntityManager em = emFactory.createEntityManager();
            
            Categoria c = em.find(Categoria.class, nom);
            if(c == null) throw new Exception("La categoria no existe");
            
            List<ListaDeReproduccion> lista = this.obtenerListasCategoria(nom);
            
            for(int i=0; i < lista.size(); i++) {
                ListaDeReproduccion l = lista.get(i);
                //Reviso su tipo
                String tipo = "Particular";
                if(em.find(ListaDeReproduccion_PorDefecto.class, l.getNombre()) != null) tipo = "Por Defecto";
                //Reviso su categoria
                String categoria = "Ninguna";
                if(l.getCategoria() != null) categoria = l.getCategoria().getNombre();
                //Reviso fecha del ultimo video
                Date d = fechaUltimoVideo(l.getVideos());
                //Creo el datatype
                retorno.add(new ListaDeReproduccionDt(
                    l.getId(), 
                    l.getNombre(), 
                    tipo, 
                    l.getPrivada(), 
                    categoria,
                    l.getUsuario().getId(),
                    d
                ));
            }
            em.close();
        } catch (Exception e) {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, e);
        }
        return retorno;
    }
    
    @Override
    public List<VideoDt> obtenerVideosDtCategoria(String nom) {
        List<VideoDt> retorno = new ArrayList<>();
        try {
            EntityManager em = emFactory.createEntityManager();
            
            Categoria c = em.find(Categoria.class, nom);
            if(c == null) throw new Exception("La categoria no existe");
            
            List<Video> lista = this.obtenerVideosCategoria(nom);

            for(int i=0; i < lista.size(); i++) {
                retorno.add(new VideoDt(lista.get(i)));
            }
            em.close();
        } catch (Exception e) {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, e);
        }
        return retorno;
    }
    
    private Date fechaUltimoVideo(Collection<Video> videos) {
        Date retorno = null;
        
        if(videos != null) {
            Iterator<Video> it = videos.iterator();
            
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date ultima = sdf.parse("1990-01-01");
                
                while(it.hasNext()) {
                    Video aux = it.next();
                    if(aux.getFechaPublicacion().after(ultima)) ultima = aux.getFechaPublicacion();
                }
                retorno = ultima;
            }catch(Exception e){}
        }
        return retorno;
    }
}


