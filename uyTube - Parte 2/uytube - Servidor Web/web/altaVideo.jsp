<%-- 
    Document   : altaVideo
    Created on : 1 Oct 2019, 21:08:45
    Author     : Xavel
--%>
<%@page import="java.util.List"%>
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
                
                String nombre = request.getParameter("video");
                String url = request.getParameter("url");
                String descr = request.getParameter("desc");
                String categoria = request.getParameter("categoria");
                
                
                int idUser = -1;
                //se fija si el user existe
                idUser = usu.obtenerIdUsuario(request.getParameter("nombreusu"));
                
                if(idUser==-1){
                session.setAttribute("errorAltaVideo","nombreusu");
             
                }
              
                //Crea la variable de la duracion
                String durat;
                int segundosint = Integer.getInteger(request.getParameter("segundos")).intValue();
                int minutosint = Integer.getInteger(request.getParameter("minutos")).intValue();
                
                if(segundosint<10){
                durat = minutosint+":0"+segundosint;
                     }else{
                    durat = minutosint+":"+segundosint;
                 }
    

                //se fija si la categoria existe
               if(request.getParameter("categoria")!=null){
                CategoriaDt dtca = new CategoriaDt();
                
                
                
                vid.AltaVideo(nombre, durat, url, descr, idUser, categoria);
                    
            
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
                                                    
			
                                    <div class="form-group row">
                                        <label for="v-ideo" class="col-md-4 col-form-label text-md-right">Nombre Video</label>
                                        <div class="col-md-6">
                                            <input type="text" id="video" class="form-control" name="video" required autofocus>
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label for="minutos" class="col-md-4 col-form-label text-md-right">Duración (minutos)</label>
                                        <div class="col-md-6">
                                            <input type="number" id="minutos" class="form-control" name="minutos" requiered min="0" max="59">
                                        </div>
                                    </div>
                                    
                                    <div class="form-group row">
                                        <label for="segundos" class="col-md-4 col-form-label text-md-right">Duración (segundos)</label>
                                        <div class="col-md-6">
                                            <input type="number" id="segundos" class="form-control" name="segundos" requiered min="0" max="59">
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
                                    <label for="exampleFormControlSelect1">Categoría del video</label>
                                    <select class="form-control" id="categoria" name="categoria">
                                        <%
                                          
                                           
                                            List<CategoriaDt> catArray = cat.ListarCategorias();
                                            
                                        %>
                                        
                                        <%
                                            for (CategoriaDt c : catArray) {
                                        %>
                                            <option value="<% out.print(c.getNombre()); %>"
                                          <%
                                               out.println(c.getNombre());
                                           %>
                                           </option>
                                      
                                            <%
                                                
                                                }
                                            
                                            %>
                                            
                                    </select>
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

                                    
                                  
                                    
		</div>
               </div>
	</div>
</div>
       			</form>

    </body>
    
</html>

