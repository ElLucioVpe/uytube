<%-- 
    Document   : test
    Created on : Sep 28, 2019, 5:15:56 PM
    Author     : Luciano
--%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.min.js"></script>

        <title>Sitio de prueba</title>
    </head>
    <%
        Date date = new Date();
    %>
    <body>
        <h1 class="display-1">Que onda!</h1>
        <p>esto es una pagina de prueba para probar que todo este funcionando. btw, la fecha es <%= date %> </p>
    
        <script>
            // Un ricolino script de jQuery
            console.log("1 2 3 4 5 6, i take em all no matter what the price is");
            $( document ).ready(function() {
                $( "#prueba" ).append( '<div class="alert alert-success" role="alert">jQuery funciona!</div>' );
            });
        </script>
        
        <div id="prueba"></div>
    </body>
</html>

