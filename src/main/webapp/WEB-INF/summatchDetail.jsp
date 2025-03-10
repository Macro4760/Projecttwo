<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>소환사 전적</title>
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
            background-color: #1e1e2f;
            color: #fff;
            text-align: center;
            margin: 0;
            padding: 20px;
        }
        h1, h2 {
            color: #ffcc00;
        }
        .summoner-detail {
            background: rgba(255, 255, 255, 0.1);
            padding: 20px;
            border-radius: 10px;
            display: inline-block;
            text-align: left;
            margin-bottom: 20px;
        }
        img {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            border: 3px solid #ffcc00;
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            border: 1px solid #444;
        }
        th {
            background: #ffcc00;
            color: #000;
        }
        td {
            background: rgba(255, 255, 255, 0.1);
        }
        a {
            color: #ffcc00;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

    <div class="summoner-detail">
        <h1>${summoner.name}의 전적</h1>
        <p>소환사 레벨: ${summoner.summonerLevel}</p>
        <img src="https://ddragon.leagueoflegends.com/cdn/14.4.1/img/profileicon/${summoner.profileIconId}.png" alt="Profile Icon" />
        <p>Region: ${summoner.region}</p>
    </div>

    <p>🔥 승률: <strong>${winRate}%</strong></p>

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
                    <td>${match.win ? '🏆 승리' : '❌ 패배'}</td>
                    <td><a href="/matchDetails?matchId=${match.matchId}">🔍 세부 정보</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>
