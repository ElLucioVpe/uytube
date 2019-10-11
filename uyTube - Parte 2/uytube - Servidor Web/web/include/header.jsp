<%-- 
    Document   : header
    Created on : Oct 2, 2019, 10:47:50 AM
    Author     : Luciano
--%>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.min.js"></script>

<%@page import = "logica.dt.UsuarioDt"%>
<%
    //Nombres con "header_" para evitar posibles conflictos de nombres con donde se agregue el header 
    String header_user_nick = "Anonimo";
    String header_user_img = "img/user.png";
    if(session.getAttribute("userid") != null) {
        UsuarioDt header_u = (UsuarioDt) session.getAttribute("user_dt");
        header_user_nick = header_u.getNickname();
        header_user_img = "http://localhost:8080/images/"+header_u.getImagen();
    }
%>
<script>.dropdown-menu.pull-left {left:0;}</script>
<header>
    <div class="pos-f-t">
        <div class="collapse" id="navbarUserToggle">
          <div class="bg-dark p-4">
            <h5 class="text-white h4">Collapsed content</h5>
            <span class="text-muted">Toggleable via the navbar brand.</span>
          </div>
        </div>
        <nav class="navbar navbar-light bg-light">
            <a class="navbar-brand" href="#">
              <img src="img/logo.png" width="60" height="40" alt="Logo">
              uyTube
            </a>
            <form class="form-inline">
              <input class="form-control mr-sm-2" type="search" placeholder="El judio esta haciendo este buscador" aria-label="Buscar">
              <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar</button>
            </form>
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
                  <li><a tabindex="-1" href="http://localhost:8080/WebApplication/login.jsp" style="black">Iniciar Sesion</a></li>
                  <%}else{%>
                  <li><a tabindex="-1" onclick="logout()" style="black">Cerrar Sesion</a></li>
                  <%}%>
                </ul>
                <script>
                  function logout() {
                      $.ajax({
                          url: "http://localhost:8080/WebApplication/api/logout.jsp",
                          success: function(){ alert("Se cerro la sesión");},
                          error: function() { alert("Error inesperado cerrando sesión");}
                      });
                  }                    
                </script>
              </div>
            </form>
        </nav>
    </div>
</header>