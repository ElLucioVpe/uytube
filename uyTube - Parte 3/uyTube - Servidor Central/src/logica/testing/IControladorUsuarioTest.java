package logica.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

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
import logica.dt.ListaDeReproduccionDt;
import logica.dt.UsuarioDt;
import logica.dt.VideoDt;

@Transactional
@TestMethodOrder(OrderAnnotation.class)
class IControladorUsuarioTest {

	IControladorUsuario controladorU = Fabrica.getInstance().getIControladorUsuario();//Para poder subir los videos a un usuario de prueba 
	IControladorVideo controladorV = Fabrica.getInstance().getIControladorVideo();
	
	@Test
	@Order(1)
	public void AltaUsuarioTest() {
		controladorU.AltaUsuario("_-_usuarioTest_-_", "Cookie234", "userTester", "Buscaglia", "_-usuarioTest-_@testing.com", new Date(14/06/1972), "_-usuarioTest-_.jpg");
		int idUser = controladorU.obtenerIdUsuario("_-_usuarioTest_-_");
		
		int idUserNoExiste = controladorU.obtenerIdUsuario("_-_NoExiste_-_");
		
		UsuarioDt userTesting = controladorU.ConsultarUsuario(idUser);
		
		
		String actualNick= userTesting.getNickname();
		String expectedNick= "_-_usuarioTest_-_";
		
		
		String actualPass= userTesting.getPassword();
		String expectedPass= "Cookie234";
		
		
		String actualNombre= userTesting.getNombre();
		String expectedNombre= "userTester";
		
		
		String actualApellido= userTesting.getApellido();
		String expectedApellido= "Buscaglia";
		
		
		String actualMail= userTesting.getMail();
		String expectedMail= "_-usuarioTest-_@testing.com";
	
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		//String fechaComoCadena = sdf.format(new Date());
		
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
		controladorU.AltaUsuario("_-_FallaPorMail_-_", "Cookie234", "Mart�n", "Buscaglia", "Martinbusagaduorguy", new Date(14/06/1972), "mbusca.jpg");
		int FallaPorMail = controladorU.obtenerIdUsuario("_-_FallaPorMail_-_");
		UsuarioDt userFallaPorMail = controladorU.ConsultarUsuario(FallaPorMail);
		assertNull(userFallaPorMail);
		//int idUserEliminar = controladorU.obtenerIdUsuario("_-usuarioTest-_");
		//controladorU.EliminarUsuario(idUserEliminar);
		
	}
	
	
	@Test
	@Order(2)
	public void AltaCanalTest() {
				
	    System.out.println("Returning default value for");  
	      
	    int idUserAltaCanal = controladorU.obtenerIdUsuario("_-_usuarioTest_-_");
	    //Fuerzo catch
	    assertEquals(-1, controladorU.obtenerIdUsuario("_-_usuarioTestNOEXISTE_-_"));
	    
    	String cate="Ninguna";
    	if(!controladorU.obtenerCategorias().isEmpty()) cate = (String)controladorU.obtenerCategorias().get(0);
        controladorU.AltaCanal("Canal userTester5", true, cate, idUserAltaCanal, "El canal userTester es para publicar contenido divertido");
        
        
        System.out.println("Testeo");
	    CanalDt canalPrueba = controladorU.obtenerCanalDt(idUserAltaCanal);
        
        
        
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
		
        assertEquals(idUserAltaCanal,canalPrueba.getUserId());

		assertEquals(expectedNickCanal, actualNickCanal);
		assertEquals(expectedPrivacidadCanal, actualPrivacidadCanal);
		assertEquals(expectedCategoriaCanal, actualCategoriaCanal);
		assertEquals(expectedIdUserCanal, actualIdUserCanal);
		assertEquals(expectedDescripcionCanal, actualDescripcionCanal);
		

		
        assertEquals(idUserAltaCanal,canalPrueba.getUserId());

		assertEquals("Canal userTester5", canalPrueba.getNombre());
		assertEquals(true, canalPrueba.getPrivacidad());
		assertEquals(cate, canalPrueba.getCategoria());
		assertEquals(idUserAltaCanal, canalPrueba.getUserId());
		assertEquals("El canal userTester es para publicar contenido divertido",canalPrueba.getDescripcion());
		
		//Que Falle
		controladorU.AltaCanal("_-_uu123_-_", false, "RealidadAumentadaMisticia", idUserAltaCanal, "El canal Horacio es para publicar contenido divertido");
		CanalDt fallaPorCategoria = controladorU.obtenerCanalDt(idUserAltaCanal);
		
		assertNull(fallaPorCategoria);
		
		  CanalDt canalPruebaError = controladorU.obtenerCanalDt(-2);
		  assertNull(canalPruebaError);
		
		
	}
	

	@Test
	@Order(3)
	public void ModificarUsuarioTest() {	
        int idUsuarioTesting = controladorU.obtenerIdUsuario("Luigi");
        String cate="Ninguna";
    	if(!controladorU.obtenerCategorias().isEmpty()) cate = (String)controladorU.obtenerCategorias().get(0);
        controladorU.ModificarUsuario(idUsuarioTesting, "123", "Drake", "Bell", new Date(28/11/1999), "DrakeBellMusic", cate, "Musica de drake bell", false, "userTesterModificar.jpg"); 
        
        UsuarioDt userTesting = controladorU.ConsultarUsuario(idUsuarioTesting);
        
		
		
		String actualPass= userTesting.getPassword();
		String expectedPass= "123";
		
		
		String actualNombre= userTesting.getNombre();
		String expectedNombre= "Drake";
		
		
		String actualApellido= userTesting.getApellido();
		String expectedApellido= "Bell";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		//String fechaComoCadena = sdf.format(new Date());
		
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
		String expectedCategoriaCanal= cate;
		
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
	
	
	
	@Test
	@Order(4)
	public void obtenerIdUsuarioMailTest() {
		 int idUserPrueba = controladorU.obtenerIdUsuario("Luigi");
		 UsuarioDt userTesting = controladorU.ConsultarUsuario(idUserPrueba);
		 
		 
		 int idObtenida = controladorU.obtenerIdUsuarioMail(userTesting.getMail());
		
		 
		 assertEquals(idUserPrueba,idObtenida);
		 
		 
		 //llamando catch
		 assertEquals(-1,controladorU.obtenerIdUsuarioMail("_-_mailquenoexisteGG_-_"));
	}
	
	
	@Test
	@Order(5)
    public void AltaListaDeReproduccionParticularTest() {
		int idUser = controladorU.obtenerIdUsuario("UsuarioParticular");

		String cate = "Ninguna";
		if(!controladorU.obtenerCategorias().isEmpty()) cate = (String) controladorU.obtenerCategorias().get(0);
		
        controladorU.AltaListaDeReproduccionParticular("rock2", idUser, false, cate);
		
		int idUserPropietario = controladorU.obtenerIdUsuario("Luigi");
		controladorU.AltaListaDeReproduccionParticular("rock", idUserPropietario, false, cate);
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
		String cate="Ninguna";
	    if(!controladorU.obtenerCategorias().isEmpty()) cate = (String)controladorU.obtenerCategorias().get(0);
	    	
		controladorU.ModificarListaDeReproduccion(idUserPropietario, "rock", cate, false);
		ListaDeReproduccionDt listaObtenida = controladorU.obtenerListaDt(idUserPropietario, "rock");
		
		
		assertEquals(cate,listaObtenida.getCategoria());
		
		//llamo catch
		controladorU.ModificarListaDeReproduccion(-1, "rock", "Carnaval", false);
		assertEquals(cate,listaObtenida.getCategoria());
		
	}
	
	@Test
	@Order(7)
	public void AgregarVideoListaDeReproduccionTest() {
		int idUserPropietario = controladorU.obtenerIdUsuario("Luigi");
		controladorU.AgregarVideoListaDeReproduccion(idUserPropietario, idUserPropietario, "tsunami", "rock");
			
		List<VideoDt> videosObtenidos = controladorU.obtenerVideosLista(idUserPropietario,"rock");
		boolean encontrado = false;
	
		
		for (VideoDt v : videosObtenidos) {
			System.out.println(v.getNombre());
			
			if(v.getNombre().equals("tsunami")) {
					encontrado=true;
				}
		}
		
		
		assertEquals(true,encontrado);
		
		//llamo catch
		encontrado = false;
		controladorU.AgregarVideoListaDeReproduccion(idUserPropietario, idUserPropietario, "_-_NOEXISTEEEE_-_", "rock");
		controladorU.obtenerListaDt(idUserPropietario, "rock");
		
		List<VideoDt> videosObtenidosError = controladorU.obtenerVideosLista(idUserPropietario,"rock");
		
		for (VideoDt v : videosObtenidosError) {
			
			if(v.getNombre().equals("_-_NOEXISTEEEE_-_")) {
					encontrado=true;
			}
		}
		
		assertEquals(false,encontrado);
		assertTrue(controladorU.obtenerVideosLista(-1, "noexiste").isEmpty());
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
		
		assertEquals(false,encontrado);
		
		
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
		assertNull(controladorU.LoginUsuario("_-_NoExiste666_-_", "NoExiste666"));
	}
	
	
	@Test
	@Order(10)
	public void obtenerListasDtPorUsuarioTest() {
		int idUserVerificado = controladorU.obtenerIdUsuario("Luigi");
		//UsuarioDt userPropietario = controladorU.ConsultarUsuario(idUserVerificado);
		
		String cate = "Ninguna";
		if(!controladorU.obtenerCategorias().isEmpty()) cate = (String) controladorU.obtenerCategorias().get(0);
		
		controladorU.AltaListaDeReproduccionParticular("listaMagica", idUserVerificado, false, cate);
		List<ListaDeReproduccionDt> listaDeUser = controladorU.obtenerListasDtPorUsuario(idUserVerificado);
		boolean encontrado = false;
		for (ListaDeReproduccionDt l : listaDeUser) {
			
			if((l.getPrivada()==false)&&(l.getCategoria().equals(cate))&&(l.getNombre().equals("listaMagica"))&&(l.getIdUsuario()==idUserVerificado)) {
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
		
		String cate = "Ninguna";
		if(!controladorU.obtenerCategorias().isEmpty()) cate = (String) controladorU.obtenerCategorias().get(0);
		int idUserVerificado = controladorU.obtenerIdUsuario("Luigi");
		controladorU.AltaListaDeReproduccionParticular("listaMagicaRd", idUserVerificado, false, cate);

		List<ListaDeReproduccionDt> listaDeUser = controladorU.obtenerListasDtPorUsuario(idUserVerificado);
		boolean encontrado = false;
		for (ListaDeReproduccionDt l : listaDeUser) {
			
			if((l.getPrivada()==false)&&(l.getCategoria().equals(cate))&&(l.getNombre().equals("listaMagicaRd"))&&(l.getIdUsuario()==idUserVerificado)) {
				encontrado=true;
			}
		}
	
		assertEquals(true,encontrado);
	}
	
	
	
	//Lucius
	@Test
	@Order(12)
	public void obtenerNickUsuarioTest() {
        int idUsuario = controladorU.obtenerIdUsuario("Luigi");
        UsuarioDt elnierson = controladorU.ConsultarUsuario(idUsuario);
        
        assertEquals("Luigi", elnierson.getNickname());
        assertEquals("", controladorU.obtenerNickUsuario(-1));
	}
	
	@Test
	@Order(13)
	public void obtenerTipoListaTest() {
		int idUser = controladorU.obtenerIdUsuario("Luigi");
        String actual_tipoLista = controladorU.obtenerTipoLista(idUser, "rock");
        String expected_tipoLista = "Privada";
        assertEquals(expected_tipoLista, actual_tipoLista);
        
        //Fuerzo catch
        //La respuesta predeterminada es "Privada"
        assertEquals("Privada", controladorU.obtenerTipoLista(-1, "rock"));
	}
	
	
	
	@Test
    @Order(25)
	public void altaListaReporudccionTest() {
		controladorU.AltaListaDeReproduccionPorDefecto("_-_ListaTest_-_");
		
		
		List<UsuarioDt> ListaUsers = controladorU.ListarUsuarios();
		List<ListaDeReproduccionDt> ListaReproduccion = controladorU.obtenerListas();
		int  ListasConNombre=0;
		
		for (ListaDeReproduccionDt temp : ListaReproduccion) {
			
			if(temp.getNombre().contains("_-_ListaTest_-_")) {
				ListasConNombre++;
			}
		}
		
		int usersCount=ListaUsers.size();
		assertEquals(usersCount,ListasConNombre);
		
		//Forzando Catch
		int OldUserCounts = ListaReproduccion.size();
		controladorU.AltaListaDeReproduccionPorDefecto(null);
		List<ListaDeReproduccionDt> ListaUsersNueva = controladorU.obtenerListas();
		int newUsersCounts = ListaUsersNueva.size();
		
		assertEquals(OldUserCounts,newUsersCounts);
		
	}
	
	@Test
	@Order(14)
	public void obtenerListasUsuarioTest() {
		int idUsuario = controladorU.obtenerIdUsuario("Luigi");
        List<String> _listas_actual = controladorU.obtenerListasUsuario(idUsuario);
        
        for(int i=0; i<_listas_actual.size(); i++) System.out.println(_listas_actual.get(i));
        assertTrue(_listas_actual.size() >= 3);
	}
	
	@Test
	@Order(15)
	public void obtenerVideosTest() {
		int idUsuario = controladorU.obtenerIdUsuario("Luigi");
        List<String> _lista_videos = controladorU.ListarVideos(idUsuario);
        assertEquals("tsunami", _lista_videos.get(0));
	}
	
	@Test
	@Order(16)
	public void obtenerListasPorIdTest() {
		int idUser = controladorU.obtenerIdUsuario("Luigi");
		assertNull(controladorU.obtenerListaDt(-1, "rock"));
		ListaDeReproduccionDt _lista1 = controladorU.obtenerListaDt(idUser, "rock");
        ListaDeReproduccionDt _lista = controladorU.obtenerListaDtPorId(_lista1.getId());
        
        assertEquals("rock",_lista.getNombre());
	}
	
	//Esteban
	@Test
    @Order(17)
    public void testListarVideosDeUsuario() {
        //int iduser = controladorU.obtenerIdUsuario("Luigi");
        assertFalse(controladorU.listarVideosDeUsuario("Luigi").isEmpty());
        //Fuerzo catch
        controladorU.listarVideosDeUsuario("_-userTest-_QueNoExiste");
    }

	@Test
    @Order(18)
		public void testListarUsuarios() {

			int antes = controladorU.ListarUsuarios().size();
			int UserListar = controladorU.obtenerIdUsuario("_-_UserListar_-_");
			 
			 if(UserListar==-1) {
				String cate = "Ninguna";
				//if(!controladorU.obtenerCategorias().isEmpty()) cate = (String) controladorU.obtenerCategorias().get(0);
				controladorU.AltaUsuario("_-_UserListar_-_", "123", "1", "2", "UserListar@test.com", new Date(21/11/1999), "");
				int id_user = controladorU.obtenerIdUsuario("_-_UserListar_-_");
				controladorU.AltaCanal("_-_UserListarCanal_-_", false, cate, id_user, "");
				
				int despues = controladorU.ListarUsuarios().size();
				assertEquals(antes+1, despues);
			 }else {
				 int despues = controladorU.ListarUsuarios().size(); 
				 assertEquals(antes, despues);
			 }	
			 
			
		}
		
	@Test
    @Order(19)
		public void testListarSeguidoresYseguidos() {
		   
			int id_user = controladorU.obtenerIdUsuario("_-_usuarioTest_-_");
			
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
			  
			 int id_user = controladorU.obtenerIdUsuario("_-_seguidor_-_");
			 int id_user2 = controladorU.obtenerIdUsuario("_-_seguir_-_");
			 
		
			 
			 if(id_user==-1) {
				//Creo un usuario de prueba
					controladorU.AltaUsuario("_-_seguidor_-_", "123", "1", "2", "seguidor@gmail.com", new Date(22/11/1999), "");
					 id_user = controladorU.obtenerIdUsuario("_-_seguidor_-_");
					controladorU.AltaCanal("_-_seguidorCanal_-_", false, "Ninguna", id_user, "");
				 
			 }
			 
			 if(id_user2==-1) {
					controladorU.AltaUsuario("_-_seguir_-_", "123", "1", "2", "seguir@gmail.com", new Date(22/11/1999), "");
					id_user2 = controladorU.obtenerIdUsuario("_-_seguir_-_");
					controladorU.AltaCanal("_-_seguirCanal_-_", false, "Ninguna", id_user2, "");	 
			 }
			 
			
			System.out.println(id_user+"---"+id_user2); //EL SYSTEM.OUT
			controladorU.seguirUsuario("_-_seguidor_-_", "_-_seguir_-_");
			
			List<String> seguidores_user2 = controladorU.ListarSeguidores(id_user2);
			
			 
			assertEquals(1, seguidores_user2.size());
			assertEquals("_-_seguidor_-_", seguidores_user2.get(0));
			List<String> seguidos_user1 = controladorU.ListarSiguiendo(id_user);
			assertEquals(1, seguidos_user1.size());
			assertEquals("_-_seguir_-_", seguidos_user1.get(0));
			
			//Fuerzo catch
			controladorU.seguirUsuario("_-_seguidor_-_", "_-_usuarioTestSeguirQueNoExiste_-_");
			controladorU.seguirUsuario("_-_usuarioTestSeguidorQueNoExiste_-_", "_-_seguir_-_");
		}
		
	

		@Test
	    @Order(21)
		public void testEstaSuscripto() {
			int id_user1 = controladorU.obtenerIdUsuario("_-_seguidor_-_");
			int id_user2 = controladorU.obtenerIdUsuario("_-_seguir_-_");
			
			assertTrue(controladorU.estaSuscripto(id_user1, id_user2));
			assertFalse(controladorU.estaSuscripto(id_user2, id_user1));
			assertFalse(controladorU.estaSuscripto(id_user2, -1));
			assertFalse(controladorU.estaSuscripto(-1, id_user1));
		}
		
	
		@Test
	    @Order(22)
		public void testDejarDeSeguirUsuario() {
			System.out.println("dejarDeseguirUsuario");
	
			 
			int id_user = controladorU.obtenerIdUsuario("_-_seguidor_-_");
			int id_user2 = controladorU.obtenerIdUsuario("_-_seguir_-_");
			
			controladorU.dejarDeSeguirUsuario("_-_seguidor_-_", "_-_seguir_-_");
			List<String> seguidores_user2 = controladorU.ListarSeguidores(id_user2);
			assertEquals(0, seguidores_user2.size());
			List<String> seguidos_user1 = controladorU.ListarSiguiendo(id_user);
			assertEquals(0, seguidos_user1.size());
			
			//Fuerzo catch
			controladorU.dejarDeSeguirUsuario("_-_seguidorXX_-_", "_-_seguirXX_-_");
		}
		
		@Test
	    @Order(23)
		public void testEliminarUsuario() {
			 
			 
			int id_user1 = controladorU.obtenerIdUsuario("_-_usuarioTest_-_");
			int id_user2 = controladorU.obtenerIdUsuario("_-_seguir_-_");
			
			controladorU.EliminarUsuario(id_user1);
			controladorU.EliminarUsuario(id_user2);
		
			controladorU.EliminarUsuario(-1); //Fuerzo catch
			
			id_user1 = controladorU.obtenerIdUsuario("_-_usuarioTest_-_");
			id_user2 = controladorU.obtenerIdUsuario("_-_seguir_-_");
			 
			assertEquals(-1, id_user1);
			assertEquals(-1, id_user2);
		}
		
		
		@Test
	    @Order(24)
		public void EliminarListaDeReproduccionParticular() {
			int idUserVerificado = controladorU.obtenerIdUsuario("Luigi");
			controladorU.EliminarListaDeReproduccionParticular(idUserVerificado, "listaMagicaRd");
			//llamo catch
			controladorU.EliminarListaDeReproduccionParticular(idUserVerificado, "_-_NoExisteEstaLista_-_");
			
			System.out.println("Eliminandooooo...");
			List<ListaDeReproduccionDt> listaDeUser = controladorU.obtenerListasDtPorUsuario(idUserVerificado);
			boolean encontrado = false;
			
			for (ListaDeReproduccionDt l : listaDeUser) {
				
				if(l.getNombre().equals("listaMagicaRd")) {
					encontrado=true;
				}
			}

			assertEquals(false,encontrado);
		}
		
	
	@AfterAll
	public static void eliminacionAntiFallos() {
		 IControladorUsuario usr1 = Fabrica.getInstance().getIControladorUsuario();
		 IControladorVideo vid = Fabrica.getInstance().getIControladorVideo();
		 
		 System.out.println("Estoy en el afterAll"); //EL SYSTEM.OUT
			
		 int UsuarioParticular = usr1.obtenerIdUsuario("UsuarioParticular");
		 int id_user1 = usr1.obtenerIdUsuario("_-_usuarioTest_-_");
		 int id_user2 = usr1.obtenerIdUsuario("_-_seguidor_-_");
		 int id_user3 = usr1.obtenerIdUsuario("_-_seguir_-_");
		 int id_user4 = usr1.obtenerIdUsuario("_-_UserListar_-_");
		 int id_user5 = usr1.obtenerIdUsuario("Luigi");
		 int id_user6 = usr1.obtenerIdUsuario("usuarioTestCanal");
		 
		 System.out.println("--"+id_user1+"--"+id_user2+"--"+id_user3+"---"+"--"+id_user4+"---"+id_user5+"---"+id_user6+"---");

		 vid.EliminarVideo(id_user1, "_-_videoTest_-_");
		 vid.EliminarVideo(id_user5, "tsunami");
		 usr1.EliminarListaDeReproduccionParticular(id_user5, "rock");
		 usr1.EliminarListaDeReproduccionParticular(id_user5, "rock2");
		 usr1.EliminarListaDeReproduccionParticular(id_user5, "listaMagicaRd");
		 usr1.EliminarListaDeReproduccionParticular(id_user5, "listaMagica");
		 usr1.EliminarListaDeReproduccionPorDefecto("_-_ListaTest_-_");
		
		 usr1.EliminarUsuario(id_user1);
		 usr1.EliminarUsuario(id_user2);
		 usr1.EliminarUsuario(id_user3);
		 usr1.EliminarUsuario(id_user4);
		 usr1.EliminarUsuario(id_user5);
		 usr1.EliminarUsuario(UsuarioParticular);
		 usr1.EliminarUsuario(id_user6);
	}
	
	@BeforeAll
	public static void agregarAntiFallo() {
		IControladorUsuario usr1 = Fabrica.getInstance().getIControladorUsuario();
		IControladorVideo vid1 = Fabrica.getInstance().getIControladorVideo();
		
		 int idUserPrueba = usr1.obtenerIdUsuario("Luigi");
		 
		 if(idUserPrueba==-1) {
			 usr1.AltaUsuario("Luigi", "Luigi123", "Luis", "Pagola", "luispagola@gmail.com", new Date(28/11/1999), "Luigi.jpg");
			 
			 int idUserAltaCanal = usr1.obtenerIdUsuario("Luigi");
			 String categ ="Ninguna";
			 usr1.AltaCanal("Canal Luigi", true, categ, idUserAltaCanal, "El canal Luigi es para publicar contenido divertido");

		 }
		 
		 
			int idUserPropietario = usr1.obtenerIdUsuario("Luigi");
			if(idUserPropietario!=-1) {
			String cate="Ninguna";
			if(!usr1.obtenerCategorias().isEmpty()) cate = (String)usr1.obtenerCategorias().get(0);
			vid1.AltaVideo("tsunami", "12.30", "https://youtu.be/vo7iZIjTM6g", "trueno", idUserPropietario, cate);
			}

		 int UsuarioParticular = usr1.obtenerIdUsuario("UsuarioParticular");
		 
		 if(UsuarioParticular!=-1) {
			usr1.AltaUsuario("UsuarioParticular", "Cookie234", "userTester", "Buscaglia", "UsuarioParticular@testing.com", new Date(14/06/1972), "UsuarioParticular.jpg");
			String cate="Ninguna";
			UsuarioParticular = usr1.obtenerIdUsuario("UsuarioParticular");
			if(!usr1.obtenerCategorias().isEmpty()) cate = (String)usr1.obtenerCategorias().get(0);
			usr1.AltaCanal("UsuarioParticularCanal", true, cate, UsuarioParticular, "El canal userTester es para publicar contenido divertido");
		 }	 
		 
	}	

}
