<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>로그인</title>
    <%@include file="/common/bootstrap_common.jsp"%>
    <link rel="stylesheet" href="/css/main.css?1" />
    <!-- 카카오 (우편번호 검색)제공 라이브러리 추가{2023-12-29} -->
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script>
      //회원가입 버튼 눌렀을 때 호출되는 함수
      const memberInsert = () =>{
        document.querySelector("#f_member").submit();
      }
    </script>
  </head>
  <body>
    <!-- header start -->
    <%@include file="/include/gym_header.jsp"%>
    <!-- header end    -->
    <!-- body start    -->
    <div class="container">
      <form id="f_login" action="/authc/login" method="post" onsubmit="return formCheck(this)">
        <h3>로그인</h3>
        <div class="msg">${param.msg}</div>
        <div class="mb-3 mt-3">
          <label for="mem_email" class="form-label">Email:</label>
          <input type="text" value="kiwi@hot.com" class="form-control" id="mem_email" placeholder="Enter Email" name="mem_email" autofocus/>
        </div>
        <div class="mb-3">
          <label for="mem_pw2" class="form-label">Password:</label>
          <input type="password" value="1234" class="form-control" id="mem_pw2" placeholder="Enter password" name="mem_pw2" />
        </div>

        <div >
          <label for="remember" >기억하기:</label>
          <input type="checkbox" id="remember" name="remember" ${empty cookie.mem_email.value?"":"checked"} />
        </div>
        
        <input type="submit" id="btn-login" class="btn btn-primary" value="로그인"/>
        <script>
          const formCheck = (f_login) =>{
            let msg = '';
            if(f_login.mem_email.value.length == 0){
              msgShow('이메일을 입력해 주세요', f_login.mem_email);
              return false;
            }
            if(f_login.mem_pw2.value.length == 0){
              msgShow('비밀번호를 입력해 주세요', f_login.mem_pw2);
              return false;
            }
            return true;
          }
          const msgShow = (msg,element) =>{
            document.querySelector(".msg").innerHTML = msg;
            if(element){
              element.select();
            }
          }
          const openZipcode = () => {
						new daum.Postcode({
							oncomplete: function(data) {
								let addr = ''; 
								if (data.userSelectedType === 'R') { 
									addr = data.roadAddress;//도로명
								} else { 
									addr = data.jibunAddress;//지번
								}
								console.log(data);
								console.log(addr);
								//console.log(post.postNum);
								//setPost({...post, zipcode:data.zonecode, addr:addr}) ;
								document.getElementById("zipcode").value = data.zonecode;
								document.getElementById("address").value = addr;
								//document.getElementById("postDetail").focus();
							}
						}).open();
					}
          </script> 
        <!-- <script>
          const btnLogin = document.querySelector('#btn-login');
          btnLogin.addEventListener('click', (e) => {
            //alert('11');
            document.querySelector('#f_login').submit();
          });
        </script> -->
        <a
          href="https://kauth.kakao.com/oauth/authorize?client_id=818b11bd2a07b66b6f5537b683402953&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code"> 
          <img src="/images/ko/kakao_login_medium_narrow.png" alt="" srcset="" />
        </a>
        <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#memberForm">
          회원가입
        </button>
      </form>
    </div>
    <!-- body end    -->
    <!-- footer start -->
    <%@include file="/include/gym_footer.jsp"%>
    <!-- footer end    -->
    <!-- ========================== [[ 회원가입 Modal - 우편번호검색기 ]] ========================== -->
    <div class="modal" id="memberForm">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <!-- Modal Header -->
          <div class="modal-header">
            <h4 class="modal-title">회원가입</h4>
            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
          </div>

          <!-- Modal body -->
          <div class="modal-body">
            <form id="f_member" method="post" enctype="multipart/form-data" action="/member/memberInsert">
              <div class="form-floating mb-3 mt-3">
                <input type="text" class="form-control" id="mem_id" name="mem_id" placeholder="Enter 아이디" />
                <label for="mem_id">아이디</label>
              </div>
              <div class="form-floating mb-3 mt-3">
                <input type="text" class="form-control" id="mem_pw" name="mem_pw" placeholder="Enter 비밀번호" />
                <label for="mem_pw">비밀번호</label>
              </div>
              <div class="form-floating mb-3 mt-3">
                <input type="text" class="form-control" id="mem_name" name="mem_name" placeholder="Enter 이름" />
                <label for="mem_name">이름</label>
              </div>
              <div class="form-floating mb-3 mt-3">
                <input type="text" class="form-control" id="mem_email" name="mem_email" placeholder="Enter 이메일" />
                <label for="mem_email">이메일</label>
              </div>
              <div class="input-group">
                <input type="text" class="form-control" id="zipcode" name="zipcode" placeholder="우편번호" />
                &nbsp;
                <input type="button" class="btn btn-success" onclick="openZipcode()" value="우편번호찾기" />
              </div>
              <div style="margin-bottom: 5px"></div>
              <div style="display: flex; flex-wrap: wrap">
                <input type="text" class="form-control" id="address" name="address" placeholder="주소" />
              </div>
              <div class="input-group mt-3">
                <input type="file" name="mem_picture" id="mem_picture">
                <label class="input-group-text" for="mem_picture">Upload</label>
              </div>
            </form>
          </div>

          <!-- Modal footer -->
          <div class="modal-footer">
            <input
              type="button"
              class="btn btn-warning"
              data-bs-dismiss="modal"
              onclick="memberInsert()"
              value="저장"
            />
            <input type="button" class="btn btn-danger" data-bs-dismiss="modal" value="닫기" />
          </div>
        </div>
      </div>
    </div>
    <!-- ========================== [[ 회원가입 Modal ]] ========================== -->
  </body>
</html>
