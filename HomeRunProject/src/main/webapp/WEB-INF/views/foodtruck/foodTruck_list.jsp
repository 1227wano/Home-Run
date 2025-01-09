<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>푸드트럭 게시물 목록</title>
<style> 

	.content{
		width: 100%;
		text-align : center;
	}
	
	.list-area{
		text-align : center;
		width : 70%;
		margin : auto;
		
	}
	
	.outer{
		width: 100%;
		float : left;
		margin : auto;
	}
	
	

	.thumbnail{
		
		width : 300px;
		padding : 12px;
		margin : 25px;
		display : inline-block;
		background-color: #ffffffb0;
		color:#000000b0;
		font-weight: bold;
		border-radius: 5px;
		border : 1px solid rgb(104, 119, 148);
	}

	.thumbnail > img{
		width : 270px;
		height : 180px;
		margin-bottom : 8px;
		border : 1px solid rgba(94, 110, 135, 0.57);
		border-radius: 8px;
	}

	.thumbnail:hover{
		cursor:pointer;
		opacity:0.8;
	}


</style>
</head>
<body class="content">

	<jsp:include page="../common/menubar.jsp" />
		 <div>
		
			<form action="foodTruckSelectDom">

				구장 : 
				<select name="domNo" >
					<c:forEach items="${ dom }" var="dom">
					<option value="${ dom.domNo }">${ dom.domName }</option>
					</c:forEach>
				</select>

				<button type="submit">검색</button>

			</form>

		</div>
	
	
	<div class="outer">
		

		<div class="list-area" >
			
			<br>
			
			
				<c:forEach items="${ foodTruck }" var="foodTruck">
				<c:forEach items="${ foodTruckFile }" var="foodTruckFile">
				<c:if test="${ foodTruck.foodTruckNo == foodTruckFile.refFtno }">
				<div class="thumbnail" align="center">
					<img src="/baseball/resources/upload_files/${ foodTruckFile.changeName }" alt="푸드트럭 이미지">
					
					<p>	${ foodTruck.foodTruckName } </p> <br>
					<label>구장 :</label> <span> ${ foodTruck.domName }</span> <br>
	                <label>영업시간 :</label> <span> ${foodTruck.foodTruckStartTime } ~ ${foodTruck.foodTruckEndTime}</span> <br>
	                <label>영업요일 :</label> <span> ${foodTruck.foodTruckDay}</span> <br>
	                <label>조회수 :</label> <span> ${foodTruck.foodTruckCount }</span>
				</div>
				</c:if>
				</c:forEach>
				</c:forEach>

		</div>
		
		
		
			<c:if test="${ not empty sessionScope.loginUser }">
	           	 <a class="btn btn-secondary" style="float:right;" href="foodTruckInsertForm">푸드트럭 신청</a>
	        </c:if>
		
		
	
	</div>

	
	
	<!--<jsp:include page="" />-->


</body>
</html>