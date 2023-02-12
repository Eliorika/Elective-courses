<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Find user</title>
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
		<b>FIND USER</b>
	</h1>
</div>

<aside>
	<nav>
		<ul class="aside-menu">

			<li><a href="/nis?command=gotoModeratorProfile">PROFILE</a></li>
			<li><a href="/nis?command=gotoAllUsersModerator">ALL USERS</a></li>
			<li class="active">FIND USER</li>
			<li><a href="/nis?command=logout">LOGOUT</a></li>

		</ul>
	</nav>
</aside>



<content style = "padding-left:250px;">
	<div id="inputUser">
		<form style="width: 500px; margin-left: 526px;" method="get" action="nis">
			<input type="hidden" name="command" value="findUser" />
			<input type="hidden" name="usertype" value="admin"/>

			<P>
				Login: <input id="txtSearch" type="text" NAME="txtSearch">

				<input name="btnSearchUser" type="submit" value="Find" ID="btnSearchUser"></P>
			<P>
				<br/>${resultMessage}<br/>
			</P>
		</form>
		<form style="width: 500px; margin-left: 526px;" method="post" action="nis">
			<input type="hidden" name="command" value="editUser" />
			<c item="${user}" var="user" varStatus="loop">
				<input type="hidden" name="idEdit" value="${user.id}">
				<P>
					User name: <input id="txtNameUser" type="text" NAME="txtNameUser" value="${user.name}">
				</P>
				<P>
					Login: <input id="txtLogin" type="text" NAME="txtLogin" value="${user.login}">
				<P>
					Password: <input id="txtPassword" type="text" NAME="txtPassword" value="${user.password}">
				</P>
				<P>
					Type of user: <select name = userType>
					<c:if test="${user.type == 'STUDENT'}">
						<option value="STUDENT" selected="selected">Student </option>
					</c:if>
					<c:if test="${user.type != 'STUDENT'}">
						<option value="STUDENT">Student </option>
					</c:if>

					<c:if test="${user.type == 'TUTOR'}">
						<option id="2" value="TUTOR" selected="selected">Tutor</option>
					</c:if>
					<c:if test="${user.type != 'TUTOR'}">
						<option id="2" value="TUTOR">Tutor</option>
					</c:if>

					<c:if test="${user.type == 'ADMIN'}">
						<option id="3" value="ADMIN" selected="selected">Admin</option>
					</c:if>
					<c:if test="${user.type != 'ADMIN'}">
						<option id="3" value="ADMIN">Admin</option>
					</c:if>

					<c:if test="${user.type == 'MODERATOR'}">
						<option id="4" value="MODERATOR" selected="selected">Moderator</option>
					</c:if>
					<c:if test="${user.type != 'MODERATOR'}">
						<option id="4" value="MODERATOR">Moderator</option>
					</c:if>
				</select>
				</P>
				<P> Active:
					<c:if test="${user.is_active()}">
						<input type="radio" id="isActiveTrue" name="isActive" value="1" checked="checked">
						<label for="isActiveTrue">Yes</label>
						<input type="radio" id ="isActiveFalse" name="isActive" value="0">
						<label for="isActiveFalse">No</label>
					</c:if>
					<c:if test="${!user.is_active()}">
						<input type="radio" id="isActiveTrue" name="isActive" value="1" >
						<label for="isActiveTrue">Yes</label>
						<input type="radio" id ="isActiveFalse" name="isActive" value="0" checked="checked">
						<label for="isActiveFalse">No</label>
					</c:if>
				</P>
				<P> Blocked:
					<c:if test="${user.is_blocked()}">
						<input type="radio" id="isBlockedTrue" name="isBlocked" value="1" checked="checked">
						<label for="isBlockedTrue">Yes</label>
						<input type="radio" id="isBlockedFalse" name="isBlocked" value="0">
						<label for="isBlockedFalse">No</label>
					</c:if>
					<c:if test="${!user.is_blocked()}">
						<input type="radio" id="isBlockedTrue" name="isBlocked" value="1" >
						<label for="isBlockedTrue">Yes</label>
						<input type="radio" id="isBlockedFalse" name="isBlocked" value="0" checked="checked">
						<label for="isBlockedFalse">No</label>
					</c:if>
				</P>
				<br/>${resultMessageEdit}<br/>
		</form>
	</div>
</content>

<footer>
	<div id="footer"></div>
</footer>
</body>
</html>