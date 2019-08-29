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
            
            if(em.find(Usuario.class, nick) != null) 
                throw new Exception("El nickname ya existe");
            //if(em.createQuery("select count(*) from Usuario where Mail = :m").setParameter("m",mail).getResultList().size() == 1) 
            //    throw new Exception("El mail ya esta registrado");
            
            Usuario u = new Usuario(nick, nom, apell, mail, new SimpleDateFormat("dd/MM/yyyy").parse(fnac));
            if(!img.isEmpty()) u.setImagen(img);
            em.persist(u);
            em.getTransaction().commit();
            em.close();
            
            JOptionPane.showMessageDialog(null,"El usuario se registro con exito");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
        
    }
}
