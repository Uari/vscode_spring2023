<%@ page language="java"	contentType="text/html;charset=UTF-8"	pageEncoding="UTF-8"%>
<%
  Cookie[] cs = request.getCookies();
  for(int i =0; i<cs.length; i++){
    if("id".equals(cs[i].getName())){
      out.print(cs[i].getValue());
    }
    if("pw".equals(cs[i].getName())){
      out.print(cs[i].getValue());
    }
  }
%>