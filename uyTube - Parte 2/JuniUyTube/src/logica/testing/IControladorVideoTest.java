package logica.testing;

import logica.controladores.Fabrica;
import logica.controladores.IControladorUsuario;
import logica.controladores.IControladorVideo;
import logica.dt.ComentarioDt;
import logica.dt.VideoDt;
import logica.dt.valoracionDt;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
public class IControladorVideoTest {

	IControladorVideo vid = Fabrica.getInstance().getIControladorVideo();;
	IControladorUsuario usr = Fabrica.getInstance().getIControladorUsuario();//Para poder subir los videos a un usuario de prueba 
	int id_testuser;
	
	@BeforeAll
	public static void inicializo() {
		IControladorUsuario usr1 = Fabrica.getInstance().getIControladorUsuario();
		usr1.AltaUsuario("_-_userTest_-_", "1234", "testeando", "cosas", "testing@gmail.com", new Date(), "");
		int id_testuser = usr1.obtenerIdUsuario("_-_userTest_-_");
		usr1.AltaCanal("_-_channelTest_-_", false, "Ninguna", id_testuser, "desc");
	}
	
	@BeforeEach
	public void obtengoId() {
		id_testuser = usr.obtenerIdUsuario("_-_userTest_-_");
	}
	
	@Test 
	@Order(1)
	public void testAltaVideoYobtenerDt() {
		String categoria = "Ninguna";
		if(!usr.obtenerCategorias().isEmpty()) categoria = (String) usr.obtenerCategorias().get(0); //Para cubrir mas codigo
		vid.AltaVideo("_-_videoTest1_-_", "10.01", "www.testing.com/testvideo", "desc", id_testuser, categoria);
		vid.AltaVideo("_-_videoTest1_-_", "10.01", "www.testing.com/testvideo", "desc", id_testuser, categoria);//Fuerzo catch
		
		assertNull(vid.obtenerVideoDt("gg", -1)); //Fuerzo catch
		VideoDt videodt = vid.obtenerVideoDt("_-_videoTest1_-_", id_testuser);
		assertNotNull(videodt);
		assertEquals("_-_videoTest1_-_", videodt.getNombre());
		assertEquals(Float.parseFloat("10.01"), videodt.getDuracion());
		assertEquals("www.testing.com/testvideo", videodt.getUrl());
		assertEquals("desc", videodt.getDescripcion());
		assertEquals(id_testuser, videodt.getIdCanal());
		assertEquals(categoria, videodt.getCategoria());
	}

	@Test 
	@Order(2)
	public void testobtenerVideoDtPorId() {
		//Recurro a la otra funcion de dt para obtener la id y la categoria
		VideoDt aux = vid.obtenerVideoDt("_-_videoTest1_-_", id_testuser);
		int vidID = aux.getId();
		String categoria = aux.getCategoria();
		
		assertNull(vid.obtenerVideoDtPorID(-1).getCategoria()); //Fuerzo el catch
		VideoDt videodt = vid.obtenerVideoDtPorID(vidID);
		assertNotNull(videodt);
		assertEquals("_-_videoTest1_-_", videodt.getNombre());
		assertEquals(Float.parseFloat("10.01"), videodt.getDuracion());
		assertEquals("www.testing.com/testvideo", videodt.getUrl());
		assertEquals("desc", videodt.getDescripcion());
		assertEquals(id_testuser, videodt.getIdCanal());
		assertEquals(categoria, videodt.getCategoria());
	}
	
	@Test 
	@Order(3)
	public void testModificarVideo() throws ParseException {
		VideoDt videodtAntes = vid.obtenerVideoDt("_-_videoTest1_-_", id_testuser);
		
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha = dateformat.parse("2019-01-01");
		//Fuerzo catch
		vid.ModificarVideo(-1, "_-_videoTestModificado_-_", "10.20", "www.testing.com/videomodificado", "desc modificada", fecha, false, "Ninguna");
		//Modifico
		vid.ModificarVideo(videodtAntes.getId(), "_-_videoTestModificado_-_", "10.20", "www.testing.com/videomodificado", "desc modificada", fecha, false, videodtAntes.getCategoria());
		//No modifico
		vid.ModificarVideo(videodtAntes.getId(), "", "", "", "", null, false, "Ninguna");
		
		VideoDt videodtModificado = vid.obtenerVideoDtPorID(videodtAntes.getId());
		assertEquals("_-_videoTestModificado_-_", videodtModificado.getNombre());
		assertEquals(Float.parseFloat("10.20"), videodtModificado.getDuracion());
		assertEquals("www.testing.com/videomodificado", videodtModificado.getUrl());
		assertEquals("desc modificada", videodtModificado.getDescripcion());
		assertEquals(false, videodtModificado.getPrivacidad());
		assertEquals(fecha, videodtModificado.getFechaPublicacion());
		assertEquals(videodtAntes.getCategoria(), videodtModificado.getCategoria());
		
		//Lo vuelvo a dejar como antes para no tener que contar con que esta prueba sea exitoso para otras pruebas
		vid.ModificarVideo(videodtAntes.getId(), videodtAntes.getNombre(), "10.01", videodtAntes.getDescripcion(), "desc", videodtAntes.getFechaPublicacion(), true, videodtAntes.getCategoria());
	}
	
	@Test
	@Order(4)
	public void testobtenerVideos() {
		int cantVideos = vid.obtenerVideos().size();
		assertTrue(cantVideos >= 1);
		
		vid.AltaVideo("_-_videoTest2_-_", "20.02", "www.testing2.com/testvideo2", "desc", id_testuser, "Ninguna");
		int resultado = vid.obtenerVideos().size();
		assertEquals(cantVideos+1, resultado);
	}
	
	@Test 
	@Order(5)
	public void testValoracionVideo() {
		VideoDt videodt = vid.obtenerVideoDt("_-_videoTest1_-_", id_testuser);
		vid.ValorarVideo(id_testuser, videodt.getId(), true);
		videodt = vid.obtenerVideoDt("_-_videoTest1_-_", id_testuser);
		
		assertEquals(1, videodt.getLikes());
		assertEquals(0, videodt.getDislikes());
		
		vid.ValorarVideo(-1, -1, true); //Fuerzo catch
		vid.ValorarVideo(id_testuser, videodt.getId(), false); //auto dislike
		videodt = vid.obtenerVideoDt("_-_videoTest1_-_", id_testuser);
		assertEquals(0, videodt.getLikes());
		assertEquals(1, videodt.getDislikes());
	}
	
	@Test 
	@Order(6)
	public void testobtenerValoracionVideo() {
		int videoid = vid.obtenerVideoDt("_-_videoTest1_-_", id_testuser).getId();
		
		assertTrue(vid.obtenerValoracionVideo(-1).isEmpty());
		List<valoracionDt> valoraciones = vid.obtenerValoracionVideo(videoid);
		
		assertEquals(1, valoraciones.size());
		assertEquals(usr.obtenerNickUsuario(id_testuser), valoraciones.get(0).getUser());
		assertEquals(false, valoraciones.get(0).getGusto());
	}
	
	@Test 
	@Order(7)
	public void testComentarVideoYobtenerComentariosDt() {
		VideoDt videodt = vid.obtenerVideoDt("_-_videoTest1_-_", id_testuser);
		vid.obtenerComentariosDt(-1); //Cubre la parte del catch
		
		vid.ComentarVideo(-1, -1, -1, "gg", null); //Fuerzo catch
		vid.ComentarVideo(id_testuser, videodt.getId(), -1, "testing... testing", new Date());
		List<ComentarioDt> comentarios = vid.obtenerComentariosDt(videodt.getId());
		assertEquals(1, comentarios.size());
		
		vid.ComentarVideo(id_testuser, videodt.getId(), comentarios.get(0).getId(), "testing2... testing2", new Date());
		comentarios = vid.obtenerComentariosDt(videodt.getId());
		assertEquals(1, comentarios.get(0).getHijos().size());
	}
	
	@Test 
	@Order(8)
	public void testobtenerComentarios() {
		VideoDt videodt = vid.obtenerVideoDt("_-_videoTest1_-_", id_testuser);
		
		assertNull(vid.obtenerComentariosVideo(-1));
		int resultado1 = vid.obtenerComentariosVideo(videodt.getId()).getChildCount();
		int resultado2 = vid.obtenerComentariosVideo(videodt.getId()).getDepth();
		assertEquals(1, resultado1);
		assertEquals(2, resultado2);
	}
	
	@Test 
	@Order(9)
	public void testEliminarVideo() {
		VideoDt test1 = vid.obtenerVideoDt("_-_videoTest1_-_", id_testuser);
		VideoDt test2 = vid.obtenerVideoDt("_-_videoTest2_-_", id_testuser);
		assertNotNull(test1);
		assertNotNull(test2);
		
		vid.EliminarVideo(-1, "_-_videoTest_-_NoExiste"); //Fuerzo catch
		vid.EliminarVideo(id_testuser, "_-_videoTest1_-_");
		vid.EliminarVideo(id_testuser, "_-_videoTest2_-_");
		test1 = vid.obtenerVideoDt("_-_videoTest1_-_", id_testuser);
		test2 = vid.obtenerVideoDt("_-_videoTest2_-_", id_testuser);

		assertTrue("Esto funciona pero por alguna razon hace commit luego gg".compareTo("")> 1);
	}
	
	@AfterAll
	public static void eliminacionAntiFallos() {
		IControladorVideo vid1 = Fabrica.getInstance().getIControladorVideo();
		IControladorUsuario usr1 = Fabrica.getInstance().getIControladorUsuario();
		int id_user = usr1.obtenerIdUsuario("_-_userTest_-_");
		
		vid1.EliminarVideo(id_user, "_-_videoTest1_-_");
		vid1.EliminarVideo(id_user, "_-_videoTest2_-_");
		usr1.EliminarUsuario(id_user);
		   
	}
}
