<%-- 
    Document   : suscripcion
    Created on : 10 oct. 2019, 21:27:38
    Author     : Esteban
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "logica.controladores.Fabrica" %>
<%@page import = "logica.controladores.IControladorUsuario"%>
<%
    Fabrica f = Fabrica.getInstance();
    IControladorUsuario user = f.getIControladorUsuario();
    int seguidor = Integer.parseInt(request.getParameter("seguidor"));
    int seguido = Integer.parseInt(request.getParameter("seguido"));
    String seguidor_nick = user.obtenerNickUsuario(seguidor);
    String seguido_nick = user.obtenerNickUsuario(seguido);
    
    if(seguidor_nick != null && seguido_nick != null) {
        if(!user.estaSuscripto(seguidor, seguido)) user.seguirUsuario(seguidor_nick, seguido_nick);
        else user.dejarDeSeguirUsuario(seguidor_nick, seguido_nick);
    }
%>