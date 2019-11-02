<%-- 
    Document   : crearListaDR
    Created on : 16 oct. 2019, 18:02:43
    Author     : Esteban
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "logica.controladores.Fabrica" %>
<%@page import = "logica.controladores.IControladorUsuario"%>
<%@page import="logica.controladores.IControladorCategoria"%>
<%@page import="java.util.List"%>
<%@page import="logica.dt.CategoriaDt"%>
<%@page import="logica.dt.CanalDt"%>
<%@page import="logica.dt.ListaDeReproduccionDt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.min.js"></script>
        
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="/resources/demos/style.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
            
        <%
            Fabrica f = Fabrica.getInstance();
            IControladorUsuario user = f.getIControladorUsuario();
            IControladorCategoria cat = f.getIControladorCategoria();
            
            session.setAttribute("errorAltaLista", null);
            if(session.getAttribute("userid") != null && session.getAttribute("user_dt") != null) {
                if(request.getParameter("nombre") != null && request.getParameter("nombre") != "") {
                    int user_id = (int)session.getAttribute("userid");
                    System.out.println(user_id + "----" + session.getAttribute("userid"));
                    ListaDeReproduccionDt existe = user.obtenerListaDt(user_id, request.getParameter("nombre"));
                    if(existe == null) {
                        if(request.getParameter("visibilidad") != null && request.getParameter("visibilidad") != "") {
                            boolean privada = true;
                            if (request.getParameter("visibilidad").equals("publico")) privada = false;
                            CanalDt c = user.obtenerCanalDt(user_id);
                            if(c.getPrivacidad() && !privada) session.setAttribute("errorAltaLista", "visibilidad");
                            else {
                                if(request.getParameter("categoria") != null && request.getParameter("categoria") != "") {
                                    user.AltaListaDeReproduccionParticular(request.getParameter("nombre"), (int)session.getAttribute("userid"), privada, request.getParameter("categoria"));
                                    //Testeo si se creo, osea si se mando alguna el user por que si aca fallo es que altero el url a posta
                                    ListaDeReproduccionDt creada = user.obtenerListaDt(user_id, request.getParameter("nombre"));
                                    if(creada == null) session.setAttribute("errorAltaLista", "tramposo");
                                    else session.setAttribute("errorAltaLista", "exito");
                                }
                            }
                        }
                    } else session.setAttribute("errorAltaLista", "nombre");
                }
            } else session.setAttribute("errorAltaLista", "userid");
        %>
        <title>Crear Lista De Reproducción</title>
            
    </head>
    <body>
        <%@include file="include/header.jsp" %>
        <main class="lista-form">
            <div class="cotainer">
                <div class="row justify-content-center">
                    <div class="col-md-8">
                        <h1>uyTube - Lista de Reproducción</h1>
                    </div>
                </div>
                <div class="row justify-content-center">
                    <div class="col-md-8">
                        <div class="card">
                            <div class="card-header">Nueva Lista</div>
                            <div class="card-body">
                                <form action="">
                                <div class="form-group row">
                                    <label for="nombre" class="col-md-4 col-form-label text-md-right">Nombre</label>
                                    <div class="col-md-6">
                                        <input type="text" id="nombre" class="form-control" name="nombre" required>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="Visibilidad" class="col-md-4 col-form-label text-md-right">Visibilidad</label>
                                    <div class="col-md-6">
                                        <div class="form-check">
                                        <input class="form-check-input" type="radio" name="visibilidad" value="privado" checked>
                                        <label class="form-check-label" for="visibilidad1"> Privado </label>
                                        </div>
                                      <div class="form-check">
                                        <input class="form-check-input" type="radio" name="visibilidad" value="publico">
                                        <label class="form-check-label" for="visibilidad2"> Publico </label>
                                      </div>
                                    </div>
                                </div>
                                
                                <div class="form-group row">
                                    <label for="selectCategoria" class="col-md-4 col-form-label text-md-right">Categoria</label>
                                    <div class="col-md-6">
                                        <select class="form-control" id="categoria" name="categoria">
                                            <option selected value="Ninguna"> Ninguna </option>
                                            <%  List<CategoriaDt> catArray = cat.ListarCategorias();
                                                for (CategoriaDt c : catArray) {%>
                                                <option value="<%=c.getNombre()%>"><%=c.getNombre()%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-6 offset-md-4">
                                    <button type="submit" class="btn btn-outline-success my-2 my-sm-0">Crear</button>
                                </div>
                                        
                                <% if (session.getAttribute("errorAltaLista")=="userid") { %>
                                        <div class="alert alert-danger" role="alert">
                                            Por favor inicie sesión.
                                        </div>
                                <%}%>
                                <% if (session.getAttribute("errorAltaLista")=="nombre") { %>
                                        <div class="alert alert-danger" role="alert">
                                            Usted ya tiene una lista con este nombre.
                                        </div>
                                <%}%>
                                <% if (session.getAttribute("errorAltaLista")=="visibilidad") {%>
                                        <div class="alert alert-danger" role="alert">
                                            La lista no puede ser pública ya que el canal es privado.
                                        </div>
                                <%}%> 
                                <% if (session.getAttribute("errorAltaLista")=="tramposo") {%>
                                        <div class="alert alert-danger" role="alert">
                                            Por favor deje de manipular el url para intentar romper la pagina.
                                        </div>
                                <%}%>
                                <% if (session.getAttribute("errorAltaLista")=="exito") {%>
                                        <script>alert("La lista se creo correctamente");</script>
                                <%}%> 
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <%@include file="include/footer.jsp"%>
    </body>
</html>
