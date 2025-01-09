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
            <h2>게시글 참여자 리스트</h2>
            <br>
            <a class="btn btn-secondary" style="float:right;" href="/baseball/small">목록으로</a>
            <br>
            <br>
            <table id="participantList" class="table table-hover" align="center">
                <thead>
                    <tr>
                        <th>리스트번호</th>
                        <th>닉네임</th>
                        <th>신청내용</th>
                        <th>신청날짜</th>
                        <th>작성자허가</th>
                        <th>퇴출사유</th>
                        <th>자진퇴출</th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach items="${ participantList }" var="participantList">
	                    <tr>
	                        <td>${ participantList.listNo }</td>
	                        <td>${ participantList.nickName }</td>
	                        <td>${ participantList.participationContent }</td>
	                        <td>${ participantList.participationDate }</td>
	                        <td>${ participantList.writerPermission }
	                        <td>${ participantList.banReason }</td>
	                        <td>${ participantList.userEscape }</td>
	                        <td>
	                        	<button onclick="writerPermission('${participantList.listNo}')">수락</button>
	                        </td>
	                        <td>
	                        	<button onclick="banParticipant('${participantList.listNo}')">추방</button>
	                        </td>
	                    </tr>
                    </c:forEach>
                </tbody>
            </table>
            <br>
			<script>
				function writerPermission(listNo){
					location.href = `../writerPermission.small/\${listNo}`
				}
				
				function banParticipant(listNo){
					location.href = `../ban-form.small/\${listNo}`
				}
			</script>

            <div id="pagingArea">
                <ul class="pagination">
					<c:choose>
						<c:when test="${ pageInfo.currentPage eq 1 }">                
                   			<li class="page-item disabled"><a class="page-link" >이전</a></li>
                   		</c:when>
                   		<c:otherwise>
                   			<li class="page-item"><a class="page-link" href="small?page=${ pageInfo.currentPage - 1}">이전</a></li>
                    	</c:otherwise>
                    </c:choose>
                    <c:forEach begin="${ pageInfo.startPage }" end="${ pageInfo.endPage }" var="num">
                    	<li class="page-item">
	                    	<a class="page-link" href="small?page=${ num }">
	                    		${ num }
	                    	</a>
                    	</li>
                    </c:forEach>
                    <c:choose>
                    	<c:when test="${ pageInfo.currentPage eq pageInfo.endPage }">
                    		<li class="page-item disabled"><a class="page-link" >다음</a></li>
                    	</c:when>
                    	<c:otherwise>
                    		<li class="page-item"><a class="page-link" href="small?page=${ pageInfo.currentPage + 1}">다음</a></li>
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