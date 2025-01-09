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
		#team-photo {
			width : 500px;
			height: 500px;
		}
		#team-detail {
			float:left;
		}
	</style>
</head>
<body>
<jsp:include page="../common/menubar.jsp" />
	<main id="main">
		<div id="team-detail">	
			<div class="team-thumnail">
				<img src="/baseball${ team.changeName }" alt="팀사진" id="team-photo"><br> 
			</div>
			<div class="team-info">
				<h3>
					<em>${ team.teamGrade }팀</em><br>
					<span>${ team.teamName }</span>
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
			</div>
		</div>
	</main>
</body>
</html>