<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-boundary">
<h2>Update category</h2>
<hr/>
	
	<form action="/categoryupdate" method="POST">
		<table class="manage-data table-size-small">
			<tr>
				<td>
					Category:
					<input type="text" name="id" id="id" tabindex="1" class="form-control" value="${category.id}" style="display:none;" required="required">
				</td>
				<th>
					<input type="text" name="name" id="name" tabindex="1" class="form-control" value="${category.name}" placeholder="Enter new category name" required="required">
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
				<td colspan="2" class="button-field"><input type="submit" name="manage-user-info-submit" id="manage-user-info-submit" tabindex="4" class="btn btn-default" value="Update Category"></td>
			</tr>
		</table>
	</form>
</div>