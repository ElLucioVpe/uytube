<%-- 
    Document   : consultaUser
    Created on : Oct 2, 2019, 3:15:18 PM
    Author     : antus
--%>
<%@page import = "javax.persistence.*"%>
<%@page import = "logica.controladores.Fabrica"%>
<%@page import = "logica.controladores.IControladorUsuario"%>
<%@page import = "logica.dt.UsuarioDt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<!-- Load JQuery -->
  <head>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <script src="js/jquery.min.js"></script>
         
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>consulta User jsp</title>          
          <%@include file="include/header.jsp" %>
          <script src="js/autocomplete.min.js"></script>
 </head>
<body>
    

<script>
        myData = [];
       // console.log("prueba1");
        $( document ).ready(function() {
            //console.log("prueba2");          
              cargarBuscador();
          $( "#btnBuscarUsuario" ).click(function() {
            //  listarDatosUsuario();
              // alert("button was clicked");
            }); 
        });
        
        function cargarBuscador() {
            $.ajax({
                url:"http://localhost:8080/WebApplication/api/obtenerUsuarios.jsp",
                success:function(response){   
                    //console.log(response);
                    let usuarios = jQuery.parseJSON(response);
                    //let html = "";
                    //console.log(usuarios);
                    console.log(usuarios[0].mail);
                    for (let i = 0; i < usuarios.length; i++) {
                    myData.push({"id":1,"name":usuarios[i].nickname,"ignore":false});
 
                    }
                            $('.demo').autocomplete({nameProperty:'name',valueField:'#hidden-field',dataSource: myData});
                }
            });
        }
          
        
       /* function listarDatosUsuario() {
            $.ajax({
                url:"http://localhost:8080/WebApplication/api/obtenerUsuarios.jsp",
                success:function(response){   
                    //console.log(response);
                    let usuarios = jQuery.parseJSON(response);
                    let html = "";
                    //console.log(usuarios);
                    console.log(usuarios[0].mail);
                    var nick = document.getElementById("txtBoxSelected").value;

                    for (let i = 0; i < usuarios.length; i++) {
                        if (nick == usuarios[i].nickname){
                        html += "<tr>";
                        html += '<th scope="row">'+usuarios[i].nombre+ ' '+usuarios[i].apellido+'</th>';
                      
                        html += '<td>'+usuarios[i].Subcriptores+'</td>';
                     
                        html += "</tr>";
                     $('.table tbody').html(html);
                        var img = document.getElementById("imgU");
                        img.src = "logo.jpg"; //aca va img.src = usuarios[i].imagen;
                        img.width = "30";
                        img.height = "30";
                        img.alt="User Picture";
                        var src = document.getElementById("imgU");
                        document.getElementById("usrNmb").innerHTML = usuarios[i].nickname;
                        src.appendChild(img);
                        
                        
                       
                    }
                    }
                }
            });
        }*/
          
    </script>
    
 
<div class="container" id="busca" >
   
    <div style="float: left; width: 450px;">
    <input type="text" id= "txtBoxSelected" class="demo">
    <input type="hidden" id="hidden-field">
    </div>
     <div style="float: left; width: 200px;">
     <button type="button" class="btn btn-primary" id="btnBuscarUsuario">Buscar Usuario</button>
    </div>
    </div>
  
     

   <!-- <div class="container" id="canalInfo">
       
        
        <div id =usrImg style="float: center; width: 450px;">
          
         <img id ="imgU" class ="rounded-circle">
         <p id="usrNmb"></p> 
    </div>

      <table class="table">
                  
          <thead>
            <tr>
              <th scope="col"></th>
              <th scope="col"></th>
             
              
            </tr>
          </thead>
          <tbody>
          </tbody>
        </table>
            </div>
-->
      <%@include file="include/footer.jsp" %>
</body>