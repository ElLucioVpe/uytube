<%-- 
    Document   : modificarUser
    Created on : Oct 14, 2019, 9:57:02 PM
    Author     : pagol
--%>

<%@page import="java.text.DateFormat"%>
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
<%@page contentType="text/html"%>
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
            <%@include file="include/header.jsp" %>
            <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
            
             <script>
                $( function() {
                 $( "#datepicker" ).datepicker({
                     defaultDate: new Date()
                 });
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
       
                //Setea sessions atributes para despues de el upload de img step 2
                session.setAttribute("userx",nick);
            

                  
                DateFormat fecha= new SimpleDateFormat("dd/MM/yyyy");
                String fechaNacimiento= fecha.format(userx.getFechanac());
                UsuarioDt imm = user.ConsultarUsuario(_id);
                
                
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
                                        <label for="password" class="col-md-4 col-form-label text-md-right">Contraseņa</label>
                                        <div class="col-md-6">
                                            <input type="password" id="password" class="form-control" name="password" required value="<%if(userx.getPassword()!=null){out.print(userx.getPassword());} %>">
                                        </div>
                                    </div>
                                        
                                    <div class="form-group row">
                                        <label for="password" class="col-md-4 col-form-label text-md-right">Confirmacion de Contraseņa</label>
                                        <div class="col-md-6">
                                            <input type="password" id="password2" class="form-control" name="password2" required value="<%if(userx.getPassword()!=null){out.print(userx.getPassword());} %>">
                                        </div>
                                    </div>
                                    
                                    <div class="form-group row">
                                        <label for="name" class="col-md-4 col-form-label text-md-right">Nombre</label>
                                        <div class="col-md-6">
                                            <input type="text" id="name" class="form-control" name="name" required value="<% if(userx.getPassword()!=null){out.print(userx.getNombre());} %>">
                                        </div>
                                    </div>
                                    
                                    <div class="form-group row">
                                        <label for="apellido" class="col-md-4 col-form-label text-md-right">Apellido</label>
                                        <div class="col-md-6">
                                            <input type="text" id="apellido" class="form-control" name="apellido" required value="<%out.print(userx.getApellido()); %>">
                                        </div>
                                    </div>
                                    
                                    
                                    <div class="form-group row">
                                        <label for="mail" class="col-md-4 col-form-label text-md-right">Mail</label>
                                        <div class="col-md-6">
                                            <input type="text" id="mail" class="form-control" name="mail" required value="<%out.print(userx.getMail()); %>">
                                        </div>
                                    </div>
                                    
                                    <div class="form-group row">
                                        <label for="fecha" class="col-md-4 col-form-label text-md-right">Fecha Nacimiento</label>
                                        <div class="col-md-6">
                                      <div class="md-form">
                                       <input type="text" id="datepicker" class="form-control" name="datepicker" required value="<%out.print(fechaNacimiento);%>" >
                                      </div>
                                        </div>
                                    </div>
                                    
                                    
                                    <div class="card-body">
                                        <div class="card-body">Canal</div>
                                        
                                     <div class="form-group row">
                                        <label for="apellido" class="col-md-4 col-form-label text-md-right">Nombre del canal</label>
                                        <div class="col-md-6">
                                            <input type="text" id="canalNombre" class="form-control" name="canalNombre" value="<%out.print(canalx.getNombre());%>">
                                        </div>
                                    </div>
                                        
                                        
                                    <div class="form-group row">
                                        <label for="desc" class="col-md-4 col-form-label text-md-right">Descripcion</label>
                                        <div class="col-md-6">
                                            <textarea class="form-control" id="desc" name="desc" rows="3" placeholder="Descripcion del canal">
                                            <%if(canalx.getDescripcion() != null){%> <%=canalx.getDescripcion()%><%}%>
                                            </textarea>
                                        </div>
                                    </div>
                                        
                                    <div class="form-group row">
                                        <label for="Visibilidad" class="col-md-4 col-form-label text-md-right">Visibilidad</label>
                                        <div class="col-md-6">
                                            <div class="form-check">
                                            <input class="form-check-input" type="radio" name="visibilidad" value="privado" <%if(canalx.getPrivacidad()){out.print("checked");} %> >
                                            <label class="form-check-label" for="visibilidad1">
                                              Privado
                                            </label>
                                          </div>
                                          <div class="form-check">
                                            <input class="form-check-input" type="radio" name="visibilidad" value="publico" <%if(!canalx.getPrivacidad()){out.print("checked");} %> >
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
                                            <option value="<%out.print(c.getNombre());%>"<%if(canalx.getCategoria().equals(c.getNombre())){out.print("selected");}%>>
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
        </main>
         <%@include file="include/footer.jsp" %>
    </body>
</html>
