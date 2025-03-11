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
    body * {
        font-family: 'pretendard', sans-serif;
    }
</style>
</head>
<body>
<jsp:include page="../../layout/title.jsp"></jsp:include>
   <div class="container mx-auto p-4">
    <h2 class="text-2xl font-bold mb-4">게시판</h2>

    <!-- ✅ "작성하기" 버튼 (모든 사용자에게 보이도록 변경) -->
    <div class="flex justify-end mb-4">
        <a href="/board/form" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-700">
            작성하기
        </a>
    </div>

    <!-- 게시판 목록 테이블 -->
    <table class="w-full border-collapse border border-gray-300">
        <thead>
            <tr class="bg-gray-200">
                <th class="border border-gray-300 px-4 py-2">글번호</th>
                <th class="border border-gray-300 px-4 py-2">글제목</th>
                <th class="border border-gray-300 px-4 py-2">작성자</th>
                <th class="border border-gray-300 px-4 py-2">작성일자</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="board" items="${boardlist}">
                <tr>
                    <td class="border border-gray-300 px-4 py-2 text-center">${nboard.idx}</td>
                    <td class="border border-gray-300 px-4 py-2">
                        <a href="/board/view?idx=${nboard.idx}" class="text-blue-600 hover:underline">
                            ${nboard.title}
                        </a>
                    </td>
                    <td class="border border-gray-300 px-4 py-2 text-center">${nboard.nickname}</td>
                    <td class="border border-gray-300 px-4 py-2 text-center">${nboard.created_at}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>