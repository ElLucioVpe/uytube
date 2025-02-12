<%@page import="java.text.SimpleDateFormat"%>
<%@page import="logica.webservices.WScontroladorVideoImplService"%>
<%@page import="logica.webservices.WScontroladorVideo"%>
<%@page import="logica.webservices.VisitaDt"%>
<%@page import="logica.webservices.ListaHistorialDt"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="logica.webservices.UsuarioDt"%>
<%@page import="logica.webservices.VideoDt"%>
<%@page import="logica.webservices.CategoriaDt"%>
<%@page import="logica.webservices.CanalDt"%>
<%@page import="logica.webservices.ListaDeReproduccionDt"%>
<%@page import = "logica.webservices.WScontroladorUsuarioImplService"%>
<%@page import = "logica.webservices.WScontroladorUsuario"%>
<%@page import = "logica.webservices.WScontroladorCategoriaImplService"%>
<%@page import = "logica.webservices.WScontroladorCategoria"%>

<!DOCTYPE html>
<html>
    <%
    	WScontroladorUsuario user = new WScontroladorUsuarioImplService().getWScontroladorUsuarioImplPort();
    
        String path = request.getContextPath();
        
        int user_id = -1;
        ListaDeReproduccionDt lista = null; 
        boolean propietario = false;
        
        if(request.getParameter("user_id") != null && request.getParameter("user_id") != "") {
            user_id = Integer.parseInt(request.getParameter("user_id"));
            if(request.getParameter("nom") != null && request.getParameter("nom") != "") {
                if(session.getAttribute("userid") != null) if(user_id == (int)session.getAttribute("userid")) propietario = true;
                lista = user.obtenerListaDt(user_id, request.getParameter("nom"));
                if(lista instanceof ListaHistorialDt) propietario = false; //oculto modificacion
                
                //Evito consultas a datos de un usuario inactivo
                UsuarioDt dt_propietario = user.consultarUsuario(user_id);
                if(dt_propietario != null) if(!dt_propietario.isActivo()) response.sendRedirect(path+"/index.jsp");
            }
        }
    %>
    <script>
        
        function quitarVideo(id_propietario, nom_lista, id_video) {
            if (confirm('¿Desea eliminar este video de la lista?')) {
                $.ajax({
                    url:'<%=path%>/api/modificarLista.jsp?accion=quitar&id_p='+id_propietario+'&nom='+nom_lista+'&id_v='+id_video,
                    success: function (result) {
                        alert(result);
                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                      console.log(xhr.status);
                      console.log(thrownError);
                    }
                });
            }
        }
    </script>
    
    <head>
    	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/jquery.min.js"></script>
        
        <%if(lista == null) {%>
            <title>uyTube - Lista de Reproducción</title>
        <%} else {%>
            <title>uyTube - Lista de Reproducción <%=lista.getNombre()%></title>
        <%}%>
    </head>
    <body>
        <%@ include file="include/navbar.jsp" %>
        <form class="form-inline">
        <%if(lista == null) {%>
            <h1>ERROR, no hay información que desplegar</h1>
        <%} else {%>
            <div id="columnaInfo" class="col md-5">
                <div class="card">
                    <div class="card-body">
                        <label><h4><%=lista.getNombre()%></h4></label>
                        <hr />
                        <label><b>Categoria: &nbsp </b> <%=lista.getCategoria()%></label>
                        <hr />
                        <label><b>Visibilidad: &nbsp </b> <%if(lista.isPrivada()){%> Privada<%} else {%> Pública <%}%></label>
                        <hr />
                        <label><b>Propietario: &nbsp </b> <%=user.obtenerNickUsuario(lista.getIdUsuario())%></label>
                    </div>
                </div>
            </div>
            <div id="columnaVideos" class="col-md-8">
                <div class="card">
                    <div class="card-body">
                    <%
	                    if(lista instanceof ListaHistorialDt) {
	                    	
	                    	ListaHistorialDt historial = (ListaHistorialDt) lista;
	                    	List<VisitaDt> visitas = historial.getVisitas();
	                    	
	                    	WScontroladorVideo vid = new WScontroladorVideoImplService().getWScontroladorVideoImplPort();
	                    	VideoDt videoaux = null;
	                 		for(int i=0; i < visitas.size(); i++) {
	                 			videoaux = vid.obtenerVideoDtPorID(visitas.get(i).getVideoId());
	                 		%>
                    	<div class="media">
                                <a href="m.video.jsp?id=<%=videoaux.getId()%>" class="pull-left mr-2">
                                    <%if(videoaux.getThumbnail() != "") {%> 
                                        <img src="<%=videoaux.getThumbnail()%>" class="img-thumbnail" alt="Thumbnail">
                                    <%} else {%> 
                                        <img src="../img/no-thumbnail.jpg" width=120 height=90 class="img-thumbnail" alt="Thumbnail">   
                                    <%}%>
                                </a>
                                <div class="media-body">
                                    <h6 class="media-heading"><a href="m.video.jsp?id=<%=videoaux.getId()%>" style="color: black"><%=videoaux.getNombre()%></a>
                                        <%if(propietario) {%> 
                                        <button class="fas fa-times" onclick="quitarVideo('<%=lista.getIdUsuario()%>', '<%=lista.getNombre()%>', '<%=videoaux.getId()%>')"></button> 
                                        <%}%>
                                    </h6>
                                    <a class="text-muted"><%=user.obtenerNickUsuario(videoaux.getIdCanal())%></a>
	                            	<a>
	                            		<i class="fas fa-eye"></i><%=visitas.get(i).getCantidad()%> 
	                            		<i class="fas fa-clock"></i><%=new SimpleDateFormat("yyyy-MM-dd HH:mm").format(visitas.get(i).getFecha().toGregorianCalendar().getTime())%>
	                            	</a>
                            	</div>
                            </div> 
                    	
                    	
                      <%}} else {
                    	
                    	List<VideoDt> videos = user.obtenerVideosLista(user_id, request.getParameter("nom")).getLista();
                        for(int i=0; i < videos.size(); i++) {%>
                            <div class="media">
                                <a href="m.video.jsp?id=<%=videos.get(i).getId()%>" class="pull-left mr-2">
                                    <%if(videos.get(i).getThumbnail() != "") {%> 
                                        <img src="<%=videos.get(i).getThumbnail()%>" class="img-thumbnail" alt="Thumbnail">
                                    <%} else {%> 
                                        <img src="../img/no-thumbnail.jpg" width=120 height=90 class="img-thumbnail" alt="Thumbnail">   
                                    <%}%>
                                </a>
                                <div class="media-body">
                                    <h6 class="media-heading"><a href="m.video.jsp?id=<%=videos.get(i).getId()%>" style="color: black"><%=videos.get(i).getNombre()%></a>
                                        <%if(propietario) {%> 
                                        <button class="fas fa-times" onclick="quitarVideo('<%=lista.getIdUsuario()%>', '<%=lista.getNombre()%>', '<%=videos.get(i).getId()%>')"></button> 
                                        <%}%>
                                    </h6>
                                    <a class="text-muted"><%=user.obtenerNickUsuario(videos.get(i).getIdCanal())%></a>
                            	</div>
                            </div> 
                    <%	}}%>
                    </div>
                </div>
            </div>
        <%}%>
        </form>
         <%@ include file="include/mini-video.jsp" %>
    </body>
</html>
