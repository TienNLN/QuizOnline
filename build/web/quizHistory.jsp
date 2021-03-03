<%-- 
    Document   : admin
    Created on : 18-Feb-2021, 17:13:28
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
    <body class="text-center" data-new-gr-c-s-check-loaded="14.990.0" data-gr-ext-installed="">

        <c:set var="lastUser" value="${sessionScope.LAST_USER}"/>
        <c:set var="listSubject" value="${sessionScope.LIST_SUBJECT}"/>
        <c:set var="searchResult" value="${requestScope.SEARCH_VALUE}"/>

        <c:set var="lastSearch" value="${requestScope.SEARCH_TEXT_VALUE}"/>
        <c:set var="lastSearchSubject" value="${requestScope.SEARCH_SUBJECT_VALUE}"/>
        <c:set var="lastSearchStatus" value="${requestScope.SEARCH_STATUS_VALUE}"/>        

        <c:set var="listQuizHistory" value="${requestScope.LIST_QUIZ_HISTORY}"/>

        <div class="cover-container d-flex h-100 p-3 mx-auto flex-column">
            <header class="masthead mb-auto">
                <div class="inner">
                    <h3 class="masthead-brand">Quiz History</h3>
                    <nav class="nav nav-masthead justify-content-center">
                        <a class="nav-link">Welcome, ${lastUser.name}</a>
                        <a class="nav-link" href="logOut">Logout</a>
                    </nav>
                </div>
            </header>

            <form action="searchQuizHistory">
                <main role="main" class="form-group">
                    <div class="row">
                        <div class="col-7">
                            <input class="form-control" name="txtSubjectName" placeholder="Search Subject Name">
                        </div>
                        <div class="col-5">
                            <input class="btn btn-lg btn-secondary" type="submit" value="Search" name="btnAction" />
                        </div>
                    </div>
                </main>
            </form>
            <br/><br/><br/><br/>


            <c:if test="${not empty listQuizHistory}">
                <main role="main" class="form-group">
                    <table class="table table-light table-striped">
                        <thead>
                            <tr>
                                <th scope="col">Quiz ID</th>
                                <th scope="col">Date Take Quiz</th>
                                <th scope="col">Time Take Quiz</th>
                                <th scope="col">Point</th>
                                <th scope="col">Subject ID</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="quiz" items="${listQuizHistory}">
                                <tr>
                                    <th scope="row">
                                        ${quiz.quizID}
                                    </th>

                                    <td>
                                        ${quiz.dateTakeQuiz}
                                    </td>

                                    <td>
                                        ${quiz.timeTakeQuiz}
                                    </td>

                                    <td>
                                        ${quiz.point}
                                    </td>
                                    
                                    <td>
                                        ${quiz.subjectID}
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <a class="btn btn-primary" href="startUp" role="button">Back to Home Page</a>
                </main>
            </c:if>

            <c:if test="${empty requestScope.LIST_QUIZ_HISTORY}">
                <main role="main" class="form-group">
                    <h1>
                        No quiz have done yet !
                    </h1>
                </main>
            </c:if>

            <footer class="mastfoot mt-auto">
                <div class="inner">
                    <p>Cover template for <a href="https://getbootstrap.com/">Bootstrap</a>, by <a href="https://twitter.com/mdo">@mdo</a>.</p>
                </div>
            </footer>
        </div>


        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js" integrity="sha512-bLT0Qm9VnAYZDflyKcBaQ2gg0hSYNQrJ8RilYldYQ1FxQYoCLtUjuuRuZo+fjqhx/qtq/1itJ0C2ejDxltZVFg==" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

    </body>
</html>
