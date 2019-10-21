<%-- 
    Document   : modificarPrivacidadCanal
    Created on : Oct 21, 2019, 2:01:02 AM
    Author     : antus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "logica.controladores.Fabrica" %>
<%@page import = "logica.controladores.IControladorUsuario"%>
<%@page import="logica.dt.UsuarioDt"%>
<%@page import="logica.Canal"%>
<%@page import="java.text.DateFormat"%>
<%@page import="logica.dt.CanalDt"%>
<%
    Fabrica f = Fabrica.getInstance();
    IControladorUsuario user = f.getIControladorUsuario();
    int id_canal = Integer.parseInt(request.getParameter("id"));
   CanalDt c= user.obtenerCanalDt(id_canal);
    UsuarioDt u=user.ConsultarUsuario(id_canal);
    user.ModificarUsuario(u.getId(), u.getPassword(), u.getNombre(), u.getApellido(), u.getFechanac(), c.getNombre(), c.getCategoria(), c.getDescripcion(), !(c.getPrivacidad()), u.getImagen());
%>