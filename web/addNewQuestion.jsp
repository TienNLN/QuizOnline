<%-- 
    Document   : addNewQuestion
    Created on : 19-Feb-2021, 20:50:25
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
        <!--<title>Hana Shop</title>-->
    </head>
    <body class="text-center" data-new-gr-c-s-check-loaded="14.990.0" data-gr-ext-installed="">

        <c:set var="lastUser" value="${sessionScope.LAST_USER}"/>
        <c:set var="listSubject" value="${sessionScope.LIST_SUBJECT}"/>

        <div class="cover-container d-flex h-100 p-3 mx-auto flex-column">
            <header class="masthead mb-auto">
                <div class="inner">
                    <h3 class="masthead-brand">Quiz Online Lecturer</h3>
                    <nav class="nav nav-masthead justify-content-center">
                        <a class="nav-link">Welcome, ${lastUser.name}</a>

                        <a class="nav-link" href="logOut">Logout</a>
                    </nav>
                </div>
            </header>
            <form action="addNewQuestion">
                <main role="main" class="form-group align-items-center">
                    <div class="form-group col-md-3" style="margin-left: 20px">
                        <label for="txtQuestion">Question</label>
                        <input type="text" 
                               <c:if test="${not empty requestScope.QUESTION_EMPTY}">
                                   class="form-control is-invalid"
                               </c:if>
                               <c:if test="${empty requestScope.QUESTION_EMPTY}">
                                   class="form-control"
                               </c:if>
                               name="txtQuestion" placeholder="Enter Question">
                        <div class="invalid-feedback">
                            ${requestScope.QUESTION_EMPTY}
                        </div>
                    </div>
                    <br/>

                    <div class="form-group col-md-3" style="margin-left: 20px">
                        <label for="txtSubjectID">Subject ID</label>
                        <select class="form-control form-control-lg" name="txtSubjectID">
                            <c:forEach items="${listSubject}" var="subject">
                                <option value="${subject.subjectID}">${subject.subjectID}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <br/>

                    <div class="row" style="margin-left: 20px">
                        <button type="submit" class="btn btn-primary col-md-1">Add Question</button>
                        <div class="col-md-1"></div>
                        <button type="reset" class="btn btn-primary col-md-1">Reset</button><br/>
                    </div>
                </main>
            </form>

            <div class="row">
                <div class="col-md-4">

                </div>

                <a class="btn btn-primary col-md-4" href="adminPage" role="button">Back to Admin Page</a>

                <div class="col-md-4">

                </div>
            </div>

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
