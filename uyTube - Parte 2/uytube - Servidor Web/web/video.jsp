<%-- 
    Document   : video
    Created on : Oct 8, 2019, 12:48:34 AM
    Author     : Luciano
--%>

<%@page import="logica.dt.valoracionDt"%>
<%@page import="java.util.List"%>
<%@page import="javax.swing.tree.DefaultMutableTreeNode"%>
<%@page import = "javax.persistence.*"%>
<%@page import = "logica.controladores.Fabrica"%>
<%@page import = "logica.controladores.IControladorUsuario"%>
<%@page import = "logica.controladores.IControladorVideo"%>
<%@page import = "logica.dt.VideoDt" %>
<%@page import = "logica.dt.UsuarioDt" %>

<%@page import="java.util.Date"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/video.css">
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.min.js"></script>
        
        <!-- Font Awesome -->
        <link href="css/fontawesome.min.css" rel="stylesheet">
        <script defer src="js/solid.js"></script>
        <script defer src="js/fontawesome.js"></script>
        <%-- Popper --%>
        <script src="js/popper.min.js"></script>
        
        <title>uyTube - Transmite tú mismo</title>
    </head>
    <body>
        <% Fabrica f = Fabrica.getInstance();
            IControladorUsuario user = f.getIControladorUsuario();
            IControladorVideo video = f.getIControladorVideo();
            int video_id = Integer.parseInt(request.getParameter("id"));
            session.setAttribute("videoid", video_id);
            VideoDt dt = video.obtenerVideoDtPorID(video_id);
            UsuarioDt u = user.ConsultarUsuario(dt.getIdCanal());
            List<String> seguidores = user.ListarSeguidores(dt.getId());
            
            String path = request.getContextPath();
            String imagenUser = "img/user.png";
            if(u.getImagen() != null) imagenUser = path+"/images/"+u.getImagen();
            
            Boolean estaSuscripto = false; //inicializo
            if(session.getAttribute("userid") != null) estaSuscripto = user.estaSuscripto((int)session.getAttribute("userid"), u.getId());
        %>
        <%@ include file="include/header.jsp" %>

        <script>var video_id="<%=video_id%>";</script>
        <script>
            $(document).ready(function () {
                cargarListas();
                cargarComentarios();
            });
            
            function cargarComentarios() {
                $.ajax({
                    url: "<%=path%>/api/obtenerComentariosVideo.jsp?id_v="+'<%=video_id%>', 
                   success: function (result) {
                       var html = result;
                       $('#comentarios').html(html);
                   },
                   error: function (xhr, ajaxOptions, thrownError) {
                      console.log(xhr.status);
                      console.log(thrownError);
                   } 
                });
            }
            
            function comentarVideo(id_p) {
                //-1 = no padre
                var id_u = '<%=session.getAttribute("userid")%>';
                var id_v = '<%=video_id%>';   
                var text = $('#textArea'+id_p).val();
                if(id_u !== null && id_u !== "null") {
                    $.ajax({
                        url: "<%=path%>/api/comentarVideo.jsp?id_u="+id_u+"&id_v="+id_v+"&id_p="+id_p+"&text="+text, 
                        success: function (result) {
                            alert(result);
                        },
                        error: function (xhr, ajaxOptions, thrownError) {
                            console.log(xhr.status);
                            console.log(thrownError);
                        } 
                    });
                } else {
                    alert("Por favor inicie sesión para comentar el video");
                }
            } 
            
            function cargarListas() {
                var usuario_actual = '<%=session.getAttribute("userid")%>';

                if(usuario_actual !== null && usuario_actual !== "null") {
                    var listasDropdown = document.getElementById('listas-menu');
                    $.ajax({
                        url:'<%=path%>/api/obtenerListas.jsp?user_id='+usuario_actual,
                        success: function (data) {
                            let listas = jQuery.parseJSON(data);
                            let html = "";
                            for (let i = 0; i < listas.length; i++) {
                                var icono = "fas fa-list";
                                if(listas[i].nombre === "Ver mas tarde" || listas[i].nombre === "Ver más tarde") icono = "fas fa-redo-alt";
                                if(listas[i].nombre === "Favoritos") icono = "fas fa-redo-alt";
                                
                                var funcion = "agregarVideoLista('"+listas[i].nombre+"');";
                                html += '<a class="dropdown-item" href="#" onclick="'+funcion+'"><i class="'+icono+'"></i>'+listas[i].nombre+'</a>';
                            }

                            listasDropdown.innerHTML = html;
                        },
                        error: function (xhr, ajaxOptions, thrownError) {
                            console.log(xhr.status);
                            console.log(thrownError);
                        }
                    });
                } else conectate();
            }         

            function agregarVideoLista(nom_lista) {
                var id_propietario = '<%=session.getAttribute("userid")%>';

                if(id_propietario !== null && id_propietario !== "") {
                    if(nom_lista !== null && nom_lista !== "") {
                        $.ajax({
                            url:'<%=path%>/api/modificarLista.jsp?accion=agregar&id_p='+id_propietario+'&nom='+nom_lista+'&id_uv='+'<%=dt.getIdCanal()%>'+'&nomv='+'<%=dt.getNombre()%>',
                            success: function (result) {
                                alert(result);
                            },
                            error: function (xhr, ajaxOptions, thrownError) {
                              console.log(xhr.status);
                              console.log(thrownError);
                            }
                        });
                    } else console.log("Error - Agregar Video: nombre de lista vacio");
                } else conectate();
            }
        </script>
        
        <div id="video-show">
            <%=dt.getEmbedded()%>
        </div>
        <div id="video-data">
            <h1 id="video-titulo"><%=dt.getNombre()%></h1>
            
            <div class="row">
                <div class="col-sm-5">
                    <img id="user-pic" src="<%=imagenUser%>" height="30px" width="30px" alt="Profile de Usuario"/>
                    <span id="user-nick"><%=u.getNickname()%></span>
                    <button class="btn btn-outline-dark" onclick="suscripcion(<%=u.getId()%>)">
                        <%if(!estaSuscripto){%>
                        Suscribirse
                        <%}else{%>
                        Desuscribirse
                        <%}%>
                    </button>
                    <span id="user-subs"><%=seguidores.size()%> seguidores</span>
                    <script>
                    function conectate() {
                      alert("Por favor, para realizar esta acción inicie sesion.");
                    }
                    function suscripcion(seguido) {
                      var seguidor = '<%=session.getAttribute("userid")%>';
                      if(seguidor === null) conectate();
                      else{
			$.ajax({
                            url: "<%=path%>/api/suscripcion.jsp?seguidor="+seguidor+"&seguido="+seguido,
                            success: function() {
                                alert("Suscripción/Desuscripción exitosa");
                            },
                            error: function () { alert("Error en la suscripción");}
                        });
                      }
                    }
                    </script>
                </div>
                
                <div class="col">
                    <button class="btn btn-outline-secondary" type="button" id="listaDropdownbtn" data-toggle="dropdown" data-target="listas-menu" aria-haspopup="true" aria-expanded="false">
                        <a class="fas fa-plus"> </a> Lista de Reproducción
                    </button>
                    <div id="listas-menu" class="dropdown-menu" aria-labelledby="listaDropdownbtn">
                        <%-- Aca van las listas --%>
                        <a class="dropdown-item" href="login.jsp">Inicie sesión</a>
                    </div>
                    
                    <button class="btn btn-outline-success my-2 my-sm-0" onclick="gustar(true)"><i class="fa fa-thumbs-up"></i>
                    <span><b></b> <%=dt.getLikes()%></span></button>
                    <button class="btn btn-outline-success my-2 my-sm-0" onclick="gustar(false)"><i class="fa fa-thumbs-down"></i>
                    <span><b></b> <%=dt.getDislikes()%></span></button>
                    <script>
                        function gustar(g) {
                            var user = '<%=session.getAttribute("userid")%>';
                            var video = '<%=video_id%>';
                            
                            if(user === null) conectate();
                            else{
                                $.ajax({
                                    url: "<%=path%>/api/valoracion.jsp?user_id="+user+"&video_id="+video+"&gusta="+g,
                                    success: function() {
                                        alert("Valoración exitosa");
                                    },
                                    error: function () { alert("Error en la valoración del video");}
                                });
                            }
                        }
                    </script>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-sm-6">
                    <h5> Descripción</h5>
                    <p id="video-desc"><%=dt.getDescripcion()%></p>
                </div>
                <div class="col-sm-3">
                    <p id="video-cat"><b>Categoría:</b> <%=dt.getCategoria()%></p>
                    <p id="video-duracion"><b>Duración:</b> <%=dt.getDuracion()%> minutos</p>
                    <p id="video-fecha"><b>Fecha:</b> <%=dt.getFechaPublicacion().toString()%></p>
                </div>
            </div>
            <div class="row">
                <div class="col">
                        <h5> Comentarios </h5>
                    <div class="form-group row">
                        <div class="col-sm-6">
                            <textarea class="form-control" rows="2" placeholder="Agregue un comentario..." required></textarea>
                        </div>
                        <button type="button" class="btn btn-success" onclick="comentarVideo(-1)">Comentar</button>
                    </div>
                    <div class="card">
                        <div id="comentarios" class="card-body">
                            <%-- Muchos comentarios --%>
                        </div>
                    </div>
                </div>
            </div>
        <%@ include file="include/footer.jsp" %>
    </body>
</html>
