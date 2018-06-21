<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib  uri="/WEB-INF/tld/pagination.tld" prefix="p"%>
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
	<%@include file="../elements/nav-bar.jsp" %>

	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<%@include file="../elements/left-bar.jsp" %>
			</div>
	
			<div class="col-lg-9">
			<h5>My films</h5>
				<%@include file="../elements/filmsViewer.jsp" %>
				<div class="pagationContaner">
					<p:pagination currentPageNumber="${currentPageNumber}" maxPageNumber="${maxPageNumber}" paginationLink="${pageContext.request.contextPath}/Controller?command=show_client_film_list" />
				</div>
			</div>
		</div>

	</div>

	<!-- Footer -->
	<%@include file="../elements/footer.jsp" %>

	<!-- Bootstrap core JavaScript -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.bundle.min.js"></script>

</body>

</html>