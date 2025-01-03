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
							<a href="상세보기로 넘어가"> 
								<img src="/baseball${ p.imagePath }" alt="선수사진" class="player-photo"> <br>
								<strong class="name"> 선수명 : ${ p.userName } </strong> <br>
								<c:choose>
									<c:when test="${ p.playerTeam eq 'null' || p.playerTeam eq 'N' }">
										<span class="team"> 소속팀 : FA </span> <br>
									</c:when>
									<c:otherwise>
										<span class="team"> 소속팀 : ${ p.playerTeam } </span> <br>
									</c:otherwise>
								</c:choose>
								<span class="position"> 포지션 : ${ p.playerPosition } </span> <br>
								<span class="number"> 등번호 : ${ p.backNo } </span> <br>
							</a> <br>
						 </li>
					</c:forEach>
					<!-- 일단 5명까지 출력 -->
					<button id="show-more-btn" align="center" onclick="showMorePlayer();">더보기</button>
					</c:when>
					<c:otherwise>
						등록된 정보가 없습니다. 
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
        
        <script> 
        
       	let moreNum = 2; // 여기서 선언안하면 아래의 펑션이 실행될때마다 2가 됨
        
        function showMorePlayer(){

        	$.ajax({
            	url : '/baseball/player',	
            	type : 'get',
            	data : { 
            		page : moreNum
            	},
            	success : function(response){
            		const players = [...response.data];
            		const resultPlayersInfo = players.map(p =>
							    			        		`<li>
							    			        		<a href="상세보기로 넘어가"> 
															<img src="/baseball\${ p.imagePath }" alt="선수사진" class="player-photo"> <br>
															<strong class="name"> 선수명 : \${ p.userName } </strong> <br>
															<c:choose>
															<c:when test="${ p.playerTeam eq null }">
																<span class="team"> 소속팀 : FA </span> <br>
															</c:when>
															<c:otherwise>
																<span class="team"> 소속팀 : ${ p.playerTeam } </span> <br>
															</c:otherwise>
															</c:choose>
															<span class="position"> 포지션 : \${ p.playerPosition } </span> <br>
															<span class="number"> 등번호 : \${ p.backNo } </span> <br> 
															</a>
															</li>`
							    			        	).join('');
            		// 더이상 선수정보가 없을때 조건문으로 $('#show-more-btn').style('display:none'); 근데 이코드 잘못됨
    				$('#show-more-btn').before(resultPlayersInfo);
    				moreNum++;
            		}
            })
        }
        
        
        </script>
        
        
        
    </main>
</body>
</html>