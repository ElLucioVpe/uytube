<%-- 
    Document   : valoracionLike
    Created on : 10 oct. 2019, 20:36:21
    Author     : Esteban
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "logica.webservices.WScontroladorVideoImplService"%>
<%@page import = "logica.webservices.WScontroladorVideo"%>
<%
	WScontroladorVideo video = new WScontroladorVideoImplService().getWScontroladorVideoImplPort();
    int user_id = Integer.parseInt(request.getParameter("user_id"));
    int video_id = Integer.parseInt(request.getParameter("video_id"));
    boolean gusta = Boolean.valueOf(request.getParameter("gusta"));
    
    video.valorarVideo(user_id, video_id, gusta);
    out.println("exito");
%>


