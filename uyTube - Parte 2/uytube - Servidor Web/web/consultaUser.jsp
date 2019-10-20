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
     <% 
        Fabrica f = Fabrica.getInstance();
        IControladorUsuario user = f.getIControladorUsuario();
        
        Boolean estaSuscripto = false; //inicializo
        int canalUser_id = Integer.parseInt(request.getParameter("id"));
        session.setAttribute("canalUserid", canalUser_id);
        UsuarioDt u = user.ConsultarUsuario(canalUser_id);
            Boolean estaSuscripto2 = false; 
             List<String> seguidores = user.ListarSeguidores(canalUser_id);
            if(session.getAttribute("userid") != null) estaSuscripto2 = user.estaSuscripto((int)session.getAttribute("userid"), u.getId());
            
%>
<script>var canalUser_id="<%=canalUser_id%>";</script>     
<script>
        myData = [];
        idUsr = 0; 
        console.log("prueba1");
        $( document ).ready(function() {
            console.log("prueba2");          
              //cargarBuscador();
              console.log("prueba2");          
               listarDatosUsuario();
               listarListas();
               listarVideos();
               listarSeguidores();
                listarSeguidos();
          /*$( "#btnBuscarUsuario" ).click(function() {
             
              listarVideos();
              listarListas();
              
              // alert("button was clicked");
            });*/ 
        });
        
      /*  function cargarBuscador() {
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
        }*/
          
        
        function listarDatosUsuario() {
            $.ajax({
                url:"http://localhost:8080/WebApplication/api/obtenerUsuarios.jsp",
                success:function(response){   
                    //console.log(response);
                    let usuarios = jQuery.parseJSON(response);
                    let html = "";
                    //console.log(usuarios);
                    //console.log(usuarios[0].mail);
                    var usrid = '<%=canalUser_id%>';   

                    
                    for (let i = 0; i < usuarios.length; i++) {
                        if ( usrid == usuarios[i].id){
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
                                    var usrid = '<%=canalUser_id%>'; 
                                     console.log("la id es:")
                                     
                                     
                                    for (let i = 0; i < videos.length; i++) {
                                    if ( videos[i].canal_user_id == usrid ){
                                           console.log("la id es:");

                                        console.log(videos[i].canal_user_id);        
                                       html += "<tr>";
                                       if(videos[i].thumbnail != "") {
                                           html += '<th><img src="'+videos[i].thumbnail+'" alt="Thumbnail"></th>';
                                       } else {
                                           html += '<th><img src="img/no-thumbnail.jpg" alt="Thumbnail"></th>';
                                       }
                                       html += '<td  scope="row"> <a href="video.jsp?id='+ videos[i].id +'">'+videos[i].nombre+'</a></td>';
                                       
                                       html += '<td>'+videos[i].descripcion+'</td>';
                                       html += '<td>'+videos[i].user+'</td>';
/*aca esta el boton de modifica video*/html += '<td> <a href=http://localhost:8080/WebApplication/modificarVideo.jsp?nombre='+videos[i].nombre+'&desc='+videos[i].descripcion+'&url='+ videos[i].url+'&categoria='+videos[i].categoria +'&datepicker='+ videos[i].fecha+'&minutos='+ videos[i].minutos+'&segundos='+ videos[i].minutos+'"><button class="fas fa-edit" ></button></a></td>';
                                       //href=http://localhost:8080/WebApplication/modificarVideo.jsp?nombre='+videos[i].id +'&desc='+videos[i].descripcion+'&url='+ videos[i].url+'&categoria='+videos[i].categoria +'&datepicker='+ videos[i].fecha+'&minutos='+ videos[i].minutos+'&segundos='+ videos[i].minutos+'"
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
            
                           $.ajax({
                               url:"http://localhost:8080/WebApplication/api/obtenerListas.jsp?user_id="+'<%=canalUser_id%>',
                               success:function(response){   
                                   //console.log(response);
                                   let listas = jQuery.parseJSON(response);
                                   let html = "";
                                   console.log(listas[0].nombre);
                                   for (let i = 0; i < listas.length; i++) {
                                       html += "<tr>";
                                       html += '<td scope="row"<a href="http://localhost:8080/WebApplication/consultarListaDR.jsp?user_id='+listas[i].user_id +'&nom='+listas[i].nombre+'">'+listas[i].nombre+'</a></td>';
                                       html += '<td><a href="http://localhost:8080/WebApplication/consultarListaDR.jsp?user_id='+listas[i].user_id +'&nom='+listas[i].nombre+'">'+listas[i].categoria+'</td>';                                      
                                       html += "</tr>";
                                        
                                        
                                   }
                                   $('.table3 tbody').html(html);
                                },
                               error: function (xhr, ajaxOptions, thrownError) {
                                 console.log(xhr.status);
                                 console.log(thrownError);
                                 console.log(window.idUsr);
                                 $('.table3 tbody').html('<span style="color:red">obtenerListas.jsp esta tirando error 500 cabeza. pa mi que te falto importar la libreria de json.</span>');
                               }
                                
                           });
        }
          function listarSeguidores() {
            
                           $.ajax({
                               url:"http://localhost:8080/WebApplication/api/obtenerSeguidores.jsp?id="+'<%=canalUser_id%>',
                               success:function(response){   
                                   //console.log(response);
                                   let listas = jQuery.parseJSON(response);
                                   let html = "";
                                   console.log(listas[0].nombre);
                                   for (let i = 0; i < listas.length; i++) {
                                       html += "<tr>";
                                       html += '<td scope="row">'+listas[i].nickname+'</td>';                                                                 
                                       html += "</tr>";                                                                             
                                   }
                                   $('.table4 tbody').html(html);
                                },
                               error: function (xhr, ajaxOptions, thrownError) {
                                 console.log(xhr.status);
                                 console.log(thrownError);
                                 console.log(window.idUsr);
                                 $('.table4 tbody').html('<span style="color:red">obtenerListas.jsp esta tirando error 500 cabeza. pa mi que te falto importar la libreria de json.</span>');
                               }
                                
                           });
        }
         function listarSeguidos() {
            
                           $.ajax({
                               url:"http://localhost:8080/WebApplication/api/obtenerSeguidos.jsp?id="+'<%=canalUser_id%>',
                               success:function(response){   
                                   //console.log(response);
                                   let listas = jQuery.parseJSON(response);
                                   let html = "";
                                   console.log(listas[0].nombre);
                                   for (let i = 0; i < listas.length; i++) {
                                       html += "<tr>";
                                       html += '<td scope="row">'+listas[i].nickname+'</td>';                                                                 
                                       html += "</tr>";                                                                             
                                   }
                                   $('.table5 tbody').html(html);
                                },
                               error: function (xhr, ajaxOptions, thrownError) {
                                 console.log(xhr.status);
                                 console.log(thrownError);
                                 console.log(window.idUsr);
                                 $('.table5 tbody').html('<span style="color:red">obtenerListas.jsp esta tirando error 500 cabeza. pa mi que te falto importar la libreria de json.</span>');
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

                                         <!-- <div style="float: left; width: 450px;">
                                      <input type="text" id= "txtBoxSelected" class="demo">
                                        <input type="hidden" id="hidden-field">
                                        </div>
                                         <div style="float: left; width: 200px;">
                                         <button type="button" class="btn btn-primary" id="btnBuscarUsuario">Buscar Usuario</button>
                                        </div>
                                        </div>-->
                                        
                                        <script>
                                               function conectate() {
                                                 alert("Por favor, para realizar esta acción inicie sesion.");
                                               }
                                               function suscripcion(seguido) {
                                                 var seguidor = '<%=session.getAttribute("userid")%>';
                                                 if(seguidor === null) conectate();
                                                 else{
                                                   $.ajax({
                                                       url: "/WebApplication/api/suscripcion.jsp?seguidor="+seguidor+"&seguido="+seguido,
                                                       success: function() {
                                                           alert("Suscripción/Desuscripción exitosa");
                                                       },
                                                       error: function () { alert("Error en la suscripción");}
                                                   });
                                                 }
                                               }
                                        </script>

                                        <div class="container" id="canalInfo">


                                            <div id =usrImg style="float: center; width: 450px;">
                                            <div style="float: left;">
                                                 <img id ="imgU" class ="rounded-circle">
                                            </div>
                                            <div style="float: right;"> 
                                             <p id="usrNmb"></p> 
                                            </div> 
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
                                            <div>
                                                <div style="float: right;">
                                            <button class="btn btn-outline-dark" onclick="suscripcion(<%=u.getId()%>)">
                                                <%if(!estaSuscripto2){%>
                                                Suscribirse
                                                <%}else{%>
                                                Desuscribirse
                                                <%}%>
                                            </button>
                                            </div>
                                            <div style="float: left;">
                                            <li class="nav-item px-2"> Privacidad: 
                                              <input id="toggle-privacidad" type="checkbox" data-toggle="toggle" data-onstyle="dark" data-on="Público" data-off="Privado" data-width="150">
                                            </li>
                                            </div>
                                            </div>
                                            </div>
                             
            </div> 
                    </div> 
                            </div> 
                
                                    </div>
                        
      <ul class="nav nav-tabs" id="myTab" role="tablist">
  <li class="nav-item">
    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Videos</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Listas</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="false">Seguidores</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" id="seguidos-tab" data-toggle="tab" href="#seguidos" role="tab" aria-controls="seguidos" aria-selected="false">Seguidos</a>
  </li>
</ul>
<div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                      <table class="table2" id="tblVideos">
                        <thead>
                          <tr>
                            <th class="thumbnail"></th>
                            <th class="titulo" scope="col">Título</th>
                            <th class="descripcion">Descripcion</th>
                            <th class="canal">Canal</th>
                            <th class="but"></th>

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
                              <th scope="col">Nombre</th>
                           <th scope="col">Categoria</th>
                           
                          </tr>
                        </thead>
                        <tbody>
                        </tbody>
                      </table>
    </div>
  <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">  
      <table class="table4" id="tblVideos">
                        <thead>
                          <tr>
                              <th scope="col">Nombre</th>                      
                           
                          </tr>
                        </thead>
                        <tbody>
                        </tbody>
                      </table>
    </div>
    <div class="tab-pane fade" id="seguidos" role="tabpanel" aria-labelledby="seguidos-tab">  
      <table class="table5" id="tblVideos">
                        <thead>
                          <tr>
                              <th scope="col">Nombre</th>                      
                           
                          </tr>
                        </thead>
                        <tbody>
                        </tbody>
                      </table>
    </div>
</div>             
                 
   
</main>
      <%@include file="include/footer.jsp" %>
</body>