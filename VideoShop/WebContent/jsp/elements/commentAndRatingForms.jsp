<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>



	 
	
	<div class="container">
		<div class="row">
			<div class="col-md-12">
			<br>
			<form action="${pageContext.request.contextPath}/Controller">
					<input type="hidden" name="command" value="add_rating"> 
					<input type="hidden" name="filmId" value="${film.id}">
					<select name="film_rating">
						<option>1</option>
						<option>1.5</option>
						<option>2</option>
						<option>2.5</option>
						<option>3</option>
						<option>3.5</option>
						<option>4</option>
						<option>4.5</option>
						<option>5</option>
					</select>	
					<button class="btn btn-danger btn-sm" type="submit">${button_add_rating} </button>
				</form>
			
			<form action="${pageContext.request.contextPath}/Controller">
					<input type="hidden" name="command" value="add_comment"> 
					<input type="hidden" name="filmId" value="${film.id}">
					<textarea class="col-sm-10" rows="8"  name="comment_message"></textarea><br>	
					<button class="btn btn-danger btn-sm" type="submit">${button_add_comment}</button>
				</form>
			</div>
		</div>
	</div>
	


</html>
