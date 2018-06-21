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
<%@include file="../elements/localization.jsp" %>

<style>
</style>
</head>

<body>
	<%@include file="../elements/nav-bar.jsp"%>

	<!-- Page Content -->
	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-success" >${button_back_to_main}</a>
				<br>
				<br>
			</div>

			<div class="col-lg-9">
				<%@include file="../elements/account-bar.jspf"%>
				<div class="tab-pane active">

					<div class="row">
						<h6 class="col-lg-3">${label_login}:</h6>
						<span class="col-lg-3">${user.login}</span>
					</div>
					<div class="row">
						<h6 class="col-lg-3">${label_password}:</h6>
						<span class="col-lg-3">${user.email}</span>
					</div>
					<div class="row">
						<h6 class="col-lg-3">${label_first_name}:</h6>
						<span class="col-lg-3">${user.login}</span>
					</div>
					<div class="row">
						<h6 class="col-lg-3">${label_last_name}:</h6>
						<span class="col-lg-3">${user.lastName}</span>
					</div>
					<div class="row">
						<h6 class="col-lg-3">${label_discount}:</h6>
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