<%-- 
    Document   : consultaUser
    Created on : Sep 29, 2019, 3:44:07 PM
    Author     : antus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "logica.controladores.Fabrica" %>
<%@page import = "logica.controladores.IControladorUsuario"%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.min.js"></script>
        <script src="js/autocomplete.min.js"></script>
          <title>Consulta User Jsp</title>
            <%
                Fabrica f = Fabrica.getInstance();
                IControladorUsuario user = f.getIControladorUsuario();
            %>
      
    </head>
    <body>
          <main class="consultaUser-from">
          <div class="form-group">
            <label>Usuario</label>
            <input type="text" class="demo">
            <input type="hidden" id="hidden-field">
 
<script>
    myData = [{"id":1,"name":'Item1',"ignore":false},{"id":2,"name":'Item2',"ignore":false},{"id":3,"name":'Item3',"ignore":false}];

    $( document ).ready(function() {
        $('.demo').autocomplete({nameProperty:'name',valueField:'#hidden-field',dataSource: myData});
    });
</script>
            
          </div>
          </main>   
      
    </body>
</html>