<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<head>
    <meta charset="UTF-8">
    <title>선수 일람 화면</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <style>
		
    </style>
</head>

<body>
    <main id="main">
        <ul class="playerList">
            <li>
                <a href="">
                    <img src="resources/image/bono.jpg" alt="선수사진" class="player-photo">
                    <strong class="name">
                        선수명
                    </strong>
                    <span class="position">
                        포지션
                    </span>
                    <span class="number">
                        NO.선수번호
                    </span>
                </a>
                <a href="">
                    <img src="resources/image/bono.jpg" alt="선수사진" class="player-photo">
                    <strong class="name">
                        선수명
                    </strong>
                    <span class="position">
                        포지션
                    </span>
                    <span class="number">
                        NO.선수번호
                    </span>
                </a>
                <a href="">
                    <img src="resources/image/bono.jpg" alt="선수사진" class="player-photo">
                    <strong class="name">
                        선수명
                    </strong>
                    <span class="position">
                        포지션
                    </span>
                    <span class="number">
                        NO.선수번호
                    </span>
                </a>
                <a href="">
                    <img src="resources/image/bono.jpg" alt="선수사진" class="player-photo">
                    <strong class="name">
                        선수명
                    </strong>
                    <span class="position">
                        포지션
                    </span>
                    <span class="number">
                        NO.선수번호
                    </span>
                </a>
                <a href="">
                    <img src="resources/image/bono.jpg" alt="선수사진" class="player-photo">
                    <strong class="name">
                        선수명
                    </strong>
                    <span class="position">
                        포지션
                    </span>
                    <span class="number">
                        NO.선수번호
                    </span>
                </a><br>

                <div class="playerLists">
                    <br>
                    <c:choose>
                        <c:when test="${ empty map }">
                            등록된 첨부파일이 없습니다.
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${ map }" var="player">
                                <div class="playerImgs" align="center">
                                    <input type="hidden" value="${ player.userNo }" /> <img
                                        src="${ player.imagePath }" alt="이미지">
                                    <p>
                                        <label>No. ${ player.backNo }</label> <span>${ player.playerName }</span>                                        <br> <label>조회수</label> : <span>${ board.count }</span>
                                    </p>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>


                <button id="more-btn" align="center">'더보기' ajax로 선수 15명 더 보여주기</button>
                
                <!-- 본래 페이징의 숫자버튼 -->
                <c:forEach begin="${ pageInfo.startPage }" end="${ pageInfo.endPage }" var="num">
					<li class="page-item"><a class="page-link"
						href="boards?page=${ num }"> ${ num } </a></li>
				</c:forEach>
				
				<!-- 더보기 ajax 버튼 -->
                <c:forEach begin="${ player.userNo = 1 }" end="${ player.userNo = player.userNo.length }" var="num">
					<c:if test="${ playerStatus = 'Y' }" >
						<li class=""><a class=""
							href=""> 더보기 </a></li>
					</c:if>
				</c:forEach>
				
            </li>
        </ul>
        
        <script>
        	
        
        </script>
        
        
        
    </main>
</body>
</html>