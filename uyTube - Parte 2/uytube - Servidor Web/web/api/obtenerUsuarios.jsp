<%-- 
    Document   : obtenerUsuarios
    Created on : Oct 2, 2019, 9:48:27 AM
    Author     : Luciano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "org.json.JSONObject"%>
<%@page import = "org.json.JSONArray"%>
<%@page import = "logica.controladores.Fabrica" %>
<%@page import = "logica.controladores.IControladorUsuario"%>
<%@page import = "logica.dt.UsuarioDt" %>
<%@page import = "java.util.List" %>
<%@page import = "java.util.Date" %>
        <%
            Fabrica f = Fabrica.getInstance();
            IControladorUsuario user = f.getIControladorUsuario();
            
            String query = request.getParameter("query");
            JSONObject json1 = null;
            JSONArray jarr = new JSONArray();
            
            List<UsuarioDt> list = user.ListarUsuarios();
            for(int i = 0; i < list.size(); i++) {
                json1 = new JSONObject();
                json1.put("id", (Integer)list.get(i).getId());
                json1.put("apellido", (String)list.get(i).getApellido());
                json1.put("fechanac", (Date)list.get(i).getFechanac());
                json1.put("imagen", (String)list.get(i).getImagen());
                json1.put("mail", (String)list.get(i).getMail());
                json1.put("nickname", (String)list.get(i).getNickname());
                json1.put("nombre", (String)list.get(i).getNombre());
                //json1.put("password", (String)list.get(i).getPassword());     
                jarr.put(json1);
                //jsonObject.put("nickname", (String)list.get(i).getNickname());
            }
            
            out.println(jarr);
        %>