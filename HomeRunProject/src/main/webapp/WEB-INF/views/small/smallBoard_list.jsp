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
        <div class="innerOuter" style="padding:5% 10%;">
            <h2>소모임 게시판</h2>
            <br>
            <c:if test="${ sessionScope.loginUser.userId eq 'admin' }">
            	<a class="btn btn-secondary" style="float:right;" href="adminList.small">허가리스트</a>
            </c:if>
            <c:if test="${ not empty sessionScope.loginUser }">
            	<a class="btn btn-secondary" style="float:right;" href="insertForm.small">글쓰기</a>
            </c:if>
            <c:if test="${ not empty sessionScope.loginUser }">
            	<a class="btn btn-secondary" style="float:right;" href="myList.small">나의 게시글 리스트</a>
           	</c:if>
            <form id="searchForm" action="small" method="get" align="left" style="float:right">
                <div class="select">
                    <select class="custom-select" name="boardLimit">
                        <option value="5">5개씩</option>
                        <option value="10">10개씩</option>
                        <option value="15">15개씩</option>
                        <option value="20">20개씩</option>
                    </select>
                </div>
                <c:choose>
                	<c:when test="${empty condition}">
                		<button type="submit" class="searchBtn btn btn-secondary">적용</button>
               		</c:when>
	                <c:otherwise>
	              	    <button type="button" class="searchBtn btn btn-secondary" 
	              	    		onclick="searchCondition();">적용</button>
	                </c:otherwise>
                </c:choose>
            </form>
            <br>
            <br>
            <table id="boardList" class="table table-hover" align="center">
                <thead>
                    <tr>
                        <th>글번호</th>
                        <th>팀명</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>작성일</th>
                        <th>조회수</th>
                        <th>참가</th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach items="${ smallBoard }" var="smallBoard">
	                    <tr>
	                        <td>${ smallBoard.boardNo }</td>
	                        <td>${ smallBoard.teamName }</td>
	                        <td onclick="detail('${smallBoard.boardNo}')">${ smallBoard.boardTitle }</td>
	                        <td>${ smallBoard.nickName }</td>
	                        <td>${ smallBoard.createDate }</td>
	                        <td>${ smallBoard.selectCount }</td>
	                        <td>
	                        	<button onclick="participate('${smallBoard.boardNo}')">신청</button>
	                        </td>
	                    </tr>
                    </c:forEach>
                </tbody>
            </table>
            <br>
			<script>
				function detail(boardNo){
					
					location.href = `/baseball/small/\${boardNo}`
				}
			
				function participate(boardNo){
					location.href = `/baseball/participate-form.small/\${boardNo}`
				}
				
				function searchCondition(){
					const option = document.querySelector('select').value;
					
					location.href = '/baseball/searchList.small?condition=${condition}&keyword=${keyword}&option='+option;
					
				}
			</script>
            <div id="pagingArea">
                <ul class="pagination">

					<c:choose>
						<c:when test="${ pageInfo.currentPage eq 1 }">                
                   			<li class="page-item disabled"><a class="page-link" >이전</a></li>
                   		</c:when>
                   		<c:when test="${empty condition}">
                   			<li class="page-item"><a class="page-link" href="small?page=${ pageInfo.currentPage - 1}&option=${option}">이전</a></li>
                   		</c:when>
                   		<c:otherwise>
                   			<li class="page-item"><a class="page-link" href="searchList.small?page=${ pageInfo.currentPage - 1 }&condition=${condition}&keyword=${keyword}&option=${option}">이전</a></li>
                    	</c:otherwise>
                    </c:choose>
                    
                    
                    <c:forEach begin="${ pageInfo.startPage }" end="${ pageInfo.endPage }" var="num">
                    	<c:choose>
		                	<c:when test="${empty condition }">
		                		<li class="page-item">
		                		<c:choose>
		                		<c:when test="${ empty boardLimit }">
			                    	<a class="page-link" href="small?page=${ num }">
			                    		${ num }
			                    	</a>
		                		</c:when>
		                		<c:otherwise>
		                			<a class="page-link" href="small?page=${ num }&boardLimit=${option}">
			                    		${ num }
			                    	</a>
		                		</c:otherwise>
		                		</c:choose>
		                    	</li>
		               		</c:when>
			                <c:otherwise>
			              	    <li class="page-item">
			                    	<a class="page-link" href="searchList.small?page=${ num }&condition=${condition}&keyword=${keyword}&option=${option}">
			                    		${ num }
			                    	</a>
		                    	</li>
			                </c:otherwise>
		                </c:choose>
                    </c:forEach>
                    
                    
                    
                    <c:choose>
                    	<c:when test="${ pageInfo.currentPage eq pageInfo.endPage }">
                    		<li class="page-item disabled"><a class="page-link" >다음</a></li>
                    	</c:when>
                    	<c:when test="${ empty condition}">
                    		<li class="page-item"><a class="page-link" href="small?page=${ pageInfo.currentPage + 1}&option=${option}">다음</a></li>
                    	</c:when>
                    	<c:otherwise>
                    		<li class="page-item"><a class="page-link" href="searchList.small?page=${ pageInfo.currentPage + 1 }&condition=${condition}&keyword=${keyword}">다음</a></li>
                    	</c:otherwise>
                    </c:choose>
                    
                    
                    
                </ul>
            </div>

            <br clear="both"><br>

            <form id="searchForm" action="searchList.small" method="get" align="center">
                <div class="select">
                    <select class="custom-select" name="condition">
                        <option value="writer">닉네임</option>
                        <option value="title">제목</option>
                        <option value="content">내용</option>
                        <option value="team">팀</option>
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

</body>
</html>