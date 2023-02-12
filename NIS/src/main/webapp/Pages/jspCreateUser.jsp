<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create new user</title>
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
			<b>CREATE NEW USER</b>
		</h1>
	</div>

	<aside>
		<nav>
			<ul class="aside-menu">
				<li><a href="/nis?command=gotoAdminProfile">PROFILE</a></li>
				<li><a href="/nis?command=gotoAllUsersForAdmin">ALL USERS</a></li>
				<li class="active">CREATE NEW USER</li>
				<li><a href="/nis?command=gotoAdminFindUser">FIND
					USER</a></li>
				<li><a href="/nis?command=logout">LOGOUT</a></li>

			</ul>
		</nav>
	</aside>



	<content>
	<div id="inputUser">
		<form style=" width: 500px; margin-left: 526px;" method="post" action="nis">
			<input type="hidden" name="command" value="createUser" />
			<P>
				User name: <input id="txtNameUser" type="text" NAME="txtNameUser">
			</P>
			<P>
				Login: <input id="txtLogin" type="text" NAME="txtLogin">
			<P>
				Password: <input id="txtPassword" type="password" NAME="txtPassword">
			</P>
			<P>
				Type of user: <select name = userType>
					<option value="STUDENT">Student</option>
					<option value="TUTOR">Tutor</option>
					<option value="ADMIN">Admin</option>
					<option value="MODERATOR">Moderator</option>
				</select>
			</P>
			<P>
				<input name="btnSaveUser" type="submit" value="Save"
					ID="btnSaveUser"> <input
					name="btnReset" type="reset" value="Cancel" ID="btnReset">
			</P>
			<br/>${resultMessage}<br/>
		</form>

	</div>
	</content>

</body>
</html>