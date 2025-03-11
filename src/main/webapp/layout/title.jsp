<!-- title.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>
<html>
<!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css2?family=Caveat:wght@400..700&family=Gaegu&family=Jua&family=Nanum+Pen+Script&family=Playwrite+AU+SA:wght@100..400&family=Single+Day&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;700&display=swap" rel="stylesheet">

<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>

<!-- Bootstrap Icons -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">

<!-- Pretendard Font -->
<link href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/static/pretendard.css" rel="stylesheet">

<!-- Tailwind CSS (Corrected to use the proper link tag) -->
<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.0.0/dist/tailwind.min.css" rel="stylesheet">

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<style>
    body * {
        font-family: 'Do-hyeon', sans-serif;
    }
</style>
<head>
    <meta charset="UTF-8">
    <title>LoL 챔피언 분석</title>
</head>
<!-- 프로젝트 경로 구하기-절대경로를 위한코드 -->
<c:set var="root" value="${pageContext.request.contextPath}"/>
<body>
<!-- The Login Modal -->
<div class="modal" id="myLoginModal">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">회원 로그인</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
      <form id="loginfrm">
	        <table class="logintab">
	        	<tbody>
	        		<tr>
	        			<th width="80">이메일</th>
	        			<td>
	        				<input type="text" id="loginemail" placeholder="이메일"
	        				 class="form-control" required="required">
	        			</td>
	        		</tr>
	        		<tr>
	        			<th width="80">비밀번호</th>
	        			<td>
	        				<input type="password" id="loginpass" placeholder="비밀번호"
	        				 class="form-control" required="required">
	        			</td>
	        		</tr>
	        		<tr>
	        			<td colspan="2" align="center">
							<button type="submit" class="btn btn-sm btn-success">로그인</button>
						</td>
	        		</tr>
	        	</tbody>
	        </table>
        </form>
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger btnclose" data-bs-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<!-- 네비게이션 바 -->
	<nav class="bg-blue-900 text-white p-4 shadow-lg">
		<div class="container mx-auto flex items-center justify-between">
			<img src="https://kr.object.ncloudstorage.com/bitcamp.bucket/logo/logo2.webp" alt="Logo" class="h-12 mr-4">

			<!-- 제목을 클릭하면 메인 페이지(${root})로 이동 -->
			<h1 class="text-4xl font-bold">
				<a href="${root}/" class="hover:text-blue-300">TEMU.GG</a>
			</h1>

			<ul class="flex space-x-6 ml-auto">
				<!-- 로그인한 상태에서만 표시 -->
				<c:if test="${sessionScope.loginstatus!=null}">
					<c:set var="naverurl"
						value="https://kr.object.ncloudstorage.com/bitcamp.bucket" />
					<img src="${naverurl}/user/${sessionScope.loginphoto}"
						class="profilephoto inline-block w-10 h-10 rounded-full" style="cursor:pointer;"
						onerror="this.src='${naverurl}/user/noimage.png'">
					<script>
						$(".profilephoto").click(function() {
							location.href = `${root}/user/mypage`;
						});
					</script>
					<b class="ml-2 font-semibold">${sessionScope.loginnickname}</b>
				</c:if>
				<li><a href="/champion/list" class="hover:text-blue-300">챔피언</a></li>
				<li><c:if test="${sessionScope.loginstatus==null}">
						<span data-bs-toggle="modal" data-bs-target="#myLoginModal"
							style="cursor: pointer;">로그인</span>
					</c:if> <c:if test="${sessionScope.loginstatus!=null}">
						<span style="cursor: pointer" id="logout">로그아웃</span>
					</c:if></li>
				<li><a href="/user/form" class="hover:text-blue-300">회원가입</a></li>
				<li><a href="/board/list" class="hover:text-blue-300">커뮤니티</a></li>
				<li><a href="/shop" class="hover:text-blue-300">롤 굿즈샵</a></li>
			</ul>
		</div>
	</nav>

	<script type="text/javascript">
    $(".loginphoto").click(function(){
        location.href = `${root}/user/mypage`;
    });

    $("#loginfrm").submit(function(e){
		e.preventDefault();//서브밋의 기본이벤트를 무효화(action호출)
		//alert("submit");
		let loginemail=$("#loginemail").val();
		let loginpass=$("#loginpass").val();
		
		$.ajax({
			type:"get",
			dataType:"json",
			data:{"loginemail":loginemail,"loginpass":loginpass},
			url:"${root}/user/login",
			success:function(res){
				if(res.result=='success'){
					//값 초기화
					$("#loginemail").val("");
					$("#loginpass").val("");
					//모달창 닫기
					$(".btnclose").trigger("click");
					//새로고침
					location.reload();
				}else{
					alert("아이디나 비밀번호가 맞지 않습니다");
					$("#loginpass").val("");
				}
			}
		});
	});

	$("#logout").click(function(){
		$.ajax({
			type:"get",
			dataType:"text",
			url:"${root}/user/logout",
			success:function(res){
				//새로고침
				location.reload();
			}
		});
	});
</script>
<hr style="clear: both;">
</body>
</html>
