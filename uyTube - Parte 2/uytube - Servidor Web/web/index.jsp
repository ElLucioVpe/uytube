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
            if (session.getAttribute("userid") != null ) { %>
                <div class="alert alert-danger" role="alert">
                   El usuario <%= session.getAttribute("userid") %> esta logueado.
                </div>
            <%} else {
%>
                <div class="alert alert-danger" role="alert">
                   No hay ningun usuario loggeado.
                </div>
            <%
            }
            %>
        
        <h2>Usuarios ☺</h2>
        <table class="table">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Nombre</th>
              <th scope="col">Apellido</th>
              <th scope="col">Nickname</th>
            </tr>
          </thead>
          <tbody>
          </tbody>
        </table>
        <button type="button" class="btn btn-primary" id="btnRecargar">Cargar datos por JSON</button>
    </body>
    
    <script>
        console.log("prueba1");
        $( document ).ready(function() {
            console.log("prueba2");
            
            // Cuando se hace click en btnRecargar llama a la funcion recargar table
            $( "#btnRecargar" ).click(function() {
              recargarTable();
            });
        });
        
        function recargarTable() {
            $.ajax({
                url:"http://localhost:8080/WebApplication/api/obtenerUsuarios.jsp",
                success:function(response){   
                    //console.log(response);
                    let usuarios = jQuery.parseJSON(response);
                    let html = "";
                    //console.log(usuarios);
                    console.log(usuarios[0].mail);
                    for (let i = 0; i < usuarios.length; i++) {
                        html += "<tr>";
                        html += '<th scope="row">'+usuarios[i].id+'</th>';
                        html += '<td>'+usuarios[i].nombre+'</td>';
                        html += '<td>'+usuarios[i].apellido+'</td>';
                        html += '<td>'+usuarios[i].nickname+'</td>';
                        html += "</tr>";
                    }
                    $('.table tbody').html(html);
                }
            });
        }

    </script>
</html>
