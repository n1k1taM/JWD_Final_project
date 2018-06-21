<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>

<head>
	<!-- Bootstrap core CSS -->
	<link href="css/bootstrap.css" rel="stylesheet">
	
	<!-- Custom styles for this template -->
	<link href="css/shop-item.css" rel="stylesheet">

	<title>Vshop</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>


<div class="jumbotron jumbotron-fluid" style="background-image:url('https://images.wallpaperscraft.com/image/lines_spots_light_30501_1366x768.jpg') ; background-repeat:round;">
  <div class="container">
    <h1 class="display-4" style="color: white;" >Video shop</h1>
    <p class="lead" style="color: white;" >Можно смотреть, а можно видеть.</p>
  </div>
</div>

<div>

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="index.jsp">VShop</a>
			
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<c:if test="${(not empty sessionScope.role) and (sessionScope.role == 'client')}">
						<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/Controller?command=show_active_order">${button_cart}</a></li>
						<li class="nav-item" ><a class="nav-link" href="${pageContext.request.contextPath}/Controller?command=show_client_film_list&pageNumber=1">${button_my_films}</a></li>
						<li class="nav-item" ><a class="nav-link" href="${pageContext.request.contextPath}/Controller?command=show_user_account">${button_account}</a></li>
					</c:if>
					<li class="nav-item"><c:choose>
							<c:when test="${sessionScope.login != null}">
							<a class="nav-link" href="${pageContext.request.contextPath}/Controller?command=logout">${button_logout}</a>
							</c:when>
							<c:otherwise>
								
								<a class="nav-link" href="${pageContext.request.contextPath}/Controller?command=show_sign_in_form">${button_sign_in}</a>
							</c:otherwise>
						</c:choose>
					</li>
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/Controller?command=localization&local=en">${en_button}</a></li>
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/Controller?command=localization&local=ru">${ru_button}</a></li>										
				</ul>
			</div>
		</div>
	</nav>
</div>

</html>
