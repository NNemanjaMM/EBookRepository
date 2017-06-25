<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-boundary">
<h2>Create new user account</h2>
<hr/>

	<form action="/useradd" method="POST">
		<table class="manage-data table-size-small">
			<tr>
				<td>Username:</td>
				<th><input type="text" maxlength="10" name="username" id="username" tabindex="1" class="form-control" value="${user.username}" Placeholder="Enter user's username" required="required"></th>
			</tr>
			<tr>
				<td>First name:</td>
				<th><input type="text" maxlength="30" name="firstName" id="firstName" tabindex="1" class="form-control" value="${user.firstName}" Placeholder="Enter user's first name" required="required"></th>
			</tr>
			<tr>
				<td>Last name:</td>
				<th><input type="text" maxlength="30" name="lastName" id="lastName" tabindex="1" class="form-control" value="${user.lastName}" Placeholder="Enter user's last name" required="required"></th>
			</tr>
			<tr>
				<td>Role:</td>
				<th>
					<select name="type" id="type" tabindex="1" class="form-control select-user-type" required="required">
						<option value="s"
							<c:if test="${user.type == 's'}">
								selected
							</c:if>
						>Subscriber</option>		
						<option value="a"
							<c:if test="${user.type == 'a'}">
								selected
							</c:if>
						>Administrator</option>
					</select>
				</th>
			</tr>
			<tr>
				<td>Subscribed to:</td>
				<th>
					<select name="category" id="category" tabindex="1" class="form-control select-user-category" required="required">
						<option value="8888">All</option>						
						<c:forEach items="${categories}" var="cat">
							<option 
									<c:if test="${cat.id == user.category.id}">
										selected
									</c:if>
									value="${cat.id}">
								${cat.name}
							</option>
						</c:forEach>
					</select>
				</th>
			</tr>
			
			<tr>
				<td>Password:</td>
				<th><input type="password" maxlength="10" name="password" id="password" tabindex="1" class="form-control" Placeholder="Enter user's password" required="required"></th>
			</tr>
			
			<tr>
				<td>Repeat Password:</td>
				<th><input type="password" maxlength="10" name="repeatPassword" id="repeatPassword" tabindex="1" class="form-control" Placeholder="Repeat user's password" required="required"></th>
			</tr>
			
			<c:if test="${error != null}">
				<tr>
					<td colspan="2" class="error-field" role="alert">
						<div class="alert alert-danger">
							<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
							${error}
						</div>
					</td>
				</tr>	
			</c:if>	
			
			<tr><td colspan="2"><hr/></td></tr>		
			
			<tr>
				<td colspan="2" class="button-field"><input type="submit" name="manage-user-info-submit" id="manage-user-info-submit" tabindex="4" class="btn btn-default" value="Create Account"></td>
			</tr>
		</table>
	</form>
</div>