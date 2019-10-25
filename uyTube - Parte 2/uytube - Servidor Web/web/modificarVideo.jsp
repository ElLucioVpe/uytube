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
                 $( "#datepicker" ).datepicker({
                     defaultDate: new Date()
                 });
                } );
            </script>
            <%

               //ErrormodificarVideo seteado en null por las deudas
                session.setAttribute("errormodificarVideo","");

                String entre = "0";
                Fabrica f = Fabrica.getInstance();
                IControladorUsuario user = f.getIControladorUsuario();
                IControladorVideo vid = f.getIControladorVideo();
                IControladorCategoria cat = f.getIControladorCategoria();


                int _id = -1;
                _id = Integer.parseInt(request.getParameter("iddelvideo"));
                out.println("<script>console.log('videito "+_id+"');</script>");

                VideoDt vidx = vid.obtenerVideoDtPorID(_id);
                if(_id == -1 ){
                    String redirectURL = "index.jsp";

                    if (!response.isCommitted()){
                    response.sendRedirect(redirectURL);
                    }
                }
                int idcanal = vidx.getIdCanal();

                CanalDt canalx = user.obtenerCanalDt(idcanal);
                 String nombreUp = vidx.getNombre();
                 String urlUp = vidx.getUrl();
                 String descripcionUp = vidx.getDescripcion();
                 String categoriaUp = vidx.getCategoria();
                 boolean visUp = vidx.getPrivacidad();
                 String visibility="AHRELOCO";


                 float tiempo = vidx.getDuracion();



                String str=Float.toString(tiempo);
                String strarray[]=str.split("\\.");

                //StringArr[0]=(arr[0]);
                //StringArr[1]=(arr[1]);


                 String segundosint = strarray[1];
                 String minutosint =  strarray[0];

                 SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
                 java.util.Date fechaSubida = null;
                 DateFormat fecha= new SimpleDateFormat("dd/MM/yyyy");
                 String fechaUp= fecha.format(vidx.getFechaPublicacion());
                 if(fechaUp.contains("-")){
                    fechaUp=fechaUp.replace("-", "/");

                 }
                 fechaSubida = sdf.parse(fechaUp);
                 String fechasubidaog= sdf.format(vidx.getFechaPublicacion());


                //se fija si el user esta logged
                int _userid=0;
                if(session.getAttribute("userid")==null){
                    _userid = -1;
                }else{
                    _userid = (int)session.getAttribute("userid");

               //int _id = (Integer)session.getAttribute("vidid");

                }

                //redirect a no loggeds
                if(_userid==-1){
                String redirectURL = "login.jsp";
                //evitar intento de doble redirect
                    if (!response.isCommitted()){
                    response.sendRedirect(redirectURL);
                    }
                }
				
                boolean seModifico = false;
                if((request.getParameter("nombre")!= null)&&(request.getParameter("url")!= null)&&(request.getParameter("desc")!= null)&&(request.getParameter("categoria")!= null)&&(request.getParameter("datepicker")!= null&&(request.getParameter("segundos")!= null&&(request.getParameter("minutos")!= null))))
                {
                entre = "1";

                nombreUp = request.getParameter("nombre");
                urlUp = request.getParameter("url");
                descripcionUp = request.getParameter("desc");
                categoriaUp = request.getParameter("categoria");
                fechaUp = request.getParameter("datepicker");

				VideoDt videodt = vid.obtenerVideoDt(nombreUp, _id);
				if(videodt == null) {

	                //Crea la variable de la duracion
	                String durat;
	                int nuevosegundosint = Integer.parseInt(request.getParameter("segundos"));
	                int nuevominutosint = Integer.parseInt(request.getParameter("minutos"));
	
	                if(nuevosegundosint<10){
	                    durat = nuevominutosint+".0"+nuevosegundosint;
	                    }else{
	                    durat = nuevominutosint+"."+nuevosegundosint;
	                 }
	
	
	                visibility = request.getParameter("visibilidad");
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
	
	                  // vid.ModificarVideo(1, "locuritaeeeeea", "4.20", "asdads", "coï¿½o", fechaSubida, true, "Carnaval");
	                   vid.ModificarVideo(_id, nombreUp, durat, urlUp, descripcionUp, fechaSubida, visUp, categoriaUp);
	                   seModifico = true;
	                }
                } else session.setAttribute("errormodificarVideo", "nombre");


            %>


        <title>Modificar Video</title>
    </head>
    <body>
    <%@ include file="include/header.jsp" %>
    <%if(seModifico){%><script>alert("El video se modifico con exito");</script><%}%>
        <main class="login-form">

            <div class="cotainer">
                <div class="row justify-content-center">
                    <div class="col-md-8">
                    </div>
                </div>
                <div class="row justify-content-center">
                    <div class="col-md-8">
                        <div class="card">
                            <div class="card-header">Modificar Video</div>
                            <div class="card-body">
                                   <form action="" method="post" >
                                        <div class="form-group row">
                                            <label for="nombre" class="col-md-4 col-form-label text-md-right">Nuevo Nombre:</label>
                                            <div class="col-md-6">
                                                <input type="text" id="nombre" class="form-control" name="nombre" required value="<%if(nombreUp!=null){out.print(nombreUp);} %>">
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label for="minutos" class="col-md-4 col-form-label text-md-right">Nueva duración (minutos)</label>
                                            <div class="col-md-6">
                                                <input type="number" id="minutos" class="form-control" name="minutos" required min="0" max="59" required value="<%if(minutosint!=null){out.print(minutosint);} %>">
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label for="segundos" class="col-md-4 col-form-label text-md-right">Nueva duración (segundos)</label>
                                            <div class="col-md-6">
                                                <input type="number" id="segundos" class="form-control" name="segundos" required min="0" max="59"  required value="<%if(segundosint!=null){out.print(segundosint);} %>">
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label for="url" class="col-md-4 col-form-label text-md-right">Nuevo URL</label>
                                            <div class="col-md-6">
                                                <input type="text" id="url" class="form-control" name="url" required required value="<%if(urlUp!=null){out.print(urlUp);} %>">
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label for="desc" class="col-md-4 col-form-label text-md-right"> Nueva Descripción</label>
                                            <div class="col-md-6">
                                                <input type="text" id="desc" class="form-control" name="desc" required value="<%if(descripcionUp!=null){out.print(descripcionUp);} %>">
                                            </div>
                                        </div>



                                        <div class="form-group row">
                                            <label for="fecha" class="col-md-4 col-form-label text-md-right">Nueva Fecha de subida</label>
                                            <div class="col-md-6">
                                          <div class="md-form">
                                           <input type="text" id="datepicker" class="form-control" name="datepicker"  required value="<%if(fechasubidaog!=null){out.print(fechasubidaog);} %>">
                                          </div>
                                            </div>
                                        </div>



                                    <div class="form-group row">
                                          <label for="Visibilidad" class="col-md-4 col-form-label text-md-right">Visibilidad</label>
                                          <div class="col-md-6">
                                            <div class="form-check">
                                              <input class="form-check-input" type="radio" name="visibilidad" value="privado"<%if(vidx.getPrivacidad()){out.print("checked");}%> >
                                              <label class="form-check-label" for="visibilidad1">
                                                Privado
                                              </label>
                                            </div>
                                            <div class="form-check">
                                              <input class="form-check-input" type="radio" name="visibilidad" value="publico"<%if(!vidx.getPrivacidad()){out.print("checked");}%> >
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
                                                   <option value="<%out.print(c.getNombre());%>" <%if(vidx.getCategoria().equals(c.getNombre())){out.println("selected");}%>>
                                                 <%
                                                      out.println(c.getNombre());
                                                  %>
                                                  </option>

                                                   <%

                                                       }

                                                   %>

                                           </select>
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
                                        
                                        <% if (session.getAttribute("errormodificarVideo")=="nombre") { %>
                                                <div class="alert alert-danger" role="alert">
                                                    Ya tiene un video con ese nombre.
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
