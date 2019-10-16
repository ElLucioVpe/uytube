<%-- 
    Document   : altaVideo
    Created on : 1 Oct 2019, 21:08:45
    Author     : Xavel
--%>
<%@page import="logica.dt.CategoriaDt"%>
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
<%@page import = "logica.controladores.Fabrica" %>
<%@page import = "logica.controladores.IControladorVideo"%>
<%@page import = "logica.controladores.IControladorUsuario"%>
<%@page import = "logica.controladores.IControladorCategoria"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>Alta Video</title>
        <%
                Fabrica f = Fabrica.getInstance();
                IControladorVideo vid = f.getIControladorVideo();
                IControladorUsuario usu = f.getIControladorUsuario();
                IControladorCategoria cat = f.getIControladorCategoria();
                
                int idUser = -1;
                //se fija si el user existe
                idUser = usu.obtenerIdUsuario(request.getParameter("nombreusu"));
                
                if(idUser==-1){
                session.setAttribute("errorAltaVideo","nombreusu");
             
                }
              
                //chequea el formato de la duración
                
                String duraso = request.getParameter("duracion");
                int posduraso = duraso.indexOf(":");
    
            
            if((posduraso==-1)){
                session.setAttribute("errorAltaUser","duracion");
            }
                //se fija si la categoria existe
               if(request.getParameter("categoria")!=null){
                CategoriaDt dtca = new CategoriaDt();
                
                dtca = cat.ConsultarCategorias(request.getParameter("categoria"));
                 if(dtca==null){
                session.setAttribute("errorAltaVideo","categoria");
                }
                }
                
                vid.AltaVideo(request.getParameter("video"), request.getParameter("duracion"), request.getParameter("url"), request.getParameter("desc"), idUser, request.getParameter("categoria"));
                    
            
            %>
    </head>
    <body>
        <main class="altaVideo-form">
            <div class="row justify-content-center">
		<div class="col-md-12">
                    <h1>uyTube</h1>
                    </div>
                         </div>
			<form role="form">
                             <div class="row justify-content-center">
                                    <div class="col-md-8">
                                            <div class="card">
                                            <div class="card-header">Alta Usuario</div>
                                                <div class="card-body">
                                                    
				<form action="altaFileImage.jsp" method="post" >
                                    <div class="form-group row">
                                        <label for="video" class="col-md-4 col-form-label text-md-right">Nombre Video</label>
                                        <div class="col-md-6">
                                            <input type="text" id="video" class="form-control" name="video" required autofocus>
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label for="duracion" class="col-md-4 col-form-label text-md-right">Duracion</label>
                                        <div class="col-md-6">
                                            <input type="text" id="duracion" class="form-control" name="duracion" required>
                                        </div>
                                    </div>
                                    
                                    <div class="form-group row">
                                        <label for="url" class="col-md-4 col-form-label text-md-right">URL</label>
                                        <div class="col-md-6">
                                            <input type="text" id="url" class="form-control" name="url" required>
                                        </div>
                                    </div>
                                    
                                    <div class="form-group row">
                                        <label for="desc" class="col-md-4 col-form-label text-md-right">Descripcion</label>
                                        <div class="col-md-6">
                                            <input type="text" id="desc" class="form-control" name="desc" required>
                                        </div>
                                    </div>
                                    
                                    
                                    <div class="form-group row">
                                        <label for="nombreusu" class="col-md-4 col-form-label text-md-right">Nombre Usuario</label>
                                        <div class="col-md-6">
                                            <input type="text" id="nombreusu" class="form-control" name="nombreusu" required>
                                        </div>
                                    </div>
                                    
                                    <div class="form-group row">
                                        <label for="categoria" class="col-md-4 col-form-label text-md-right">Categoria</label>
                                        <div class="col-md-6">
                                            <input type="text" id="categoria" class="form-control" name="categoria" required>
                                        </div>
                                    </div>
                                    
                                    
				
				<button type="submit" class="btn btn-primary">
					Submit
				</button>
                                    
                                    <% if (session.getAttribute("errorAltaVideo")=="nombreusu") { %>
                                            <div class="alert alert-danger" role="alert">
                                                No existe usuario con ese nick.
                                            </div>
                                        <%}
                                    %>
                                    
                                    <% if (session.getAttribute("errorAltaVideo")=="duracion") { %>
                                            <div class="alert alert-danger" role="alert">
                                                La duración debe ser ingresada en el siguiente formato "mm:ss".
                                            </div>
                                        <%}
                                    %>
                                    
                                    <% if (session.getAttribute("errorAltaVideo")=="categoria") { %>
                                            <div class="alert alert-danger" role="alert">
                                                La categoria ingresada no existe.
                                            </div>
                                        <%}
                                    %>
                                    
		</div>
               </div>
	</div>
</div>
       			</form>

    </body>
    
</html>

