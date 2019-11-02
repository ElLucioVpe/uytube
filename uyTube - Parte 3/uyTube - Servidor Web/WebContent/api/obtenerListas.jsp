<%-- 
    Document   : obtenerListas
    Created on : 12 oct. 2019, 20:22:34
    Author     : Esteban
--%>

<%@page import="logica.dt.UsuarioDt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "logica.controladores.Fabrica" %>
<%@page import = "logica.controladores.IControladorUsuario"%>
<%@page import = "logica.controladores.IControladorCategoria"%>
<%@page import = "logica.dt.ListaDeReproduccionDt" %>
<%@page import = "java.util.List" %>
<%@page import = "org.json.JSONObject"%>
<%@page import = "org.json.JSONArray"%>
<%@page import = "java.util.Date"%>

<%
    Fabrica f = Fabrica.getInstance();
    IControladorUsuario user = f.getIControladorUsuario();
    IControladorCategoria cat = f.getIControladorCategoria();
    
    List<ListaDeReproduccionDt> l = null; //No hagan esto en casa
    JSONArray retorno = new JSONArray();
    
    if(request.getParameter("id") != null && request.getParameter("id") != "") {
        
        ListaDeReproduccionDt ldt = user.obtenerListaDtPorId(Integer.parseInt(request.getParameter("id")));
        UsuarioDt u = user.ConsultarUsuario(ldt.getIdUsuario());
        out.println(crearJSON(ldt, u));
    } else {
        if(request.getParameter("user_id") != null && request.getParameter("user_id") != "" && request.getParameter("user_id") != "null") {
            l = user.obtenerListasDtPorUsuario(Integer.parseInt(request.getParameter("user_id")));
            
            for(int i = 0; i < l.size(); i++) {
                UsuarioDt u = user.ConsultarUsuario(l.get(i).getIdUsuario());
                retorno.put(crearJSON(l.get(i), u));
            }
            out.println(retorno);
        } else {
            l = user.obtenerListas();

            for(int i = 0; i < l.size(); i++) {
                if(!l.get(i).getPrivada()){//Solo agrego las listas publicas
                    UsuarioDt u = user.ConsultarUsuario(l.get(i).getIdUsuario());
                    JSONObject json1 = crearJSON(l.get(i), u);
                    if(request.getParameter("cat") != null && request.getParameter("cat") != "") {
                        if(json1.getString("categoria").equals(request.getParameter("cat"))) retorno.put(json1);
                    } else retorno.put(json1);
                } 
            }
            out.println(retorno);
        }
    }
%>

<%! 
    JSONObject crearJSON(ListaDeReproduccionDt ldt, UsuarioDt udt) {
        JSONObject json = new JSONObject();
        try{
            json.put("jsonType", "lista");
            json.put("id", ldt.getId());
            json.put("nombre", ldt.getNombre());
            json.put("privacidad", ldt.getPrivada());
            json.put("tipo", ldt.getTipo());
            json.put("categoria", ldt.getCategoria());
            json.put("user_id", ldt.getIdUsuario());
            json.put("user_nick", udt.getNickname());
            if(ldt.getFechaUV() != null) json.put("fechaPublicacion", ldt.getFechaUV());
            else json.put("fechaPublicacion", "1990-01-01"); //no tiene videos lo mando bien para el fondo
        } catch (Exception e) {
        }
        return json;
    }
%>
