<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your courses</title>
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
				<b>My courses</b>
			</h1>
		</div>

		<aside>
			<nav>
				<ul class="aside-menu">
					<li><a href="/nis?command=gotoStudentProfile">PROFILE</a></li>
					<li class="active">MY COURSES</li>
					<li><a href="/nis?command=gotoSignCourse">SIGN UP FOR A COURSE</a></li>
					<li><a href="/nis?command=gotoStudentMarks">GRADES</a></li>
					<li><a href="/nis?command=gotoStudentAttendance">FINAl MARKS</a></li>
					<li><a href="/nis?command=logout">LOGOUT</a></li>
				</ul>
			</nav>
		</aside>



		<content>
		<div id="content" style = "margin-left:500px; text-align: center; max-width:600px;height: 420px;	font: 20px/20px 'Oswald', CONSTANTIA;">
			<p>
			<table>
				<tr>
					<th>Description</th>
					<th>Tutor</th>
					<th>Status</th>
					<th>Grades</th>
					<th>Leave course</th>
				</tr>

				<c:forEach items="${coursesList}" var="course" varStatus="loop">
					<tr>
						<td><c:out value="${course.getCourseDescription()}" /></td>
						<td><c:out value="${course.getTutor()}"/> </td>
						<td><c:out value="${course.status}"/> </td>
						<td><form method="GET" action="nis">
							<input type="hidden" name="command" value="getMarks">
							<input type="hidden" name="course" value="${course.course.id}">
							<input type="submit" value="Grades">
						</form></td>
						<td><form method="POST" action="nis">
							<input type="hidden" name="command" value="expelCourse">
							<input type="hidden" name="idCourse" value="${course.course.id}">
							<input type="submit" value="Expel">
						</form></td>
					</tr>
				</c:forEach>

			</table>
			</p>
		</div>
		</content>



</body>
</html>