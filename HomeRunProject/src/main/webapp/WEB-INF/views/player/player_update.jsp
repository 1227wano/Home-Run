<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선수정보 수정 화면</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<style>
		
        
</style>
</head>
<body>
	
	<jsp:include page="../common/menubar.jsp" />

	<div class="content">
        <br><br>

		<div class="innerOuter">
            <h2>선수정보 수정</h2>
            <br>
            
            <form id="updateForm" action="updatePlayer" method="post" enctype="multipart/form-data">
                <div class="form-group" id="player-enroll-form">
                
                	<input type="hidden" name="playerNo" value="${ player.playerNo }" />
                	<input type="hidden" name="userNo" value="${ player.userNo }" />
                	
                    <label for="">선수 등급 : </label> &nbsp;&nbsp;
                    <input type="radio" id="pro" value="1" name="grade">
                    <label for="">프로</label> &nbsp;&nbsp;
                    <input type="radio" id="amateur" value="2" name="grade">
                    <label for="">아마추어</label> &nbsp;&nbsp;
                    <input type="radio" id="normal" value="3" name="grade">
                    <label for="">일반</label> &nbsp;&nbsp;
                    <br><br>
            
                    포지션:
                    <select name="playerPosition">
                        <option value="감독">감독</option>
                        <option value="투수">투수</option>
                        <option value="포수">포수</option>
                        <option value="내야수">내야수</option>
                        <option value="외야수">외야수</option>
                    </select>
                    <br><br>
            
                    <fieldset>
                        <legend>자기소개</legend>
                        <textarea id="player-intro" name="playerIntro" cols="53" rows="34" style="resize:none;" placeholder="1000자 이내로 시합성적 및 자신소개글을 입력하세요." maxlength="1000">${ player.playerIntro }</textarea>
                        <br>
                        <span id="count">0</span> / 1000
                    </fieldset>
                    <br>

                    <label for="player-salary">희망 연봉 : </label>
                    <input type="number" min="1000000" max="1000000000" step="1000000" name="playerSalary" value="${ player.playerSalary }">                    
                    <br><br>
                    
                    변경할 선수 사진을 선택해주세요. 
                    
                    <input type="file" id="upfile" class="form-control-file border" name="upfile">
	                     <c:if test="${ not empty player.imagePath }">
	                      현재 업로드된 파일 :
	                      <img src="/baseball${ player.imagePath }">
	                     </c:if>	
                    <!-- 
                        선택한 파일이 이미지가 아닐 경우
                        뒷단 : contentType 이 image가 아니라면 예외처리
                        앞단 : ajax / page 로 image파일이 아닐때 파일 양식 안내 
                    -->
                    
                </div>
                <br>
                <div class="btns" align="center">
                    <button type="submit" class="btn btn-primary disabled" id="player-enroll-btn">제출</button>
                    <button type="reset" class="btn btn-danger" id="player-reset-btn">초기화</button>
                    <button id="player-delete-btn" onclick="deletePlayer();">선수 탈퇴</button>
                </div>
            </form>		
        </div>
        <br><br>
        
        
        
    </div>
    

    <script>
        $(function(){
            $('#player-intro').keyup(function(){
                $('#count').text(this.value.length);
            });
        });
        
        function deletePlayer(){

        	$.ajax({
            	url : '/baseball/deletePlayer',
            	type : 'get',
            	data : { 
            		userNo : userNo,
            		userId : userId
            	},
            	success : function(result){
            		
            	}
            })
        }
        
        window.onload = function(){
        	
        	// console.log(document.querySelector('input[type=radio][value="${player.grade}"]'));
	        document.querySelector('input[value="${player.grade}"]').checked = true;
	        document.querySelector('option[value="${player.playerPosition}"]').selected = true;
        }

    </script>


</body>
</html>