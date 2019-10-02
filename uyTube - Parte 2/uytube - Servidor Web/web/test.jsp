<%-- 
    Document   : test
    Created on : Sep 28, 2019, 5:15:56 PM
    Author     : Luciano
--%>
<%@page import = "logica.controladores.Fabrica" %>
<%@page import = "logica.controladores.IControladorUsuario"%>

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
    <body>
        <h1 class="display-1">Que onda!</h1>
        <p>esto es una pagina de prueba de toda la wea AJAX/JS/JSON para comunicar una lista de usuarios que se actualiza en tiempo real</p>
    
        
        
        <script>
            $(document).ready(function () {
                $('#txtCountry').typeahead({
                    source: function (query, result) {
                        $.ajax({
                            url: "server.php",
                            data: 'query=' + query,            
                            dataType: "json",
                            type: "POST",
                            success: function (data) {
                            result($.map(data, function (item) {
                                    return item;
                            }));
                            }
                        });
                    }
                });
            });
        </script>
        
        <div id="prueba"></div>
    </body>
</html>

