<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<jsp:useBean id="genre" class="by.epam.vshop.bean.Film" />

<div class="container">
	<c:forEach var="film" items="${filmList}">

		<div class="row rounded border" style="padding: 5px;" >
			<div style="width: 250px; height: 300px;">
				<img src="${film.cover}" class="img-thumbnail" />				
			</div>

			<div class="col-sm-8" style="padding-left: 30px;">
				<div class="row"><h3>${film.title}</h3></div>
				<div class="row"><span class="text-warning">&#9733; &#9733; &#9733; &#9733; &#9734;</span>&nbsp;&nbsp; ${film.rating}</div>
				<div class="row"><h4>${film.price}$</h4></div>
				<div class="row"><p class="card-text">${label_year}: ${film.releaseYear}</p></div>
				<div class="row"> ${label_genre}:
					<c:forEach var="genre" items="${film.genreList}">
						&nbsp;<a href="${pageContext.request.contextPath}/Controller?command=show_films_by_genre&genreId=${genre.id}&pageNumber=1&filmsPerPage=2">${genre.name}</a>
					</c:forEach>
				</div>
				<br>
				<div class="row"><span>${film.shortDescription}</span></div>
				<br>
				<div class="row"><a href="${pageContext.request.contextPath}/Controller?command=show_film&filmId=${film.id}&commentPageNumber=1" class="btn btn-info" role="button">${button_more}</a></div>
				<br>
			</div>
		</div>
		<br>
	</c:forEach>
</div>
</html>