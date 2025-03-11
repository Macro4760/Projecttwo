<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page</title>

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css2?family=Caveat:wght@400..700&family=Gaegu&family=Jua&family=Nanum+Pen+Script&family=Playwrite+AU+SA:wght@100..400&family=Single+Day&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap"
	rel="stylesheet">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>

<!-- Bootstrap Icons -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">

<!-- Pretendard Font -->
<link
	href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/static/pretendard.css"
	rel="stylesheet">

<!-- Tailwind CSS (Corrected to use the proper link tag) -->
<link
	href="https://cdn.jsdelivr.net/npm/tailwindcss@2.0.0/dist/tailwind.min.css"
	rel="stylesheet">

<style>
body * {
	font-family: 'Pretendard', sans-serif;
}

/* 프로필 사진 업로드 버튼 스타일 */
.profile-upload-btn {
	background-color: #1D4ED8; /* blue-700 */
	color: white;
	padding: 10px 20px;
	border-radius: 5px;
	cursor: pointer;
}

.profile-upload-btn:hover {
	background-color: #2563EB; /* hover color */
}
</style>
</head>
<body class="bg-gray-100">
	<jsp:include page="../../layout/title.jsp" />

	<div
		class="flex flex-col items-center justify-center min-h-screen bg-gray-100">
		<!-- 타이틀 섹션 -->
		<div class="w-full bg-blue-900 text-white py-6">
			<h2 class="text-4xl font-semibold text-center">마이페이지</h2>
		</div>

		<!-- 마이페이지 폼 섹션 -->
		<form>
			<div class="bg-white shadow-lg rounded-lg p-8 mt-8 w-full max-w-lg">
				<!-- 닉네임 입력 폼 -->
				<input type="hidden" id="id" value="${dto.id}">
				<div class="mb-6">
					<label for="nickname"
						class="block text-xl font-medium text-gray-700">닉네임</label> <input
						type="text" id="nickname" name="nickname"
						class="mt-2 p-4 w-full border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
						value="${dto.nickname}" required>
				</div>

				<!-- 이메일 입력 폼 -->
				<div class="mb-6">
					<label for="email" class="block text-xl font-medium text-gray-700">이메일
						주소</label> <input type="email" id="email" name="email"
						class="mt-2 p-4 w-full border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
						value="${dto.email}" disabled required>
				</div>

				<!-- 비밀번호 입력 폼 -->
				<div class="mb-6">
					<label for="password"
						class="block text-xl font-medium text-gray-700">비밀번호</label> <input
						type="password" id="password" name="password"
						class="mt-2 p-4 w-full border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
						placeholder="비밀번호를 입력하세요">
				</div>

				<div class="mb-6">
					<label for="confirmPassword"
						class="block text-xl font-medium text-gray-700">비밀번호 확인</label> <input
						type="password" id="password2" name="password2"
						class="mt-2 p-4 w-full border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
						placeholder="비밀번호를 다시 입력하세요">
				</div>
				<!-- 프로필 사진 변경 -->
				<div class="mb-6">
					<label for="profilePhoto"
						class="block text-xl font-medium text-gray-700">프로필 사진</label> <input
						type="file" id="profilePhoto" name="upload"
						class="mt-2 p-4 w-full border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
						onchange="preview(this)"> <br>
					<!-- 기존 프로필 사진을 표시 -->
					<img src="${naverurl}/user/${sessionScope.loginphoto}" id="showimg">
			
				</div>

				<%--    <!-- 선호 챔피언 -->
        <div class="mb-6">
            <label for="favoriteChampion" class="block text-xl font-medium text-gray-700">선호 챔피언</label>
            <select name="favoriteChampion" id="favorite-champion" class="w-full p-2 border rounded-md h-32 overflow-auto">
                <c:forEach var="champion" items="${champions}">
                    <option value="${dto.champion.id}" <c:if test="${champion.id == user.favoriteChampion.id}">selected</c:if>>${champion.name}</option>
                </c:forEach> 
				</select>
			</div>--%>

				<!-- 회원 탈퇴 -->
				<div class="text-center mb-6">
					<button type="button" id="btndelete"
						class="px-4 py-2 bg-red-600 text-white rounded-md hover:bg-red-700">
						회원 탈퇴</button>
				</div>
			

				<!-- 저장 버튼 -->
				<button type="submit"
					class="w-full py-3 bg-blue-900 text-white rounded-lg hover:bg-indigo-700  focus:outline-none focus:ring-2 focus:ring-indigo-500" id="btnupdate">
					정보수정</button>
			</div>		
		</form>
	<script>
        // 프로필 사진 미리보기
        function preview(tag) {
            let f = tag.files[0];
            if (!f.type.match("image.*")) {
                alert("이미지 파일만 가능합니다");
                return;
            }
            
            if (f) {
                let reader = new FileReader();
                reader.onload = function (e) {
                    $("#showimg").attr("src", e.target.result);
                }
                reader.readAsDataURL(f);
            }
        }
        $("#btnupdate").click(function(e) {
        	e.preventDefault();
        	
        	var formData = new FormData();
        	formData.append("id", $("#id").val());
        	formData.append("nickname", $("#nickname").val());
        	formData.append("password", $("#password").val());
        	formData.append("upload", $("#profilePhoto")[0].files[0]);
        	console.log($("#profilePhoto")[0].files[0]);
        	
            $.ajax({
                url: "/user/update",
                type: "POST",
                data: formData,
                processData: false,
                contentType: false,
                success:function(){
                	location.href= "/";
                }
            });
        });
        // 회원 탈퇴 요청
        $("#btndelete").click(function () {
            // 탈퇴 확인 메시지
            var confirmation = confirm("정말로 회원 탈퇴를 하시겠습니까?");
            if (confirmation) {
                var formData = new FormData();
                formData.append("id", $("#id").val());  // 사용자 ID를 폼 데이터에 추가

                $.ajax({
                    url: "/user/delete",  // 탈퇴 처리 URL
                    type: "POST",  // POST 방식으로 요청
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function (response) {
                        alert("회원 탈퇴가 완료되었습니다.");
                        location.href = "/";  // 탈퇴 후 홈으로 리디렉션
                    }
                });
            }
        });
    </script>

</body>
</html>
