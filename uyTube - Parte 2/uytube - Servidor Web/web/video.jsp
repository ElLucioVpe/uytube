A<%-- 
    Document   : video
    Created on : Oct 8, 2019, 12:48:34 AM
    Author     : Luciano
--%>

<%@page import="javax.swing.tree.TreeNode"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Vector"%>
<%@page import="logica.dt.valoracionDt"%>
<%@page import="java.util.List"%>
<%@page import="javax.swing.tree.DefaultMutableTreeNode"%>
<%@page import = "javax.persistence.*"%>
<%@page import = "logica.controladores.Fabrica"%>
<%@page import = "logica.controladores.IControladorUsuario"%>
<%@page import = "logica.controladores.IControladorVideo"%>
<%@page import = "logica.dt.VideoDt" %>
<%@page import = "logica.dt.UsuarioDt" %>
<%@page import = "logica.Comentario" %>

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
        
        <!-- Font Awesome -->
        <link href="css/fontawesome.min.css" rel="stylesheet">
        <script defer src="js/solid.js"></script>
        <script defer src="js/fontawesome.js"></script>
        
        <title>uyTube - Transmite tú mismo</title>
    </head>
    <body>
        <% Fabrica f = Fabrica.getInstance();
            IControladorUsuario user = f.getIControladorUsuario();
            IControladorVideo video = f.getIControladorVideo();
            int video_id = Integer.parseInt(request.getParameter("id"));
            VideoDt dt = video.obtenerVideoDtPorID(video_id);
            UsuarioDt u = user.ConsultarUsuario(dt.getId());
            session.setAttribute("videoid", video_id);
            List<String> seguidores = user.ListarSeguidores(dt.getId());
            
            String imagenUser = "img/user.png";
            if(u.getImagen() != null) imagenUser = "http://localhost:8080/images/"+u.getImagen();
            
            Boolean estaSuscripto = false;
            if(session.getAttribute("userid") != null) estaSuscripto = user.estaSuscripto((int)session.getAttribute("userid"), u.getId());
        %>
        
        <%@ include file="include/header.jsp" %>

        <script>var video_id="<%=video_id%>";</script>
        <script>
            $(document).ready(function(){
                cargarComentarios();
            });
            
            function cargarComentarios() {
                $.ajax({
                    url: "http://localhost:8080/WebApplication/api/obtenerComentariosVideo.jsp?id_v="+'<%=video_id%>', 
                   success: function (result) {
                       var html = result;
                       $('#comentarios').html(html);
                   },
                   error: function (xhr, ajaxOptions, thrownError) {
                      console.log(xhr.status);
                      console.log(thrownError);
                   } 
                });
            }
            
            function comentarVideo(id_padre) {
                //-1 = no padre
                var id_u = '<%=session.getAttribute("userid")%>';
                var id_v = '<%=video_id%>';   
                var text = $('#textArea'+id_p).val();
                var id_p = id_padre; console.log(id_p);
                if(id_u !== null && id_u !== "null") {
                    $.ajax({
                        url: "http://localhost:8080/WebApplication/api/comentarVideo.jsp?id_u="+id_u+"&id_v="+id_v+"&id_p="+id_p+"&text="+text, 
                        success: function (result) {
                            alert(result);
                        },
                        error: function (xhr, ajaxOptions, thrownError) {
                            console.log(xhr.status);
                            console.log(thrownError);
                        } 
                    });
                } else {
                    alert("Por favor inicie sesión para comentar el video");
                }
            }
            
        </script>
        

        <div id="video-show">
            <%=dt.getEmbedded()%>
        </div>
        <div class="container">
            <div id="video-data">
            <h1 id="video-titulo"><%=dt.getNombre()%></h1>
            <div>
                <img id="user-pic" src="http://localhost:8080/images/<%=u.getImagen()%>" height="30px" width="30px" alt="Profile de Usuario"/>
                <span id="user-nick"><%=u.getNickname()%></span>
                <button class="btn btn-primary" onclick="suscripcion(<%=u.getId()%>)">
                    <%if(!estaSuscripto){%>
                    Suscribirse
                    <%}else{%>
                    Desuscribirse
                    <%}%>
                </button>
                <span id="user-subs"><%=seguidores.size() %> seguidores</span>
            </div>
            <hr>
            <div class="row">
                <div class="col-sm-6">
                    <h5> Descripción</h5>
                    <p id="video-desc"><%=dt.getDescripcion()%></p>

                    <div>
                        <button class="btn btn-primary" onclick="gustar(true)"><i class="fa fa-thumbs-up"></i>
                        <span><b></b> <%=dt.getLikes()%></span></button>
                        <button class="btn btn-primary" onclick="gustar(false)"><i class="fa fa-thumbs-down"></i>
                        <span><b></b> <%=dt.getDislikes()%></span></button>
                    </div>
                </div>
                <div class="col-sm-3">
                    <p id="video-cat"><b>Categoría:</b> <%=dt.getCategoria()%></p>
                    <p id="video-duracion"><b>Duración:</b> <%=dt.getDuracion()%> minutos</p>
                    <p id="video-fecha"><b>Fecha:</b> <%=dt.getFechaPublicacion().toString()%></p>
                </div>
            </div>
            <div class="row">
                <div class="col">
                        <h5> Comentarios </h5>
                    <div class="form-group row">
                        <div class="col-sm-6">
                            <textarea class="form-control" rows="2" placeholder="Agregue un comentario..." required></textarea>
                        </div>
                        <button type="button" class="btn btn-success" onclick="comentarVideo(-1)">Comentar</button>
                    </div>
                    <div class="card">
                        <div id="comentarios" class="card-body">
                            <%-- Muchos comentarios --%>
                        </div>
                    </div>
                </div>
            </div>
        <%@ include file="include/footer.jsp" %>
    </body>
</html>