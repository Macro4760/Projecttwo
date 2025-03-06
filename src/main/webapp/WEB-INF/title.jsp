<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Champion List</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <h1>Champion List</h1>
    <div id="champion-list"></div> <!-- 챔피언 리스트를 여기에 출력 -->

    <script>
        $(document).ready(function() {
            // AJAX 요청으로 챔피언 데이터 가져오기
            $.ajax({
                url: '/riot/champion/list', // 데이터가 반환되는 API 주소
                method: 'GET',
                success: function(data) {
                    let html = '<ul>';
                    data.forEach(function(champion) {z
                        html += `<li>
                                    <strong>${champion.name}</strong> - ${champion.title}<br>
                                    <img src="https://ddragon.leagueoflegends.com/cdn/14.4.1/img/champion/${champion.id}.png" alt="${champion.name}" style="width:50px;height:50px;">
                                    <p>${champion.blurb}</p>
                                  </li>`;
                    });
                    html += '</ul>';

                    // 챔피언 리스트를 페이지에 추가
                    $('#champion-list').html(html);
                },
                error: function(error) {
                    console.log('Error fetching data:', error);
                }
            });
        });
    </script>
</body>
</html>