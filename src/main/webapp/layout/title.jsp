<!-- title.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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

<head>
    <meta charset="UTF-8">
    <title>LoL 챔피언 분석</title>
</head>
<body>
    <nav class="bg-blue-900 text-white p-4 shadow-lg">
        <div class="container mx-auto">
            <!-- 로고와 제목을 float로 왼쪽 정렬 -->
            <img src="../save/Logo.webp" alt="Logo" class="h-8 float-left mr-2"> <!-- 로고 왼쪽 정렬과 마진 추가 -->
            <h1 class="text-2xl font-bold float-left">Champion Cordex</h1>
            <ul class="flex space-x-6 clear-both"> <!-- float 효과를 해제하려면 clear-both 추가 -->
                <li><a href="/" class="hover:text-blue-300">메인</a></li>
                <li><a href="/notice" class="hover:text-blue-300">공지사항</a></li>
                <li><a href="/champions" class="hover:text-blue-300">챔피언</a></li>
                <li><a href="/login" class="hover:text-blue-300">로그인</a></li>
                <li><a href="/user/form" class="hover:text-blue-300">회원가입</a></li>
                <li><a href="/shop" class="hover:text-blue-300">롤 굿즈샵</a></li>
            </ul>
        </div>
    </nav>
</body>
</html>
