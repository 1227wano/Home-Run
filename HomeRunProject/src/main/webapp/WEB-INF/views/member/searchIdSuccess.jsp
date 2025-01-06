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

        .searchIdSuccess {
            width: 400px;
            height: 450px;
            border: 1px solid black;
            margin: auto;
        }

        #middle{
            margin-top: 200px;
        }


    </style>
</head>
<body>

	<jsp:include page="../common/menubar.jsp" />
	
    <div class="content">
   

        <div class="searchId" align="center">
            <h1>아이디 찾기 성공</h1>
            <br>
        
        </div>

        <div class="searchIdSuccess" align="center">
            <h2 id="middle">
            <label >${ successId.userId }</label>
            </h2>

        </div>
        
       
        <br><br>
        
        <div class="search" align="center">
				
            <a href="/baseball/" style="text-decoration : none; color: rgb(68, 68, 68); font-size : 15px; margin: 5px; "> HOME </a> |
        
            <a href="#" style="text-decoration : none; color: rgb(68, 68, 68); font-size : 15px; margin: 5px;"> 비밀번호 찾기</a>
        
        </div>




    </div>
    
</body>
</html>