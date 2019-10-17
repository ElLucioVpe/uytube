<%-- 
    Document   : obtenerComentariosVideo
    Created on : 17 oct. 2019, 16:18:14
    Author     : Esteban
--%>

<%@page import="java.util.List"%>
<%@page import="logica.dt.ComentarioDt"%>
<%@page import="logica.controladores.IControladorVideo"%>
<%@page import="logica.controladores.Fabrica"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Fabrica f = Fabrica.getInstance();
    IControladorVideo vid = f.getIControladorVideo();
    int video_id = -1;
    
    if(request.getParameter("id_v") != null){ 
        video_id = Integer.parseInt(request.getParameter("id_v"));
        String html = "";
        List<ComentarioDt> comentarios = vid.obtenerComentariosDt(video_id);
        if(!comentarios.isEmpty()) {
            for(int i = 0; i < comentarios.size(); i++) {
                html += comentariosRecursivo(comentarios.get(i));
            }
        }
        out.println(html);
    } else out.println("");
%>

<%!
    String comentariosRecursivo(ComentarioDt c) {
        String html = "";
        
        if(html != null){
            String imagen = "img/user.png";
            if(c.getUsuarioDt().getImagen() != null) imagen = "http://localhost:8080/images/"+c.getUsuarioDt().getImagen();
            
            html+= "<li class=\"media\">";
            html+= "<img class=\"rounded-circle\" width=60 height=60 src=\""+imagen+"\">";
            html+= "<div class=\"media-body\"> <div class=\"well well-lg\"";
            html+= "<h4>" + c.getUsuarioDt().getNickname() + "</h4>";
            html+= "<ul class=\"media-date text-uppercase reviews list-inline\"><li>"+c.getFecha()+"</li></ul>";
            html+= "<p class=\"media-comment\"> " + c.getContenido() +" </p>";
            html+= "<button class=\"btn btn-success btn-circle text-uppercase\" data-toggle=\"collapse\" data-target=\"#responder"+c.getId()+"\" id=\"reply\"><span class=\"fas fa-reply\"></span> Responder</button>";
            html+= "<button class=\"btn btn-dark btn-circle text-uppercase\" style=\"margin-left:50px;\" data-toggle=\"collapse\" data-target=\"#respuestas"+c.getId()+"\"><span class=\"fas fa-comment\"></span>"+ c.getHijos().size()+" comentarios</button>";
            html+= "<div class=\"collapse\" id=\"responder"+c.getId()+"\"><div class=\"form-group row\"><div class=\"col-sm-6\">\n";
            html+= "<hr/><textarea id=\"textArea"+c.getId()+"\"class=\"form-control\" rows=\"2\" placeholder=\"Agregue un comentario...\" required></textarea>";
            html+= "<button type=\"button\" class=\"btn btn-success\" onclick=\"comentarVideo("+c.getId()+")\">Comentar</button></div></div>";
            html+= "</div></div><div class=\"collapse\" id=\"respuestas"+c.getId()+"\"><ul class=\"media-list\"><hr/>";

            List<ComentarioDt> hijos = c.getHijos();
            for(int i = 0; i < hijos.size(); i++) {
                html += comentariosRecursivo(hijos.get(i));
            }
            html += "</div></li>";
        }
        return html;
    }
%>
