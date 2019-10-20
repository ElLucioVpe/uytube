package logica.testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({FabricaTest.class, IControladorCategoriaTest.class, IControladorVideoTest.class})
public class TestsSuite {
	//Esto va vacio es solo para poner lo de arriba y que identifique el grupo de clases
}
