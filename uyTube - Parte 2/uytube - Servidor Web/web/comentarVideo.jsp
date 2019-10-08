<%-- 
    Document   : comentarVideo
    Created on : 7 oct. 2019, 21:17:35
    Author     : Esteban
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "logica.controladores.Fabrica" %>
<%@page import = "logica.controladores.IControladorVideo"%>
<%@page import = "java.util.Date"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.min.js"></script>
        <title>Comentando un video</title>
            <%
                Fabrica f = Fabrica.getInstance();
                IControladorVideo vid = f.getIControladorVideo();
                int video_id = -1;//esto deberia desplegarse en consultarVideo y tener la id del video consultado
                int padre = -1;//-1 = no padre, si es respuesta se le asignara el padre correspondiente
                
                if(session.getAttribute("videoid") != null) video_id = (int) session.getAttribute("videoid");
                if(session.getAttribute("comentariopadre") != null) padre = (int) session.getAttribute("comentariopadre");
                
                if (video_id < 1) { %>
                    <div class="alert alert-danger" role="alert">
                        Tu no deberias estar aqui, videoid no esta asignado
                    </div>
                <%}
                session.setAttribute("alert", "Si quiere comentar el video por favor inicie sesion.");

                if(session.getAttribute("userid") != null) {
                    session.setAttribute("alert", "Por favor escriba algo");
                    if (request.getParameter("comentar") != null) {
                        session.setAttribute("alert", null);
                        vid.ComentarVideo((Integer)session.getAttribute("userid"), video_id, padre, request.getParameter("comentar"), new Date());
                    }
                }
            %>
    </head>
    <body>
        <main class="comentar-form">
            <div class="cotainer">
                <div class="row justify-content-center">
                    <div class="col-md-8">
                        <div class="card">
                            <div class="card-header">Agregue un comentario</div>
                            <div class="card-body">
                                <form action="" method="">
                                    <div class="form-group row">
                                        <div class="col-md-6">
                                            <input type="text" id="comentar" class="form-control" name="comentar" required autofocus>
                                        </div>
                                    </div>

                                    <div class="col-md-6 offset-md-4">
                                        <button type="submit" class="btn btn-primary">
                                            Comentar
                                        </button>
                                    </div>
                                    
                                    <% if (session.getAttribute("alert") != null) { %>
                                            <div class="alert alert-danger" role="alert">
                                                <%= session.getAttribute("alert")%>
                                            </div>
                                    <%}%>
                            </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            </div>

        </main>
    </body>
</html>