<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to page! NIS "Facultative Courses"</title>
<style>
        <jsp:include page="../css/styleInformation.css"/>
        <jsp:include page="../css/local.css"/>
        <jsp:include page="../css/styleWelcome.css"/>
    </style>
</head>
<body bgcolor=#FFEBCD>
	<div id="welcome">
		<nav>
			<ul class="top-menu">
				<li class="active">HOME</li>
				<li><a href="/nis?command=gotoInformationFC">OUR COURSES</a></li>
				<li><a href="/nis?command=?">PROFILE</a></li>
				<li><a href="/nis?command=gotoAboutUsAuthorized">ABOUT US</a></li>
			</ul>
		</nav>

		<div id="heading">
			<h1>
				<b>WELCOME TO NIS!</b>
			</h1>
		</div>


		<content>
		<div id="content"></div>
		</content>

		<footer>
			<div id="footer"></div>
		</footer>
	</div>
</body>
</html>