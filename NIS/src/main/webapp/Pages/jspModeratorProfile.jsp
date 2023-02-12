<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Moderator profile</title>
	<style>
		<jsp:include page="../css/styleAnotherUsers.css"/>
	</style>
</head>
<body bgcolor=#FFEBCD height: 100%; margin: 0; padding: 0;>

<nav>
	<ul class="top-menu">
		<li><a href="/nis?command=gotoWelcomeOther">HOME</a></li>
		<li class="active">PROFILE</li>
		<li><a href="/nis?command=gotoAboutUsOther">ABOUT US</a></li>
	</ul>
</nav>

<div id="heading">
	<h1>
		<b>PROFILE</b>
	</h1>
</div>

<aside>
	<nav>
		<ul class="aside-menu">
			<li class="active">PROFILE</li>
			<li><a href="/nis?command=gotoAllUsersModerator">ALL USERS</a></li>
			<li><a href="/nis?command=gotoModeratorFindUser">FIND USER</a></li>
			<li><a href="/nis?command=logout">LOGOUT</a></li>
		</ul>
	</nav>
</aside>



<content>
	<form style="border: 2px solid #2F4F4F; width: 500px; margin-left: 520px;">
		<div id="profile">
			<P>
				Welcome to System, ${name}
			</P>
			<P>
				This is ${type} profile
			</P>
		</div>
	</form>
</content>

<footer>
	<div id="footer"></div>
</footer>
</body>
</html>