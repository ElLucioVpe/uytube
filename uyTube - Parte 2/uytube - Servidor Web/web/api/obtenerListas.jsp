<%-- 
    Document   : obtenerListas
    Created on : 12 oct. 2019, 20:22:34
    Author     : Esteban
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "logica.controladores.Fabrica" %>
<%@page import = "logica.controladores.IControladorUsuario"%>
<%@page import = "logica.controladores.IControladorCategoria"%>
<%@page import = "logica.dt.ListaDeReproduccionDt" %>
<%@page import = "java.util.List" %>
<%@page import = "org.json.JSONObject"%>
<%@page import = "org.json.JSONArray"%>

<%
    Fabrica f = Fabrica.getInstance();
    IControladorUsuario user = f.getIControladorUsuario();
    IControladorCategoria cat = f.getIControladorCategoria();
    
    List<ListaDeReproduccionDt> l = null; //No hagan esto en casa
    JSONArray retorno = new JSONArray();
    
    if(request.getParameter("id") != null && request.getParameter("id") != "") {
        
        ListaDeReproduccionDt ldt = user.obtenerListaDtPorId(Integer.parseInt(request.getParameter("id")));     
        out.println(crearJSON(ldt));
    } else {
        if(request.getParameter("user_id") != null && request.getParameter("user_id") != "" && request.getParameter("user_id") != "null") {
            l = user.obtenerListasDtPorUsuario(Integer.parseInt(request.getParameter("user_id")));
            
            for(int i = 0; i < l.size(); i++) {
                retorno.put(crearJSON(l.get(i)));
            }
            out.println(retorno);
        } else {
            if(request.getParameter("cat") != null && request.getParameter("cat") != "") {
                l = cat.obtenerListasDtCategoria((String) request.getParameter("cat"));
                
                for(int i = 0; i < l.size(); i++) {
                    retorno.put(crearJSON(l.get(i)));
                }
                out.println(retorno);
            } else {
                l = user.obtenerListas();

                for(int i = 0; i < l.size(); i++) {
                    if(!l.get(i).getPrivada()) retorno.put(crearJSON(l.get(i))); //Solo agrego las listas publicas
                }
                out.println(retorno);
            }
        }
    }
%>

<%! 
    JSONObject crearJSON(ListaDeReproduccionDt ldt) {
        JSONObject json = new JSONObject();
        try{
            json.put("id", ldt.getId());
            json.put("nombre", ldt.getNombre());
            json.put("privada", ldt.getPrivada());
            json.put("tipo", ldt.getTipo());
            json.put("categoria", ldt.getCategoria());
            json.put("user_id", ldt.getIdUsuario());
        } catch (Exception e) {
        }
        return json;
    }
%>
