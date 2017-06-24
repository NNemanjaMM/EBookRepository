<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-boundary">
<h2>Update your info</h2>
<hr/>

	<form action="/accountinfo" method="POST">
		<table class="manage-data table-size-small">
			<tr>
				<td>Username:</td>
				<th><input type="text" name="username" id="username" tabindex="1" class="form-control" value="micha12" Placeholder="Enter your username" required="required"></th>
			</tr>
			<tr>
				<td>First name:</td>
				<th><input type="text" name="firstname" id="firstname" tabindex="1" class="form-control" value="Michael" Placeholder="Enter your first name" required="required"></th>
			</tr>
			<tr>
				<td>Last name:</td>
				<th><input type="text" name="lastname" id="lastname" tabindex="1" class="form-control" value="Grant" Placeholder="Enter your last name" required="required"></th>
			</tr>
			<tr>
				<td>Subscribed to:</td>
				<th>
					<select name="category" id="category" tabindex="1" class="form-control" required="required">
						<option value="8888">All</option>
						<option value="1">History</option>
						<option value="2">Novel</option>
					</select>
				</th>
			</tr>
			
			<tr style="display:none;">
				<td colspan="2" class="error-field" role="alert">
					<div class="alert alert-danger">
						<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
						Enter a valid email address
					</div>
				</td>
			</tr>		
			
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
			
			<tr style="display:none;">
				<td colspan="2" class="error-field" role="alert">
					<div class="alert alert-danger">
						<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
						Enter a valid email address
					</div>
				</td>
			</tr>	
			
			<tr><td colspan="2"><hr/></td></tr>			
			
			<tr>
				<td colspan="2" class="button-field"><input type="submit" name="manage-user-info-submit" id="manage-user-info-submit" tabindex="4" class="btn btn-default" value="Update your Password"></td>
			</tr>
		</table>
	</form>
</div>