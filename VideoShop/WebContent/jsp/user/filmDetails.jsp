<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
	<%@include file="../elements/nav-bar.jsp" %>
	
	<!-- Page Content -->
	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<%@include file="../elements/left-bar.jsp" %>
				<c:if test="${(not empty sessionScope.role)and(sessionScope.role == 'admin') }">	
					<%@include file="../elements/left-bar-admin.jsp" %>
				</c:if>
			</div>

			 <div class="col-lg-9" >
					<div class="row">
						<div style="width: 250px; height: 300px;">
							<img src="${film.cover}" class="img-thumbnail" />
						</div>
						
						<div class="col-md-8">
							<div style="width: 120px; float: right;">
								<span class="text-warning">&#9733; &#9733; &#9733; &#9733; &#9734;</span> ${film.rating}
							</div>
							<h4 class="card-title">${film.title}</h4>
							<h4>${film.price}$</h4>
							<p>${label_year}: ${film.releaseYear}</p>
							<p>${label_genre}:
								<c:forEach var="genre" items="${film.genreList}">
									<a href="${pageContext.request.contextPath}/Controller?command=show_films_by_genre&genreId=${genre.id}&pageNumber=1&filmsPerPage=2">${genre.name}</a>
								</c:forEach>
							</p>
							<p>${film.longDescription}</p>
							<c:choose>
								<c:when test="${(empty sessionScope.role)}">
									<a href="${pageContext.request.contextPath}/Controller?command=show_sign_in_form" class="btn btn-success" role="button">${button_add_to_cart}</a>
								</c:when>
								<c:when test="${(empty filmOrderStatus)and(sessionScope.role != 'admin')}">
									<a href="${pageContext.request.contextPath}/Controller?command=add_film_to_active_order&filmId=${film.id}" class="btn btn-success" role="button">${button_add_to_cart}</a>
								</c:when>
								<c:when test="${(filmOrderStatus == 'ACTIVE')}">
									<a href="${pageContext.request.contextPath}/Controller?command=show_active_order" class="btn btn-success" role="button">${button_go_to_cart}</a>
								</c:when>
								
								<c:when test="${(filmOrderStatus == 'PAYED')}">
									<a href="${pageContext.request.contextPath}/Controller?command=watch_film&filmId=${film.id}" class="btn btn-success" role="button">${button_watch_film}</a>
								</c:when>
							</c:choose>
							
							
							<c:if test="${(not empty sessionScope.role)and(sessionScope.role == 'admin') }">	
								<a href="${pageContext.request.contextPath}/Controller?command=load_film_on_edit_page&filmId=${film.id}" class="btn btn-info" role="button">${button_edit_film}</a>
							</c:if>
						</div>

						<div class="videoWrapper">
							<iframe width="560" height="315" src="${film.trailerURL}" frameborder="0" allow="encrypted-media" allowfullscreen></iframe>
						</div>
					</div>
					<c:if test="${(not empty sessionScope.role)and(sessionScope.role == 'client') }">	
								<%@include file="../elements/commentAndRatingForms.jsp" %>
							</c:if>
					<jsp:include page="../elements/commentsViewer.jsp"></jsp:include>
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