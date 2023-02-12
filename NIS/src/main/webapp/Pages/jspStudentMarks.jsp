<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="my.script.js"></script>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Grades</title>
    <style>
        <jsp:include page="../css/styleInformation.css"/>
        <jsp:include page="../css/local.css"/>
        <jsp:include page="../css/styleProfile.css"/>
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
        <b>Grades</b>
    </h1>
</div>

<aside>
    <nav>
        <ul class="aside-menu">
            <li><a href="/nis?command=gotoStudentProfile">PROFILE</a></li>
            <li><a href="/nis?command=gotoStudentCourses">MY COURSES</a></li>
            <li><a href="/nis?command=gotoSignCourse">SIGN UP FOR A COURSE</a></li>
            <li class="active">GRADES</li>
            <li><a href="/nis?command=gotoStudentAttendance">FINAl MARKS</a></li>
            <li><a href="/nis?command=logout">LOGOUT</a></li>

        </ul>
    </nav>
</aside>

<content>
    <div id="content"
         style="margin-left: 350px; max-width: 600px; height: 420px; font: 20px/20px 'Oswald', CONSTANTIA;">
        <P>
        <form method="get" action="nis">
            <input type="hidden" name="command" value="getStudentsOfCourse">
            Select a course:&nbsp;
            <select name="course" id="course" onchange="">
                <c:forEach items="${listCourse}" var="course">
                    <c:if test="${showStudentsList}">
                        <c:if test="${course.getCourseId() == courseID}">
                            <option value="${course.getCourseId()}" selected>${course.getCourseDescription()}</option>
                        </c:if>
                        <c:if test="${!(course.getCourseId() == courseID)}">
                            <option value="${course.getCourseId()}">${course.getCourseDescription()}</option>
                        </c:if>
                    </c:if>
                    <c:if test="${!showStudentsList}">
                        <option value="${course.getCourseId()}" selected>${course.getCourseDescription()}</option>
                    </c:if>
                </c:forEach>
            </select>
            <input type="submit" value="Choose">
        </form>

        <c:if test="${showStudentsList}">
            <form method="get" action="nis">
                <input type="hidden" name="command" value="getMarks">
                <input type="hidden" name="course" value=${courseID}>
                Choose student:
                <select name="student" onchange="">
                    <c:forEach items="${listStudents}" var="student">
                    <c:if test="${showDocs}">
                    <c:if test="${student.id == studentID}">
                    <option value="${student.id}" selected>${student.name}</option>
                    </c:if>
                    <c:if test="${!(student.id == studentID)}">
                    <option value="${student.id}">${student.name}</option>
                    </c:if>
                    </c:if>
                    <c:if test="${!showDocs}">
                    <option value="${student.id}">${student.name}</option>
                    </c:if>
                    </c:forEach>
                    <br/><br/>
                    <input type="submit" value="Show">
            </form>
        </c:if>


        <c:if test="${showDocs and !(empty docs)}">
            <table>
                <tr>
                    <th>Date</th>
                    <th>Attendance</th>
                    <th>Mark</th>
                </tr>

                <c:forEach items="${docs}" var="doc" varStatus="loop">
                    <tr>
                        <td><c:out value="${doc.date}"/></td>
                        <td>
                            <c:if test="${!doc.attendance}">
                                Absent
                            </c:if>
                            <c:if test="${doc.attendance}">
                                Present
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${doc.mark == 0}">
                                No mark
                            </c:if>
                            <c:if test="${!(doc.mark == 0)}">
                                <c:out value="${doc.mark}"/>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>

            </table>
        </c:if>
        <c:if test="${showDocs and (empty docs)}">
            No marks
        </c:if>
        </P>
    </div>
</content>


</body>
</html>

