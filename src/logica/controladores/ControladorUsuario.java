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
import java.util.List;
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
    public void EliminarUsuario(int id) {
        //eliminacion re loca
        //estilo lo de ingresar pero em.remove(u)
    }
    
    @Override
    public void ModificarUsuario(int id, String nuevonom, String nuevoapell, Date nuevafechaNac, String nuevonomC, String nuevadesC, boolean nuevaprivC){
        //en su respectivo frame deberan antes ser utilizados 
        //ListarUsuarios() y ConsultarUsuario(id)
        //los atributos que no se deseen modificar llegaran en blanco o null
        try {
            
            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();
            
            Usuario u = em.find(Usuario.class, id);
            if(!nuevonom.isBlank()) u.setNombre(nuevonom);
            if(!nuevoapell.isBlank()) u.setApellido(nuevoapell);
            if(nuevafechaNac != null) u.setFechanac(nuevafechaNac);
            
            Canal c = em.find(Canal.class, u.getId()); //Por las dudas lo busco con find
            if(!nuevonomC.isBlank()) c.setNombre(nuevonomC);
            if(!nuevadesC.isBlank()) c.setDescripcion(nuevadesC);
            c.setPrivacidad(nuevaprivC); //al no poder comparar a null si no hay nueva damos la misma
            
            u.setCanal(c);
            em.merge(c);
            em.merge(u);
            em.getTransaction().commit();
            em.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
        //Posteriormente en su respectivo frame se podra seleccionar para editar datos
        //de los videos o listas de reproduccion del usuario
        //ademas la imagen al llamarse igual ya que su nombre es el nick del usuario
        //simplemente sera reemplazada luego de finalizada la modificacion en caso de ser necesario
    }
    
    @Override
    public List<String> ListarUsuarios(){
        List<String> list = null;
        //probablemente devuelve una lista de los nicks de los usuarios existentes
        //esa lista luego es mostrada en su respectivo frame
        return list;
    }
    
    @Override
    public void ConsultarUsuario(int id){
        //devuelve un DataType o algo por el estilo con la informacion del usuario y su canal
        //esa informacion luego es mostrada en su respectivo frame
    }
    
    //Auxiliares
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
}
