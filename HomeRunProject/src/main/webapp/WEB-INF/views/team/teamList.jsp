<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <style>
		.team-photo {
			width : 200px;
			height: 200px;
		}
    </style>
</head>
<body>
	<main id="main">
		<div class="teamLists">
			<ul class="teamList">
				<c:choose>
					<c:when test="${ not empty teams }">
						<c:forEach items="${ teams }" var="t">
							<li id="">
								<a href="selectTeam/${ t.teamNo }"> 
									<img src="/baseball${ t.changeName }" alt="팀사진" class="team-photo"> <br>
									<strong class="team-name"> ${ t.teamName } </strong> <br>
									<span class="team-grade"> ${ t.teamGrade }팀 </span> <br>
								</a><br>
							 </li>
						</c:forEach>
						<button id="show-more-btn" align="center" onclick="showMoreTeam();">더보기</button>
						
						<!-- 일단 5명까지 출력 -->
						<!--  
						<c:choose>
							<c:when test="=선수정보가 더 있을때">
								<button id="show-more-btn" align="center" onclick="showMoreteam();">더보기</button>
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
        
        function showMoreTeam(){

        	$.ajax({
            	url : '/baseball/team',	// teamAjaxController
            	type : 'get',
            	data : { 
            		page : moreNum
            	},
            	success : function(response){
            		const teams = [...response.data];
            		const resultteamsInfo = teams.map(t =>
							    			        		`<li>
							    			        		<a href="findTeam/\${ t.teamNo }"> 
															<img src="/baseball\${ t.changeName }" alt="팀사진" class="team-photo"> <br>
															<strong class="team-name"> \${ t.teamName } </strong> <br>
															<span class="team-grade"> ${ t.teamGrade }팀 </span> <br> 
															</a>
															</li>`
							    			        	).join('');
    				$('#show-more-btn').before(resultteamsInfo);
    				moreNum++;
            	}
            })
        }
        </script>
    </main>
</body>
</html>