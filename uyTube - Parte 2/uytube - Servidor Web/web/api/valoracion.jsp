<%-- 
    Document   : valoracionLike
    Created on : 10 oct. 2019, 20:36:21
    Author     : Esteban
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "logica.controladores.Fabrica" %>
<%@page import = "logica.controladores.IControladorVideo"%>
<%
    Fabrica f = Fabrica.getInstance();
    IControladorVideo video = f.getIControladorVideo();
    int user_id = Integer.parseInt(request.getParameter("user_id"));
    int video_id = Integer.parseInt(request.getParameter("video_id"));
    boolean gusta = Boolean.valueOf(request.getParameter("gusta"));
    
    video.ValorarVideo(user_id, video_id, gusta);
    out.println("exito");
%>


