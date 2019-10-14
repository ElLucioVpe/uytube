<%-- 
    Document   : buscador
    Created on : 13 oct. 2019, 15:27:52
    Author     : Esteban
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        String text = "", video = "", channel = "", list = "", cat = "", order = "asc"; 
        
        //Por las dudas
        if(request.getParameter("text") != null) text = request.getParameter("text");
        if(request.getParameter("video") != null) video = request.getParameter("video");
        if(request.getParameter("channel") != null) channel = request.getParameter("channel");
        if(request.getParameter("list") != null) list = request.getParameter("list");
        if(request.getParameter("cat") != null) cat = request.getParameter("cat");
        if(request.getParameter("order") != null) order = request.getParameter("order");
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.min.js"></script>

        <title>uyTube - Transmite tú mismo</title>
    </head>
    <body>
        <%@ include file="include/header.jsp" %>  
        
        <div class="row">
            <%-- Aca van todos los datos de videos, listas y canales --%>
            <div id="data" class="col-xs-12 col-md-8">
            <%-- Formato
              <div id="nombre+id" class="media">
                <a href="#" class="pull-left"><img src="" class="" alt=""></a> 
                
                <div class="media-body">
                  <h4 class="media-heading"><a href="">Titulo</a></h4><a>Descripcion</a>
                </div>
              </div>
            --%>
            </div>
        </div>
        
        <%@ include file="include/footer.jsp" %>
        
        <script>
            $( document ).ready(function() {
                cargarDatos('<%=text%>', '<%=video%>', '<%=channel%>', '<%=list%>', '<%=cat%>');
                //ordenarDatos();
            });
            
            function cargarDatos(text, video, channel, list, cat) {
                //Arrays de datos
                //Si la categoria esta vacia busca en general
                listarVideos(cat, function(val) { 
                    if(video === "true") {
                        var datos = [];
                        var videos = jQuery.parseJSON(val);
                        
                        if(text === null || text === "") videos.forEach(function(v){datos.push(v);});
                        else {
                            videos.forEach(function(v){
                                if(v.nombre.includes(text)) datos.push(v);
                            });
                        }
                        crearHTML(JSON.stringify(datos));
                    }
                });
                
                listarUsuarios(cat, function(val) { 
                    if(channel === "true") {
                        var datos = [];
                        var usuarios = jQuery.parseJSON(val);
                        
                        if(text === null || text === "") usuarios.forEach(function(u){datos.push(u);});
                        else {
                            usuarios.forEach(function(u){
                                if(u.canal.nombre.includes(text)) datos.push(u);
                            });
                        }
                        crearHTML(JSON.stringify(datos));
                    }
                    
                });
                
                listarListasDR(cat, function(val) { 
                    if(list === "true") {
                        var datos = [];
                        var listas = jQuery.parseJSON(val);
                        
                        if(text === null || text === "") listas.forEach(function(l){datos.push(l);});
                        else {
                            listas.forEach(function(l){
                                if(l.nombre.includes(text)) datos.push(l);
                            });
                        }
                        crearHTML(JSON.stringify(datos));
                    }
                    
                });
            }
            
            function crearHTML(data) {
                var divDatos = document.getElementById("data");
                var datos = jQuery.parseJSON(data);
                
                var html = "";
                for (var i = 0; i < datos.length; i++) {
                    
                    if(datos[i].jsonType === "video") {
                        html += '<div id="'+datos[i].nombre+datos[i].id+'" class="media">';
                        html += '<a href="video.jsp?id='+datos[i].id+'" class="pull-left mr-2">';
                        if(datos[i].thumbnail !== "") html += '<img src="'+datos[i].thumbnail+'" class="img-thumbnail" alt="Thumbnail"></a>';
                        else html += '<img src="img/no-thumbnail.jpg" class="img-thumbnail" alt="Thumbnail"></a>';
                        
                        html += '<div class="media-body"><h5 class="media-heading"><a href="video.jsp?id='+datos[i].id+'">'+datos[i].nombre+'</a></h5><a>'+datos[i].descripcion+'</a>';
                        html += '<p class="text-secondary"> '+datos[i].user+'</p>';
                    }
                    
                    if(datos[i].jsonType === "usuario") {
                        html += '<div id="'+datos[i].canal.nombre+datos[i].id+'" class="media">';
                        html += '<a href="consultarUser.jsp?id='+datos[i].id+'" class="pull-left mr-2">';
                        if(datos[i].imagen !== "") html += '<img src="http://localhost:8080/images/'+datos[i].imagen+'" width=90 height=90 class="rounded-circle" alt="User Picture"></a>';
                        else html += '<img src="img/user.png" class="rounded-circle" width=90 height=90 alt="User Picture"></a>';
                        
                        html += '<div class="media-body"><h5 class="media-heading"><a href="consultarUser.jsp?id='+datos[i].id+'">'+datos[i].canal.nombre+'</a></h5><a>'+datos[i].canal.descripcion+'</a>';
                    }
                    
                    if(datos[i].jsonType === "lista") {
                        html += '<div id="'+datos[i].nombre+datos[i].id+'" class="media">';
                        html += '<a href="consultarLista.jsp?user_id='+datos[i].user_id+'&nom='+datos[i].nombre+'" class="pull-left mr-2">';
                        html += '<img src="img/playlist.png" class="rounded" width=120 height=90 alt="List Picture"></a>';
                        
                        html += '<div class="media-body"><h5 class="media-heading"><a href="consultarLista.jsp?user_id='+datos[i].user_id+'&nom='+datos[i].nombre+'">'+datos[i].nombre+'</a></h5><a> Categoria: '+datos[i].categoria+'</a>';
                        html += '<p class="text-secondary"> '+datos[i].user_nick+'</p>';
                    }
                    
                    html += "</div></div>";
                }
                
                divDatos.innerHTML += html;
            }
            
            function ordenarDatos() {
                var divData = $('#data');
                var listitems = divData.children('div').get();
                
                listitems.sort(function(a, b) {
                   return $(a).id.toUpperCase().localeCompare($(b).id.toUpperCase());
                });

                $.each(listitems, function(index, item) {
                   divData.append(item); 
                });
                
                if('<%=order%>' === "desc") listitems = listitems.reverse();
                console.log(listitems);
            }
            
            function listarVideos(cat, callback) {
                $.ajax({
                    url:"http://localhost:8080/WebApplication/api/obtenerVideos.jsp?cat="+cat,
                    success: callback,
                    error: function (xhr, ajaxOptions, thrownError) {
                      console.log(xhr.status);
                      console.log(thrownError);
                    }
                });
            }
            
            function listarListasDR(cat, callback) {
                $.ajax({
                    url:'http://localhost:8080/WebApplication/api/obtenerListas.jsp?cat='+cat,
                    success: callback,
                    error: function (xhr, ajaxOptions, thrownError) {
                      console.log(xhr.status);
                      console.log(thrownError);
                    }
                });
            }
            
            function listarUsuarios(cat, callback) {
                $.ajax({
                    url:'http://localhost:8080/WebApplication/api/obtenerUsuarios.jsp?cat='+cat,
                    success: callback,
                    error: function (xhr, ajaxOptions, thrownError) {
                      console.log(xhr.status);
                      console.log(thrownError);
                    }
                });
            }
            
        </script>
    </body>
</html>