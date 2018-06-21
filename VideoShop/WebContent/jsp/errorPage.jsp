<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<body>
	<div class="wrapper container">
		<div class="heading">
			<h1>${error_label} ${pageContext.errorData.statusCode}</h1>
		</div>
		
		<h3>
			<c:choose>
	    		<c:when test="${pageContext.errorData.statusCode == 404}">
	       			Страница не найдена
	    		</c:when>
	    		<c:when test="${pageContext.errorData.statusCode == 403}">
	       			Доступ запрещен
	       		</c:when>
	    		<c:when test="${pageContext.errorData.statusCode == 414}">
	       			Превышен допустимый размер адреса
	       		</c:when>
	    		<c:when test="${pageContext.errorData.statusCode == 504}">
	       			Превышено время ожидания
	       		</c:when>
	       		<c:when test="${pageContext.errorData.statusCode == 500}">
	       			Внутренняя ошибка сервера
	       		</c:when>
	    		<c:otherwise>
	    			Unknown error
	    		</c:otherwise>
			</c:choose>
		</h3>
	</div>
</body>
</html>