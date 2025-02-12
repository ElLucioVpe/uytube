<%-- 
    Document   : index
    Created on : Sep 27, 2019, 10:32:17 PM
    Author     : pagol
--%>

<%@page import="java.util.Date"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.min.js"></script>

        <!-- Font Awesome -->
        <link href="css/fontawesome.min.css" rel="stylesheet">
        <script defer src="js/solid.js"></script>
        <script defer src="js/fontawesome.js"></script>

        <title>uyTube - Transmite tú mismo</title>
    </head>
    <body>
        <%@ include file="include/header.jsp" %>
        <table class="table" id="tblVideos">
          <thead>
            <tr>
              <th class="thumbnail"></th>
              <th class="titulo" scope="col">Título</th>
              <th class="descripcion">Descripcion</th>
              <th class="canal">Canal</th>
            </tr>
          </thead>
          <tbody>
          </tbody>
        </table>
        
        <%@ include file="include/footer.jsp" %>
        
            <script>
                $( document ).ready(function() {
                    // Cuando se hace click en btnRecargar llama a la funcion recargar table
                    listarVideos();
                });

                function listarVideos() {
                    $.ajax({
                        url:"<%=request.getContextPath()%>/api/obtenerVideos.jsp",
                        success:function(response){   
                            //console.log(response);
                            let videos = jQuery.parseJSON(response);
                            let html = "";
                            if(jQuery.isEmptyObject(videos)) {
                                $('.table tbody').html('<span style="color:red">obtenerUsuarios.jsp esta retornando vacio. pa mi que tenes mal la base de datos.</span>');
                            } else {
                                for (let i = 0; i < videos.length; i++) {
                                    html += "<tr>";
                                    if(videos[i].thumbnail !== "") {
                                        html += '<th><img src="'+videos[i].thumbnail+'" alt="Thumbnail"></th>';
                                    } else {
                                        html += '<th><img src="img/no-thumbnail.jpg" width=120 height=90 alt="Thumbnail"></th>';
                                    }
                                    html += '<td  scope="row"><a href="video.jsp?id='+ videos[i].id +'">'+videos[i].nombre+'</a></td>';
                                    html += '<td>'+videos[i].descripcion+'</td>';
                                    html += '<td>'+videos[i].user+'</td>';
                                    html += "</tr>";
                                }
                                $('.table tbody').html(html);
                            }
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
