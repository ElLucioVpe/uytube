
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.controladores;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import logica.Canal;
import logica.Usuario;
import logica.Valoracion;
import logica.ValoracionPK;
import logica.Video;
import logica.Categoria;
import logica.Comentario;
import logica.dt.VideoDt;
import logica.dt.UsuarioDt;
import logica.dt.ComentarioDt;
import logica.dt.valoracionDt;

/**
 *
 * @author Esteban
 */

public class ControladorVideo implements IControladorVideo {
    
    private static ControladorVideo instancia;
    private final EntityManagerFactory emFactory;
 
    private ControladorVideo() {
        emFactory = Persistence.createEntityManagerFactory("UyTubePU");
    }
    
    public static ControladorVideo getInstance() {
        if (instancia == null) {
            instancia = new ControladorVideo();
        }
        return instancia;
    }
    
    @Override
    public void AltaVideo(String nombre, String duracion, String url, String desc, int user, String categoria){
          try {
            
            EntityManager emanager = emFactory.createEntityManager();
            emanager.getTransaction().begin();
            
            Usuario usr = emanager.find(Usuario.class, user); 
            if(usr == null) throw new Exception("El usuario no existe");
            Canal cnl =  usr.getCanal();
            if(cnl.obtenerVideo(nombre) != null) throw new Exception("El video ya esta registrado en ese user");
            
            java.util.Date fecha = new Date();
            Video vid = new Video(nombre, Float.parseFloat(duracion), url, desc, fecha,true,user);
            
            if(!categoria.equals("Ninguna")){
                Categoria cat = emanager.find(Categoria.class, categoria);
                if(cat == null) throw new Exception("La categoria no existe");
                vid.setCategoria(categoria);
            }
            
            cnl.agregarVideo(vid);
            emanager.persist(vid);
            emanager.merge(cnl);
            emanager.getTransaction().commit();
            emanager.close();
        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
    }
    
    @Override
    public void ModificarVideo(int id, String nuevoNom, String nuevaDur, String nuevaUrl, String nuevaDesc, Date nuevaFpub, boolean nuevaPriv, String nuevaCat){
        try {

            EntityManager emanager = emFactory.createEntityManager();
            emanager.getTransaction().begin();
            java.util.Date fecha = new Date();

            Video vid = emanager.find(Video.class, id);
            if(!nuevoNom.isBlank()) vid.setNombre(nuevoNom);
            if(!nuevaDur.isBlank()) vid.setDuracion(Float.parseFloat(nuevaDur));
            if(!nuevaUrl.isBlank()) vid.setUrl(nuevaUrl);
            if(!nuevaDesc.isBlank()) vid.setDescripcion(nuevaDesc);
            if(nuevaFpub != null){
                if(nuevaFpub.after(fecha)) throw new Exception("Fecha Imposible aun no estamos en esa fecha");
                vid.setFechaPublicacion(nuevaFpub);
            }
            //Priv cambiar chanc
            Canal cnlv = emanager.find(Canal.class, vid.getIdUsuario());
            if(cnlv.getPrivacidad() && !nuevaPriv) throw new Exception("El video no puede ser publico ya que el canal es privado");
            vid.setPrivacidad(nuevaPriv);
            if(!nuevaCat.equals("Ninguna")) vid.setCategoria(nuevaCat);
            
            emanager.merge(vid);
            emanager.getTransaction().commit();
            emanager.close();
        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
    }
        
        @Override
        public void ValorarVideo(int user_valoracion, int id_video, boolean gusta) {
            try {

                EntityManager emanager = emFactory.createEntityManager();
                emanager.getTransaction().begin();

                Video video = emanager.find(Video.class, id_video);
                if(video == null) throw new Exception("El video no existe");
                Usuario user = emanager.find(Usuario.class, user_valoracion);
                if(user == null) throw new Exception("El usuario no existe");
                
                Valoracion vid = emanager.find(Valoracion.class, new ValoracionPK(user_valoracion, id_video));
                if(vid == null){ 
                    vid = new Valoracion(user_valoracion, id_video, gusta);
                    emanager.persist(vid);
                    user.agregarValoracion(vid);
                    video.agregarValoracion(vid);
                } else {
                    vid.setGustar(gusta);
                    emanager.merge(vid);
                }
                
                emanager.merge(video);
                emanager.merge(user);
                emanager.getTransaction().commit();
                emanager.close();
                
            } catch (Exception exc) {
                Throwable _throwable = new Throwable();
                StackTraceElement[] elements = _throwable.getStackTrace();
                String invocador = elements[1].getFileName();
                exceptionAux(invocador, exc);
            }
        }
        
        @Override
        public void ComentarVideo(int user_id, int video_id, long id_padre, String texto, Date fecha) {
            try {

                EntityManager emanager = emFactory.createEntityManager();
                emanager.getTransaction().begin();

                Video video = emanager.find(Video.class, video_id);
                if(video == null) throw new Exception("El video no existe");
                Usuario user = emanager.find(Usuario.class, user_id);
                if(user == null) throw new Exception("El usuario no existe");

                Comentario com = new Comentario(user_id, video_id, texto, fecha);
                emanager.persist(com);
                
                if (id_padre >= 0) {
                    Comentario comp = emanager.find(Comentario.class, id_padre);
                    if(comp == null) throw new Exception("El comentario padre no existe");
                    com.setPadre(comp);
                    comp.agregarHijo(com);
                    emanager.merge(comp);
                    emanager.merge(com);
                }
                else {
                    //lo agrego al video solo si no tiene padre, para facilitar su uso en funciones recursivas
                    //si tiene padre el padre ya estara en la coleccion del video
                    video.agregarComentario(com); 
                    emanager.merge(video);
                }
                emanager.getTransaction().commit();
                emanager.close();

            } catch (Exception exc) {
                Throwable _throwable = new Throwable();
                StackTraceElement[] elements = _throwable.getStackTrace();
                String invocador = elements[1].getFileName();
                exceptionAux(invocador, exc);
            }
        }
        
        //Auxiliares
        @Override
        public VideoDt obtenerVideoDt(String nom, Integer user) {
            VideoDt vdt = null;
            try {
                EntityManager emanager = emFactory.createEntityManager();
                emanager.getTransaction().begin();
                
                Canal cnl = emanager.find(Canal.class, user);
                if(cnl == null) throw new Exception("El usuario no existe");
                
                
                var querry = emanager.createQuery("SELECT v FROM Video v WHERE v.nombre = :nombre AND v.canal_user_id = :canal_user_id", Video.class);
                
                Video video = querry.setParameter("nombre", nom).setParameter("canal_user_id", user).getSingleResult();
                if(video == null) throw new Exception("El video no existe");
                
                vdt = new VideoDt(video);
                emanager.getTransaction().commit();
                emanager.close();

            } catch (Exception exc) {
                Throwable _throwable = new Throwable();
                StackTraceElement[] elements = _throwable.getStackTrace();
                String invocador = elements[1].getFileName();
                exceptionAux(invocador, exc);
            }
            return vdt;
        }
        
        @Override
        public DefaultMutableTreeNode obtenerComentariosVideo(int id_video) {
            DefaultMutableTreeNode root = null;
            try {
                EntityManager emanager = emFactory.createEntityManager();
                emanager.getTransaction().begin();

                Video video = emanager.find(Video.class, id_video);
                if(video == null) throw new Exception("El video no existe");
                
                Collection<Comentario> comentarios = video.getComentarios();
                Iterator<Comentario> iter = comentarios.iterator();
                root = new DefaultMutableTreeNode(video.getNombre() + " :: Comentarios");
                while(iter.hasNext()) {
                    Comentario com = iter.next();
                    if(com.getPadre() == null) obtenerHijosRecursivo(root, com);
                }
                
                emanager.getTransaction().commit();
                emanager.close();

            } catch (Exception exc) {
                Throwable _throwable = new Throwable();
                StackTraceElement[] elements = _throwable.getStackTrace();
                String invocador = elements[1].getFileName();
                exceptionAux(invocador, exc);
            }
            return root;
        }
        
        protected void obtenerHijosRecursivo(DefaultMutableTreeNode nodo, Comentario com) {
            if(nodo != null){
                DefaultMutableTreeNode nuevoNodo= new DefaultMutableTreeNode("(" + com.getUsuario().getNickname() + ") " + com.getContenido());
                nodo.add(nuevoNodo);
                
                Collection<Comentario> hijos = com.getHijos();
                Iterator<Comentario> iter = hijos.iterator();
                
                while(iter.hasNext()) {
                    Comentario hijo = iter.next();
                    obtenerHijosRecursivo(nuevoNodo, hijo);
                }
            }
        }
        
        public List<valoracionDt> obtenerValoracionVideo(int id_video){
            List<valoracionDt> list = new ArrayList<valoracionDt>();
            try {
                EntityManager emanager = emFactory.createEntityManager();

                Video vid = emanager.find(Video.class, id_video);
                if(vid == null) throw new Exception("El video no existe");
                Collection<Valoracion> vals = vid.getValoraciones();
                Iterator<Valoracion> iter = vals.iterator();
                while(iter.hasNext()) {
                    list.add(new valoracionDt(iter.next()));
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
        public List<VideoDt> obtenerVideos() {
                List<VideoDt> list = new ArrayList<VideoDt>();
                try {
                EntityManager emanager = emFactory.createEntityManager();
                List<Video> videos = emanager.createQuery("SELECT v FROM Video v", Video.class).getResultList();
                for(int i=0;i < videos.size(); i++) {
                    list.add(new VideoDt(videos.get(i)));
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
        public VideoDt obtenerVideoDtPorID(int id_video){
            VideoDt video = new VideoDt();
            try {
                EntityManager emanager = emFactory.createEntityManager();
                Video vid = emanager.find(Video.class, id_video);
                //List<Video> videos = em.createQuery("SELECT v FROM Video v WHERE id = ", Video.class).getResultList();
                video = new VideoDt(vid);
                emanager.close();
            } catch (Exception exc) {
                Throwable _throwable = new Throwable();
                StackTraceElement[] elements = _throwable.getStackTrace();
                String invocador = elements[1].getFileName();
                exceptionAux(invocador, exc);
            }
            return video;
        }
        
        private void exceptionAux(String inv, Exception exc){
            if(!inv.endsWith("_jsp.java")){
                JOptionPane.showMessageDialog(null," Error: "+exc.getMessage());
            } else {
                System.out.println("Error: "+exc.getMessage());
            }
        }
        
        @Override
        public List<ComentarioDt> obtenerComentariosDt(int id_video) {
            List<ComentarioDt> comentariosDt = new ArrayList<>();
            try { 
                EntityManager emanager = emFactory.createEntityManager();
                
                Video vid = emanager.find(Video.class, id_video);
                if(vid == null) throw new Exception("El video no existe");
                Collection<Comentario> comentarios = vid.getComentarios();
                if(comentarios == null) throw new Exception("El video no tiene comentarios");
                
                Iterator<Comentario> iter = comentarios.iterator();
                while (iter.hasNext()) {
                    Comentario com = iter.next();
                    if(com.getPadre() == null) {
                        comentariosDt.add(new ComentarioDt(
                            com.getId(),
                            -1,
                            com.getContenido(),
                            new UsuarioDt(com.getUsuario()),
                            com.getFecha(),
                            obtenerHijosDt(com)
                        ));
                    }
                }
                emanager.close();
            } catch (Exception exc) {
                Throwable _throwable = new Throwable();
                StackTraceElement[] elements = _throwable.getStackTrace();
                String invocador = elements[1].getFileName();
                exceptionAux(invocador, exc);
            }
            return comentariosDt;
        }
        
        protected List<ComentarioDt> obtenerHijosDt(Comentario com) {
            List<ComentarioDt> hijosdt = new ArrayList<>();
            if(com != null){
                Collection<Comentario> hijos = com.getHijos();
                Iterator<Comentario> iter = hijos.iterator();
                while(iter.hasNext()) {
                    Comentario hijo = iter.next();
                    hijosdt.add(new ComentarioDt(
                        hijo.getId(),
                        hijo.getPadre().getId(),
                        hijo.getContenido(),
                        new UsuarioDt(hijo.getUsuario()),
                        hijo.getFecha(),
                        obtenerHijosDt(hijo)
                    ));
                }
            }
            return hijosdt;
        }
}