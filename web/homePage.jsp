<%-- 
    Document   : home-page
    Created on : Feb 23, 2021, 3:34:33 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="https://getbootstrap.com/docs/4.0/assets/img/favicons/favicon.ico">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
        <link type="text/css" rel="stylesheet" href="C:/Users/ADMIN/Desktop/SE5/LAB231/Cover Template for Bootstrap_files/cover.css">

        <title>Quiz Online</title>

        <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/cover/">

        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link rel="stylesheet" type="text/css" href="css/cover.css" />
        <title>Quiz Online</title>
    </head>
    <body>

        <style>
            .view-quiz-history-field{
                border-left: 2px solid black;
                height: 150px;
            }
        </style>

        <c:set var="subjects" value="${requestScope.SUBJECT}" />
        <c:set var="lastUser" value="${sessionScope.LAST_USER}"/>

        <div class="cover-container d-flex h-100 p-3 mx-auto flex-column">
            <header class="masthead mb-auto">
                <div class="inner">
                    <h3 class="masthead-brand">Home Page</h3>
                    <nav class="nav nav-masthead justify-content-center">
                        <a class="nav-link">Welcome, ${lastUser.name}</a>
                        <a class="nav-link" href="logOut">Logout</a>
                    </nav>
                </div>
            </header>

            <main role="main" class="form-group">
                <form action="startAQuiz">
                    <div class="container">
                        <div class="row border border-white rounded bg-light text-dark">
                            <div class="col-md-2" style="margin: 50px">
                                <c:forEach var="subject" items="${subjects}" >
                                    <input class="form-check-input" type="radio" id="${subject.subjectID}" name="SubjectID" value="${subject.subjectID}">
                                    <label class="form-check-label" for="${subject.subjectID}">
                                        ${subject.subjectName}
                                    </label><br/>
                                </c:forEach>
                            </div>
                            <div class="col-md-4" style="margin-bottom: 50px; margin-top: 50px">
                                <input class="btn btn-primary" type="submit" value="Start A Quiz">
                            </div>
                            <div class="col-md-4 view-quiz-history-field">
                                <a class="btn btn-primary" href="viewQuizHistory" role="button" style="margin-bottom: 50px; margin-top: 50px; margin-left: 120px" >View Quiz History</a>
                            </div>
                        </div>
                    </div>
                </form>
            </main>
<!--            <main role="main" class="form-group">
                <div class="row">
                    <div class="col-md-4">

                    </div>
                    <div class="col-md-4">
                        <a class="btn btn-primary" href="viewQuizHistory" role="button">View Quiz History</a>
                    </div>
                    <div class="col-md-4">

                    </div>
                </div>
            </main>-->
            <footer class="mastfoot mt-auto">
                <div class="inner">
                    <p>Cover template for <a href="https://getbootstrap.com/">Bootstrap</a>, by <a href="https://twitter.com/mdo">@mdo</a>.</p>
                </div>
            </footer>
        </div>

        <!--        <div class="headinformation" >
                    <h1>Home Page</h1>
                    <div class="welcome-and-login">
                        <a href="#"><i class="fa fa-sign-out"> Welcome, ${lastUser.name} </i></a>
                    </div>
                </div>
                <div class="container">
                    <h1>Welcome to quiz online</h1>
                    <h3>Please choose one subject that you want to take quiz</h3>
                    <form class="boxed" action="startAQuiz">
        <c:forEach var="subject" items="${subjects}" >
            <input type="radio" id="${subject.subjectID}" name="SubjectID" value="${subject.subjectID}">
            <label for="${subject.subjectID}">${subject.subjectName}</label>
        </c:forEach>
        <input class="Timeout" type="submit" value="Star A Quiz">
        </form>
        </div>
        
        <main class="form-control">
        <a class="btn btn-primary" href="" role="button">View History</a>
        </main>-->

        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js" integrity="sha512-bLT0Qm9VnAYZDflyKcBaQ2gg0hSYNQrJ8RilYldYQ1FxQYoCLtUjuuRuZo+fjqhx/qtq/1itJ0C2ejDxltZVFg==" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
    <!--    <style>
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
            .container
            {
                font-size: 120%;
                color: black;
                border-style: double;
                border-color: #008CBA;
                margin: auto;
            }
            .container h1, h3
            {
                text-align: center;
                color: #008CBA;
            }
            .boxed {
                padding-left: 25%;
                padding-right: 15%;
            }
            .boxed label {
                display: inline-block;
                width: 200px;
                padding: 10px;
                border: solid 2px #008CBA;
                transition: all 0.3s;
                color: white;
                background-color: #008CBA;
                margin: 15px;
            }
    
            .boxed input[type="radio"] {
                display: none;
            }
    
            .boxed input[type="radio"]:checked + label {
                border: solid 2px #008CBA;
                background-color: white;
                color: black
            }
            .boxed input[type="radio"]:hover + label {
                cursor: pointer;
                border: 2px solid #008CBA;
                background-color: white;
                color: black
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
                margin-left: 17%;
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
        </style>-->
</html>
