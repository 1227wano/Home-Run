<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	
<style>
	.list-area{
		text-align : center;
	}
	
	.dom-list {
		width : 320px;
		height : 240px;
	}
	
	.dom-list > img{
		width : 270px;
		height : 180px;
		margin-bottom : 8px;
		border : 1px solid rgb(172 205 255 / 57%);
		
	}
	
	#pagingArea {
		width:fit-content; 
		margin:auto;
	}
</style>

</head>
<body>

	<jsp:include page="../common/menubar.jsp" /> 
	<br><br><br><br><br>
	
	<div align="center">
		<c:if test="${ sessionScope.loginUser.userId eq 'admin' }">
			<a href="/baseball/enrollForm">구장 등록</a> | 
		</c:if>
		<a href="/baseball/match">경기 일정</a>
	</div>
	<br><br>
	
    <div class="list-area">
		<br>        
        <div align="center">
	        <c:choose>
	        	<c:when test="${ empty domList }">
					<h3>구장 정보가 존재하지 않습니다. 다른 페이지로 이동하세요.</h3> <br><br><br>
				</c:when>
				<c:otherwise>
		            <c:forEach items="${ domList }" var="dom">
			            <div class="dom-list" onclick="detail('${dom.domNo}')" align="center">
				            <img src="/baseball${ dom.imagePath }" alt="이미지">
			                <label>${ dom.domName }</label>
			            </div>
		            </c:forEach>
				</c:otherwise>
	        </c:choose>
        </div>
        
        <div id="pagingArea" align="center">
	  		<ul class="pagination">
		  		<c:choose>
			   		<c:when test="${ pageInfo.currentPage ne 1 }">
			        	<li class="page-item"><a class="page-link" href="dom?page=${ pageInfo.currentPage - 1 }">이전</a></li>
			   		</c:when>
			   		<c:otherwise>
			   			<li class="page-item disabled"><a class="page-link" href="#">이전</a></li>
			   		</c:otherwise>
			   	</c:choose>
			   	
	  			<c:forEach begin="${ pageInfo.startPage }" end="${ pageInfo.endPage }" var="num">
		            <li class="page-item">
		            	<a class="page-link" href="dom?page=${ num }">${ num }</a>
		            </li>
				</c:forEach>
	  			
	  			<c:choose>
					<c:when test="${ pageInfo.currentPage ne pageInfo.endPage }">
		            	<li class="page-item"><a class="page-link" href="dom?page=${ pageInfo.currentPage + 1 }">다음</a></li>
					</c:when>
					<c:otherwise>
		            	<li class="page-item disabled"><a class="page-link" href="#">다음</a></li>
					</c:otherwise>
				</c:choose>
	  		</ul>
	  	</div>
        
        <script>
	    	function detail(num){
	    		location.href = `/baseball/dom/\${num}`;
	    	}
    	</script>
		<br><br><br><br><br>
		
    </div>
    
</body>
</html>