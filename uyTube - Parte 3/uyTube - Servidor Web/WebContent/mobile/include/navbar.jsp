
<%@page import="java.util.Locale"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.net.URLClassLoader"%>
<%@page import="java.net.URL"%>
<%@page import="java.io.File"%>
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
<script src="../js/bootstrap.min.js"></script>
<script src="../js/jquery.min.js"></script>

<!-- Font Awesome -->
<link href="../css/fontawesome.min.css" rel="stylesheet">
<script defer src="../js/solid.js"></script>
<script defer src="../js/fontawesome.js"></script>

<%-- Popper --%>
<script src="../js/popper.min.js"></script>

<%@page import = "logica.webservices.WScontroladorUsuarioImplService"%>
<%@page import = "logica.webservices.WScontroladorUsuario"%>
<%@page import = "logica.webservices.UsuarioDt"%>
<%
    //Nombres con "header_" para evitar posibles conflictos de nombres con donde se agregue el header 
    String header_user_nick = "Anonimo";
    String header_user_img = "../img/user.png";
    WScontroladorUsuario user_header = new WScontroladorUsuarioImplService().getWScontroladorUsuarioImplPort();
    
    File properties_header = new File(System.getProperty("user.home")+"/.UyTube");
	URL[] urls_header = {properties_header.toURI().toURL()};
	ClassLoader loader_header = new URLClassLoader(urls_header);
	ResourceBundle bundle_header = ResourceBundle.getBundle("uytube_conf", Locale.getDefault(), loader_header);
    
	String images_url = bundle_header.getString("url_images");
    String header_path = request.getContextPath();System.out.println("header_path:"+header_path);
    
	int sessionId = -1;
	if(session.getAttribute("userid") != null) {
         sessionId = (int)session.getAttribute("userid");
          UsuarioDt header_u = user_header.consultarUsuario(sessionId);
          
        header_user_nick = header_u.getNickname();
        
        if(header_u.getImagen() != null){
            header_user_img = images_url+header_u.getImagen();
        }
    }
%>
<header>
	<script>
                
	            
	            function logout() {
                    $.ajax({
                        url: "<%=header_path%>/api/logout.jsp",
                        success: function(){ 
                            alert("Se cerro la sesión");
                            location.reload();
                        },
                        error: function() { alert("Error inesperado cerrando sesión");}
                    });
                }      
    </script>
    <div class="col">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <form class="form-inline">
             <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#menu" aria-controls="menu" 
             	aria-expanded="false" aria-label="Toggle">
                <i class="navbar-toggler-icon"></i>
            </button>
            <span class="text-muted">Bienvenido <%=header_user_nick%></span>
            </form>
            <form class="form-inline" id="user-info">
              <img src="<%=header_user_img%>" class="rounded-circle" width=30 height=30 alt="User Picture">
            </form>
        </nav>
        <div class="collapse" id="menu">
			<div class="col">
				<a class="dropdown-item" href="buscador.jsp?video=true&list=true">Buscar</a>
				<a class="dropdown-item" href="mislistas.jsp">Mis listas</a>
	    		<%if(sessionId != -1){%>
				<a class="dropdown-item" onclick="logout()">Cerrar Sesion</a>
			<%} else {%>
				<a class="dropdown-item" href="login.jsp">Iniciar Sesion</a>
			<%}%>
			</div>
		</div>
    </div>
</header>
