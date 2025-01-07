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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	
	<style>
		#pagingArea {
			width:fit-content; 
			margin:auto;
		}
		
	</style>

</head>
<body>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
  	
  	<jsp:include page="../common/menubar.jsp" />
  	<br><br><br><br><br>
  	<div align="center">
  	
  		<h2>경기 일정</h2>
  		<br><br>
  		
  		<table class="table table-hover">
  			<thead>
	  			<tr>
					<th>경기 일자</th>	  				
	  				<th>팀명</th>
	  				<th>팀명</th>
					<th>경기 장소</th>	  				
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
  	
  	<br>
  	
  	
  	<a href="/baseball/enrollForm.match" style="display: block; text-align : center;">경기 등록</a>
  	<br>
  	<div id="pagingArea" align="center">
  		<ul class="pagination">
	  		<c:choose>
		   		<c:when test="${ pageInfo.currentPage ne 1 }">
		        	<li class="page-item"><a class="page-link" href="match?page=${ pageInfo.currentPage - 1 }">이전</a></li>
		   		</c:when>
		   		<c:otherwise>
		   			<li class="page-item disabled"><a class="page-link" href="#">이전</a></li>
		   		</c:otherwise>
		   	</c:choose>
		   	
  			<c:forEach begin="${ pageInfo.startPage }" end="${ pageInfo.endPage }" var="num">
	            <li class="page-item">
	            	<a class="page-link" href="match?page=${ num }">${ num }</a>
	            </li>
			</c:forEach>
  			
  			<c:choose>
				<c:when test="${ pageInfo.currentPage ne pageInfo.endPage }">
	            	<li class="page-item"><a class="page-link" href="match?page=${ pageInfo.currentPage + 1 }">다음</a></li>
				</c:when>
				<c:otherwise>
	            	<li class="page-item disabled"><a class="page-link" href="#">다음</a></li>
				</c:otherwise>
			</c:choose>
  		</ul>
  	</div>
  
  
  
</body>

</html>