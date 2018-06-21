<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<div class="list-group">
	<h3 style="text-align: center" >${admin_menu}</h3>
	
	<div class="btn-group-vertical">
		<a href="${pageContext.request.contextPath}/Controller?command=user_list&pageNumber=1" class="btn border list-group-item">${button_user_list}</a><br>
		<a href="${pageContext.request.contextPath}/Controller?command=show_add_film_form" class="btn border list-group-item">${button_add_film}</a><br>
	</div>	
</div>

</html>