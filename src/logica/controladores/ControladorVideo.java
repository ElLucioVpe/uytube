/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.controladores;
import java.text.SimpleDateFormat;
import logica.controladores.IControladorVideo;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.JOptionPane;
import logica.Usuario;
import logica.Video;

/**
 *
 * @author Esteban
 */

public class ControladorVideo implements IControladorVideo {
    
    private Collection videos;
    private EntityManagerFactory emFactory;
      
    public ControladorVideo() {
        
    }
    
    public void AltaVideo(String nombre, String duracion, String url, String desc, String fpub, String user){
          try {
            
            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();
            
            //if(em.createQuery("select count(*) from Usuario INNER JOIN Canal ON  Video ON where Mail = :m").setParameter("m",mail).getResultList().size() == 1) 
               // throw new Exception("El video ya esta registrado en ese user");
            
            Video v = new Video(0,nombre, Integer.parseInt(duracion), url, desc, new SimpleDateFormat("dd/MM/yyyy").parse(fpub),false,user);
            em.persist(v);
            em.getTransaction().commit();
            em.close();
            
            JOptionPane.showMessageDialog(null,"El video se registro con exito");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
        
        
    }
}
