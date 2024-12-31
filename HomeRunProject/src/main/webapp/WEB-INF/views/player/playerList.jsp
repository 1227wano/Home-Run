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
					<c:when test="${ not empty map }"> 
						
						<li id="">
							<a href="상세보기로 넘어가"> 
								<img src="${ player.imagePath }" alt="선수사진" class="player-photo">
								<strong class="name"> ${ player.playerName } </strong>
								<span class="team"> ${ player.playerTeam } </span> 
								<span class="position"> ${ player.playerPosition } </span> 
								<span class="number"> ${ player.backNo } </span>
							</a>
						</li>
						
						<!-- 15명까지 출력 -->
						
						<button id="show-more-btn" align="center" onclick="showMorePlayer();">'더보기' ajax로 선수 15명 더 보여주기</button>
					</c:when>
					<c:otherwise>
						등록된 첨부파일이 없습니다. 
						show-more-btn { style  display:none 
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
        
        <script> 
        
         $(function (){
	        
	        showMorePlayer(moreNum);
         });
        
        function showMorePlayer(moreNum){
        	
        	let moreNum = 1;

        	$.ajax({
            	url : "/baseball/playerList.player",
            	type : 'get',
            	data : { 
            		currentPage : moreNum	
            	},
            	success : function(response){
            		const players = [...response.list];
            		const resulrPlayersInfo = players.map(p =>
							    			        		`<tr>
							            						<td>\${p.player.imagePath}</td>
							    			        			<td>\${p.player.playerName}</td>
							    			        			<td>\${p.player.backNo}</td>
							    			        			<td>\${p.player.playerPosition}</td>
							    			        			<td>\${p.player.backNo}</td>
							    			        		</tr>`
							    			        	).join('');
            		}
    				$('#playerList li').html(resulrPlayersInfo);
    				moreNum++;
            	}
            })
        }
        
        
        
        </script>
        
        
        
    </main>
</body>
</html>