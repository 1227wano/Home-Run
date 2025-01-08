<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선수 및 팀 관련 정보 조회</title>
</head>
<body>
	
	<jsp:include page="../common/menubar.jsp" />
	
	<!-- 
		본인이 선수신청했다 == (시퀀스로) 선수번호가 생겼다. if(playerNo != null)
		-> case 1 : 선수 승인 안됨 -> "아직 승인되지 않았습니다."
		   case 2 : 선수 승인 됨 ->	선수 정보 상세보기처럼 출력하기  
	-->	
	<c:choose> 
		<c:when test="${ player.playerNo ne null }">
			<c:choose>
				<c:when test="${ player.playerStatus eq 'Y' }">
					<div id="player-detail">	
						<div class="player-thumnail">
							<img src="/baseball${ player.imagePath }" alt="선수사진" id="player-photo"><br> 
						</div>
						<div class="player-info">
							<h3>
								<em>No : ${ player.backNo }</em><br>
								<span>선수명 : ${ player.userName }</span>
							</h3>
							<table class="profile">
								<tbody>
									<tr>
										<th scope="row">소속팀</th>
										<td>${ player.playerTeam }</td>
									</tr>
									<tr>
										<th scope="row">포지션</th>
										<td>${ player.playerPosition }</td>
									</tr>
									<tr>
										<th scope="row">입단일</th>
										<td>${ player.playerDate }</td>
									</tr>
									<tr>
										<th scope="row">연봉</th>
										<td>${ player.playerSalary }</td>
									</tr>
									<tr>
										<th scope="row">자기소개</th>
										<td>${ player.playerIntro }</td>
									</tr>
								</tbody>
							</table>
							
							<a href="/baseball/updatePlayerform?playerNo=${ player.playerNo }">선수정보 수정</a>
						</div>
					</div>
				</c:when>
				<c:otherwise>"아직 승인되지 않았습니다."</c:otherwise>
			</c:choose>	
		</c:when>
		<c:otherwise>
			선수 등록을 해주세요
		</c:otherwise>
	</c:choose>
		
	
</body>
</html>