<%-- 
    Document   : comentarVideo
    Created on : 17 oct. 2019, 19:00:45
    Author     : Esteban
--%>

<%@page import="java.util.Date"%>
<%@page import = "logica.webservices.WScontroladorVideoImplService"%>
<%@page import = "logica.webservices.WScontroladorVideo"%>
<%@ page import = "javax.xml.datatype.XMLGregorianCalendar"%>
<%@ page import = "javax.xml.datatype.DatatypeFactory"%>
<%@ page import = "java.util.GregorianCalendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	WScontroladorVideo vid = new WScontroladorVideoImplService().getWScontroladorVideoImplPort();
    
    if(request.getParameter("id_u") != null && request.getParameter("id_u") != "") {
        int usuario_id = Integer.parseInt(request.getParameter("id_u"));
        if(request.getParameter("id_v") != null && request.getParameter("id_v") != "") {
            int video_id = Integer.parseInt(request.getParameter("id_v"));
            if(request.getParameter("id_p") != null && request.getParameter("id_p") != "") {
                long padre_id = Long.parseLong(request.getParameter("id_p"));
                if (request.getParameter("text") != null && request.getParameter("text") != "") {
                	GregorianCalendar gcdate = new GregorianCalendar();
                  	gcdate.setTime(new Date());
					XMLGregorianCalendar xmlFecha = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcdate);
					
                    vid.comentarVideo(usuario_id, video_id, padre_id, request.getParameter("text"), xmlFecha);
                    out.println("El comentario se realizo con exito");
                } else out.println("Por favor escriba algo");
            } else out.println("Error: Faltan parametros");
        } else out.println("Error: Faltan parametros");
    } else out.println("Error: Faltan parametros");
%>
