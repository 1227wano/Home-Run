<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<head>
    <meta charset="UTF-8">
    <title>선수 일람 화면</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <style>
		
    </style>
</head>

<body>
    <main id="main">
		<div class="playerLists">
			<ul class="playerList">
				<c:choose>
					<c:when test="${ not empty players }">
					<c:forEach items="${ players }" var="p" begin="0" end="4">
						<li id="">
							<a href="상세보기로 넘어가"> 
								<img src="/baseball${ p.imagePath }" alt="선수사진" class="player-photo">
								<strong class="name"> ${ p.userName } </strong>
								<span class="team"> ${ p.playerTeam } </span> 
								<span class="position"> ${ p.playerPosition } </span> 
								<span class="number"> ${ p.backNo } </span>
							</a>
						 </li>
						<!-- 일단 5명까지 출력 -->
						</c:forEach>
						<button id="show-more-btn" align="center" onclick="showMorePlayer();">더보기</button>
					</c:when>
					<c:otherwise>
						등록된 정보가 없습니다. 
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
        
        <script> 
        
        
        
        function showMorePlayer(){
        	
        	let moreNum = 1;

        	$.ajax({
            	url : '/baseball/findMorePlayer.player',
            	type : 'get',
            	data : { 
            		currentPage : moreNum	
            	},
            	success : function(response){
            		const players = [...response.list];
            		const resultPlayersInfo = players.map(p =>
							    			        		`<tr>
							            						<td>\${p.imagePath}</td>
							    			        			<td>\${p.userName}</td>
							    			        			<td>\${p.playerTeam}</td>
							    			        			<td>\${p.playerPosition}</td>
							    			        			<td>\${p.backNo}</td>
							    			        		</tr>`
							    			        	).join('');
            		// 더이상 선수정보가 없을때 조건문으로 $('#show-more-btn').style('display:none'); 근데 이코드 잘못됨
            		}
    				$('.playerList li').html(resultPlayersInfo);
    				moreNum++;
            	}
            })
        }
        
        
        
        </script>
        
        
        
    </main>
</body>
</html>