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
        String path = request.getContextPath();
        
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
        
        <!-- Font Awesome -->
        <link href="css/fontawesome.min.css" rel="stylesheet">
        <script defer src="js/solid.js"></script>
        <script defer src="js/fontawesome.js"></script>
        
        <title>uyTube - Transmite tú mismo</title>
    </head>
    <body>
        <%@ include file="include/header.jsp" %>
        <input type="hidden" value="asc" name="hidden-order">
        <link rel="stylesheet" href="css/bootstrap4-toggle.min.css">
        <script src="js/bootstrap4-toggle.min.js"></script>
        <script>
            $( document ).ready(function() {
                cargarDatos('<%=cat%>', '<%=text%>');
                
                //Seteo los valores de filtros de acuerdo a las variables
                if('<%=video%>' === "true") $('#toggle-videos').bootstrapToggle('on');
                else $('#toggle-videos').bootstrapToggle('off');
                
                if('<%=list%>' === "true") $('#toggle-listas').bootstrapToggle('on');
                else $('#toggle-listas').bootstrapToggle('off');
                
                if('<%=channel%>' === "true") $('#toggle-canales').bootstrapToggle('on');
                else $('#toggle-canales').bootstrapToggle('off');

                if('<%=order%>' === "date") $('#tipos-de-orden').val('<%=order%>');
                else $('#tipos-de-orden').val('asc');
                
                $('#tipos-de-orden').change(function() {
                    var v = $(this).val();
                    $('input[name="hidden-order"]').val(v);
                });
            });
 
            function crearHTML(data) {
                var divDatos = document.getElementById("data");
                var datos = jQuery.parseJSON(data);
                var resultados = 0;
                
                var html = "";
                for (var i = 0; i < datos.length; i++) {
                    
                    if(datos[i].jsonType === "video") {
                        if(datos[i].privacidad === false) {
                            html += '<div id="'+datos[i].nombre+datos[i].id+'" class="media">';
                            html += '<a href="video.jsp?id='+datos[i].id+'" class="pull-left mr-2">';
                            if(datos[i].thumbnail !== "") html += '<img src="'+datos[i].thumbnail+'" class="img-thumbnail" alt="Thumbnail"></a>';
                            else html += '<img src="img/no-thumbnail.jpg" width=120 height=90 class="img-thumbnail" alt="Thumbnail"></a>';
                            
                            html += '<div class="media-body"><h5 class="media-heading"><a href="video.jsp?id='+datos[i].id+'">'+datos[i].nombre+'</a></h5><a>'+datos[i].descripcion+'</a>';
                            html += '<p class="text-secondary"> '+datos[i].user+'</p></div></div>';
                            resultados++;
                        }
                    }
                    
                    if(datos[i].jsonType === "usuario") {
                        if(datos[i].canal.privacidad === false) {
                            html += '<div id="'+datos[i].canal.nombre+datos[i].id+'" class="media">';
                            html += '<a href="consultarUser.jsp?id='+datos[i].id+'" class="pull-left mr-2">';
                            if(datos[i].imagen !== "") html += '<img src="<%=path%>/images/'+datos[i].imagen+'" width=90 height=90 class="rounded-circle" alt="User Picture"></a>';
                            else html += '<img src="img/user.png" class="rounded-circle" width=90 height=90 alt="User Picture"></a>';

                            html += '<div class="media-body"><h5 class="media-heading"><a href="consultarUser.jsp?id='+datos[i].id+'">'+datos[i].canal.nombre+'</a></h5><a>'+datos[i].canal.descripcion+'</a></div></div>';
                            resultados++;
                        }
                    }
                    
                    if(datos[i].jsonType === "lista") {
                        if(datos[i].privacidad === false) { 
                            html += '<div id="'+datos[i].nombre+datos[i].id+'" class="media">';
                            html += '<a href="consultarLista.jsp?user_id='+datos[i].user_id+'&nom='+datos[i].nombre+'" class="pull-left mr-2">';
                            html += '<img src="img/playlist.png" class="rounded" width=120 height=90 alt="List Picture"></a>';

                            html += '<div class="media-body"><h5 class="media-heading"><a href="consultarListaDR.jsp?user_id='+datos[i].user_id+'&nom='+datos[i].nombre+'">'+datos[i].nombre+'</a></h5><a> Categoria: '+datos[i].categoria+'</a>';
                            html += '<p class="text-secondary"> '+datos[i].user_nick+'</p></div></div>';
                            resultados++;
                        }
                    }
                }
                
                divDatos.innerHTML += html;
                
                //Muestro la cantidad de resultados
                document.getElementById("cantidad-resultados").innerHTML = resultados + " resultados";
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
                       var dateA = new Date(a.fechaPublicacion);
                       var dateB = new Date(b.fechaPublicacion);
    
                       return dateA>dateB ? -1 : dateA<dateB ? 1 : 0;
                    });
                }
                
                retorno = JSON.stringify(datosAux);
                return retorno;
            }
            
            function cargarDatos(cat, text) {console.log(cat);
                var listarVideos = $.ajax({
                    url:"<%=path%>/api/obtenerVideos.jsp?cat="+cat,
                    success: function (result) {},
                    error: function (xhr, ajaxOptions, thrownError) {
                      console.log(xhr.status);
                      console.log(thrownError);
                    }
                });
                
                var listarListasDR = $.ajax({
                    url:'<%=path%>/api/obtenerListas.jsp?cat='+cat,
                    success: function (result) {},
                    error: function (xhr, ajaxOptions, thrownError) {
                      console.log(xhr.status);
                      console.log(thrownError);
                    }
                });
                
                var listarUsuarios = $.ajax({
                    url:'<%=path%>/api/obtenerUsuarios.jsp?cat='+cat,
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
                
                redirectURL = "<%=path%>/buscador.jsp?";
                redirectURL += "video="+videos;
                redirectURL += "&list="+listas;
                redirectURL += "&channel="+canales;
                redirectURL += "&order="+orden;
                redirectURL += "&text="+'<%=text%>';
                
                console.log(redirectURL);
                window.location.href = redirectURL;
            }
        </script>
        <nav class="navbar navbar-expand-md navbar-light" style="background-color: #7EA16B;">
            <a id="cantidad-resultados" class="navbar-brand mx-auto" href="#">0 Resultados</a>
            <div class="navbar-collapse collapse w-100">
                <ul class="navbar-nav ml-auto">
                    <%-- Filtros de tipos --%>
                    <li class="nav-item px-2"> Videos
                      <input id="toggle-videos" type="checkbox" data-toggle="toggle" data-onstyle="dark" data-on="Si" data-off="No">
                    </li>
                    <li class="nav-item px-2"> Listas
                      <input id="toggle-listas" type="checkbox" data-toggle="toggle" data-onstyle="dark" data-on="Si" data-off="No">
                    </li>
                    <li class="nav-item px-2"> Canales
                      <input id="toggle-canales" type="checkbox" data-toggle="toggle" data-onstyle="dark" data-on="Si" data-off="No">
                    </li>
                </ul>
            </div>
            <div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item"> Ordenar:
                        <select id="tipos-de-orden" class="form-control">
                            <option selected="">Seleccione una opción</option>
                            <option value="asc">Alfabéticamente (A-Z a-z)</option>
                            <option value="date">Fecha de publicación (descendente)</option>
                        </select>
                    </li>
                    <li class="nav-item px-2"><button id="filtrar-btn" class="btn btn-outline-dark my-2 my-sm-0" onclick="filtrarDatos()"> Filtrar </button></li>
                </ul>
            </div>
        </nav>
        <div class="container-fullwidth">
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