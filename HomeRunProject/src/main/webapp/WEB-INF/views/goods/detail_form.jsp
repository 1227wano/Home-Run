<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

</head>
<body>

	<jsp:include page="../common/menubar.jsp" />
	<br><br><br><br><br>
	
	<div align="center">
		<h2>굿즈 상세 정보</h2>
		<br>
		<button type="button" onclick="history.back();">굿즈 목록</button>
		<br><br>
		<div>
			<label for="">상품 이미지</label><br>
			<img src="/baseball${ goods.changeName }" alt="이미지">
		</div>
		<div>
            <label for="">상품명</label><br>
            <input type="text" style="width:380px;" name="goodsName" readonly value="${ goods.goodsName }"/>
        </div>
		<div>
            <label for="">상품 설명</label><br>
            <textarea class="" name="description" cols="50" rows="10" id="" style="resize:none;" readonly>${ goods.description }</textarea>
        </div>
        <div>
            <label for="">상품 가격</label><br>
            <input type="number" style="width:380px;" name="goodsPrice" readonly value="${ goods.goodsPrice }" />
        </div>
        <div>
            <label for="">상품 개수</label><br>
            <input type="number" style="width:380px;" name="goodsCount" readonly value="${ goods.goodsCount }" />
        </div>
        <div>
            <label for="">등록자 닉네임</label><br>
            <input type="text" style="width:380px;" name="nickname" readonly value="${ goods.nickname }"/>
        </div>
        
	</div>
	
	<br><br><br><br><br>

</body>
</html>