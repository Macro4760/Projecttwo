<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>userform</title>
<!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css2?family=Caveat:wght@400..700&family=Gaegu&family=Jua&family=Nanum+Pen+Script&family=Playwrite+AU+SA:wght@100..400&family=Single+Day&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">

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

    <style>
        body *{
            font-family: 'Pretendard', sans-serif;
        }
        
        /* 이메일 중복 체크 아이콘 옆 위치 조정 */
        #emailAlert {
            font-size: 0.9rem;
            color: red;
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
     <script>
     	let jungbok=false;
     	
     	$(function(){
     		//중복버튼 이벤트
     		$("#btnidcheck").click(function(){
     			let email=$("#email").val();
     			$.ajax({
     				type:"get",
     				dataType:"json",
     				data:{"email":email},
     				url:"./emailcheck",
     				success:function(res){
     					if(res.result=='success'){
     						jungbok=true;
     						alert("사용가능한 이메일입니다");     						
     					}else{
     						jungbok=false;
     						alert("존재하는 이메일입니다\n다시 입력해주세요");
     						$("#email").val("");
     					}
     				}
     			});
     			
     		});
     		
     		//아이디를 입력시 중복변수 다시 false로
     		$("#email").keyup(function(){
     			jungbok=false;
     		});
     	});
     	
     	function check(){
     		let p1=$("#password").val();
     		let p2=$("#password2").val();
     		if(p1!=p2){
     			alert("비밀번호가 맞지 않습니다");
     			return false; //false로 주면 action 으로 안넘어감
     		}
     		
     		if(!jungbok){
     			alert("중복체크 버튼을 눌러주세요");
     			return false;
     		}
     	}
     </script>
</head>
<body class="bg-gray-100">

    <!-- 전체 컨테이너 -->
    <div>
    <jsp:include page="../../layout/title.jsp"/></div>
    <div class="flex flex-col items-center justify-center min-h-screen bg-gray-100">
       
         <!-- 타이틀 섹션 -->
        <div class="w-full bg-blue-900 text-white py-6">
            <h2 class="text-4xl font-semibold text-center">이메일 중복 체크</h2>
        </div>

        <!-- 회원가입 폼 섹션 -->
        <form action="/signup" method="post" enctype="multipart/form-data" onsubmit="return check()">
        <div class="bg-white shadow-lg rounded-lg p-8 mt-8 w-full max-w-lg">
            <div class="mb-6">
                <label for="email" class="block text-xl font-medium text-gray-700">이메일 주소</label>
                <div class="flex items-center">
                    <input type="email" id="email" name="email" class="mt-2 p-4 w-full border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500" placeholder="이메일을 입력하세요" required>
                    <button id="btnidcheck" type="button" class="ml-2 py-2 px-4 bg-green-600 text-white rounded-lg hover:bg-green-700 focus:outline-none">중복 확인</button>
                </div>
                <div id="emailAlert"></div>
            </div>

            <!-- 비밀번호 입력 폼 -->
            <div class="mb-6">
                <label for="password" class="block text-xl font-medium text-gray-700">비밀번호</label>
                <input type="password" id="password" name="password1" class="mt-2 p-4 w-full border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500" placeholder="비밀번호를 입력하세요" required>
            </div>

            <div class="mb-6">
                <label for="confirmPassword" class="block text-xl font-medium text-gray-700">비밀번호 확인</label>
                <input type="password" id="password2" name="password2" class="mt-2 p-4 w-full border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500" placeholder="비밀번호를 다시 입력하세요" required>
            </div>

            <!-- 프로필 사진 업로드 -->
            <div class="mb-6">
                <label for="profilePic" class="block text-xl font-medium text-gray-700">프로필 사진</label>
                <input type="file" id="profilePic" name="profilePic" class="mt-2 p-4 w-full border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500">
            </div>

            <div class="flex items-center justify-between">
                <button type="submit" class="w-full py-3 bg-blue-900 text-white rounded-lg hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500">회원가입</button>
            </div>
        </div>
        </form>
    </div>

</body>
</html>
