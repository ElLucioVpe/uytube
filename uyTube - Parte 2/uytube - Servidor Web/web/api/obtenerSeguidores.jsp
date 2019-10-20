<%-- 
    Document   : obtenerSeguidores
    Created on : Oct 20, 2019, 12:00:08 PM
    Author     : antus
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "org.json.JSONObject"%>
<%@page import = "org.json.JSONArray"%>
<%@page import = "logica.controladores.Fabrica" %>
<%@page import = "logica.controladores.IControladorUsuario"%>
<%@page import = "logica.dt.UsuarioDt" %>
<%@page import = "java.util.List" %>
<%@page import = "java.util.Date" %>
<%@page import="logica.dt.CanalDt"%>
        <%
            Fabrica f = Fabrica.getInstance();
            IControladorUsuario user = f.getIControladorUsuario();
            int User_id = Integer.parseInt(request.getParameter("id"));
            List<String> list = user.ListarSeguidores(User_id);
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
