<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Attendance</title>
<style>
        <jsp:include page="../css/styleInformation.css"/>
        <jsp:include page="../css/local.css"/>
        <jsp:include page="../css/styleProfile.css"/>
    </style>
</head>
<body bgcolor=#FFEBCD>
	<div id="welcome">
		<nav>
			<ul class="top-menu">
				<li><a href="/nis?command=gotoWelcomeAuthorized">HOME</a></li>
				<li><a href="/nis?command=gotoInformationFC">OUR COURSES</a></li>
				<li class="active">PROFILE</li>
				<li><a href="/nis?command=gotoAboutUsAuthorized">ABOUT US</a></li>
			</ul>
		</nav>

		<div id="heading">
			<h1>
				<b>Attendance</b>
			</h1>
		</div>

		<aside>
			<nav>
				<ul class="aside-menu">
					<li><a href="/nis?command=gotoStudentProfile">PROFILE</a></li>
					<li><a href="/nis?command=gotoStudentCourses">MY COURSES</a></li>
					<li><a href="/nis?command=gotoSignCourse">SIGN UP FOR A COURSE</a></li>
					<li><a href="/nis?command=gotoStudentMarks">MARKS</a></li>
					<li class="active">ATTENDANCE</li>
				</ul>
			</nav>
		</aside>



		<content>
		<div id="content"></div>
		</content>

		<footer>
			<div id="footer"></div>
		</footer>
	</div>
</body>
</html>