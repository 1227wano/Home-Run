<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>

	<jsp:include page="../common/menubar.jsp" /> 
	
	<br><br><br><br><br>
	
	<!-- <input type="hidden" name="userNo" value="${ loginUser.userNo }" /> -->
	<div align="center">
		<div>
			<label>구장명</label>
			<input type="text" name="domName" value="${ requestScope.dom.domName }">
		</div>
		<div>
			<label>구장 세부 내용</label>
			<textarea name="domContent">${ requestScope.dom.domContent }</textarea>
		</div>
		<div>
			<label>구장 주소</label>
			<textarea name="domAddr">${ requestScope.dom.domAddr }</textarea>
		</div>
		<div>
			<label>구장 이미지 첨부</label>
			<img width="300" height="180" name="${ requestScope.dom.imagePath }" 
			src="/baseball${ requestScope.dom.imagePath }" alt="대표이미지">
			<input type="file" id="" class="form-control-file border" name="upfile">
		</div>
		
		<div align="center">
            <!-- Application > Session > Request > Page -->
        	<c:if test="${ not empty sessionScope.loginUser.userId }"></c:if>
        	
            	<a onclick="postSubmit(1);">수정하기</a>
            	<a onclick="postSubmit(2);">삭제하기</a>
        	
        </div>
		
		<form action="" method="post" id="postForm">
	      	<input type="hidden" name="domNo" value="${ dom.domNo }" />
	      	<input type="hidden" name="imagePath" value="${ dom.imagePath }" />
        </form>
        
        <script>
           	function postSubmit(num){
           		if(num == 1){
           			$('#postForm').attr('action', "/baseball/dom/updateForm").submit();
           		} else {
           			$('#postForm').attr('action', "/baseball/dom/delete").submit();
           		}
           	}
        </script>
		
	</div>
    <br>

	
	
</body>
</html>