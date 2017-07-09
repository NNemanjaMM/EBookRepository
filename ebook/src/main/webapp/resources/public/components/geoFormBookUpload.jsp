<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-boundary">
<h2>Upload e-Book</h2>
<hr/>

	<form action="/dogeobookupload" method="POST" enctype="multipart/form-data">
		<table class="manage-data">
			<tr>
				<td>File location:</td>
				<th>
					<input type="file" name="file" id="file" class="form-control bigInput" required="required" accept=".pdf">
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
				<td colspan="2" class="button-field">
					<input type="submit" name="manage-book-add-submit" id="manage-book-add-submit" class="btn btn-default" value="Upload Book">
				</td>
			</tr>
		</table>
	</form>
</div>