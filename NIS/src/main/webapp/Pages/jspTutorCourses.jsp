<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
				<b>Your courses</b>
			</h1>
		</div>

		<aside>
			<nav>
				<ul class="aside-menu">
					<li><a href="/nis?command=gotoTutorProfile">PROFILE</a></li>
					<li><a href="/nis?command=gotoNewCourse">CREATE NEW COURSE</a></li>
					<li class="active">COURSES</li>
					<li><a href="/nis?command=gotoDocs">DOCS</a></li>
					<li><a href="/nis?command=gotoFinalDocs">FINAL DOCS</a></li>
					<li><a href="/nis?command=logout">LOGOUT</a></li>
					
				</ul>
			</nav>
		</aside>



		<content>
		<div id="content" style = "margin-left:310px; max-width:600px; height: 420px; font: 20px/20px 'Oswald', CONSTANTIA;">
			<div id="special_table">
			<table>
				<tr>
					<th>ID</th>
					<th>Description</th>
				</tr>

				<c:forEach items="${coursesList}" var="course" varStatus="loop">
					<tr>
						<td><c:out value="${course.id}" /></td>
						<td><c:out value="${course.courseDescription}" /></td>
					</tr>
				</c:forEach>

			</table>
		</div>
		</div>
		</content>


</body>
</html>