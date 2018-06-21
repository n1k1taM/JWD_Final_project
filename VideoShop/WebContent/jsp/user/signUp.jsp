<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
<link rel="stylesheet" href="css/signup_page.css">
<link href='https://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
<%@include file="../elements/localization.jsp" %>
</head>

<body>
	<%@include file="../elements/nav-bar.jsp" %>
	
	<div class="wrapper">
		<h1 style="color: white;">Register For An Account</h1>
		

		<div style="color: red">
			<c:if test="${param.errorMassage == 'singupError'}">Registration was failed</c:if>
		</div>
		<br>

		<form class="form" method="post" action="Controller">
			<input type="hidden" name="command" value="sign_up"> 
			<input type="text" class="login" placeholder="login" name="login">
			<p class="login-help">Please enter login.</p>

			<input type="text" class="firstname" placeholder="First name" name="firstname">
			<p class="firstname-help">Please enter your first name.</p>

			<input type="text" class="lastname" placeholder="Last name" name="lastname">
			<p class="lastname-help">Please enter your last name.</p>

			<input type="email" class="email" placeholder="Email" name="email">
			<p class="email-help">Please enter your current email address.</p>

			<input type="text" class="password" placeholder="Password" name="password">
			<p class="password-help">Please enter your password.</p>

			<input type="text" class="password" placeholder="Confirm password" name="confirm_password">
			<p class="password-help">Please enter your confirm password.</p>

			<input type="submit" class="submit" value="Register">
		</form>
		<br><br>
		<br><br>
		<br><br>
		
	</div>


	<script src="js/jquery.min.js"></script>
	<script src="js/signup_page.js"></script>
</body>
</html>