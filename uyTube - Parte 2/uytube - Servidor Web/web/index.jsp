<%-- 
    Document   : index
    Created on : Sep 27, 2019, 10:32:17 PM
    Author     : pagol
--%>
<%@page import = "javax.persistence.*"%>
<%@page import = "logica.controladores.Fabrica"%>
<%@page import = "logica.controladores.IControladorUsuario"%>

<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.min.js"></script>

        <title>UyTube Test</title>
    </head>
    <%
        Date date = new Date();
        
    %>
        
    <body>
         <!-- Alta User-->
        <%! Fabrica f = Fabrica.getInstance();
            IControladorUsuario user = f.getIControladorUsuario(); %>
     
        <!--/Alta User -->
        <h1>Prueba de Base de Datos</h1>
        <p>El usuario con id = 1 es <%= user.obtenerNickUsuario(1) %> </p>
        
        <%
            if (session.getAttribute("userid") != "" ) { %>
                <div class="alert alert-danger" role="alert">
                   El usuario <%= session.getAttribute("userid") %> esta logueado.
                </div>
            <%}
        %>
    </body>
</html>
