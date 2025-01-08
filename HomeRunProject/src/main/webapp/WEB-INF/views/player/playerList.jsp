<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선수 일람 화면</title>
<head>
    <meta charset="UTF-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <style>
		.player-photo {
			width : 200px;
			height: 200px;
		}
    </style>
</head>

<body>
    <main id="main">
		<div class="playerLists">
			<ul class="playerList">
				<c:choose>
					<c:when test="${ not empty players }">
						<c:forEach items="${ players }" var="p">
							<li id="">
								<a href="findPlayer/${ p.playerNo }"> 
									<img src="/baseball${ p.imagePath }" alt="선수사진" class="player-photo"> <br>
									<span class="player-position"> ${ p.playerPosition } </span> <br>
									<strong class="player-name"> ${ p.userName } </strong> <br>
									<span class="player-team"> 소속팀 : ${ p.playerTeam } </span> <br>
									<span class="player-number"> No. ${ p.backNo } </span> <br>
								</a><br>
							 </li>
						</c:forEach>
						<button id="show-more-btn" align="center" onclick="showMorePlayer();">더보기</button>
						
						<!-- 일단 5명까지 출력 -->
						<!--  
						<c:choose>
							<c:when test="=선수정보가 더 있을때">
								<button id="show-more-btn" align="center" onclick="showMorePlayer();">더보기</button>
							</c:when>
							<c:otherwise>더 이상의 선수가 존재하지 않습니다.</c:otherwise>
						</c:choose>
						-->
					</c:when>
					<c:otherwise>
						등록된 정보가 없습니다. 
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
        
        <script> 
        
       	let moreNum = 2; // function안에서 선언하면 아래의 function이 실행될때마다 2가 됨
        
        function showMorePlayer(){

        	$.ajax({
            	url : '/baseball/player',	// PlayerAjaxController
            	type : 'get',
            	data : { 
            		page : moreNum
            	},
            	success : function(response){
            		const players = [...response.data];
            		const resultPlayersInfo = players.map(p =>
							    			        		`<li>
							    			        		<a href="findPlayer/\${ p.playerNo }"> 
															<img src="/baseball\${ p.imagePath }" alt="선수사진" class="player-photo"> <br>
															<span class="position"> \${ p.playerPosition } </span> <br>
															<strong class="name"> \${ p.userName } </strong> <br>
															<span class="team"> 소속팀 : ${ p.playerTeam } </span> <br>
															<span class="number"> No. \${ p.backNo } </span> <br> 
															</a>
															</li>`
							    			        	).join('');
    				$('#show-more-btn').before(resultPlayersInfo);
    				moreNum++;
            	}
            })
        }
        </script>
    </main>
</body>
</html>