<%-- 
    Document   : obtenerVideos
    Created on : Oct 4, 2019, 10:54:58 PM
    Author     : Luciano
--%>

<%@page import="logica.webservices.UsuarioDt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "org.json.JSONObject"%>
<%@page import = "org.json.JSONArray"%>
<%@page import = "logica.webservices.WScontroladorUsuarioImplService"%>
<%@page import = "logica.webservices.WScontroladorUsuario"%>
<%@page import = "logica.webservices.WScontroladorVideoImplService"%>
<%@page import = "logica.webservices.WScontroladorVideo"%>
<%@page import = "logica.webservices.VideoDt" %>
<%@page import = "java.util.List" %>
<%@page import = "java.util.Date" %>
        <%
        	WScontroladorUsuario user = new WScontroladorUsuarioImplService().getWScontroladorUsuarioImplPort();
        	WScontroladorVideo video = new WScontroladorVideoImplService().getWScontroladorVideoImplPort();
            String cat = request.getParameter("cat");
            JSONObject json1 = null;
            JSONArray jarr = new JSONArray();
            
            if (request.getParameter("id") == null || request.getParameter("id") == "") {
                List<VideoDt> list = video.obtenerVideos().getLista();
                for(int i = 0; i < list.size(); i++) {
                    UsuarioDt _user = user.consultarUsuario((int)list.get(i).getIdCanal());
                    json1 = new JSONObject();
                    json1.put("jsonType", "video");
                    json1.put("id", (Integer)list.get(i).getId());
                    json1.put("nombre", (String)list.get(i).getNombre());
                    json1.put("thumbnail", (String)list.get(i).getThumbnail());
                    json1.put("url", (String)list.get(i).getUrl());
                    json1.put("descripcion", (String)list.get(i).getDescripcion());
                    json1.put("fechaPublicacion", list.get(i).getFechaPublicacion());
                    json1.put("privacidad", (Boolean)list.get(i).isPrivacidad());
                    json1.put("categoria", list.get(i).getCategoria());
                    json1.put("canal_user_id", (int)list.get(i).getIdCanal());
                    json1.put("user", _user.getNickname());
                    json1.put("likes", (int)list.get(i).getLikes());
                    json1.put("dislikes", (int)list.get(i).getDislikes());
                    
                    if(cat == null || cat == "") jarr.put(json1);
                    else if(cat.equals(list.get(i).getCategoria())) jarr.put(json1);
                }
                
                out.println(jarr);
            } else {
                VideoDt _video = video.obtenerVideoDtPorID(Integer.parseInt(request.getParameter("id")));
                UsuarioDt _user = user.consultarUsuario((int)_video.getIdCanal());
                json1 = new JSONObject();
                json1.put("id", (Integer)_video.getId());
                json1.put("nombre", (String)_video.getNombre());
                json1.put("thumbnail", (String)_video.getThumbnail());
                json1.put("url", (String)_video.getUrl());
                json1.put("descripcion", (String)_video.getDescripcion());
                json1.put("fecha", _video.getFechaPublicacion());
                json1.put("privacidad", (Boolean)_video.isPrivacidad());
                json1.put("canal_user_id", (int)_video.getIdCanal());
                json1.put("user", _user.getNickname());
                json1.put("likes", (int)_video.getLikes());
                json1.put("dislikes", (int)_video.getDislikes());                
                int segundos = (int)((_video.getDuracion() % 1)*100);
                json1.put("segundos",segundos);
                int dur= (int)_video.getDuracion();
                json1.put("minutos", (dur));

                
                out.println(json1);
            }
        %>