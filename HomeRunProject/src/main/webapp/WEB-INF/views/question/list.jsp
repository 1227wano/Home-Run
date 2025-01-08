<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Document</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>

        .content {
            background-color:white;
            width:85%;
            margin:auto;
        }

        .innerOuter {
            border:1px solid white;
            width:85%;
            margin:auto;
            padding:5% 10%;
            background-color:white;
            text-align: center;
        }

        .inquiry-button {
            padding: 10px 20px; 
            font-size: 16px; 
            color: white; 
            background-color: gray; 
            border: none; 
            border-radius: 5px; 
            cursor: pointer; 
            transition: background-color 0.3s;
            display: flex;
        }

        .inquiry-button:hover {
            background-color: gray; 
        }


        .container {
            margin: 20px; 
        }

        ul.category-list {
            list-style: none; 
            padding: 0; 
            margin: 0; 
            display: flex; 
            justify-content: space-around; 
        }

        .category-list li {
            padding: 15px; 
            text-align: center; 
            border: 1px solid #ddd; 
            cursor: pointer; 
            flex-grow: 1; 
        }

        .category-list li.active {
            border: 2px solid gray; 
            color: #e74c3c; 
            font-weight: bold; 
        }

        .category-list li:hover {
            background-color: #f1f1f1; 
        }


        table#column-list {
            width: 100%;
            border-collapse: collapse; 
            margin-top: 20px; 
        }

        #column-list th, 
        #column-list td {
            padding: 15px; 
            text-align: center; 
            border: 1px solid #ddd; 
        }

        #column-list th {
            background-color: #f2f2f2; 
            font-weight: bold; 
        }

        #column-list tbody tr:hover {
            background-color: #f1f1f1; 
        }

        .pagination {
            display: flex; 
            justify-content: center; 
            padding: 10px; 
        }

        .pagination a {
            padding: 8px 16px; 
            margin: 0 5px; 
            text-decoration: none; 
            color: #333; 
            border: 1px solid #ddd; 
            border-radius: 4px; 
        }

        .pagination a:hover {
            background-color: #f1f1f1; 
        }

        .pagination a.active {
            background-color: #e74c3c; 
            color: white; 
            border: 1px solid #e74c3c; 
        }


        #searchForm {
            margin-top: 10px;
            padding: 10px 20px; 
        }

        .select, .text {
            display: inline-block; 
            margin-right: 10px;
            padding: 10px;
        }

        .form-control {
            padding: 10px 20px; 
            width: 200px; 
            border-radius: 5px;
            font-size: 16px;
        }

        .searchBtn {
            background-color: gray;
            color: white; 
            border: none; 
            padding: 10px 20px; 
            font-size: 16px; 
            border-radius: 5px; 
            cursor: pointer; 
            transition: background-color 0.3s; 
        }

        .searchBtn:hover {
            background-color: gray; 
        }

        .custom-select {
            border: none;
            padding: 10px 20px; 
            font-size: 16px; 
            border-radius: 5px;
            cursor: pointer; 
            border: 1px solid #ccc; 
            width: 100px; 
            height: 42px;
        }



    </style>
</head>
<body>

    <jsp:include page="../common/menubar.jsp" />

    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>Q&A</h2>
            <br>

            <div class="inquiry-button-container" style="margin-top: 10px;">
                <button class="inquiry-button">1:1 문의하기</button>
            </div>

            <hr>


            <div id="questionList" class="container">
                <ul class="category-list">
                    <li class="active">전체</li>
                    <li>굿즈</li>
                    <li>티켓</li>
                    <li>주차 관련</li>
                    <li>기타</li>
                </ul>                
            </div>
            <br>

                <table id="column-list">
                    <thead>
                        <tr>
                            <th>글번호</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일</th>
                            <th>상태</th>
                        </tr>
                    </thead>
                    <tbody>
                    	<c:forEach items="${ questions }" var="question">
	                        <tr onclick="detail(${ question.questionNo })">
	                            <td>${ qiestion.questionNo }</td>
	                            <td>${ question.questionTitle }</a></td>
	                            <td>${ question.questionWriter }</td>
	                            <td>${ question.creationDate }</td>
	                            <td>${ question.status }</td>                   
	                        </tr>
	                    </c:forEach>
                    </tbody>
                </table>
                <br>

                <div class="pagination">
                    <a href="#">&laquo;</a> 
                    <a href="#">1</a>
                    <a href="#">2</a>
                    <a href="#" class="active">3</a> 
                    <a href="#">4</a>
                    <a href="#">5</a>
                    <a href="#"> &raquo;</a> 
                </div>
                
                <form id="searchForm" action="/search" method="get" align="center">
                    <div class="select">
                        <select class="custom-select" name="condition">
                            <option value="title">제목</option>
                            <option value="content">내용</option>
                        </select>
                    </div>
                    <div class="text">
                        <input type="text" class="form-control" name="keyword" placeholder="검색어를 입력하세요.">
                    </div>
                    <button type="submit" class="searchBtn btn btn-primary">검색</button>
                </form>
        </div>
       
    </div>

    <jsp:include page="" />
</body>
</html>
