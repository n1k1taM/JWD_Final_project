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
				
			
				<c:if test="${(not empty sessionScope.role)and(sessionScope.role == 'admin') }">	
								<%@include file="../elements/left-bar-admin.jsp"%>
				</c:if>
				
			</div>
			
			
			<c:set var="userId" value="${user.id}"></c:set>
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
						<div class="col-lg-6">
							<form action="${pageContext.request.contextPath}/Controller">
								<input type="hidden" name="command"
									value="add_discount_to_client"> <input type="hidden"
									name="userId" value="${user.id}"> <select
									name="discount_id">
									<c:forEach var="discount" items="${discountList}">
										<c:choose>
											<c:when test="${discount.id == userDiscount.id}">
												<option value="${discount.id}" selected>${discount.persеnt}</option>
											</c:when>
											<c:otherwise>
												<option value="${discount.id}">${discount.persеnt}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select> <label>%</label> <input type="submit" class="btn btn-primary"
									value="Change discount" >
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
											active <br>
										</p>
										<p>
											<input name="user_status" type="radio" value="false">
											blocked<br>
										</p>
									</c:when>
									<c:otherwise>
										<p>
											<input name="user_status" type="radio" value="true">
											active <br>
										</p>
										<p>
											<input name="user_status" type="radio" value="false" checked>
											blocked<br>
										</p>
									</c:otherwise>
								</c:choose>
								<!-- 
								<p>
									<input name="user_status" type="radio" value="true"> Не
									дзен
								</p>
								<p>
									<input name="user_status" type="radio" value="false">
									Дзен
								</p>

								
								 -->
								 <input type="submit" class="btn btn-primary" value="Change status" >
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