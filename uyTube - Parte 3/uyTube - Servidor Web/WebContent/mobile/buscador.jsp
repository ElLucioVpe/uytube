
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	
    <%
        String text = "", video = "", list = "", order = "asc"; 
        String path = request.getContextPath();
        
        //Por las dudas
        if(request.getParameter("text") != null) text = request.getParameter("text");
        if(request.getParameter("video") != null) video = request.getParameter("video");
        if(request.getParameter("list") != null) list = request.getParameter("list");
        if(request.getParameter("order") != null) order = request.getParameter("order");
    %>
    <head>
    	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	    <meta name="description" content="">
	    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
	    <meta name="generator" content="Jekyll v3.8.5">
	    
	  
	    
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
                cargarDatos('<%=text%>');
            });
 
            function buscar() {
                var contenido = $('#search-text').val();
                if(contenido !== null) window.location.replace("<%=header_path%>/mobile/buscador.jsp?video=true&list=true&text="+contenido);
                else window.location.replace("<%=header_path%>/mobile/buscador.jsp?video=true&list=true");
            }
            
            function crearHTML(data) {
                var divDatos = document.getElementById("data");
                var datos = jQuery.parseJSON(data);
                var resultados = 0;
                
                var html = "";
                for (var i = 0; i < datos.length; i++) {
                	
                    if(datos[i].jsonType === "video") {
                        if(datos[i].privacidad === false) {
                            html += '<div id="'+datos[i].nombre+datos[i].id+'" class="col-md-4">';
                            html += '<a href="m.video.jsp?id='+datos[i].id+'">';
                            if(datos[i].thumbnail !== "") html += '<img src="'+datos[i].thumbnail+'" class="card-img-top" alt="Thumbnail"></a>';
                            else html += '<img src="../img/no-thumbnail.jpg" class="card-img-top" width="100%" height="225" alt="Thumbnail">';
                            
                            html += '<h5><a href="m.video.jsp?id='+datos[i].id+'" style="color: black">'+datos[i].nombre+'</a></h5></div>';
                            resultados++;
                        }
                    }
                    
                    if(datos[i].jsonType === "lista") {
                        if(datos[i].privacidad === false) { 
                            html += '<div id="'+datos[i].nombre+datos[i].id+'" class="col-md-4">';
                            html += '<a href="m.consultarLista.jsp?user_id='+datos[i].user_id+'&nom='+datos[i].nombre+'">';
                            html += '<img src="../img/playlist.png" class="card-img-top" width="100%" height="225" alt="List Picture"></a>';

                            html += '<h5><a href="m.consultarLista.jsp?user_id='+datos[i].user_id+'&nom='+datos[i].nombre+'" style="color: black">'+datos[i].nombre+'</a></div>';
                            resultados++;
                        }
                    }
                }
                
                divDatos.innerHTML += html;
                
                //Muestro la cantidad de resultados
                var resultadoshtml = resultados + " resultados";
                var text = '<%=text%>';
                if(text !== "") resultadoshtml+=' para: "'+text+'"';
                document.getElementById("cantidad-resultados").innerHTML = resultadoshtml;
            }
            
            function ordenarDatos(datos) {
                var datosAux = [];
                var retorno = "";
                
                datosAux = jQuery.parseJSON(datos);
                if('<%=order%>' !== "date") {
                    datosAux.sort(function(a, b) {
                       return a.nombre.localeCompare(b.nombre);
                    });
                } else {
                    datosAux.sort(function(a, b) {
                       var dateA = new Date(a.fechaPublicacion);
                       var dateB = new Date(b.fechaPublicacion);
    
                       return dateA>dateB ? -1 : dateA<dateB ? 1 : 0;
                    });
                }
                
                retorno = JSON.stringify(datosAux);
                return retorno;
            }
            
            function cargarDatos(text) {
                var listarVideos = $.ajax({
                    url:"<%=path%>/api/obtenerVideos.jsp",
                    success: function (result) {},
                    error: function (xhr, ajaxOptions, thrownError) {
                      console.log(xhr.status);
                      console.log(thrownError);
                    }
                });
                
                var listarListasDR = $.ajax({
                    url:'<%=path%>/api/obtenerListas.jsp',
                    success: function (result) {},
                    error: function (xhr, ajaxOptions, thrownError) {
                      console.log(xhr.status);
                      console.log(thrownError);
                    }
                });
                
                $.when(listarVideos, listarListasDR).done(function(dv, dl) {
                    var datos = [];
                    var videos = jQuery.parseJSON(dv[0]);
                    var listas = jQuery.parseJSON(dl[0]);
                    
                    if('<%=video%>' === "true") {
                        if(text === null || text === "") videos.forEach(function(v){datos.push(v);});
                        else {
                            videos.forEach(function(v){
                                if(v.nombre.includes(text)) datos.push(v);
                            });
                        }
                    }
                    
                    if('<%=list%>' === "true") {
                        if(text === null || text === "") listas.forEach(function(l){datos.push(l);});
                        else {
                            listas.forEach(function(l){
                                if(l.nombre.includes(text)) datos.push(l);
                            });
                        }
                    }
                    
                    datos = ordenarDatos(JSON.stringify(datos));
                    crearHTML(datos);
                });
            }
        </script>
        <div class="container-fullwidth">
        	<!-- Buscador -->
	        <nav class="navbar navbar-expand-md navbar-light" style="background-color: #7EA16B;">
		        <div class="navbar-brand mx-auto">
				      <form class="navbar-form" action="javascript:buscar()">
				         <input id="search-text" type="text" class="form-control" placeholder="Buscar...">
				      </form>
			    </div>
	        </nav>
	        <!-- Resultados -->
	        <nav class="navbar navbar-expand-md navbar-light" >
	        	<p id="cantidad-resultados" class="navbar-brand mx-auto">0 Resultados</p>
	       	</nav>
        </div>
        <div class="container">
        	<div id="data" class="row">
					<%-- Aca van todos los datos de videos y listas --%>
	        </div>
        </div>
    </body>
</html>