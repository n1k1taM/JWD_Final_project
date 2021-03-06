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


</head>

<body>
	<%@include file="../elements/nav-bar.jsp"%>

	<!-- Page Content -->
	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-success" >${button_back_to_main}</a>
			</div>
			<div class="col-lg-9">
				<div class="row">
					<h6 class="col-lg-3">${label_cost}</h6>
					<span class="col-lg-3">$ ${costAfterDiscount}</span>
				</div>
				<div class="row">

					<label class="col-lg-3 col-form-label form-control-label">${label_enter}
						${costAfterDiscount}</label>
					<form action="${pageContext.request.contextPath}/Controller"
						class="col-lg-6">
						<div class="row">
							<div class="col-lg-6">
								<input type="text" class="form-control" name="payment_value">
								<input type="hidden" name="command" value="pay_order">
							</div>

							<div class="col-lg-6">
								<button type="submit">${button_pay_order}</button>
							</div>
						</div>
					</form>
				</div>
				<br>
				<br>
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