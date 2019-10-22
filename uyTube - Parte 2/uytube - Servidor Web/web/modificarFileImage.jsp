<%-- 
    Document   : modificarFileImage
    Created on : Oct 16, 2019, 12:00:39 AM
    Author     : pagol
--%>

<%@page import="logica.dt.UsuarioDt"%>
<%@page import="java.io.File"%>
<%@page import="logica.controladores.Fabrica"%>
<%@page import="logica.controladores.IControladorUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
          <%@include file="include/header.jsp" %>
          
          <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.min.js"></script>
        
          <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
            <link rel="stylesheet" href="/resources/demos/style.css">
            <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
            <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  
            
            
        <title>JSP Page</title>
        
         <%
             
             //ErrorAltaUser seteo en null por si las dudas
             session.setAttribute("errorAltaUser","");
   
               //Verifica User
                    Fabrica f = Fabrica.getInstance();
                    IControladorUsuario user = f.getIControladorUsuario();
             
             
            int _id = (Integer)session.getAttribute("userid");
                    
                    
             //Verifico Email
            String emailV = request.getParameter("mail");
            int posEmailV = emailV.indexOf("@");
            int posEmailV2 = emailV.indexOf(".");
            
            if((posEmailV2==-1)||(posEmailV==-1)){
                session.setAttribute("errorAltaUser","email");
                String redirectURL = "modificarUser.jsp";
                //evitar intento de doble redirect
                if (!response.isCommitted()){
                response.sendRedirect(redirectURL); 
                }
              
            }
            
               //Mail No Duplicado que no sea de el
             int idUserM = -1;
             idUserM = user.obtenerIdUsuarioMail(request.getParameter("mail"));
             
             if((idUserM!=-1)&&(idUserM!=_id)){
                 session.setAttribute("errorAltaUser","mail");
                String redirectURL = "modificarUser.jsp";
                  if (!response.isCommitted()){
                response.sendRedirect(redirectURL); 
                }
             
             }
             
              
             //Pass correctas
             if(!(request.getParameter("password").equals(request.getParameter("password2")))){
               session.setAttribute("errorAltaUser","pass");
               String redirectURL = "altaUser.jsp";
                if (!response.isCommitted()){
                response.sendRedirect(redirectURL); 
                }
             }
                 
                 
             //Setea sessions atributes para despues de el upload de img step 2
               session.setAttribute("pswdx",request.getParameter("password"));
               session.setAttribute("namex",request.getParameter("name"));
               session.setAttribute("apellidox",request.getParameter("apellido"));
               session.setAttribute("mailx",request.getParameter("mail"));
               
               if(request.getParameter("datepicker")!=null){
               session.setAttribute("fechax",request.getParameter("datepicker"));
               }
               

            //Canal Sessions
            session.setAttribute("canalx",request.getParameter("canalNombre"));
            session.setAttribute("descx",request.getParameter("desc"));            
            session.setAttribute("visx",request.getParameter("visibilidad"));
            session.setAttribute("catx",request.getParameter("categoria"));
            

            //
            UsuarioDt userx = user.ConsultarUsuario(_id);
               
               
               
                
         %>
            
            
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
                            <div class="card-header">Modificar User - Parte 2</div>
                            <div class="card-body">
                                
                                  <div>
                                      <center>
                                        <img src="/images/<% out.print(userx.getImagen()); %>" width="100" height="auto" />
                                        </center>
                                    </div>
                                 <!-- <form action="UploadServlet" method="post" enctype = "multipart/form-data">-->
                                   <!--   <form action="altaFileImage.jsp" method="post" enctype = "multipart/form-data">-->
                               <form action="modifyFile.jsp" method="post" enctype = "multipart/form-data">
                                    
                                       <div class="form-group row">
                                        <label for="Imagen" class="col-md-4 col-form-label text-md-right">Imagen</label>
                                        <div class="col-md-6">
                                      <div class="md-form">
                                    Select a profile image: <br />
                                       <input type = "file" name = "file" size = "50" />
                                       <br />
                                       </div>
                                        </div>
                                    </div>
                                    

                                    <div class="col-md-6 offset-md-4">
                                        <button type="submit" class="btn btn-primary">
                                            Confirmar Modificaiones
                                        </button>
                                    </div>
                                    
                                    
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

