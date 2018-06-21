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
				<a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-success" >${button_back_to_main}</a><br><br>
				<c:if test="${(not empty sessionScope.role)and(sessionScope.role == 'admin') }">	
								<%@include file="../elements/left-bar-admin.jsp"%>
				</c:if>
			</div>
			
			
			<c:set var="userId" value="${user.id}"></c:set>
			<div class="col-lg-9">
				<%@include file="../elements/account-bar.jspf"%>
				<div class="tab-pane active">

					<div class="row">
						<h6 class="col-lg-3">${label_login}:</h6>
						<span class="col-lg-3">${user.login}</span>
					</div>
					<div class="row">
						<h6 class="col-lg-3">${label_email}:</h6>
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
						<div class="col-lg-6">
							<form action="${pageContext.request.contextPath}/Controller">
								<input type="hidden" name="command"
									value="add_discount_to_client"> <input type="hidden"
									name="userId" value="${user.id}"> <select
									name="discount_id">
									<c:forEach var="discount" items="${discountList}">
										<c:choose>
											<c:when test="${discount.id == userDiscount.id}">
												<option value="${discount.id}" selected>${discount.persent}</option>
											</c:when>
											<c:otherwise>
												<option value="${discount.id}">${discount.persent}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select> <label>%</label> <input type="submit" class="btn btn-primary"
									value="${button_change_discount}" >
							</form>
						</div>

					</div><br>
					<div class="row">
						<h6 class="col-lg-3">Status:</h6>
						<div class="col-lg-6">
							<form action="${pageContext.request.contextPath}/Controller" class="form-inline">
								<input name="command" type="hidden" value="change_user_status">
								<input name="userId" type="hidden" value="${user.id}">
								<c:choose>
									<c:when test="${user.status}">
										<p>
											<input name="user_status" type="radio" value="true" checked>
											${label_active} <br>
										</p>
										<p>
											<input name="user_status" type="radio" value="false">
											${label_blocked}<br>
										</p>
									</c:when>
									<c:otherwise>
										<p>
											<input name="user_status" type="radio" value="true">
											${label_active} <br>
										</p>
										<p>
											<input name="user_status" type="radio" value="false" checked>
											${label_blocked}<br>
										</p>
									</c:otherwise>
								</c:choose>
								 <input type="submit" class="btn btn-primary" value="${button_change_status}" >
							</form>
	
						</div>
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