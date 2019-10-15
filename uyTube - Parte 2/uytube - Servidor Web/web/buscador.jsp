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
        <script>
            $( document ).ready(function() {
                cargarDatos('<%=cat%>', '<%=text%>');
                
                $('#tipos-de-orden').change(function() {
                    var v = $(this).find(':selected').val();
                    $('input[name="hidden-order"]').val(v);
                });
                
                //$('.mdb-select').materialSelect();
                
                //Seteo los valores de filtros de acuerdo a las variables
                //$('#toggle-videos').prop('checked').val(false);
                
                //$('#toggle-listas').prop('checked').val(false);
                
                //$('#toggle-canales').prop('checked').val(false);
                
                //$('input[name="hidden-order"]').val('order');
            });

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
            
            function ordenarDatos(datos) {
                var datosAux = [];
                var retorno = "";
                
                datosAux = jQuery.parseJSON(datos);
                if('<%=order%>' !== "date") {
                    datosAux.sort(function(a, b) {
                       if(a.jsonType === "usuario") {
                           if(b.jsonType === "usuario") return a.canal.nombre.localeCompare(b.canal.nombre);
                           else return a.canal.nombre.localeCompare(b.nombre);
                       } else { 
                           if(b.jsonType === "usuario") return a.nombre.localeCompare(b.canal.nombre);
                           else return a.nombre.localeCompare(b.nombre);
                       }
                    });
                } else {
                    datosAux.sort(function(a, b) {
                       /*if(a.jsonType === "usuario") {
                           if(b.jsonType === "usuario") return dates.compare(a.canal.ultimaFecha, b.canal.ultimaFecha);
                           else return dates.compare(a.canal.ultimaFecha, b.fecha);
                       } else { 
                           if(b.jsonType === "usuario") return dates.compare(a.fecha, b.canal.ultimaFecha);
                           else return dates.compare(a.fecha, b.fecha);
                       }*/
                       var dateA;
                       var dateB;
                       if(a.jsonType === "usuario") dateA = new Date(a.canal.ultimaFecha);
                       else dateA = new Date(a.fecha);
                       
                       if(b.jsonType === "usuario") dateB = new Date(b.canal.ultimaFecha);
                       else dateB = new Date(b.fecha);
                       
                       return dateA < dateB;
                    });
                }
                
                //Muestro la cantidad de resultados
                document.getElementById("cantidad-resultados").innerHTML = datosAux.length + " resultados";
                
                retorno = JSON.stringify(datosAux);
                return retorno;
            }
            
            function cargarDatos(cat, text) {
                var listarVideos = $.ajax({
                    url:"http://localhost:8080/WebApplication/api/obtenerVideos.jsp?cat="+cat,
                    success: function (result) {},
                    error: function (xhr, ajaxOptions, thrownError) {
                      console.log(xhr.status);
                      console.log(thrownError);
                    }
                });
                
                var listarListasDR = $.ajax({
                    url:'http://localhost:8080/WebApplication/api/obtenerListas.jsp?cat='+cat,
                    success: function (result) {},
                    error: function (xhr, ajaxOptions, thrownError) {
                      console.log(xhr.status);
                      console.log(thrownError);
                    }
                });
                
                var listarUsuarios = $.ajax({
                    url:'http://localhost:8080/WebApplication/api/obtenerUsuarios.jsp?cat='+cat,
                    success: function (result) {},
                    error: function (xhr, ajaxOptions, thrownError) {
                      console.log(xhr.status);
                      console.log(thrownError);
                    }
                });
                
                $.when(listarVideos, listarListasDR, listarUsuarios).done(function(dv, dl, du) {
                    var datos = [];
                    var videos = jQuery.parseJSON(dv[0]);
                    var listas = jQuery.parseJSON(dl[0]);
                    var usuarios = jQuery.parseJSON(du[0]);
                    
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

                    if('<%=channel%>' === "true") {
                        if(text === null || text === "") usuarios.forEach(function(u){datos.push(u);});
                        else {
                            usuarios.forEach(function(u){
                                if(u.canal.nombre.includes(text)) datos.push(u);
                            });
                        }
                    }
                    
                    datos = ordenarDatos(JSON.stringify(datos));
                    crearHTML(datos);
                });
            }
            
            function filtrarDatos() {
                var videos = ($('#toggle-videos').prop('checked'));
                var listas = ($('#toggle-listas').prop('checked'));
                var canales = ($('#toggle-canales').prop('checked'));
                var orden = ($('input[name="hidden-order"]').val());
                
                redirectURL = "http://localhost:8080/WebApplication/buscador.jsp?";
                redirectURL += "video="+videos;
                redirectURL += "&list="+listas;
                redirectURL += "&channel="+canales;
                redirectURL += "&order="+orden;
                redirectURL += "&text="+'<%=text%>';
                
                console.log(redirectURL);
                window.location.href = redirectURL;
            }
        </script>
        <input type="hidden" value="asc" name="hidden-order">
        <div class="row">
            <nav class="navbar navbar-expand-lg navbar-light" style="background-color: #7EA16B; display:inline-block;">
              <a class="navbar-brand" href="#"></a>
              <ul class="navbar-nav mr-auto">
                <li id="cantidad-resultados" class="nav-item px-5"> X resultados </li>
                <li class="nav-item px-2"></li>
                <%-- Filtros de tipos --%>
                <li class="nav-item px-1"> Videos <input id="toggle-videos" type="checkbox" data-toggle="toggle" data-size="mini"></li>
                <li class="nav-item px-1"> Listas <input id="toggle-listas" type="checkbox" data-toggle="toggle" data-size="mini"></li>
                <li class="nav-item px-1"> Canales <input id="toggle-canales" type="checkbox" data-toggle="toggle" data-size="mini"></li>
                <li class="nav-item px-2"></li>
                <%-- Filtros de Orden --%>
                <li class="nav-item"> Ordenar: </li>
                  <select id="tipos-de-orden" class="mdb-select md-form colorful-select dropdown-dark">
                    <option selected>Seleccione una opción</option>
                    <option value="asc">Alfabéticamente (A-Z a-z)</option>
                    <option value="date">Fecha de publicación (descendente)</option>
                  </select>
                <li class="nav-item px-2"><button id="filtrar-btn" class="btn btn-outline-dark my-2 my-sm-0" onclick="filtrarDatos()"> Filtrar </button></li>
              </ul>
            </nav>
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
    </body>
</html>