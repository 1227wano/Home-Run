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

        #updateForm>table {width:100%;}
        #updateForm>table * {margin:5px;}
    </style>
</head>
<body>
        
    <jsp:include page="../common/menubar.jsp" />

    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>게시글 수정하기</h2>
            <br>
            

            <form id="updateForm" method="post" action="/baseball/notices/update" enctype="multipart/form-data">
            	<input type="hidden" name="noticeNo" value="${ notice.noticeNo }">
                <table algin="center">
                    <tr>
                        <th><label for="title">제목</label></th>
                        <td><input type="text" id="title" class="form-control" value="${ requestScope.notice.noticeTitle }" name="noticeTitle" required></td>
                    </tr>
                    <tr>
                        <th><label for="creationDate">작성일</label></th>
                        <td><input type="text" id="creationDate" class="form-control" value="${ requestScope.notice.creationDate }" name="creationDate" readonly></td>
                    </tr> 
                    <tr>
                        <th><label for="upfile">첨부파일</label></th>
                        <td>
                            <input type="file" id="upfile" class="form-control-file border" name="upfile">
                            
                            <c:if test="${ not empty notice.attachMent }">
	                            현재 업로드된 파일 : 
	                            <a href="${ notice.attachMent }" download="${ notice.attachMent }">${ notice.attachMent }</a>
	                            <input type="hidden" value="${ notice.attachMent }" name="attachMent" />
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <th><label for="content">내용</label></th>
                        <td><textarea id="content" class="form-control" rows="10" style="resize:none;" name="noticeContent" required>${ requestScope.notice.noticeContent }</textarea></td>
                    </tr>
                </table>
                <br>

                <div align="center">
                    <button type="submit" class="btn btn-primary">수정</button>
                    <button type="reset" class="btn btn-danger" onclick="history.back();">이전</button>
                </div>
                
            </form>
        </div>
        <br><br>
        

    </div>
    
    <jsp:include page="" />
    
</body>
</html>