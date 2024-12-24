<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <style>


    </style>

</head>
<body>
    <!-- 메뉴바 -->
    <jsp:include page="../common/menubar.jsp" />

    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>회원가입</h2>
            <br>

            <form action="" method="post">
                <div class="form-group">
                    <label for="userId"> 아이디 </label>
                    <input type="text" class="form-control" id="userId" placeholder="아이디를 입력해주세요" name="userId" required> 
                    <button type="submit" class="btn btn-primary">중복확인</button> <br>

                    <label for="userPwd"> 비밀번호 </label>
                    <input type="password" class="form-control" id="userPwd" placeholder="비밀번호를 입력해주세요" name="userPwd" required> <br>

                    <label for="checkPwd"> 비밀번호 재확인 </label>
                    <input type="password" class="form-control" id="checkPwd" placeholder="비밀번호 재확인" required> <br>

                    <label for="userName"> 이름 </label>
                    <input type="text" class="form-control" id="userName" placeholder="이름을 입력해주세요" name="userName" required> <br>

                    <label for="nickName"> 닉네임 </label>
                    <input type="text" class="form-control" id="email" placeholder="닉네임을 입력해주세요" name="nickName"> 
                    <button type="submit" class="btn btn-primary">중복확인</button> <br>

                    <label for="phone"> 전화번호 </label>
                    <input type="number" class="form-control" id="age" placeholder="전화번호를 입력해주세요" name="phone"> <br>

                    <label for="email"> 이메일 </label>
                    <input type="tel" class="form-control" id="phone" placeholder="이메일을 입력해주세요" name="email"> <br>
                    
                    <label for="address"> 주소 </label>
                    <input type="text" class="form-control" id="address" placeholder="주소를 입력해주세요" name="address"> <br>
                    
                </div> 
                <br> 
                <div class="btns" align="center">
                    <button type="submit" class="btn btn-primary" disabled>회원가입</button>
                </div>
            </form>
        </div>
        <br><br>

    </div>

    <!-- 푸터바 -->
    <jsp:include page="" />

</body>
</html>