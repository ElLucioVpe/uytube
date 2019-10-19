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
        <input type="hidden" value="asc" name="hidden-order">
        <link rel="stylesheet" href="css/bootstrap4-toggle.min.css">
        <script src="js/bootstrap4-toggle.min.js"></script> 
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <%@include file="include/header.jsp" %>
          <script src="js/autocomplete.min.js"></script>
                  <title>consulta User jsp</title>          
<meta name="viewport" content="width=device-width, initial-scale=1">

 </head>
<body>
    

<script>
        myData = [];
        console.log("prueba1");
        $( document ).ready(function() {
            console.log("prueba2");          
              cargarBuscador();
              console.log("prueba2");          

          $( "#btnBuscarUsuario" ).click(function() {
              listarDatosUsuario();
              listarVideos();
              listarListas();
              
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
                             window.idUsr = usuarios[i].id;
                        html += "<tr>";
                        html += '<th scope="row">'+usuarios[i].nombre+ ' '+usuarios[i].apellido+'</th>';
                      
                        html += '<td>'+usuarios[i].Subcriptores+ '  subscriptores</td>';
                     
                        html += "</tr>";
                     $('.table tbody').html(html);
                        var img = document.getElementById("imgU");
                        img.src = usuarios[i].imagen; //aca va img.src = usuarios[i].imagen;
                        img.width = "30";
                        img.height = "30";
                        img.alt="User Picture";
                        var src = document.getElementById("usrImg");
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
                                   var nick = document.getElementById("txtBoxSelected").value;
                                   for (let i = 0; i < videos.length; i++) {
                                    if (nick == videos[i].user){
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
                               }
                                   $('.table2 tbody').html(html);
                               },
                               error: function (xhr, ajaxOptions, thrownError) {
                                 console.log(xhr.status);
                                 console.log(thrownError);
                                 $('.table2 tbody').html('<span style="color:red">obtenerVideos.jsp esta tirando error 500 cabeza. pa mi que te falto importar la libreria de json.</span>');
                               }
                           });
        }
        
        
        
        function listarListas() {
            //console.log(window.idUsr);
                           $.ajax({
                               url:"http://localhost:8080/WebApplication/api/obtenerListas.jsp?user_id="+window.idUs,
                               success:function(response){   
                                   //console.log(response);
                                   let listas = jQuery.parseJSON(response);
                                   let html = "";
                                   for (let i = 0; i < listas.length; i++) {
                                       html += "<tr>";
                                       html += '<td>'+listas[i].nombre+'</td>';
                                       html += '<td>'+listas[i].categoria+'</td>';                                      
                                       html += "</tr>";
                                        //console.log(window.idUsr);
                                        
                                   }
                                },
                               error: function (xhr, ajaxOptions, thrownError) {
                                 console.log(xhr.status);
                                 console.log(thrownError);
                                 $('.table3 tbody').html('<span style="color:red">obtenerListas.jsp esta tirando error 500 cabeza. pa mi que te falto importar la libreria de json.</span>');
                               }
                                
                           });
        }
        
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
                             <li class="nav-item px-2"> Privacidad: 
                      <input id="toggle-privacidad" type="checkbox" data-toggle="toggle" data-onstyle="dark" data-on="Público" data-off="Privado">
                    </li>
            </div> 
                    </div> 
                            </div> 
                
                                    </div>
                        
      <ul class="nav nav-tabs" id="myTab" role="tablist">
  <li class="nav-item">
    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Home</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Profile</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="false">Contact</a>
  </li>
</ul>
<div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                      <table class="table2" id="tblVideos">
                        <thead>
                          <tr>
                           <th scope="col">Nombre</th>
                           <th scope="col">Categoria</th>
                          </tr>
                        </thead>
                        <tbody>
                        </tbody>
                      </table>
                </div>
    <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
        <table class="table3" id="tblVideos">
                        <thead>
                          <tr>
                            <th class="thumbnail"></th>
                            <th class="titulo" scope="col">Título</th>
                            <th class="descripcion">Descripcion</th>
                            <th class="canal">Canal</th>
                          </tr>
                        </thead>
                        <tbody>
                        </tbody>
                      </table>
    </div>
  <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">...</div>
</div>             
                 
   
</main>
      <%@include file="include/footer.jsp" %>
</body>