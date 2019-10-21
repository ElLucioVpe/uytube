package logica.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import logica.controladores.Fabrica;
import logica.controladores.IControladorUsuario;
import logica.controladores.IControladorVideo;
import logica.dt.CanalDt;
import logica.dt.CategoriaDt;
import logica.dt.ListaDeReproduccionDt;
import logica.dt.UsuarioDt;
import logica.dt.VideoDt;


@TestMethodOrder(OrderAnnotation.class)
class IControladorUsuarioTest {

	IControladorUsuario controladorU = Fabrica.getInstance().getIControladorUsuario();//Para poder subir los videos a un usuario de prueba 
	IControladorVideo controladorV = Fabrica.getInstance().getIControladorVideo();
	
	@Test
	@Order(1)
	public void AltaUsuarioTest() {
		controladorU.AltaUsuario("_-usuarioTest-_", "Cookie234", "userTester", "Buscaglia", "_-usuarioTest-_@testing.com", new Date(14/06/1972), "_-usuarioTest-_.jpg");
		int idUser = controladorU.obtenerIdUsuario("_-usuarioTest-_");
		
		int idUserNoExiste = controladorU.obtenerIdUsuario("NoExiste");
		
		UsuarioDt userTesting = controladorU.ConsultarUsuario(idUser);
		
		
		String actualNick= userTesting.getNickname();
		String expectedNick= "_-usuarioTest-_";
		
		
		String actualPass= userTesting.getPassword();
		String expectedPass= "Cookie234";
		
		
		String actualNombre= userTesting.getNombre();
		String expectedNombre= "userTester";
		
		
		String actualApellido= userTesting.getApellido();
		String expectedApellido= "Buscaglia";
		
		
		String actualMail= userTesting.getMail();
		String expectedMail= "_-usuarioTest-_@testing.com";
	
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String fechaComoCadena = sdf.format(new Date());
		
		String actualFecha= sdf.format(userTesting.getFechanac());
		String expectedFecha= sdf.format(new Date(14/06/1972));
		
		String actualImagen = userTesting.getImagen();
		String expectedImagen = "_-usuarioTest-_.jpg";
		

		assertEquals(expectedNick, actualNick);
		assertEquals(expectedPass, actualPass);
		assertEquals(expectedNombre, actualNombre);
		assertEquals(expectedApellido, actualApellido);
		assertEquals(expectedMail, actualMail);
		assertEquals(expectedFecha, actualFecha);
		assertEquals(expectedImagen, actualImagen);
		
		
		//Verifico IdUsuario catch
		assertEquals(-1, idUserNoExiste);
		//Verifica IdUsuario
		assertEquals(idUser, userTesting.getId());
		
		
		
		//Que falle
		controladorU.AltaUsuario("FallaPorMail", "Cookie234", "Martín", "Buscaglia", "Martinbusagaduorguy", new Date(14/06/1972), "mbusca.jpg");
		int FallaPorMail = controladorU.obtenerIdUsuario("FallaPorMail");
		UsuarioDt userFallaPorMail = controladorU.ConsultarUsuario(FallaPorMail);
		
		
		
	}
	
	
	@Test
	@Order(2)
	public void AltaCanalTest() {
		controladorU.AltaUsuario("_-usuarioTest-_", "Cookie234", "userTester", "Buscaglia", "_-usuarioTest-_@testing.com", new Date(14/06/1972), "_-usuarioTest-_.jpg");
    	int idUserAltaCanal = controladorU.obtenerIdUsuario("_-usuarioTest-_");
    	
    	List<CategoriaDt> categorias = controladorU.obtenerCategorias();
    	String cate="Carnaval";
    	
    	
        controladorU.AltaCanal("Canal userTester5", true, cate, idUserAltaCanal, "El canal userTester es para publicar contenido divertido");
        CanalDt canalPrueba = controladorU.obtenerCanalDt(idUserAltaCanal);
        assertEquals(idUserAltaCanal,canalPrueba.getUserId());
        CanalDt canalPruebaError = controladorU.obtenerCanalDt(-2);
        assertNull(canalPruebaError);
        
        
        String actualNickCanal= canalPrueba.getNombre();
		String expectedNickCanal= "Canal userTester5";
		
		boolean actualPrivacidadCanal= canalPrueba.getPrivacidad();
		boolean expectedPrivacidadCanal= true;
		
		String actualCategoriaCanal= canalPrueba.getCategoria();
		String expectedCategoriaCanal= cate;
		
		int actualIdUserCanal= canalPrueba.getUserId();
		int expectedIdUserCanal= idUserAltaCanal;

		String actualDescripcionCanal= canalPrueba.getDescripcion();
		String expectedDescripcionCanal= "El canal userTester es para publicar contenido divertido";
		
		
		assertEquals(expectedNickCanal, actualNickCanal);
		assertEquals(expectedPrivacidadCanal, actualPrivacidadCanal);
		assertEquals(expectedCategoriaCanal, actualCategoriaCanal);
		assertEquals(expectedIdUserCanal, actualIdUserCanal);
		assertEquals(expectedDescripcionCanal, actualDescripcionCanal);
		
		//Que Falle
		controladorU.AltaCanal("uu123", false, "RealidadAumentadaMisticia", idUserAltaCanal, "El canal Horacio es para publicar contenido divertido");
		CanalDt fallaPorCategoria = controladorU.obtenerCanalDt(idUserAltaCanal);
		
		assertNull(fallaPorCategoria);
		
		
	}
	

	@Test
	@Order(3)
	public void ModificarUsuarioTest() {	
        int idUsuarioTesting = controladorU.obtenerIdUsuario("Luigi");
        controladorU.ModificarUsuario(idUsuarioTesting, "123", "Drake", "Bell", new Date(28/11/1999), "DrakeBellMusic", "Entretenimiento", "Musica de drake bell", false, "userTesterModificar.jpg"); 
        
        UsuarioDt userTesting = controladorU.ConsultarUsuario(idUsuarioTesting);
        
		
		
		String actualPass= userTesting.getPassword();
		String expectedPass= "123";
		
		
		String actualNombre= userTesting.getNombre();
		String expectedNombre= "Drake";
		
		
		String actualApellido= userTesting.getApellido();
		String expectedApellido= "Bell";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String fechaComoCadena = sdf.format(new Date());
		
		String actualFecha= sdf.format(userTesting.getFechanac());
		String expectedFecha= sdf.format(new Date(28/11/1999));
		
		String actualImagen = userTesting.getImagen();
		String expectedImagen = "userTesterModificar.jpg";
		
		
		assertEquals(expectedPass, actualPass);
		assertEquals(expectedNombre, actualNombre);
		assertEquals(expectedApellido, actualApellido);
		assertEquals(expectedFecha, actualFecha);
		assertEquals(expectedImagen, actualImagen);
		
		CanalDt canalTesting = controladorU.obtenerCanalDt(idUsuarioTesting);

        String actualNickCanal= canalTesting.getNombre();
		String expectedNickCanal= "DrakeBellMusic";
		
		boolean actualPrivacidadCanal= canalTesting.getPrivacidad();
		boolean expectedPrivacidadCanal= false;
		
		String actualCategoriaCanal= canalTesting.getCategoria();
		String expectedCategoriaCanal= "Entretenimiento";
		
		int actualIdUserCanal= canalTesting.getUserId();
		int expectedIdUserCanal= idUsuarioTesting;

		String actualDescripcionCanal= canalTesting.getDescripcion();
		String expectedDescripcionCanal= "Musica de drake bell";
		
		
		assertEquals(expectedNickCanal, actualNickCanal);
		assertEquals(expectedPrivacidadCanal, actualPrivacidadCanal);
		assertEquals(expectedCategoriaCanal, actualCategoriaCanal);
		assertEquals(expectedIdUserCanal, actualIdUserCanal);
		assertEquals(expectedDescripcionCanal, actualDescripcionCanal);
		
		
		//fallaModificar
        controladorU.ModificarUsuario(-1, "123", "Drake", "Bell", new Date(28/11/1999), "DrakeBellMusic", "Entretenimiento", "Drake bell blogs", false, "userTester5.jpg");
        UsuarioDt fallaPorid = controladorU.ConsultarUsuario(-1);
		
		assertNull(fallaPorid);
		
		
	}
	
	
	
	public void altaListaReporudccionTest() {
		controladorU.AltaListaDeReproduccionPorDefecto("_ListaTest_");
		
		List<UsuarioDt> ListaUsers = controladorU.ListarUsuarios();
		List<ListaDeReproduccionDt> ListaReproduccion = controladorU.obtenerListas();
		int usersCount=ListaUsers.size();
		int  ListasConNombre=0;
		
		for (ListaDeReproduccionDt temp : ListaReproduccion) {
			
			if(temp.getNombre().contains("_ListaTest_")) {
				ListasConNombre++;
			}
		}
		
		
		assertEquals(usersCount,ListasConNombre);
		
		//Forzando Catch
		int OldUserCounts = ListaReproduccion.size();
		controladorU.AltaListaDeReproduccionPorDefecto(null);
		List<UsuarioDt> ListaUsersNueva = controladorU.ListarUsuarios();
		int newUsersCounts = ListaUsersNueva.size();
		
		assertEquals(OldUserCounts,newUsersCounts);
		
	}
	
	
	@Test
	@Order(4)
	public void obtenerIdUsuarioMailTest() {
		 int idUserPrueba = controladorU.obtenerIdUsuario("Luigi");
		 UsuarioDt userTesting = controladorU.ConsultarUsuario(idUserPrueba);
		 
		 
		 int idObtenida = controladorU.obtenerIdUsuarioMail(userTesting.getMail());
		
		 
		 assertEquals(idUserPrueba,idObtenida);
		 
		 
		 //llamando catch
		 int idObtenidaError = controladorU.obtenerIdUsuarioMail("");
		 assertEquals(-1,idObtenidaError);
	}
	
	
	@Test
	@Order(5)
    public void AltaListaDeReproduccionParticularTest() {
		int idUserPropietario = controladorU.obtenerIdUsuario("Luigi");
		controladorU.AltaListaDeReproduccionParticular("rock", idUserPropietario, false, "Carnaval");
		List<String> listasUser = controladorU.obtenerListasUsuario(idUserPropietario);
		
		
		 assertEquals(true,listasUser.contains("rock"));
		 
		 //Force Catch
		controladorU.AltaListaDeReproduccionParticular("papitas", -1, false, "Carnaval");
		
		assertEquals(false,listasUser.contains("papitas"));
	}
	
	@Test
	@Order(6)
	public void ModificarListaDeReproduccionTest() {
		int idUserPropietario = controladorU.obtenerIdUsuario("Luigi");
		controladorU.ModificarListaDeReproduccion(idUserPropietario, "rock", "Entretenimiento", false);
		ListaDeReproduccionDt listaObtenida = controladorU.obtenerListaDt(idUserPropietario, "rock");
		
		
		assertEquals("Entretenimiento",listaObtenida.getCategoria());
		
		//llamo catch
		controladorU.ModificarListaDeReproduccion(-1, "rock", "Carnaval", false);
		assertEquals("Entretenimiento",listaObtenida.getCategoria());
		
	}
	
	@Test
	@Order(7)
	public void AgregarVideoListaDeReproduccionTest() {
		int idUserPropietario = controladorU.obtenerIdUsuario("Luigi");
		controladorV.AltaVideo("tsunami", "12.30", "https://youtu.be/vo7iZIjTM6g", "trueno", idUserPropietario, "Entretenimiento");
		controladorU.AgregarVideoListaDeReproduccion(idUserPropietario, idUserPropietario, "tsunami", "rock");
		
		ListaDeReproduccionDt listaObtenida = controladorU.obtenerListaDt(idUserPropietario, "rock");
		
		List<VideoDt> videosObtenidos = controladorU.obtenerVideosLista(idUserPropietario,"rock");
		boolean encontrado = false;
		
		for (VideoDt v : videosObtenidos) {
			
			if(v.getNombre().equals("tsunami")) {
					encontrado=true;
				}
		}
		
		
		
		assertEquals(true,encontrado);
		
		//llamo catch
		encontrado = false;
		controladorU.AgregarVideoListaDeReproduccion(idUserPropietario, idUserPropietario, "NOEXISTEEEE", "rock");
		ListaDeReproduccionDt listaObtenidaError = controladorU.obtenerListaDt(idUserPropietario, "rock");
		
		List<VideoDt> videosObtenidosError = controladorU.obtenerVideosLista(idUserPropietario,"rock");
		
		for (VideoDt v : videosObtenidosError) {
			
			if(v.getNombre().equals("NOEXISTEEEE")) {
					encontrado=true;
				}
		}
		
		
		assertEquals(false,encontrado);
	}
	
	
	
	@Test
	@Order(8)
	public void QuitarVideoListaDeReproduccionTest() {
		int idUserPropietario = controladorU.obtenerIdUsuario("Luigi");
		VideoDt videoObtenido = controladorV.obtenerVideoDt("tsunami", idUserPropietario);
		
		controladorU.QuitarVideoListaDeReproduccion(idUserPropietario, "rock", videoObtenido.getId());
		
		List<VideoDt> videosObtenidosError = controladorU.obtenerVideosLista(idUserPropietario,"rock");
		boolean encontrado = false;
		
		for (VideoDt v : videosObtenidosError) {
			
			if(v.getNombre().equals("tsunami")) {
					encontrado=true;
				}
		}
		
		assertEquals(true,encontrado);
		
		
		//Catch
		controladorU.QuitarVideoListaDeReproduccion(-1, "rock", videoObtenido.getId());
		
	}
	
	
	@Test
	@Order(9)
	public void LoginUsuarioTest() {
		int idUserVerificado = controladorU.obtenerIdUsuario("Luigi");
		UsuarioDt userLogin = controladorU.ConsultarUsuario(idUserVerificado);
		int LoginKey = controladorU.LoginUsuario("Luigi", userLogin.getPassword());
		
		
		assertEquals(idUserVerificado,LoginKey);
		
		
		//Catch
		assertNull(controladorU.LoginUsuario("NoExiste666", "NoExiste666"));
	}
	
	
	@Test
	@Order(10)
	public void obtenerListasDtPorUsuarioTest() {
		int idUserVerificado = controladorU.obtenerIdUsuario("Luigi");
		UsuarioDt userPropietario = controladorU.ConsultarUsuario(idUserVerificado);
		
		controladorU.AltaListaDeReproduccionParticular("listaMagica", idUserVerificado, false, "Carnaval");
		List<ListaDeReproduccionDt> listaDeUser = controladorU.obtenerListasDtPorUsuario(idUserVerificado);
		boolean encontrado = false;
		for (ListaDeReproduccionDt l : listaDeUser) {
			
			if((l.getPrivada()==false)&&(l.getCategoria().equals("Carnaval"))&&(l.getNombre().equals("listaMagica"))&&(l.getIdUsuario()==idUserVerificado)) {
				encontrado=true;
			}
		}
	
	
		assertEquals(true,encontrado);
		
		//llamo catch
		List<ListaDeReproduccionDt> ListaError = null;
		ListaError = controladorU.obtenerListasDtPorUsuario(-2);
		List<ListaDeReproduccionDt> ListaErrorVerificado = new ArrayList<>();
		assertEquals(ListaErrorVerificado,ListaError);
	}
	
	
	@Test
	@Order(11)
	public void obtenerListasTest() {
		controladorU.obtenerListas();
		
		int idUserVerificado = controladorU.obtenerIdUsuario("Luigi");
		List<ListaDeReproduccionDt> listaDeUser = controladorU.obtenerListasDtPorUsuario(idUserVerificado);
		boolean encontrado = false;
		for (ListaDeReproduccionDt l : listaDeUser) {
			
			if((l.getPrivada()==false)&&(l.getCategoria().equals("Carnaval"))&&(l.getNombre().equals("listaMagica"))&&(l.getIdUsuario()==idUserVerificado)) {
				encontrado=true;
			}
		}
	
	
		assertEquals(true,encontrado);
			
		
		
	}
	
	
	
	//Lucius
	@Test
	@Order(12)
	public void obtenerNickUsuarioTest() {
        String actual_idUsuarioHurbino = controladorU.obtenerNickUsuario(1);
        String expected_idUsuarioHurbino = "hrubino";
        assertEquals(expected_idUsuarioHurbino, actual_idUsuarioHurbino);
	}
	
	@Test
	@Order(13)
	public void obtenerTipoListaTest() {
        String actual_tipoLista = controladorU.obtenerTipoLista(8, "Nostalgia");
        String expected_tipoLista = "Privada";
        assertEquals(expected_tipoLista, actual_tipoLista);
	}
	
	@Test
	@Order(14)
	public void obtenerListasUsuarioTest() {
        List<String> _listas_actual = controladorU.obtenerListasUsuario(1);
        
        assertEquals("Escuchar más tarde", _listas_actual.get(0));
        assertEquals("Deporte total", _listas_actual.get(1));
        assertEquals("Novedades generales", _listas_actual.get(2));
	}
	
	@Test
	@Order(15)
	public void obtenerVideosTest() {
        List<String> _lista_videos = controladorU.ListarVideos(3);
        assertEquals("100 años de FING", _lista_videos.get(0));
        assertEquals("50 años del InCo", _lista_videos.get(1));
        assertEquals("Ingeniería de Muestra 2017", _lista_videos.get(2));
	}
	
	@Test
	@Order(16)
	public void obtenerListasPorIdTest() {
        ListaDeReproduccionDt _lista = controladorU.obtenerListaDtPorId(1);
        assertEquals(1,_lista.getId());
        assertEquals("Escuchar más tarde",_lista.getNombre());
	}
	
	//Esteban
	@Test
    @Order(17)
    public void testListarVideosDeUsuario() {
        int iduser = controladorU.obtenerIdUsuario("Luigi");
        //Fuerzo catch
        controladorU.listarVideosDeUsuario("-userTest-_QueNoExiste");

        //
        assertFalse(controladorU.listarVideosDeUsuario("Luigi").isEmpty());
    }

		@Test
		@Order(18)
		public void testListarUsuarios() {
			int antes = controladorU.ListarUsuarios().size();
			
			controladorU.AltaUsuario("_-_usuarioTestListar_-_", "123", "1", "2", "_-_Testeo_-_@test.com", new Date(), "");
			int id_user = controladorU.obtenerIdUsuario("_-_usuarioTestListar_-_");
			controladorU.AltaCanal("_-_canalTestListar_-_", false, "Ninguna", id_user, "");
			
			int despues = controladorU.ListarUsuarios().size();
			assertEquals(antes+1, despues);
		}
		
		@Test
		@Order(19)
		public void testListarSeguidoresYseguidos() {
			int id_user = controladorU.obtenerIdUsuario("_-usuarioTest-_");
			
			//Fuerzo catch
			controladorU.ListarSeguidores(-1);
			controladorU.ListarSiguiendo(-1);

			assertTrue(controladorU.ListarSeguidores(id_user).isEmpty());
			assertTrue(controladorU.ListarSiguiendo(id_user).isEmpty());
		}
		
		
		@Test
		@Order(20)
		public void testSeguirUsuario() {
			System.out.println("seguirUsuario");
			//Creo un usuario de prueba
			controladorU.AltaUsuario("_-_usuarioTestSeguidor_-_", "123", "1", "2", "3@gmail.com", new Date(), "");
			controladorU.AltaUsuario("_-_usuarioTestSeguir_-_", "123", "1", "2", "555@gmail.com", new Date(), "");
			
			int id_user = controladorU.obtenerIdUsuario("_-_usuarioTestSeguidor_-_");
			int id_user2 = controladorU.obtenerIdUsuario("_-_usuarioTestSeguir_-_");
			controladorU.AltaCanal("_-_canalTestSeguido_-_", false, "Ninguna", id_user, "");
			controladorU.AltaCanal("_-_canalTestSeguir_-_", false, "Ninguna", id_user2, "");
			
			System.out.println(id_user+"---"+id_user2); //EL SYSTEM.OUT
			controladorU.seguirUsuario("_-_usuarioTestSeguidor_-_", "_-_usuarioTestSeguir_-_");
			List<String> seguidores_user2 = controladorU.ListarSeguidores(id_user2);
			assertEquals(1, seguidores_user2.size());
			assertEquals("_-_usuarioTestSeguidor_-_", seguidores_user2.get(0));
			List<String> seguidos_user1 = controladorU.ListarSiguiendo(id_user);
			assertEquals(1, seguidos_user1.size());
			assertEquals("_-_usuarioTestSeguir_-_", seguidos_user1.get(0));
			
			//Fuerzo catch
			controladorU.seguirUsuario("_-_usuarioTestSeguidor_-_", "_-_usuarioTestSeguirQueNoExiste_-_");
			controladorU.seguirUsuario("_-_usuarioTestSeguidorQueNoExiste_-_", "_-_usuarioTestSeguir_-_");
		}
		
		@Test
		@Order(21)
		public void testEstaSuscripto() {
			int id_user1 = controladorU.obtenerIdUsuario("_-_usuarioTestSeguidor_-_");
			int id_user2 = controladorU.obtenerIdUsuario("_-_usuarioTestSeguir_-_");
			
			assertTrue(controladorU.estaSuscripto(id_user1, id_user2));
			assertFalse(controladorU.estaSuscripto(id_user2, id_user1));
			assertFalse(controladorU.estaSuscripto(id_user2, -1));
			assertFalse(controladorU.estaSuscripto(-1, id_user1));
		}
		
		@Test
		@Order(22)
		public void testDejarDeSeguirUsuario() {
			System.out.println("dejarDeseguirUsuario");
			int id_user = controladorU.obtenerIdUsuario("_-_usuarioTestSeguidor_-_");
			int id_user2 = controladorU.obtenerIdUsuario("_-_usuarioTestSeguir_-_");
			
			controladorU.dejarDeSeguirUsuario("_-_usuarioTestSeguidor_-_", "_-_usuarioTestSeguir_-_");
			List<String> seguidores_user2 = controladorU.ListarSeguidores(id_user2);
			assertEquals(0, seguidores_user2.size());
			List<String> seguidos_user1 = controladorU.ListarSiguiendo(id_user);
			assertEquals(0, seguidos_user1.size());
		}
		
		@Test
		@Order(23)
		public void testEliminarUsuario() {
			int id_user1 = controladorU.obtenerIdUsuario("_-usuarioTest-_");
			int id_user2 = controladorU.obtenerIdUsuario("_-_usuarioTestSeguir_-_");
			
			controladorU.EliminarUsuario(-1); //Fuerzo catch
			controladorU.EliminarUsuario(id_user1);
			controladorU.EliminarUsuario(id_user2);
			
			id_user1 = controladorU.obtenerIdUsuario("_-usuarioTest-_");
			id_user2 = controladorU.obtenerIdUsuario("_-_usuarioTestSeguir_-_");
			
			assertEquals(-1, id_user1);
			assertEquals(-1, id_user2);
		}
		
		
	
	@AfterAll
	public static void eliminacionAntiFallos() {
		IControladorUsuario usr1 = Fabrica.getInstance().getIControladorUsuario();
		IControladorVideo vid = Fabrica.getInstance().getIControladorVideo();
		
		int idUserEliminar = usr1.obtenerIdUsuario("_-usuarioTest-_");
		usr1.EliminarUsuario(idUserEliminar);
		
		
		int id_user1 = usr1.obtenerIdUsuario("_-usuarioTest-_");
		int id_user2 = usr1.obtenerIdUsuario("_-_usuarioTestSeguir_-_");
		int id_user3 = usr1.obtenerIdUsuario("_-_usuarioTestSeguidor_-_");
		int id_user5 = usr1.obtenerIdUsuario("_-_usuarioTestListar_-_");
		usr1.EliminarUsuario(id_user1);
		usr1.EliminarUsuario(id_user2);
		usr1.EliminarUsuario(id_user3);
		usr1.EliminarUsuario(id_user5);
		usr1.EliminarListaDeReproduccionPorDefecto("_-_ListaTest_-_");
		vid.EliminarVideo(id_user1, "_-_videoTest_-_");
	}
	
	@BeforeAll
	public static void agregarAntiFallo() {
		IControladorUsuario usr1 = Fabrica.getInstance().getIControladorUsuario();
		
		 int idUserPrueba = usr1.obtenerIdUsuario("Luigi");
		 
		 if(idUserPrueba==-1) {
			 usr1.AltaUsuario("Luigi", "Luigi123", "Luis", "Pagola", "luispagola@gmail.com", new Date(28/11/1999), "Luigi.jpg");
			 
			 int idUserAltaCanal = usr1.obtenerIdUsuario("Luigi");
			 String categ ="Carnaval";
			 usr1.AltaCanal("Canal Luigi", true, categ, idUserAltaCanal, "El canal Luigi es para publicar contenido divertido");

		 }
		
	}
	

}
