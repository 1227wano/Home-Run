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
      
      .content>div{
            box-sizing: border-box;
            float: left;
        }

    

        .list{ 
            width: 15%;
            height: 100%;
            list-style: none;
            background-color: rgb(56, 76, 148);
            text-align: center;

        }

        .list>li>a{
            color: white;
            font-size: 20px;
            text-decoration: none;
           
        }

        .memberpage{
            width: 85%;
            height: 100%;
            text-align: center;
        }

       
    </style>
</head>
<body>
    
    <jsp:include page="../common/menubar.jsp" />

    

    <div class="content">

        <div class="list" >
            <li><a href="mypage.me">  ⚾개인 정보</a></li>
            <li><a href="#">  ⚾선수 등록</a></li>
            <li><a href="#">  ⚾야구교실</a></li>
            <li><a href="#">  ⚾소모임</a></li>
            <li><a href="#">  ⚾푸드트럭</a></li>
            <li><a href="#">  ⚾굿즈등록</a></li>
            <li><a href="#">  ⚾예매정보</a></li>

        </div>

        <div class="memberpage">
            <h2>마이페이지</h2>
            <br>

            <form action="update.me" method="post" style="width: 50%; margin: auto;">
                <div class="form-group">
                    <label for="userId"> 아이디 </label>
                    <input type="text" class="form-control" id="userId" value="${ sessionScope.loginUser.userId }" name="userId" readonly> <br>

                    <label for="userName"> 이름  </label>
                    <input type="text" class="form-control" id="userName" value="${ sessionScope.loginUser.userName }" name="userName" required> <br>

                    <label for="nickName"> &nbsp; 닉네임 </label>
                    <input type="text" class="form-control" id="nickName" value="${ sessionScope.loginUser.nickName }" name="nickName"> <br>

                    <label for="phone"> &nbsp; 전화번호 </label>
                    <input type="tel" class="form-control" id="phone" value="${ sessionScope.loginUser.phone }" name="phone"> <br>

                    <label for="email"> &nbsp; 이메일 </label>
                    <input type="text" class="form-control" id="email" value="${ sessionScope.loginUser.email }" name="email"> <br>
                    
                    <label for="address"> &nbsp; 주소 </label>
                    <input type="text" class="form-control" id="address" value="${ sessionScope.loginUser.address }" name="address"> <br>
                    
                </div> 
                <br>
                <div class="btns" align="center">
                    <button type="submit" class="btn btn-primary">수정하기</button>
                    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteForm">회원탈퇴</button>
                </div>
            </form>
            <br><br>

        </div>


    </div>

        
        <br><br>
        
    </div>

    <!-- 회원탈퇴 버튼 클릭 시 보여질 Modal -->
    <div class="modal fade" id="deleteForm">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">회원탈퇴</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <form action="delete.me" method="post">
                    <!-- Modal body -->
                    <div class="modal-body">
                        <div align="center">
                            탈퇴 후 복구가 불가능합니다. <br>
                            정말로 탈퇴 하시겠습니까? <br>
                        </div>
                        <br>
                            <label for="userPwd" class="mr-sm-2">Password : </label>
                            <input type="text" class="form-control mb-2 mr-sm-2" placeholder="Enter Password" id="userPwd" name="userPwd"> <br>
                    </div>
                    <!-- Modal footer -->
                    <div class="modal-footer" align="center">
                        <button type="submit" class="btn btn-danger">탈퇴하기</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!--<jsp:include page="" />-->

</body>
</html>