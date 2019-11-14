<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    
    if(request.getParameter("emb") != null && request.getParameter("emb") != "null" && request.getParameter("emb") != "") {
    	session.setAttribute("embedded", request.getParameter("emb"));
    } else {
    	session.setAttribute("embedded", null);
    }
    	
    
%>