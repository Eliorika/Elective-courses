<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Docs</title>
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
        <b>Docs</b>
    </h1>
</div>

<aside>
    <nav>
        <ul class="aside-menu">
            <li><a href="/nis?command=gotoTutorProfile">PROFILE</a></li>
            <li><a href="/nis?command=gotoNewCourse">CREATE NEW COURSE</a></li>
            <li><a href="/nis?command=gotoTutorCourses">COURSES</a></li>
            <li class="active">DOCS</li>
            <li><a href="/nis?command=gotoFinalDocs">FINAL DOCS</a></li>
            <li><a href="/nis?command=logout">LOGOUT</a></li>
        </ul>
    </nav>
</aside>


<content>
    <div id="inputMark">
        <form method="get" action="nis">
            <input type="hidden" name="command" value="getStudentsOfCourse">
            Select a course:&nbsp;
            <select name="course" id="course" onchange="">
                <c:forEach items="${listCourse}" var="course">
                    <c:if test="${showStudentsList}">
                        <c:if test="${course.id == courseID}">
                            <option value="${course.id}" selected>${course.courseDescription}</option>
                        </c:if>
                        <c:if test="${!(course.id == courseID)}">
                            <option value="${course.id}">${course.courseDescription}</option>
                        </c:if>
                    </c:if>
                    <c:if test="${!showStudentsList}">
                        <option value="${course.id}" selected>${course.courseDescription}</option>
                    </c:if>
                </c:forEach>
            </select>
            <input type="submit" value="Choose">
        </form>

        <c:if test="${showStudentsList and !(empty listStudents)}">
            <form method="get" action="nis">
                <input type="hidden" name="command" value="getStudentsDocs">
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
        <c:if test="${showStudentsList and empty listStudents}">
            No students
        </c:if>

        <c:if test="${showDocs}">
            <table>
                <tr>
                    <th>Date</th>
                    <th>Attendance</th>
                    <th>Mark</th>
                </tr>
                <tr>
                    <form method="POST" action="nis">
                        <input type="hidden" name="command" value="addDocs">
                        <input type="hidden" name="student" value="${studentID}">
                        <input type="hidden" name="course" value="${courseID}">
                        <td>
                            <input id="date" type="date" name="date" value="">
                        </td>
                        <td>
                            <select name="attendance">
                                <option value="1">Present</option>
                                <option value="0">Absent</option>
                            </select>
                        </td>
                        <td>
                            <select name="mark">
                                <option value="0">--no--</option>
                                <option value="5">5</option>
                                <option value="4">4</option>
                                <option value="3">3</option>
                                <option value="2">2</option>
                            </select>
                        </td>
                        <td><input type="submit" value="Save"></td>
                    </form>
                    <td>${resultMessage}</td>
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

    </div>
</content>


</body>
</html>