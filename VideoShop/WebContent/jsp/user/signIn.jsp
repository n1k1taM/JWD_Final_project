<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="css/signup_page.css">
<link href='https://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>

</head>
<body>
	<%@include file="../elements/nav-bar.jsp" %>
	<div class="wrapper">
		<h1 style="color: white;">Sign in</h1>

		<div style="color: red">
			<c:if test="${param.errorMassage == 'singinError'}">Invalid login or password. </c:if>
			<c:if test="${param.errorMassage == 'blockedAccount'}">Account was blocked </c:if>
		</div>
		<br>

		<form class="form" method="post" action="Controller">
			<input type="hidden" name="command" value="sing_in">
			
			<input type="text" class="login" placeholder="login" name="login">
			<p class="login-help">Please enter login.</p>

			<input type="text" class="password" placeholder="Password" name="password">
			<p class="password-help">Please enter your password.</p>
			
			<input type="submit" class="submit" value="Sign in">
			<div class="login-register"><h5><a href="signUp">Registration</a></h5></div>
		</form>
	</div>
		<br><br>
		<br><br>
		<br><br>

	<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
	<script src="js/signup_page.js"></script>

</body>
</html>