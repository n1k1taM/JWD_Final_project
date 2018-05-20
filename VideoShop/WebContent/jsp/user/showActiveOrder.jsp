<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/pagination.tld" prefix="p"%>
<!DOCTYPE html>
<html>

<head>

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/css/shopping-cart.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/pagination.css"
	rel="stylesheet">
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">
</head>
<body>
	<%@include file="../elements/nav-bar.jsp"%>

	<div class="container">
		<table id="cart" class="table table-hover table-condensed">
			<thead>
				<tr>
					<th style="width: 58%">Film</th>
					<th style="width: 10%"></th>
					<th style="width: 22%" class="text-center">Price</th>
					<th style="width: 10%"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="film" items="${order.films}">
					<tr>
					<td data-th="Product">
						<!-- foreach -->
						<div class="row">
							<div class="col-sm-2 hidden-xs">
								<img src="${film.cover}" class="img-responsive"
									style="width: 100px; height: 100px" />
							</div>
							<div class="col-sm-10" style="padding-left: 50px;">
								<h4 class="nomargin">${film.title}</h4>
								<p>${film.shortDescription}</p>
							</div>
						</div>



					</td>
					<td class="text-center"></td>
					<td data-th="Price" class="text-center">$ ${film.price}</td>
					<td class="actions" data-th="">
						<form action="${pageContext.request.contextPath}/Controller">
						<input type="hidden" name="command" value="delete_film_from_active_order">
						<input type="hidden" name="filmId" value="${film.id}">
							<button class="btn btn-danger btn-sm" type="submit">
							<i class="fa fa-trash-o"></i>
						</button>
						</form>
					</td>
				</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td></td>
					<td>Cost</td>
					<td class="text-center">${costBeforeDescount}</td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td>Discount</td>
					<td class="text-center">${discount}</td>
					<td></td>
				</tr>
				<tr>
					<td><a href="#" class="btn btn-warning"><i
							class="fa fa-angle-left"></i> Continue Shopping</a></td>
					<td colspan="1" class="hidden-xs">Cost atfer discount</td>
					<td class="hidden-xs text-center"><strong>$ ${costAfterDiscount}</strong></td>
					<td><a href="${pageContext.request.contextPath}/Controller?command=show_payment_page" class="btn btn-success btn-block">Checkout
							<i class="fa fa-angle-right"></i>
					</a></td>
				</tr>
			</tfoot>
		</table>
	</div>
</body>
</html>