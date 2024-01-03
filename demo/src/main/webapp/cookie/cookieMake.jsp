<%@ page language="java"	contentType="text/html;charset=UTF-8"	pageEncoding="UTF-8"%>
<%
  Cookie id = new Cookie("id", "smith");
  id.setMaxAge(60*30);
  
  Cookie pw = new Cookie("pw", "1234");
  pw.setMaxAge(60*20);

  response.addCookie(id);
  response.addCookie(pw);
%>

<!-- 
  쿠키는 문자열이다.
  쿠키는 시간을 제어함
  쿠키는 한글처리가 필요
  쿠키는 로컬 컴퓨터에 저장됨
  쿠키는 여러개 생성 가능 
  쿠키를 생성하면 반드시 클라이언트쪽에 내림처리 해줘야한다( response.addCookie() )
  
  실습결과 
  setPath를 추가로 정의하지 않았기 때문에 같은 경로에서 생성된 페이지만 쿠키정보를 읽을수 있었다 
  
-->