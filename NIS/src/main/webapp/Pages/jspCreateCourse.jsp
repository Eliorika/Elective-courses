<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Create course</title>
	<style>
		<jsp:include page="../css/styleInformation.css"/>
		<jsp:include page="../css/styleProfile.css"/>
		<jsp:include page="../css/styleFormCourse.css"/>
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
		<b>Create new course</b>
	</h1>
</div>

<aside>
	<nav>
		<ul class="aside-menu">
			<li><a href="/nis?command=gotoTutorProfile">PROFILE</a></li>
			<li class="active">CREATE NEW COURSE</li>
			<li><a href="/nis?command=gotoTutorCourses">COURSES</a></li>
			<li><a href="/nis?command=gotoDocs">DOCS</a></li>
			<li><a href="/nis?command=gotoFinalDocs">FINAL DOCS</a></li>
			<li><a href="/nis?command=logout">LOGOUT</a></li>


		</ul>
	</nav>
</aside>


<content>
	<div id="content">
		<form>
			<P>
				NEW COURSE
			</P>
			<form method="post" action="nis">
				<input type="hidden" name="command" value="createCourse" />
			<P>
				Description: <input id="txtDescriptionCourse" type="text" NAME="txtDescriptionCourse" >
			</P>
			<input name="btnSaveCourse" type="submit" value="Save">
			<input name="btnReset" type="reset" value="Reset" ID="btnReset">
			<P>${resultMessage}</P>
			</form>
		</form>
	</div>
</content>

</body>
</html>