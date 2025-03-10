<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>소환사 전적</title>
</head>
<body>
	<!-- 소환사 정보 출력 -->
	<div class="summoner-detail">
		<h1>${summoner.name}의전적</h1>
		<p>소환사 레벨: ${summoner.summonerLevel}</p>
		<img
			src="https://ddragon.leagueoflegends.com/cdn/14.4.1/img/profileicon/${summoner.profileIconId}.png"
			alt="Profile Icon" />
		<p>Region: ${summoner.region}</p>
	</div>

	<!-- 승률 출력 -->
	<p>승률: ${winRate}%</p>

	<!-- 최근 게임 기록 출력 -->
	<h2>최근 게임 기록</h2>
	<table>
		<thead>
			<tr>
				<th>게임 ID</th>
				<th>챔피언</th>
				<th>킬</th>
				<th>데스</th>
				<th>어시스트</th>
				<th>승패</th>
				<th>세부 정보</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${recentMatches}" var="match">
				<tr>
					<td>${match.matchId}</td>
					<td>${match.championId}</td>
					<td>${match.kills}</td>
					<td>${match.deaths}</td>
					<td>${match.assists}</td>
					<td>${match.win ? '승리' : '패배'}</td>
					<td><a href="/matchDetails?matchId=${match.matchId}">세부 정보</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>