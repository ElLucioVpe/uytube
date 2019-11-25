
package logica.webservices;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Action;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "WScontroladorUsuario", targetNamespace = "http://webservices.logica/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WScontroladorUsuario {


    /**
     * 
     * @param arg0
     * @return
     *     returns logica.webservices.PojoString
     */
    @WebMethod(operationName = "ListarSeguidores")
    @WebResult(partName = "return")
    @Action(input = "http://webservices.logica/WScontroladorUsuario/ListarSeguidoresRequest", output = "http://webservices.logica/WScontroladorUsuario/ListarSeguidoresResponse")
    public PojoString listarSeguidores(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg5
     * @param arg4
     * @param arg1
     * @param arg0
     * @param arg7
     * @param arg6
     * @param arg9
     * @param arg8
     */
    @WebMethod(operationName = "ModificarUsuario")
    @Action(input = "http://webservices.logica/WScontroladorUsuario/ModificarUsuarioRequest", output = "http://webservices.logica/WScontroladorUsuario/ModificarUsuarioResponse")
    public void modificarUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3,
        @WebParam(name = "arg4", partName = "arg4")
        XMLGregorianCalendar arg4,
        @WebParam(name = "arg5", partName = "arg5")
        String arg5,
        @WebParam(name = "arg6", partName = "arg6")
        String arg6,
        @WebParam(name = "arg7", partName = "arg7")
        String arg7,
        @WebParam(name = "arg8", partName = "arg8")
        boolean arg8,
        @WebParam(name = "arg9", partName = "arg9")
        String arg9);

    /**
     * 
     * @param arg0
     * @return
     *     returns logica.webservices.UsuarioDt
     */
    @WebMethod(operationName = "ConsultarUsuario")
    @WebResult(partName = "return")
    @Action(input = "http://webservices.logica/WScontroladorUsuario/ConsultarUsuarioRequest", output = "http://webservices.logica/WScontroladorUsuario/ConsultarUsuarioResponse")
    public UsuarioDt consultarUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod(operationName = "EliminarUsuario")
    @Action(input = "http://webservices.logica/WScontroladorUsuario/EliminarUsuarioRequest", output = "http://webservices.logica/WScontroladorUsuario/EliminarUsuarioResponse")
    public void eliminarUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns logica.webservices.ListaDeReproduccionDt
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.logica/WScontroladorUsuario/obtenerListaDtRequest", output = "http://webservices.logica/WScontroladorUsuario/obtenerListaDtResponse")
    public ListaDeReproduccionDt obtenerListaDt(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     */
    @WebMethod(operationName = "QuitarVideoListaDeReproduccion")
    @Action(input = "http://webservices.logica/WScontroladorUsuario/QuitarVideoListaDeReproduccionRequest", output = "http://webservices.logica/WScontroladorUsuario/QuitarVideoListaDeReproduccionResponse")
    public void quitarVideoListaDeReproduccion(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        int arg2);

    /**
     * 
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @Action(input = "http://webservices.logica/WScontroladorUsuario/actualizarVisitaListahistorialRequest", output = "http://webservices.logica/WScontroladorUsuario/actualizarVisitaListahistorialResponse")
    public void actualizarVisitaListahistorial(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0,
        @WebParam(name = "arg1", partName = "arg1")
        int arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns logica.webservices.ListaHistorialDt
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.logica/WScontroladorUsuario/obtenerListaHistorialRequest", output = "http://webservices.logica/WScontroladorUsuario/obtenerListaHistorialResponse")
    public ListaHistorialDt obtenerListaHistorial(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @Action(input = "http://webservices.logica/WScontroladorUsuario/dejarDeSeguirUsuarioRequest", output = "http://webservices.logica/WScontroladorUsuario/dejarDeSeguirUsuarioResponse")
    public void dejarDeSeguirUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns logica.webservices.PojoVideoDt
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.logica/WScontroladorUsuario/listarVideosDeUsuarioRequest", output = "http://webservices.logica/WScontroladorUsuario/listarVideosDeUsuarioResponse")
    public PojoVideoDt listarVideosDeUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.logica/WScontroladorUsuario/obtenerIdUsuarioMailRequest", output = "http://webservices.logica/WScontroladorUsuario/obtenerIdUsuarioMailResponse")
    public int obtenerIdUsuarioMail(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.logica/WScontroladorUsuario/obtenerNickUsuarioRequest", output = "http://webservices.logica/WScontroladorUsuario/obtenerNickUsuarioResponse")
    public String obtenerNickUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns logica.webservices.PojoVideoDt
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.logica/WScontroladorUsuario/obtenerVideosListaRequest", output = "http://webservices.logica/WScontroladorUsuario/obtenerVideosListaResponse")
    public PojoVideoDt obtenerVideosLista(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns logica.webservices.PojoString
     */
    @WebMethod(operationName = "ListarSiguiendo")
    @WebResult(partName = "return")
    @Action(input = "http://webservices.logica/WScontroladorUsuario/ListarSiguiendoRequest", output = "http://webservices.logica/WScontroladorUsuario/ListarSiguiendoResponse")
    public PojoString listarSiguiendo(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns int
     */
    @WebMethod(operationName = "LoginUsuario")
    @WebResult(partName = "return")
    @Action(input = "http://webservices.logica/WScontroladorUsuario/LoginUsuarioRequest", output = "http://webservices.logica/WScontroladorUsuario/LoginUsuarioResponse")
    public int loginUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.logica/WScontroladorUsuario/estaSuscriptoRequest", output = "http://webservices.logica/WScontroladorUsuario/estaSuscriptoResponse")
    public boolean estaSuscripto(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0,
        @WebParam(name = "arg1", partName = "arg1")
        int arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns logica.webservices.PojoListadrDt
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.logica/WScontroladorUsuario/obtenerListasDtPorUsuarioRequest", output = "http://webservices.logica/WScontroladorUsuario/obtenerListasDtPorUsuarioResponse")
    public PojoListadrDt obtenerListasDtPorUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns logica.webservices.ListaDeReproduccionDt
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.logica/WScontroladorUsuario/obtenerListaDtPorIdRequest", output = "http://webservices.logica/WScontroladorUsuario/obtenerListaDtPorIdResponse")
    public ListaDeReproduccionDt obtenerListaDtPorId(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns logica.webservices.CanalDt
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.logica/WScontroladorUsuario/obtenerCanalDtRequest", output = "http://webservices.logica/WScontroladorUsuario/obtenerCanalDtResponse")
    public CanalDt obtenerCanalDt(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod(operationName = "BajaUsuario")
    @Action(input = "http://webservices.logica/WScontroladorUsuario/BajaUsuarioRequest", output = "http://webservices.logica/WScontroladorUsuario/BajaUsuarioResponse")
    public void bajaUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.logica/WScontroladorUsuario/probandoRequest", output = "http://webservices.logica/WScontroladorUsuario/probandoResponse")
    public String probando(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.logica/WScontroladorUsuario/saveFileRequest", output = "http://webservices.logica/WScontroladorUsuario/saveFileResponse")
    public boolean saveFile(
        @WebParam(name = "arg0", partName = "arg0")
        byte[] arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.logica/WScontroladorUsuario/obtenerIdUsuarioRequest", output = "http://webservices.logica/WScontroladorUsuario/obtenerIdUsuarioResponse")
    public int obtenerIdUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg5
     * @param arg4
     * @param arg1
     * @param arg0
     * @param arg6
     */
    @WebMethod(operationName = "AltaUsuario")
    @Action(input = "http://webservices.logica/WScontroladorUsuario/AltaUsuarioRequest", output = "http://webservices.logica/WScontroladorUsuario/AltaUsuarioResponse")
    public void altaUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3,
        @WebParam(name = "arg4", partName = "arg4")
        String arg4,
        @WebParam(name = "arg5", partName = "arg5")
        XMLGregorianCalendar arg5,
        @WebParam(name = "arg6", partName = "arg6")
        String arg6);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg4
     * @param arg1
     * @param arg0
     */
    @WebMethod(operationName = "AltaCanal")
    @Action(input = "http://webservices.logica/WScontroladorUsuario/AltaCanalRequest", output = "http://webservices.logica/WScontroladorUsuario/AltaCanalResponse")
    public void altaCanal(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        boolean arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        int arg3,
        @WebParam(name = "arg4", partName = "arg4")
        String arg4);

    /**
     * 
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @Action(input = "http://webservices.logica/WScontroladorUsuario/seguirUsuarioRequest", output = "http://webservices.logica/WScontroladorUsuario/seguirUsuarioResponse")
    public void seguirUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg0
     */
    @WebMethod(operationName = "AltaListaDeReproduccionPorDefecto")
    @Action(input = "http://webservices.logica/WScontroladorUsuario/AltaListaDeReproduccionPorDefectoRequest", output = "http://webservices.logica/WScontroladorUsuario/AltaListaDeReproduccionPorDefectoResponse")
    public void altaListaDeReproduccionPorDefecto(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     */
    @WebMethod(operationName = "AltaListaDeReproduccionParticular")
    @Action(input = "http://webservices.logica/WScontroladorUsuario/AltaListaDeReproduccionParticularRequest", output = "http://webservices.logica/WScontroladorUsuario/AltaListaDeReproduccionParticularResponse")
    public void altaListaDeReproduccionParticular(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        int arg1,
        @WebParam(name = "arg2", partName = "arg2")
        boolean arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     */
    @WebMethod(operationName = "AgregarVideoListaDeReproduccion")
    @Action(input = "http://webservices.logica/WScontroladorUsuario/AgregarVideoListaDeReproduccionRequest", output = "http://webservices.logica/WScontroladorUsuario/AgregarVideoListaDeReproduccionResponse")
    public void agregarVideoListaDeReproduccion(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0,
        @WebParam(name = "arg1", partName = "arg1")
        int arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3);

    /**
     * 
     * @return
     *     returns logica.webservices.PojoUsuarioDt
     */
    @WebMethod(operationName = "ListarUsuarios")
    @WebResult(partName = "return")
    @Action(input = "http://webservices.logica/WScontroladorUsuario/ListarUsuariosRequest", output = "http://webservices.logica/WScontroladorUsuario/ListarUsuariosResponse")
    public PojoUsuarioDt listarUsuarios();

    /**
     * 
     * @return
     *     returns logica.webservices.PojoListadrDt
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.logica/WScontroladorUsuario/obtenerListasRequest", output = "http://webservices.logica/WScontroladorUsuario/obtenerListasResponse")
    public PojoListadrDt obtenerListas();

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.logica/WScontroladorUsuario/obtenerTipoListaRequest", output = "http://webservices.logica/WScontroladorUsuario/obtenerTipoListaResponse")
    public String obtenerTipoLista(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns logica.webservices.PojoString
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.logica/WScontroladorUsuario/obtenerListasUsuarioRequest", output = "http://webservices.logica/WScontroladorUsuario/obtenerListasUsuarioResponse")
    public PojoString obtenerListasUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0);

    /**
     * 
     * @return
     *     returns logica.webservices.PojoUsuarioDt
     */
    @WebMethod(operationName = "ListarUsuariosInactivos")
    @WebResult(partName = "return")
    @Action(input = "http://webservices.logica/WScontroladorUsuario/ListarUsuariosInactivosRequest", output = "http://webservices.logica/WScontroladorUsuario/ListarUsuariosInactivosResponse")
    public PojoUsuarioDt listarUsuariosInactivos();

    /**
     * 
     * @param arg0
     * @return
     *     returns logica.webservices.PojoString
     */
    @WebMethod(operationName = "ListarVideos")
    @WebResult(partName = "return")
    @Action(input = "http://webservices.logica/WScontroladorUsuario/ListarVideosRequest", output = "http://webservices.logica/WScontroladorUsuario/ListarVideosResponse")
    public PojoString listarVideos(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0);

    /**
     * 
     * @return
     *     returns logica.webservices.PojoString
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.logica/WScontroladorUsuario/obtenerCategoriasRequest", output = "http://webservices.logica/WScontroladorUsuario/obtenerCategoriasResponse")
    public PojoString obtenerCategorias();

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     */
    @WebMethod(operationName = "ModificarListaDeReproduccion")
    @Action(input = "http://webservices.logica/WScontroladorUsuario/ModificarListaDeReproduccionRequest", output = "http://webservices.logica/WScontroladorUsuario/ModificarListaDeReproduccionResponse")
    public void modificarListaDeReproduccion(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        boolean arg3);

}
