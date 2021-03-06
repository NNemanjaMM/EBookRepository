<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-boundary">
<h2>Create category</h2>
<hr/>

	<form action="/categoryadd" method="POST">
		<table class="manage-data table-size-small">
			<tr>
				<td>Category:</td>
				<th><input type="text" maxlength="30" name="name" id="name" tabindex="1" class="form-control" placeholder="Enter new category name" required="required"></th>
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
				<td colspan="2" class="button-field"><input type="submit" name="manage-user-info-submit" id="manage-user-info-submit" tabindex="4" class="btn btn-default" value="Create Category"></td>
			</tr>
		</table>
	</form>
</div>