<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>댓글 목록</title>

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Caveat:wght@400..700&family=Gaegu&family=Jua&family=Nanum+Pen+Script&family=Playwrite+AU+SA:wght@100..400&family=Single+Day&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">

    <!-- Tailwind CSS -->
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.0.0/dist/tailwind.min.css" rel="stylesheet">

    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">

    <style>
        body {
            font-family: 'Jua', sans-serif;
            background-color: #f3f4f6;
        }
        .comment-container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }
    </style>
</head>
<body>

    <div class="comment-container">
        <h3 class="text-2xl font-bold mb-4">댓글 목록</h3>

        <!-- 댓글 리스트 -->
        <c:forEach var="dto" items="${repleList}">
            <div class="comment p-4 bg-white rounded-lg shadow-md mb-4">
                <p class="font-semibold text-lg">${dto.writer} <span class="text-sm text-gray-500">| ${dto.writeday}</span></p>
                <p class="mt-2">${dto.message}</p>

                <c:choose>
                    <c:when test="${dto.writer == sessionScope.loginemail">
                        <div class="mt-4 flex space-x-2">
                            <button onclick="location.href='./modifyform?idx=${dto.idx}&pageNum=${pageNum}'"
                                    class="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 focus:outline-none">
                                수정
                            </button>
                            <button onclick="location.href='./delete?idx=${dto.idx}&pageNum=${pageNum}'"
                                    class="px-4 py-2 bg-red-500 text-white rounded-md hover:bg-red-600 focus:outline-none">
                                삭제
                            </button>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="mt-4 flex space-x-2">
                            <button type="button" class="px-4 py-2 bg-gray-300 text-white rounded-md cursor-not-allowed">
                                수정
                            </button>
                            <button type="button" class="px-4 py-2 bg-gray-300 text-white rounded-md cursor-not-allowed">
                                삭제
                            </button>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </c:forEach>

        <!-- 댓글 추가 폼 -->
        <div class="mt-8">
            <h4 class="text-xl font-semibold mb-2">댓글 작성</h4>
            <form action="./addreple" method="post" class="space-y-4">
                <textarea name="message" id="message" rows="4" class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" placeholder="댓글을 입력하세요" required></textarea>
                <button type="submit" class="w-full px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 focus:outline-none">
                    댓글 등록
                </button>
            </form>
        </div>
    </div>

</body>
</html>
