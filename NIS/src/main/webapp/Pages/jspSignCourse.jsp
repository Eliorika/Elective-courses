<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign up for a course</title>
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
				<b>Sign up for a course</b>
			</h1>
		</div>

		<aside>
			<nav>
				<ul class="aside-menu">
					<li><a href="/nis?command=gotoStudentProfile">PROFILE</a></li>
					<li><a href="/nis?command=gotoStudentCourses">MY COURSES</a></li>
					<li class="active">SIGN UP FOR A COURSE</li>
					<li><a href="/nis?command=gotoStudentMarks">GRADES</a></li>
					<li><a href="/nis?command=gotoStudentAttendance">FINAl MARKS</a></li>
					<li><a href="/nis?command=logout">LOGOUT</a></li>
					
				</ul>
			</nav>
		</aside>

		<content>
		<div id="content" style = "margin-left:500px;text-align: center; max-width:600px;height: 420px;	font: 20px/20px 'Oswald', CONSTANTIA;">
			${resultMessage}
			<table>
				<tr>
					<th>Description</th>
					<th>Tutor</th>
					<th>Sign up</th>
				</tr>

				<c:forEach items="${coursesList}" var="course" varStatus="loop">
					<tr>
						<td><c:out value="${course.courseDescription}" /></td>
						<td><c:out value="${course.tutor.name}"/> </td>
						<td><form method="post" action="nis">
							<input type="hidden" name="command" value="signCourse">
							<input type="hidden" name="idSign" value="${course.id}">
							<input type="submit" value="Sign up">
						</form></td>
					</tr>
				</c:forEach>

			</table>
		</div>
		</content>
</body>
</html>