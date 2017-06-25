<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-boundary">
<h2>Update your info</h2>
<hr/>

	<form action="/accountinfo" method="POST">
		<table class="manage-data table-size-small">
			<tr>
				<td>Username:</td>
				<th><input type="text" name="username" id="username" tabindex="1" class="form-control" value="${sessionUser.username}" Placeholder="Enter your username" required="required"></th>
			</tr>
			<tr>
				<td>First name:</td>
				<th><input type="text" name="firstName" id="firstname" tabindex="1" class="form-control" value="${sessionUser.firstName}" Placeholder="Enter your first name" required="required"></th>
			</tr>
			<tr>
				<td>Last name:</td>
				<th><input type="text" name="lastName" id="lastname" tabindex="1" class="form-control" value="${sessionUser.lastName}" Placeholder="Enter your last name" required="required"></th>
			</tr>
			<tr>
				<td>Subscribed to:</td>
				<th>
					<select name="category" id="category" tabindex="1" class="form-control" required="required">
						<option value="8888">All</option>						
						<c:forEach items="${categories}" var="cat">
							<option 
									<c:if test="${cat.id == sessionUser.category.id}">
										selected
									</c:if>
									value="${cat.id}">
								${cat.name}
							</option>
						</c:forEach>
					</select>
					<input type="text" name="type" id="type" tabindex="1" required="required" value="${sessionUser.type}" style="display:none">
					<input type="password" name="password" id="password" tabindex="1" required="required" value="${sessionUser.password}" style="display:none">
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
				<td colspan="2" class="button-field"><input type="submit" name="manage-user-info-submit" id="manage-user-info-submit" tabindex="4" class="btn btn-default" value="Update your Account"></td>
			</tr>
		</table>
	</form>
</div>

<div class="form-boundary">
<h2>Change your password</h2>
<hr/>

	<form action="/accountpassword" method="POST">
		<table class="manage-data table-size-small">
			<tr>
				<td>Old Password:</td>
				<th><input type="password" name="oldPassword" id="oldPassword" tabindex="1" class="form-control" Placeholder="Enter your old password" required="required"></th>
			</tr>
			
			<tr>
				<td>New Password:</td>
				<th><input type="password" name="newPassword" id="newPassword" tabindex="1" class="form-control" Placeholder="Enter your new password" required="required"></th>
			</tr>
			
			<tr>
				<td>Repeat Password:</td>
				<th><input type="password" name="repeatPassword" id="repeatPassword" tabindex="1" class="form-control" Placeholder="Repeat your new password" required="required"></th>
			</tr>
			
			<c:if test="${error2 != null}">
				<tr>
					<td colspan="2" class="error-field" role="alert">
						<div class="alert alert-danger">
							<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
							${error2}
						</div>
					</td>
				</tr>	
			</c:if>
			
			<tr><td colspan="2"><hr/></td></tr>			
			
			<tr>
				<td colspan="2" class="button-field"><input type="submit" name="manage-user-info-submit" id="manage-user-info-submit" tabindex="4" class="btn btn-default" value="Update your Password"></td>
			</tr>
		</table>
	</form>
</div>