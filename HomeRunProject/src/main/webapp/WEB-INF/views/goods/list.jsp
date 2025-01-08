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

	.goods{
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

	.goods > img{
		width : 270px;
		height : 180px;
		margin-bottom : 8px;
		border : 1px solid rgb(172 205 255 / 57%);
		border-radius: 12px;
	}

	.goods:hover{
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
		<a style="float:center;" href="/baseball/enrollForm.goods">상품등록</a>
		<br>

		<c:forEach items="${ requestScope.goodsList }" var="goods">
			<div class="goods" align="center" onclick="detail('${goods.goodsNo}');">
				<img src="/baseball${ goods.changeName }" alt="이미지">
				<p>
					<label>상품명</label> : <span>${ goods.goodsName }</span>
				</p>
			</div>
		</c:forEach>
	
	</div>
	
	<script>
		function detail(goodsNo){
			location.href = `/baseball/goods/\${goodsNo}`;
		}
	</script>
	
	
	<div id="pagingArea" align="center">
  		<ul class="pagination">
  			
	  		<c:choose>
		   		<c:when test="${ pageInfo.currentPage ne 1 }">
		        	<li class="page-item"><a class="page-link" href="goods?page=${ pageInfo.currentPage - 1 }">이전</a></li>
		   		</c:when>
		   		<c:otherwise>
		   			<li class="page-item disabled"><a class="page-link" href="#">이전</a></li>
		   		</c:otherwise>
		   	</c:choose>
		   	
  			<c:forEach begin="${ pageInfo.startPage }" end="${ pageInfo.endPage }" var="num">
	            <li class="page-item">
	            	<a class="page-link" href="goods?page=${ num }">${ num }</a>
	            </li>
			</c:forEach>
			
  			<c:choose>
				<c:when test="${ pageInfo.currentPage ne pageInfo.endPage }">
	            	<li class="page-item"><a class="page-link" href="goods?page=${ pageInfo.currentPage + 1 }">다음</a></li>
				</c:when>
				<c:otherwise>
	            	<li class="page-item disabled"><a class="page-link" href="#">다음</a></li>
				</c:otherwise>
			</c:choose>
			
  		</ul>
  	</div>

</body>
</html>