<%-- 
    Document   : consultarListaDR
    Created on : 16 oct. 2019, 20:58:48
    Author     : Esteban
--%>

<%@page import="logica.controladores.IControladorCategoria"%>
<%@page import="logica.dt.VideoDt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "logica.controladores.Fabrica" %>
<%@page import = "logica.controladores.IControladorUsuario"%>
<%@page import="logica.dt.CategoriaDt"%>
<%@page import="logica.dt.CanalDt"%>
<%@page import="logica.dt.ListaDeReproduccionDt"%>

<!DOCTYPE html>
<html>
    <%
        Fabrica f = Fabrica.getInstance();
        IControladorUsuario user = f.getIControladorUsuario();
        
        int user_id = -1;
        ListaDeReproduccionDt lista = null; 
        boolean propietario = false;
        
        if(request.getParameter("user_id") != null && request.getParameter("user_id") != "") {
            user_id = Integer.parseInt(request.getParameter("user_id"));
            if(request.getParameter("nom") != null && request.getParameter("nom") != "") {
                if(session.getAttribute("userid") != null) if(user_id == (int)session.getAttribute("userid")) propietario = true;
                lista = user.obtenerListaDt(user_id, request.getParameter("nom"));
            }
        }
    %>
    <script>
        function modificar(id_propietario, nom_lista) {
            if (confirm('¿Desea modificar la lista?')) {
                var nueva_categoria = $('#categoria').val();
                var nueva_privacidad = $('#visibilidad').val();
                $.ajax({
                    url:'http://localhost:8080/WebApplication/api/modificarLista.jsp?accion=modificar&id_p='+id_propietario+'&nom='+nom_lista+'&nCat='+nueva_categoria+'&nPri='+nueva_privacidad,
                    success: function (result) {
                        alert(result);
                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                      console.log(xhr.status);
                      console.log(thrownError);
                    }
                });
            }
        }
        
        function quitarVideo(id_propietario, nom_lista, id_video) {
            if (confirm('¿Desea eliminar este video de la lista?')) {
                $.ajax({
                    url:'http://localhost:8080/WebApplication/api/modificarLista.jsp?accion=quitar&id_p='+id_propietario+'&nom='+nom_lista+'&id_v='+id_video,
                    success: function (result) {
                        alert(result);
                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                      console.log(xhr.status);
                      console.log(thrownError);
                    }
                });
            }
        }
    </script>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.min.js"></script>
        
        <%if(lista == null) {%>
            <title>uyTube - Lista de Reproducción</title>
        <%} else {%>
            <title>uyTube - Lista de Reproducción <%=lista.getNombre()%></title>
        <%}%>
    </head>
    <body>
        <%@ include file="include/header.jsp" %>
        <form class="form-inline">
        <%if(lista == null) {%>
            <h1>ERROR, no hay información que desplegar</h1>
        <%} else {%>
            <div id="columnaInfo" class="col md-5">
                <div class="card">
                    <div class="card-body">
                        <label><h4><%=lista.getNombre()%></h4></label>
                        <hr />
                        <label><b>Categoria: &nbsp </b> <%=lista.getCategoria()%></label>
                        <hr />
                        <label><b>Visibilidad: &nbsp </b> <%if(lista.getPrivada()){%> Privada<%} else {%> Pública <%}%></label>
                        <hr />
                        <label><b>Propietario: &nbsp </b> <a href="consultarUser.jsp?id=<%=lista.getIdUsuario()%>"><%=user.obtenerNickUsuario(lista.getIdUsuario())%></a></label>
                        <%if(propietario) {%>
                        <hr /><label><b>Modificar &nbsp</b> <button class="fas fa-edit"  data-toggle="collapse" data-target="#modificarCollapse" aria-expanded="false" aria-controls="modificarCollapse"></button></label> 
                            
                        <div class="collapse" id="modificarCollapse">
                            <div class="form-group row">
                                <label for="Visibilidad" class="col-md-4 col-form-label text-md-right"> Visibilidad</label>
                                <select class="form-control" id="visibilidad" name="visibilidad">
                                    <option selected value="true"> Privada </option>
                                    <option value="false"> Pública </option>
                                </select>
                            </div>
                            <div class="form-group row">
                                <label for="selectCategoria" class="col-md-4 col-form-label text-md-right"> Categoria</label>
                                <select class="form-control" id="categoria" name="categoria">
                                    <option selected value="Ninguna"> Ninguna </option>
                                    <%  IControladorCategoria cat = f.getIControladorCategoria();
                                        List<CategoriaDt> catArray = cat.ListarCategorias();
                                        for (CategoriaDt c : catArray) {%>
                                        <option value="<%=c.getNombre()%>"><%=c.getNombre()%></option>
                                    <%}%>
                                </select>
                            </div><hr/>
                            <label><button class="btn btn-outline-success my-2 my-sm-0" type="button" style="text-align:center;" onclick="modificar('<%=lista.getIdUsuario()%>', '<%=lista.getNombre()%>')">Modificar</button></label>        
                        </div>
                        <%}%>
                    </div>
                </div>
            </div>
            <div id="columnaVideos" class="col-md-8">
                <div class="card">
                    <div class="card-body">
                    <%
                        List<VideoDt> videos = user.obtenerVideosLista(user_id, request.getParameter("nom"));
                        for(int i=0; i < videos.size(); i++) {%>
                            <div class="media">
                                <a href="video.jsp?id=<%=videos.get(i).getId()%>" class="pull-left mr-2">
                                    <%if(videos.get(i).getThumbnail() != "") {%> 
                                        <img src="<%=videos.get(i).getThumbnail()%>" class="img-thumbnail" alt="Thumbnail">
                                    <%} else {%> 
                                        <img src="img/no-thumbnail.jpg" width=120 height=90 class="img-thumbnail" alt="Thumbnail">   
                                    <%}%>
                                </a>
                                <div class="media-body">
                                    <h5 class="media-heading"><a href="video.jsp?id=<%=videos.get(i).getId()%>"><%=videos.get(i).getNombre()%></a>
                                        <%if(propietario) {%> 
                                        <button class="fas fa-times" onclick="quitarVideo('<%=lista.getIdUsuario()%>', '<%=lista.getNombre()%>', '<%=videos.get(i).getId()%>')"></button> 
                                        <%}%>
                                    </h5>
                                    <a><%=videos.get(i).getDescripcion()%></a>
                                </div>
                            </div>
                                
                    <%  }%>
                    </div>
                </div>
            </div>
        <%}%>
        </form>
        <%@ include file="include/footer.jsp" %>
    </body>
</html>
