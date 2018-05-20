<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<div class="list-group">
	<jsp:useBean id="Genre" class="by.epam.vshop.bean.Genre"></jsp:useBean>
	<h3 style="text-align: center" >Admin Menu</h3>
	
	<div class="btn-group-vertical">
		<a href="${pageContext.request.contextPath}/Controller?command=user_list&pageNumber=1" class="btn border list-group-item">User list</a><br>
		<a href="${pageContext.request.contextPath}/addFilm" class="btn border list-group-item">Add Film</a><br>
	</div>	
</div>

</html>