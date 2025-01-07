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
	            background-color:rgb(247, 245, 245);
	            width:80%;
	            margin:auto;
	        }
	        .innerOuter {
	            border:1px solid lightgray;
	            width:80%;
	            margin:auto;
	            padding:5% 10%;
	            background-color:white;
	        }
	
	        #noticeList {text-align:center;}
	        #noticeList>tbody>tr:hover {cursor:pointer;}
	        #pagingArea {width:fit-content; margin:auto;}
	        
	        #searchForm {
	            width:80%;
	            margin:auto;
	        }
	        #searchForm>* {
	            float:left;
	            margin:5px;
	        }
	        .select {width:20%;}
	        .text {width:53%;}
	        .searchBtn {width:20%;}
	    </style>
	    
	      <script>
		        function detail(num) {
		            // 공지사항 세부 페이지로 이동
		            location.href = `notices/\${num}`;
		        }
    	   </script>
    	   
    	   
    
	</head>
	<body>
	    
	    <jsp:include page="../common/menubar.jsp" />
	
	    <div class="content">
	        <br><br>
	        <div class="innerOuter" style="padding:5% 10%;">
	            <h2>공지사항</h2>
	            <br><br>
	            
	            <c:if test="${ sessionScope.loginUser.userId == 'admin' }">
            		<a class="btn btn-secondary" style="float:right;" href="insertForm">글쓰기</a>
            	</c:if>
            	
	            <table id="noticeList" class="table table-hover" align="center">
	                <thead>
	                    <tr>
	                        <th>글번호</th>
	                        <th>제목</th>
	                        <th>작성일</th>
	                        <th>첨부파일</th>
	                    </tr>
	                </thead>
	                <tbody>
	                
	                 <c:forEach items="${ notices }" var="notice">
	                   <tr onclick="detail(${ notice.noticeNo })">
	                        <td>${ notice.noticeNo }</td>
	                        <td>${ notice.noticeTitle }</td>
	                        <td>${ notice.creationDate }</td>
	                        <td>
	                        	<c:if test="${ not empty notice.attachMent }">
	                        		💌
	                        	</c:if>
	                        </td>
	                    </tr>
	                  </c:forEach>
	                </tbody>
	            </table>
	            
	            <br>
	
	            <div id="pagingArea">
                <ul class="pagination">
                
					<li class="page-item"><a class="page-link" href="notices?page=${ pageInfo.currentPage - 1 }">이전</a>
                
                    <li class="page-item disabled"><a class="page-link" href="#">이전</a></li>
                    
                    <c:forEach begin="${ pageInfo.startPage }" end="${ pageInfo.endPage }" var="num">
                    	<li class="page-item">
                    		<a class="page-link" href="notices?page=${ num }">
                    			${ num }
                    		</a>
                    	</li>
                    </c:forEach>
                    
                    <li class="page-item"><a class="page-link" href="#">다음</a></li>
                    
                    <li class="page-item disabled"><a class="page-link" href="#">다음</a></li>
                    
                </ul>
            </div>
	
	            <br clear="both"><br>
	
	            <form id="searchForm" action="/notice/search" method="get" align="center">
	                <div class="select">
	                    <select class="custom-select" name="condition">
	                        <option value="title">제목</option>
	                        <option value="content">내용</option>
	                    </select>
	                </div>
	                <div class="text">
	                    <input type="text" class="form-control" name="keyword">
	                </div>
	                <button type="submit" class="searchBtn btn btn-secondary">검색</button>
	            </form>
	            <br><br>
	        </div>
	        <br><br>
	
			
	    </div>
	
	    <jsp:include page="" />
	
	</body>
	</html>