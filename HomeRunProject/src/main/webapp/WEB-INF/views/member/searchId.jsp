<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <style>


    </style>
</head>
<body>

	<jsp:include page="../common/menubar.jsp" />
    
    <div class="content">
        <div class="searchId" align="center">
            <h2>아이디 찾기</h2>
            <br>
            <form action="findById.me" method="post">
				<div style="width: 40%; margin: auto;">
                <label for="searchUserName"> 이름 </label>
                <input type="text" class="form-control" id="searchUserName" placeholder="이름을 입력해주세요" name="userName" required> <br>

                <label for="searchPhone"> 전화번호 </label>
                <input type="text" class="form-control" id="searchPhone" placeholder="전화번호를 입력해주세요" name="phone" required> <br>
				</div>
				
				<br>
		        <div class="btns" align="center">
		            <button type="submit" class="btn btn-primary" style="color: #003876">아이디 찾기</button>
		        </div>
						
				
            </form>

        </div>
       
    </div>



</body>
</html>