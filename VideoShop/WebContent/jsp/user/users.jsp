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
			<div class="list-group">
					<jsp:useBean id="Genre" class="by.epam.vshop.bean.Genre"></jsp:useBean>
					<h3 style="text-align: center">${admin_menu}</h3>

					<div class="btn-group-vertical">
						<a
							href="${pageContext.request.contextPath}/Controller?command=user_list&pageNumber=1"
							class="btn border list-group-item active">${button_user_list}</a><br> 
						<a
							href="${pageContext.request.contextPath}/Controller?command=show_add_film_form"
							class="btn border list-group-item">${button_add_film}</a><br>
					</div>
				</div>
		</div>

		<!-- /.col-lg-3 -->
		<div class="col-lg-9">
			
		
	
			<table class="table">
			<thead class="thead-dark">
				<tr>
					<th scope="col">#</th>
					<th scope="col">${label_login}</th>
					<th scope="col">${label_fullName}</th>
					<th scope="col">${label_email}</th>
					<th scope="col">${label_status}</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.user_list}" var="list">
					<tr>
						<th scope="row">${list.id}</th>
						<td><a href="${pageContext.request.contextPath}/Controller?command=show_user_account_for_admin&userId=${list.id}">${list.login}</a></td>
						<td>${list.firstName} ${list.lastName}</td>
						<td>${list.email}</td>
						<td>
							<c:choose>
								<c:when test="${list.status}">
									<span class="text-success">${label_active}</span>
								</c:when>
								<c:otherwise>
									<span class="text-danger">${label_blocked}</span>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
		</div>
	</div>
	

	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	

	<!-- Footer -->
	<%@include file="../elements/footer.jsp"%>

	<!-- Bootstrap core JavaScript -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.bundle.min.js"></script>
</body>

</html>
