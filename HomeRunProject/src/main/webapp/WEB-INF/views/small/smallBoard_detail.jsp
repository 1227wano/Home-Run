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

        table * {margin:5px;}
        table {width:100%;}
    </style>
</head>
<body>
        
    <jsp:include page="../common/menubar.jsp" />
    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>게시글 상세보기</h2>
            <br>

            <a class="btn btn-secondary" style="float:right;" href="/baseball/small">목록으로</a>
            <br><br>

            <table id="contentArea" align="center" class="table">
                <tr>
                    <th width="100">제목</th>
                    <td colspan="3">${smallBoard.boardTitle}</td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td>${smallBoard.nickName}</td>
                    <th>작성일</th>
                    <td>${smallBoard.createDate}</td>
                </tr>
                <tr>
                    <th>첨부파일</th>
                </tr>
                <tr>   
                    <c:choose>
	                    <c:when test="${empty file.originName}">
		                    <td colspan="3">
		                        첨부파일이 존재하지 않습니다.
		                    </td>
	                    </c:when>
	                    <c:otherwise>
		                    <td colspan="3">
		                    	<img src="/baseball/resources/upload_files/${file.changeName}" width="150px" height="150px"/>
		                        <a href="${file.changeName}" download="${file.originName}">${file.originName}</a>
		                    </td>
	                    </c:otherwise>
                    </c:choose>
                 </tr>
                <tr>
                    <th>내용</th>
                    <td colspan="3"></td>
                </tr>
                <tr>
                    <td colspan="4"><p style="height:150px;">${smallBoard.boardContent}</p></td>
                </tr>
            </table>
            <br>

            <div align="center">
            	<c:if test="${ (sessionScope.loginUser.nickName eq requestScope.smallBoard.nickName) }">
            	<a class="btn btn-danger" onclick="postSubmit(1);">수정하기</a>
            	</c:if>
                <c:if test="${ (sessionScope.loginUser.nickName eq requestScope.smallBoard.nickName) || (sessionScope.loginUser.userId eq 'admin')}">
                <a class="btn btn-danger" onclick="postSubmit(2);">삭제하기</a>
                </c:if>
            </div>
            
            <script>
            	function postSubmit(num){
            		
            		if(num == 1){
            			$('#postForm').attr('action', '/baseball/update-form.small').submit();
            		} else{
            			$('#postForm').attr('action', '/baseball/delete.small').submit();
            		}
            	}
            </script>
            <!-- 삭제기능에는 파일삭제도 있기 때문에 -->
            <form action="" method="post" id="postForm">
            	<input type="hidden" name="boardNo" value="${smallBoard.boardNo}" />
            </form>
            
            <br><br>

        </div>
        <br><br>

    </div>

</body>
</html>