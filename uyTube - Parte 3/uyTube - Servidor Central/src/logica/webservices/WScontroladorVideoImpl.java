package logica.webservices;

import java.util.ArrayList;
import java.util.Date;

import javax.jws.WebService;
import logica.controladores.Fabrica;
import logica.controladores.IControladorVideo;
import logica.dt.ComentarioDt;
import logica.dt.VideoDt;
import logica.dt.valoracionDt;

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
		controlador.ComentarVideo(user_id, video_id, id_padre, texto, fecha);
		ComentarioDt com = new ComentarioDt();
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
	public ArrayList<valoracionDt> obtenerValoracionVideo(int id_video) {
		return new ArrayList<>(controlador.obtenerValoracionVideo(id_video));
	}

	@Override
	public ArrayList<VideoDt> obtenerVideos() {
		return new ArrayList<>(controlador.obtenerVideos());
	}

	@Override
	public ArrayList<ComentarioDt> obtenerComentariosDt(int id_video) {
		return new ArrayList<>(controlador.obtenerComentariosDt(id_video));
	}

	@Override
	public VideoDt obtenerVideoDtPorCOD(String codigo) {
		return controlador.obtenerVideoDtPorCOD(codigo);
	}

}
