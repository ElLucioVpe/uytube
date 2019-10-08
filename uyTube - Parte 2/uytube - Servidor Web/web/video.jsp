<%-- 
    Document   : video
    Created on : Oct 8, 2019, 12:48:34 AM
    Author     : Luciano
--%>

<%@page import = "javax.persistence.*"%>
<%@page import = "logica.controladores.Fabrica"%>
<%@page import = "logica.controladores.IControladorUsuario"%>

<%@page import="java.util.Date"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.min.js"></script>

        <title>uyTube - Transmite tú mismo</title>
    </head>
    <body>
        <% Fabrica f = Fabrica.getInstance();
            IControladorUsuario user = f.getIControladorUsuario();
            String video_id = request.getParameter("id");
        %>
        
        <script>var video_id="<%=video_id%>";</script>
        
        <h1>Nombre del video: <span id="titulo"></span> </h1>
        
        Mañana termino esto y hago todo lo otro, pero ya casi funciona todo para tocar la parte de diseño.
        
        <%@ include file="include/footer.jsp" %>
        
            <script>
                $( document ).ready(function() {
                    // Cuando se hace click en btnRecargar llama a la funcion recargar table
                    consultarVideo(video_id);
                    console.log(video_id);
                });

                function consultarVideo(video_id) {
                    $.ajax({
                        url:"http://localhost:8080/WebApplication/api/obtenerVideos.jsp",
                        data : { id : video_id },
                        success:function(response){   
                            //console.log(response);
                            let video = jQuery.parseJSON(response);
                            $("#titulo").html(video.nombre);
                        },
                        error: function (xhr, ajaxOptions, thrownError) {
                          console.log(xhr.status);
                          console.log(thrownError);
                          $('.table tbody').html('<span style="color:red">obtenerUsuarios.jsp esta tirando error 500 cabeza. pa mi que te falto importar la libreria de json.</span>');
                        }
                    });
                }

            </script>
    </body>
</html>
