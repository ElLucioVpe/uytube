/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.controladores;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import logica.Canal;
import logica.Categoria;
import logica.ListaDeReproduccion;
import logica.ListaDeReproduccion_PorDefecto;
import logica.Usuario;
//import logica.controladores.IControladorUsuario;

/**
 *
 * @author Esteban
 */
public class ControladorUsuario implements IControladorUsuario {

    private final EntityManagerFactory emFactory;

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
            //Creacion de listas de reproduccion por defecto existentes
            List listasD = em.createQuery("select l from ListaDeReproduccion_PorDefecto l").getResultList();
            if (!listasD.isEmpty()) {
                Iterator it = listasD.iterator();
                while(it.hasNext()) {
                    ListaDeReproduccion_PorDefecto ld = (ListaDeReproduccion_PorDefecto) it.next();
                    ListaDeReproduccion l = new ListaDeReproduccion(ld.getNombre(), u, true);
                    em.persist(l);
                    u.addLista(l);
                }
                em.merge(u);
            }
            //
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
    public void ModificarUsuario(int id, String nuevonom, String nuevoapell, String nuevafechaNac, String nuevonomC, String nuevadesC, boolean nuevaprivC){
        //en su respectivo frame deberan antes ser utilizados
        //ListarUsuarios() y ConsultarUsuario(id)
        //los atributos que no se deseen modificar llegaran en blanco o null
        try {

            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();

            Usuario u = em.find(Usuario.class, id);
            if(!nuevonom.isBlank()) u.setNombre(nuevonom);
            if(!nuevoapell.isBlank()) u.setApellido(nuevoapell);
            if(nuevafechaNac != null) u.setFechanac(new SimpleDateFormat("dd/MM/yyyy").parse(nuevafechaNac));

            Canal c = em.find(Canal.class, u.getId()); //Por las dudas lo busco con find
            if(!nuevonomC.isBlank()) c.setNombre(nuevonomC);
            if(!nuevadesC.isBlank()) c.setDescripcion(nuevadesC);
            c.setPrivacidad(nuevaprivC); //al no poder comparar a null si no hay nueva damos la misma

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

    //Listas de Reproduccion
    @Override
    public void AltaListaDeReproduccionPorDefecto(String nombre) {
        try {

            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();

            if(em.find(ListaDeReproduccion_PorDefecto.class, nombre) != null)
                throw new Exception("El nombre de la lista ya existe");

            em.persist(new ListaDeReproduccion_PorDefecto(nombre));
            List users = em.createQuery("SELECT u FROM Usuario u").getResultList();

            Iterator it = users.iterator();
            while(it.hasNext()) {
                Usuario u = (Usuario) it.next();
                ListaDeReproduccion l = new ListaDeReproduccion(nombre, u, true);
                em.persist(l);
                u.addLista(l);
                em.merge(u);
            }
            em.getTransaction().commit();
            em.close();

            JOptionPane.showMessageDialog(null,"La lista de reproduccion se creo con exito");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
    }

    @Override
    public void AltaListaDeReproduccionParticular(String nombre, int id_propietario, boolean privacidad, String categoria) {
        try {

            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();

            Usuario propietario = em.find(Usuario.class, id_propietario);

            if(propietario == null) throw new Exception("El usuario ingresado no existe");
            if(propietario.existeLista(nombre)) throw new Exception("El nombre de la lista ya existe");

            ListaDeReproduccion l = new ListaDeReproduccion(nombre, propietario, privacidad);
            if(!categoria.equals("Ninguna")) l.setCategoria(em.find(Categoria.class, categoria));

            em.persist(l);
            propietario.addLista(l);
            em.merge(propietario);
            em.getTransaction().commit();
            em.close();

            JOptionPane.showMessageDialog(null,"La lista de reproduccion se creo con exito");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
    }

    //Auxiliares
    @Override
    public int obtenerIdUsuario(String nick) {
        int id = -1;
        try {

            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();

            Usuario u = em.createNamedQuery("Usuario.findByNickname", Usuario.class).setParameter("nickname", nick).getSingleResult();
            if(u == null) throw new Exception("El usuario no existe");

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
        try {

            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();

            Usuario u = em.find(Usuario.class, id);
            em.remove(u);
            em.getTransaction().commit();
            em.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
    }

    @Override
    public List obtenerCategorias() {
        List l = new ArrayList<String>();
        try {
            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();

            l = em.createQuery("select c.nombre from Categoria c").getResultList();

            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
        return l;
    }
    
    @Override
    public void seguirUsuario(String seguidor, String seguido){
        try {
            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();
            if(em.createNamedQuery("Canal.findByUserId", Canal.class).setParameter("userId", obtenerIdUsuario(seguido)).getResultList().size() == 0)
                throw new Exception("Ese usuario no existe o no tiene canal");
            Canal c = em.find(Canal.class, obtenerIdUsuario(seguido));
            Usuario u = em.createNamedQuery("Usuario.findByNickname", Usuario.class).setParameter("nickname", seguidor).getSingleResult();
            c.setUsuario(u);
            em.merge(c);
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
           
    }
    
           
}
    
 