a<%-- 
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
    <body>
        <%! Fabrica f = Fabrica.getInstance();
            IControladorUsuario user = f.getIControladorUsuario(); %>
     
        <h1>Estoy haciendo el listar videos, que me imagino va a ser el index</h1>
        <h2>- lucius </h2>
        
        <iframe width="420" height="345" src="http://www.youtube.com/embed/CsKbCdzkNZg?autoplay=1" frameborder="0" allowfullscreen></iframe>
        
        <span>si estan buscando el test de json vayan a test.jsp</span>
    </body>
</html>
