<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<br/><br/>
<a class="create-new-block" href="/categoryadd">
	Create New Category
</a>
<br/><br/>

<table class="tableCategories">
	<c:if test="${error != null}">
		<tr>
			<td colspan="3" class="error-field-top" role="alert">
				<div class="alert alert-danger">
					<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
					${error}
				</div>
			</td>
		</tr>	
	</c:if>

	<c:forEach items="${categories}" var="cat" varStatus="i">

		<tr>
			<td><i>${i.index+1}</i></td>
			<th class="info">${cat.name}</th>
			<td class="add">
				<a href="/categoryupdate?categoryId=${cat.id}">
					<button class="btn btn-default">Update</button>
				</a>
				<form action="/categorydelete" method="post">
					<input name="categoryId" value="${cat.id}" style="display: none;" />
					<input type="submit" class="btn btn-default" Value="Delete"/>
				</form>
			</td>
		</tr>	
		<tr><td>&nbsp;</td></tr>
	
	</c:forEach>
</table>