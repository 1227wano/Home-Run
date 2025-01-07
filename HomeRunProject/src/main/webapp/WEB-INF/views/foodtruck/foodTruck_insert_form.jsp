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
            <h2 style="text-align: center;">푸드트럭 등록</h2>
            <br>
            
            
            <form action="#" method="post"> 
                <div class="form-group" id="enroll_form" style="width: 50%; margin: auto;">
                    <label for="userId"> 사용자 아이디</label>
                    <input type="text" class="form-control" id="userId" placeholder="아이디를 입력해주세요" name="userId" required> <br>
                    
                    
                    <label for="userPwd"> 구장 </label>
                    <select name = "dom">
                        <option>잠실야구장</option>
                        <option>인천야구장</option>
                        <option>부산야구장</option>
                    </select> <br>
                   
                    <label for="foodTruckName"> 푸드트럭 이름 </label>
                    <input type="text" class="form-control" id="userName" placeholder="푸드트럭 상호명을 입력해주세요." name="foodTruckName" required> 
                    <button type="button" onclick="#">중복확인</button> <br><br>

                    <label for="startTime"> 푸드트럭 영업 시작 시간 </label>
                    <input type="time" class="form-control" id="startTime" name="startTime"><br>
                    
                    <label for="endTime"> 푸드트럭 영업 끝나는 시간</label>
                    <input type="time" class="form-control" id="endTime" name="endTime"> <br>

                    <label for="openDay"> 푸드트럭 영업 요일 </label> <br>
                    
                    <div style="display: inline-block;" class="checkbox-inline" >
                    <input type="checkbox" class="form-control" id="monday" name="openDay"><label for="월요일">월</label> 
                    </div>
                    <div style="display: inline-block;" class="checkbox-inline" >
                    <input type="checkbox" class="form-control" id="tuesday" name="openDay"><label for="화요일">화</label> 
                    </div>
                    <div style="display: inline-block;" class="checkbox-inline" >
                    <input type="checkbox" class="form-control" id="wednesday" name="openDay"><label for="수요일">수</label> 
                    </div>
                    <div style="display: inline-block;" class="checkbox-inline" >
                    <input type="checkbox" class="form-control" id="thursday" name="openDay"><label for="목요일">목</label> 
                    </div>
                    <div style="display: inline-block;" class="checkbox-inline" >
                    <input type="checkbox" class="form-control" id="friday" name="openDay"><label for="금요일">금</label> 
                    </div>
                    <div style="display: inline-block;" class="checkbox-inline" >
                    <input type="checkbox" class="form-control" id="saturday" name="openDay"><label for="토요일">토</label> 
                    </div>
                    <div style="display: inline-block;" class="checkbox-inline" >
                    <input type="checkbox" class="form-control" id="sunday" name="openDay"><label for="일요일">일</label> <br>
                    </div>
                    
                    <br>

                    <label for="startDate"> 푸드트럭 시작 날짜 </label>
                    <input type="date" class="form-control" id="startDate" name="startDate"> <br>
                    
                    <label for="endDate"> 푸드트럭 끝나는 날짜 </label>
                    <input type="date" class="form-control" id="endDate"  name="endDate"> <br>
                    
                    <label for="openDay"> 푸드트럭 소개 </label><br>
                    <textarea id="content" class="form-control" rows="10" cols="20" style="resize:none;" name="" required>여긴내용~</textarea><br>
                    
                    <label for="upfile">첨부파일</label><br>
                    <input type="file" id="upfile1" class="form-control-file border" name=""> <br>
                    <input type="file" id="upfile2" class="form-control-file border" name=""> <br>
                    <input type="file" id="upfile3" class="form-control-file border" name=""> <br>
                    
                    
                </div> 
                <br> 
                <div class="btns" align="center">
                    <button type="submit" class="btn btn-primary disabled" id="join-btn" >푸드트럭 신청</button>
                </div>
            </form>
        </div>
        <br><br>

    </div>


</body>
</html>