<%-- 
    Document   : JsonArtists
    Created on : Oct 2, 2019, 4:34:12 PM
    Author     : antus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.json.JSONObject"%>
<%
    JSONObject json = new JSONObject();
    json.put("artistname", "Leonard Cohen");
    json.put("born", "1934");
    System.out.print(json);
    System.out.flush();
%>
<!--<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>-->
