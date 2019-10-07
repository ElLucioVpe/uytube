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

        <title>uyTube - Transmite tú mismo</title>
    </head>
    <body>
        <%! Fabrica f = Fabrica.getInstance();
            IControladorUsuario user = f.getIControladorUsuario(); %>
        <%@ include file="include/header.jsp" %>  
        
        
        
        <%@ include file="include/footer.jsp" %>  
    </body>
</html>
