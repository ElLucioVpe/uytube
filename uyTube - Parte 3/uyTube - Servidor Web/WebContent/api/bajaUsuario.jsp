<%-- 
    Document   : bajaUsuario
    Created on : hoy mismo
    Author     : Esteban
--%>
<%@page import = "logica.webservices.WScontroladorUsuarioImplService"%>
<%@page import = "logica.webservices.WScontroladorUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	WScontroladorUsuario user = new WScontroladorUsuarioImplService().getWScontroladorUsuarioImplPort();
    
	//Doy de baja al usuario
	if(request.getParameter("id") != null && request.getParameter("id") != "" && request.getParameter("id") != "null") {
		int id_user = Integer.parseInt(request.getParameter("id"));
		
		if(!user.obtenerNickUsuario(id_user).isEmpty()){ 
			user.bajaUsuario(id_user);
			
			if(!user.consultarUsuario(id_user).isActivo()) { 
				//Cierro sesion
			    session.setAttribute("userid", null);
			    session.setAttribute("user_dt", null);
			    
				out.println("La baja de usuario se completo con exito");
			}
			else out.println("Ha ocurrido un error en la baja de usuario");
			
		} else out.println("El usuario no existe");
		
	}
%>