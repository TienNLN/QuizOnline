<%-- 
    Document   : done-page
    Created on : Feb 21, 2021, 7:56:06 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://kit.fontawesome.com/ff02cdde28.js" crossorigin="anonymous"></script>
        <title>Quiz Online</title>
    </head>
    <body>
        <c:set var="lastUser" value="${sessionScope.LAST_USER}"/>
        
        <c:set var="subjectName" value="${requestScope.SUBJECT_NAME}"/>
        <c:set var="dateTakeQuiz" value="${requestScope.DATE_TAKE_QUIZ}"/>
        <c:set var="timeTakeQuiz" value="${requestScope.TIME_TAKE_QUIZ}"/>
        <c:set var="point" value="${requestScope.POINT}"/>
        <c:set var="correctAnswer" value="${requestScope.CORRECT_ANSWER}"/>
        <c:set var="totalQuestion" value="${requestScope.TOTAL_QUESTION}"/>
        
        <div class="headinformation">
            <h1>Result Page</h1>
            <div class="welcome-and-login">
                <a href="#"><i class="fa fa-sign-out"> Welcome, ${lastUser.name} </i></a>
            </div>
        </div>
        <div class="container">
            <form action="#">
                <h1>Your Quiz is Done</h1>
                <table class="DesignTable">
                    <thead>
                        <tr>
                            <th>Subject Name</th>
                            <th>Date Take</th>
                            <th>Time Take</th>
                            <th>Point</th>
                            <th>Correct Answers</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                ${subjectName}
                            </td>
                            <td>
                                ${dateTakeQuiz}
                            </td>
                            <td>
                                ${timeTakeQuiz}
                            </td>
                            <td>
                                ${point}
                            </td>
                            <td>
                                ${correctAnswer}/${totalQuestion}
                            </td>
                        </tr>
                    </tbody>
                </table>
                <a class="btn btn-primary" href="startUp" role="button">Back to Home Page</a>
            </form>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js" integrity="sha512-bLT0Qm9VnAYZDflyKcBaQ2gg0hSYNQrJ8RilYldYQ1FxQYoCLtUjuuRuZo+fjqhx/qtq/1itJ0C2ejDxltZVFg==" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
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
        table {
            border-collapse: collapse;
            width: 90%;
            margin: 5%;
        }

        th, td {
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even){background-color: #f2f2f2}

        th {
            background-color: #008CBA;
            color: white;
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
    </style>
</html>
