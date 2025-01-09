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
        <div class="updatePwd.me" align="center">
            <h2>비밀번호 찾기</h2>
            <br>
            <form action="changePwd.me" method="post">
				<div style="width: 40%; margin: auto;">
				
				<input type="hidden" name="userId" value="${ sessionScope.loginUser.userId }">

                <label for="userPwd"> 현재 비밀번호 </label>
                <input type="text" class="form-control" id="userPwd" placeholder="현재 비밀번호를 입력해주세요" name="userPwd" required> <br>

                <label for="newPwd"> 새 비밀번호 </label>
                <input type="text" class="form-control" id="changePwd" placeholder="새 비밀번호를 입력해주세요" name="changePwd" required> <br>

                <label for="changePwd"> 새 비밀번호 확인 </label>
                <input type="text" class="form-control" id="checkChangePwd" placeholder="새 비밀번호 재확인" name="checkChangePwd" required> <br>
				<div id="check-result" style="font-size:0.7em; display:none;"></div>
				 
				</div>
				
				<br>
		        <div class="btns" align="center">
		            <button type="submit" class="btn btn-primary" onclick="return validatePwd()">비밀번호 변경하기</button>
                    <button type="button" class="btn btn-primary" onclick="window.location.href='/baseball'">다음에 변경하기</button>
		        </div>
		        
		        <script>
		        	function validatePwd(){
		        		
		        		if($('#changePwd').val() != $('#checkChangePwd').val()){
		        			alert('비밀번호를 동일하게 입력해주세요!');
		        			$('#checkChangePwd').focus().val('');
		        			return false;
		        		}
		        		return true;
		        	}
		      
		        
		        </script>

            </form>

        </div> 
        		 
    </div>
    
    
				    



</body>
</html>