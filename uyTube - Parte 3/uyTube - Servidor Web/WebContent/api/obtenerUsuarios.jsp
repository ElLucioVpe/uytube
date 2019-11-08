<%-- 
    Document   : obtenerUsuarios
    Created on : Oct 2, 2019, 9:48:27 AM
    Author     : Luciano
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

            String cat = request.getParameter("cat");
            JSONObject json1 = null;
            JSONObject jsonC = null;
            JSONArray jarr = new JSONArray();

            List<UsuarioDt> list = user.listarUsuarios().getLista();
            for(int i = 0; i < list.size(); i++) {
                json1 = new JSONObject();
                json1.put("jsonType", "usuario");
                json1.put("id", (Integer)list.get(i).getId());
                json1.put("nombre", (String)list.get(i).getNombre());
                json1.put("apellido", (String)list.get(i).getApellido());
                json1.put("nickname", (String)list.get(i).getNickname());
                //json1.put("password", (String)list.get(i).getPassword());
                json1.put("mail", (String)list.get(i).getMail());
                json1.put("fechanac", list.get(i).getFechanac());
                if(list.get(i).getImagen() != null) json1.put("imagen", (String)list.get(i).getImagen());
                else json1.put("imagen", "");

                //Info Canal
                CanalDt cdt = user.obtenerCanalDt(list.get(i).getId());
                jsonC = new JSONObject();
                jsonC.put("jsonType", "canal");
                jsonC.put("id", cdt.getUserId());
                jsonC.put("nombre", cdt.getNombre());
                jsonC.put("descripcion", cdt.getDescripcion());
                jsonC.put("privacidad", cdt.isPrivacidad());
                jsonC.put("categoria", cdt.getCategoria());
                if(cdt.getFechaUV() != null) json1.put("fechaPublicacion", cdt.getFechaUV());
                else json1.put("fechaPublicacion", "1990-01-01"); //no tiene videos lo mando bien para el fondo
                json1.put("canal", jsonC);

                if(cat == null || cat == "") jarr.put(json1);
                else if(cat.equals(cdt.getCategoria())) jarr.put(json1);
                //Cant Subscriptores
                List<String> list2 = user.listarSeguidores((Integer)list.get(i).getId()).getLista();
                json1.put("Subcriptores", list2.size());

            }
            out.println(jarr);
        %>
