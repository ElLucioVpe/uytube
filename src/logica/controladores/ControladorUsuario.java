/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.controladores;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import logica.Canal;
import logica.Usuario;
import logica.controladores.IControladorUsuario;

/**
 *
 * @author Esteban
 */
public class ControladorUsuario implements IControladorUsuario {
    
    private EntityManagerFactory emFactory;
    
    public ControladorUsuario() {
        emFactory = Persistence.createEntityManagerFactory("UyTubePU");
    }
    
    @Override
    public void AltaUsuario(String nick, String nom, String apell, String mail, String fnac, String img) {
        try {
            
            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();
            
            if(em.createNamedQuery("Usuario.findByNickname", Usuario.class).setParameter("nickname", nick).getResultList().size() > 0) 
                throw new Exception("El nickname ya existe");
            if(em.createNamedQuery("Usuario.findByMail", Usuario.class).setParameter("mail",mail).getResultList().size() > 0) 
                throw new Exception("El mail ya esta registrado");
            
            Usuario u = new Usuario(nick, nom, apell, mail, new SimpleDateFormat("dd/MM/yyyy").parse(fnac));
            if(!img.isEmpty()) u.setImagen(img);
            em.persist(u);
            em.getTransaction().commit();
            em.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
        
    }
    
    @Override
    public void AltaCanal(String nombre, boolean privado, int user_id, String descripcion) {
        try {
            
            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();
            
            if(em.createNamedQuery("Canal.findByNombre", Canal.class).setParameter("nombre", nombre).getResultList().size() > 0)
                throw new Exception("El nombre del canal ya existe");
            
            Usuario u = em.find(Usuario.class, user_id);
            if(nombre.isBlank()) nombre = u.getNickname();
            
            Canal c = new Canal(user_id, nombre, privado);
            if(!descripcion.isEmpty()) c.setDescripcion(descripcion);
            c.setUsuario(u);
            u.setCanal(c);
            em.persist(c);
            em.merge(u);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            EliminarUsuario(user_id); //Elimino ya que no se completo correctamente todo el proceso
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
    }
    
    @Override
    public int obtenerIdUsuario(String nick) {
        int id = -1;
        try {
            
            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();
            
            Usuario u = em.createNamedQuery("Usuario.findByNickname", Usuario.class).setParameter("nickname", nick).getSingleResult();
            id = u.getId();
            
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
        return id;
    }
    
    @Override
    public void EliminarUsuario(int id) {
        //eliminacion re loca
        //estilo lo de ingresar pero em.remove(u)
    }
}
