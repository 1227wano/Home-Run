<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="utf-8">

  <title>baseball</title>
  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

  <!-- Custom fonts for this template -->
  <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
  <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
  <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
  <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>

  <!-- Custom styles for this template -->
  <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
  
  <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
  <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>

<style>

    #mainNav .navbar-nav .nav-item .nav-link {
        font-weight: 600;
       
    }
    
    #search {
		float: left;
		text-align: center;
		margin-bottom: 2%;
	}
    
    <!-- 선수 정보 내브바 -->
	#PnT{
        height : 50px;
        margin : 0%;
    }
    #playerAndTeam{
    	margin-top : 0px;
    	height : 50px;
    }
	#navbar > li > ul {
        list-style : none;
        padding-inline: 0;
        display: none;
    }
    #navbar > li > a:hover + ul {
        display: block;
    }

    #navbar > li > ul:hover{
        display: block;
    }
 
</style>


</head>

<body id="page-top">

		<c:if test="${ not empty alertMsg }">
		<script>
			alert('${ alertMsg }');
		</script>
		<c:remove var="alertMsg" scope="session" />
		</c:if>
		
  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
    <div class="container">
      <a class="navbar-brand" href="/baseball">
      	<img class="img-fluid" src="/baseball/resources/upload_files/logo.png" alt="임시로고" style="width:50px; height:50px;" />
      </a>
      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive">
        메뉴
        <i class="fas fa-bars"></i>
      </button>
      
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav text-uppercase ml-auto" id="navbar">
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="/baseball/dom">구장</a>
          </li>
          
          <li class="nav-item" id="PnT">
            <a class="nav-link js-scroll-trigger" href="#">선수 및 팀 정보</a>
	            <ul id="playerAndTeam">
					<li><a href="/baseball/savePlayerform">선수 등록</a></li>
					<li><a href="/baseball/findAllPlayer">선수 조회</a></li>
					<li><a href="/baseball/insertTeamform">팀 등록</a></li>
					<li><a href="/baseball/selectAllTeam">팀 조회</a></li>
				</ul>
          </li>

          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="/baseball/goods">굿즈</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="/baseball/freeBoard">자유게시판</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="/baseball/small">소모임</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="/baseball/foodTruckBoards">푸드트럭</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="/baseball/notices">공지사항</a>
          </li>
          <li class="nav-item">
          	<a class="nav-link js-scroll-trigger" href="/baseball/question">Q&A</a>
          </li>
          
          
          <c:choose>
          	<c:when test="${ empty sessionScope.loginUser }">
          	  <li class="nav-item">
	          <a class="nav-link js-scroll-trigger" href="/baseball/enrollform.me">회원가입</a>
	          </li>
	          <li class="nav-item">
	          <a class="nav-link js-scroll-trigger" data-toggle="modal" data-target="#log-in">로그인</a>
	          </li>
         	</c:when> 
         

          	<c:otherwise>
          	  <label>${ sessionScope.loginUser.userName }님 환영합니다</label> &nbsp;&nbsp;
	          <li class="nav-item">
	          <a class="nav-link js-scroll-trigger" href="/baseball/mypage.me">마이페이지</a>
	          </li>
	          <li class="nav-item">
	          <a class="nav-link js-scroll-trigger" href="/baseball/logout.me" >로그아웃</a>
	          </li>
          	</c:otherwise>
          </c:choose>
          
        </ul>
      </div>
    </div>
  </nav><br><br><br>
  
  

  <!-- 로그인 Modal-->
<div class="modal fade" id="log-in">
	<div class="modal-dialog">
		<div class="modal-content">

			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">
					<span style="color: #346c9a;">BaseBall</span> 로그인
				</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>

			<!-- Modal body -->
			<div class="modal-body">

				<form action="/baseball/login.me" name="sign-in" method="post" id="signInForm"
					style="margin-bottom: 0;">
					<table style="cellpadding: 0; cellspacing: 0; margin: 0 auto; width: 100%">
						<tr>
							<td style="text-align: left">
								<p><strong>아이디</strong>&nbsp;&nbsp;&nbsp;<span id="idCheck"></span></p>
							</td>
						</tr>
						<tr>
							<td><input type="text" name="userId" id="signInId"
								class="form-control tooltipstered" maxlength="10"
								required="required" aria-required="true"
								style="margin-bottom: 25px; width: 100%; height: 40px; border: 1px solid #d9d9de"
								placeholder="아이디를 입력해주세요."></td>
						</tr>
						<tr>
							<td style="text-align: left">
								<p><strong>비밀번호</strong>&nbsp;&nbsp;&nbsp;<span id="pwCheck"></span></p>
							</td>
						</tr>
						<tr>
							<td><input type="password" size="17" maxlength="20" id="signInPw"
								name="userPwd" class="form-control tooltipstered" 
								maxlength="20" required="required" aria-required="true"
								style="ime-mode: inactive; margin-bottom: 25px; height: 40px; border: 1px solid #d9d9de"
								placeholder="비밀번호를 입력해주세요."></td>
						</tr>

						<tr>
							<td style="width: 100%; text-align: center; colspan: 2;"><input
								type="submit" value="로그인" class="btn form-control tooltipstered" id="signIn-btn"
								style="background-color: #3c698e; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div id="search">
				
					<a href="searchId" style="text-decoration : none; color: rgb(68, 68, 68); font-size : 15px; margin: 5px; "> 아이디 찾기 </a> |
			
					<a href="searchPwd" style="text-decoration : none; color: rgb(68, 68, 68); font-size : 15px; margin: 5px;"> 비밀번호 찾기</a>
				
			</div>
		
		</div>
	</div>
</div>


</body>
</html>