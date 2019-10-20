package logica.testing;

import logica.controladores.Fabrica;
import logica.controladores.IControladorUsuario;
import logica.controladores.IControladorVideo;
import logica.controladores.IControladorCategoria;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class FabricaTest {

    /**
     * Test of getInstance method, of class Fabrica.
     */
    
    @BeforeAll
    public void testGetInstance() {
        System.out.println("getInstance");
        Fabrica instance1 = Fabrica.getInstance();
        assertNotNull(instance1);
        Fabrica instance2 = Fabrica.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    public void testGetIControladorUsuario() {
        System.out.println("getIControladorUsuario");
        Fabrica instance = Fabrica.getInstance();
        IControladorUsuario instance1 = instance.getIControladorUsuario();
        assertNotNull(instance1);
        IControladorUsuario instance2 = instance.getIControladorUsuario();
        assertSame(instance1, instance2);
    }


    @Test
    public void testGetIControladorVideo() {
        System.out.println("getIControladorVideo");
        Fabrica instance = Fabrica.getInstance();
        IControladorVideo instance1 = instance.getIControladorVideo();
        assertNotNull(instance1);
        IControladorVideo instance2 = instance.getIControladorVideo();
        assertSame(instance1, instance2);
    }

    @Test
    public void testGetIControladorCategoria() {
        System.out.println("getIControladorCategoria");
        Fabrica instance = Fabrica.getInstance();
        IControladorCategoria instance1 = instance.getIControladorCategoria();
        assertNotNull(instance1);
        IControladorCategoria instance2 = instance.getIControladorCategoria();
        assertSame(instance1, instance2);
    }
}
