<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>Champion List</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f6f8fa;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            color: #3d3d3d;
            padding: 20px;
            font-size: 2em;
        }

        .champion-list {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
            gap: 20px;
            padding: 20px;
            justify-items: center;
            margin: 0 auto;
            max-width: 1200px;
        }

        .champion-card {
            border-radius: 12px;
            background-color: white;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 20px;
            text-align: center;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            cursor: pointer;
        }

        .champion-card:hover {
            transform: scale(1.05);
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
        }

        .champion-card img {
            width: 120px;
            height: 120px;
            border-radius: 50%;
            object-fit: cover;
            margin-bottom: 10px;
        }

        .champion-name {
            font-size: 1.2em;
            font-weight: 600;
            color: #333;
            margin: 10px 0;
        }

        .champion-title {
            font-size: 0.9em;
            color: #777;
            margin-bottom: 10px;
        }

        .champion-card a {
            text-decoration: none;
            color: inherit;
        }

        .champion-card:active {
            transform: scale(0.98);
        }
    </style>
</head>
<body>
	<jsp:include page="../layout/title.jsp"></jsp:include>
    <h1>Champion List</h1>
    <div class="champion-list">
        <c:forEach var="champion" items="${championList}">
            <div class="champion-card">
                <a href="/champion/detail/${champion.id}">
                    <img src="https://ddragon.leagueoflegends.com/cdn/14.4.1/img/champion/${champion.image}" alt="${champion.name}" />
                    <div class="champion-name">${champion.name}</div>
                    <div class="champion-title">${champion.title}</div>
                </a>
            </div>
        </c:forEach>
    </div>
</body>

</html>