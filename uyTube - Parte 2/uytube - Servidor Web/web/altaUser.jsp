<%-- 
    Document   : altaUser
    Created on : Sep 28, 2019, 5:28:28 PM
    Author     : pagol
--%>
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
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "logica.controladores.Fabrica" %>
<%@page import = "logica.controladores.IControladorUsuario"%>

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

        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.min.js"></script>
        
          <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
            <link rel="stylesheet" href="/resources/demos/style.css">
            <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
            <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  
          <script>
                $( function() {
                 $( "#datepicker" ).datepicker();
                } );
            </script>
            
            <%
                
                //Testing weas
                /*
                Fabrica f = Fabrica.getInstance();
                IControladorUsuario user = f.getIControladorUsuario();
              
                     SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
                     java.util.Date fechaNacimiento = sdf.parse("11/11/1999"); 
                     
                user.AltaUsuario("asd", "123", "123","123", "123@123.com", fechaNacimiento, "Richard.jpg");
                
                int IdUsuarioCreate = user.obtenerIdUsuario("asd");
                */   
                     
                //user.AltaCanal("asd", true, IdUsuarioCreate, "");
                     
                     
                
                
                 if (request.getParameter("user") != null) {
                    if (request.getParameter("file") != null) {
                        
               //session.setAttribute("myname","TULIOs");  
               //session.setAttribute("myname",request.getParameter("name")); 
               //session.setAttribute("file",request.getParameter("file")); 
                //request.setAttribute("myname",login);
                //equest.getRequestDispatcher("altaFileImage.jsp").forward(request, response);
                //String redirectURL = "altaFileImage.jsp";
                //response.sendRedirect(redirectURL);                
                 }
                
                }
                
            %>
            
            
  
  
        <title>Crear usuario</title>
            
    </head>
    <body>
        <main class="login-form">
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
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label for="password" class="col-md-4 col-form-label text-md-right">Contraseña</label>
                                        <div class="col-md-6">
                                            <input type="password" id="password" class="form-control" name="password" required>
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
                                    
                                    
                            </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            </div>

        </main>
    </body>
</html>