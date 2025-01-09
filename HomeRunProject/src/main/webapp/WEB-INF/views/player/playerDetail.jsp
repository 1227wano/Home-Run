<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>선수 상세보기</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<style>
		#player-photo {
			width : 500px;
			height: 500px;
		}
		#player-detail {
			float:left;
		}
	</style>
</head>
<body>
<jsp:include page="../common/menubar.jsp" />
	<!-- 선수 상세 조회 : 선택한 선수의 a태그로 이동해온다. 선수번호와 함께. -->

	<main id="main">
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
			</div>
		</div>
	</main>
</body>
</html>