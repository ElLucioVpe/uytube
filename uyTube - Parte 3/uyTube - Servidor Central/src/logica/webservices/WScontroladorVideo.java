package logica.webservices;

import java.util.ArrayList;
import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import logica.dt.ComentarioDt;
import logica.dt.VideoDt;
import logica.dt.valoracionDt;

@WebService
@SOAPBinding(style = Style.RPC)
public interface WScontroladorVideo {
	
	@WebMethod String probando(String text);
	@WebMethod ComentarioDt pruebaComentarioDt();
	@WebMethod void AltaVideo(String nombre, String duracion, String url, String desc, int user, String categoria);
	@WebMethod void ModificarVideo(int id_vid, String nuevoNom, String nuevaDur, String nuevaUrl, String nuevaDesc, Date nuevaFpub, boolean nuevaPriv, String nuevaCat);
	@WebMethod void ValorarVideo(int user_valoracion, int id_video, boolean gusta);
	@WebMethod void ComentarVideo(int user_id, int video_id, long id_padre, String texto, Date fecha);
	@WebMethod VideoDt obtenerVideoDt(String video, Integer user);
	@WebMethod VideoDt obtenerVideoDtPorID(int id_video);
	@WebMethod ArrayList<valoracionDt>  obtenerValoracionVideo(int id_video);
	@WebMethod ArrayList<VideoDt> obtenerVideos();
	@WebMethod ArrayList<ComentarioDt> obtenerComentariosDt(int id_video);
	@WebMethod VideoDt obtenerVideoDtPorCOD(String codigo);

}
