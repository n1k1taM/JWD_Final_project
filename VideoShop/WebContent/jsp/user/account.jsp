<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/pagination.tld" prefix="p"%>
<!DOCTYPE html>
<html>

<head>
<!-- Bootstrap core CSS -->
<link href="css/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/shop-item.css" rel="stylesheet">
<link href="css/pagination.css" rel="stylesheet">

<style>
</style>
</head>

<body>
	<%@include file="../elements/nav-bar.jsp"%>

	<!-- Page Content -->
	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-success" >Back to films</a>
				<br>
				<br>
			</div>

			<div class="col-lg-9">
				<%@include file="../elements/account-bar.jspf"%>
				<!--  
				<div class="row">
					<ul class="nav nav-tabs">
						<c:if test="${account_command == 'show_profile'}"></c:if>
						<li class="nav-item"><a href="profile.html" class="nav-link">Profile</a>
						</li>
						<li class="nav-item"><a href="message.html" class="nav-link">Messages</a>
						</li>
						<li class="nav-item"><a href="edit.html"
							class="nav-link active">Edit</a></li>
					</ul>
				</div>
				-->
				<div class="tab-pane active">

					<div class="row">
						<h6 class="col-lg-3">Login:</h6>
						<span class="col-lg-3">${user.login}</span>
					</div>
					<div class="row">
						<h6 class="col-lg-3">Email:</h6>
						<span class="col-lg-3">${user.email}</span>
					</div>
					<div class="row">
						<h6 class="col-lg-3">First name:</h6>
						<span class="col-lg-3">${user.login}</span>
					</div>
					<div class="row">
						<h6 class="col-lg-3">Last name:</h6>
						<span class="col-lg-3">${user.lastName}</span>
					</div>
					<div class="row">
						<h6 class="col-lg-3">Discount:</h6>
						<span class="col-lg-3">${user.discount} %</span>
					</div>

				</div>

			</div>
		</div>
	</div>


	<!-- Footer -->
	<%@include file="../elements/footer.jsp"%>


	<!-- Bootstrap core JavaScript -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.bundle.min.js"></script>

</body>

</html>