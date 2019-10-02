<%-- 
    Document   : obtenerUsuarios
    Created on : Oct 2, 2019, 9:48:27 AM
    Author     : Luciano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "org.json.simple.JSONObject"%>
<%@page import = "logica.controladores.Fabrica" %>
<%@page import = "logica.controladores.IControladorUsuario"%>
<%@page import = "logica.dt.UsuarioDt" %>
<%@page import = "java.util.List" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Fabrica f = Fabrica.getInstance();
            IControladorUsuario user = f.getIControladorUsuario();
            
            String query = request.getParameter("query");
            
            JSONObject jsonObject = new JSONObject();
            
            List<UsuarioDt> list = user.ListarUsuarios();
            for(int i = 0; i < list.size(); i++) {
                jsonObject.put((String)list.get(i).getNickname());
            }
            
            out.println(jsonObject);
        %>
    </body>
</html>
