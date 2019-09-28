<%-- 
    Document   : index
    Created on : Sep 27, 2019, 10:32:17 PM
    Author     : pagol
--%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fecha de Hoy</title>
    </head>
    <%
        Date date = new Date();
        
    %>
    <body>
        <h1>Hello World!</h1>
        <p>hoy es <%= date %> </p>
    </body>
</html>
