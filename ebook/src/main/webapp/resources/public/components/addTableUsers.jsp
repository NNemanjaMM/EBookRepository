<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<br/><br/>
<a class="create-new-block" href="/useradd">
	Create New User Account
</a>
<br/><br/>

<table class="tableUsers">
	<c:if test="${error != null}">
		<tr>
			<td colspan="5" class="error-field-top" role="alert">
				<div class="alert alert-danger">
					<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
					${error}
				</div>
			</td>
		</tr>	
	</c:if>

	<c:forEach items="${users}" var="user" varStatus="i">
	
		<tr>
			<td><i>${i.index+1}</i></td>
			<th class="info">${user.firstName} ${user.lastName} (${user.username})</th>
			<td class="account"><i>Type:&nbsp;</i></td>
			<td>
				<c:if test="${user.type == 's'}">
					Subscriber
				</c:if>
				<c:if test="${user.type == 'a'}">
					Administrator
				</c:if>
			</td>
			<td rowspan="2" class="add">
				<a href="/userupdate?userId=${user.id}">
					<button class="btn btn-default">Update</button>
				</a>
				<form action="/userdelete" method="post">
					<input name="userId" value="${user.id}" style="display: none;" />
					<input type="submit" class="btn btn-default" Value="Delete"/>
				</form>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td><i>Category:&nbsp;</i></td>
			<td>
				<c:if test="${user.category == null}">
					All
				</c:if>
				<c:if test="${user.category != null}">
					${user.category.name}
				</c:if>
			</td>
		</tr>
		
		<tr><td>&nbsp;</td></tr>
	</c:forEach>	
	
</table>