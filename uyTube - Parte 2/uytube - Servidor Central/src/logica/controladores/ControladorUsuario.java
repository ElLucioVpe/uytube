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
import logica.dt.CanalDt;
import logica.dt.ListaDeReproduccionDt;
import logica.dt.UsuarioDt;
import logica.dt.VideoDt;

/**
 *
 * @author Esteban
 */
public class ControladorUsuario implements IControladorUsuario {
    
    private static ControladorUsuario instancia;
    private final EntityManagerFactory emFactory;

    private ControladorUsuario() {
        emFactory = Persistence.createEntityManagerFactory("UyTubePU");
    }
    
    public static ControladorUsuario getInstance() {
        if (instancia == null) {
            instancia = new ControladorUsuario();
        }
        return instancia;
    }

    @Override
    public void AltaUsuario(String nick, String pass, String nom, String apell, String mail, Date fnac, String img) {
        try {

            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();

            if(em.createNamedQuery("Usuario.findByNickname", Usuario.class).setParameter("nickname", nick).getResultList().size() > 0)
                throw new Exception("El nickname ya existe");
            if(em.createNamedQuery("Usuario.findByMail", Usuario.class).setParameter("mail",mail).getResultList().size() > 0)
                throw new Exception("El mail ya esta registrado");
            if(!mail.matches(".*@.*[.].*")) 
                throw new Exception("El mail no es valido");
            
            Usuario u = new Usuario(nick, pass, nom, apell, mail, fnac);
            if(!img.isEmpty()) u.setImagen(img);

            em.persist(u);
            em.getTransaction().commit();
            em.close();

        } catch (Exception e) {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, e);
        }
    }

    @Override
    public void AltaCanal(String nombre, boolean privado, String categoria, int user_id, String descripcion) {
        try {

            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();

            if(em.createNamedQuery("Canal.findByNombre", Canal.class).setParameter("nombre", nombre).getResultList().size() > 0)
                throw new Exception("El nombre del canal ya existe");

            Usuario u = em.find(Usuario.class, user_id);
            if(nombre.isBlank()) nombre = u.getNickname();
            
            Canal c = new Canal(user_id, nombre, privado);
            if(!descripcion.isEmpty()) c.setDescripcion(descripcion);
            
            if(!categoria.equals("Ninguna")){
                Categoria cat = em.find(Categoria.class, categoria);
                if(cat == null) throw new Exception("La categoria no existe");
                c.setCategoria(cat);
            }
            
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
        } catch (Exception e) {
            EliminarUsuario(user_id); //Elimino ya que no se completo correctamente todo el proceso
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, e);
        }
    }

    @Override
    public void ModificarUsuario(int id, String nuevopass, String nuevonom, String nuevoapell, Date nuevafechaNac, String nuevonomC, String nuevadesC, boolean nuevaprivC){
        //en su respectivo frame deberan antes ser utilizados
        //ListarUsuarios() y ConsultarUsuario(id)
        //los atributos que no se deseen modificar llegaran en blanco o null
        try {

            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();

            Usuario u = em.find(Usuario.class, id);
            if(!nuevonom.isBlank()) u.setNombre(nuevonom);
            if(!nuevoapell.isBlank()) u.setApellido(nuevoapell);
            if(!nuevopass.isBlank()) u.setPassword(nuevopass);
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
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, e);
        }
        //Posteriormente en su respectivo frame se podra seleccionar para editar datos
        //de los videos o listas de reproduccion del usuario
        //ademas la imagen al llamarse igual ya que su nombre es el nick del usuario
        //simplemente sera reemplazada luego de finalizada la modificacion en caso de ser necesario
    }
    
    @Override
    public List<VideoDt> listarVideosDeUsuario(String usernick){
        List<VideoDt> list = new ArrayList<>();
        try {
            int idUser= obtenerIdUsuario(usernick);
            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();
            
            Canal c = em.find(Canal.class, idUser);
            if(c == null) throw new Exception("El usuario no existe");
            
            Collection<Video> vid = c.getVideos();
            Iterator<Video> it = vid.iterator();
            
            while(it.hasNext()) {
                list.add(new VideoDt(it.next()));
            }
            em.getTransaction().commit();
            em.close();
        }catch (Exception e) {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, e);
        }
        return list;

    }

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
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, e);
        }
        return list;
    }

    @Override
    public UsuarioDt ConsultarUsuario(int id){
        UsuarioDt dt = null;
        try {
            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();
            Usuario u = em.find(Usuario.class, id);
            
            List<String> suscripciones = new ArrayList<>(); 
            Collection<Canal> sus = u.getSuscripciones();
            
            if(!sus.isEmpty()) {
                Iterator<Canal> it = sus.iterator();
                while(it.hasNext()){
                    suscripciones.add(it.next().getUsuario().getNickname());
                }
            }
            
            dt = new UsuarioDt(
                    u.getId(),
                    u.getPassword(),
                    u.getNickname(),
                    u.getNombre(),
                    u.getApellido(),
                    u.getMail(),
                    u.getFechanac(),
                    u.getImagen(),
                    u.getCanal(),
                    suscripciones
            );
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, e);
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
        } catch (Exception e) {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, e);
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
        } catch (Exception e) {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, e);
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
        } catch (Exception e) {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, e);
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
        } catch (Exception e) {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, e);
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
        } catch (Exception e) {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, e);
        }
    }

    @Override
    public void seguirUsuario(String seguidor, String seguido){
        try {
            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();

            Canal c = em.find(Canal.class, obtenerIdUsuario(seguido));
            if(c == null) throw new Exception("Ese usuario al que quiere seguir no existe o no tiene canal");
            Usuario uSeguidor = em.find(Usuario.class, obtenerIdUsuario(seguidor));
            if(uSeguidor == null) throw new Exception("El usuario seguidor no existe");
            if(c.getSeguidores().contains(uSeguidor)) throw new Exception("El usuario ya es seguidor del canal");
            
            c.agregarSeguidor(uSeguidor);
            uSeguidor.agregarSuscripcion(c);
            em.merge(c);
            em.merge(uSeguidor);
            em.getTransaction().commit();
            em.close();
       } catch (Exception e) {
           Throwable t = new Throwable();
           StackTraceElement[] elements = t.getStackTrace();
           String invocador = elements[1].getFileName();
           exceptionAux(invocador, e);
       }

    }

    @Override
    public List<String> ListarSeguidores(int userId){
        List<String> seguidores = new ArrayList<>();
        try {
            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();
            
            Canal c = em.find(Canal.class, userId);
            if(c == null) throw new Exception("El usuario no existe"); //es lo mismo canal o usuario
            
            Collection<Usuario> seg = c.getSeguidores();
            Iterator<Usuario> it = seg.iterator();
            while(it.hasNext()) {
                seguidores.add(it.next().getNickname());
            }
            
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, e);
        }
        return seguidores;
    }

    @Override
    public List<String> ListarSiguiendo(int userId){
        List<String> suscripciones = new ArrayList<>();
        try {
            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();
            
            Usuario u = em.find(Usuario.class, userId);
            if(u == null) throw new Exception("El usuario no existe");
            
            Collection<Canal> sig = u.getSuscripciones();
            Iterator<Canal> it = sig.iterator();
            while(it.hasNext()) {
                Usuario uc = it.next().getUsuario();
                suscripciones.add(uc.getNickname());
            }
            
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, e);
        }
        return suscripciones;
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
       } catch (Exception e) {
           Throwable t = new Throwable();
           StackTraceElement[] elements = t.getStackTrace();
           String invocador = elements[1].getFileName();
           exceptionAux(invocador, e);
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
            //if (q.getResultList().isEmpty()) throw new Exception("El usuario no existe");
            if (!q.getResultList().isEmpty()){ 
                Usuario u = q.getSingleResult();
                id = u.getId();
            }
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, e);
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
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, e);
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
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, e);
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
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, e);
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
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, e);
        }
        return l;
    }

    @Override
    public List<VideoDt> obtenerVideosLista(int id, String lista) {
        List<VideoDt> l = new ArrayList<>();
        try {
            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();

            Canal c = em.find(Canal.class, id);
            if(c == null) throw new Exception("El usuario no existe");
            Collection<Video> lvideo = c.getLista(lista).getVideos();

            Iterator it = lvideo.iterator();
            while(it.hasNext()) {
                Video v = (Video) it.next();
                l.add(new VideoDt(v));
            }

            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, e);
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
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, e);
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
            //Reviso fecha del ultimo video
            Date d = fechaUltimoVideo(l.getVideos());
            //Creo el datatype
            ldt = new ListaDeReproduccionDt(
                l.getId(), 
                l.getNombre(), 
                tipo, 
                l.getPrivada(), 
                categoria,
                id,
                d
            );
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, e);
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
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, e);
        }
        return lista;
    }
    
    private void exceptionAux(String inv, Exception e){
        if(!inv.endsWith("_jsp.java")){
            JOptionPane.showMessageDialog(null," Error: "+e.getMessage());
        } else {
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    //Para el servidor web
    @Override
    public Integer LoginUsuario(String _user, String _password) {
        Integer userId = null;
        try {
            EntityManager em = emFactory.createEntityManager();
            
            int _id = obtenerIdUsuario(_user);
            Usuario u = em.find(Usuario.class, _id);
            if(u != null) {
                if (u.getNickname().equals(_user) && u.getPassword().equals(_password)) {
                    userId = _id;
                }
            }
            em.close();
        } catch (Exception e) {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, e);
        }
        return userId;
    }
    
    @Override
    public boolean estaSuscripto(int suscripto, int pcanal) {
        boolean si = false;
        try{
            EntityManager em = emFactory.createEntityManager();
            
            Usuario u = em.find(Usuario.class, suscripto);
            if(u == null) throw new Exception("No se encontro el usuario suscriptor");
            Canal c = em.find(Canal.class, pcanal);
            if(u == null) throw new Exception("No se encontro al propietario del canal");
            
            if(c.getSeguidores().contains(u)) si = true;
            
            em.close();
        }
        catch (Exception e) {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, e);
        }
        return si;
    }
    
    @Override
    public List<ListaDeReproduccionDt> obtenerListasDtPorUsuario(int id) {
            List<ListaDeReproduccionDt> list = new ArrayList<>();
            try {
                EntityManager em = emFactory.createEntityManager();
                
                Canal c = em.find(Canal.class, id);
                if(c == null) throw new Exception("El usuario no existe");
                
                Collection<ListaDeReproduccion> l = c.getListas();
                Iterator<ListaDeReproduccion> it = l.iterator();
                while (it.hasNext()) {
                    ListaDeReproduccion li = it.next();
                    //Reviso su tipo
                    String tipo = "Particular";
                    if(em.find(ListaDeReproduccion_PorDefecto.class, li.getNombre()) != null) tipo = "Por Defecto";
                    //Reviso su categoria
                    String categoria = "Ninguna";
                    if(li.getCategoria() != null) categoria = li.getCategoria().getNombre();
                    //Reviso fecha del ultimo video
                    Date d = fechaUltimoVideo(li.getVideos());
                    //Creo el datatype
                    list.add(new ListaDeReproduccionDt(
                        li.getId(), 
                        li.getNombre(), 
                        tipo, 
                        li.getPrivada(), 
                        categoria,
                        id,
                        d
                    ));
                }
                em.close();
       } catch (Exception e) {
           Throwable t = new Throwable();
           StackTraceElement[] elements = t.getStackTrace();
           String invocador = elements[1].getFileName();
           exceptionAux(invocador, e);
       }
       return list;
    }
    
    @Override
    public List<ListaDeReproduccionDt> obtenerListas() {
            List<ListaDeReproduccionDt> list = new ArrayList<>();
            try {
                EntityManager em = emFactory.createEntityManager();
                
                List<ListaDeReproduccion> l = em.createQuery("SELECT l FROM ListaDeReproduccion l", ListaDeReproduccion.class).getResultList();
                Iterator<ListaDeReproduccion> it = l.iterator();
                
                while (it.hasNext()) {
                    ListaDeReproduccion li = it.next();
                    //Reviso su tipo
                    String tipo = "Particular";
                    if(em.find(ListaDeReproduccion_PorDefecto.class, li.getNombre()) != null) tipo = "Por Defecto";
                    //Reviso su categoria
                    String categoria = "Ninguna";
                    if(li.getCategoria() != null) categoria = li.getCategoria().getNombre();
                    //Reviso fecha del ultimo video
                    Date d = fechaUltimoVideo(li.getVideos());
                    //Creo el datatype
                    list.add(new ListaDeReproduccionDt(
                        li.getId(), 
                        li.getNombre(), 
                        tipo, 
                        li.getPrivada(), 
                        categoria,
                        li.getUsuario().getId(),
                        d
                    ));
                }
                em.close();
       } catch (Exception e) {
           Throwable t = new Throwable();
           StackTraceElement[] elements = t.getStackTrace();
           String invocador = elements[1].getFileName();
           exceptionAux(invocador, e);
       }
       return list;
    }
    
    @Override
    public ListaDeReproduccionDt obtenerListaDtPorId(int id) {
        ListaDeReproduccionDt ldt = null;
        try {
            EntityManager em = emFactory.createEntityManager();
            em.getTransaction().begin();
            
            ListaDeReproduccion l = em.find(ListaDeReproduccion.class, id);
            if(l == null) throw new Exception("La lista no existe");
            
            //Reviso su tipo
            String tipo = "Particular";
            if(em.find(ListaDeReproduccion_PorDefecto.class, l.getNombre()) != null) tipo = "Por Defecto";
            //Reviso su categoria
            String categoria = "Ninguna";
            if(l.getCategoria() != null) categoria = l.getCategoria().getNombre();
            //Reviso fecha del ultimo video
            Date d = fechaUltimoVideo(l.getVideos());
            //Creo el datatype
            ldt = new ListaDeReproduccionDt(
                l.getId(), 
                l.getNombre(), 
                tipo, 
                l.getPrivada(), 
                categoria,
                id,
                d
            );
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, e);
        }
        return ldt;
    }
    
    @Override
    public CanalDt obtenerCanalDt(int id) {
        CanalDt dt = null;
        try {
            EntityManager em = emFactory.createEntityManager();
            
            Canal c = em.find(Canal.class, id);
            if(c == null) throw new Exception("El canal no existe");
            
            dt = new CanalDt(c);
            em.close();
        } catch (Exception e) {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, e);
        }
        return dt;
    }
    
    private Date fechaUltimoVideo(Collection<Video> videos) {
        Date retorno = null;
        
        if(videos != null) {
            Iterator<Video> it = videos.iterator();
            
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date ultima = sdf.parse("1990-01-01");
                
                while(it.hasNext()) {
                    Video aux = it.next();
                    if(aux.getFechaPublicacion().after(ultima)) ultima = aux.getFechaPublicacion();
                }
                retorno = ultima;
            }catch(Exception e){}
        }
        return retorno;
    }
}
