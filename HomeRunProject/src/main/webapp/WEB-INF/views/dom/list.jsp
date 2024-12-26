<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

    <div>
        <br>
        <div>
            
            <c:forEach items="${ domList }" var="dom">
	            <input type="hidden" value="${ dom.domNo }" />
                <label>${ dom.domName }</label>
            </c:forEach>
            <c:forEach items="${ attList }" var="att">
	            <img src="/baseball${ att.imagePath }" alt="이미지">
            </c:forEach>
            
        </div>
       	<a href="saveForm.dom">구장 등록 폼으로 이동</a>
    </div>
    
    
    <h1>어이없네</h1>
    
    
    
        



</body>
</html>