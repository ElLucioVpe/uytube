package logica.webservices;

import java.util.ArrayList;
import java.util.Date;

import javax.jws.WebService;
import logica.controladores.Fabrica;
import logica.controladores.IControladorVideo;
import logica.dt.ComentarioDt;
import logica.dt.VideoDt;
import logica.dt.valoracionDt;
import logica.webservices.pojos.POJOComentarioDt;
import logica.webservices.pojos.POJOString;
import logica.webservices.pojos.POJOVideoDt;
import logica.webservices.pojos.POJOvaloracionDt;

@WebService(endpointInterface = "logica.webservices.WScontroladorVideo")
public class WScontroladorVideoImpl implements WScontroladorVideo {

	Fabrica fab = Fabrica.getInstance();
	final IControladorVideo controlador = fab.getIControladorVideo();
	
	@Override
	public String probando(String text) {
		return "probando "+text+"...";
	}
	
	@Override
	public ComentarioDt pruebaComentarioDt() {
		//Hecho solamente para poder obtener la clase en el wsdl
		return new ComentarioDt(0, -1, "prueba", null, null, null);
	}

	@Override
	public void AltaVideo(String nombre, String duracion, String url, String desc, int user, String categoria) {
		controlador.AltaVideo(nombre, duracion, url, desc, user, categoria);
	}

	@Override
	public void ModificarVideo(int id_vid, String nuevoNom, String nuevaDur, String nuevaUrl, String nuevaDesc,
			Date nuevaFpub, boolean nuevaPriv, String nuevaCat) {
		controlador.ModificarVideo(id_vid, nuevoNom, nuevaDur, nuevaUrl, nuevaDesc, nuevaFpub, nuevaPriv, nuevaCat);

	}

	@Override
	public void ValorarVideo(int user_valoracion, int id_video, boolean gusta) {
		controlador.ValorarVideo(user_valoracion, id_video, gusta);
	}

	@Override
	public void ComentarVideo(int user_id, int video_id, long id_padre, String texto, Date fecha) {
		ComentarioDt com = new ComentarioDt(); //Para que se genere la clase en wsimport
		controlador.ComentarVideo(user_id, video_id, id_padre, texto, fecha);
	}

	@Override
	public VideoDt obtenerVideoDt(String video, Integer user) {
		return controlador.obtenerVideoDt(video, user);
	}

	@Override
	public VideoDt obtenerVideoDtPorID(int id_video) {
		return controlador.obtenerVideoDtPorID(id_video);
	}

	@Override
	public POJOvaloracionDt obtenerValoracionVideo(int id_video) {
		valoracionDt val = new valoracionDt(); //Para que se genere la clase en wsimport
		return new POJOvaloracionDt(controlador.obtenerValoracionVideo(id_video));
	}

	@Override
	public POJOVideoDt obtenerVideos() {
		return new POJOVideoDt(controlador.obtenerVideos());
	}

	@Override
	public POJOComentarioDt obtenerComentariosDt(int id_video) {
		return new POJOComentarioDt(controlador.obtenerComentariosDt(id_video));
	}

	@Override
	public VideoDt obtenerVideoDtPorCOD(String codigo) {
		return controlador.obtenerVideoDtPorCOD(codigo);
	}

}
