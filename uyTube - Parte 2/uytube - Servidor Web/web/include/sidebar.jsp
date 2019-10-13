<%-- 
    Document   : sidebar
    Created on : 11 oct. 2019, 20:21:50
    Author     : Esteban
--%>
<%@page import="java.util.List"%>
<%@page import = "org.json.JSONArray"%>
<!-- Our Custom CSS -->
<link rel="stylesheet" href="css/sidebar.css">
<!-- Scrollbar Custom CSS -->
<link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css">

        <div class="wrapper">
            <!-- Sidebar Holder -->
            <nav id="sidebar">
                <div id="dismiss">
                    <i class="fas fa-arrow-left"></i>
                </div>

                <div class="sidebar-header">
                    <h3>Menu</h3>
                </div>

                <ul class="list-unstyled components">
                    <ul>
                      <li><a href="http://localhost:8080/WebApplication/consultarUser.jsp"><i class="fa fa-user"></i> Mi Canal</a></li>
                    </ul>
                    <ul id="videos">
                      <li class="title">VIDEOS</li>
                      <li><a href="http://localhost:8080/WebApplication/altaVideo.jsp"><i class="fa fa-plus"></i> Subir video</a></li>
                      <li><a href="http://localhost:8080/WebApplication/"><i class="fa fa-film"></i> Ver videos</a></li>
                    </ul>
                    <ul id="playlists">
                      <li class="title">LISTAS</li>
                      
                    </ul>
                    <ul id="cat">
                        <li class="title">CATEGORIAS</li>
                    </ul>
                </ul> <!-- End list -->
            </nav>
        </div>
		


        <div class="overlay"></div>


        <!-- Popper.JS -->
        <script src="js/popper.min.js"></script>
        <!-- jQuery Custom Scroller CDN -->
        <script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
		
        <script>
            $(document).ready(function () {
                listarListasDeReproduccion();
                listarCategorias();
            });
            
            function listarListasDeReproduccion() {
                var lists = document.getElementById("playlists");
                
                var sidebar_user_id = '<%=session.getAttribute("userid")%>';
                
                if(sidebar_user_id === null  || sidebar_user_id === "null") //Y posta hizo la de asignar null como string
                    lists.innerHTML += '<li><a href="http://localhost:8080/WebApplication/login.jsp">Inicie sesión para ver sus listas</a></li>';
                else lists.innerHTML += '<li><a href="http://localhost:8080/WebApplication/crearListaDR.jsp"><i class="fa fa-plus"></i> Crear Lista</a></li>';
                
                $.ajax({
                    url:'http://localhost:8080/WebApplication/api/obtenerListas.jsp?user_id='+sidebar_user_id,
                    success:function(data){   
                        let listas = jQuery.parseJSON(data);
                        let html = "";
                        for (let i = 0; i < listas.length; i++) {
                            var icono = "fas fa-list";
                            if(listas[i].nombre == "Ver mas tarde" || listas[i].nombre == "Ver más tarde") icono = "fas fa-redo-alt";
                            if(listas[i].nombre == "Favoritos") icono = "fas fa-redo-alt";
                            
                            html += '<li><a href="http://localhost:8080/WebApplication/buscador.jsp?lista='+listas[i].id+'"><i class="'+icono+'"></i> '+listas[i].nombre+'</a></li>';
                        }
                        lists.innerHTML += html;
                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                      console.log(xhr.status);
                      console.log(thrownError);
                    }
                });
            }
            
            function listarCategorias() {
                var cats = document.getElementById("cat");
                $.ajax({
                    url:"http://localhost:8080/WebApplication/api/obtenerCategorias.jsp",
                    success:function(data){   
                        let categorias = jQuery.parseJSON(data);
                        let html = "";
                        for (let i = 0; i < categorias.length; i++) {
                            html += '<li><a href="http://localhost:8080/WebApplication/buscador.jsp?cat='+categorias[i].nombre+'"><i class="fas fa-circle"></i> '+categorias[i].nombre+'</a></li>';
                        }
                        cats.innerHTML += html;
                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                      console.log(xhr.status);
                      console.log(thrownError);
                    }
                });
            }

        </script>
        <script type="text/javascript">
            $(document).ready(function () {
                $("#sidebar").mCustomScrollbar({
                    theme: "minimal"
                });

                $('#dismiss, .overlay').on('click', function () {
                    $('#sidebar').removeClass('active');
                    $('.overlay').fadeOut();
                });

                $('#sidebarCollapse').on('click', function () {
                    $('#sidebar').addClass('active');
                    $('.overlay').fadeIn();
                    $('.collapse.in').toggleClass('in');
                    $('a[aria-expanded=true]').attr('aria-expanded', 'false');
                });
            });
        </script>
