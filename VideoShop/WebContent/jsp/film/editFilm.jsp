<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>

<link href="css/bootstrap.css" rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker.standalone.min.css"
	rel="stylesheet">
<link
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
<style type="text/css">
.film-form {
	margin-top: 70px;
}
</style>

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.min.js"></script>

<script type="text/javascript">
	$(window).load(function() {
		loadFilmGenre();
	});

	function loadFilmGenre() {
		$
				.ajax({
					url : "ajaxController",
					data : {
						command : 'get_genre_list'
					},
					success : function(responseJson) {
						$
								.each(
										responseJson,
										function(index, genre) { // Iterate over the JSON array.

											var input = $("<input id="+genre.name+" name='genre' class='form-check-input' type='checkbox' value="+genre.id+" > <label for="+genre.name+" class='form-check-label' > "
													+ genre.name
													+ " </label><br>");
											input.appendTo($("#checkboxGenre"));
										});
					}
				});
	}
</script>

<%@include file="../elements/localization.jsp" %>
</head>

<body>

	<%@include file="../elements/nav-bar.jsp"%>
	<div class="container film-form">
		<div class="row">
			<div class="col-md-3">
				<a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-success" >${button_back_to_main}</a>
				<br>
				<br>
				<div class="list-group">
					<jsp:useBean id="Genre" class="by.epam.vshop.bean.Genre"></jsp:useBean>
					<h3 style="text-align: center">${admin_menu}</h3>

					<div class="btn-group-vertical">
						<a
							href="${pageContext.request.contextPath}/Controller?command=user_list&pageNumber=1"
							class="btn border list-group-item">${button_user_list}</a><br> 
						<a
							href="${pageContext.request.contextPath}/addFilm"
							class="btn border list-group-item">${button_add_film}</a><br>
					</div>
				</div>
			</div>
			<div class="col-md-6">

				<form class="well form-horizontal" action="Controller" method="post">
			<input type="hidden" name="command"  value="edit_film">
			<input type="hidden" name="filmId"  value="${requestScope.film.id}">
			
			<fieldset>
				<legend>Edit film</legend>


		<div style="color: red">
			<c:if test="${param.errorMassage == 'editFilmError'}">edit film was failed</c:if>
		</div>

<!-- Title input-->
				<div class="form-group">
					<label class="col-md-8 control-label">Title*</label>
					<div class="col-md-8">
						<div class="input-group">
							<input name="film_title" placeholder="Title" class="form-control" type="text" value="${requestScope.film.title}" />
						</div>
					</div>
				</div>

<!-- Film logo input-->
				<div class="form-group">
					<label class="col-md-8 control-label">Film logo</label>
					<div class="col-md-8">
						<div class="input-group">
							<input name="film_cover" placeholder="Film logo url" class="form-control" type="text" value="${requestScope.film.cover}"  />
						</div>
					</div>
				</div>

<!-- Film url input-->
				<div class="form-group">
					<label class="col-md-8 control-label">Film url</label>
					<div class="col-md-8">
						<div class="input-group">
							<input name="film_url" placeholder="Film url" class="form-control" type="text" value="${requestScope.film.URL}" />
						</div>
					</div>
				</div>

<!-- Trailer url input-->
				<div class="form-group">
					<label class="col-md-8 control-label">Trailer url</label>
					<div class="col-md-8">
						<div class="input-group">
							<input name="film_trailer_url" placeholder="Trailer url" class="form-control" type="text" value="${requestScope.film.trailerURL}"  />
						</div>
					</div>
				</div>

<!-- Year input-->
				<div class="form-group">
					<label class="col-md-8 control-label">Year</label>
					<div class="col-md-8">
						<div class="input-group"> 
							<input name="film_year" class="date-own form-control" type="text" placeholder="Year" value="${requestScope.film.releaseYear}" />
						</div>
					</div>
				</div>

<!-- price input-->
				<div class="form-group">
					<label class="col-md-8 control-label">Price*</label>
					<div class="col-md-8">
						<div class="input-group">
							<input name="film_price" class="form-control"  type="number" placeholder="Price" value="${requestScope.film.price}" />
						</div>
					</div>
				</div>	  
	  
	  	<script type="text/javascript">
			$('.date-own').datepicker({
	      		minViewMode: 2,
	         	format: 'yyyy'
	       	});
  		</script>  

<!-- Text area short description -->
				<div class="form-group">
					<label class="col-md-8 control-label">Short Description</label>
					<div class="col-md-8">
						<div class="input-group">
							<textarea class="form-control" name="film_short_description" placeholder="Short Description" rows="10">${requestScope.film.shortDescription}</textarea>
						</div>
					</div>
				</div>

<!-- Text area long description -->
				<div class="form-group">
					<label class="col-md-8 control-label">Long Description</label>
					<div class="col-md-8">
						<div class="input-group">
							<textarea class="form-control" name="film_long_description" placeholder="Long Description" rows="10">${requestScope.film.longDescription}</textarea>
						</div>
					</div>
				</div>


<!-- Text area long description -->
				<div class="form-group">
					<label class="col-md-8 control-label">Genres</label>
					<div class="col-md-8">
						<div class="input-group">
							<div id="checkboxGenre" class="form-check"></div>
						</div>
					</div>
				</div>

<!-- Button -->
				<div class="form-group">
					<label class="col-md-8 control-label"></label>
					<div class="col-md-8">
						<button type="submit" class="btn btn-info">Edit film<span class="glyphicon glyphicon-send"></span>
						</button>
					</div>
				</div>

			</fieldset>
		</form>
			</div>
			<div class="col-md-3"></div>
		</div>
	</div>


	------


</body>

</html>
