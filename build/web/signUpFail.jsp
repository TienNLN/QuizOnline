<%-- 
    Document   : signUpFail
    Created on : 18-Feb-2021, 10:55:19
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Quiz Online</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <meta name="google-signin-client_id" content="154810970511-re6vslhed4s3dm115n8m1mcfu7g79ga1.apps.googleusercontent.com">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

        <link href="css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <h2 style="margin: 40px">Sign Up Page</h2>
        <form action="signUp" method="POST">
            <div class="form-group col-md-3" style="margin-left: 20px; margin-top: 100px">
                <label for="txtEmail">Email</label>
                <input type="email" 
                       <c:if test="${not empty requestScope.EMAIL_EMPTY_ERROR}">
                           class="form-control is-invalid"
                       </c:if>
                       <c:if test="${empty requestScope.EMAIL_EMPTY_ERROR}">
                           class="form-control"
                       </c:if>
                       name="txtEmail" placeholder="Enter Email">
                <div class="invalid-feedback">
                    ${requestScope.EMAIL_EMPTY_ERROR}
                </div>
            </div>
            <br/>
            <div class="form-group col-md-3" style="margin-left: 20px">
                <label for="txtPassword">Password</label>
                <input type="password" 
                       <c:if test="${not empty requestScope.PASSWORD_EMPTY_ERROR}">
                           class="form-control is-invalid"
                       </c:if>
                       <c:if test="${empty requestScope.PASSWORD_EMPTY_ERROR}">
                           class="form-control"
                       </c:if>
                       name="txtPassword" placeholder="Enter Password">
                <div class="invalid-feedback">
                    ${requestScope.PASSWORD_EMPTY_ERROR}
                </div>
            </div>
            <br/>
            <div class="form-group col-md-3" style="margin-left: 20px">
                <label for="txtConfirmPassword">Confirm Password</label>
                <input type="password" 
                       <c:if test="${not empty requestScope.CONFIRM_PASSWORD_EMPTY_ERROR}">
                           class="form-control is-invalid"
                           <c:set var="confirmPassErr" value="${requestScope.CONFIRM_PASSWORD_EMPTY_ERROR}"/>
                       </c:if>
                       <c:if test="${empty requestScope.CONFIRM_PASSWORD_EMPTY_ERROR}">
                           <c:if test="${not empty requestScope.PASSWORD_CONFIRM_NOT_MATCH_ERROR}">
                               class="form-control is-invalid"
                               <c:set var="confirmPassErr" value="${requestScope.PASSWORD_CONFIRM_NOT_MATCH_ERROR}"/>
                           </c:if>
                           <c:if test="${empty requestScope.PASSWORD_CONFIRM_NOT_MATCH_ERROR}">
                               class="form-control"
                           </c:if>
                       </c:if>
                       name="txtConfirmPassword" placeholder="Confirm Password">
                <div class="invalid-feedback">
                    ${confirmPassErr}
                </div>
            </div>
            <br/>
            <div class="form-group col-md-3" style="margin-left: 20px">
                <label for="txtName">Name</label>
                <input type="text" 
                       <c:if test="${not empty requestScope.NAME_EMPTY_ERROR}">
                           class="form-control is-invalid"
                       </c:if>
                       <c:if test="${empty requestScope.NAME_EMPTY_ERROR}">
                           class="form-control"
                       </c:if>
                       name="txtName" placeholder="Enter Name">
                <div class="invalid-feedback">
                    ${requestScope.NAME_EMPTY_ERROR}
                </div>
            </div>
            <br/>

            <c:if test="${not empty requestScope.SIGN_UP_FAIL_ERROR}">
                <div class="invalid-feedback">
                    ${requestScope.SIGN_UP_FAIL_ERROR}
                </div>
            </c:if>
            
            <br/>

            <hr class="col-md-3" style="margin-left: 20px" align="left">
            <button type="submit" class="btn btn-primary" value="Sign Up" name="btnAction" style="margin-left: 20px">Sign Up</button>
            <button type="reset" class="btn btn-primary" style="margin-left: 20px">Reset</button>
            <hr class="col-md-3" style="margin-left: 20px" align="left">
        </form>

        <script src="https://apis.google.com/js/platform.js?onload=init" async defer></script>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>
