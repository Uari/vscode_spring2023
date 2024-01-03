<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.util.BSPageBar" %>
<%
	int size = 0;//전체 레코드 수
	List<Map<String,Object>> mList = (List)request.getAttribute("mList");
	if(mList !=null){
		size = mList.size();
	}

  int numPerPage = 3;
	int nowPage = 0;
		if(request.getParameter("nowPage")!=null){
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		} 
%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>회원관리{webapp}</title>
    <%@include file="/common/bootstrap_common.jsp" %>
	<link rel="stylesheet" href="/css/member.css">
  <!-- 카카오 (우편번호 검색)제공 라이브러리 추가{2023-12-29} -->
  <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	
    <script type="text/javascript">

    function searchEnter(){
    console.log('searchEnter')
			if(window.event.keyCode==13){
				memberSearch();
			}
    }
		
		function memberSearch(){
			console.log('memberSearch');
			const gubun = document.querySelector("#gubun").value;
			const keyword = document.querySelector("#keyword").value;
			console.log(`${gubun} , ${keyword}`);
			location.href="/member/memberList?gubun="+gubun+"&keyword="+keyword;
			// document.querySelector('#gubun').value = '분류선택';
    	// document.querySelector('#keyword').value = '';
		}

		const memberInsert = () =>{
			document.querySelector("#f_member").submit();
		}

		const memberDetail = (mem_no) =>{
			//console.log(MEM_NO);
			location.href="/member/memberDetail?mem_no="+mem_no;
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
  </head>
  <body>
    <!-- 공지사항 목록 처리할 코드 작성해 보기 -->
	<!-- header start -->
	<%@include file="/include/gym_header.jsp" %>
	<!-- header end -->
	<div class="container">
		<div class="page-header">
			<h2>회원관리 <small>회원목록</small></h2>
			<hr /> 
		</div>
		<!-- 검색기 시작 -->
		<div class="row">
			<div class="col-3">
		    	<select id="gubun" class="form-select" aria-label="분류선택">
		      		<option value="none">분류선택</option>
		      		<option value="mem_name">이름</option>
		      		<option value="mem_id">아이디</option>
		      		<option value="n_content">내용</option>
		    	</select>			
		  	</div>
			<div class="col-6">
		 		<input type="text" id="keyword" class="form-control" placeholder="검색어를 입력하세요" 
		           aria-label="검색어를 입력하세요" aria-describedby="btn_search" onkeyup="searchEnter()"/>
			</div>
			<div class="col-3">
		 		<button id="btn_search" class="btn btn-danger" onClick="memberSearch()">검색</button>
		 	</div>
		</div>		
		<!-- 검색기 끝 -->
		
		<!-- 회원목록 시작 -->
		<div class='member-list'>
			<table class="table table-hover">
		    	<thead>
		      		<tr>
		        		<th width="10%">#</th>
		        		<th width="50%">이름</th>
		        		<th width="20%">아이디</th>
		      		</tr>
		    	</thead>
		    	<tbody>	      	
<%
	//스크립틀릿 - 지변이다, 메소드 선언불가, 생성자 선언불가, 실행문
	//n건을 조회하는 경우이지만 resultType에는 map이나 vo패턴을 주는게 맞다
	//주의사항 - 자동으로 키값을 생성함 - 디폴트가 대문자이다
	//myBatis연동시 resultType=map{한행}으로 줌 -> selectList("memberList", pMap)
	for(int i=nowPage*numPerPage;i<nowPage*numPerPage+numPerPage; i++){
		if(i == size) break;
		Map<String,Object> rmap = mList.get(i);
%>					
					<tr>
						<td><%=rmap.get("MEM_NO") %></td>
						<td>
							<a href="javascript:memberDetail('<%=rmap.get("MEM_NO") %>')"><%=rmap.get("MEM_NAME") %></a>
						</td>
						<td><%=rmap.get("MEM_ID") %></td>
					</tr>					
<%
	}
%>
		    	</tbody>
			</table> 
    
<!-- [[ 페이징 처리  구간  ]] -->
			<div style="display:flex; justify-content:center;">
				<ul class="pagination">
					<%
						String pagePath = "memberList";
						BSPageBar bspb = new BSPageBar(numPerPage, size, nowPage, pagePath);
						out.print(bspb.getPageBar());
					%>
				</ul>
			</div>
<!-- [[ 페이징 처리  구간  ]] -->		
	  
		  	<div class='member-footer'>
		    	<button class="btn btn-warning" onclick="memberSearch()">
		      		전체조회
		    	</button>&nbsp; 
			    <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#memberForm">
			    회원등록
			    </button>
		    </div>
		</div>		
		<!-- 회원목록   끝  -->		
	</div>
	<!-- body end       -->
	<!-- footer start -->
	<%@include file="/include/gym_footer.jsp" %>
	<!-- footer end    -->	
	<!-- ========================== [[ 회원가입 Modal ]] ========================== -->
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
    <!-- ========================== [[ 공지사항 Modal ]] ========================== -->

  </body>
</html>