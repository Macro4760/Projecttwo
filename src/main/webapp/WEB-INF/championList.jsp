<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>챔피언 목록</title>
    <style>
        .champion-container {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
        }
        .champion-card {
            width: 200px;
            border: 1px solid #ddd;
            padding: 10px;
            text-align: center;
        }
        .champion-card img {
            width: 100%;
            height: auto;
        }
    </style>
</head>
<body>
    <h1>챔피언 목록</h1>
    
        <c:forEach var="champion" items="${champions}">
            <div class="champion-card">
                <h2>${champion.name}</h2>
                <h3>${champion.title}</h3>
                <p>${champion.blurb}</p>
                <img src="${champion.image}" alt="${champion.name}">
            </div>
        </c:forEach>
    </div>
</body>
</html>