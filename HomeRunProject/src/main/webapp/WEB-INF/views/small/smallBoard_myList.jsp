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

        #boardList {text-align:center;}
        #boardList>tbody>tr:hover {cursor:pointer;}

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
</head>
<body>
    
    <jsp:include page="../common/menubar.jsp" />

    <div class="content">
        <br><br>
            <br>
            <h2>나의 게시글 조회</h2>
            <br>
            <a class="btn btn-secondary" style="float:right;" href="/baseball/small">목록으로</a>
            <br><br>
            <table id="adminBoardList" class="table table-hover" align="center">
                <thead>
                    <tr>
                        <th>글번호</th>
                        <th>팀명</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>작성일</th>
                        <th>관리자허가</th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach items="${ myBoards }" var="myBoards">
	                    <tr onclick="detail('${myBoards.boardNo}')">
	                        <td>${ myBoards.boardNo }</td>
	                        <td>${ myBoards.teamName }</td>
	                        <td>${ myBoards.boardTitle }</td>
	                        <td>${ myBoards.nickName }</td>
	                        <td>${ myBoards.createDate }</td>
	                        <td>${ myBoards.adminStatus }</td>
	                    </tr>
                    </c:forEach>
                </tbody>
            </table>
            <br>
            <script>
            	function detail(num){
            		location.href = `myListDetail/\${num}`;
            	}
            
            </script>
            <div id="pagingArea">
                <ul class="pagination">

					<c:choose>
						<c:when test="${ pageInfo.currentPage eq 1 }">                
                   			<li class="page-item disabled"><a class="page-link" >이전</a></li>
                   		</c:when>
                   		<c:otherwise>
                   			<li class="page-item"><a class="page-link" href="myList.small?page=${ pageInfo.currentPage - 1}">이전</a></li>
                    	</c:otherwise>
                    </c:choose>
                    <c:forEach begin="${ pageInfo.startPage }" end="${ pageInfo.endPage }" var="num">
                    	<li class="page-item">
	                    	<a class="page-link" href="myList.small?page=${ num }">
	                    		${ num }
	                    	</a>
                    	</li>
                    </c:forEach>
                    <c:choose>
                    	<c:when test="${ pageInfo.currentPage eq pageInfo.endPage }">
                    		<li class="page-item disabled"><a class="page-link" >다음</a></li>
                    	</c:when>
                    	<c:otherwise>
                    		<li class="page-item"><a class="page-link" href="myList.small?page=${ pageInfo.currentPage + 1}">다음</a></li>
                    	</c:otherwise>
                    </c:choose>
                </ul>
            </div>
            <br><br>
        </div>
        <br><br>
    </div>

</body>
</html>