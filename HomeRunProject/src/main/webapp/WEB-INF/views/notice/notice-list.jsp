<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>공지사항</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #F9F9F9;
      color: #333;
    }
    .content {
      width: 80%;
      margin: 50px auto;
      background-color: white;
      padding: 20px;
      box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
      border-radius: 5px;
    }
    h2 {
      text-align: center;
      margin-bottom: 20px;
    }
    #searchForm {
      display: flex;
      justify-content: center;
      margin-bottom: 20px;
    }
    #searchForm select,
    #searchForm input {
      padding: 8px;
      font-size: 14px;
      border: 1px solid #ccc;
      border-radius: 4px;
    }
    #searchForm select {
      margin-right: 10px;
      width: 100px;
    }
    #searchForm input {
      flex: 1;
      margin-right: 10px;
    }
    #searchForm button {
      padding: 8px 20px;
      background-color: black;
      color: white;
      border: none;
      border-radius: 4px;
      cursor: pointer;
    }
    #searchForm button:hover {
      background-color: #444;
    }
    table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 20px;
    }
    table th {
      text-align: center;
      background-color: #F4F4F4;
      padding: 10px;
      font-weight: bold;
      border: 1px solid #ddd;
    }
    table td {
      padding: 10px;
      border: 1px solid #ddd;
      text-align: left;
    }
    th:nth-child(1), td:nth-child(1) {
      width: 10%;
      text-align: center;
    }
    th:nth-child(2), td:nth-child(2) {
      width: 70%;
    }
    th:nth-child(3), td:nth-child(3) {
      width: 20%;
      text-align: center;
    }
  </style>
</head>
<body>

	<jsp:include page="../common/menubar.jsp" />
	
	  <div class="content">
	  	<br><br>
	    <h2>공지사항</h2>
	    <br>
	    <form id="searchForm" action="/searchNotice" method="get">
	      <select name="condition">
	        <option value="title">제목</option>
	        <option value="content">내용</option>
	      </select>
	      <input type="text" name="keyword" placeholder="검색어를 입력하세요">
	      <button type="submit">검색</button>
	    </form>
	     <h2>공지사항 목록</h2>
        <table>
            <thead>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성일</th>
                    <th>첨부파일</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="notice" items="${noticeList}">
                    <tr>
                        <td>${notice.noticeNo}</td>
                        <td>${notice.title}</td>
                        <td>${notice.creationDate}</td>
                        <td>
                            <c:if test="${not empty notice.attachment}">
                                <a href="${notice.attachment}" download>💌</a>
                            </c:if>
                            <c:if test="${empty notice.attachment}">
                                없음
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
	  </div>
	  
	  
	  
</body>
</html>