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
            
            EntityManager emanager = emFactory.createEntityManager();
            emanager.getTransaction().begin();
            
            if(emanager.find(Categoria.class, nombre) != null) throw new Exception("La categoria ya existe");
            Categoria cat = new Categoria(nombre);
            emanager.persist(cat);
            emanager.getTransaction().commit();
            emanager.close();
            
            JOptionPane.showMessageDialog(null,"La categoría se registro con exito");
        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
    }
     
    
     @Override
     public List<CategoriaDt> ListarCategorias(){
         List<CategoriaDt> list = new ArrayList<>();
        try {
          
            EntityManager emanager = emFactory.createEntityManager();
            List<Categoria> categorias = emanager.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
            for(int i=0;i < categorias.size(); i++) {
                list.add(new CategoriaDt(categorias.get(i)));
            }
            emanager.close();

        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
        return list;
}
     
     
     @Override
     public CategoriaDt ConsultarCategorias(String Nombre){
        CategoriaDt cdt = null;
        try {
            EntityManager emanager = emFactory.createEntityManager();
            
            Categoria cat = emanager.find(Categoria.class, Nombre);
            if(cat == null) throw new Exception("La categoria no existe");
            
            cdt = new CategoriaDt(cat.getNombre());
        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
        return cdt;
     }
     
     @Override
     public List<ListaDeReproduccion> obtenerListasCategoria(String nom){
        List<ListaDeReproduccion> query = null;
        try {
            EntityManager emanager = emFactory.createEntityManager();
            emanager.getTransaction().begin();

            Categoria cat = emanager.find(Categoria.class, nom);
            if(cat == null) throw new Exception("La categoria no existe");
            
            query = emanager.createNamedQuery("ListaDeReproduccion.findByCategoria",ListaDeReproduccion.class).setParameter("categoria", cat).getResultList();
            
            emanager.getTransaction().commit();
            emanager.close();
        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
        return query;
     }
     
     @Override
     public List<Video> obtenerVideosCategoria(String nom){
        List<Video> query = null;
        try {
            EntityManager emanager = emFactory.createEntityManager();
            emanager.getTransaction().begin();

            Categoria cat = emanager.find(Categoria.class, nom);
            if(cat == null) throw new Exception("La categoria no existe");
            
            query = emanager.createNamedQuery("Video.findByCategoria",Video.class).setParameter("categoria", nom).getResultList();

            emanager.getTransaction().commit();
            emanager.close();
        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
        return query;
     }
     
    private void exceptionAux(String inv, Exception exc){
        if(!inv.endsWith("_jsp.java")){
            JOptionPane.showMessageDialog(null," Error: "+exc.getMessage());
        } else {
            System.out.println("Error: "+exc.getMessage());
        }
    }
    
    //Para la web
    
    @Override
    public List<ListaDeReproduccionDt> obtenerListasDtCategoria(String nom){
        List<ListaDeReproduccionDt> retorno = new ArrayList<>();
        try {
            EntityManager emanager = emFactory.createEntityManager();
            
            Categoria cat = emanager.find(Categoria.class, nom);
            if(cat == null) throw new Exception("La categoria no existe");
            
            List<ListaDeReproduccion> lista = this.obtenerListasCategoria(nom);
            
            for(int i=0; i < lista.size(); i++) {
                ListaDeReproduccion lst = lista.get(i);
                //Reviso su tipo
                String tipo = "Particular";
                if(emanager.find(ListaDeReproduccion_PorDefecto.class, lst.getNombre()) != null) tipo = "Por Defecto";
                //Reviso su categoria
                String categoria = "Ninguna";
                if(lst.getCategoria() != null) categoria = lst.getCategoria().getNombre();
                //Reviso fecha del ultimo video
                Date _date = fechaUltimoVideo(lst.getVideos());
                //Creo el datatype
                retorno.add(new ListaDeReproduccionDt(
                    lst.getId(), 
                    lst.getNombre(), 
                    tipo, 
                    lst.getPrivada(), 
                    categoria,
                    lst.getUsuario().getId(),
                    _date
                ));
            }
            emanager.close();
        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
        return retorno;
    }
    
    @Override
    public List<VideoDt> obtenerVideosDtCategoria(String nom) {
        List<VideoDt> retorno = new ArrayList<>();
        try {
            EntityManager emanager = emFactory.createEntityManager();
            
            Categoria cat = emanager.find(Categoria.class, nom);
            if(cat == null) throw new Exception("La categoria no existe");
            
            List<Video> lista = this.obtenerVideosCategoria(nom);

            for(int i=0; i < lista.size(); i++) {
                retorno.add(new VideoDt(lista.get(i)));
            }
            emanager.close();
        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
        return retorno;
    }
    
    private Date fechaUltimoVideo(Collection<Video> videos) {
        Date retorno = null;
        
        if(videos != null) {
            Iterator<Video> iter = videos.iterator();
            
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date ultima = sdf.parse("1990-01-01");
                
                while(iter.hasNext()) {
                    Video aux = iter.next();
                    if(aux.getFechaPublicacion().after(ultima)) ultima = aux.getFechaPublicacion();
                }
                retorno = ultima;
            } catch (Exception exc) {
                Throwable _throwable = new Throwable();
                StackTraceElement[] elements = _throwable.getStackTrace();
                String invocador = elements[1].getFileName();
                exceptionAux(invocador, exc);
            }
        }
        return retorno;
    }
}


