<%-- 
    Document   : altaVideo2
    Created on : 18 Oct 2019, 12:35:23
    Author     : Xavel
--%>

<%@page import="java.util.List"%>
<%@page import="logica.dt.VideoDt"%>
<%@page import="logica.dt.CategoriaDt"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import = "logica.controladores.Fabrica" %>
<%@page import = "logica.controladores.IControladorVideo"%>
<%@page import = "logica.controladores.IControladorUsuario"%>
<%@page import = "logica.controladores.IControladorCategoria"%>


<%@page contentType="text/html"%>
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alta Video</title>
         <%
             
             
                Fabrica f = Fabrica.getInstance();
                IControladorVideo vid = f.getIControladorVideo();
                IControladorUsuario usu = f.getIControladorUsuario();
                IControladorCategoria cat = f.getIControladorCategoria();
                
                session.setAttribute("errorAltaVideo", "");
                boolean seCreo = false;
                //se fija si el user esta logged
                int _id=0;
                if(session.getAttribute("userid")==null){
                    _id = -1; 
                }else{
                    _id = (int)session.getAttribute("userid");
                }
               
                //redirect a no loggeds
                if(_id==-1){
                String redirectURL = "login.jsp";
                //evitar intento de doble redirect
                    if (!response.isCommitted()){
                    response.sendRedirect(redirectURL); 
                    }
                }
               
                if((request.getParameter("video")!= null)&&(request.getParameter("url")!= null)&&(request.getParameter("desc")!= null)&&(request.getParameter("categoria")!= null)&&(request.getParameter("segundos")!= null)&&(request.getParameter("minutos")!= null))
                {
                String nombre = request.getParameter("video");
                String url = request.getParameter("url");
                String descr = request.getParameter("desc");
                String categoria = request.getParameter("categoria");
                
                //Crea la variable de la duracion
                String durat;
                int segundosint = Integer.parseInt(request.getParameter("segundos"));	
                int minutosint = Integer.parseInt(request.getParameter("minutos"));	
                
                if(segundosint<10){
                durat = minutosint+".0"+segundosint;
                     }else{
                    durat = minutosint+"."+segundosint;
                 }
                	VideoDt videoAntes = vid.obtenerVideoDt(nombre, _id);
                	if(videoAntes != null) session.setAttribute("errorAltaVideo", "nombrevid");
                	
                    vid.AltaVideo(nombre, durat, url, descr, _id, categoria);
                    
                    VideoDt videoDespues = vid.obtenerVideoDt(nombre, _id);
                    if(videoAntes == null && videoDespues != null) seCreo = true;
                }
        
             
            %>
    </head>
    
    <body>
      <%@ include file="include/header.jsp" %>
      <%if(seCreo){%><script>alert("El video se agrego con exito");</script><%}%>
      <main class="altaVideo-form">
            <div class="row justify-content-center">
		<div class="col-md-12">

                    </div>
                         </div>
			<form role="form">
                             <div class="row justify-content-center">
                                    <div class="col-md-8">
                                            <div class="card">
                                            <div class="card-header">Alta Video</div>
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
                                            <input type="number" id="minutos" class="form-control" name="minutos" required min="0">
                                        </div>
                                    </div>
                                    
                                    <div class="form-group row">
                                        <label for="segundos" class="col-md-4 col-form-label text-md-right">Duración (segundos)</label>
                                        <div class="col-md-6">
                                            <input type="number" id="segundos" class="form-control" name="segundos" required min="0" max="59">
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
                                    <label for="exampleFormControlSelect1">Categoria del video</label>
                                    <select class="form-control" id="categoria" name="categoria">
                                        <%
                                       
                                            List<CategoriaDt> catArray = cat.ListarCategorias();
                                            
                                        %>
                                        
                                        <%
                                            for (CategoriaDt c : catArray) {
                                        %>
                                            <option value="<%out.print(c.getNombre());%>">
                                          <%
                                               out.print(c.getNombre());
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
                                    <% if (session.getAttribute("errorAltaVideo")=="nombrevid") { %>
                                            <div class="alert alert-danger" role="alert">
                                                Ya tiene un video con ese nombre.
                                            </div>
                                        <%}
                                    %>
                     
		</div>
               </div>
	</div>
</div>
                                
                                    
       			</form>
<%@include file="include/footer.jsp" %>
    </body>
    
</html>   
