/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.controladores;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import logica.Canal;
import logica.Usuario;
import logica.Valoracion;
import logica.ValoracionPK;
import logica.Video;
import logica.Comentario;

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
    public void AltaVideo(String nombre, String duracion, String url, String desc, int user){
          try {
            
            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();
            
            Usuario u = em.find(Usuario.class, user); 
            if(u == null) throw new Exception("El usuario no existe");
            Canal c =  u.getCanal();
            if(c.obtenerVideo(nombre) != null) throw new Exception("El video ya esta registrado en ese user");
            java.util.Date fecha = new Date();
            //DateFormat dtf = new SimpleDateFormat("yyyy/MM/dd");
            //System.out.println(dtf.format(fecha));
            //new SimpleDateFormat("dd/MM/yyyy").parse(dtf.format(fecha))
            Video v = new Video(nombre, Integer.parseInt(duracion), url, desc, fecha,true,user);
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
    
        public void ModificarVideo(int id, String nuevoNom, String nuevaDur, String nuevaUrl, String nuevaDesc, Date nuevaFpub, boolean nuevaPriv){
        try {

            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();
            java.util.Date fecha = new Date();

            Video v = em.find(Video.class, id);
            if(!nuevoNom.isBlank()) v.setNombre(nuevoNom);
            if(!nuevaDur.isBlank()) v.setDuracion( Integer.parseInt(nuevaDur));
            if(!nuevaUrl.isBlank()) v.setUrl(nuevaUrl);
            if(!nuevaDesc.isBlank()) v.setUrl(nuevaDesc);
            if(nuevaFpub.after(fecha)){
                throw new Exception("Fecha Imposible aun no estamos en esa fecha");
            }
            if(nuevaFpub != null) v.setFechaPublicacion(nuevaFpub);
            //Priv cambiar chanc
            v.setPrivacidad(nuevaPriv);

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
                } else {
                                    v.setGustar(gusta);
                                    em.merge(v);
                }

                user.agregarValoracion(v);
                video.agregarValoracion(v);
                em.merge(user);
                em.merge(video);
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
                video.agregarComentario(c);
                em.merge(video);
                em.getTransaction().commit();
                em.close();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
            }
        }
}
