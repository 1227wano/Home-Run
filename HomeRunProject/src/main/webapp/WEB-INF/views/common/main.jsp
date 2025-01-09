<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
    <title>웰컴</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

    <style>
      body{
        height : 1200px;
      }

      header{
        height : 7%;
      }
      main{
        height : 78%;
      }
      footer{
        height : 15%
      }
      
      .d-block{
        height : 450px;
      }
      
      .bottom11{
          float : left;
          width: 50%;
          height: 40%;
          text-align: center;
          margin-left: 0%;
      }
      #bottom1{
          margin-top: 0%;
          margin-right: 0%;
          font-size: 24px;
          font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
      }
      #bottom2>button{
          color: azure;
          width : 80%;
          height : 80%;
          font-size : 35px;
          margin-top: 5%;
          border-radius: 25%;
          background-color: rgb(125, 184, 226);
      }
      #bottom2>button:hover{
          color: rgb(61, 61, 227);
          background-color: white;
          border-radius: 10px;
          box-shadow: 5px 5px 10px 5px rgb(46, 14, 84);
          transition: all 0.4s;
          font-size : 40px;

      }
      #gameCalendar{
        height: 400px;
        width: 400px;
      }
      
      #footer1, #footer2{
          width: 100%;
          height: 30px;
      }
      #footer1{
          background-color: rgb(224, 224, 242);
      }
      #footer2{
          height: 50%;
      }
      #footer1 > a{
          text-decoration: none;
          color: rgb(70, 67, 67);
          font-weight: 900;
          margin: 15px;
          vertical-align: middle;
      }
      #footer1>a:hover{
          color: rgb(74, 133, 209);
      }
      #footer2 > p {
          width: 100%;
          margin: 0;
          color: #afafaf;
          font-size: 15px;
          font-weight: lighter;
      }
      #p_1{
          height: 70%;
          padding-left: 15px;
          padding-top: 15px;
      }
      #p_2{
          height: 30%;
          text-align: center;
          font-size: 30px;
      }

    </style>

  </head>
  <body class="p-3 m-0 border-0 bd-example m-0 border-0">
    <main>
    <div id="carouselExampleAutoplaying" class="carousel slide" data-bs-ride="carousel">
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img src="resources/image/main.jpg" class="d-block w-100" alt="...">
        </div>
        <div class="carousel-item">
          <img src="https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2F20160805_107%2Fkang066_1470397129348TDWtX_JPEG%2F_EN_3671_Panorama_Crop_S-5000.jpg&type=l340_165" class="d-block w-100" alt="야구">
        </div>
        <div class="carousel-item">
          <img src="resources/image/main2.jpg" class="d-block w-100" alt="...">
        </div>
      </div>
      <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>
    </div>

      <div id="bottom1" class="bottom11"><h4>지난 시즌 경기일정</h4>
        <img src="resources/image/경기일정.png" id="gameCalendar" alt="...">
      </div>
      <div id="bottom2" class="bottom11">
        <button>야구 굿즈 판매 <br> -GRAND OPEN-<br> 바로 이동하기 <br> </button>
      </div>
    </main>
    <footer id="footer">
      <div id="footer1">
          <a href="#">이용약관</a>
          <a href="#">개인정보 처리방침</a>
          <a href="#">인재채용 문의</a>
      </div>
      <div id="footer2">
          <p id="p_1">
              본관 : 서울특별시 중구가 시키드나 2-23-454 <br>
              별관 : 서울특별시 동작구 만움직이면 쏜다 343-4499<br>
          </p>
          <p id="p_2">
              Copyright © 2024 JongNo B-Class All Right Reserved
          </p>
      </div>
    </footer>
  </body>
</html>