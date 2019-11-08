package logica.webservices;


import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import logica.dt.CategoriaDt;
import logica.webservices.pojos.POJOCategoriaDt;
import logica.webservices.pojos.POJOListadrDt;
import logica.webservices.pojos.POJOVideoDt;

@WebService
@SOAPBinding(style = Style.RPC)
public interface WScontroladorCategoria {
	
	@WebMethod void AltaCategoria(String nombre);
	@WebMethod POJOCategoriaDt ListarCategorias();
	@WebMethod CategoriaDt ConsultarCategorias(String nombre);
	//@WebMethod ArrayList<ListaDeReproduccion> obtenerListasCategoria(String nom);
	//@WebMethod ArrayList<Video> obtenerVideosCategoria(String nom);
	@WebMethod POJOListadrDt obtenerListasDtCategoria(String nom);
	@WebMethod POJOVideoDt obtenerVideosDtCategoria(String nom);
}
