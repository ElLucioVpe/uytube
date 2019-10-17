<%-- 
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
            
            DefaultMutableTreeNode root = video.obtenerComentariosVideo(dt.getId());
            List<valoracionDt> list = video.obtenerValoracionVideo(dt.getId());
            
            Boolean estaSuscripto = false;
            if(session.getAttribute("userid") != null) estaSuscripto = user.estaSuscripto((int)session.getAttribute("userid"), u.getId());
        %>
        
        <%!
            public String[][] getComentario(DefaultMutableTreeNode root) {
                String[][] comentarios = new String[5][];
                if(root.children() != null) {
                    Enumeration<TreeNode> vc = root.children();
                    while(vc.hasMoreElements()) {
                        TreeNode n = (TreeNode) vc.nextElement();
                        getComentarioRec(n);
                    }
                }
                return;
            }

            public Comentario getComentarioRec(TreeNode root) {
                if(root.children() != null) {
                    while(vc.hasMoreElements()) {
                        TreeNode n = (TreeNode) vc.nextElement();
                        n.toString();
                    }
                }
                return new Comentario();
            }
        %>
        
        <%@ include file="include/header.jsp" %>

        <script>
            var video_id="<%=video_id%>";
            function conectate() {
                alert("Por favor, para realizar esta acción inicie sesion.");
            }
            function suscripcion(seguido) {
                var seguidor = '<%=session.getAttribute("userid")%>';
                if(seguidor === null) conectate();
                else{
                $.ajax({
                url: "/WebApplication/api/suscripcion.jsp?seguidor="+seguidor+"&seguido="+seguido,
                success: function() {
                alert("Suscripción/Desuscripción exitosa");
                },
                error: function () { alert("Error en la suscripción");}
                });
                }
            }
            function gustar(g) {
                var user = '<%=session.getAttribute("userid")%>';
                var video = '<%=video_id%>';
                var v = '<%=video%>';

                if(user === null) conectate();
                else{
                $.ajax({
                    url: "/WebApplication/api/valoracion.jsp?user_id="+user+"&video_id="+video+"&gusta="+g,
                    success: function() {
                        alert("Valoración exitosa");
                    },
                    error: function () { alert("Error en la valoración del video");}
                    });
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
            <hr>
            <div class="row">
                <div class="col-sm-6">
                    <table class="table">
                        <thead>
                          <tr>
                            <th scope="col">#</th>
                            <th scope="col">First</th>
                            <th scope="col">Last</th>
                            <th scope="col">Handle</th>
                          </tr>
                        </thead>
                        <tbody>
                            <%
                                getComentario(root);
                            %>
                        </tbody>
                      </table>
                </div>
            </div>
        </div>
        </div>
        <%@ include file="include/footer.jsp" %>
    </body>
</html>