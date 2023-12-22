<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<form method="post" id="f_notice" action="noticeInsert">
  <a href="javascript:noticeInsert()">저장</a>
</form>
<script>
  const noticeInsert = () => {
    document.querySelector('#f_notice').submit();
  };
</script>
