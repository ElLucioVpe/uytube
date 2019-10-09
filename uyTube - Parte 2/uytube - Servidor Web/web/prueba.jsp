<%-- 
    Document   : prueba
    Created on : Oct 2, 2019, 3:15:18 PM
    Author     : antus
--%>
<%@page import = "javax.persistence.*"%>
<%@page import = "logica.controladores.Fabrica"%>
<%@page import = "logica.controladores.IControladorUsuario"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<!-- Load JQuery -->
  <head>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <script src="js/jquery.min.js"></script>
         <script src="js/autocomplete.min.js"></script>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prueba Jsp</title>          
      
 </head>
<body>
<!-- Load JSON file and output it-->

<!--$<script>
(function() {
    myData = [];
   // myData.push({"id":1,"name":'Item1',"ignore":false},{"id":2,"name":'Item2',"ignore":false},{"id":3,"name":'Item3',"ignore":false})

 // User clicks the "getData" button
   $( document ).ready(function() {
          

    // Put artistList element and JSON file location into a variable
    var artistList = $("#artistList");
    var url = "http://localhost:8080/WebApplication/artists.txt";

    // Get the JSON file
    $.getJSON(url, function(data) {

      // Put artist info into a variable
       data.artists.map(function(item) {
           myData.push({"id":1,"name":item.artistname,"ignore":false});
        //return item.artistname + " (" + item.born + ")";
      });
      
      // Remove all child nodes (including text nodes) 
      artistList.empty();

      // Format artists with HTML tags 
      if (artists.length) {
        var content = "<li>" + artists.join("</li><li>") + "</li>";
        var list = $("<ul>").html(content);
        artistList.append(list);
      }
    });
  });

});

</script>-->

<script>
        myData = [];
        console.log("prueba1");
        $( document ).ready(function() {
            console.log("prueba2");          
              cargarBuscador();
          $( "#btnBuscarUsuario" ).click(function() {
              listarDatosUsuario();
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
          
        
        function listarDatosUsuario() {
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
                        html += '<th scope="row">'+usuarios[i].id+'</th>';
                        html += '<td>'+usuarios[i].nombre+'</td>';
                        html += '<td>'+usuarios[i].apellido+'</td>';
                        html += '<td>'+usuarios[i].nickname+'</td>';
                        html += "</tr>";
                    }
                    }
                    $('.table tbody').html(html);
                }
            });
        }
          
    </script>
<!--<script>
    

    $( document ).ready(function() {
    });
</script>-->
<!-- The output appears here -->
<section class="container">
    <div style="float: left; width: 80px;">

    <img src="logo.jpg" alt="Youtube Logo" style= " width:70px;height:40px;">
    </div>
    <div style="float: left; width: 500px;">
    <input type="text" id= "txtBoxSelected" class="demo">
    <input type="hidden" id="hidden-field">
    </div>
     <div style="float: right; width: 200px;">
     <button type="button" class="btn btn-primary" id="btnBuscarUsuario">Buscar Usuario</button>
    </div>
    </section>
    
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
</body>