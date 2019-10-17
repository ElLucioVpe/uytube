<%-- 
    Document   : modificarVideo
    Created on : 17 Oct 2019, 18:27:04
    Author     : Xavel
--%>
<%@page import="logica.Video"%>
<%@page import="logica.dt.UsuarioDt"%>
<%@page import="logica.Canal"%>
<%@page import="java.text.DateFormat"%>
<%@page import="logica.dt.CanalDt"%>
<%@page import="logica.dt.VideoDt"%>
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

<%@ page import = "java.util.*, javax.servlet.*" %>
<%@ page import = "javax.servlet.http.*" %>
<%@ page import = "org.apache.commons.fileupload.*" %>
<%@ page import = "org.apache.commons.fileupload.disk.*" %>
<%@ page import = "org.apache.commons.fileupload.servlet.*" %>
<%@ page import = "org.apache.commons.io.output.*" %>

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
                 $( "#datepicker" ).datepicker({
                     defaultDate: new Date()
                 });
                } );
            </script>

            <%
                
               //ErrormodificarVideo seteado en null por las deudas
                session.setAttribute("errormodificarVideo","");
             
                
                Fabrica f = Fabrica.getInstance();
                IControladorUsuario user = f.getIControladorUsuario();
                IControladorVideo vid = f.getIControladorVideo();
                IControladorCategoria cat = f.getIControladorCategoria();
                
                 String nombreUp = request.getParameter("nombre");
             
                 String urlUp = request.getParameter("URL");
                 String descripcionUp = request.getParameter("desc");
                  String categoriaUp = request.getParameter("categoria");
                 String fechaUp = request.getParameter("datepicker");
                
                 java.util.Date fechaSubida = null;
                 if(fechaUp.contains("-")){
                    fechaUp=fechaUp.replace("-", "/");
                 
                 }  
                 
                 SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
                 fechaSubida = sdf.parse(fechaUp);
                
                int _id = (Integer)session.getAttribute("vidid");
                
        
                VideoDt vidx = vid.obtenerVideoDtPorID(_id);
                int idcanal = vidx.getIdCanal();
                
                CanalDt canalx = user.obtenerCanalDt(idcanal);
                
                    
                    String fechasubidaog= sdf.format(vidx.getFechaPublicacion());
                   

                //Crea la variable de la duracion
                String durat;
                int segundosint = Integer.getInteger(request.getParameter("segundos")).intValue();
                int minutosint = Integer.getInteger(request.getParameter("minutos")).intValue();
                
                if(segundosint<10){
                durat = minutosint+":0"+segundosint;
                     }else{
                    durat = minutosint+":"+segundosint;
                 }
                
                //visibilidad thangs
                String visibility = (String)session.getAttribute("visx");
                Boolean visUp = true;
                
                if(visibility.contains("privado")){
                 visUp=true;
                 }
                 if (visibility.contains("publico")){
                 visUp=false;
                 }
 
                 Boolean canalpriv = canalx.getPrivacidad();
                    if(canalpriv= true && !visUp) {
                    session.setAttribute("errormodificarVideo","privacidad");

                    }
                    
                    vid.ModificarVideo(_id, nombreUp, durat, urlUp, descripcionUp, fechaSubida, visUp, categoriaUp);
                     
                
            %>
            
  
        <title>Modificar Video</title>
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
                            <div class="card-header">Modificar Video</div>
                            <div class="card-body">
 
                                    <div class="form-group row">
                                        <label for="nombre" class="col-md-4 col-form-label text-md-right">Nuevo Nombre:</label>
                                        <div class="col-md-6">
                                            <input type="text" id="nombre" class="form-control" name="nombre">
                                        </div>
                                    </div>
                                        
                                    <div class="form-group row">
                                        <label for="minutos" class="col-md-4 col-form-label text-md-right">Nueva duración (minutos)</label>
                                        <div class="col-md-6">
                                            <input type="number" id="minutos" class="form-control" name="minutos" requiered min="0" max="59">
                                        </div>
                                    </div>
                                    
                                    <div class="form-group row">
                                        <label for="segundos" class="col-md-4 col-form-label text-md-right">Nueva duración (segundos)</label>
                                        <div class="col-md-6">
                                            <input type="number" id="segundos" class="form-control" name="segundos" requiered min="0" max="59">
                                        </div>
                                    </div>
                                    
                                    <div class="form-group row">
                                        <label for="url" class="col-md-4 col-form-label text-md-right">Nuevo URL</label>
                                        <div class="col-md-6">
                                            <input type="text" id="url" class="form-control" name="url" required>
                                        </div>
                                    </div>
                                    
                                    <div class="form-group row">
                                        <label for="desc" class="col-md-4 col-form-label text-md-right"> Nueva Descripción</label>
                                        <div class="col-md-6">
                                            <input type="text" id="desc" class="form-control" name="desc" required>
                                        </div>
                                    </div>
                                    
                                  
                                    
                                    <div class="form-group row">
                                        <label for="fecha" class="col-md-4 col-form-label text-md-right">Nueva Fecha de subida</label>
                                        <div class="col-md-6">
                                      <div class="md-form">
                                       <input type="text" id="datepicker" class="form-control" name="datepicker" required value="<%out.print(fechasubidaog);%>" >
                                      </div>
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
  
                                            List<CategoriaDt> catArray = cat.ListarCategorias();
                                            
                                        %>
                                        
                                        <%
                                            for (CategoriaDt c : catArray) {
                                        %>
                                            <option value="<% out.print(c.getNombre()); %>" <% if(canalx.getCategoria().equals(c.getNombre())){out.println("selected");}  %>>
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
                                            Modificar
                                        </button>
 
                                    </div>
                                  
                                             <% if (session.getAttribute("errormodificarVideo")=="privacidad") { %>
                                            <div class="alert alert-danger" role="alert">
                                                No se puede tener un video público en un canal privado.
                                            </div>
                                        <%}
                                    %>
                                    
                            
                        </div>
                    </div>
                </div>
            </div>
            

        </main>
    </body>
</html>
