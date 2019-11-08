<%-- 
    Document   : obtenerSeguidos
    Created on : Oct 20, 2019, 12:55:45 PM
    Author     : antus
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "org.json.JSONObject"%>
<%@page import = "org.json.JSONArray"%>
<%@page import = "logica.webservices.WScontroladorUsuarioImplService"%>
<%@page import = "logica.webservices.WScontroladorUsuario"%>
<%@page import = "logica.webservices.UsuarioDt" %>
<%@page import = "java.util.List" %>
<%@page import = "java.util.Date" %>
<%@page import="logica.webservices.CanalDt"%>
        <%
       	 	WScontroladorUsuario user = new WScontroladorUsuarioImplService().getWScontroladorUsuarioImplPort();
            int User_id = Integer.parseInt(request.getParameter("id"));
            List<String> list = user.listarSiguiendo(User_id).getLista();
            JSONObject json1 = null;
            JSONArray jarr = new JSONArray();

            for(int i = 0; i < list.size(); i++) {
                json1 = new JSONObject();
                json1.put("jsonType", "usuario");
                json1.put("nickname", list.get(i));
                 jarr.put(json1);
            }
           
            out.println(jarr);
        %>
