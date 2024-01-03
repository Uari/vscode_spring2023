<%@ page language="java"	contentType="text/html;charset=UTF-8"	pageEncoding="UTF-8"%>
<%
  Cookie id = new Cookie("id", "");
  id.setMaxAge(0);
  
  Cookie pw = new Cookie("pw", "");
  pw.setMaxAge(0);

  response.addCookie(id);
  response.addCookie(pw);
%>