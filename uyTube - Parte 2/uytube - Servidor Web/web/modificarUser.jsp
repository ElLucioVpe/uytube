<%-- 
    Document   : modificarUser
    Created on : Oct 14, 2019, 9:57:02 PM
    Author     : pagol
--%>

<%@page import="logica.dt.CanalDt"%>
<%@page import="logica.dt.UsuarioDt"%>
<%@page import="logica.dt.CategoriaDt"%>
<%@page import="logica.Categoria"%>
<%@page import="logica.controladores.IControladorCategoria"%>
<%@page import="logica.controladores.IControladorVideo"%>
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
                
                Fabrica f = Fabrica.getInstance();
                IControladorUsuario user = f.getIControladorUsuario();
                
                int _id = (Integer)session.getAttribute("userid");
                //int _id = 16;
                UsuarioDt userx = user.ConsultarUsuario(_id);
                
                //Canal
                CanalDt canalx = user.obtenerCanalDt(_id);
                 
                //El nick no va a cambiar
                String nick = user.obtenerNickUsuario(_id);
                session.setAttribute("nickx",nick);
                session.setAttribute("imagex",userx.getImagen()); 
                     
                //user.AltaCanal("asd", true, IdUsuarioCreate, "");
                     
                     
                
            %>
            
            
  
  
        <title>Modificar usuario</title>
            
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
                            <div class="card-header">Modificar User - Parte 1</div>
                            <div class="card-body">
                                 <!-- <form action="UploadServlet" method="post" enctype = "multipart/form-data">-->
                                   <!--   <form action="altaFileImage.jsp" method="post" enctype = "multipart/form-data">-->
                                <form action="modificarFileImage.jsp" method="post" >
                                
                                    <div class="form-group row">
                                        <label for="password" class="col-md-4 col-form-label text-md-right">Contraseña</label>
                                        <div class="col-md-6">
                                            <input type="password" id="password" class="form-control" name="password" value="<%if(userx.getPassword()!=null){out.println(userx.getPassword());} %>" required>
                                        </div>
                                    </div>
                                    
                                    <div class="form-group row">
                                        <label for="name" class="col-md-4 col-form-label text-md-right">Nombre</label>
                                        <div class="col-md-6">
                                            <input type="text" id="name" class="form-control" name="name" value="<% if(userx.getPassword()!=null){out.println(userx.getNombre());} %>" required>
                                        </div>
                                    </div>
                                    
                                    <div class="form-group row">
                                        <label for="apellido" class="col-md-4 col-form-label text-md-right">Apellido</label>
                                        <div class="col-md-6">
                                            <input type="text" id="apellido" class="form-control" name="apellido" value="<% out.println(userx.getApellido()); %>"  required>
                                        </div>
                                    </div>
                                    
                                    
                                    <div class="form-group row">
                                        <label for="mail" class="col-md-4 col-form-label text-md-right">Mail</label>
                                        <div class="col-md-6">
                                            <input type="text" id="mail" class="form-control" name="mail" value="<% out.println(userx.getMail()); %>"  required>
                                        </div>
                                    </div>
                                    
                                    <div class="form-group row">
                                        <label for="fecha" class="col-md-4 col-form-label text-md-right">Fecha Nacimiento</label>
                                        <div class="col-md-6">
                                      <div class="md-form">
                                       <input type="text" id="datepicker" class="form-control" name="datepicker" value="<% out.println(userx.getFechanac()); %>"  required>
                                      </div>
                                        </div>
                                    </div>
                                    
                                    
                                    <div class="card-body">
                                        <div class="card-body">Canal</div>
                                        
                                    <div class="form-group row">
                                        <label for="desc" class="col-md-4 col-form-label text-md-right">Descripcion</label>
                                        <div class="col-md-6">
                                            <textarea class="form-control" id="desc" name="desc" rows="3" placeholder="Descripcion del canal" required> <% out.print(canalx.getDescripcion()); %> </textarea>
                                        </div>
                                    </div>
                                        
                                    <div class="form-group row">
                                        <label for="Visibilidad" class="col-md-4 col-form-label text-md-right">Visibilidad</label>
                                        <div class="col-md-6">
                                            <div class="form-check">
                                            <input class="form-check-input" type="radio" name="visibilidad" value="privado" <%if(canalx.getPrivacidad()){out.println("checked");} %> >
                                            <label class="form-check-label" for="visibilidad1">
                                              Privado
                                            </label>
                                          </div>
                                          <div class="form-check">
                                            <input class="form-check-input" type="radio" name="visibilidad" value="publico" <%if(!canalx.getPrivacidad()){out.println("checked");} %> >
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
                                          
                                            IControladorCategoria cat = f.getIControladorCategoria();
                                            List<CategoriaDt> catArray = cat.ListarCategorias();
                                            
                                        %>
                                        
                                        <%
                                            for (CategoriaDt c : catArray) {
                                        %>
                                            <option value=<% out.println(c.getNombre()); %> <% if(canalx.getCategoria().equals(c.getNombre())){out.println("selected");}  %> >
                                          <%
                                               out.println(c.getNombre());
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
