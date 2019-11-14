<%-- 
    Document   : obtenerComentariosVideo
    Created on : 17 oct. 2019, 16:18:14
    Author     : Esteban
--%>

<%@page import="java.util.Locale"%>
<%@page import="java.io.File"%>
<%@page import="java.net.URL"%>
<%@page import="java.net.URLClassLoader"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.List"%><%@page import="java.util.ArrayList"%>
<%@page import="logica.webservices.ComentarioDt"%>
<%@page import = "logica.webservices.WScontroladorVideoImplService"%>
<%@page import = "logica.webservices.WScontroladorVideo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	WScontroladorVideo vid = new WScontroladorVideoImplService().getWScontroladorVideoImplPort();
    int video_id = -1;
    
    if(request.getParameter("id_v") != null){
    	
    	File properties = new File(System.getProperty("user.home")+"/.UyTube");
		URL[] urls = {properties.toURI().toURL()};
		ClassLoader loader = new URLClassLoader(urls);
		ResourceBundle bundle = ResourceBundle.getBundle("uytube_conf", Locale.getDefault(), loader);
    	
        String path = bundle.getString("url_images");
        video_id = Integer.parseInt(request.getParameter("id_v"));
        String html = "";
        List<ComentarioDt> comentarios = vid.obtenerComentariosDt(video_id).getLista();
        if(!comentarios.isEmpty()) {
            for(int i = 0; i < comentarios.size(); i++) {
                html += comentariosRecursivo(comentarios.get(i), path);
            }
        }
        out.println(html);
    } else out.println("");
%>

<%!
    String comentariosRecursivo(ComentarioDt c, String path) {
        String html = "";
        
        if(html != null){
            String imagen = "img/user.png";
            if(c.getUsuarioDt().getImagen() != null) imagen = path+c.getUsuarioDt().getImagen();

            html+= "<li class=\"media\">";
            html+= "<img class=\"rounded-circle\" width=40 height=40 src=\""+imagen+"\">";
            html+= "<div class=\"media-body\"> <div class=\"well well-lg\"";
            html+= "<h4>" + c.getUsuarioDt().getNickname() + "</h4>";
            html+= "<ul class=\"media-date text-uppercase reviews list-inline\"><li>"+c.getFecha().toGregorianCalendar().getTime().toInstant()+"</li></ul>";
            html+= "<p class=\"media-comment\"> " + c.getContenido() +" </p>";
            html+= "<button class=\"btn btn-success btn-circle text-uppercase\" data-toggle=\"collapse\" data-target=\"#responder"+c.getId()+"\" id=\"reply\"><span class=\"fas fa-reply\"></span></button>";
            html+= "<button class=\"btn btn-dark btn-circle text-uppercase\" data-toggle=\"collapse\" data-target=\"#respuestas"+c.getId()+"\"><span class=\"fas fa-comment\"></span>"+ c.getHijos().size()+"</button>";
            html+= "<div class=\"collapse\" id=\"responder"+c.getId()+"\"><div class=\"form-group row\"><div class=\"col-sm-6\">\n";
            html+= "<hr/><textarea id=\"textArea"+c.getId()+"\"class=\"form-control\" rows=\"2\" placeholder=\"Agregue un comentario...\" required></textarea>";
            html+= "<button type=\"button\" class=\"btn btn-success\" onclick=\"comentarVideo("+c.getId()+")\">Comentar</button></div></div>";
            html+= "</div></div><div class=\"collapse pull-left\" id=\"respuestas"+c.getId()+"\"><ul class=\"media-list\"><hr/>";


            List auxhijos = c.getHijos();
            List<ComentarioDt> hijos = (List<ComentarioDt>) auxhijos;
            for(int i = 0; i < hijos.size(); i++) {
                html += comentariosRecursivo(hijos.get(i), path);
            }
            html += "</div></li>";
        }
        return html;
    }
%>
