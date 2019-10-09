<%-- 
    Document   : video
    Created on : Oct 8, 2019, 12:48:34 AM
    Author     : Luciano
--%>

<%@page import="logica.dt.valoracionDt"%>
<%@page import="java.util.List"%>
<%@page import="javax.swing.tree.DefaultMutableTreeNode"%>
<%@page import = "javax.persistence.*"%>
<%@page import = "logica.controladores.Fabrica"%>
<%@page import = "logica.controladores.IControladorUsuario"%>
<%@page import = "logica.controladores.IControladorVideo"%>
<%@page import = "logica.dt.VideoDt" %>
<%@page import = "logica.dt.UsuarioDt" %>

<%@page import="java.util.Date"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/video.css">
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.min.js"></script>

        <title>uyTube - Transmite tú mismo</title>
    </head>
    <body>
        <% Fabrica f = Fabrica.getInstance();
            IControladorUsuario user = f.getIControladorUsuario();
            IControladorVideo video = f.getIControladorVideo();
            int video_id = Integer.parseInt(request.getParameter("id"));
            VideoDt dt = video.obtenerVideoDtPorID(video_id);
            UsuarioDt u = user.ConsultarUsuario(dt.getId());
            List<String> seguidores = user.ListarSeguidores(dt.getId());
            
            DefaultMutableTreeNode root = video.obtenerComentariosVideo(dt.getId());
            //Valoraciones
            List<valoracionDt> list = video.obtenerValoracionVideo(dt.getId());
            DefaultMutableTreeNode root = video.obtenerComentariosVideo(dt.getId());
        %>
        
        <%@ include file="include/header.jsp" %>

        <script>var video_id="<%=video_id%>";</script>
        
        <div id="video-show">
            <%=dt.getEmbedded()%>
        </div>
        <div id="video-data">
            <h1 id="video-titulo"><%=dt.getNombre()%></h1>
            
            <div>
                <img id="user-pic" src="http://localhost:8080/images/<%=u.getImagen()%>" height="30px" width="30px" alt="Profile de Usuario"/>
                <span id="user-nick"><%=u.getNickname()%></span>
                <span id="user-subs"><%=seguidores.size() %> seguidores</span>
            </div>
            <hr>
            <div class="row">
                <div class="col-sm-3">
                    <table class="table" id="tblValoraciones">
                      <thead>
                        <tr>
                          <th class="thumbnail">Usuario</th>
                          <th class="titulo" scope="col">Valoracion</th>
                        </tr>
                      </thead>
                      <tbody>
                        <%
                            if(list != null) for(int i = 0; i < list.size(); i++) {
                                String gustar = "Me gusta";
                                if(!list.get(i).getGusto()) gustar = "No me gusta";
                                %>
                                <tr>
                                    <td><%= list.get(i).getUser() %></td>
                                    <td> <%= gustar %> </td>
                                </tr>
                                <%
                            }
                        %>
                      </tbody>
                    </table>
                </div>
                <div class="col-sm-6">
                    <p id="video-desc"><%=dt.getDescripcion()%></p>

                    <span><b>Likes:</b> <%=dt.getLikes()%></span>
                    <span><b>Dislikes:</b> <%=dt.getDislikes()%></span>
                </div>
                <div class="col-sm-3">
                    <p id="video-cat"><b>Categoría:</b> <%=dt.getCategoria()%></p>
                    <p id="video-duracion"><b>Duración:</b> <%=dt.getDuracion()%> minutos</p>
                    <p id="video-fecha"><b>Fecha:</b> <%=dt.getFechaPublicacion().toString()%></p>
                </div>
            </div>
            <hr>
            
        </div>
        <%@ include file="include/footer.jsp" %>
    </body>
</html>
