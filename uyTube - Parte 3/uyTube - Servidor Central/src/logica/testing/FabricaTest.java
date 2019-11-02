package logica.testing;



import static org.junit.jupiter.api.Assertions.*;

import logica.controladores.Fabrica;
import logica.controladores.IControladorUsuario;
import logica.controladores.IControladorVideo;
import logica.controladores.IControladorCategoria;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
public class FabricaTest {
    
	Fabrica fab = Fabrica.getInstance();
	
	@Test 
	@Order(1)
    public void testGetInstance() {
        System.out.println("getInstance");
        assertNotNull(fab);
        Fabrica instance2 = Fabrica.getInstance();
        assertSame(fab, instance2);
    }

    @Test
    public void testGetIControladorUsuario() {
        System.out.println("getIControladorUsuario");
        IControladorUsuario instance1 = fab.getIControladorUsuario();
        assertNotNull(instance1);
        IControladorUsuario instance2 = fab.getIControladorUsuario();
        assertSame(instance1, instance2);
    }


    @Test
    public void testGetIControladorVideo() {
        System.out.println("getIControladorVideo");
        IControladorVideo instance1 = fab.getIControladorVideo();
        assertNotNull(instance1);
        IControladorVideo instance2 = fab.getIControladorVideo();
        assertSame(instance1, instance2);
    }

    @Test
    public void testGetIControladorCategoria() {
        System.out.println("getIControladorCategoria");
        IControladorCategoria instance1 = fab.getIControladorCategoria();
        assertNotNull(instance1);
        IControladorCategoria instance2 = fab.getIControladorCategoria();
        assertSame(instance1, instance2);
    }
    
}
