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
import logica.Usuario;
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
     
    @Override
    public void AltaCategoria(String nombre){
    	EntityManager emanager = emFactory.createEntityManager();
    	try {
            emanager.getTransaction().begin();
            
            if(emanager.find(Categoria.class, nombre) != null) throw new Exception("La categoria ya existe");
            Categoria cat = new Categoria(nombre);
            emanager.persist(cat);
            
        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
    	emanager.getTransaction().commit();
        emanager.close();
    }
     
    
     @Override
     public List<CategoriaDt> ListarCategorias(){
         List<CategoriaDt> list = new ArrayList<>();
         EntityManager emanager = emFactory.createEntityManager();
         try {
        	 emanager.getTransaction().begin();
        	 List<Categoria> categorias = emanager.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
        	 for(int i=0;i < categorias.size(); i++) {
        		 list.add(new CategoriaDt(categorias.get(i)));
        	 }
        	 
         } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
         }
         emanager.getTransaction().commit();
         emanager.close();
         return list;
}
     
     
     @Override
     public CategoriaDt ConsultarCategorias(String Nombre){
        CategoriaDt cdt = null;
        EntityManager emanager = emFactory.createEntityManager();
        try {
            
            emanager.getTransaction().begin();
            Categoria cat = emanager.find(Categoria.class, Nombre);
            if(cat == null) throw new Exception("La categoria no existe");
            
            cdt = new CategoriaDt(cat.getNombre());
        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
        emanager.getTransaction().commit();
        emanager.close();
        return cdt;
     }
     
     @Override
     public List<ListaDeReproduccion> obtenerListasCategoria(String nom){
        List<ListaDeReproduccion> query = null;
        EntityManager emanager = emFactory.createEntityManager();
        try {
            emanager.getTransaction().begin();

            Categoria cat = emanager.find(Categoria.class, nom);
            if(cat == null) throw new Exception("La categoria no existe");
            
            List<ListaDeReproduccion> aux = emanager.createNamedQuery("ListaDeReproduccion.findByCategoria",ListaDeReproduccion.class).setParameter("categoria", cat).getResultList();
            
            query = new ArrayList<>();
            //Quito las listas de los usuarios inactivos
            for(int i=0; i < aux.size(); i++) {
            	ListaDeReproduccion lst = aux.get(i);
            	if(lst.getUsuario().getActivo()) query.add(lst);
            }
        
        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
        emanager.getTransaction().commit();
        emanager.close();
        return query;
     }
     
     @Override
     public List<Video> obtenerVideosCategoria(String nom){
        List<Video> query = null;
        EntityManager emanager = emFactory.createEntityManager();
        try {
            
            emanager.getTransaction().begin();

            Categoria cat = emanager.find(Categoria.class, nom);
            if(cat == null) throw new Exception("La categoria no existe");
            
            List<Video> aux  = emanager.createNamedQuery("Video.findByCategoria",Video.class).setParameter("categoria", nom).getResultList();

            query = new ArrayList<>();
            //Quito las listas de los usuarios inactivos
            for(int i=0; i < aux.size(); i++) {
            	Video vid = aux.get(i);
            	Usuario usr = emanager.find(Usuario.class, vid.getIdUsuario());
            	if(usr.getActivo()) query.add(vid);
            }
            
        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
        emanager.getTransaction().commit();
        emanager.close();
        return query;
     }
     
    private void exceptionAux(String inv, Exception exc){
        if(!inv.endsWith("_jsp.java") && !inv.endsWith("Test.java")) {
            JOptionPane.showMessageDialog(null," Error: "+exc.getMessage());
        } else {
            System.out.println("Error: "+exc.getMessage());
        }
    }
    
    //Para la web
    
    @Override
    public List<ListaDeReproduccionDt> obtenerListasDtCategoria(String nom){
        List<ListaDeReproduccionDt> retorno = new ArrayList<>();
        EntityManager emanager = emFactory.createEntityManager();
        try {
            
            emanager.getTransaction().begin();
            
            Categoria cat = emanager.find(Categoria.class, nom);
            
            if(cat == null) throw new Exception("La categoria no existe");
            
            List<ListaDeReproduccion> lista = emanager.createNamedQuery("ListaDeReproduccion.findByCategoria",ListaDeReproduccion.class).setParameter("categoria", cat).getResultList();
            
            for(int i=0; i < lista.size(); i++) {
                ListaDeReproduccion lst = lista.get(i);
                if(lst.getUsuario().getActivo()) {
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
            }
                        
        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
        emanager.getTransaction().commit();
        emanager.close();
        return retorno;
    }
    
    @Override
    public List<VideoDt> obtenerVideosDtCategoria(String nom) {
        List<VideoDt> retorno = new ArrayList<>();
        EntityManager emanager = emFactory.createEntityManager();
        try {
            emanager.getTransaction().begin();
            
            Categoria cat = emanager.find(Categoria.class, nom);
            if(cat == null) throw new Exception("La categoria no existe");
            
            List<Video> lista = emanager.createNamedQuery("Video.findByCategoria",Video.class).setParameter("categoria", nom).getResultList();

            for(int i=0; i < lista.size(); i++) {
            	Usuario usr = emanager.find(Usuario.class, lista.get(i).getIdUsuario());
                if(usr.getActivo()) retorno.add(new VideoDt(lista.get(i)));
            }
            
        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
        emanager.getTransaction().commit();
        emanager.close();
        return retorno;
    }
    
    protected Date fechaUltimoVideo(Collection<Video> videos) {
        Date retorno = null;
        
        if(videos != null) {
        	if(!videos.isEmpty()) {
	            try {
	            	Iterator<Video> iter = videos.iterator();
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
        }
        return retorno;
    }
    
    //Auxiliar de pruebas
    @Override
    public void EliminarCategoria(String nom) {
    	EntityManager emanager = emFactory.createEntityManager();
    	try {
    		
            emanager.getTransaction().begin();
            
            Categoria cat = emanager.find(Categoria.class, nom);
            if(cat == null) throw new Exception("La categoria no existe");
            emanager.remove(cat);
            //Ya que es una funcion hecha para testeo y se eliminaran categorias sin videos ni listas
            //no es necesario encargarse de estas en el codigo
            
            
        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
    	emanager.getTransaction().commit();
        emanager.close();
    }
}


