<%-- 
    Document   : modificarPrivacidadCanal
    Created on : Oct 21, 2019, 2:01:02 AM
    Author     : antus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "logica.webservices.WScontroladorUsuarioImplService"%>
<%@page import = "logica.webservices.WScontroladorUsuario"%>
<%@page import="logica.webservices.UsuarioDt"%>
<%@page import="java.text.DateFormat"%>
<%@page import="logica.webservices.CanalDt"%>
<%
	WScontroladorUsuario user = new WScontroladorUsuarioImplService().getWScontroladorUsuarioImplPort();
    int id_canal = Integer.parseInt(request.getParameter("id"));
   	CanalDt c= user.obtenerCanalDt(id_canal);
    UsuarioDt u=user.consultarUsuario(id_canal);
    user.modificarUsuario(u.getId(), u.getPassword(), u.getNombre(), u.getApellido(), u.getFechanac(), c.getNombre(), c.getCategoria(), c.getDescripcion(), !(c.isPrivacidad()), u.getImagen());
%>