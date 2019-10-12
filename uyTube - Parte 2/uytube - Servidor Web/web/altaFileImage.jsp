<%-- 
    Document   : altaFileImage
    Created on : Oct 11, 2019, 5:34:11 PM
    Author     : pagol
--%>

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
  
            
            
        <title>JSP Page</title>
        
         <%
               session.setAttribute("userx",request.getParameter("user"));
               session.setAttribute("pswdx",request.getParameter("password"));
               session.setAttribute("namex",request.getParameter("name"));
               session.setAttribute("apellidox",request.getParameter("apellido"));
               session.setAttribute("mailx",request.getParameter("mail"));
               session.setAttribute("fechax",request.getParameter("datepicker"));
                
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
                            <div class="card-header">Crear User - Parte 2</div>
                            <div class="card-body">
                                 <!-- <form action="UploadServlet" method="post" enctype = "multipart/form-data">-->
                                   <!--   <form action="altaFileImage.jsp" method="post" enctype = "multipart/form-data">-->
                               <form action="UploadServlet" method="post" enctype = "multipart/form-data">
                                    
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
                                            Crear
                                        </button>
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
