<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>${champion.name}-챔피언 분석</title>
<style>
body {
	background-color: #0a0a0a;
	color: #ffffff;
	font-family: 'Arial', sans-serif;
	margin: 0;
	padding: 0;
}

.container {
	max-width: 900px;
	margin: 50px auto;
	padding: 20px;
	background: rgba(0, 0, 0, 0.8);
	border-radius: 10px;
	box-shadow: 0 0 15px rgba(255, 255, 255, 0.2);
}

.champion-header {
	display: flex;
	align-items: center;
	gap: 20px;
}

.champion-header img {
	width: 150px;
	height: 150px;
	border-radius: 10px;
	border: 2px solid #ffd700;
}

.champion-info h2 {
	font-size: 2.5em;
	margin: 0;
}

.champion-info p {
	font-size: 1.2em;
	color: #bbb;
}

.skills {
	margin-top: 30px;
}

.skills h3 {
	color: #ffd700;
}

.skill {
	display: flex;
	align-items: center;
	margin-bottom: 15px;
}

.skill img {
	width: 50px;
	height: 50px;
	border-radius: 5px;
	margin-right: 15px;
}

.skill p {
	color: #ddd;
	margin: 0;
}
#star-rating i {
    cursor: pointer; /* 클릭 가능한 느낌을 주는 스타일 */
}
</style>
</head>

<body>

	<div class="container">
		<div class="champion-header">
			<img
				src="https://ddragon.leagueoflegends.com/cdn/14.4.1/img/champion/${champion.id}.png"
				alt="${champion.name}">
			<div class="champion-info">
				<h2>${champion.name}</h2>
				<p>${champion.title}</p>
			</div>
		</div>



		<div class="skills">
			<h3>스킬 정보</h3>
			<div class="skill">
				<img
					src="https://ddragon.leagueoflegends.com/cdn/14.4.1/img/spell/${champion.skillQImage}"
					alt="${champion.skillQName}">
				<p>${champion.skillQDescription}</p>
			</div>
			<div class="skill">
				<img
					src="https://ddragon.leagueoflegends.com/cdn/14.4.1/img/spell/${champion.skillWImage}"
					alt="${champion.skillWName}">
				<p>${champion.skillWDescription}</p>
			</div>
			<div class="skill">
				<img
					src="https://ddragon.leagueoflegends.com/cdn/14.4.1/img/spell/${champion.skillEImage}"
					alt="${champion.skillEName}">
				<p>${champion.skillEDescription}</p>
			</div>
			<div class="skill">
				<img
					src="https://ddragon.leagueoflegends.com/cdn/14.4.1/img/spell/${champion.skillRImage}"
					alt="${champion.skillRName}">
				<p>${champion.skillRDescription}</p>
			</div>
		</div>
		<div class="rating-section">
		    <h3>평점 주기</h3>
		    <div id="star-rating">
		        <i class="bi bi-star" data-value="1"></i>
		        <i class="bi bi-star" data-value="2"></i>
		        <i class="bi bi-star" data-value="3"></i>
		        <i class="bi bi-star" data-value="4"></i>
		        <i class="bi bi-star" data-value="5"></i>
		    </div>
		    <input type="hidden" id="ratingValue" name="rating" value="0">
		    <button type="button" onclick="submitRating()">평점 등록</button>
		</div>
		<script>
		    $(document).ready(function() {
		        $("#star-rating i").on("click", function() {
		            var selectedValue = $(this).data("value");
		            $("#ratingValue").val(selectedValue); // hidden input에 값 저장

		            // 선택한 별 이하를 채운 별로 변경
		            $("#star-rating i").each(function() {
		                var starValue = $(this).data("value");
		                if (starValue <= selectedValue) {
		                    $(this).removeClass("bi-star").addClass("bi-star-fill");
		                } else {
		                    $(this).removeClass("bi-star-fill").addClass("bi-star");
		                }
		            });
		        });
		    });

		    function submitRating() {
		        var championId = "${champion.id}";
		        var rating = $("#ratingValue").val();

		        if (rating == 0) {
		            alert("평점을 선택하세요!");
		            return;
		        }

		        $.post("/champion/rating", { championId: championId, rating: rating }, function(response) {
		            alert("평점이 등록되었습니다!");
		        }).fail(function() {
		            alert("평점 등록 실패!");
		        });
		    }
		</script>

		<div class="comment-section">
		    <h3>댓글 입력</h3>
		    <form id="commentForm">
		        <input type="hidden" name="championId" value="${champion.id}">
		        <textarea name="comment" id="comment" placeholder="댓글을 입력하세요..." rows="3"></textarea>
		        <button type="button" onclick="submitComment()">댓글 등록</button>
		    </form>
		</div>

		<!-- 댓글 및 평점 등록을 위한 AJAX 스크립트 -->
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		<script>
			function submitRating() {
			    var championId = "${champion.id}"; // championId 값 확인
			    var rating = $("#ratingValue").val(); // rating 값 확인

			    // rating이 0이면 경고창을 띄운다.
			    if (rating == 0) {
			        alert("평점을 선택하세요!");
			        return;
			    }

			    // 보내는 데이터 확인 (championId와 rating)
			    console.log("Champion ID: " + championId + " Rating: " + rating);

			    $.post("/champion/rating", { championId: championId, rating: rating }, function(response) {
			        alert("평점이 등록되었습니다!");
			    }).fail(function() {
			        alert("평점 등록 실패!");
			    });
			}
		</script>

	</div>
</body>
</html>