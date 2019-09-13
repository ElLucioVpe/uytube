
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
import javax.persistence.TypedQuery;
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
import logica.dt.valoracionDt;

/**
 *
 * @author Esteban
 */

public class ControladorVideo implements IControladorVideo {
    
    private EntityManagerFactory emFactory;
      
    public ControladorVideo() {
        emFactory = Persistence.createEntityManagerFactory("UyTubePU");
    }
    
    @Override
    public void AltaVideo(String nombre, String duracion, String url, String desc, int user, String categoria){
          try {
            
            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();
            
            Usuario u = em.find(Usuario.class, user); 
            if(u == null) throw new Exception("El usuario no existe");
            Canal c =  u.getCanal();
            if(c.obtenerVideo(nombre) != null) throw new Exception("El video ya esta registrado en ese user");
            
            java.util.Date fecha = new Date();
            Video v = new Video(nombre, Float.parseFloat(duracion), url, desc, fecha,true,user);
            
            if(!categoria.equals("Ninguna")){
                Categoria cat = em.find(Categoria.class, categoria);
                if(cat == null) throw new Exception("La categoria no existe");
                v.setCategoria(categoria);
            }
            
            c.agregarVideo(v);
            em.persist(v);
            em.merge(c);
            em.getTransaction().commit();
            em.close();
            
            JOptionPane.showMessageDialog(null,"El video se registro con exito");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
    }
    
    @Override
    public void ModificarVideo(int id, String nuevoNom, String nuevaDur, String nuevaUrl, String nuevaDesc, Date nuevaFpub, boolean nuevaPriv, String nuevaCat){
        try {

            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();
            java.util.Date fecha = new Date();

            Video v = em.find(Video.class, id);
            if(!nuevoNom.isBlank()) v.setNombre(nuevoNom);
            if(!nuevaDur.isBlank()) v.setDuracion(Float.parseFloat(nuevaDur));
            if(!nuevaUrl.isBlank()) v.setUrl(nuevaUrl);
            if(!nuevaDesc.isBlank()) v.setUrl(nuevaDesc);
            if(nuevaFpub != null){
                if(nuevaFpub.after(fecha)) throw new Exception("Fecha Imposible aun no estamos en esa fecha");
                v.setFechaPublicacion(nuevaFpub);
            }
            //Priv cambiar chanc
            Canal cv = em.find(Canal.class, v.getIdUsuario());
            if(cv.getPrivacidad() && !nuevaPriv) throw new Exception("El video no puede ser publico ya que el canal es privado");
            v.setPrivacidad(nuevaPriv);
            if(!nuevaCat.equals("Ninguna")) v.setCategoria(nuevaCat);
            
            em.merge(v);
            em.getTransaction().commit();
            em.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
        }
        
        @Override
        public void ValorarVideo(int user_valoracion, int id_video, boolean gusta) {
            try {

                EntityManager em = emFactory.createEntityManager();
                em.getTransaction().begin();

                Video video = em.find(Video.class, id_video);
                if(video == null) throw new Exception("El video no existe");
                Usuario user = em.find(Usuario.class, user_valoracion);
                if(user == null) throw new Exception("El usuario no existe");
                
                Valoracion v = em.find(Valoracion.class, new ValoracionPK(user_valoracion, id_video));
                if(v == null){ 
                    v = new Valoracion(user_valoracion, id_video, gusta);
                    em.persist(v);
                    user.agregarValoracion(v);
                    video.agregarValoracion(v);
                } else {
                    v.setGustar(gusta);
                    em.merge(v);
                }
                
                em.merge(video);
                em.merge(user);
                em.getTransaction().commit();
                em.close();
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
            }
        }
        
        @Override
        public void ComentarVideo(int user_id, int video_id, long id_padre, String texto, Date fecha) {
            try {

                EntityManager em = emFactory.createEntityManager();
                em.getTransaction().begin();

                Video video = em.find(Video.class, video_id);
                if(video == null) throw new Exception("El video no existe");
                Usuario user = em.find(Usuario.class, user_id);
                if(user == null) throw new Exception("El usuario no existe");

                Comentario c = new Comentario(user_id, video_id, texto, fecha);
                em.persist(c);
                
                if (id_padre >= 0) {
                    Comentario cp = em.find(Comentario.class, id_padre);
                    if(cp == null) throw new Exception("El comentario padre no existe");
                    c.setPadre(cp);
                    cp.agregarHijo(c);
                    em.merge(cp);
                    em.merge(c);
                }
                else {
                    //lo agrego al video solo si no tiene padre, para facilitar su uso en funciones recursivas
                    //si tiene padre el padre ya estara en la coleccion del video
                    video.agregarComentario(c); 
                    em.merge(video);
                }
                em.getTransaction().commit();
                em.close();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
            }
        }
        
        //Auxiliares
        @Override
        public VideoDt obtenerVideoDt(String nom, int user) {
            VideoDt dt = null;
            try {
                EntityManager em = emFactory.createEntityManager();
                em.getTransaction().begin();
                
                Canal c = em.find(Canal.class, user);
                if(c == null) throw new Exception("El usuario no existe");
                
                
                var querry = em.createQuery("SELECT v FROM Video v WHERE v.nombre = :nombre AND v.canal_user_id = :canal_user_id", Video.class);
                
                Video video = querry.setParameter("nombre", nom).setParameter("canal_user_id", user).getSingleResult();
                if(video == null) throw new Exception("El video no existe");
                
                dt = new VideoDt(video);
                em.getTransaction().commit();
                em.close();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
            }
            return dt;
        }
        
        @Override
        public DefaultMutableTreeNode obtenerComentariosVideo(int id_video) {
            DefaultMutableTreeNode root = null;
            try {
                EntityManager em = emFactory.createEntityManager();
                em.getTransaction().begin();

                Video video = em.find(Video.class, id_video);
                if(video == null) throw new Exception("El video no existe");
                
                Collection<Comentario> cs = video.getComentarios();
                Iterator<Comentario> it = cs.iterator();
                root = new DefaultMutableTreeNode(video.getNombre() + " :: Comentarios");
                while(it.hasNext()) {
                    Comentario c = it.next();
                    if(c.getPadre() == null) obtenerHijosRecursivo(root, c);
                }
                
                em.getTransaction().commit();
                em.close();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
            }
            return root;
        }
        
        protected void obtenerHijosRecursivo(DefaultMutableTreeNode nodo, Comentario c) {
            if(nodo != null){
                DefaultMutableTreeNode nuevoNodo= new DefaultMutableTreeNode("(" + c.getUsuario().getNickname() + ") " + c.getContenido());
                nodo.add(nuevoNodo);
                
                Collection<Comentario> hijos = c.getHijos();
                Iterator<Comentario> it = hijos.iterator();
                
                while(it.hasNext()) {
                    Comentario hijo = it.next();
                    obtenerHijosRecursivo(nuevoNodo, hijo);
                }
            }
        }
        
        public List<valoracionDt> obtenerValoracionVideo(int id_video){
            List<valoracionDt> list = new ArrayList<valoracionDt>();
            try {
                EntityManager em = emFactory.createEntityManager();

                Video vid = em.find(Video.class, id_video);
                if(vid == null) throw new Exception("El video no existe");
                Collection<Valoracion> vals = vid.getValoraciones();
                Iterator<Valoracion> it = vals.iterator();
                while(it.hasNext()) {
                    list.add(new valoracionDt(it.next()));
                }
                em.close();
           } catch (Exception e) {
               JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
           }
           return list;
        }
        
}
