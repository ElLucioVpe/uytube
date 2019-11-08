<%-- 
    Document   : obtenerCategorias
    Created on : 12 oct. 2019, 17:39:06
    Author     : Esteban
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "logica.webservices.WScontroladorCategoriaImplService"%>
<%@page import = "logica.webservices.WScontroladorCategoria"%>
<%@page import = "logica.webservices.CategoriaDt" %>
<%@page import = "java.util.List" %>
<%@page import = "org.json.JSONObject"%>
<%@page import = "org.json.JSONArray"%>

<%
	WScontroladorCategoria cat = new WScontroladorCategoriaImplService().getWScontroladorCategoriaImplPort();
    
    List<CategoriaDt> l = cat.listarCategorias().getLista(); //porque existe esto :\
    
    JSONObject jsonCat = null;
    JSONArray categorias = new JSONArray();
    
    for(int i = 0; i < l.size(); i++) {
        jsonCat = new JSONObject();
        jsonCat.put("nombre", (String) l.get(i).getNombre());
        categorias.put(jsonCat);
    }
    
    out.println(categorias);
%>

