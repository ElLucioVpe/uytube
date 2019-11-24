<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import = "javax.persistence.*"%>
<%@page import = "logica.webservices.VideoDt" %>
<%@page import = "logica.webservices.UsuarioDt" %>
<%@page import = "java.io.File" %>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.net.URL"%>
<%@page import="java.net.URLClassLoader"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Date"%>

<%@page import = "logica.webservices.WScontroladorUsuarioImplService"%>
<%@page import = "logica.webservices.WScontroladorUsuario"%>
<%@page import = "logica.webservices.WScontroladorVideoImplService"%>
<%@page import = "logica.webservices.WScontroladorVideo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="../css/video.css">
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/jquery.min.js"></script>
        
        <!-- Font Awesome -->
        <link href="../css/fontawesome.min.css" rel="stylesheet">
        <script defer src="../js/solid.js"></script>
        <script defer src="../js/fontawesome.js"></script>
        <!-- Popper -->
        <script src="../js/popper.min.js"></script>

        
        <title>uyTube - Transmite tú mismo</title>
    </head>
    <body>
        <% 
	        WScontroladorUsuario user = new WScontroladorUsuarioImplService().getWScontroladorUsuarioImplPort();
	    	WScontroladorVideo video = new WScontroladorVideoImplService().getWScontroladorVideoImplPort();
            String path = request.getContextPath();
            int video_id = -1;
            VideoDt dt = null;
            
            String embedded = null;
            if(request.getParameter("id") != null) {
	            video_id = Integer.parseInt(request.getParameter("id"));
	            dt = video.obtenerVideoDtPorID(video_id);
	            session.setAttribute("videoid", video_id);
	            embedded = dt.getEmbedded();
            } else {
            	if(request.getParameter("cod") != null) {
            		dt = video.obtenerVideoDtPorCOD(request.getParameter("cod"));
            		video_id = dt.getId();
            		session.setAttribute("videoid", video_id);
            		embedded = dt.getEmbedded();
            	}
            }
            session.setAttribute("embedded", embedded);
            session.setAttribute("embedded-time", null);
            //Evito crasheo
            if(dt == null || dt.getNombre() == null) {
            	video_id = 1;
            	session.setAttribute("videoid", video_id);
            	dt = video.obtenerVideoDtPorID(video_id);
            	response.sendRedirect(path+"/index.jsp");
            }

            UsuarioDt u = user.consultarUsuario(dt.getIdCanal());
            List<String> seguidores = user.listarSeguidores(dt.getIdCanal()).getLista();
            
            File properties = new File(System.getProperty("user.home")+"/.UyTube");
        	URL[] urls = {properties.toURI().toURL()};
        	ClassLoader loader = new URLClassLoader(urls);
        	ResourceBundle bundle = ResourceBundle.getBundle("uytube_conf", Locale.getDefault(), loader);
            
        	//Obtengo url para compartir
		    String url_compartir = bundle.getString("url")+dt.getCodigo();
        	//Obtengo url de imagenes
		    String images = bundle.getString("url_images");
        	
            String imagenUser = "../img/user.png";
            if(u.getImagen() != null) imagenUser = images+u.getImagen();
            
            Boolean estaSuscripto = false; //inicializo
            if(session.getAttribute("userid") != null) estaSuscripto = user.estaSuscripto((int)session.getAttribute("userid"), u.getId());
            
            //Veo si dio like o dislike
            boolean puntuo = false;
            boolean puntuacion = false;
            if(session.getAttribute("userid") != null) {
            	int userid = (int) session.getAttribute("userid");
            	String valoro = video.dioValoracion(userid, video_id);
            	if(valoro.equals("Like")) {
            		puntuo = true;
            		puntuacion = true;
            	}
            	if(valoro.equals("Dislike")) puntuo = true;
            }
            
        %>
        <%@ include file="include/navbar.jsp" %>

        <script>var video_id="<%=video_id%>";</script>
        <script src="https://www.youtube.com/iframe_api"></script>
		<script type="text/javascript">

			var player;
			function onYouTubeIframeAPIReady() {
			  player = new YT.Player('player', {
			    events: {
			      'onReady': onPlayerReady,
			      'onStateChange': onPlayerStateChange
			    }
			  });
			}
		
			function onPlayerReady() {
				//nada
			}
		
			function onPlayerStateChange() {
			  //console.log("cambio el estado");
			}
			
			$(window).on('beforeunload', function() { 
				var time = player.getCurrentTime();
				console.log(time);
				$.ajax({
		            url: "<%=request.getContextPath()%>/api/embeddedVideo.jsp?emb="+'<%=embedded%>'+"&time="+time,
		            success: function(){},
		            error: function() { alert("Error inesperado al modificar el reproductor");}
		        });
			});
	
            $(document).ready(function () {
                cargarListas();
                cargarComentarios();
            });
            
            
            function cargarComentarios() {
                $.ajax({
                    url: "<%=path%>/api/obtenerComentariosMovil.jsp?id_v="+'<%=video_id%>', 
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
                            cargarComentarios();
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
        
	   <div class="row" id="video-show">
		   	<iframe id="player" width="560" height="315" src="<%=dt.getEmbedded()%>?enablejsapi=1" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
	    </div>
        <div id="video-data">
            <h1 id="video-titulo"><%=dt.getNombre()%></h1>
            
            <div class="row">
                <div class="col">
                    <img id="user-pic" src="<%=imagenUser%>" height="30px" width="30px" alt="Profile de Usuario"/>
                    <span id="user-nick"><%=u.getNickname()%></span>
                    <button id="btn-suscripcion" class="btn btn-outline-dark" onclick="suscripcion(<%=u.getId()%>)">
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
						var seguidor = <%=session.getAttribute("userid")%>;
                        
                        if(seguidor === null) conectate();
                        else{
  						$.ajax({
                              url: "<%=path%>/api/suscripcion.jsp?seguidor="+seguidor+"&seguido="+seguido,
                              success: function() {
                                  alert("Suscripción/Desuscripción exitosa");
                              },
                              error: function () { alert("Error en la suscripción");}
                          });
  						  recargarSubs();
                        }
                    }
                    
                    var estado = <%=estaSuscripto%>;
                    var subs = <%=user.listarSeguidores(dt.getIdCanal()).getLista().size()%>;
                    function recargarSubs() {

                        var boton = document.getElementById("btn-suscripcion");
                        if(estado){
                        	estado = false;
                        	boton.innerHTML = "Suscribirse";
                        	subs-=1;
                        }
                        else { 
                        	estado = true;
                        	boton.innerHTML = "Desuscribirse";
                        	subs+=1;
                        }
                        document.getElementById("user-subs").innerHTML = subs + " seguidores";
                    }
                    </script>
                    
                </div>
            </div>
                
          	<div class="row">
                <div class="col">
                    <button class="btn btn-outline-success my-2 my-sm-0" onclick="gustar(true)"><i class="fa fa-thumbs-up"></i>
                    <span id="likes"><b></b> <%=dt.getLikes()%></span></button>
                    <button class="btn btn-outline-success my-2 my-sm-0" onclick="gustar(false)"><i class="fa fa-thumbs-down"></i>
                    <span id="dislikes"><b></b> <%=dt.getDislikes()%></span></button>
                    
                    <div class="btn-group">
	                    <button class="btn btn-outline-secondary" type="button" id="listaDropdownbtn" data-toggle="dropdown" data-target="listas-menu" aria-haspopup="true" aria-expanded="false">
	                        <i class="fas fa-plus"> </i>
	                    </button>
	                    <div id="listas-menu" class="dropdown-menu" aria-labelledby="listaDropdownbtn">
	                        <%-- Aca van las listas --%>
	                        <a class="dropdown-item" href="login.jsp">Inicie sesión</a>
	                    </div>
                    </div>
                    
                    <div class="btn-group">
	                    <button class="btn btn-outline-secondary" type="button" id="shareDropdownbtn" data-toggle="dropdown" data-target="share-menu" aria-haspopup="true" aria-expanded="false">
	                        <i class="fas fa-share"></i>
	                    </button>
	                    <div id="share-menu" class="dropdown-menu dropdown-menu-right" aria-labelledby="shareDropdownbtn">
	                        <a class="dropdown-item">
	                        	<textarea id="share-url" readonly><%=url_compartir%></textarea> 
	                        	<button class="btn btn-outline-dark" onclick="copiarURL()"><i class="fas fa-copy"></i></button>
	                    	</a>
	                    </div>
                    </div>
                    <script>
                        function gustar(g) {
                            var user = <%=session.getAttribute("userid")%>;
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
                                recargarValoraciones(g);
                            }
                        }
                        
                        var likes = <%=dt.getLikes()%>;
                     	var dislikes = <%=dt.getDislikes()%>;
                     	var puntuo = <%=puntuo%>;
                     	var puntuacion = <%=puntuacion%>;
                        function recargarValoraciones(g) {
                        	console.log(puntuo);
							if(g) {
								if(!puntuo) likes++;
								if(puntuo && !puntuacion) {
									likes++;
									dislikes--;
								}
								puntuacion = true;
							} else {
								if(!puntuo) dislikes++;
								if(puntuo && puntuacion) {
									dislikes++;
									likes--;
								}
								puntuacion = false;
							}
							puntuo = true;
							
                            document.getElementById("likes").innerHTML = likes
                            document.getElementById("dislikes").innerHTML = dislikes;
                        }
                        
                        function copiarURL() {
                       		var urlText = document.getElementById("share-url");
                       		urlText.select();
                        	document.execCommand("copy");

                        	alert("URL Copiada al portapapeles: " + urlText.value);
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
                    <p id="video-fecha"><b>Fecha:</b> <%=new SimpleDateFormat("yyyy-MM-dd").format(dt.getFechaPublicacion().toGregorianCalendar().getTime())%></p>
                </div>    
            </div>
            
            <div class="row" style="margin: 0 auto; background: #7EA16B;">
	            <h5 style="margin: 0 auto;" onclick="muestroCom()">Ver Comentarios</h5>
	            
	            <script>
	            	function muestroCom() {
	            		var com = document.getElementById("divcom");
	            		if (com.style.display === "none") {
	            			com.style.display = "block";
	            		} else {
	            			com.style.display = "none";
	            		}
	            	}
	            </script>
            </div>
            
                <div id="divcom" class="col" style="display: none;">
                    <div class="form-group row">
                        <div class="col-sm-6">
                            <textarea id="textArea-1" class="form-control" rows="2" placeholder="Agregue un comentario..." required></textarea>
                        </div>
                        <button type="button" class="btn btn-success" onclick="comentarVideo(-1)">Comentar</button>
                    </div>
                    <div class="card">
                        <div id="comentarios" class="card-body" style="overflow: scroll;">
                            <%-- Muchos comentarios --%>
                        </div>
                    </div>
                </div>
        </div>
    </body>
</html>