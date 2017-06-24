<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-boundary">
<h2>Update user account</h2>
<hr/>
	<form action="/userupdate" method="POST">
		<table class="manage-data table-size-small">
			<tr>
				<td>Username:</td>
				<th><input type="text" name="username" disabled="disabled" id="username" tabindex="1" class="form-control" placeholder="Username" value="micha12" required="required"></th>
			</tr>
			<tr>
				<td>First name:</td>
				<th><input type="text" name="firstname" disabled="disabled" id="firstname" tabindex="1" class="form-control" placeholder="First Name" value="Michael" required="required"></th>
			</tr>
			<tr>
				<td>Last name:</td>
				<th><input type="text" name="lastname" disabled="disabled" id="lastname" tabindex="1" class="form-control" placeholder="Last Name" value="Grant" required="required"></th>
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
			<tr>
				<td>Role:</td>
				<th>
					<select name="type" id="type" tabindex="1" class="form-control" required="required">
						<option value="s" selected>Subscriber</option>
						<option value="a">Administrator</option>
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
				<td colspan="2" class="button-field"><input type="submit" name="manage-user-role-submit" id="manage-user-role-submit" tabindex="4" class="btn btn-default" value="Update User Account"></td>
			</tr>
		</table>
	</form>

</div>