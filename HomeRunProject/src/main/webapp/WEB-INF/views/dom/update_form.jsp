<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<style>
	.domEnroll-area{
		text-align : center;
	}
</style>

</head>
<body>

	<jsp:include page="../common/menubar.jsp" /> 

	<br><br><br><br><br>
	
	<div class="domEnroll-area">
		<form id="" method="post" action="update" enctype="multipart/form-data" >
			
			<input type="hidden" name="domNo" value="${ dom.domNo }" />
			
			<h2>구장 정보 수정</h2>
			
			<div>
				<label>구장명</label>
				<input type="text" name="domName" value="${ dom.domName }">
			</div>
			<div>
				<label>구장 세부 내용</label>
				<textarea name="domContent">${ dom.domContent }</textarea>
			</div>
			<div>
				<label>구장 주소</label>
				<textarea name="domAddr">${ dom.domAddr }</textarea>
			</div>
			<div>
				<label>구장 이미지 첨부</label>
				<img width="300" height="180" id="title-img"
				src="/baseball${ requestScope.dom.imagePath }" alt="대표이미지">
				<input type="file" id="upfile" name="upfile" onchange="loadImg(this);" />
			</div>
			
			<br>
			<div align="center">
				<button type="submit">정보 수정</button>
                <button type="reset">취소하기</button>
			</div>
			<br>
			
			<br><br><br>
			
			<script>
				function loadImg(inputFile){

					if(inputFile.files.length === 1){
						
						const reader = new FileReader();
						reader.readAsDataURL(inputFile.files[0]);
						
						reader.onload = function(e){
							
							const url = e.target.result;
							
							$('#title-img').attr('src', url);
						}
					} else {
						
						const heart = "https://img.sbs.co.kr/newimg/news/20240815/201973069_500.jpg";
						
						$('#title-img').attr('src', heart); 
					}
				}
				
				$(function (){
					
					$('#upfile').hide();
					
					$('#title-img').click(function (){
						$("#upfile").click();
					});
				});
				
			</script>
			
		</form>
	</div>
	
	


</body>
</html>