<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Final Docs</title>
    <style>
        <jsp:include page="../css/styleInformation.css"/>
        <jsp:include page="../css/local.css"/>
        <jsp:include page="../css/styleProfile.css"/>
        <jsp:include page="../css/styleInputMark.css"/>
    </style>
</head>
<body bgcolor=#FFEBCD>
<nav>
    <ul class="top-menu">
        <li><a href="/nis?command=gotoWelcomeAuthorized">HOME</a></li>
        <li class="active">PROFILE</li>
        <li><a href="/nis?command=gotoAboutUsAuthorized">ABOUT US</a></li>
    </ul>
</nav>

<div id="heading">
    <h1>
        <b>Final docs</b>
    </h1>
</div>

<aside>
    <nav>
        <ul class="aside-menu">
            <li><a href="/nis?command=gotoTutorProfile">PROFILE</a></li>
            <li><a href="/nis?command=gotoNewCourse">CREATE NEW COURSE</a></li>
            <li><a href="/nis?command=gotoTutorCourses">COURSES</a></li>
            <li><a href="/nis?command=gotoDocs">DOCS</a></li>
            <li class="active">FINAL DOCS</li>
            <li><a href="/nis?command=logout">LOGOUT</a></li>
        </ul>
    </nav>
</aside>


<content>
    <div id="inputMark">
        <form method="get" action="nis">
            <input type="hidden" name="command" value="getFinalMarks">
            Select a course:&nbsp;
            <select name="course" id="course" onchange="">
                <c:forEach items="${listCourse}" var="course">
                    <c:if test="${showMarks}">
                        <c:if test="${course.id == courseID}">
                            <option value="${course.id}" selected>${course.courseDescription}</option>
                        </c:if>
                        <c:if test="${!(course.id == courseID)}">
                            <option value="${course.id}">${course.courseDescription}</option>
                        </c:if>
                    </c:if>
                    <c:if test="${!showMarks}">
                        <option value="${course.id}">${course.courseDescription}</option>
                    </c:if>
                </c:forEach>
            </select>
            <input type="submit" value="Choose">
        </form>

        <c:if test="${showMarks and !((empty listStudents) and (empty marks))}">
            <table>
                <tr>
                    <th>Student</th>
                    <th>Mark</th>
                </tr>
                <tr>
                    <c:if test="${!(empty listStudents)}">
                    <form method="POST" action="nis">
                        <input type="hidden" name="command" value="addFinalDocs">
                        <input type="hidden" name="course" value="${courseID}">
                        <td>
                            <select name="student" onchange="">
                                <c:forEach items="${listStudents}" var="student">
                                <option value="${student.id}">${student.name}</option>
                                </c:forEach>
                        </td>
                        <td><select name="finalMark">
                            <option value="Passed">Passed</option>
                            <option value="Expelled">Expelled</option>
                            <option value="Not passed">Not passed</option>
                        </select>
                        </td>
                        <td><input type="submit" value="Save"></td>
                    </form>
                    <td>${resultMessage}</td>
                    </c:if>
                </tr>

                <c:forEach items="${marks}" var="doc" varStatus="loop">
                    <tr>
                        <td><c:out value="${doc.student.name}"/></td>
                        <td><c:out value="${doc.mark}"/>
                        </td>
                    </tr>
                </c:forEach>

            </table>
        </c:if>
        <c:if test="${showMarks and (empty listStudents) and (empty marks)}">
            No students
        </c:if>
        </P>

        </form>
    </div>
</content>


</body>
</html>