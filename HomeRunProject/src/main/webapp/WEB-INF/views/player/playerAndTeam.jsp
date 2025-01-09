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
	
	<!-- 선수 -->	
	<h3>${ player.userName }님의 선수정보</h2>
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
	<br><br>
	
	<!-- 팀정보 -->
	<c:choose>
		<c:when test="${ not empty team.teamNo }">
			<h3>${ player.userName }님이 창설한 팀</h3>
			<div id="team-detail">	
				<div class="team-thumnail">
					<img src="/baseball${ team.changeName }" alt="팀사진" id="team-photo"><br>
				</div>
				<div class="team-info">
					<h3>
						<em>${ team.teamName }</em><br>
						<span>${ team.teamGrade }팀</span>
					</h3>
					<table class="profile">
						<tbody>
							<tr>
								<th scope="row">창단일</th>
								<td>${ team.teamDate }</td>
							</tr>
							<tr>
								<th scope="row">팀 소개글</th>
								<td>${ team.teamIntro }</td>
							</tr>
						</tbody>
					</table>
				<!-- <a href="/baseball/updateTeamform?teamNo=${ team.teamNo }">팀정보 수정</a> -->
				
				</div>
			</div>
		</c:when>
		<c:otherwise>팀 등록 또는 팀 소속 신청을 해주세요.
			<a href="/baseball/joinTeamform?playerNo=${ player.playerNo }">팀 소속 신청</a>
		</c:otherwise>
	</c:choose>
	<br><br><br>
</body>
</html>