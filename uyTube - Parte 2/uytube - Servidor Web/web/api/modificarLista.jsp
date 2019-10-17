<%-- 
    Document   : modificarLista
    Created on : 16 oct. 2019, 23:34:43
    Author     : Esteban
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "logica.controladores.Fabrica" %>
<%@page import = "logica.controladores.IControladorUsuario"%>
<%
    Fabrica f = Fabrica.getInstance();
    IControladorUsuario user = f.getIControladorUsuario();
    
    if(request.getParameter("id_p") != null && request.getParameter("id_p") != "") {
        if(request.getParameter("nom") != null && request.getParameter("nom") != "") {
            int id_propietario = Integer.parseInt(request.getParameter("id_p"));
            String nombre_lista = request.getParameter("nom");
            
            if(request.getParameter("accion").equals("quitar")) {
                if(request.getParameter("id_v") != null && request.getParameter("id_v") != "") {
                    int id_video = Integer.parseInt(request.getParameter("id_v"));
                    user.QuitarVideoListaDeReproduccion(id_propietario, nombre_lista, id_video);
                    out.println("El video se elimino con exito de la lista");
                } else out.println("Error: faltaron parametros");
            } else {
                if(request.getParameter("accion").equals("agregar")) {
                    if(request.getParameter("id_uv") != null && request.getParameter("id_uv") != "" && request.getParameter("nomv") != null && request.getParameter("nomv") != "") {
                        int id_usuariovideo = Integer.parseInt(request.getParameter("id_uv"));
                        String nombre_video = request.getParameter("nomv");

                        user.AgregarVideoListaDeReproduccion(id_usuariovideo, id_propietario, nombre_video, nombre_lista);
                        out.println("El video se elimino con exito de la lista");
                    } else out.println("Error: faltan parametros");
                } else {
                    if(request.getParameter("accion").equals("modificar")) {
                        if(request.getParameter("nCat") != null && request.getParameter("nCat") != "" && request.getParameter("nPri") != null && request.getParameter("nPri") != "") {
                            String categoria = request.getParameter("nCat");
                            boolean privacidad = Boolean.parseBoolean(request.getParameter("nPri"));

                            user.ModificarListaDeReproduccion(id_propietario, nombre_lista, categoria, privacidad);
                            out.println("La lista se modifico con exito");
                        } else out.println("Error: faltan parametros");
                    }
                }
            }
        } else out.println("Error: falto el parametro nom");
    } else out.println("Error: falto el parametro id_p");
%>
