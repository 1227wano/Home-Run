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

        #enrollForm>table {width:100%;}
        #enrollForm>table * {margin:5px;}
        #img-area{
            width : 100%;
            margin : auto;
            text-align: center;
        }
        #img-area > img{
            width : 80%;
        }
    </style>
</head>
<body>
        
    <jsp:include page="../common/menubar.jsp" />

    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>게시글 수정하기</h2>
            <br>
            <a class="btn btn-secondary" style="float:right;" href="/baseball/small">목록으로</a>
            <br><br>

            <form id="enrollForm" method="post" action="update.small" enctype="multipart/form-data">
                <input type="hidden" name="boardWriter" value="${ sessionScope.loginUser.userNo }">
                <table align="center">
                    <tr>
                        <th><label for="title">제목</label></th>
                        <td><input type="text" id="title" class="form-control" value="${smallBoard.boardTitle}" name="boardTitle" required></td>
                    </tr>
                    <tr>
                        <th><label for="team">팀</label></th>
                        <td><input type="text" id="title" class="form-control" value="${smallBoard.teamName}" name="teamName" readonly></td>
                    </tr>
                    <tr>
                        <th><label for="writer">작성자</label></th>
                        <td><input type="text" id="writer" class="form-control" value="${ sessionScope.loginUser.nickName }" readonly></td>
                    </tr>
                    <tr>
                        <th><label for="upfile">첨부파일</label></th>
                        <td>
                            <input type="file" id="upfile" class="form-control-file border" name="upfile">
                            
                            <c:if test="${ not empty file.originName }">
                            현재 업로드된 파일 : 
                            <a href="${ file.changeName }" download="${ file.originName }">${ file.originName}</a>
                            <input type="hidden" value="${file.originName}" name="originName" />
                            <input type="hidden" value="${file.changeName}" name="changeName" />
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <th><label for="content">내용</label></th>
                        <td><textarea id="content" class="form-control" rows="10" style="resize:none;" name="boardContent" required>${smallBoard.boardContent}${smallBoard.boardContent}</textarea></td>
                    </tr>
                </table>
                <br>

                <div align="center">
                    <button type="submit" class="btn btn-primary">수정하기</button>
                    <button type="reset" class="btn btn-danger">지우기</button>
                </div>
            </form>
        </div>
        <br><br>

    </div>
    
</body>
</html>