<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-boundary">
<h2>Update user account</h2>
<hr/>
	<form action="/userupdate" method="POST">
		<table class="manage-data table-size-small">
			<tr>
				<td>Username:</td>
				<th><input type="text" maxlength="10" name="username" readonly="readonly" id="username" tabindex="1" class="form-control" placeholder="Username" value="${user.username}" required="required"></th>
			</tr>
			<tr>
				<td>First name:</td>
				<th><input type="text" maxlength="30" name="firstName" readonly="readonly" id="firstName" tabindex="1" class="form-control" placeholder="First Name" value="${user.firstName}" required="required"></th>
			</tr>
			<tr>
				<td>Last name:</td>
				<th><input type="text" maxlength="30" name="lastName" readonly="readonly" id="lastName" tabindex="1" class="form-control" placeholder="Last Name" value="${user.lastName}" required="required"></th>
			</tr>
			<tr>
				<td>Role:</td>
				<th>
					<select name="type" id="type" tabindex="1" class="form-control select-user-type" required="required">
						<option 						
							<c:if test="${user.type == 's'}">
								selected
							</c:if>
						value="s">Subscriber</option>		
						<option 				
							<c:if test="${user.type == 'a'}">
								selected
							</c:if>
						value="a">Administrator</option>
					</select>
					<input type="password" name="id" id="id" class="form-control" value="${user.id}" required="required" style="display: none;">
					<input type="password" maxlength="10" name="password" id="password" class="form-control" value="${user.password}" required="required" style="display: none;">
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
				<td colspan="2" class="button-field"><input type="submit" name="manage-user-role-submit" id="manage-user-role-submit" tabindex="4" class="btn btn-default" value="Update User Account"></td>
			</tr>
		</table>
	</form>

</div>