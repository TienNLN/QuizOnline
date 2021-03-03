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
        <c:set var="listQuestionChoice" value="${requestScope.LIST_QUESTION_CHOICE}"/>

        <c:set var="lastSearch" value="${requestScope.SEARCH_TEXT_VALUE}"/>
        <c:set var="lastSearchSubject" value="${requestScope.SEARCH_SUBJECT_VALUE}"/>
        <c:set var="lastSearchStatus" value="${requestScope.SEARCH_STATUS_VALUE}"/>

        <c:set var="numberOfPage" value="${sessionScope.NUMBER_OF_PAGE_USER}"/>


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
            <main role="main" class="form-group">
                <a class="btn btn-primary" href="addNewQuestionPage" role="button">Add New Question</a>
            </main><br/><br/><br/>

            <form action="search">
                <main role="main" class="form-group">
                    <div class="row">
                        <div class="col-7">
                            <input class="form-control" name="txtSearch" placeholder="Search Question">
                        </div>
                        <div class="col-5">
                            <input class="btn btn-lg btn-secondary" type="submit" value="Search" name="btnAction" />
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-1">
                            <button class="btn btn-primary" type="button" data-toggle="collapse" data-target=".multi-collapse" aria-expanded="false" aria-controls="multiCollapseExample">Advance Search</button>
                        </div>
                    </div>


                    <div class="row" style="width: 100%">
                        <div class="col">
                            <div class="collapse multi-collapse" id="multiCollapseExample">
                                <div class="card card-body">
                                    <div class="form-group">
                                        <label for="dropdownMenuButton" class="text-secondary">
                                            Search by Subject Name
                                        </label><br/>

                                        <select class="form-control form-control-lg" id="categorySelect" name="txtSubject">
                                            <option value=""></option>
                                            <c:forEach items="${listSubject}" var="subject">
                                                <option value="${subject.subjectID}">${subject.subjectID}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col">
                            <div class="collapse multi-collapse" id="multiCollapseExample">
                                <div class="card card-body">
                                    <label for="radio-gr-question-status" class="text-secondary">
                                        Search by Question Status
                                    </label><br/>
                                    <div class="form-group radio-gr-question-status">
                                        <!-- Material unchecked -->
                                        <div class="form-check">
                                            <input type="radio" class="form-check-input" id="materialUnchecked" name="txtQuestionStatus" value="usingQuestion">
                                            <label class="form-check-label text-secondary" for="materialUnchecked">Being Used Question</label>
                                        </div>

                                        <!-- Material checked -->
                                        <div class="form-check">
                                            <input type="radio" class="form-check-input" id="materialChecked" name="txtQuestionStatus" value="disableQuestion" checked>
                                            <label class="form-check-label text-secondary" for="materialChecked">Disabled Question</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                </main>
            </form>
            <br/><br/><br/><br/>

            <!--            <main role="main" class="form-group">-->
            <c:if test="${not empty searchResult}">
                <main role="main" class="form-group">
                    <table class="table table-light table-striped">
                        <thead>
                            <tr>
                                <th scope="col">Question ID</th>
                                <th scope="col">Question</th>
                                <th scope="col">Create Date</th>
                                <th scope="col">Subject ID</th>
                                <th scope="col">Status</th>
                                <th scope="col" colspan="3">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="question" items="${searchResult}">
                            <form action="updateQuestion">
                                <tr>
                                    <th scope="row">
                                        ${question.questionID}
                                        <input type="hidden" name="txtQuestionID" value="${question.questionID}" />
                                    </th>

                                    <td>
                                        <input type="text" 
                                               <c:if test="${empty requestScope.QUESTION_ID_EMPTY}">
                                                   class="form-control"
                                               </c:if>
                                               <c:if test="${not empty requestScope.QUESTION_ID_EMPTY}">
                                                   <c:if test="${question.questionID != requestScope.QUESTION_ID_EMPTY}">
                                                       class="form-control"
                                                   </c:if>
                                                   <c:if test="${question.questionID == requestScope.QUESTION_ID_EMPTY}">
                                                       class="form-control is-invalid"
                                                   </c:if>
                                               </c:if>
                                               name="txtQuestion" value="${question.question}">
                                        <div class="invalid-feedback">
                                            Question content cannot be empty !
                                        </div>
                                    </td>

                                    <td>
                                        ${question.createDate}
                                    </td>

                                    <td>
                                        <select class="form-control form-control-lg" name="txtSubjectID">
                                            <option value="${question.subjectID}">${question.subjectID}</option>
                                            <c:forEach items="${listSubject}" var="subject">
                                                <c:if test="${subject.subjectID != question.subjectID}">
                                                    <option value="${subject.subjectID}">${subject.subjectID}</option>
                                                </c:if>

                                            </c:forEach>
                                        </select>
                                    </td>

                                    <td>
                                        <c:if test="${question.status}">
                                            <select class="form-control form-control-lg" name="txtStatus">
                                                <option value="Using">Using</option>
                                                <option value="Disable">Disable</option>
                                            </select>
                                        </c:if>
                                        <c:if test="${not question.status}">
                                            <select class="form-control form-control-lg" name="txtStatus">
                                                <option value="Disable">Disable</option>
                                                <option value="Using">Using</option>
                                            </select>
                                        </c:if>
                                    </td>

                                    <td>
                                        <a class="btn btn-primary" href="viewQuestionInfo?questionID=${question.questionID}" role="button">View</a>
                                    </td>

                                    <td>
                                        <button class="btn btn-primary" type="submit">Update</button>
                                    </td>

                                <input type="hidden" name="searchTextValue" value="${requestScope.SEARCH_TEXT_VALUE}" />
                                <input type="hidden" name="searchSubjectValue" value="${requestScope.SEARCH_SUBJECT_VALUE}" />
                                <input type="hidden" name="searchStatusValue" value="${requestScope.SEARCH_STATUS_VALUE}" />

                                <td>
                                    <c:url var="urlDeleteQuestion" value="deleteQuestion">
                                        <c:param name="questionID" value="${question.questionID}"></c:param>
                                        <c:param name="searchTextValue" value="${requestScope.SEARCH_TEXT_VALUE}"></c:param>
                                        <c:param name="searchSubjectValue" value="${requestScope.SEARCH_SUBJECT_VALUE}"></c:param>
                                        <c:param name="searchStatusValue" value="${requestScope.SEARCH_STATUS_VALUE}"></c:param>
                                    </c:url>
                                    <a class="btn btn-primary" href="${urlDeleteQuestion}" role="button" onclick="return confirm('Are you sure?')">Delete</a>
                                </td>
                                </tr>
                            </form>
                        </c:forEach>
                        </tbody>
                    </table>
                </main>
            </c:if>
            <!--            </main>-->
            <c:if test="${not empty requestScope.NO_SEARCH_RESULT}">
                <main role="main" class="form-group">
                    <h1>
                        ${requestScope.NO_SEARCH_RESULT}
                    </h1>
                </main>
            </c:if>


            <c:if test="${not empty listQuestionChoice}">
                <main role="main" class="form-group">
                    <table class="table table-light table-striped">
                        <thead>
                            <tr>
                                <th scope="col">Choice ID</th>
                                <th scope="col">Question ID</th>
                                <th scope="col">Result</th>
                                <th scope="col">Choice</th>
                                <th scope="col" colspan="1">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="questionChoice" items="${listQuestionChoice}">
                            <form action="updateQuestionChoice">
                                <tr>
                                    <th scope="row">
                                        ${questionChoice.choiceID}
                                        <input type="hidden" name="txtQuestionChoiceID" value="${questionChoice.choiceID}" />
                                    </th>

                                    <td>
                                        ${questionChoice.questionID}
                                        <input type="hidden" name="txtQuestionID" value="${questionChoice.questionID}" />
                                    </td>

                                    <td>
                                        <input type="hidden" name="txtOldQuestionResult" value="${questionChoice.isCorrect}" />
                                        <c:if test="${questionChoice.isCorrect}">
                                            <select class="form-control form-control-lg" name="txtQuestionResult">
                                                <option value="true">True</option>
                                                <option value="false">False</option>
                                            </select>
                                        </c:if>
                                        <c:if test="${not questionChoice.isCorrect}">
                                            <select class="form-control form-control-lg" name="txtQuestionResult">
                                                <option value="false">False</option>
                                                <option value="true">True</option>
                                            </select>
                                        </c:if>
                                    </td>

                                    <td>
                                        <input type="text" 
                                               <c:if test="${empty requestScope.QUESTION_CHOICE_ID_EMPTY}">
                                                   class="form-control"
                                               </c:if>
                                               <c:if test="${not empty requestScope.QUESTION_CHOICE_ID_EMPTY}">
                                                   <c:if test="${questionChoice.choiceID != requestScope.QUESTION_CHOICE_ID_EMPTY}">
                                                       class="form-control"
                                                   </c:if>
                                                   <c:if test="${questionChoice.choiceID == requestScope.QUESTION_CHOICE_ID_EMPTY}">
                                                       class="form-control is-invalid"
                                                   </c:if>
                                               </c:if>
                                               name="txtQuestionChoiceContent" value="${questionChoice.choice}">
                                        <div class="invalid-feedback">
                                            Choice content cannot be empty !
                                        </div>
                                    </td>

                                    <td>
                                        <button class="btn btn-primary" type="submit">Update</button>
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                        </tbody>
                    </table>
                    <a class="btn btn-primary" href="adminPage" role="button">Back to Admin Page</a>
                </main>
            </c:if>

            <c:if test="${not empty requestScope.NO_QUESTION_CHOICE}">
                <main role="main" class="form-group">
                    <h1>
                        ${requestScope.NO_QUESTION_CHOICE}
                    </h1>
                </main>
                <main class="form-group">
                    <a class="btn btn-primary" href="addNewQuestionChoicePage?onTargetQuestionID=${requestScope.VIEWING_INFO_QUESTION_ID}" role="button">Add Question Choice for this Question</a>
                </main>
            </c:if>

            <c:if test="${not empty searchResult}">
                    <main>
                        <div style="margin-left: 40%; width: 5%">
                            <nav aria-label="Page navigation example">
                                <ul class="pagination">
                                    <c:forEach var="i" begin="1" end="${numberOfPage}">
                                        <c:url value="search" var="swapPageNumber">
                                            <c:param name="pageNumber" value="${i}"></c:param>

                                            <c:param name="txtSearch" value="${lastSearch}"></c:param>
                                            <c:param name="txtSubject" value="${lastSearchSubject}"></c:param>
                                            <c:param name="txtQuestionStatus" value="${lastSearchStatus}"></c:param>
                                        </c:url>
                                        <li class="page-item"><a class="page-link" href="${swapPageNumber}">${i}</a></li>
                                        </c:forEach>
                                </ul>
                            </nav>
                        </div>
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
