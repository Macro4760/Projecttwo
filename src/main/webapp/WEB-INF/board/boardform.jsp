<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>502 jsp study</title>
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
        font-family: 'Jua';
    }
</style>
</head>
<body class="bg-gray-100">
<c:if test="${sessionScope.loginstatus==null}">
	<script>
		alert("로그인이 필요합니다");
		history.back();
	</script>
</c:if>

<jsp:include page="../../layout/title.jsp"/>

<div class="max-w-3xl mx-auto mt-8 p-6 bg-white rounded-lg shadow-lg">
    <h2 class="text-2xl font-bold text-black mb-6">게시글 작성</h2>
    <form action="./insert" method="post" enctype="multipart/form-data">
        <!-- hidden -->
        <input type="hidden" name="idx" value="${idx}">
		<input type="hidden" name="pageNum" value="${pageNum}">
        <!-- 제목 -->
        <div class="mb-4">
            <label for="title" class="block text-lg font-medium text-blue-700">제목</label>
            <input type="text" id="title" name="title" required
                class="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500">
        </div>

        <!-- 공지글 여부 -->
        <div class="mb-4">
            <label for="isNotice" class="block text-lg font-medium text-blue-700">공지글 여부</label>
            <select id="isNotice" name="isNotice"
                class="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500">
                <option value="general">일반글</option> <!-- '일반글'을 선택할 수 있도록 수정 -->
                
                <!-- 'admin' 역할인 경우에만 공지글을 작성할 수 있도록 -->
                <c:if test="${sessionScope.role == 'ADMIN'}">
                    <option value="notice">공지글</option> <!-- '공지글'을 선택할 수 있도록 수정 -->
                </c:if>
            </select>
        </div>

        <!-- 내용 -->
        <div class="mb-4">
            <label for="content" class="block text-lg font-medium text-blue-700">내용</label>
            <textarea id="content" name="content" required
                class="mt-1 block w-full h-60 px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500"></textarea>
        </div>

        <!-- 사진 첨부 -->
        <div class="mb-4">
            <label for="image" class="block text-lg font-medium text-blue-700">사진 첨부</label>
            <input type="file" id="upload" name="upload" multiple
                class="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500">
        </div>

        <!-- 제출 버튼 -->
        <div>
            <button type="submit"
                class="w-full py-2 px-4 bg-blue-700 text-white font-semibold rounded-md shadow-sm hover:bg-blue-800 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2">
                작성
            </button>
        </div>
    </form>
</div>

</body>
</html>
