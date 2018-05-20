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
.videoWrapper {
	width: 100%;
	height: 0;
	padding-bottom: 56.25%;
	position: relative;
}

.videoWrapper iframe {
	position: absolute;
	width: 100%;
	height: 100%;
}
</style>
</head>

<body>
	<%@include file="../elements/nav-bar.jsp"%>

	<!-- Page Content -->
	<div class="container">
		<div class="row">
			<div class="col-lg-3"></div>

			<div class="col-lg-9">
				<br><br><br><br>
				<h2>Эта страница должна содержать проигровать фильма ${film.title}</h2>
				<br><br><br><br>
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