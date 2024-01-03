<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 
  테스트 UTL : http://localhost:8000/notice/noticeEL.jsp?id=kiwi&pw=111
-->

파라미터로 넘어온 값 : ${param.id} -> <%=request.getParameter("id")%>
<br />
파라미터로 넘어온 값 : ${param.pw}
<br />
쿠키정보 - ${cookie.c_name} <br />
쿠키이름 - ${cookie.c_name.name} <br />
쿠키값 - ${cookie.c_name.value} <br />
<hr />
쿠키정보 - ${cookie.mem_id} <br />
쿠키이름 - ${cookie.mem_id.name} <br />
쿠키값 - ${cookie.mem_id.value} <br />
