<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>${champion.name}-챔피언분석</title>
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: 'Arial', sans-serif;
}

body {
	background-color: #f9fafb;
	color: #333;
	font-size: 16px;
}

.container {
	max-width: 1200px;
	margin: 20px auto;
	padding: 20px;
	background-color: white;
	border-radius: 8px;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.champion-header {
	display: flex;
	align-items: center;
	justify-content: space-between;
	margin-bottom: 30px;
	border-bottom: 2px solid #eee;
	padding-bottom: 20px;
}

.champion-header img {
	border-radius: 8px;
	width: 250px;
	height: 250px;
	object-fit: cover;
}

.champion-info {
	flex: 1;
	margin-left: 20px;
}

.champion-info h2 {
	font-size: 2rem;
	font-weight: bold;
	color: #5c6bc0;
	margin-bottom: 10px;
}

.champion-info p {
	font-size: 1.2rem;
	color: #555;
}

.back-btn {
	display: inline-block;
	padding: 10px 20px;
	background-color: #5c6bc0;
	color: white;
	border-radius: 5px;
	text-decoration: none;
	font-weight: bold;
	margin-top: 20px;
}

.back-btn:hover {
	background-color: #3f4c99;
}

.skills h3 {
	font-size: 1.8rem;
	color: #5c6bc0;
	margin-bottom: 15px;
	font-weight: bold;
}

.skill-list {
	display: grid;
	grid-template-columns: repeat(4, 1fr);
	gap: 20px;
}

.skill {
	text-align: center;
	background-color: #fff;
	padding: 15px;
	border-radius: 8px;
	border: 1px solid #ddd;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	transition: all 0.3s ease;
}

.skill:hover {
	transform: translateY(-5px);
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15);
}

.skill img {
	width: 50px;
	height: 50px;
	margin-bottom: 15px;
}

.skill p {
	font-size: 1rem;
	color: #555;
}

.rating-section {
	margin-top: 40px;
	border-top: 2px solid #eee;
	padding-top: 20px;
}

#star-rating {
	display: flex;
	justify-content: center;
	gap: 10px;
	margin-bottom: 20px;
	align-items: center;
}

#star-rating i {
	font-size: 2rem;
	cursor: pointer;
	color: #ddd;
}

#star-rating i.bi-star-fill {
	color: #ffbc00;
}

.comment-section {
	margin-top: 40px;
}

.comment-section h3 {
	font-size: 1.8rem;
	color: #5c6bc0;
	margin-bottom: 15px;
	font-weight: bold;
}

.comments-list {
	margin-bottom: 20px;
}

.comment {
	background-color: #fff;
	padding: 15px;
	border-radius: 8px;
	border: 1px solid #ddd;
	margin-bottom: 10px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

.rating-container {
	display: flex;
	align-items: center; /* 세로 정렬 */
	gap: 10px; /* 별점과 버튼 사이의 간격 조정 */
}

.comment strong {
	font-weight: bold;
	color: #5c6bc0;
}

.comment-form {
	display: flex;
	flex-direction: column;
	gap: 15px;
}

/* 댓글 입력란 스타일 */
.comment-input-container {
	display: flex; /* 가로로 배치 */
	gap: 10px; /* 댓글 입력란과 버튼 사이 간격 */
	align-items: center;
}

textarea {
	width: 100%; /* 부모 컨테이너에 맞춰서 넓이 100% */
	max-width: 600px; /* 최대 너비 설정 */
	min-width: 300px; /* 최소 너비 설정 */
	padding: 10px;
	font-size: 1rem;
	border-radius: 5px;
	border: 1px solid #ddd;
	resize: none; /* 크기 조절 비활성화 */
}

.submit-btn {
	background-color: #5c6bc0;
	color: white;
	padding: 10px;
	border-radius: 5px;
	border: none;
	font-size: 1.2rem;
	cursor: pointer;
	transition: background-color 0.3s ease;
	margin-left: 10px;
}

.submit-btn:hover {
	background-color: #3f4c99;
}

.submit-btn:disabled {
	background-color: #ccc;
	cursor: not-allowed;
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
				<a href="javascript:history.back()" class="back-btn">뒤로가기</a>
			</div>
		</div>

		<div class="skills">
			<h3>스킬 정보</h3>
			<div class="skill-list">
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
		</div>
		<div class="rating-section">
			<h3>평점 주기</h3>
			<div id="star-rating">
				<i class="bi bi-star" data-value="1"></i> <i class="bi bi-star"
					data-value="2"></i> <i class="bi bi-star" data-value="3"></i> <i
					class="bi bi-star" data-value="4"></i> <i class="bi bi-star"
					data-value="5"></i>
			</div>
			<input type="hidden" id="ratingValue" name="rating" value="0">
			<button type="button" class="submit-btn" onclick="submitRating()">평점
				등록</button>

			<div class="rating-summary">
				<h3>평균 평점</h3>
				<p>
					<strong id="avgRating">${avgRating != null ? avgRating : '0.0'}</strong>
					/ 5.0 (<span id="ratingCount">${ratingCount != null ? ratingCount : '0'}</span>명
					참여)
				</p>
			</div>
		</div>




		<div class="comment-section">
			<h3>댓글 입력</h3>
			<form id="commentForm">
				<input type="hidden" id="championId" name="championId"
					value="${champion.id}"> <input type="hidden" id="userId"
					name="userId" value="testUser123">
				<div class="comment-input-container">
					<textarea name="comment" id="comment" placeholder="댓글을 입력하세요..."
						rows="3"></textarea>
					<button type="button" class="submit-btn" onclick="submitComment()">댓글
						등록</button>
				</div>
			</form>
		</div>

		<!-- 🛑 댓글이 표시될 영역 추가! -->
		<div id="commentsList" class="comments-container">
			<!-- 여기에 댓글이 동적으로 추가됨 -->
		</div>


	</div>

	<script>
		$(document).ready(function() {
		    // 별 클릭 시 평점 값 업데이트
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

				// 평점 등록 함수
				function submitRating() {
			    var championId = "${champion.id}"; // 챔피언 ID
			    var rating = $("#ratingValue").val(); // 평점
			    
			    if (rating == 0) {
			        alert("평점을 선택하세요!");
			        return;
			    }
			
			    $.post("/champion/rating", {
			        championId: championId,
			        rating: rating
			    }, function(response) {
			        // 평점 등록 완료 후 알림
			        alert("평점이 등록되었습니다!");
			
			        // 평균 평점과 참여자 수 갱신
			        $("#avgRating").text(response.avgRating); // 평균 평점 갱신
			        $("#ratingCount").text(response.ratingCount); // 참여자 수 갱신
			
			        // 선택한 별점 다시 채우기
			        var selectedValue = $("#ratingValue").val();
			        $("#star-rating i").each(function() {
			            var starValue = $(this).data("value");
			            if (starValue <= selectedValue) {
			                $(this).removeClass("bi-star").addClass("bi-star-fill");
			            } else {
			                $(this).removeClass("bi-star-fill").addClass("bi-star");
			            }
			        });
			
			    }).fail(function(xhr, status, error) {
			        // 실패 시 서버 응답 확인
			        console.log("Error: " + error);
			        console.log("Status: " + status);
			        console.log("Response: " + xhr.responseText);
			        alert("평점 등록 실패!");
			    });
			}


				$("#comment").on("input", function() {
				    if ($(this).val().trim() === "") {
				        $(".submit-btn").prop("disabled", true);  // 비활성화
				    } else {
				        $(".submit-btn").prop("disabled", false);  // 활성화
				    }
				});
				
				
				// 댓글 목록 새로고침 (이걸 먼저 정의!)
				function loadComments() {
				    var championId = $("#championId").val();

				    $.get("/champion/comments", { championId: championId }, function(response) {
				        console.log(response); // 응답 데이터 확인
				        var commentsList = $("#commentsList");
				        commentsList.empty();  // 기존 댓글 목록 초기화

				        response.comments.forEach(function(comment) {
				            var commentHtml = `
				                <div class="comment">
				                    <strong>${comment.userId}</strong> <span>${comment.timestamp}</span>
				                    <p>${comment.commentText}</p>
				                </div>
				            `;
				            commentsList.append(commentHtml);  // 댓글 추가
				        });
				    }).fail(function(xhr, status, error) {
				        console.log("Error: " + error);
				        alert("댓글 목록을 불러오지 못했습니다.");
				    });
				}


				function submitComment() {
				    var championId = $("#championId").val();
				    var userId = $("#userId").val();
				    var commentText = $("#comment").val().trim();

				    console.log("🏆 Champion ID:", championId);
				    console.log("👤 User ID:", userId);
				    console.log("💬 Comment Text:", commentText);

				    if (!championId) {
				        alert("❌ 챔피언 ID가 없습니다!");
				        return;
				    }
				    if (!userId) {
				        alert("❌ 로그인 후 댓글을 작성해주세요!");
				        return;
				    }
				    if (commentText === "") {
				        alert("❌ 댓글을 입력해주세요!");
				        return;
				    }

				    // AJAX 요청
				    $.ajax({
				        type: "POST",
				        url: "/champion/comment",
				        contentType: "application/json",
				        data: JSON.stringify({
				            championId: championId,
				            userId: userId,
				            comment: commentText
				        }),
				        success: function(response) {
				            console.log("✅ 댓글 등록 성공:", response);
				            alert("댓글이 등록되었습니다!");
				            loadComments();
				            $("#comment").val("");
				        },
				        error: function(xhr, status, error) {
				            console.log("❌ 댓글 등록 실패:", error);
				            console.log(xhr.responseText);
				            alert("댓글 등록 실패!");
				        }
				    });
				}



        </script>

	</div>
</body>
</html>