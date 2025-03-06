<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<<!DOCTYPE html>
<html>
<head>
    <title>${championInfo.name} - ${championInfo.title}</title>
</head>
<body>
    <h1>${championInfo.name} (${championInfo.title})</h1>
    <p>${championInfo.description}</p>

    <!-- 챔피언 프로필 이미지 -->
    <img src="${images.profile}" alt="${championName} Profile" width="150"><br>

    <h2>Skills</h2>
    <img src="${images.Q}" alt="${championName} Q Skill" width="50">
    <img src="${images.W}" alt="${championName} W Skill" width="50">
    <img src="${images.E}" alt="${championName} E Skill" width="50">
    <img src="${images.R}" alt="${championName} R Skill" width="50">

    <br><a href="/">Back to Home</a>
</body>
</html>