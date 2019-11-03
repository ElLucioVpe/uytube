<%-- 
    Document   : bajaUsuario
    Created on : hoy mismo
    Author     : Esteban
--%>
<%@page import="logica.controladores.IControladorUsuario"%>
<%@page import="logica.controladores.Fabrica"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Fabrica f = Fabrica.getInstance();
    IControladorUsuario user = f.getIControladorUsuario();
    
	//Doy de baja al usuario
	if(request.getParameter("id") != null && request.getParameter("id") != "" && request.getParameter("id") != "null") {
		int id_user = Integer.parseInt(request.getParameter("id"));
		
		if(!user.obtenerNickUsuario(id_user).isEmpty()){ 
			user.BajaUsuario(id_user);
			
			if(!user.ConsultarUsuario(id_user).getActivo()) { 
				//Cierro sesion
			    session.setAttribute("userid", null);
			    session.setAttribute("user_dt", null);
			    
				out.println("La baja de usuario se completo con exito");
			}
			else out.println("Ha ocurrido un error en la baja de usuario");
			
		} else out.println("El usuario no existe");
		
	}
%>