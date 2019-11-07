package logica.webservices;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import logica.dt.CategoriaDt;
import logica.dt.ListaDeReproduccionDt;
import logica.dt.VideoDt;

@WebService
@SOAPBinding(style = Style.RPC)
public interface WScontroladorCategoria {
	
	@WebMethod void AltaCategoria(String nombre);
	@WebMethod ArrayList<CategoriaDt> ListarCategorias();
	@WebMethod CategoriaDt ConsultarCategorias(String nombre);
	//@WebMethod ArrayList<ListaDeReproduccion> obtenerListasCategoria(String nom);
	//@WebMethod ArrayList<Video> obtenerVideosCategoria(String nom);
	@WebMethod ArrayList<ListaDeReproduccionDt> obtenerListasDtCategoria(String nom);
	@WebMethod ArrayList<VideoDt> obtenerVideosDtCategoria(String nom);
}
