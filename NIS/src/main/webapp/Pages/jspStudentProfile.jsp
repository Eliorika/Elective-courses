<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Profile</title>
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
		<b>My profile</b>
	</h1>
</div>

<aside>
	<nav>
		<ul class="aside-menu">
			<li class="active">PROFILE</li>
			<li><a href="/nis?command=gotoStudentCourses">MY COURSES</a></li>

			<li><a href="/nis?command=gotoSignCourse">SIGN UP FOR A COURSE</a></li>
			<li><a href="/nis?command=gotoStudentMarks">GRADES</a></li>
			<li><a href="/nis?command=gotoStudentAttendance">FINAl MARKS</a></li>
			<li><a href="/nis?command=logout">LOGOUT</a></li>
		</ul>
	</nav>
</aside>



<content>
	<div id="content">
		<form >
			<div class="profile" style = "border: 2px solid #2F4F4F;  margin-left: 215px;">
				<P>
					Welcome to System, ${name}
				</P>
				<P>
					This is ${type} profile
				</P>
			</div>
		</form>
	</div>
</content>


</body>
</html>