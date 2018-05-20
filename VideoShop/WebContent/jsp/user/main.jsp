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

</head>

<body>
	<%@include file="../elements/nav-bar.jsp" %>

	<!-- Page Content -->
	<c:set var="paginationLink" value="${pageContext.request.contextPath}/Controller?command=show_main_page"></c:set>
	<div class="container">
	
		<div class="row">
			<div class="col-lg-3">
				<jsp:include page="../elements/left-bar.jsp"/>
				<c:if test="${(not empty sessionScope.role)and(sessionScope.role == 'admin') }">	
					<%@include file="../elements/left-bar-admin.jsp" %>
				</c:if>
			</div>
			
			<!-- /.col-lg-3 -->
			<div class="col-lg-9">
				<jsp:include page="../elements/filmsViewer.jsp"></jsp:include>
				<div class="row"></div>
				<div class="col-lg-4"></div>
				
				<div class="pagationContaner col-lg-4">
					<p:pagination currentPageNumber="${currentPageNumber}" maxPageNumber="${maxPageNumber}" paginationLink="${pageContext.request.contextPath}/Controller?command=show_main_page" />
				</div>
				<div class="col-lg-4"></div>
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
