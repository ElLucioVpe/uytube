<%-- 
    Document   : altaUser
    Created on : Sep 28, 2019, 5:28:28 PM
    Author     : pagol
--%>
<%@page import = "logica.controladores.Fabrica" %>
<%@page import = "logica.controladores.IControladorUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alta User Jsp</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <!-- Alta User-->
        <% Fabrica f = Fabrica.getInstance(); %>
        <% IControladorUsuario user = f.getIControladorUsuario(); %>
     
        <% user.AltaUsuario("hrubino", "Horacio", "Rubino", "horacio.Rubias@guambia.com.uy", ("25/02/1962") , "hrubino.jpg");  %>
        <!--/Alta User -->
        
    </body>
</html>
