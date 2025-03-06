<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

	</div>
</body>
</html>