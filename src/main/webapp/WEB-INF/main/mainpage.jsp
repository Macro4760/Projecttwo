<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

<!-- Custom Styles -->
<style>
    body * {
        font-family: 'Do-Hyeon', sans-serif;
    }
</style>

</head>
<body>

<div>
  <jsp:include page="../../layout/title.jsp"/><br>
  <div class="bg-blue-900 text-white p-6 rounded-lg shadow-lg">
    <h1 class="text-3xl font-bold">Welcome</h1>
  </div>
</div>

<form action="/summoner" method="get" class="flex justify-center items-center min-h-screen mt-50">
    <div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-sm border-2 border-blue-600">
        <h2 class="text-2xl font-bold text-center mb-4">소환사 검색</h2>
        <label for="summonerName" class="block text-lg mb-2">소환사 이름:</label>
        <input type="text" id="summonerName" name="summonerName" required class="w-full p-2 border border-gray-300 rounded-lg mb-4">
        <button type="submit" class="w-full bg-blue-600 text-white p-3 rounded-lg hover:bg-blue-700 transition-colors">검색</button>
    </div>
</form>


</body>
</html>
