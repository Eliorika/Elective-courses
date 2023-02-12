<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">

	<title>Authorization</title>
	<style>
		<jsp:include page="../css/styleAuthorization.css"/>
	</style>
</head>
<body bgcolor=#FFEBCD height: 100%; margin: 0; padding: 0; >

<nav>
	<ul class="top-menu">
		<li><a href="/nis?command=gotoWelcomeNotAuthorized">HOME</a></li>
		<li class="active">AUTHORIZATION</li>
		<li><a href="/nis?command=gotoAboutUsNotAuthorized">ABOUT US</a></li>
	</ul>
</nav>

<div id="heading">
	<h1>
		<b>AUTHORIZATION</b>
	</h1>
</div>

<content>
	<div id="content">
		<form id="AuthorizationPage" name="AuthorizationPage" method="POST" action="nis">
			<input type="hidden" name="command" value="login" />
			<p1>Enter login and password</p1>
			<P>
				Login: <input id="txtLogin" type="text" name="txtLogin" value="">
			</P>
			<P>
				Password: <input name="txtPassword" type="password" ID="txtPassword" value="">
			</P>
			<br/>${errorLoginpassMessage}<br/>
			<P>
				<input name="btnSubmit" type="submit" value="Submit" ID="btnSubmit">
				<input name="btnReset" type="reset" value="Reset" ID="btnReset">
			</P>

		</form>
	</div>
</content>

<footer>
	<div id="footer"></div>
</footer>
</body>
</html>