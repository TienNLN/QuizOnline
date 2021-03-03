<%-- 
    Document   : quiz-page
    Created on : Feb 18, 2021, 1:59:58 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://kit.fontawesome.com/ff02cdde28.js" crossorigin="anonymous"></script>
        <title>Quiz Online</title>
    </head>
    <body>
        <c:set var="answereds" value="${sessionScope.QUESTION_ANSWER}" />
        <c:set var="amount" value="${sessionScope.AMOUNT}" />
        <c:set var="question" value="${requestScope.QUESTION}" />
        <c:set var="answers" value="${requestScope.ANSWER}" />
        <c:set var="subject" value="${sessionScope.SUBJECT}" />
        <c:set var="timeend" value="${sessionScope.TIME_END}" />
        <c:set var="qnumber" value="${requestScope.Q_NUMBER}" />
        <c:set var="ansnumber" value="${1}" />
        
        <c:set var="lastUser" value="${sessionScope.LAST_USER}"/>
        <div class="headinformation">
            <h1>Quiz Page</h1>
            <div class="welcome-and-login">
                <a href="logOut"><i class="fa fa-sign-out"> Welcome, ${lastUser.name} </i></a>
            </div>
        </div>
        <div class="container">
            <form action="handleQuestion">
                <div id="countdown">
                    <div class="Time">
                        <h4>Time left</h4>
                        <ul>
                            <li><span id="minutes"></span>:</li>
                            <li><span id="seconds"></span></li>
                        </ul>
                    </div>
                    <h1>Online quiz of ${subject}</h1>
                    <h3>Question ${qnumber}: ${question.question}</h3>
                    <c:forEach var="answer" items="${answers}" >
                        <div>  <input type="radio" name="Answer" value="${answer.choice}" 
                                      <c:forEach var="answered" items="${answereds}" >
                                          <c:if test="${answered.key == question and answered.value == answer.choice}" > checked="checked" </c:if>
                                      </c:forEach> /> <p>${ansnumber}. ${answer.choice}</p> </div>
                            <c:set var="ansnumber" value="${ansnumber + 1}" />
                        <input type="hidden" name="QuestionNumber" value="${qnumber}" />
                    </c:forEach>
                    <c:if test="${qnumber == 1}" >
                        <input name="btAction" class="Previoust" style="cursor: not-allowed;  opacity: 0.6;" type="submit" value="Previoust Question" disabled/> <input name="btAction" class="Next" type="submit" value="Next Question" /> </br>
                    </c:if>
                    <c:if test="${qnumber == amount}" >
                        <input name="btAction" class="Previoust" type="submit" value="Previoust Question" /> <input name="btAction" class="Next" type="submit" value="Finished" /> </br>
                    </c:if>
                    <c:if test="${qnumber != 1 and qnumber != amount}">
                        <input name="btAction" class="Previoust" type="submit" value="Previoust Question" /> <input name="btAction" class="Next" type="submit" value="Next Question" /> </br>
                    </c:if>
                </div>
                <div class="message">
                    <div id="content">
                        <h1>Time out!!!!</h1>
                        <input name="btAction" class="Timeout" type="submit" value="Time out" />
                    </div>
                </div>
            </form>
        </div>
    </body>
    <style>
        .container
        {
            font-size: 120%;
            color: black;
            border-style: double;
            border-color: #008CBA;
            margin: auto;
        }
        .container h1
        {
            text-align: center;
            color: #008CBA;
        }
        .container h3 {
            margin-left: 15px;
            text-align: left;
            color: tomato;
        }
        .container div
        {
            margin: 20px
        }
        .container div p {
            word-break: break-all;
            width: 95%;
            margin-left: 5px;
            display: inline-block
        }
        .container div input[type=radio] {
            vertical-align: top;
            margin-top: 17px;
            width: 2em;
            height: 2em
        }
        .Previoust {
            border: none;
            border-radius: 12px;
            margin: 20px;
            display: inline-block;
            background-color: #008CBA;
            width: 25%;
            height: 50px;
            color: white;
            transition-duration: 0.4s;
            font-size: 20px;
        }
        .Previoust:hover {
            cursor: pointer;
            border: 2px solid #008CBA;
            background-color: white;
            color: black
        }
        .Next {
            border: none;
            border-radius: 12px;
            display: inline-block;
            background-color: #008CBA;
            width: 25%;
            height: 50px;
            color: white;
            margin: 20px;
            transition-duration: 0.4s;
            font-size: 20px;
            bottom: 20px;
            right: 20px;
            float: right;
        }
        .Next:hover {
            cursor: pointer;
            border: 2px solid #008CBA;
            background-color: white;
            color: black
        }
        .headinformation {
            background-color: #008CBA;
            position: sticky;
            top: 0;
        }
        .headinformation h1 {
            margin-left: 15px;
            color: white;
            display: inline-block;
        }
        .welcome-and-login {
            display: inline-block;
            position: absolute;
            bottom: 27px;
            right: 15px;
            font-size: 20px;
        }
        .welcome-and-login i {
            color: white;
        }
        .welcome-and-login i:hover {
            color: #a6a6aa;
        }
        .Time {
            display: inline;
            color: tomato;
            margin: 0;
        }
        .Time h4 {
            display: inline;
        }
        ul {
            display: inline;
            margin-left: 0;
            padding-left: 0;
            color: tomato;
        }
        li {
            display: inline-block;
            font-size: 20px;
            list-style-type: none;
            text-transform: uppercase;
        }
        li span {
            margin-right: 10px;
            font-size: 20px;
        }
        .Timeout {
            border: none;
            border-radius: 12px;
            display: inline-block;
            background-color: #008CBA;
            width: 50%;
            height: 50px;
            color: white;
            margin-top: 20px;
            margin-bottom: 20px;
            margin-left: 25%;
            margin-right: 25%;
            transition-duration: 0.4s;
            font-size: 20px;
            bottom: 20px;
            right: 20px;
        }
        .Timeout:hover {
            cursor: pointer;
            border: 2px solid #008CBA;
            background-color: white;
            color: black
        }
        .message h1 {

        }
    </style>
    <script>
        (function () {
            content.style.display = "none";
            const second = 1000,
                    minute = second * 60,
                    hour = minute * 60,
                    day = hour * 24;
            var today = new Date();
            let end = "${timeend}",
                    countDown = new Date(end).getTime(),
                    x = setInterval(function () {

                        let now = new Date().getTime(),
                                distance = countDown - now;

                        document.getElementById("minutes").innerText = Math.floor(distance / (minute)),
                                document.getElementById("seconds").innerText = Math.floor((distance % (minute)) / second);

                        //do something later when date is reached
                        if (distance < 0) {
                            let countdown = document.getElementById("countdown"),
                                    content = document.getElementById("content");
                            countdown.style.display = "none";
                            content.style.display = "";

                            clearInterval(x);
                        }
                        //seconds
                    }, 0);
        }());
    </script>
</html>
