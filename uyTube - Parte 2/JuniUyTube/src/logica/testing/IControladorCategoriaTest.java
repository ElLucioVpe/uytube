package logica.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import logica.controladores.IControladorCategoria;
import logica.controladores.Fabrica;
import logica.dt.CategoriaDt;

@TestMethodOrder(OrderAnnotation.class)
public class IControladorCategoriaTest {

	IControladorCategoria cat = Fabrica.getInstance().getIControladorCategoria();
	
	@Test 
	@Order(1)
	public void testAltaConsultar() {
		System.out.println("AltaConsultar");
		String testnombre = "_-_categoriaTest_-_";
		cat.AltaCategoria(testnombre);
		CategoriaDt testdt = cat.ConsultarCategorias(testnombre);
		assertNotNull(testdt);
		assertEquals(testnombre, testdt.getNombre());
	}
	
	@Test 
	@Order(2)
	public void testListarCategorias() {
		System.out.println("Listar");
		String testnombre = "_-_categoriaTest_-_";
		int cantCategorias = cat.ListarCategorias().size();
		cat.AltaCategoria(testnombre);
		cat.AltaCategoria(testnombre+"2");
		int cantCategorias2 = cat.ListarCategorias().size();
		assertEquals(cantCategorias+1, cantCategorias2);
	}
	
	@Test 
	@Order(3)
	public void testobtenerListasCategoria() {
		System.out.println("obtenerListasCategoria");

		assertEquals(0, cat.obtenerListasCategoria("_-_categoriaTest_-_").size());
		assertNull(cat.obtenerListasCategoria("_-_categoriaTest_-_QueNoExiste"));
		assertEquals(0, cat.obtenerListasDtCategoria("_-_categoriaTest_-_").size());
		assertEquals(0, cat.obtenerListasDtCategoria("_-_categoriaTest_-_QueNoExiste").size());
		
		
		List<CategoriaDt> categorias = cat.ListarCategorias();
		for(int i=0; i < categorias.size(); i++) {
			CategoriaDt catdt = categorias.get(i);
			if(!catdt.getNombre().contains("_-_categoriaTest_-_")) {
				//Esto es meramente para recorrer el codigo de obtener las listas
				assertEquals(cat.obtenerListasCategoria(catdt.getNombre()).size(), cat.obtenerListasDtCategoria(catdt.getNombre()).size());
				//El listaDt recorre lista normal ya que la utiliza
			}
		}
	}
	
	@Test 
	@Order(4)
	public void testobtenerVideosCategoria() {
		System.out.println("obtenerVideosCategoria");
		assertEquals(0, cat.obtenerVideosCategoria("_-_categoriaTest_-_").size());
		assertNull(cat.obtenerVideosCategoria("_-_categoriaTest_-_QueNoExiste"));
		assertEquals(0, cat.obtenerVideosDtCategoria("_-_categoriaTest_-_").size());
		assertEquals(0, cat.obtenerVideosDtCategoria("_-_categoriaTest_-_QueNoExiste").size());
		
		List<CategoriaDt> categorias = cat.ListarCategorias();
		for(int i=0; i < categorias.size(); i++) {
			CategoriaDt catdt = categorias.get(i);
			if(!catdt.getNombre().contains("_-_categoriaTest_-_")) {
				//Esto es meramente para recorrer el codigo de obtener las listas
				assertEquals(cat.obtenerVideosCategoria(catdt.getNombre()).size(), cat.obtenerVideosDtCategoria(catdt.getNombre()).size());
				//El videoDt recorre video normal ya que lo utiliza
			}
		}
	}
	
	@Test 
	@Order(5)
	public void testEliminarCategoria() {
		System.out.println("Eliminar");
		int esperado = cat.ListarCategorias().size();
		
		cat.EliminarCategoria("_-_categoriaTest_-_QueNoExiste");
		cat.EliminarCategoria("_-_categoriaTest_-_");
		cat.EliminarCategoria("_-_categoriaTest_-_2");
		int resultado = cat.ListarCategorias().size();
		assertEquals(esperado-2, resultado);
	}
	
	@AfterAll
	public static void EliminacionPreviendoFallo() {
		IControladorCategoria cat1 = Fabrica.getInstance().getIControladorCategoria();
		cat1.EliminarCategoria("_-_categoriaTest_-_");
		cat1.EliminarCategoria("_-_categoriaTest_-_2");

		   
	}
	
	
}
