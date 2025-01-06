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

            <form id="updateForm" method="post" action="/baseball/freeBoard/update.free" enctype="multipart/form-data">
                <input type="hidden" name="boardNo" value="${ freeBoard.boardNo }" />
                <table align="center">
                    <tr>
                        <th><label for="title">제목</label></th>
                        <td><input type="text" id="title" class="form-control" value="${freeBoard.boardTitle}" name="boardTitle" required></td>
                    </tr>
                    <tr>
                        <th><label for="writer">작성자</label></th>
                        <td><input type="text" id="nickName" class="form-control" value="${freeBoard.nickName }" name="nickName" readonly></td>
                    </tr> 
                    <tr>
                        <th><label for="upfile">첨부파일1</label></th>
                        <td>
                            <input type="file" id="upfile" class="form-control-file border" name="upfile">
                            
                            <c:if test="${ not empty file1.originName }">
                            현재 업로드된 파일 : 
                            <a href="${ file1.changeName }" download="${ file1.originName }">${ file1.originName}</a>
                            <input type="hidden" value="${file1.originName}" name="originName" />
                            <input type="hidden" value="${file1.changeName}" name="changeName" />
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <th><label for="upfile">첨부파일2</label></th>
                        <td>
                            <input type="file" id="upfile" class="form-control-file border" name="upfile">
                            
                            <c:if test="${ not empty file2.originName }">
                            현재 업로드된 파일 : 
                            <a href="${ file2.changeName }" download="${ file2.originName }">${ file2.originName}</a>
                            <input type="hidden" value="${file2.originName}" name="originName" />
                            <input type="hidden" value="${file2.changeName}" name="changeName" />
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <th><label for="upfile">첨부파일3</label></th>
                        <td>
                            <input type="file" id="upfile" class="form-control-file border" name="upfile">
                            
                            <c:if test="${ not empty file3.originName }">
                            현재 업로드된 파일 : 
                            <a href="${ file3.changeName }" download="${ file3.originName }">${ file3.originName}</a>
                            <input type="hidden" value="${file3.originName}" name="originName" />
                            <input type="hidden" value="${file3.changeName}" name="changeName" />
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <th><label for="upfile">첨부파일4</label></th>
                        <td>
                            <input type="file" id="upfile" class="form-control-file border" name="upfile">
                            
                            <c:if test="${ not empty file4.originName }">
                            현재 업로드된 파일 : 
                            <a href="${ file4.changeName }" download="${ file4.originName }">${ file4.originName}</a>
                            <input type="hidden" value="${file4.originName}" name="originName" />
                            <input type="hidden" value="${file4.changeName}" name="changeName" />
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <th><label for="upfile">첨부파일5</label></th>
                        <td>
                            <input type="file" id="upfile" class="form-control-file border" name="upfile">
                            
                            <c:if test="${ not empty file5.originName }">
                            현재 업로드된 파일 : 
                            <a href="${ file5.changeName }" download="${ file5.originName }">${ file5.originName}</a>
                            <input type="hidden" value="${file5.originName}" name="originName" />
                            <input type="hidden" value="${file5.changeName}" name="changeName" />
                            </c:if>
                        </td>
                    </tr>
                    
                    <tr>
                        <th><label for="content">내용</label></th>
                        <td><textarea id="content" class="form-control" rows="10" style="resize:none;" name="boardContent" required>${freeBoard.boardContent}</textarea></td>
                    </tr>
                </table>
                <br>

                <div align="center">
                    <button type="submit" class="btn btn-primary">수정하기</button>
                    <a href="/baseball/freeBoard" class="btn btn-danger" >이전으로</a>
                </div>
            </form>
        </div>
        <br><br>

    </div>
    
</body>
</html>