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

            <a class="btn btn-secondary" style="float:right;" href="/baseball/freeBoard">목록으로</a>
            <br><br>

            <table id="contentArea" align="center" class="table">
                <tr>
                    <th width="100">제목</th>
                    <td colspan="3">${freeBoard.boardTitle}</td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td>${freeBoard.nickName}</td>
                    <th>작성일</th>
                    <td>${freeBoard.createDate}</td>
                </tr>
                <tr>
                    <th>첨부파일</th>
                </tr>
                <tr>   
                    <c:choose>
	                    <c:when test="${empty file1.originName}">
		                    <td colspan="3">
		                        첨부파일이 존재하지 않습니다.
		                    </td>
	                    </c:when>
	                    <c:otherwise>
		                    <td colspan="3">
		                        <a href="${file1.changeName}" download="${file1.originName}">${file1.originName}</a>
		                    </td>
	                    </c:otherwise>
                    </c:choose>
                 </tr>
                 <tr>
                    <c:choose>
	                    <c:when test="${empty file2.originName}">
		                    <td colspan="3">
		                        첨부파일이 존재하지 않습니다.
		                    </td>
	                    </c:when>
	                    <c:otherwise>
		                    <td colspan="3">
		                        <a href="${file2.changeName}" download="${file2.originName}">${file2.originName}</a>
		                    </td>
	                    </c:otherwise>
                    </c:choose>
                 </tr>
                 <tr>
                    <c:choose>
	                    <c:when test="${empty file3.originName}">
		                    <td colspan="3">
		                        첨부파일이 존재하지 않습니다.
		                    </td>
	                    </c:when>
	                    <c:otherwise>
		                    <td colspan="3">
		                        <a href="${file3.changeName}" download="${file3.originName}">${file3.originName}</a>
		                    </td>
	                    </c:otherwise>
                    </c:choose>
                 </tr>
                 <tr>
                    <c:choose>
	                    <c:when test="${empty file4.originName}">
		                    <td colspan="3">
		                        첨부파일이 존재하지 않습니다.
		                    </td>
	                    </c:when>
	                 	<c:otherwise>
		                    <td colspan="3">
		                        <a href="${file4.changeName}" download="${file4.originName}">${file4.originName}</a>
		                    </td>
	                    </c:otherwise>
                    </c:choose>
                 </tr>
                 <tr>
                   <c:choose>
	                    <c:when test="${empty file5.originName}">
		                    <td colspan="3">
		                        첨부파일이 존재하지 않습니다.
		                    </td>
	                    </c:when>
	                 	<c:otherwise>
		                    <td colspan="3">
		                        <a href="${file5.changeName}" download="${file5.originName}">${file5.originName}</a>
		                    </td>
	                    </c:otherwise>
                    </c:choose>
                </tr>
                <tr>
                    <th>내용</th>
                    <td colspan="3"></td>
                </tr>
                <tr>
                    <td colspan="4"><pre style="height:150px;">${freeBoard.boardContent}</pre></td>
                </tr>
            </table>
            <br>

            <div align="center">
                <c:if test="${ (sessionScope.loginUser.nickName eq requestScope.freeBoard.nickName) || (sessionScope.loginUser.userId eq 'admin')}">
                <a class="btn btn-primary" onclick="postSubmit(1);">수정하기</a>
                <a class="btn btn-danger" onclick="postSubmit(2);">삭제하기</a>
                </c:if>
            </div>
            
            <script>
            	function postSubmit(num){
            		
            		if(num == 1){
            			$('#postForm').attr('action', '/baseball/freeBoard/update-form.free').submit();
            		} else{
            			$('#postForm').attr('action', '/baseball/freeBoard/delete.free').submit();
            		}
            		
            	}
            </script>
            <!-- 삭제기능에는 파일삭제도 있기 때문에 -->
            <form action="" method="post" id="postForm">
            	<input type="hidden" name="boardNo" value="${freeBoard.boardNo}" />
            	<input type="hidden" name="file1ChangeName" value="${file1.changeName }" />
            	<input type="hidden" name="file2ChangeName" value="${file2.changeName }" />
            	<input type="hidden" name="file3ChangeName" value="${file3.changeName }" />
            	<input type="hidden" name="file4ChangeName" value="${file4.changeName }" />
            	<input type="hidden" name="file5ChangeName" value="${file5.changeName }" />
            </form>
            
            <br><br>

            <table id="replyArea" class="table" align="center">
                <thead>
                
                	<c:choose>
                		<c:when test="${ empty sessionScope.loginUser }">
	                    <tr>
	                        <th colspan="2">
	                            <textarea class="form-control" readonly cols="55" rows="2" style="resize:none; width:100%;">로그인 후 이용가능합니다.</textarea>
	                        </th>
	                        <th style="vertical-align:middle"><button class="btn btn-secondary">등록하기</button></th> 
	                    </tr>
                    	</c:when>
                    	<c:otherwise>
	                    <tr>
	                        <th colspan="2">
	                            <textarea class="form-control" id="content" cols="55" rows="2" style="resize:none; width:100%;"></textarea>
	                        </th>
	                        <th style="vertical-align:middle"><button class="btn btn-secondary" onclick="addReply();">등록하기</button></th> 
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
    
    <script>
    	
    	function addReply(){
    		
    		if($('#content').val().trim() != ''){
    			
    			$.ajax({
    				url : '/baseball/reply.free',
    				data : {
    					refBno : ${freeBoard.boardNo},
    					replyContent : $('#content').val(),
    					replyWriter	: '${sessionScope.loginUser.userNo}'
    				},
    				type : 'post',
    				success : function(result){
    					
    					
    					if(result.data === 1){
    						$('#content').val('');
    					}
    					selectReply();
    					alert(result.message);
    				},
    				error : function(result){
    					alert(result.responseJSON.message);
    				}
    			});
    		}
    	}
    	
    	$(function(){
    		selectReply();
    	})
    	
    	function selectReply(){
    		
    		$.ajax({
    			url : '/baseball/reply.free',
    			type : 'get',
    			data : {
    				boardNo : ${freeBoard.boardNo}
    			},
    			success : function(result){
    				
    				const replies = [...result.data];
    				//console.log(replies);
    				
    				const resultStr = replies.map(e => 
								    					`<tr>
								    					<td>\${e.replyNickName}</td>
								    					<td>\${e.replyContent}</td>
								    					<td>\${e.createDate}</td>
								    					<td>
										    					<button onclick="deleteChat(\${e.replyNo});"> 삭제 </button>
								    					</td>
								    					</tr>`
    												).join('');
    				$('#replyArea tbody').html(resultStr);
    				$('#rcount').text(result.data.length);
    			}
    		});
    	}
    	
    	function deleteChat(e){
    		const replyNo = e;
    		// console.log(replyNo);
    		$.ajax({
    			url : '/baseball/reply.free/deleteChat',
    			type : 'get',
    			data : {
    				replyNo : replyNo
    			},
    			success : function(result){
					alert("댓글 삭제 성공하셨습니다.");
    				selectReply();
    			},
    			error : function(result){
    				//console.log(result.responseJSON.message);
    				alert(result.responseJSON.message);
    			}
    			
    		})
    	}
    	
    </script>
    
</body>
</html>