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
import javax.persistence.TypedQuery;
import logica.Canal;
import logica.Categoria;
import logica.ListaDeReproduccion;
import logica.ListaDeReproduccion_PorDefecto;
import logica.Usuario;
import logica.Video;
import logica.dt.ListaDeReproduccionDt;
import logica.dt.UsuarioDt;
import logica.dt.VideoDt;
import logica.dt.VideoListaDt;
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
    public void AltaUsuario(String nick, String nom, String apell, String mail, Date fnac, String img) {
        try {

            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();

            if(em.createNamedQuery("Usuario.findByNickname", Usuario.class).setParameter("nickname", nick).getResultList().size() > 0)
                throw new Exception("El nickname ya existe");
            if(em.createNamedQuery("Usuario.findByMail", Usuario.class).setParameter("mail",mail).getResultList().size() > 0)
                throw new Exception("El mail ya esta registrado");

            Usuario u = new Usuario(nick, nom, apell, mail, fnac);
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
            //Creacion de listas de reproduccion por defecto existentes
            List listasD = em.createQuery("select l from ListaDeReproduccion_PorDefecto l").getResultList();
            if (!listasD.isEmpty()) {
                Iterator it = listasD.iterator();
                while(it.hasNext()) {
                    ListaDeReproduccion_PorDefecto ld = (ListaDeReproduccion_PorDefecto) it.next();
                    ListaDeReproduccion l = new ListaDeReproduccion(ld.getNombre(), u, true);
                    em.persist(l);
                    c.addLista(l);
                }
                em.merge(c);
            }
            //
            em.getTransaction().commit();
            em.close();
            
            JOptionPane.showMessageDialog(null,"El usuario se registro con exito");
        } catch (Exception e) {
            EliminarUsuario(user_id); //Elimino ya que no se completo correctamente todo el proceso
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
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
    public List<VideoDt> listarVideosDeUsuario(String usernick){
      List<VideoDt> list = new ArrayList<VideoDt>();
        try {
            int idUser= obtenerIdUsuario(usernick);
            EntityManager em = emFactory.createEntityManager();
            TypedQuery<Video> query1 = em.createQuery("SELECT v FROM Video v where v.canal_user_id= :idUser", Video.class);
            List<Video> vid = query1.setParameter("idUser", idUser).getResultList();


            for(int i=0;i < vid.size(); i++) {
                list.add(new VideoDt(vid.get(i)));
            }
            em.close();
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
        return list;

    }
/*public List<VideoDt> listarVideo(String nombrevideo, usernick){
      List<VideoDt> list = new ArrayList<VideoDt>();
        try {
            int idUser= obtenerIdUsuario(usernick);
             EntityManager em = emFactory.createEntityManager();
            TypedQuery<Video> vid = em.createQuery("Video.findByNombre", Video.class).setParameter("nombre", nombrevideo);

           //query1.setParameter("nombrevideo", nombrevideo);
           // vidID = query1.setParameter("idUser", idUser).getResultList();
         //  vid
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
        return list;

}*/
    @Override
    public List<UsuarioDt> ListarUsuarios(){
        List<UsuarioDt> list = new ArrayList<UsuarioDt>();
        try {

            //probablemente devuelve una lista de los nicks de los usuarios existentes
            //esa lista luego es mostrada en su respectivo frameS
            EntityManager em = emFactory.createEntityManager();
            List<Usuario> users = em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
            for(int i=0;i < users.size(); i++) {
                list.add(new UsuarioDt(users.get(i)));
            }
            em.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
        return list;
    }

    @Override
    public UsuarioDt ConsultarUsuario(int id){
        UsuarioDt dt = null;
        try {
            EntityManager em = emFactory.createEntityManager();
            //List users = em.createQuery("SELECT nick FROM Usuario u WHERE id = :id").getResultList();
            //TypedQuery<Usuario> query = em.createQuery("SELECT * FROM Usuario u WHERE u.id = :id", Usuario.class);
            //Usuario u = query.setParameter("id", id).getSingleResult();
            Usuario u = em.find(Usuario.class, id);
            dt = new UsuarioDt(u);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
        return dt;
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
            List users = em.createQuery("SELECT c FROM Canal c").getResultList();

            Iterator it = users.iterator();
            while(it.hasNext()) {
                Canal c = (Canal) it.next();
                ListaDeReproduccion l = new ListaDeReproduccion(nombre, c.getUsuario(), true);
                em.persist(l);
                c.addLista(l);
                em.merge(c);
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

            Canal propietario = em.find(Canal.class, id_propietario);
            
            
            if(propietario == null) throw new Exception("El usuario ingresado no existe");
            if(propietario.existeLista(nombre)) throw new Exception("El nombre de la lista ya existe");
            if(propietario.getPrivacidad() && !privacidad) throw new Exception("La lista no puede ser publica ya que el canal es privado");
            
            ListaDeReproduccion l = new ListaDeReproduccion(nombre, propietario.getUsuario(), privacidad);
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

    @Override
    public void ModificarListaDeReproduccion(int usuario, String lista, String nuevaCat, boolean nuevaPri) {
        try {

            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();

            Canal propietario = em.find(Canal.class, usuario);

            if(propietario == null) throw new Exception("El usuario ingresado no existe");
            if(!propietario.existeLista(lista)) throw new Exception("La lista no existe");
            if(propietario.getPrivacidad() && !nuevaPri) throw new Exception("La lista no puede ser publica ya que el canal es privado");
            
            ListaDeReproduccion l = propietario.getLista(lista);
            if(!nuevaCat.equals("Ninguna")) l.setCategoria(em.find(Categoria.class, nuevaCat));
            l.setPrivada(nuevaPri); //Se supone que si no se va a cambiar nuevaPri tiene el valor anterior

            em.merge(l);
            //propietario.modificarLista(l); //no deberia ser necesario
            em.merge(propietario);
            em.getTransaction().commit();
            em.close();

            JOptionPane.showMessageDialog(null,"La lista de reproduccion se modifico con exito");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
    }

    @Override
    public void AgregarVideoListaDeReproduccion(int usuarioVideo, int usuarioLista, String video, String lista) {
        try {

            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();

            Usuario user_video = em.find(Usuario.class, usuarioVideo);
            Canal canal_lista = em.find(Canal.class, usuarioLista);
            if(user_video == null) throw new Exception("El usuario propietario del video no existe");
            if(canal_lista == null) throw new Exception("El usuario propietario de la lista no existe");

            Canal canal_video = user_video.getCanal();
            Video v = canal_video.obtenerVideo(video);
            if(v == null) throw new Exception("El video no existe");

            canal_lista.agregarVideoLista(v, lista);
            
            em.merge(canal_lista);
            em.getTransaction().commit();
            em.close();

            JOptionPane.showMessageDialog(null,"El video fue agregado con exito");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
    }

    @Override
    public void QuitarVideoListaDeReproduccion(int usuariolista, String lista, int video) {
        try {

            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();

            Canal canal_lista = em.find(Canal.class, usuariolista);
            if(canal_lista == null) throw new Exception("El usuario propietario de la lista no existe");

            canal_lista.quitarVideoLista(video, lista);

            em.merge(canal_lista);
            em.getTransaction().commit();
            em.close();

            JOptionPane.showMessageDialog(null,"El video fue removido con exito");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
    }

    //Seguir Usuario y eso
    /*@Override
    public void seguirUsuario(String seguidor, String seguido){
        try {
            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();

            if(em.find(Canal.class, obtenerIdUsuario(seguido)) == null)
                throw new Exception("Ese usuario a seguir no existe o no tiene canal");
            if(em.find(Usuario.class, obtenerIdUsuario(seguidor)) == null)
                throw new Exception("Ese usuario seguidor no existe");

            Canal c = em.find(Canal.class, obtenerIdUsuario(seguido));
            Usuario u = em.createNamedQuery("Usuario.findByNickname", Usuario.class).setParameter("nickname", seguidor).getSingleResult();

            u.agregarSuscripcion(c);
            c.agregarSeguidor(u);
            em.merge(c);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }

    }*/

    @Override
    public void seguirUsuario(String seguidor, String seguido){
        try {
            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();

            Canal c = em.find(Canal.class, obtenerIdUsuario(seguido));
            if(c == null) throw new Exception("Ese usuario al que quiere seguir no existe o no tiene canal");
            Usuario uSeguidor = em.find(Usuario.class, obtenerIdUsuario(seguidor));
            if(uSeguidor == null) throw new Exception("El usuario seguidor no existe");

            c.agregarSeguidor(uSeguidor);
            uSeguidor.agregarSuscripcion(c);
            em.merge(c);
            em.merge(uSeguidor);
            em.getTransaction().commit();
            em.close();

            JOptionPane.showMessageDialog(null,"La suscripcion se realizo con exito");
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
       }

    }

    @Override
    public List<String> ListarSeguidores(int userId){
        List<String> seguidores = null;
        /*
        try {
            EntityManager em = emFactory.createEntityManager();
            //List users = em.createQuery("SELECT nick FROM Usuario u WHERE id = :id").getResultList();
            TypedQuery<Usuario> query = em.createQuery("SELECT * FROM Usuario u WHERE u.id = :id", Usuario.class);
            Usuario u = query.setParameter("id", id).getSingleResult();
            dt = new UsuarioDt(u);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
*/
        seguidores.add("prueba");
        return seguidores;
    }

    @Override
    public List<String> ListarSiguiendo(int userId){
        List<String> seguidores = null;
        /*
        try {
            EntityManager em = emFactory.createEntityManager();
            //List users = em.createQuery("SELECT nick FROM Usuario u WHERE id = :id").getResultList();
            TypedQuery<Usuario> query = em.createQuery("SELECT * FROM Usuario u WHERE u.id = :id", Usuario.class);
            Usuario u = query.setParameter("id", id).getSingleResult();
            dt = new UsuarioDt(u);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
*/
        seguidores.add("prueba");
        return seguidores;
    }

    @Override
    public void dejarDeSeguirUsuario(String seguidor, String seguido){
      try {
            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();
            Canal c = em.find(Canal.class, obtenerIdUsuario(seguido));
            if(c == null)
                throw new Exception("Ese usuario al que quiere seguir no existe o no tiene canal");
            Usuario uSeguidor = em.find(Usuario.class, obtenerIdUsuario(seguidor));
            if(uSeguidor == null)
                throw new Exception("El usuario seguidor no existe");
            //con asegurarnos de uno de los lados de la relacion basta
            if(!c.getSeguidores().contains(uSeguidor)) throw new Exception("El usuario no es seguidor del canal");
            
            c.eliminarSeguidor(uSeguidor);
            uSeguidor.eliminarSuscripcion(c);
            em.merge(c);
            em.merge(uSeguidor);
            em.getTransaction().commit();
            em.close();

            JOptionPane.showMessageDialog(null,"La suscripcion se eliminó con exito");
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
            
            TypedQuery<Usuario> q = em.createNamedQuery("Usuario.findByNickname", Usuario.class).setParameter("nickname", nick);
            if (q.getResultList().isEmpty()) throw new Exception("El usuario no existe");
            Usuario u = q.getSingleResult();

            id = u.getId();
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
        return id;
    }

    @Override
    public String obtenerNickUsuario(int id) {
        String nick = "";
        try {

            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();
            
            Usuario u = em.find(Usuario.class, id);
            if (u == null) throw new Exception("El usuario no existe");

            nick = u.getNickname();
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
        return nick;
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
    public List<String> obtenerListasUsuario(int id) {
        List<String> l = new ArrayList<>();
        try {
            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();

            Canal c = em.find(Canal.class, id);
            if(c == null) throw new Exception("El usuario no existe");
            Collection<ListaDeReproduccion> aux = c.getListas();

            Iterator<ListaDeReproduccion> it = aux.iterator();
            while(it.hasNext()) {
                l.add(it.next().getNombre());
            }

            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
        return l;
    }

    @Override
    public List<VideoListaDt> obtenerVideosLista(int id, String lista) {
        List<VideoListaDt> l = new ArrayList<>();
        try {
            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();

            Canal c = em.find(Canal.class, id);
            if(c == null) throw new Exception("El usuario no existe");
            Collection<Video> lvideo = c.getLista(lista).getVideos();

            Iterator it = lvideo.iterator();
            while(it.hasNext()) {
                Video v = (Video) it.next();
                l.add(new VideoListaDt(v.getId(), v.getNombre()));
            }

            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
        return l;
    }
    
    @Override
    public String obtenerTipoLista(int propietario, String lista) {
        String tipo = "Privada";
        try {
            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();

            Canal c = em.find(Canal.class, propietario);
            if(c == null) throw new Exception("El usuario no existe");
            ListaDeReproduccion l = c.getLista(lista);
            if(l == null) throw new Exception("El usuario no tiene ninguna lista con ese nombre");
            
            if(em.find(ListaDeReproduccion_PorDefecto.class, lista) != null) tipo = "Por Defecto";
            
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
        return tipo;
    }
    
    @Override
    public ListaDeReproduccionDt obtenerListaDt(int id, String lista) {
        ListaDeReproduccionDt ldt = null;
        try {
            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();

            Canal c = em.find(Canal.class, id);
            if(c == null) throw new Exception("El usuario no existe");
            ListaDeReproduccion l = c.getLista(lista);
            if(l == null) throw new Exception("El usuario no tiene ninguna lista con ese nombre");
            
            //Reviso su tipo
            String tipo = "Particular";
            if(em.find(ListaDeReproduccion_PorDefecto.class, lista) != null) tipo = "Por Defecto";
            //Reviso su categoria
            String categoria = "Ninguna";
            if(l.getCategoria() != null) categoria = l.getCategoria().getNombre();
            //Creo el datatype
            ldt = new ListaDeReproduccionDt(
                l.getId(), 
                l.getNombre(), 
                tipo, 
                l.getPrivada(), 
                categoria,
                id
            );
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
        return ldt;
    }

    @Override
    public List<String> ListarVideos(int userId) {
        List<String> lista = new ArrayList<>();
        try {
            EntityManager em = emFactory.createEntityManager();
            Canal c = em.find(Canal.class, userId);
            if(c == null) throw new Exception("El usuario no existe");
           
            Collection<Video> l = c.getVideos();
            if(l.isEmpty()) throw new Exception("El canal del usuario no tiene videos");
            
            Iterator<Video> it = l.iterator();
            while(it.hasNext()) {
                Video v = it.next();
                lista.add(v.getNombre());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
        return lista;
    }
}
