<%-- 
    Document   : login
    Created on : Sep 28, 2019, 5:37:20 PM
    Author     : Luciano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "logica.webservices.WScontroladorUsuarioImplService"%>
<%@page import = "logica.webservices.WScontroladorUsuario"%>

<!DOCTYPE html>
<html>
    <head>
    	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/jquery.min.js"></script>
        <title>Iniciar sesion en uyTube</title>
            <%
                WScontroladorUsuario user = new WScontroladorUsuarioImplService().getWScontroladorUsuarioImplPort();
                
                Boolean formInput = false;
                UsuarioDt userRec = null;
                //Get Cookies
		
		boolean foundCookie = false;

                Cookie[] cookies = request.getCookies();
		int pos = 0;
               	if(cookies!=null){

		        for(int i = 0; i < cookies.length; i++)
		        { 
		            	Cookie cC = cookies[i];
				if (cC.getName().equals("userid"))
				{
				    String userIdStr= cC.getValue();
				    foundCookie = true;
			   	    pos = i;					

				    int numId = Integer.parseInt(userIdStr);
				    userRec = user.consultarUsuario(numId);

				}
		        }
                
		}
                
                if (request.getParameter("user") != null) {
                    if (request.getParameter("password") != null) {
                        Integer _id = user.loginUsuario(request.getParameter("user"), request.getParameter("password"));
                        if (_id != -1) {
                            session.setAttribute("userid",_id);
                            session.setAttribute("user_dt", user.consultarUsuario(_id));
                           
			   if(request.getParameter("recuerdame") != null) {
				   if(request.getParameter("recuerdame").equals("on")){
		                        String cadenaId = String.valueOf(_id);
		                        Cookie c = new Cookie("userid", cadenaId);
					if(foundCookie) cookies[pos].setMaxAge(0);
		                        
					c.setMaxAge(24*60*60);
		                        response.addCookie(c);  // response
		                    
		                    }
			    }
                            String redirectURL = "buscador.jsp?video=true&list=true";
                            response.sendRedirect(redirectURL);
                        }
                    }
                    formInput = true;
                }
            %>
    </head>
    <body>
        <%@include file="include/navbar.jsp" %>
        <main class="login-form">
            <div class="cotainer">
                <div class="row justify-content-center">
                    <div class="col-md-8">
                        <h1>Inicio de sesión</h1>
                    </div>
                </div>
                <div class="row justify-content-center">
                    <div class="col-md-8">
                        <div class="card">
                            <div class="card-header">Iniciar sesion</div>
                            <div class="card-body">
                                <form action="">
                                    <div class="form-group row">
                                        <label for="user" class="col-md-4 col-form-label text-md-right">Usuario</label>
                                        <div class="col-md-6">
                                            <input type="text" id="user" class="form-control" name="user" <%if(foundCookie){%>value=<%=userRec.getNickname()%><%}%> required autofocus>
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label for="password" class="col-md-4 col-form-label text-md-right">Contraseña</label>
                                        <div class="col-md-6">
                                            <input type="password" id="password" class="form-control" name="password" <%if(foundCookie){%>value=<%=userRec.getPassword()%><%}%> required>
                                        </div>
                                    </div>

 				<div class="form-group row">
				<label class="container">Recuerdame 
				  <input type="checkbox" id="recuerdame" name="recuerdame">
				  <span class="checkmark"></span>
				</label>
 				</div>

                                    <div class="col-md-6 offset-md-4">
                                        <button type="submit" class="btn btn-primary">
                                            Iniciar sesion
                                        </button>
                                    </div>
                                    
                                    <% if (formInput) { %>
                                            <div class="alert alert-danger" role="alert">
                                                El usuario o la contraseña no son correctos. Intente nuevamente.
                                            </div>
                                        <%}
                                    %>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <%@include file="include/mini-video.jsp" %>
    </body>
</html>
