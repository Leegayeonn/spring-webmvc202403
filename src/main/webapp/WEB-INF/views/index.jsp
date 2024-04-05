<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Insert Your Title</title>
    <%@ include file="./include/static-head.jsp" %>

    <style>

        .logo {
            color: white;
            
        }
       
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

    <!-- header -->
<header>
    <div class="inner-header">
        <h1 class="logo">
            <a href="/">
                <img src="/assets/img/logo.png" alt="로고이미지">
                꾸러기
            </a>
            
        </h1>

        <!-- 프로필 사진 -->
        <div class="profile-box">
                <img src="/assets/img/anonymous.jpg" alt="프사">
        </div>


        <h2 class="intro-text">
            Welcome ${sessionScope.login == null ? '로그인을 해주세요' : login.name} 
            ${sessionScope.login == null ? '' : '님 안녕하세요!'}
        </h2>
        <a href="#" class="menu-open">
            <span class="menu-txt">MENU</span>
            <span class="lnr lnr-menu"></span>
        </a>
    </div>

    <nav class="gnb">
        <a href="#" class="close">
            <span class="lnr lnr-cross"></span>
        </a>
        <ul>
            <li><a href="/">Home</a></li>
            <li><a href="#">About</a></li>
            <li><a href="/board/list">Board</a></li>
            <li><a href="#">Contact</a></li>

            <c:if test="${login == null}">
                <li><a href="/members/sign-up">Sign Up</a></li>
                <li><a href="/members/sign-in">Sign In</a></li>
            </c:if>

            <c:if test="${sessionScope.login != null}">
                <li><a href="#">My Page</a></li>
                <li><a href="/members/sign-out">Sign Out</a></li>
            </c:if>

        </ul>
    </nav>

</header>
<!-- //header -->

    

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