<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All users</title>
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
				<b>ALL USERS</b>
			</h1>
		</div>

		<aside>
			<nav>
				<ul class="aside-menu">
					<li><a href="/nis?command=gotoAdminProfile">PROFILE</a></li>
					<li class="active">ALL USERS</li>
					<li><a href="/nis?command=gotoCreateUser">CREATE NEW USER</a></li>
					<li><a href="/nis?command=gotoAdminFindUser">FIND USER</a></li>
					<li><a href="/nis?command=logout">LOGOUT</a></li>
				</ul>
			</nav>
		</aside>



		<content>
		<div id="allUsers">
		<form name="AllUsers" method="get" action="nis">
			<table>
				<tr>
					<th>ID</th>
					<th>Login</th>
					<th>Password</th>
					<th>Type</th>
					<th>Name</th>
					<th>Authorized</th>
					<th>Blocked</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>

				<c:forEach items="${usersList}" var="user" varStatus="loop">
					<tr>
						<td><c:out value="${user.id}" /></td>
						<td><c:out value="${user.login}" /></td>
						<td><c:out value="${user.password}" /></td>
						<td><c:out value="${user.type}" /></td>
						<td><c:out value="${user.name}" /></td>
						<td>
							<c:if test="${user.is_active()}">
								Authorized
							</c:if>
							<c:if test="${!user.is_active()}">
								Not Authorized
							</c:if>
						</td>
						<td>
							<c:if test="${!user.is_blocked()}">
								Active
							</c:if>
							<c:if test="${user.is_blocked()}">
								Blocked
							</c:if>
						</td>
						<td><form method="post" action="nis">
							<input type="hidden" name="command" value="FINDUSER">
							<input type="hidden" name="usertype" value="admin">
							<input type="hidden" name="txtSearch" value=${user.login}>
							<input type="submit" value="Edit">
						</form> </td>
						<td><form method="delete" action="nis">
							<input type="hidden" name="command" value="deleteUser">
							<input type="hidden" name="idDelete" value="${user.id}">
							<input type="submit" value="Delete">
						</form> </td>
					</tr>
				</c:forEach>

			</table>

		</form>
		</div>
		</content>
		
		<footer>
		<div id="footer"></div>
	</footer>

</body>
</html>