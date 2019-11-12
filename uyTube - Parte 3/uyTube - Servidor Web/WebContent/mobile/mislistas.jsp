<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mis listas - uyTube</title>
<head>
		<% 
			String path = request.getContextPath(); 
			int userid = 1;
			if(session.getAttribute("userid") != null) {
				userid = (int) session.getAttribute("userid");
			} else {
				response.sendRedirect("login.jsp");
			}
		%>
    	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

		<%@ include file="include/navbar.jsp" %>
		<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
		<script src="../js/bootstrap.min.js"></script>
		<script src="../js/jquery.min.js"></script>
		
		<!-- Font Awesome -->
		<link href="../css/fontawesome.min.css" rel="stylesheet">
		<script defer src="../js/solid.js"></script>
		<script defer src="../js/fontawesome.js"></script>
		
		<%-- Popper --%>
		<script src="../js/popper.min.js"></script>
        <title>uyTube - Transmite tú mismo</title>
    </head>
    <body>
        <input type="hidden" value="asc" name="hidden-order">
        <script>
            $( document ).ready(function() {
                cargarListas();
            });
            
            function cargarListas() {
                $.ajax({
                    url:'<%=path%>/api/obtenerListas.jsp?user_id='+<%=userid%>,
                    success: function (result) {
                    	var divDatos = document.getElementById("data");
                        var datos = jQuery.parseJSON(result);
                        
                        var html = "";
                        for (var i = 0; i < datos.length; i++) {
                        	var icon = "fas fa-lock";
                        	var badge = "badge badge-success badge-pill";
                        	if(datos[i].privacidad === false) {
                        		icon = "fas fa-globe";
                        		badge = "badge badge-primary badge-pill"
                        	}
                        	
                        	html += '<li class="list-group-item d-flex justify-content-between align-items-center">';
                            html += '<a href="m.consultarLista.jsp?user_id='+datos[i].user_id+'&nom='+datos[i].nombre+'" style="color: black">';
                            html += datos[i].nombre+'</a><span class="'+badge+'"><i class="'+icon+'"></i></span></li>';
                        }
                        
                        divDatos.innerHTML += html;
                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                      console.log(xhr.status);
                      console.log(thrownError);
                    }
                });
            }
        </script>
        <div class="container">
        	<h5>Listas de reproducción</h5>
        	<ul id="data" class="list-group list-group-flush">
        		<%-- Aca van todos los datos de videos y listas --%>
			</ul>
        </div>
    </body>
</html>
