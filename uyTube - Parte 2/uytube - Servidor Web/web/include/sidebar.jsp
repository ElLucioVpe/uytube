<%-- 
    Document   : sidebar
    Created on : 11 oct. 2019, 20:21:50
    Author     : Esteban
--%>
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
                      <li><a href="#"><i class="fa fa-user"></i> Mi Canal</a></li> <!-- href=".....consultarUser.jsp" -->    
                    </ul>
                    <ul id="videos">
                      <li class="title">VIDEOS</li>
                      <li><a href="#"><i class="fa fa-plus"></i> Subir video</a></li> <!-- href=".....altaVideo.jsp" --> 
                      <li><a href="http://localhost:8080/WebApplication/"><i class="fa fa-film"></i> Ver videos</a></li>
                    </ul>
                    <ul id="playlists">
                      <li class="title">LISTAS</li>
                      <li><a href="#"><i class="fa fa-plus"></i> Crear Lista</a></li>
                      <!-- Cargar listas genericas-->
                      <li><a href="#"><i class="fa fa-film"></i> Me gusta</a></li>
                      <li><a href="#"><i class="fa fa-repeat"></i> Ver mas tarde</a></li>
                      <!-- Cargar listas particulares-->
                      
                    </ul>
                    <ul id="cat">
                      <li class="title">CATEGORIAS</li> 
                      <!-- Cargar categorias-->
                      <li><a href="#"> 1</a></li>
                      <li><a href="#"> 2</a></li>
                      <li><a href="#"> 3</a></li>
                    </ul>
                </ul> <!-- End list -->
            </nav>
        </div>



        <div class="overlay"></div>


        <!-- Popper.JS -->
        <script src="js/popper.min.js"></script>
        <!-- jQuery Custom Scroller CDN -->
        <script src="js/jquery.mCustomScrollbar.concat.min.js"></script>

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
