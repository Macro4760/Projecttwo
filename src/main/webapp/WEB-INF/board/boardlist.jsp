<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>502 jsp study</title>
    <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
    <script src="https://cdn.jsdelivr.net/npm/tailwindcss@2.0.0/dist/tailwind.min.css"></script>
    <style>
        body * {
            font-family: 'Jua';
        }
        .tabboard thead th {
            text-align: center;
            background-color: #f0f0f0;
        }
        .picon {
            color: #ccc;
            font-size: 0.9em;
        }
    </style>
</head>
<body>
<jsp:include page="../../layout/title.jsp"/>

<div class="container my-4">
    <div class="alert alert-danger d-flex justify-content-between align-items-center">
        <span>총 ${totalCount} 개의 글이 있습니다</span>
        <button type="button" class="btn btn-success btn-sm" onclick="location.href='./form'">글쓰기</button>
    </div>

    <table class="table table-bordered tabboard">
        <thead>
            <tr>
                <th class="text-center" width="50">번호</th>
                <th class="text-center" width="400">제목</th>
                <th class="text-center" width="120">작성자</th>
                <th class="text-center" width="100">작성일</th>
                <th class="text-center" width="100">조회수</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${totalCount == 0}">
                <tr>
                    <td colspan="5" class="text-center">등록된 글이 없습니다</td>
                </tr>
            </c:if>

            <c:if test="${totalCount > 0}">
                <c:forEach var="dto" items="${list}">
                    <tr>
                        <td class="text-center">${no}<c:set var="no" value="${no - 1}"/></td>
                        <td>
                            <a href="./detail?idx=${dto.idx}&pageNum=${pageNum}" class="text-black no-underline">

                                ${dto.title}
                                <c:if test="${dto.repleCount > 0}">
                                    <span class="text-red-500">[${dto.repleCount}]</span>
                                </c:if>
                            </a>
                        </td>
                        <td class="text-center">${dto.writer}</td>
                        <td class="text-center">
                            <span class="text-sm">
                                <fmt:formatDate value="${dto.createdate}" pattern="yyyy-MM-dd"/>
                            </span>
                        </td>
                        <td class="text-center">${dto.readcount}</td>
                    </tr>
                </c:forEach>
            </c:if>
        </tbody>
    </table>

    <!-- 페이징 처리 -->
    <div class="pagination-container">
        <ul class="pagination justify-content-center">
            <c:if test="${startPage > 1}">
                <li class="page-item">
                    <a class="page-link" href="./list?pageNum=${startPage - 1}">Prev</a>
                </li>
            </c:if>

            <c:forEach var="pp" begin="${startPage}" end="${endPage}">
                <c:if test="${pp == pageNum}">
                    <li class="page-item active">
                        <a class="page-link" href="./list?pageNum=${pp}">${pp}</a>
                    </li>
                </c:if>
                <c:if test="${pp != pageNum}">
                    <li class="page-item">
                        <a class="page-link" href="./list?pageNum=${pp}">${pp}</a>
                    </li>
                </c:if>
            </c:forEach>

            <c:if test="${endPage < totalPage}">
                <li class="page-item">
                    <a class="page-link" href="./list?pageNum=${endPage + 1}">Next</a>
                </li>
            </c:if>
        </ul>
    </div>
</div>

</body>
</html>
