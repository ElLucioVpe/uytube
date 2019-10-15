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
<%@page import="logica.dt.CanalDt"%>
        <%
            Fabrica f = Fabrica.getInstance();
            IControladorUsuario user = f.getIControladorUsuario();
            
            String cat = request.getParameter("cat");
            JSONObject json1 = null;
            JSONObject jsonC = null;
            JSONArray jarr = new JSONArray();
            
            List<UsuarioDt> list = user.ListarUsuarios();
            for(int i = 0; i < list.size(); i++) {
                json1 = new JSONObject();
                json1.put("jsonType", "usuario");
                json1.put("id", (Integer)list.get(i).getId());
                json1.put("nombre", (String)list.get(i).getNombre());
                json1.put("apellido", (String)list.get(i).getApellido());
                json1.put("nickname", (String)list.get(i).getNickname());
                //json1.put("password", (String)list.get(i).getPassword());
                json1.put("mail", (String)list.get(i).getMail());
                json1.put("fechanac", (Date)list.get(i).getFechanac());
                if(list.get(i).getImagen() != null) json1.put("imagen", (String)list.get(i).getImagen());
                else json1.put("imagen", "");
                
                //Info Canal
                CanalDt cdt = user.obtenerCanalDt(list.get(i).getId());
                jsonC = new JSONObject();
                jsonC.put("jsonType", "canal");
                jsonC.put("id", cdt.getUserId());
                jsonC.put("nombre", cdt.getNombre());
                jsonC.put("descripcion", cdt.getDescripcion());
                jsonC.put("privada", cdt.getPrivacidad());
                jsonC.put("categoria", cdt.getCategoria());
                if(cdt.getFechaUV() != null) jsonC.put("ultimaFecha", cdt.getFechaUV().toString());
                else jsonC.put("ultimaFecha", "1990-01-01"); //no tiene videos lo mando bien para el fondo
                json1.put("canal", jsonC);
                
                if(cat == null || cat == "") jarr.put(json1);
                else if(cat == cdt.getCategoria()) jarr.put(json1);
            }
            
            out.println(jarr);
        %>