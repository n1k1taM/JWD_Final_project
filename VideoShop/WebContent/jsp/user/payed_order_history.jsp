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
<%@include file="../elements/localization.jsp" %>

</head>

<body>
	<%@include file="../elements/nav-bar.jsp"%>
	

	<div class="container">
		<div class="row">
			<div class="col-lg-3">
			
				<a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-success" >${button_back_to_main}</a>
				<br>
				<br>
				
			
				<c:if test="${(not empty sessionScope.role)and(sessionScope.role == 'admin') }">	
						<%@include file="../elements/left-bar-admin.jsp"%>
				</c:if>
			</div>

			<div class="col-lg-9">
				<%@include file="../elements/account-bar.jspf"%>
				<div class="container">
		<table class="table table-hover table-dark">
			<thead class="thead-dark">
				<tr>
					<th scope="col">#</th>
					<th scope="col">Creation date</th>
					<th scope="col">Total price</th>
					<th scope="col">Status</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.order_list}" var="list">
					<tr>
						<th scope="row">${list.id}</th>
						<td>${list.date}</td>
						<td>${list.cost}</td>
						<td>${list.status}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	

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
