<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

	<div class="container">
        <h2>공지사항 상세보기</h2>
        <br>
        <br>
        
        <table id="contentArea" algin="center" class="table">
            <tr>
                <th>제목</th>
                <td colspan="3">${notice.noticeTitle}</td>
            </tr>
            <tr>
                <th>작성일</th>
                <td>
                    <fmt:formatDate value="${notice.creationDate}" pattern="yyyy-MM-dd HH:mm" />
                </td>
            </tr>
            <tr>
                <th>첨부파일</th>
                
                   <c:choose>                   
	                    <c:when test="${ empty notice.attachMent }">
		                    <td colspan="3">
		                    	첨부파일이 존재하지 않습니다.
		                    </td>
	                    </c:when>
	                    <c:otherwise>
		                    <td colspan="3">
		                        <a href="${ notice.attachMent }" download="${ notice.attachMent }">${ notice.attachMent }</a>
		                    </td>
	                    </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <th>내용</th>
                <td colspan="3"></td>
            </tr>
            <tr>
            	<td colspan="4"><p style="height:150px;">${ notice.noticeContent }</p></td>
            </tr>
        </table>
        <a href="/baseball/notices" class="btn btn-secondary">목록으로</a>
        <br>
        <br>
        
        <div>
              <!-- 관리자 전용 수정/삭제 버튼 -->
              <c:if test="${ sessionScope.loginUser.userId == 'admin' }">
                  <a class="btn btn-primary" onclick="confirmUpdate();">수정</a>
                  <a class="btn btn-danger" onclick="confirmDelete();">삭제</a>
              </c:if>
        </div>
        
        <script>
        	function confirmUpdate() {
        		if(confirm("게시글을 수정하시겠습니까?")) {
        			$('#postForm').attr('action', '/baseball/notices/update-form').submit();
        		}
        	}
        	
        	function confirmDelete() {
        		if (confirm("게시글을 삭제하시겠습니까?")) {
        			$('#postForm').attr('action', '/baseball/notices/delete').submit();
        		}
        	}
        </script>
        
        
        <form action="" method="post" id="postForm">
        	<input type="hidden" name="noticeNo" value="${ notice.noticeNo }" />
        	<input type="hidden" name="attachMent" value="${ notice.attachMent }" />
        </form>
        
        
        
        
        
    </div>
</body>
</html>