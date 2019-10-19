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
          <%@include file="include/header.jsp" %>
          <script src="js/autocomplete.min.js"></script>
                  <title>consulta User jsp</title>          
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial;}

/* Style the tab */
.tab {
  overflow: hidden;
  border: 1px solid #ccc;
  background-color: #f1f1f1;
}

/* Style the buttons inside the tab */
.tab button {
  background-color: inherit;
  float: left;
  border: none;
  outline: none;
  cursor: pointer;
  padding: 14px 16px;
  transition: 0.3s;
  font-size: 17px;
}

/* Change background color of buttons on hover */
.tab button:hover {
  background-color: #ddd;
}

/* Create an active/current tablink class */
.tab button.active {
  background-color: #ccc;
}

/* Style the tab content */
.tabcontent {
  display: none;
  padding: 6px 12px;
  border: 1px solid #ccc;
  border-top: none;
}
</style>
 </head>
<body>
    

<script>
        myData = [];
        console.log("prueba1");
        $( document ).ready(function() {
            console.log("prueba2");          
              cargarBuscador();
                listarVideos();
              console.log("prueba2");          

          $( "#btnBuscarUsuario" ).click(function() {
              listarDatosUsuario();
              
              
              // alert("button was clicked");
            }); 
        });
        
        function cargarBuscador() {
            $.ajax({

                url:"http://localhost:8080/WebApplication/api/obtenerUsuarios.jsp",
                
                success:function(response){   
                    console.log(response);
                    let usuarios = jQuery.parseJSON(response);
                    //let html = "";
                    //console.log(usuarios);
                    console.log(usuarios[0].mail);
                                        console.log("prueba4");          

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
                        html += '<th scope="row">'+usuarios[i].nombre+ ' '+usuarios[i].apellido+'</th>';
                      
                        html += '<td>'+usuarios[i].Subcriptores+ '  subscriptores</td>';
                     
                        html += "</tr>";
                     $('.table tbody').html(html);
                        var img = document.getElementById("imgU");
                        img.src = img.src = usuarios[i].imagen; //aca va img.src = usuarios[i].imagen;
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
        }
          function listarVideos() {
                    $.ajax({
                        url:"http://localhost:8080/WebApplication/api/obtenerVideos.jsp",
                        success:function(response){   
                            //console.log(response);
                            let videos = jQuery.parseJSON(response);
                            let html = "";
                            //console.log(usuarios);
                            //console.log(videos[0].mail);
                            for (let i = 0; i < videos.length; i++) {
                                html += "<tr>";
                                if(videos[i].thumbnail != "") {
                                    html += '<th><img src="'+videos[i].thumbnail+'" alt="Thumbnail"></th>';
                                } else {
                                    html += '<th><img src="img/no-thumbnail.jpg" alt="Thumbnail"></th>';
                                }
                                html += '<td  scope="row"><a href="video.jsp?id='+ videos[i].id +'">'+videos[i].nombre+'</a></td>';
                                html += '<td>'+videos[i].descripcion+'</td>';
                                html += '<td>'+videos[i].user+'</td>';
                                html += "</tr>";
                            }
                            $('.table2 tbody2').html(html);
                        },
                        error: function (xhr, ajaxOptions, thrownError) {
                          console.log(xhr.status);
                          console.log(thrownError);
                          $('.table2 tbody2').html('<span style="color:red">obtenerUsuarios.jsp esta tirando error 500 cabeza. pa mi que te falto importar la libreria de json.</span>');
                        }
                    });
                }
                      console.log("prueba3");          

    </script>
    
   <main class="comentar-form">
            <div class="cotainer">
                <div class="row justify-content-center">
                    <div class="col-md-8">
                        <div class="card">
                            <div class="container" id="busca" >

                                        <div style="float: left; width: 450px;">
                                        <input type="text" id= "txtBoxSelected" class="demo">
                                        <input type="hidden" id="hidden-field">
                                        </div>
                                         <div style="float: left; width: 200px;">
                                         <button type="button" class="btn btn-primary" id="btnBuscarUsuario">Buscar Usuario</button>
                                        </div>
                                        </div>



                                        <div class="container" id="canalInfo">


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
                            
            </div> 
                    </div> 
                            </div> 
                
                                    </div>
                        
                     
                  <div class="tab">
                    <button class="tablinks" onclick="openCity(event, 'London')">London</button>
                    <button class="tablinks" onclick="openCity(event, 'Paris')">Paris</button>
                    <button class="tablinks" onclick="openCity(event, 'Tokyo')">Tokyo</button>
                  </div>

                  <div id="London" class="tabcontent">
                    <h3>London</h3>
                    <p>London is the capital city of England.</p>
                   
                  </div>

                  <div id="Paris" class="tabcontent">
                    <h3>Paris</h3>
                    <p>Paris is the capital of France.</p> 
                    
                  </div>

                  <div id="Tokyo" class="tabcontent">
                    <h3>Tokyo</h3>
                    <p>Tokyo is the capital of Japan.</p>
                  </div>
                  <script>
                  function openCity(evt, cityName) {
                    var i, tabcontent, tablinks;
                    tabcontent = document.getElementsByClassName("tabcontent");
                    for (i = 0; i < tabcontent.length; i++) {
                      tabcontent[i].style.display = "none";
                    }
                    tablinks = document.getElementsByClassName("tablinks");
                    for (i = 0; i < tablinks.length; i++) {
                      tablinks[i].className = tablinks[i].className.replace(" active", "");
                    }
                    document.getElementById(cityName).style.display = "block";
                    evt.currentTarget.className += " active";
                  }
                  </script>

   
</main>
      <%@include file="include/footer.jsp" %>
</body>