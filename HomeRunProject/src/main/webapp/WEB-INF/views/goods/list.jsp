<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<style> 
	.list-area{
		text-align : center;
	}

	.thumbnail{
		box-shadow : 1px 1px 2px #0000002e;
		width : 300px;
		padding : 12px;
		margin : 25px;
		display : inline-block;
		background-color: #ffffffb0;
		color:#000000b0;
		font-weight: bold;
		border-radius: 12px;
	}

	.thumbnail > img{
		width : 270px;
		height : 180px;
		margin-bottom : 8px;
		border : 1px solid rgb(172 205 255 / 57%);
		border-radius: 12px;
	}

	.thumbnail:hover{
		cursor:pointer;
		opacity:0.8;
	}

	#pagingArea {
		width:fit-content; 
		margin:auto;
	}

</style>

</head>
<body>

	<jsp:include page="../common/menubar.jsp" />
	<br><br><br>
	
	<div class="list-area">
		
		<h2>굿즈 목록</h2>
		<br>
		
		<div class="thumbnail" align="center">
			<input type="hidden" value="" />
			<img src="" alt="이미지">
			<p>
				<label>No. 01 </label> / <span>글 제목가져온거</span> <br>
				<label>조회수</label> : <span>조회수 가져온거</span>
			</p>
		</div>
		
		<div class="thumbnail" align="center">
			<input type="hidden" value="" />
			<img src="" alt="이미지">
			<p>
				<label>No. 01 </label> / <span>글 제목가져온거</span> <br>
				<label>조회수</label> : <span>조회수 가져온거</span>
			</p>
		</div>
		
		<div class="thumbnail" align="center">
			<input type="hidden" value="" />
			<img src="" alt="이미지">
			<p>
				<label>No. 01 </label> / <span>글 제목가져온거</span> <br>
				<label>조회수</label> : <span>조회수 가져온거</span>
			</p>
		</div>
	
	</div>
	
	<div id="pagingArea" align="center">
  		<ul class="pagination">
  			<!-- 
	  		<c:choose>
		   		<c:when test="${ pageInfo.currentPage ne 1 }">
		        	<li class="page-item"><a class="page-link" href="dom?page=${ pageInfo.currentPage - 1 }">이전</a></li>
		   		</c:when>
		   		<c:otherwise>
		   			<li class="page-item disabled"><a class="page-link" href="#">이전</a></li>
		   		</c:otherwise>
		   	</c:choose>
		   	-->
		   	<li class="page-item disabled"><a class="page-link" href="#">이전</a></li>
		   	
		   	<!-- 
  			<c:forEach begin="${ pageInfo.startPage }" end="${ pageInfo.endPage }" var="num">
	            <li class="page-item">
	            	<a class="page-link" href="dom?page=${ num }">${ num }</a>
	            </li>
			</c:forEach>
			-->
			<li class="page-item">
            	<a class="page-link" href="#">1</a>
            </li>
			<li class="page-item">
            	<a class="page-link" href="#">2</a>
            </li>
			<li class="page-item">
            	<a class="page-link" href="#">3</a>
            </li>
  			
  			<!--
  			<c:choose>
				<c:when test="${ pageInfo.currentPage ne pageInfo.endPage }">
	            	<li class="page-item"><a class="page-link" href="dom?page=${ pageInfo.currentPage + 1 }">다음</a></li>
				</c:when>
				<c:otherwise>
	            	<li class="page-item disabled"><a class="page-link" href="#">다음</a></li>
				</c:otherwise>
			</c:choose>
			-->
			<li class="page-item disabled"><a class="page-link" href="#">다음</a></li>
			
  		</ul>
  	</div>

</body>
</html>