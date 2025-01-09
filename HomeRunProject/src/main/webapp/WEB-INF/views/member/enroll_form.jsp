<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <style>
		
#cover{
	widht="730px;"
}

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
            	
        
     
            		if( $idInput.val().length >= 4){
            			
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
            
            function pwdcheck(){
            	
            	const $userPwd = $('#userPwd');
          		const $checkPwd = $('#checkPwd');
          		const $checkPwdResult = $('#checkPwdResult');
          	
              	if(($userPwd.val().length >= 4) && ($checkPwd.val().length >= 4 )){
              		
              		$.ajax({
              			
              			url : 'pwdcheck',
              			type : 'post',
              			data : {
              				userPwd : $userPwd.val(),
              				checkPwd : $checkPwd.val()
              			},
              			success: function(result){
              				 console.log('AJAX 요청 성공:', result);
              				if(result == true){
              					$checkPwdResult.show().css('color','blue').text('확인되었습니다.');
              				}else{
              					$checkPwdResult.show().css('color','red').text('동일한 비밀번호를 작성해주세요.');
              				}
              			}
              });
            }
            	
         }
            
            $(document).ready(function(){ $('#checkPwd').on('keyup', pwdcheck); });
             
            
        	
            
            
            
            </script>
            <form action="sign_up.me" method="post">
                <div class="form-group" id="enroll_form" style="width: 50%; margin: auto;">
                    <label for="userId"> 아이디 </label>
                    <input type="text" class="form-control" id="userId" placeholder="9자 이하 아이디를 입력해주세요" name="userId"  required> 
                    <button type="button" onclick="idcheck()">중복확인</button> <br>
                    <div id="check-result" style="font-size:0.9em; display:none;"></div> <br>

                    <label for="userPwd"> 비밀번호 </label>
                    <input type="password" class="form-control" id="userPwd" placeholder="4글자 이상 비밀번호를 입력해주세요" name="userPwd" required> <br>

                    <label for="checkPwd"> 비밀번호 재확인 </label>
                    <input type="password" class="form-control" id="checkPwd" placeholder="비밀번호 재확인" name="checkPwd" keyup="pwdcheck()" required > 
                    <div id="checkPwdResult" style="font-size:0.9em; display:none;"></div> <br>

                    <label for="userName"> 이름 </label>
                    <input type="text" class="form-control" id="userName" placeholder="이름을 입력해주세요" name="userName" required> <br>

                    <label for="nickName"> 닉네임 </label>
                    <input type="text" class="form-control" id="nickName" placeholder="닉네임을 입력해주세요" name="nickName" required>
                    <button type="button" onclick="nicknamecheck()" >중복확인</button> <br>
                    <div id="nickNameCheckResult" style="font-size:0.9em; display:none;"></div> <br>

                    <label for="phone"> 전화번호 </label>
                    <input type="tel" class="form-control" id="age" placeholder="전화번호를 입력해주세요" name="phone" required> <br>

                    <label for="email"> 이메일 </label>
                    <input type="text" class="form-control" id="phone" placeholder="이메일을 입력해주세요" name="email"> <br>
                    
                    <label for="address"> 주소 </label>
                    <input type="text" class="form-control" id="address" placeholder="주소를 입력해주세요" name="address"> <br>
                    
                    <input type="checkbox" id="agreement" name="agreement" style="display: inline-block;" required> 
                    <label for="agreement"> 인증 약관 전체동의 [필수] </label>
                    <div style="width:730px; height:100px; resize: none; overflow-x: hidden; overflow-y:auto; ">
						개인정보보호법에 따라 네이버에 회원가입 신청하시는 분께 수집하는 개인정보의 항목, 개인정보의 수집 및
						이용목적,개인정보의 보유 및 이용기간, 동의 거부권 및 동의 거부 시 불이익에 관한 사항을 안내 드리오니 
						자세히 읽은 후 동의하여 주시기 바랍니다.<br>
						<br>
						1. 수집하는 개인정보
						이용자는 회원가입을 하지 않아도 정보 검색, 뉴스 보기 등 대부분의 서비스를 회원과 동일하게 이용할 수 있습니다. 
						이용자가 메일, 캘린더, 카페, 블로그 등과 같이 개인화 혹은 회원제 서비스를 이용하기 위해 회원가입을 할 경우, 서비스 이용을 위해 필요한 최소한의 
						개인정보를 수집합니다.
						회원가입 시점에 이용자로부터 수집하는 개인정보는 아래와 같습니다.
						<br>
						- 회원 가입 시 필수항목으로 아이디, 비밀번호, 이름, 생년월일, 성별, 휴대전화번호를, 선택항목으로 본인확인 이메일주소를 수집합니다. 
						실명 인증된 아이디로 가입 시, 암호화된 동일인 식별정보(CI), 중복가입 확인정보(DI), 내외국인 정보를 함께 수집합니다. 만 14세 미만 아동의 경우, 
						법정대리인의 동의를 받고 있으며, 휴대전화번호 또는 아이핀 인증을 통해 법정대리인의 동의를 확인하고 있습니다. 
						이 과정에서 법정대리인의 정보(법정대리인의 이름, 중복가입확인정보(DI), 휴대전화번호(아이핀 인증인 경우 아이핀번호))를 추가로 수집합니다.
						<br>
						- 비밀번호 없이 회원 가입 시에는 필수항목으로 아이디, 이름, 생년월일, 휴대전화번호를, 선택항목으로 비밀번호를 수집합니다.
						<br>
						- 단체 회원가입 시 필수 항목으로 단체아이디, 비밀번호, 단체이름, 이메일주소, 휴대전화번호를, 선택항목으로 단체 대표자명을 수집합니다.
						서비스 이용 과정에서 이용자로부터 수집하는 개인정보는 아래와 같습니다.
						<br>
						- 회원정보 또는 개별 서비스에서 프로필 정보(별명, 프로필 사진)를 설정할 수 있습니다. 회원정보에 별명을 입력하지 않은 경우에는 마스킹 처리된 
						아이디가 별명으로 자동 입력됩니다.
						<br>
						- 개별 서비스 이용, 이벤트 응모 및 경품 신청 과정에서 해당 서비스의 이용자에 한해 추가 개인정보 수집이 발생할 수 있습니다. 
						추가로 개인정보를 수집할 경우에는 해당 개인정보 수집 시점에서 이용자에게 ‘수집하는 개인정보 항목, 개인정보의 수집 및 이용목적, 
						개인정보의 보관기간’에 대해 안내 드리고 동의를 받습니다.
						<br>
						2. 수집한 개인정보의 이용
						네이버 관련 제반 서비스(모바일 웹/앱 포함)의 회원관리, 서비스 개발・제공 및 향상, 안전한 인터넷 이용환경 구축 등 아래의 목적으로만 개인정보를 이용합니다.
						<br>
						- 회원 가입 의사의 확인, 연령 확인 및 법정대리인 동의 진행, 이용자 및 법정대리인의 본인 확인, 이용자 식별, 회원탈퇴 의사의 확인 등 회원관리를 위하여 개인정보를 이용합니다.
						<br>
						- 콘텐츠 등 기존 서비스 제공(광고 포함)에 더하여, 인구통계학적 분석, 서비스 방문 및 이용기록의 분석, 개인정보 및 관심에 기반한 이용자간 관계의 형성, 
						지인 및 관심사 등에 기반한 맞춤형 서비스 제공 등 신규 서비스 요소의 발굴 및 기존 서비스 개선 등을 위하여 개인정보를 이용합니다. 신규 서비스 요소의 발굴 
						및 기존 서비스 개선 등에는 정보 검색, 다른 이용자와의 커뮤니케이션, 콘텐츠 생성·제공·추천, 상품 쇼핑 등에서의 인공지능(AI) 기술 적용이 포함됩니다.
						<br>
						- 법령 및 이용약관을 위반하는 회원에 대한 이용 제한 조치, 부정 이용 행위를 포함하여 서비스의 원활한 운영에 지장을 주는 행위에 대한 방지 및 제재, 
						계정도용 및 부정거래 방지, 약관 개정 등의 고지사항 전달, 분쟁조정을 위한 기록 보존, 민원처리 등 이용자 보호 및 서비스 운영을 위하여 개인정보를 이용합니다.
						<br>
						- 유료 서비스 제공에 따르는 본인인증, 구매 및 요금 결제, 상품 및 서비스의 배송을 위하여 개인정보를 이용합니다.
						<br>
						- 이벤트 정보 및 참여기회 제공, 광고성 정보 제공 등 마케팅 및 프로모션 목적으로 개인정보를 이용합니다.
						<br>
						- 서비스 이용기록과 접속 빈도 분석, 서비스 이용에 대한 통계, 서비스 분석 및 통계에 따른 맞춤 서비스 제공 및 광고 게재 등에 개인정보를 이용합니다.
						<br>
						- 보안, 프라이버시, 안전 측면에서 이용자가 안심하고 이용할 수 있는 서비스 이용환경 구축을 위해 개인정보를 이용합니다.
                    </div>
          
                </div> 
                <br> 
                <div class="btns" align="center">
                    <button type="submit" class="btn btn-primary" id="join-btn" disabled >회원가입</button>
                </div>
                
              
            </form>
        </div>
        <br><br>

    </div>

</body>
</html>