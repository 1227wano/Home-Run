<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
<title>팀 창설 신청 페이지</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<div class="content">
	   <br><br>
	   <div class="innerOuter">
	    <h2>팀 창설</h2>
	    <br>
	    
	    <form id="selectForm" action="insertTeam" method="post" enctype="multipart/form-data">
	        <div class="form-group" id="team-enroll-form">
	        	
	        	<input type="hidden" name="userNo" value="${ loginUser.userNo }" />
				
				<div class="row g-2">
				  <div class="col-md">
				    <div class="form-floating">
				      <input type="text" class="form-control" id="floatingInputGrid" name="teamName">
				      <label for="floatingInputGrid">팀명을 입력해주세요.</label>
				    </div>
				  </div>
				  <div class="col-md">
				    <div class="form-floating">
				      <select class="form-select" id="floatingSelectGrid" name="teamGrade">
				        <option selected>팀의 등급을 선택해주세요</option>
				        <option value="프로">프로</option>
				        <option value="아마추어">아마추어</option>
				        <option value="일반">일반</option>
				      </select>
				      <label for="floatingSelectGrid">팀 등급</label>
				    </div>
				  </div>
				</div>     	
	    
	            <fieldset>
	                <legend>팀소개글 작성</legend>
	                <textarea id="team-intro" name="teamIntro" cols="53" rows="34" style="resize:none;" placeholder="1000자 이내로 팀소개글을 작성해주세요." maxlength="1000"></textarea>
	                <br>
	                <span id="count">0</span> / 1000
	            </fieldset>
	            <br>
	
	            팀 사진을 선택해주세요. <input type="file" name="upfile">
	            <!-- 
	                선택한 파일이 이미지가 아닐 경우
	                뒷단 : contentType 이 image가 아니라면 예외처리
	                앞단 : ajax / page 로 image파일이 아닐때 파일 양식 안내 
	            -->
	        </div>
	        <br>
	        <div class="btns" align="center">
	            <button type="submit" class="btn btn-primary" id="team-enroll-btn">제출</button>
	            <button type="reset" class="btn btn-danger" id="team-reset-btn">초기화</button>
	        </div>
	    </form>		
      </div>
     <br><br>
	</div>

    <script>
        $(function(){
            $('#team-intro').keydown(function(){
                $('#count').text(this.value.length);
            });
        });
    </script>
</body>
</html>