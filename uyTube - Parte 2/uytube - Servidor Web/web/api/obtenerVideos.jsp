<%-- 
    Document   : obtenerVideos
    Created on : Oct 4, 2019, 10:54:58 PM
    Author     : Luciano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "org.json.JSONObject"%>
<%@page import = "org.json.JSONArray"%>
<%@page import = "logica.controladores.Fabrica" %>
<%@page import = "logica.controladores.IControladorVideo"%>
<%@page import = "logica.controladores.IControladorUsuario"%>
<%@page import = "logica.dt.VideoDt" %>
<%@page import = "java.util.List" %>
<%@page import = "java.util.Date" %>
        <%
            Fabrica f = Fabrica.getInstance();
            IControladorVideo video = f.getIControladorVideo();
            IControladorUsuario user = f.getIControladorUsuario();
            
            JSONObject json1 = null;
            JSONArray jarr = new JSONArray();
            
            List<VideoDt> list = user.listarVideosDeUsuario("cachilas");
            for(int i = 0; i < list.size(); i++) {
                json1 = new JSONObject();
                json1.put("id", (Integer)list.get(i).getId());
                json1.put("nombre", (String)list.get(i).getNombre());
                json1.put("duracion", (float)list.get(i).getDuracion());
                json1.put("url", (String)list.get(i).getUrl());
                json1.put("descripcion", (String)list.get(i).getDescripcion());
                json1.put("fechaPublicacion", (Date)list.get(i).getFechaPublicacion());
                json1.put("privacidad", (Boolean)list.get(i).getPrivacidad());
                json1.put("canal_user_id", (int)list.get(i).getIdCanal());
                json1.put("likes", (int)list.get(i).getLikes());
                json1.put("dislikes", (int)list.get(i).getDislikes());
            }
            
            out.println(jarr);
        %>