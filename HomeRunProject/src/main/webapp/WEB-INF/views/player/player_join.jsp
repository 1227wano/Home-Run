<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>팀 창설 신청 페이지</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<jsp:include page="../common/menubar.jsp" />

	<div class="content">
	   <br><br>
	   <div class="innerOuter">
	    <h2>팀 소속 신청</h2>
	    <br>
	    
	    <form id="joinTeam" action="joinTeam" method="post" enctype="multipart/form-data">
	        <div class="form-group" id="team-enroll-form">
	        	
				<input type="hidden" name="playerNo" value="${ player.playerNo }" />
				
				<div class="row g-2">
				  <div class="col-md">
				    <div class="form-floating">
				      <input type="text" class="form-control" id="floatingInputGrid" name="playerTeam">
				      <label for="floatingInputGrid">조회되는 팀 중 소속하고 싶은 팀명을 정확히 입력해주세요.</label>
				    </div>
				  </div>
				</div>     	
	    		<!--  
	            <fieldset>
	                <legend>자기소개글 작성</legend>
	                <textarea id="team-intro" name="teamIntro" cols="53" rows="34" style="resize:none;" placeholder="자기소개와 포부를 1000자 이내로 작성해주세요." maxlength="1000"></textarea>
	                <br>
	                <span id="count">0</span> / 1000
	            </fieldset>
	            -->
	            <br>
	
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