package logica.webservices;

import java.util.ArrayList;

import javax.jws.WebService;

import logica.controladores.Fabrica;
import logica.controladores.IControladorCategoria;
import logica.dt.CategoriaDt;
import logica.dt.ListaDeReproduccionDt;
import logica.dt.VideoDt;
import logica.webservices.pojos.POJOCategoriaDt;
import logica.webservices.pojos.POJOListadrDt;
import logica.webservices.pojos.POJOString;
import logica.webservices.pojos.POJOVideoDt;

@WebService(endpointInterface = "logica.webservices.WScontroladorCategoria")
public class WScontroladorCategoriaImpl implements WScontroladorCategoria {

	Fabrica fab = Fabrica.getInstance();
	final IControladorCategoria controlador = fab.getIControladorCategoria();
	
	@Override
	public void AltaCategoria(String nombre) {
		controlador.AltaCategoria(nombre);
	}

	@Override
	public POJOCategoriaDt ListarCategorias() {
		return new POJOCategoriaDt(controlador.ListarCategorias());
	}

	@Override
	public CategoriaDt ConsultarCategorias(String nombre) {
		return controlador.ConsultarCategorias(nombre);
	}

	/*@Override
	public ArrayList<ListaDeReproduccion> obtenerListasCategoria(String nom) {
		return new ArrayList<>(controlador.obtenerListasCategoria(nom));
	}

	@Override
	public ArrayList<Video> obtenerVideosCategoria(String nom) {
		return new ArrayList<>(controlador.obtenerVideosCategoria(nom);
	}*/

	@Override
	public POJOListadrDt obtenerListasDtCategoria(String nom) {
		return new POJOListadrDt(controlador.obtenerListasDtCategoria(nom));
	}

	@Override
	public POJOVideoDt obtenerVideosDtCategoria(String nom) {
		return new POJOVideoDt(controlador.obtenerVideosDtCategoria(nom));
	}

}
