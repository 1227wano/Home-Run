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
	.list-area{
		text-align : center;
	}
	
	.dom-list {
		width : 320px;
		height : 240px;
	}
	
	.dom-list > img{
		width : 270px;
		height : 180px;
		margin-bottom : 8px;
		border : 1px solid rgb(172 205 255 / 57%);
		
	}
</style>

</head>
<body>

    <div class="list-area">
		<br>        
        <div align="center">
	        <c:choose>
	        	<c:when test="${ empty domList }">
					등록된 게시글이 존재하지 않습니다. <br>
				</c:when>
				<c:otherwise>
		            <c:forEach items="${ domList }" var="dom">
			            <div class="dom-list" onclick="detail('${dom.domNo}')" align="center">
				            <img src="/baseball${ dom.imagePath }" alt="이미지">
			                <label>No. ${ dom.domNo } / ${ dom.domName }</label>
			            </div>
		            </c:forEach>
				</c:otherwise>
	        </c:choose>
        </div>
        
        <script>
	    	function detail(num){
	    		location.href = `/baseball/dom/\${num}`;
	    	}
    	</script>
        
	    <a href="/baseball/saveForm.dom">구장 등록 폼으로 이동</a>
    </div>
    
    
    
    
    
    
    
        



</body>
</html>