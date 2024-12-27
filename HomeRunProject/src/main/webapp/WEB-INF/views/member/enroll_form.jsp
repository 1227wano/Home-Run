<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <style>
		

    </style>

</head>
<body>
    <!-- 메뉴바 -->
    <jsp:include page="../common/menubar.jsp" />

    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2 style="text-align: center;">회원가입</h2>
            <br>
            
            <script>
            
       
            
            function idcheck(){
            	
            	const $idInput = $('#enroll_form > #userId');
            	const $checkResult = $('#check-result');
            	const $joinBtn = $('#join-btn');
            	
        
     
            		if( $idInput.val().length >= 5){
            			
            			$.ajax({
            				
            				url : 'idcheck',
            				type : 'get',
            				data : {
            					userId : $idInput.val()
            				},
            				success : function(result){
            					
            					console.log(result);
            					
            					if(result.substr(4) === 'I'){
            						$checkResult.show().css('color', 'red').text('사용할 수 없는 아이디입니다.');
            						$joinBtn.attr('disabled',true);
            					}else{
            						$checkResult.show().css('color','blue').text('사용 가능한 아이디입니다.');
            						$joinBtn.removeAttr('disabled');
            					}
            				}	
            			});
            		         		
            	}
	
            }
            
            function nicknamecheck(){
            	
            	const $nickNameInput = $('#enroll_form > #nickName');
            	const $nickNameCheckResult = $('#nickNameCheckResult');
            	const $joinBtn = $('#join-btn');
            	
            		
            		if($nickNameInput.val().length >= 2){
            			
            			$.ajax({
            				
            				url : 'nicknamecheck',
            				type : 'get',  
            				data : {
            					
            					nickName : $nickNameInput.val()
            				},
            				success : function(result){
            					
            					console.log(result);
            					
            					if(result.substr(4) === 'N'){
            						$nickNameCheckResult.show().css('color','red').text('이미 사용중인 닉네임입니다.');
            						$joinBtn.attr('disabled',true);
            					}else{
            						$nickNameCheckResult.show().css('color', 'blue').text('사용 가능한 닉네임입니다.');
            						$joinBtn.removeAttr('disabled');
            					}
            					
            				}
            				
            			});
            		}
            	}
            
            
            
            
            </script>
            <form action="sign_up.me" method="post">
                <div class="form-group" id="enroll_form" style="width: 50%; margin: auto;">
                    <label for="userId"> 아이디 </label>
                    <input type="text" class="form-control" id="userId" placeholder="아이디를 입력해주세요" name="userId" required> 
                    <button type="button" onclick="idcheck()">중복확인</button> <br>
                    <div id="check-result" style="fonst-size:0.7em; display:none;"></div> <br>

                    <label for="userPwd"> 비밀번호 </label>
                    <input type="password" class="form-control" id="userPwd" placeholder="비밀번호를 입력해주세요" name="userPwd" required> <br>

                    <label for="checkPwd"> 비밀번호 재확인 </label>
                    <input type="password" class="form-control" id="checkPwd" placeholder="비밀번호 재확인" required> <br>

                    <label for="userName"> 이름 </label>
                    <input type="text" class="form-control" id="userName" placeholder="이름을 입력해주세요" name="userName" required> <br>

                    <label for="nickName"> 닉네임 </label>
                    <input type="text" class="form-control" id="nickName" placeholder="닉네임을 입력해주세요" name="nickName">
                    <button type="button" onclick="nicknamecheck()" >중복확인</button> <br>
                    <div id="nickNameCheckResult" style="fonst-size:0.7em; display:none;"></div> <br>

                    <label for="phone"> 전화번호 </label>
                    <input type="number" class="form-control" id="age" placeholder="전화번호를 입력해주세요" name="phone"> <br>

                    <label for="email"> 이메일 </label>
                    <input type="tel" class="form-control" id="phone" placeholder="이메일을 입력해주세요" name="email"> <br>
                    
                    <label for="address"> 주소 </label>
                    <input type="text" class="form-control" id="address" placeholder="주소를 입력해주세요" name="address"> <br>
                    
                </div> 
                <br> 
                <div class="btns" align="center">
                    <button type="submit" class="btn btn-primary disabled" id="join-btn" >회원가입</button>
                </div>
            </form>
        </div>
        <br><br>

    </div>

</body>
</html>