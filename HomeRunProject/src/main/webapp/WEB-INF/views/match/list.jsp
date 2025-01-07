<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Home-Run</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">


</head>
<body>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
  	
  	<jsp:include page="../common/menubar.jsp" />
  	<br><br><br><br><br>
  	<div align="center">
  	
  		<h2>경기 일정</h2>
  		
  		<!-- <button onclick="window.location.href='/baseball/match'">경기 등록</button> -->
  		
  		<table class="table table-hover">
  			<thead>
	  			<tr>
					<th>경기 일자</th>	  				
	  				<th>팀명</th>
	  				<th>팀명</th>
					<th>경기장 명</th>	  				
					<th>경기장 주소</th>	  				
	  			</tr>
  			</thead>
  			<tbody>
	  			<c:forEach items="${ requestScope.matchList }" var="match">
	  				<input type="hidden" ${ match.gameNo } />
	  				<tr>
	  					<td>${ match.matchDate }</td>
	  					<td>${ match.firstTeam }</td>
	  					<td>${ match.secondTeam }</td>
	  					<td>${ match.domName }</td>
	  					<td>${ match.domAddr }</td>
	  				</tr>
	  			</c:forEach>
  			</tbody>
		</table>
  	</div>
  
  
  
</body>

</html>