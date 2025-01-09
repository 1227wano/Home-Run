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
            <h2 style="text-align: center;">푸드트럭 신청</h2>
            <br>
            
            <script>
            
            function foodTruckNameCheck(){
            	
            	const $foodTruckName = $('#foodTruck_form > #foodTruckName');
            	const $foodTruckNameCheckResult = $('#foodTruckNameCheckResult');
            	const $foodTruckBtn = $('foodTruck-btn');
            	
            	if($foodTruckName.val().length >= 1){
            		
            		$.ajax({
            			
            			url : 'foodTruckNameCheck',
            			type : 'get',
            			data : {
            				foodTruckName : $foodTruckName.val()
            			},
            			success : function(result){
            				
            				console.log(result);
            				
            				if(result.substr(4) === 'F'){
            					$foodTruckNameCheckResult.show().css('color','red').text('사용할 수 없는 푸드트럭 상호명입니다.');
            					$foodTruckBtn.attr('disabled', true);
            				}else{
            					$foodTruckNameCheckResult.show().css('color', 'blue').text('사용 가능한 푸드트럭 상호명입니다.');
            					$foodTruckBtn.attr('disabled');
            				}
            			}
            		});
            	}
            }
            
            
            
            
            </script>
            
            
            <form action="foodTruck" method="post" enctype="multipart/form-data"> 
                <div class="form-group" id="foodTruck_form" style="width: 50%; margin: auto;">
                    <label for="userId"> 사용자 아이디 :   </label> <span>${sessionScope.loginUser.userId}</span> <br>
                    <input type="hidden" value="${ sessionScope.loginUser.userId }" name="userId"/>
             
                    
                    <label for="domName"> 구장 </label>
                    <select name="domNo" >
					<c:forEach items="${ dom }" var="dom">
					<option value="${ dom.domNo }">${ dom.domName }</option>
					</c:forEach>
					</select>
                    <br>
                    
                   	
                   	<br>
                    <label for="foodTruckName"> 푸드트럭 이름 </label>
                    <input type="text" id="foodTruckName" placeholder="푸드트럭 상호명을 입력해주세요." name="foodTruckName" required> 
                    <button type="button" onclick="foodTruckNameCheck()">중복확인</button> <br>
                    <div id="foodTruckNameCheckResult" style="font-size:0.9em; display:none;"></div> <br>

                    <label for="foodTruckStartTime"> 푸드트럭 영업 시작 시간 </label>
                    <input type="time"  id="foodTruckStartTime" name="foodTruckStartTime"><br>
                    
                    <label for="foodTruckEndTime"> 푸드트럭 영업 끝나는 시간</label>
                    <input type="time" id="foodTruckEndTime" name="foodTruckEndTime"> <br>

                    <label for="foodTruckDay"> 푸드트럭 영업 요일 </label> <br>
                    
                    <div style="display: inline-block;" class="checkbox-inline" >
                    <input type="checkbox" id="monday" value="월" name="foodTruckDay" ><label for="monday">월</label> 
                    </div>
                    <div style="display: inline-block;" class="checkbox-inline" >
                    <input type="checkbox"  id="tuesday" value="화" name="foodTruckDay" ><label for="tuesday">화</label> 
                    </div>
                    <div style="display: inline-block;" class="checkbox-inline" >
                    <input type="checkbox"  id="wednesday" value="수" name="foodTruckDay" ><label for="wednesday">수</label> 
                    </div>
                    <div style="display: inline-block;" class="checkbox-inline" >
                    <input type="checkbox" id="thursday" value="목" name="foodTruckDay" ><label for="thursday">목</label> 
                    </div>
                    <div style="display: inline-block;" class="checkbox-inline" >
                    <input type="checkbox"  id="friday" value="금" name="foodTruckDay" "><label for="friday">금</label> 
                    </div>
                    <div style="display: inline-block;" class="checkbox-inline" >
                    <input type="checkbox" id="saturday" value="토" name="foodTruckDay" ><label for="saturday">토</label> 
                    </div>
                    <div style="display: inline-block;" class="checkbox-inline" >
                    <input type="checkbox"  id="sunday" value="일" name="foodTruckDay" ><label for="sunday">일</label> <br>
                    </div>  
                    
                    <br>

                    <label for="enrollDate"> 푸드트럭 시작 날짜 </label>
                    <input type="date" class="form-control" id="enrollDate" name="enrollDate"> <br>
                    
                    <label for="modifyDate"> 푸드트럭 끝나는 날짜 </label>
                    <input type="date" class="form-control" id="modifyDate"  name="modifyDate"> <br>
                    
                    <label for="foodTruckContent"> 푸드트럭 소개 </label><br>
                    <textarea id="foodTruckContent" class="form-control" rows="10" cols="20" style="resize:none;" name="foodTruckContent" required>푸드트럭 소개 작성해주세요.</textarea><br>
                    
                    
                    <label for="upfile">첨부파일 1</label> <br>
                    <input type="file" id="upfile1" class="form-control-file border" name="upfile" required><br>
                                                       
                    <label for="upfile">첨부파일 2</label><br>
                    <input type="file" id="upfile2" class="form-control-file border" name="upfile"><br>
                   
                    <label for="upfile">첨부파일 3</label><br>
                    <input type="file" id="upfile3" class="form-control-file border" name="upfile"> <br>
                    
                </div> 
                <br> 
                <div class="btns" align="center">
                    <button type="submit" class="btn btn-primary disabled" id="foodTruck-btn" >푸드트럭 신청</button>
                </div>
            </form>
        </div>
        <br><br>

    </div>


</body>
</html>