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
<<<<<<< HEAD
<jsp:include page="../layout/title.jsp"/>
=======
	<jsp:include page="../layout/title.jsp"></jsp:include>
>>>>>>> branch 'main' of https://github.com/Macro4760/Projecttwo.git
    <h1>Champion List</h1>

    <!-- 검색 입력 필드 -->
    <div style="text-align: center; margin-bottom: 20px;">
        <input type="text" id="searchInput" placeholder="챔피언 이름 검색" onkeyup="filterChampions()" 
               style="padding: 10px; width: 300px; font-size: 1em; border: 1px solid #ccc; border-radius: 5px;">
    </div>

    <div class="champion-list" id="championList">
        <c:forEach var="champion" items="${championList}">
            <div class="champion-card" data-name="${champion.name}">
                <a href="/champion/detail/${champion.id}">
                    <img src="https://ddragon.leagueoflegends.com/cdn/14.4.1/img/champion/${champion.image}" alt="${champion.name}" />
                    <div class="champion-name">${champion.name}</div>
                    <div class="champion-title">${champion.title}</div>
                </a>
            </div>
        </c:forEach>
    </div>

    <script>
        function filterChampions() {
            let input = document.getElementById("searchInput").value.toLowerCase();
            let cards = document.querySelectorAll(".champion-card");

            cards.forEach(card => {
                let name = card.getAttribute("data-name").toLowerCase();
                if (name.includes(input)) {
                    card.style.display = "block";  // 검색어 포함 시 표시
                } else {
                    card.style.display = "none";   // 검색어 없으면 숨김
                }
            });
        }
    </script>
</body>


</html>