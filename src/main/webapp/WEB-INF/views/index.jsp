<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Insert Your Title</title>
    <%@ include file="./include/static-head.jsp" %>

    <style>

       
        #welcome {

            width: 400px;
            height: 50px;
            margin-top: 120px;
            font-size: 40px;
            color: white;
            background-color: yellowgreen;
            text-align: center;

        }


    </style>
</head>
<body>

    <%@ include file="./include/header.jsp" %>

    <%
        String userName = "방문자";

        // 클라이언트에게 쿠키를 검사
        Cookie[] cookies = request.getCookies();
        for(Cookie c : cookies) {
            if (c.getName().equals("login")) {
                userName = c.getValue();
            }
        }

    %>

    <h1 id="welcome"><%= userName %> 님 안녕하세요!</h1>



</body>
</html>