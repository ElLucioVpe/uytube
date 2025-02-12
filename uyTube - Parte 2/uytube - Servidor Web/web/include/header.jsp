<%-- 
    Document   : header
    Created on : Oct 2, 2019, 10:47:50 AM
    Author     : Luciano
--%>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.min.js"></script>

<!-- Font Awesome -->
<link href="css/fontawesome.min.css" rel="stylesheet">
<script defer src="js/solid.js"></script>
<script defer src="js/fontawesome.js"></script>

<%-- Popper --%>
<script src="js/popper.min.js"></script>

<%@page import = "logica.controladores.Fabrica"%>
<%@page import = "logica.controladores.IControladorUsuario"%>
<%@page import = "logica.dt.UsuarioDt"%>
<%
    //Nombres con "header_" para evitar posibles conflictos de nombres con donde se agregue el header 
    String header_user_nick = "Anonimo";
    String header_user_img = "img/user.png";
    Fabrica f_Header = Fabrica.getInstance();
    IControladorUsuario user_header = f_Header.getIControladorUsuario();
    
    String header_path = request.getContextPath();System.out.println("header_path:"+header_path);
    if(session.getAttribute("userid") != null) {
         int sessionId = (int)session.getAttribute("userid");
          UsuarioDt header_u = user_header.ConsultarUsuario(sessionId);
          
        header_user_nick = header_u.getNickname();
        
        if(header_u.getImagen() != null){
            header_user_img = header_path+"/images/"+header_u.getImagen();
        }
    }
%>
<header>
    <div class="pos-f-t">
        <nav class="navbar navbar-light bg-light">
            <form class="form-inline">
            <a class="navbar-brand" href="<%=header_path%>/index.jsp">
              <img src="img/logo.png" width="60" height="40" alt="Logo">
              uyTube
            </a>
            <button type="button" id="sidebarCollapse" class="btn toggle">
                <i class="navbar-toggler-icon"></i>
            </button>
            </form>
            <form class="form-inline">
              <input id="search-text" class="form-control mr-sm-2" type="search" placeholder="Busque videos, listas o canales" aria-label="Buscar">
              <button class="btn btn-outline-success my-2 my-sm-0" type="button" onclick="buscar()">Buscar</button>
            </form>
            <script>
                function buscar() {
                    var contenido = $('#search-text').val();
                    if(contenido !== null) window.location.replace("<%=header_path%>/buscador.jsp?video=true&channel=true&list=true&text="+contenido);
                    else window.location.replace("<%=header_path%>/buscador.jsp?video=true&channel=true&list=true");
                }
            </script>
            
            <form class="form-inline" id="user-info">
              <img src=<%=header_user_img%> class="rounded-circle" width="30" height="30" alt="User Picture">
              <span class="text-muted"><%=header_user_nick%></span>
              <div class="btn-group">
                <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                  <span class="navbar-toggler-icon"></span>
                </a>
                <ul class="dropdown-menu dropdown-menu-right" role="menu">
                  <!-- dropdown menu links -->
                  <%if(session.getAttribute("userid") == null) {%>
                  <li><a tabindex="-1" href="<%=header_path%>/login.jsp"><i class="fas fa-sign-in-alt"></i> Iniciar Sesion</a></li>
                  <li><a tabindex="-1" href="<%=header_path%>/altaUser.jsp"><i class="fas fa-sign-in-alt"></i> Registrarse</a></li>
                  <%}else{%>
                  <li><a tabindex="-1" onclick="logout()"><i class="fas fa-sign-out-alt"></i> Cerrar Sesion</a></li>
                  <%}%>
                </ul>
                <script>
                  function logout() {
                      $.ajax({
                          url: "<%=header_path%>/api/logout.jsp",
                          success: function(){ 
                              alert("Se cerro la sesi�n");
                              location.reload();
                          },
                          error: function() { alert("Error inesperado cerrando sesi�n");}
                      });
                  }                    
                </script>
              </div>
            </form>
        </nav>
    </div>
</header>
<%@ include file="sidebar.jsp" %>