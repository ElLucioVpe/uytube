<%-- 
    Document   : altaUser
    Created on : Sep 28, 2019, 5:28:28 PM
    Author     : pagol
--%>
<%@page import="logica.webservices.CategoriaDt"%>
<%@page import="logica.webservices.Categoria"%>
<%@page import="javax.servlet.annotation.MultipartConfig"%>
<%@page import="javax.servlet.annotation.WebServlet"%>
<%@page import="java.io.InputStream"%>
<%@page import="org.apache.tomcat.util.http.fileupload.FileItem"%>
<%@page import="org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory"%>
<%@page import="java.io.File"%>
<%@page import="java.nio.file.Paths"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html"%>

<%@page import = "logica.webservices.WScontroladorCategoriaImplService"%>
<%@page import = "logica.webservices.WScontroladorCategoria"%>

<%@ page import = "java.util.*, javax.servlet.*" %>
<%@ page import = "javax.servlet.http.*" %>
<%@ page import = "org.apache.commons.fileupload.*" %>
<%@ page import = "org.apache.commons.fileupload.disk.*" %>
<%@ page import = "org.apache.commons.fileupload.servlet.*" %>
<%@ page import = "org.apache.commons.io.output.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.min.js"></script>
        
          <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
          <link rel="stylesheet" href="/resources/demos/style.css">
          <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
           <%@include file="include/header.jsp" %>
          <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  
          <script>
                $( function() {
                 $( "#datepicker" ).datepicker();
                } );

                $( document ).ready(function() {
                    /*$('#user').on('input',function(e){
                        console.log($('#user').val());
                        verificarUser($('#user').val());
                    });*/
                    $( '#user' ).keyup(function() {
                        console.log($('#user').val());
                        verificarUser($('#user').val());
                    });
                });

                function verificarUser(user) {
                    var userExiste = false;
                    if(user !== "" && user !== null) {
                        $.ajax({
                        url:"http://localhost:8080/UyTube/api/obtenerUsuarios.jsp",
                        success:function(response){   
                            let usuarios = jQuery.parseJSON(response);
                            console.log(usuarios);
                            for(i = 0; i < usuarios.length; i++) {
                                if(usuarios[i].nickname === user) {
                                    userExiste = true;
                                }
                            }
                            if(userExiste) {
                                $('#user').removeClass("is-valid");
                                $('#user').addClass("is-invalid");
                                $('#user-validacion').removeClass("valid-feedback");
                                $('#user-validacion').addClass("invalid-feedback");
                                $('#user-validacion').html("<span>"+user+" ya existe. Por favor, elige otro.</span>");
                            } else {
                                $('#user').removeClass("is-invalid");
                                $('#user').addClass("is-valid");
                                $('#user-validacion').removeClass("invalid-feedback");
                                $('#user-validacion').addClass("valid-feedback");
                                $('#user-validacion').html("<span>"+user+" está disponible.</span>");
                            }
                            console.log("resultado :"+userExiste);
                        },
                        error: function (xhr, ajaxOptions, thrownError) {
                        console.log(xhr.status);
                        console.log(thrownError);
                        }
                        });
                    } else {
                        console.log("user vacio");
                        $('#user').removeClass("is-invalid");
                        $('#user').removeClass("is-valid");
                        $('#user-validacion').removeClass("valid-feedback");
                        $('#user-validacion').removeClass("invalid-feedback");
                        $('#user-validacion').html("");
                    }
                }
            </script>
        <title>Crear usuario</title>
    </head>
    <body>
            <div class="cotainer">
                <div class="row justify-content-center">
                    <div class="col-md-8">
                        <h1>uyTube</h1>
                    </div>
                </div>
                <div class="row justify-content-center">
                    <div class="col-md-8">
                        <div class="card">
                            <div class="card-header">Crear User - Parte 1</div>
                            <div class="card-body">
                                 <!-- <form action="UploadServlet" method="post" enctype = "multipart/form-data">-->
                                   <!--   <form action="altaFileImage.jsp" method="post" enctype = "multipart/form-data">-->
                                <form action="altaFileImage.jsp" method="post" >
                                    <div class="form-group row">
                                        <label for="user" class="col-md-4 col-form-label text-md-right">Usuario</label>
                                        <div class="col-md-6">
                                            <input type="text" id="user" class="form-control" name="user" required autofocus>
                                            <div id="user-validacion">
                                            </div> 
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label for="password" class="col-md-4 col-form-label text-md-right">Contraseña</label>
                                        <div class="col-md-6">
                                            <input type="password" id="password" class="form-control" name="password" required>
                                        </div>
                                    </div>
                                    
                                      <div class="form-group row">
                                        <label for="password" class="col-md-4 col-form-label text-md-right">Confirmacion de Contraseña</label>
                                        <div class="col-md-6">
                                            <input type="password" id="password2" class="form-control" name="password2" required>
                                        </div>
                                    </div>
                                    
                                    <div class="form-group row">
                                        <label for="name" class="col-md-4 col-form-label text-md-right">Nombre</label>
                                        <div class="col-md-6">
                                            <input type="text" id="name" class="form-control" name="name" required>
                                        </div>
                                    </div>
                                    
                                    <div class="form-group row">
                                        <label for="apellido" class="col-md-4 col-form-label text-md-right">Apellido</label>
                                        <div class="col-md-6">
                                            <input type="text" id="apellido" class="form-control" name="apellido" required>
                                        </div>
                                    </div>
                                    
                                    
                                    <div class="form-group row">
                                        <label for="mail" class="col-md-4 col-form-label text-md-right">Mail</label>
                                        <div class="col-md-6">
                                            <input type="text" id="mail" class="form-control" name="mail" required>
                                        </div>
                                    </div>
                                    
                                    <div class="form-group row">
                                        <label for="fecha" class="col-md-4 col-form-label text-md-right">Fecha Nacimiento</label>
                                        <div class="col-md-6">
                                      <div class="md-form">
                                       <input type="text" id="datepicker" class="form-control" name="datepicker" required>
                                      </div>
                                        </div>
                                    </div>
                                    
                                    
                                    <div class="card-body">
                                        <div class="card-body">Canal</div>
                                        
                                        
                                    <div class="form-group row">
                                        <label for="apellido" class="col-md-4 col-form-label text-md-right">Nombre del canal</label>
                                        <div class="col-md-6">
                                            <input type="text" id="canalNombre" class="form-control" name="canalNombre">
                                        </div>
                                    </div>
                                        
                                        
                                    <div class="form-group row">
                                        <label for="desc" class="col-md-4 col-form-label text-md-right">Descripción</label>
                                        <div class="col-md-6">
                                            <textarea class="form-control" id="desc" name="desc" rows="3" placeholder="Descripcion del canal"></textarea>
                                        </div>
                                    </div>
                                        
                                    <div class="form-group row">
                                        <label for="Visibilidad" class="col-md-4 col-form-label text-md-right">Visibilidad</label>
                                        <div class="col-md-6">
                                            <div class="form-check">
                                            <input class="form-check-input" type="radio" name="visibilidad" value="privado" checked>
                                            <label class="form-check-label" for="visibilidad1">
                                              Privado
                                            </label>
                                          </div>
                                          <div class="form-check">
                                            <input class="form-check-input" type="radio" name="visibilidad" value="publico">
                                            <label class="form-check-label" for="visibilidad2">
                                              Publico
                                            </label>
                                          </div>
                                        </div>
                                    </div>
                                        
                                        
                                   <div class="form-group row">
                                    <label for="exampleFormControlSelect1">Categoria del canal</label>
                                    <select class="form-control" id="categoria" name="categoria">
                                        <%
                                        	WScontroladorCategoria cat = new WScontroladorCategoriaImplService().getWScontroladorCategoriaImplPort();
                                            List<CategoriaDt> catArray = cat.listarCategorias().getLista();
                                            
                                        %>
                                        
                                        <%
                                            for (CategoriaDt c : catArray) {
                                        %>
                                            <option value="<% out.print(c.getNombre()); %>">
                                          <%
                                               out.print(c.getNombre());
                                           %>
                                           </option>
                                      
                                            <%
                                                
                                                }
                                            
                                            %>
                                            
                                    </select>
                                  </div>
                                        
                                        
                                    </div>

                                    <div class="col-md-6 offset-md-4">
                                        <button type="submit" class="btn btn-primary">
                                            Siguiente
                                        </button>
                                    </div>
                                    
                                     <% if (session.getAttribute("errorAltaUser")=="nick") { %>
                                            <div class="alert alert-danger" role="alert">
                                                Ya existe un user con ese Nick
                                            </div>
                                        <%}
                                    %>
                                    
                                    <% if (session.getAttribute("errorAltaUser")=="email") { %>
                                            <div class="alert alert-danger" role="alert">
                                                No ingreso un email valido
                                            </div>
                                        <%}
                                    %>
                                    
                                    <% if (session.getAttribute("errorAltaUser")=="pass") { %>
                                            <div class="alert alert-danger" role="alert">
                                                Los passwords no coinciden
                                            </div>
                                        <%}
                                    %>
                                    
                                       <% if (session.getAttribute("errorAltaUser")=="mail") { %>
                                            <div class="alert alert-danger" role="alert">
                                                Ya existe un user con ese mail
                                            </div>
                                        <%}
                                    %>
                                    
                              </form>      
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        <%@include file="include/footer.jsp" %>
    </body>
</html>