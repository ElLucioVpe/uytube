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
import logica.Comentario;
import logica.ListaDeReproduccion;
import logica.ListaDeReproduccion_PorDefecto;
import logica.ListaHistorial;
import logica.Usuario;
import logica.Valoracion;
import logica.Video;
import logica.Visita;
import logica.dt.CanalDt;
import logica.dt.ListaDeReproduccionDt;
import logica.dt.ListaHistorialDt;
import logica.dt.UsuarioDt;
import logica.dt.VideoDt;
import logica.dt.VisitaDt;

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
    	EntityManager emanager = emFactory.createEntityManager();
    	try {

            emanager.getTransaction().begin();

            if(emanager.createNamedQuery("Usuario.findByNickname", Usuario.class).setParameter("nickname", nick).getResultList().size() > 0)
                throw new Exception("El nickname ya existe");
            if(emanager.createNamedQuery("Usuario.findByMail", Usuario.class).setParameter("mail",mail).getResultList().size() > 0)
                throw new Exception("El mail ya esta registrado");
            if(!mail.matches(".*@.*[.].*")) 
                throw new Exception("El mail no es valido");
            
            Usuario user = new Usuario(nick, pass, nom, apell, mail, fnac);
            if(!img.isEmpty()) user.setImagen(img);

            emanager.persist(user);
            
        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
    	emanager.getTransaction().commit();
        emanager.close();
    }

    @Override
    public void AltaCanal(String nombre, boolean privado, String categoria, int user_id, String descripcion) {
    	EntityManager emanager = emFactory.createEntityManager();
    	try {
            emanager.getTransaction().begin();

            if(emanager.createNamedQuery("Canal.findByNombre", Canal.class).setParameter("nombre", nombre).getResultList().size() > 0)
                throw new Exception("El nombre del canal ya existe");

            Usuario user = emanager.find(Usuario.class, user_id);
            if(nombre.isEmpty()) nombre = user.getNickname();
            
            Canal cnl = new Canal(user_id, nombre, privado);
            if(!descripcion.isEmpty()) cnl.setDescripcion(descripcion);
            
            if(!categoria.equals("Ninguna")){
                Categoria cat = emanager.find(Categoria.class, categoria);
                if(cat == null) throw new Exception("La categoria no existe");
                cnl.setCategoria(cat);
            }
            
            cnl.setUsuario(user);
            user.setCanal(cnl);
            emanager.persist(cnl);
            emanager.merge(user);
            
            //Creacion de listas de reproduccion por defecto existentes
            List<ListaDeReproduccion_PorDefecto> listasD = emanager.createQuery("select l from ListaDeReproduccion_PorDefecto l", ListaDeReproduccion_PorDefecto.class).getResultList();
            if (!listasD.isEmpty()) {
                Iterator<ListaDeReproduccion_PorDefecto> iter = listasD.iterator();
                while(iter.hasNext()) {
                    ListaDeReproduccion_PorDefecto listad = iter.next();
                    ListaDeReproduccion lista = new ListaDeReproduccion(listad.getNombre(), user, true);
                    emanager.persist(lista);
                    cnl.addLista(lista);
                }
                emanager.merge(cnl);
            }
            //Creacion de lista historial
            ListaHistorial historial = new ListaHistorial(user);
            emanager.persist(historial);
            cnl.addLista(historial);
            emanager.merge(cnl);
            //
            
        } catch (Exception exc) {
            EliminarUsuario(user_id); //Elimino ya que no se completo correctamente todo el proceso
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
    	emanager.getTransaction().commit();
        emanager.close();
    }

    @Override
    public void ModificarUsuario(int id_user, String nuevopass, String nuevonom, String nuevoapell, Date nuevafechaNac, String nuevonomC, String nuevacatC, String nuevadesC, boolean nuevaprivC, String nuevaImg){
        //en su respectivo frame deberan antes ser utilizados
        //ListarUsuarios() y ConsultarUsuario(id)
        //los atributos que no se deseen modificar llegaran en blanco o null
    	EntityManager emanager = emFactory.createEntityManager();
        try {
            emanager.getTransaction().begin();

            Usuario user = emanager.find(Usuario.class, id_user);
            if(!nuevonom.isEmpty()) user.setNombre(nuevonom);
            if(!nuevoapell.isEmpty()) user.setApellido(nuevoapell);
            if(!nuevopass.isEmpty()) user.setPassword(nuevopass);
            if(nuevafechaNac != null) user.setFechanac(nuevafechaNac);
            if(!nuevaImg.isEmpty()) user.setImagen(nuevaImg);

            Canal cnl = emanager.find(Canal.class, user.getId()); //Por las dudas lo busco con find
            if(!nuevonomC.isEmpty()) cnl.setNombre(nuevonomC);
            if(!nuevadesC.isEmpty()) cnl.setDescripcion(nuevadesC);
            if(!nuevacatC.isEmpty()){
                Categoria cat = emanager.find(Categoria.class, nuevacatC);
                cnl.setCategoria(cat);
            }
          
            cnl.setPrivacidad(nuevaprivC); //al no poder comparar a null si no hay nueva damos la misma

            emanager.merge(cnl);
            emanager.merge(user);
            
        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
        emanager.getTransaction().commit();
        emanager.close();
        //Posteriormente en su respectivo frame se podra seleccionar para editar datos
        //de los videos o listas de reproduccion del usuario
        //ademas la imagen al llamarse igual ya que su nombre es el nick del usuario
        //simplemente sera reemplazada luego de finalizada la modificacion en caso de ser necesario
    }
    
    @Override
    public List<VideoDt> listarVideosDeUsuario(String usernick) {
        List<VideoDt> list = new ArrayList<>();
        EntityManager emanager = emFactory.createEntityManager();
        try {
            int idUser= obtenerIdUsuario(usernick);
            emanager.getTransaction().begin();
            
            Canal cnl = emanager.find(Canal.class, idUser);
            if(cnl == null) throw new Exception("El usuario no existe");
            
            Collection<Video> vid = cnl.getVideos();
            Iterator<Video> iter = vid.iterator();
            
            while(iter.hasNext()) {
                list.add(new VideoDt(iter.next()));
            }
            
        }catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
        emanager.getTransaction().commit();
        emanager.close();
        return list;
    }

    @Override
    public List<UsuarioDt> ListarUsuarios() {
        List<UsuarioDt> list = new ArrayList<>();
        EntityManager emanager = emFactory.createEntityManager();
        try {
        	emanager.getTransaction().begin();
            //probablemente devuelve una lista de los nicks de los usuarios existentes
            //esa lista luego es mostrada en su respectivo frameS
            
            List<Usuario> users = emanager.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
            for(int i=0;i < users.size(); i++) {
                if(users.get(i).getActivo()) list.add(new UsuarioDt(users.get(i)));
            }

        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
        emanager.getTransaction().commit();
        emanager.close();
        return list;
    }

    @Override
    public UsuarioDt ConsultarUsuario(int id_user) {
        UsuarioDt udt = null;
        EntityManager emanager = emFactory.createEntityManager();
        try {
            
            emanager.getTransaction().begin();
            Usuario user = emanager.find(Usuario.class, id_user);
            if(user == null) throw new Exception("El usuario no existe");
            
            CanalDt cnl = obtenerCanalDt(user.getId());
            
            List<Canal> saux = new ArrayList<>(user.getSuscripciones());
            ArrayList<String> suscripciones = new ArrayList<>();
            
            for(int i = 0; i < saux.size(); i++) suscripciones.add(saux.get(i).getNombre());

            udt = new UsuarioDt(
                    user.getId(),
                    user.getPassword(),
                    user.getNickname(),
                    user.getNombre(),
                    user.getApellido(),
                    user.getMail(),
                    user.getFechanac(),
                    user.getImagen(),
                    cnl,
                    user.getActivo(),
                    suscripciones
            );
            //Collection<Canal> sus = user.getSuscripciones();
            
            /*if(!sus.isEmpty()) {
                Iterator<Canal> iter = sus.iterator();
                while(iter.hasNext()){
                    suscripciones.add(iter.next().getUsuario().getNickname());
                }
            }
            
            udt = new UsuarioDt(
                    user.getId(),
                    user.getPassword(),
                    user.getNickname(),
                    user.getNombre(),
                    user.getApellido(),
                    user.getMail(),
                    user.getFechanac(),
                    user.getImagen(),
                    user.getCanal(),
                    user.getActivo(),
                    suscripciones
            );*/
            
        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
        emanager.getTransaction().commit();
        emanager.close();
        return udt;
    }

    //Listas de Reproduccion
    @Override
    public void AltaListaDeReproduccionPorDefecto(String nombre) {
    	EntityManager emanager = emFactory.createEntityManager();
        try {

            emanager.getTransaction().begin();

            if(emanager.find(ListaDeReproduccion_PorDefecto.class, nombre) != null)
                throw new Exception("El nombre de la lista ya existe");

            emanager.persist(new ListaDeReproduccion_PorDefecto(nombre));
            List<Canal> users = emanager.createQuery("SELECT c FROM Canal c", Canal.class).getResultList();

            Iterator<Canal> itr = users.iterator();
            while(itr.hasNext()) {
                Canal cnl = itr.next();
                ListaDeReproduccion lst = new ListaDeReproduccion(nombre, cnl.getUsuario(), true);
                emanager.persist(lst);
                cnl.addLista(lst);
                emanager.merge(cnl);
            }
            
        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
        emanager.getTransaction().commit();
        emanager.close();
    }


    @Override
    public void AltaListaDeReproduccionParticular(String nombre, int id_propietario, boolean privacidad, String categoria) {
    	EntityManager emanager = emFactory.createEntityManager();
    	try {

            emanager.getTransaction().begin();

            Canal propietario = emanager.find(Canal.class, id_propietario);
            
            
            if(propietario == null) throw new Exception("El usuario ingresado no existe");
            if(propietario.existeLista(nombre)) throw new Exception("El nombre de la lista ya existe");
            if(propietario.getPrivacidad() && !privacidad) throw new Exception("La lista no puede ser publica ya que el canal es privado");
            
            ListaDeReproduccion lista = new ListaDeReproduccion(nombre, propietario.getUsuario(), privacidad);
            if(!categoria.equals("Ninguna")) lista.setCategoria(emanager.find(Categoria.class, categoria));

            emanager.persist(lista);
            propietario.addLista(lista);
            emanager.merge(propietario);
            
        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
    	emanager.getTransaction().commit();
        emanager.close();
    }

    @Override
    public void ModificarListaDeReproduccion(int usuario, String lista, String nuevaCat, boolean nuevaPri) {
    	EntityManager emanager = emFactory.createEntityManager();
    	try {

            emanager.getTransaction().begin();

            Canal propietario = emanager.find(Canal.class, usuario);

            if(propietario == null) throw new Exception("El usuario ingresado no existe");
            if(!propietario.existeLista(lista)) throw new Exception("La lista no existe");
            if(propietario.getPrivacidad() && !nuevaPri) throw new Exception("La lista no puede ser publica ya que el canal es privado");
            
            ListaDeReproduccion _list = propietario.getLista(lista);
            if(!nuevaCat.equals("Ninguna")) _list.setCategoria(emanager.find(Categoria.class, nuevaCat));
            _list.setPrivada(nuevaPri); //Se supone que si no se va a cambiar nuevaPri tiene el valor anterior

            emanager.merge(_list);
            //propietario.modificarLista(l); //no deberia ser necesario
            emanager.merge(propietario);
            
        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
    	emanager.getTransaction().commit();
        emanager.close();
    }

    @Override
    public void AgregarVideoListaDeReproduccion(int usuarioVideo, int usuarioLista, String video, String lista) {
    	EntityManager emanager = emFactory.createEntityManager();
    	try {

            emanager.getTransaction().begin();

            Usuario user_video = emanager.find(Usuario.class, usuarioVideo);
            Canal canal_lista = emanager.find(Canal.class, usuarioLista);
            if(user_video == null) throw new Exception("El usuario propietario del video no existe");
            if(!user_video.getActivo()) throw new Exception("El usuario propietario del video esta inactivo");
            if(canal_lista == null) throw new Exception("El usuario propietario de la lista no existe");

            Canal canal_video = user_video.getCanal();
            Video vid = canal_video.obtenerVideo(video);
            if(vid == null) throw new Exception("El video no existe");

            canal_lista.agregarVideoLista(vid, lista);
            
            emanager.merge(canal_lista);
            
        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
    	emanager.getTransaction().commit();
        emanager.close();
    }

    @Override
    public void QuitarVideoListaDeReproduccion(int usuariolista, String lista, int video) {
    	EntityManager emanager = emFactory.createEntityManager();
    	try {

            emanager.getTransaction().begin();

            Canal canal_lista = emanager.find(Canal.class, usuariolista);
            if(canal_lista == null) throw new Exception("El usuario propietario de la lista no existe");

            canal_lista.quitarVideoLista(video, lista);

            emanager.merge(canal_lista);
            
        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
    	emanager.getTransaction().commit();
        emanager.close();
    }

    @Override
    public void actualizarVisitaListahistorial(int idVideo, int idUsuario){
    	EntityManager emanager = emFactory.createEntityManager();
    	try {            
    		emanager.getTransaction().begin();     

            Video vid = emanager.find(Video.class, idVideo);
            if(vid == null) throw new Exception("El video ingresado no existe");

            Canal propietario = emanager.find(Canal.class, idUsuario);
            if(propietario == null) throw new Exception("El usuario ingresado no existe");
            
            ListaHistorial historial = (ListaHistorial) emanager.createQuery("SELECT l from ListaDeReproduccion l WHERE DTYPE = 'ListaHistorial' AND ID_PROPIETARIO = :user", ListaDeReproduccion.class).setParameter("user", idUsuario).getSingleResult();
            if(historial == null) throw new Exception("La lista historial no existe");

            TypedQuery<Visita> query = emanager.createNamedQuery("Visita.findVisita", Visita.class).setParameter("userId", idUsuario).setParameter("videoId", idVideo);
            Visita vis = null;
            if(!query.getResultList().isEmpty()) vis = query.getSingleResult();
            System.out.println(vis);
            if(vis == null) { 
            	vis = new Visita(idUsuario, idVideo, new Date(), 0, vid, propietario.getUsuario());
            	emanager.persist(vis);
            	
            	if(!historial.getVideos().contains(vid)) { 
            		historial.agregarVideo(vid);
            		historial.agregarVisita(vis);
            	}
            }
            
            vis.actualizarVisita();
           	emanager.merge(vis);
           	emanager.merge(historial);
           
        }catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
    	emanager.getTransaction().commit();
        emanager.close();
    }
    
    @Override 
    public ListaHistorialDt obtenerListaHistorial(int idUsuario){
    	//List<ListaHistorialDt> historial = new ArrayList<>();
    	ListaHistorialDt historial = new ListaHistorialDt();
    	List<VisitaDt> visitas = new ArrayList<>();

        EntityManager emanager = emFactory.createEntityManager();
    	try {
        	emanager.getTransaction().begin();     

            Canal propietario = emanager.find(Canal.class, idUsuario);
            if(propietario == null) throw new Exception("El usuario ingresado no existe");
                
            List<Visita> vis = emanager.createQuery("SELECT v FROM Visita v WHERE v.userId = :idusuario ORDER BY CANTIDAD", Visita.class).setParameter("idusuario", idUsuario).getResultList();
            ListaHistorial listaH = emanager.createQuery("SELECT l from ListaHistorial l WHERE ID_PROPIETARIO = :idusuario", ListaHistorial.class).setParameter("idusuario", idUsuario).getSingleResult();
            if(vis == null) throw new Exception("El usuario no tiene Visitas");    
            
            Iterator<Visita> iter = vis.iterator();
            while(iter.hasNext()) {
                Visita visi = (Visita) iter.next();
                visitas.add(new VisitaDt(idUsuario, visi.getVideoId(), visi.getFecha(), visi.getCantidad()));
            
            }
            String cat = "Ninguna";
            if(listaH.getCategoria() != null) cat = listaH.getCategoria().getNombre();
            
            historial = new ListaHistorialDt(listaH.getId(), propietario.getUsuario(), listaH.getNombre(), cat, visitas, fechaUltimoVideo(listaH.getVideos()));
        }catch (Exception exc) {
            System.out.println(exc);
        }
    	emanager.getTransaction().commit();
        emanager.close();    
        return historial;
    } 

    @Override
    public void seguirUsuario(String seguidor, String seguido){
    	EntityManager emanager = emFactory.createEntityManager();
        try {
            
            emanager.getTransaction().begin();

            int idSeguido = obtenerIdUsuario(seguido);
            Canal cnl = emanager.find(Canal.class, idSeguido);
            if(cnl == null) throw new Exception("Ese usuario al que quiere seguir no existe o no tiene canal");
            if(!cnl.getUsuario().getActivo()) throw new Exception("Ese usuario al que quiere seguir se encuentra inactivo");
            
            int idSeguidor = obtenerIdUsuario(seguidor);
            Usuario uSeguidor = emanager.find(Usuario.class, idSeguidor);
            //System.out.println("--"+seguidor+"--"+obtenerIdUsuario(seguidor));
            if(uSeguidor == null) throw new Exception("El usuario seguidor no existe");
            
            if(cnl.getSeguidores().contains(uSeguidor)) throw new Exception("El usuario ya es seguidor del canal");
            
            cnl.agregarSeguidor(uSeguidor);
            uSeguidor.agregarSuscripcion(cnl);
            emanager.merge(cnl);
            emanager.merge(uSeguidor);
            
       } catch (Exception exc) {
           Throwable _throwable = new Throwable();
           StackTraceElement[] elements = _throwable.getStackTrace();
           String invocador = elements[1].getFileName();
           exceptionAux(invocador, exc);
       }
       emanager.getTransaction().commit();
       emanager.close();

    }

    @Override
    public List<String> ListarSeguidores(int userId) {
        List<String> seguidores = new ArrayList<>();
        EntityManager emanager = emFactory.createEntityManager();
        try {
            
            emanager.getTransaction().begin();
            
            Canal cnl = emanager.find(Canal.class, userId);
            if(cnl == null) throw new Exception("El usuario no existe"); //es lo mismo canal o usuario
            
            Collection<Usuario> seg = cnl.getSeguidores();
            Iterator<Usuario> iter = seg.iterator();
            while(iter.hasNext()) {
                seguidores.add(iter.next().getNickname());
            }
            
        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
        emanager.getTransaction().commit();
        emanager.close();
        return seguidores;
    }

    @Override
    public List<String> ListarSiguiendo(int userId) {
        List<String> suscripciones = new ArrayList<>();
        EntityManager emanager = emFactory.createEntityManager();
        try {
            emanager.getTransaction().begin();
            
            Usuario user = emanager.find(Usuario.class, userId);
            if(user == null) throw new Exception("El usuario no existe");
            
            Collection<Canal> sig = user.getSuscripciones();
            Iterator<Canal> iter = sig.iterator();
            while(iter.hasNext()) {
                Usuario userc = iter.next().getUsuario();
                suscripciones.add(userc.getNickname());
            }

        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
        emanager.getTransaction().commit();
        emanager.close();
        return suscripciones;
    }

    @Override
    public void dejarDeSeguirUsuario(String seguidor, String seguido) {
    	EntityManager emanager = emFactory.createEntityManager();
    	try {
            
            emanager.getTransaction().begin();
            
            int idSeguido = obtenerIdUsuario(seguido);
            Canal cnl = emanager.find(Canal.class, idSeguido);
            if(cnl == null) throw new Exception("Ese usuario al que quiere dejar de seguir no existe o no tiene canal");
            
            int idSeguidor = obtenerIdUsuario(seguidor);
            Usuario uSeguidor = emanager.find(Usuario.class, idSeguidor);
            //System.out.println("--"+seguidor+"--"+obtenerIdUsuario(seguidor));
            if(uSeguidor == null) throw new Exception("El usuario seguidor no existe");
            
            //con asegurarnos de uno de los lados de la relacion basta
            if(!cnl.getSeguidores().contains(uSeguidor)) throw new Exception("El usuario no es seguidor del canal");
            
            cnl.eliminarSeguidor(uSeguidor);
            uSeguidor.eliminarSuscripcion(cnl);
            emanager.merge(cnl);
            emanager.merge(uSeguidor);
            
    	} catch (Exception exc) {
           Throwable _throwable = new Throwable();
           StackTraceElement[] elements = _throwable.getStackTrace();
           String invocador = elements[1].getFileName();
           exceptionAux(invocador, exc);
    	}
    	emanager.getTransaction().commit();
        emanager.close();
    }
    //Auxiliares
    @Override
    public int obtenerIdUsuario(String nick) {
        int id_user = -1;
        EntityManager emanager = emFactory.createEntityManager();
        try {
            emanager.getTransaction().begin();
            
            TypedQuery<Usuario> query = emanager.createNamedQuery("Usuario.findByNickname", Usuario.class).setParameter("nickname", nick);
            //if (q.getResultList().isEmpty()) throw new Exception("El usuario no existe");
            if (!query.getResultList().isEmpty()) {
                Usuario usr = query.getSingleResult();
                id_user = usr.getId();
            }
            
            if (id_user == -1) throw new Exception("El usuario no existe");//puto catch
        } catch (Exception exc) {
            System.out.println(exc);
        }
        emanager.getTransaction().commit();
        emanager.close();
        return id_user;
    }
    
    @Override
    public int obtenerIdUsuarioMail(String mail) {
	    int id_user = -1;
	    EntityManager emanager = emFactory.createEntityManager();
	    try {
            
            emanager.getTransaction().begin();
            
            TypedQuery<Usuario> query = emanager.createNamedQuery("Usuario.findByMail", Usuario.class).setParameter("mail", mail);
            
            //if (q.getResultList().isEmpty()) throw new Exception("El usuario no existe");
            if (!query.getResultList().isEmpty()){ 
                Usuario usr = query.getSingleResult();
                id_user = usr.getId();
            }
            if (id_user == -1) throw new Exception("El usuario con ese mail no existe");

        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
	    emanager.getTransaction().commit();
        emanager.close();
        return id_user;
    }

    @Override
    public String obtenerNickUsuario(int id_user) {
        String nick = "";
        EntityManager emanager = emFactory.createEntityManager();
        try {

            emanager.getTransaction().begin();
            
            Usuario user = emanager.find(Usuario.class, id_user);
            if (user == null) throw new Exception("El usuario no existe");

            nick = user.getNickname();
            
        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
        emanager.getTransaction().commit();
        emanager.close();
        return nick;
    }
    
    @Override
    public void EliminarUsuario(int id_user) {
        try {

            EntityManager emanager = emFactory.createEntityManager();
            emanager.getTransaction().begin();

            Usuario user = emanager.find(Usuario.class, id_user);
            if(user == null) throw new Exception("El usuario no existe");
            
            Canal cnl = emanager.find(Canal.class, id_user);
            
            emanager.remove(cnl);
            emanager.remove(user);
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
    public List<String> obtenerCategorias() {
        List<String> lretorno = new ArrayList<String>();
        try {
            EntityManager emanager = emFactory.createEntityManager();
            emanager.getTransaction().begin();

            lretorno = emanager.createQuery("select c.nombre from Categoria c", String.class).getResultList();

            emanager.getTransaction().commit();
            emanager.close();
        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
        return lretorno;
    }

    @Override
    public List<String> obtenerListasUsuario(int id_user) {
        List<String> retorno = new ArrayList<>();
        try {
            EntityManager emanager = emFactory.createEntityManager();
            emanager.getTransaction().begin();

            Canal cnl = emanager.find(Canal.class, id_user);
            if(cnl == null) throw new Exception("El usuario no existe");
            Collection<ListaDeReproduccion> aux = cnl.getListas();

            Iterator<ListaDeReproduccion> iter = aux.iterator();
            while(iter.hasNext()) {
                retorno.add(iter.next().getNombre());
            }

            emanager.getTransaction().commit();
            emanager.close();
        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
        return retorno;
    }

    @Override
    public List<VideoDt> obtenerVideosLista(int id_user, String lista) {
        List<VideoDt> lretorno = new ArrayList<>();
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();

            Canal cnl = em.find(Canal.class, id_user);
            if(cnl == null) throw new Exception("El usuario no existe");
            Collection<Video> lvideo = cnl.getLista(lista).getVideos();

            Iterator<Video> iter = lvideo.iterator();
            while(iter.hasNext()) {
                Video vid = iter.next();
                lretorno.add(new VideoDt(vid));
            }

        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
        em.getTransaction().commit();
        em.close();
        return lretorno;
    }
    
    @Override
    public String obtenerTipoLista(int propietario, String lista) {
        String tipo = "Privada";
        try {
            EntityManager emanager = emFactory.createEntityManager();
            emanager.getTransaction().begin();

            Canal cnl = emanager.find(Canal.class, propietario);
            if(cnl == null) throw new Exception("El usuario no existe");
            ListaDeReproduccion lst = cnl.getLista(lista);
            if(lst == null) throw new Exception("El usuario no tiene ninguna lista con ese nombre");
            
            if(emanager.find(ListaDeReproduccion_PorDefecto.class, lista) != null) tipo = "Por Defecto";
            
            emanager.getTransaction().commit();
            emanager.close();
        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
        return tipo;
    }
    
    @Override
    public ListaDeReproduccionDt obtenerListaDt(int id, String lista) {
        ListaDeReproduccionDt ldt = null;
        EntityManager emanager = emFactory.createEntityManager();
        try {
            
            emanager.getTransaction().begin();

            Canal cnl = emanager.find(Canal.class, id);
            if(cnl == null) throw new Exception("El usuario no existe");
            ListaDeReproduccion lst = cnl.getLista(lista);
            if(lst == null) throw new Exception("El usuario no tiene ninguna lista con ese nombre");
            
            if(lst instanceof ListaHistorial) {
            	ldt = this.obtenerListaHistorial(lst.getUsuario().getId());
            } else {
	            //Reviso su tipo
	            String tipo = "Particular";
	            if(emanager.find(ListaDeReproduccion_PorDefecto.class, lista) != null) tipo = "Por Defecto";
	            //Reviso su categoria
	            String categoria = "Ninguna";
	            if(lst.getCategoria() != null) categoria = lst.getCategoria().getNombre();
	            //Reviso fecha del ultimo video
	            Date _date = fechaUltimoVideo(lst.getVideos());
	            //Creo el datatype
	            ldt = new ListaDeReproduccionDt(
	                lst.getId(), 
	                lst.getNombre(), 
	                tipo, 
	                lst.getPrivada(), 
	                categoria,
	                id,
	                _date
	            );
            }
        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
        emanager.getTransaction().commit();
        emanager.close();
        return ldt;
    }

    @Override
    public List<String> ListarVideos(int userId) {
        List<String> lista = new ArrayList<>();
        EntityManager emanager = emFactory.createEntityManager();
        try {
            emanager.getTransaction().begin();
            
            Canal cnl = emanager.find(Canal.class, userId);
            if(cnl == null) throw new Exception("El usuario no existe");
           
            Collection<Video> videos = cnl.getVideos();
            if(videos.isEmpty()) throw new Exception("El canal del usuario no tiene videos");
            
            Iterator<Video> iter = videos.iterator();
            while(iter.hasNext()) {
                Video vid = iter.next();
                lista.add(vid.getNombre());
            }
        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
        emanager.getTransaction().commit();
        emanager.close();
        return lista;
    }
    
    private void exceptionAux(String inv, Exception exc){
        if(!inv.startsWith("WS") && !inv.endsWith("_jsp.java") && !inv.endsWith("Test.java")){
            JOptionPane.showMessageDialog(null," Error: "+exc.getMessage());
        } else {
            System.out.println("Error: "+exc.getMessage());
        }
    }
    
    //Para el servidor web
    @Override
    public Integer LoginUsuario(String _user, String _password) {
        Integer userId = -1;
        EntityManager emanager = emFactory.createEntityManager();
        try {
            emanager.getTransaction().begin();
            
            int _id = obtenerIdUsuario(_user);
            Usuario usr = emanager.find(Usuario.class, _id);
            if(usr != null) {
            	if(!usr.getActivo()) throw new Exception("El usuario esta inactivo");
            	
                if (usr.getNickname().equals(_user) && usr.getPassword().equals(_password)) {
                    userId = _id;
                }
            }
        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
        emanager.getTransaction().commit();
        emanager.close();
        return userId;
    }
    
    @Override
    public boolean estaSuscripto(int suscripto, int pcanal) {
        boolean _si = false;
        EntityManager emanager = emFactory.createEntityManager();
        try{
            emanager.getTransaction().begin();
            
            Usuario usr = emanager.find(Usuario.class, suscripto);
            if(usr == null) throw new Exception("No se encontro el usuario suscriptor");
            Canal cnl = emanager.find(Canal.class, pcanal);
            if(cnl == null) throw new Exception("No se encontro al propietario del canal");
            
            if(cnl.getSeguidores().contains(usr)) _si = true;

        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
        emanager.getTransaction().commit();
        emanager.close();
        return _si;
    }
    
    @Override
    public List<ListaDeReproduccionDt> obtenerListasDtPorUsuario(int id_user) {
            List<ListaDeReproduccionDt> list = new ArrayList<>();
            EntityManager emanager = emFactory.createEntityManager();
            try {
                emanager.getTransaction().begin();
            	
                Canal cnl = emanager.find(Canal.class, id_user);
                if(cnl == null) throw new Exception("El usuario no existe");
                
                Collection<ListaDeReproduccion> listas = cnl.getListas();
                Iterator<ListaDeReproduccion> iter = listas.iterator();
                while (iter.hasNext()) {
                    ListaDeReproduccion lst = iter.next();
                    if(lst instanceof ListaHistorial) {
                    	list.add(this.obtenerListaHistorial(lst.getUsuario().getId()));
                    } else {
	                    //Reviso su tipo
	                    String tipo = "Particular";
	                    if(emanager.find(ListaDeReproduccion_PorDefecto.class, lst.getNombre()) != null) tipo = "Por Defecto";
	                    //Reviso su categoria
	                    String categoria = "Ninguna";
	                    if(lst.getCategoria() != null) categoria = lst.getCategoria().getNombre();
	                    //Reviso fecha del ultimo video
	                    Date _date = fechaUltimoVideo(lst.getVideos());
	                    //Creo el datatype
	                    list.add(new ListaDeReproduccionDt(
	                        lst.getId(), 
	                        lst.getNombre(), 
	                        tipo, 
	                        lst.getPrivada(), 
	                        categoria,
	                        id_user,
	                        _date
	                    ));
                    }
                }
                
       } catch (Exception exc) {
           Throwable _throwable = new Throwable();
           StackTraceElement[] elements = _throwable.getStackTrace();
           String invocador = elements[1].getFileName();
           exceptionAux(invocador, exc);
       }
       emanager.getTransaction().commit();
       emanager.close();
       return list;
    }
    
    @Override
    public List<ListaDeReproduccionDt> obtenerListas() {
            List<ListaDeReproduccionDt> list = new ArrayList<>();
            EntityManager emanager = emFactory.createEntityManager();
            try {
                emanager.getTransaction().begin();
                
                List<ListaDeReproduccion> listas = emanager.createQuery("SELECT l FROM ListaDeReproduccion l", ListaDeReproduccion.class).getResultList();
                Iterator<ListaDeReproduccion> iter = listas.iterator();
                
                while (iter.hasNext()) {
                    ListaDeReproduccion lst = iter.next();
                    if(lst.getUsuario().getActivo()) {
                    	if(lst instanceof ListaHistorial) {
                        	list.add(this.obtenerListaHistorial(lst.getUsuario().getId()));
                        } else {
		                    //Reviso su tipo
		                    String tipo = "Particular";
		                    if(emanager.find(ListaDeReproduccion_PorDefecto.class, lst.getNombre()) != null) tipo = "Por Defecto";
		                    //Reviso su categoria
		                    String categoria = "Ninguna";
		                    if(lst.getCategoria() != null) categoria = lst.getCategoria().getNombre();
		                    //Reviso fecha del ultimo video
		                    Date _date = fechaUltimoVideo(lst.getVideos());
		                    //Creo el datatype
		                    list.add(new ListaDeReproduccionDt(
		                        lst.getId(), 
		                        lst.getNombre(), 
		                        tipo, 
		                        lst.getPrivada(), 
		                        categoria,
		                        lst.getUsuario().getId(),
		                        _date
		                    ));
                        }
                    }
                }
	       } catch (Exception exc) {
	           Throwable _throwable = new Throwable();
	           StackTraceElement[] elements = _throwable.getStackTrace();
	           String invocador = elements[1].getFileName();
	           exceptionAux(invocador, exc);
	       }
	       emanager.getTransaction().commit();
	       emanager.close();
	       return list;
    }
    
    @Override
    public ListaDeReproduccionDt obtenerListaDtPorId(int id_lista) {
        ListaDeReproduccionDt ldt = null;
        EntityManager emanager = emFactory.createEntityManager();
        try {
            
            emanager.getTransaction().begin();
            
            ListaDeReproduccion lst = emanager.find(ListaDeReproduccion.class, id_lista);
            if(lst == null) throw new Exception("La lista no existe");
            
            if(lst instanceof ListaHistorial) {
            	ldt = this.obtenerListaHistorial(lst.getUsuario().getId());
            } else {
	            //Reviso su categoria
	            String categoria = "Ninguna";
	            if(lst.getCategoria() != null) categoria = lst.getCategoria().getNombre();
	            //Reviso fecha del ultimo video
	            Date _date = fechaUltimoVideo(lst.getVideos());
	            //Reviso su tipo
	            String tipo = "Particular";
	            if(emanager.find(ListaDeReproduccion_PorDefecto.class, lst.getNombre()) != null) tipo = "Por Defecto";
	            //Creo el datatype
	            ldt = new ListaDeReproduccionDt(
	                lst.getId(), 
	                lst.getNombre(), 
	                tipo, 
	                lst.getPrivada(), 
	                categoria,
	                id_lista,
	                _date
	            );
            }
            
        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
        emanager.getTransaction().commit();
        emanager.close();
        return ldt;
    }
    
    @Override
    public CanalDt obtenerCanalDt(int id) {
        CanalDt cdt = null;
        EntityManager emanager = emFactory.createEntityManager();
        try {
            emanager.getTransaction().begin();
            
            Canal cnl = emanager.find(Canal.class, id);
            if(cnl == null) throw new Exception("El canal no existe");
            
            List<Video> videoscnl = new ArrayList<>(cnl.getVideos());
            List<ListaDeReproduccion> listascnl = new ArrayList<>(cnl.getListas());
            List<Usuario> saux = new ArrayList<>(cnl.getSeguidores());
            
            List<VideoDt> videos = new ArrayList<>();
            List<ListaDeReproduccionDt> listas = new ArrayList<>();
            List<String> seguidores = new ArrayList<>();
            
            for(int i = 0; i < saux.size(); i++) seguidores.add(saux.get(i).getNickname());
            for(int i=0;i < videoscnl.size(); i++) videos.add(new VideoDt(videoscnl.get(i)));
            for(int i=0;i < listascnl.size(); i++) { 
            	
            	ListaDeReproduccion lista = listascnl.get(i);
            	Categoria cat = lista.getCategoria();
            	String categoria = "Ninguna";
            	if(cat != null) categoria = cat.getNombre();
            	
            	listas.add(new ListaDeReproduccionDt(
            			lista.getId(),
            			lista.getNombre(),
            			this.obtenerTipoLista(lista.getUsuario().getId(), lista.getNombre()),
            			lista.getPrivada(),
            			categoria,
            			lista.getUsuario().getId(),
            			fechaUltimoVideo(lista.getVideos())
            	));
            }

            cdt = new CanalDt(cnl, videos, listas, seguidores);
            
        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
        emanager.getTransaction().commit();
        emanager.close();
        return cdt;
    }
    
    protected Date fechaUltimoVideo(Collection<Video> videos) {
        Date retorno = null;
        
        if(videos != null) {
            Iterator<Video> iter = videos.iterator();
            
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date ultima = sdf.parse("1990-01-01");
                
                while(iter.hasNext()) {
                    Video aux = iter.next();
                    if(aux.getFechaPublicacion().after(ultima)) ultima = aux.getFechaPublicacion();
                }
                retorno = ultima;
            } catch (Exception exc) {
                Throwable _throwable = new Throwable();
                StackTraceElement[] elements = _throwable.getStackTrace();
                String invocador = elements[1].getFileName();
                exceptionAux(invocador, exc);
            }
        }
        return retorno;
    }
    
    //Para pruebas
    @Override
    public void EliminarListaDeReproduccionPorDefecto(String nombre) {
    	EntityManager emanager = emFactory.createEntityManager();
        try {
            emanager.getTransaction().begin();

            ListaDeReproduccion_PorDefecto list_pordefecto = emanager.find(ListaDeReproduccion_PorDefecto.class, nombre);
            if(list_pordefecto == null) throw new Exception("La lista no existe");
            
            List<Canal> canales = emanager.createQuery("SELECT c FROM Canal c", Canal.class).getResultList();
            for(int i=0; i < canales.size(); i++) {
            	Canal cnl = canales.get(i);
                ListaDeReproduccion lst = cnl.getLista(nombre);
                if(lst != null) emanager.remove(lst);
                //emanager.merge(cnl);
            }
            emanager.remove(list_pordefecto);
            
        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
        emanager.getTransaction().commit();
        emanager.close();
    }
    
    @Override
    public void EliminarListaDeReproduccionParticular(int id_user, String nombre) {
    	EntityManager emanager = emFactory.createEntityManager();
    	try {
            emanager.getTransaction().begin();

            Canal cnl = emanager.find(Canal.class, id_user);
            if(cnl == null) throw new Exception("El canal no existe");
            
            ListaDeReproduccion lst = cnl.getLista(nombre);
            Collection<Video> videos = lst.getVideos();
            Iterator<Video> iter = videos.iterator();
            while(iter.hasNext()) { lst.quitarVideo(iter.next().getId());}
            
            emanager.remove(lst);
            
        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
    	emanager.getTransaction().commit();
        emanager.close();
    }
    
    //Parte 3
    @Override
    public void BajaUsuario(int iduser) {
    	EntityManager emanager = emFactory.createEntityManager();
        try {
        	emanager.getTransaction().begin();
        	
        	Usuario usr = emanager.find(Usuario.class, iduser);
        	if(usr == null) throw new Exception("El usuario no existe");
        	if(!usr.getActivo()) throw new Exception("El usuario ya se dio de baja");
        	
        	usr.setValoraciones(new ArrayList<Valoracion>()); //Vacio las valoraciones
        	usr.setSuscripciones(new ArrayList<Canal>()); //Vacio las suscripciones
        	
        	Canal cnl = usr.getCanal();
        	cnl.setSeguidores(new ArrayList<Usuario>()); //Vacio los seguidores

        	List<Comentario> comentarios = emanager.createQuery("SELECT c FROM Comentario c where USER_ID = :userid", Comentario.class).setParameter("userid", usr.getId()).getResultList();
        	
        	for(int i = 0; i < comentarios.size(); i++) {
        		emanager.remove(comentarios.get(i));
        	}
        	
        	List<Video> videos = new ArrayList<>(cnl.getVideos());
        	for(int i = 0; i < videos.size(); i++) {
        		List<Visita> visitas = emanager.createQuery("SELECT v FROM Visita v where v.videoId = :videoid", Visita.class).setParameter("videoid", videos.get(i).getId()).getResultList();
        		for(int j = 0; j < visitas.size(); j++) {
        			emanager.remove(visitas.get(j));
        			
        		}
        	}
        	
        	usr.setActivo(false);
        	usr.setCanal(cnl);
        	emanager.merge(cnl);
        	emanager.merge(usr);
        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
        emanager.getTransaction().commit();
        emanager.close();
    }

    
    @Override
    public List<UsuarioDt> ListarUsuariosInactivos() {
        List<UsuarioDt> list = new ArrayList<>();
        EntityManager emanager = emFactory.createEntityManager();
        try {
        	emanager.getTransaction().begin();
            //probablemente devuelve una lista de los nicks de los usuarios existentes
            //esa lista luego es mostrada en su respectivo frameS
            
            List<Usuario> users = emanager.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
            for(int i=0;i < users.size(); i++) {
                if(!users.get(i).getActivo()) list.add(new UsuarioDt(users.get(i)));
            }

        } catch (Exception exc) {
            Throwable _throwable = new Throwable();
            StackTraceElement[] elements = _throwable.getStackTrace();
            String invocador = elements[1].getFileName();
            exceptionAux(invocador, exc);
        }
        emanager.getTransaction().commit();
        emanager.close();
        return list;
    }
}
