<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="list-group">
	<jsp:useBean id="Genre" class="by.epam.vshop.bean.Genre"></jsp:useBean>
	<h3 style="text-align: center" >${label_genres}</h3>
	
	<div class="btn-group-vertical">

		<c:forEach var="genre" items="${genreList}">
			<c:choose>
				<c:when test="${genre.id==currentGenreId}">
					<a href="${pageContext.request.contextPath}/Controller?command=show_films_by_genre&genreId=${genre.id}&pageNumber=1" class="btn border list-group-item active">${genre.name}</a>				
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/Controller?command=show_films_by_genre&genreId=${genre.id}&pageNumber=1" class="btn border">${genre.name}</a>
				</c:otherwise>
			</c:choose>
			<br>
		</c:forEach>
	</div>	
</div>
