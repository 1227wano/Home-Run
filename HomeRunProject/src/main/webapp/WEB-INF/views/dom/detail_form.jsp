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

	<div>
		<label>구장명</label>
		<input type="text" name="domName" value="${ requestScope.domList.domName }">
	</div>
	<!-- <input type="hidden" name="userNo" value="${ loginUser.userNo }" /> -->
	<div>
		<label>구장 세부 내용</label>
		<input type="text" name="domContent" value="${ requestScope.domList.domContent }">
	</div>
	<div>
		<label>구장위치</label>
		<input type="text" name="domAddr" value="${ requestScope.domList.domAddr }">
	</div>
	<div>
		<label>구장이미지 첨부</label>
		<img width="300" height="180" name="${ requestScope.domList.originName }" 
		src="/baseball${ requestScope.domList.filePath }" alt="대표이미지">
		<input type="file" id="" class="form-control-file border" name="upfile">
	</div>



</body>
</html>