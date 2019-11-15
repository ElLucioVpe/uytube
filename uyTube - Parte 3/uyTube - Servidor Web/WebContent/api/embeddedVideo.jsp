<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
    
    if(request.getParameter("emb") != null && request.getParameter("emb") != "null" && request.getParameter("emb") != "") {
    	session.setAttribute("embedded", request.getParameter("emb"));
    } else {
    	session.setAttribute("embedded", null);
    }
	
	if(request.getParameter("time") != null && request.getParameter("time") != "null" && request.getParameter("time") != "") {
		float time = Float.parseFloat(request.getParameter("time"));
		session.setAttribute("embedded-time", time);
	} else {
		session.setAttribute("embedded-time", null);
	}
	    	
    
%>