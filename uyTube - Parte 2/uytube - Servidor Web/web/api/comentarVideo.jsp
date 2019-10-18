<%-- 
    Document   : comentarVideo
    Created on : 17 oct. 2019, 19:00:45
    Author     : Esteban
--%>

<%@page import="java.util.Date"%>
<%@page import="logica.controladores.IControladorVideo"%>
<%@page import="logica.controladores.Fabrica"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Fabrica f = Fabrica.getInstance();
    IControladorVideo vid = f.getIControladorVideo();
    
    if(request.getParameter("id_u") != null && request.getParameter("id_u") != "") {
        int usuario_id = Integer.parseInt(request.getParameter("id_u"));
        if(request.getParameter("id_v") != null && request.getParameter("id_v") != "") {
            int video_id = Integer.parseInt(request.getParameter("id_v"));
            if(request.getParameter("id_p") != null && request.getParameter("id_p") != "") {
                long padre_id = Long.parseLong(request.getParameter("id_p"));
                if (request.getParameter("text") != null && request.getParameter("text") != "") {
                    vid.ComentarVideo(usuario_id, video_id, padre_id, request.getParameter("text"), new Date());
                    out.println("El comentario se realizo con exito");
                } else out.println("Por favor escriba algo");
            } else out.println("Error: Faltan parametros");
        } else out.println("Error: Faltan parametros");
    } else out.println("Error: Faltan parametros");
%>
