<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

            <a class="btn btn-secondary" style="float:right;" href="">목록으로</a>
            <br><br>

            <table id="contentArea" algin="center" class="table">
                <tr>
                    <th width="100">제목</th>
                    <td colspan="3">${ notice.noticeTitle }</td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td>${ sessionScope.loginUser.userId == 'admin' }</td>
                    <th>작성일</th>
                    <td>${ notice.creationDate }</td>
                </tr>
                
                  <c:choose>
                  	<c:when test="${ empty notice.attachMent }">
                  		<td colspan="3">
                  			<input type="file" name="attachMent" />
                  		</td>
                  	</c:when>
                  	<c:otherwise>
                  		<td colspan="3">
                  			${ notice.attachMent }
                  			<input type="file" name="attachMent" />
                  		</td>
                  	</c:otherwise>
                  </c:choose>
		        
                <tr>
                    <th>내용</th>
                    <td colspan="3"></td>
                </tr>
                <tr>
                    <td colspan="4"><p style="height:150px;">${ notice.noticeContent }</p></td>
                </tr>
            </table>
            <br>

            <div align="center">
                <!-- 수정하기, 삭제하기 버튼은 이 글이 본인이 작성한 글일 경우에만 보여져야 함 -->
               	<c:if test="${ sessionScope.loginUser.userId == 'admin' }">
	                <a class="btn btn-primary" href="">수정하기</a>
	                <a class="btn btn-danger" href="">취소하기</a>
	            </c:if>
            </div>
            
            <br><br>

			<script>
            	function postSubmit(num){
            		
            		if(num == 1) {
            			$('#postForm').attr('action', '/baseball/notices/update-form').submit();
            		} else {
            			$('#postForm').attr('action', '/baseball/notices/delets').submit();
            		}
            	}
            </script>
            
             <form action="" method="post" id="postForm">
            	<input type="hidden" name="noticeNo" value="${ board.boardNo }" />
            	<input type="hidden" name="atachMent" value="${ board.changeName }" />
            	
            	<!--
				<input type="hidden" name="userId" value="${ loginUser.userId }" />            	
            	-->
            </form>
          
			
            <!-- 댓글 기능은 나중에 ajax 배우고 나서 구현할 예정! 우선은 화면구현만 해놓음 -->
            <table id="replyArea" class="table" align="center">
                <thead>
                
                <c:choose>
                	<c:when test="${ sessionScope.loginUser.userId == 'admin' }">
                    <tr>
                        <th colspan="2">
                            <textarea class="form-control" name="" id="content" cols="55" rows="2" style="resize:none; width:100%;">관리자만 이용가능합니다.</textarea>
                        </th>
                        <th style="vertical-align:middle"><button class="btn btn-secondary">등록하기</button></th> 
                    </tr>
                 	</c:when>
                 	<c:otherwise>
                 	<tr>
                        <th colspan="2">
                            <textarea class="form-control" id="content" cols="55" rows="2" style="resize:none; width:100%;"></textarea>
                        </th>
                        <th style="vertical-align:middle"><button class="btn btn-secondary">등록하기</button></th> 
                    </tr>
                    </c:otherwise>
                 </c:choose>

                    <tr>
                        <td colspan="3">댓글(<span id="rcount">0</span>)</td>
                    </tr>
                </thead>
                <tbody>
                    
                </tbody>
            </table>
        </div>
        <br><br>

    </div>
    
    <jsp:include page="" />
    
</body>
</html>