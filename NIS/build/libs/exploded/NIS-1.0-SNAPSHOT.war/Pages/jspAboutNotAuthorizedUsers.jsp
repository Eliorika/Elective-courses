<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>About us</title>
<style>
        <jsp:include page="../css/styleInformation.css"/>
        <jsp:include page="../css/local.css"/>
    </style>
</head>
<body bgcolor=#FFEBCD>
	<div id="welcome">
		<nav>
			<ul class="top-menu">
				<li><a href="/nis?command=gotoWelcomeNotAuthorized">HOME</a></li>
					<li><a href="/nis?command=gotoAuthorization">AUTHORIZATION</a></li>
				<li class="active">ABOUT US</li>
			</ul>
		</nav>

		<div id="heading">
			<h1>
				<b>About us</b>
			</h1>
		</div>

		<content>
		<div id="content">
			<p>Сетевая информационная система "Факультативные курсы".</p>
			<p>Разработано в рамках занятия "Управление программным проектом"</p>
			<p>2022, РГРТУ</p>
		</div>

		</content>

		<footer>
			<div id="footer"></div>
		</footer>
	</div>
</body>
</html>