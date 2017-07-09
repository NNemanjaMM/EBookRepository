<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-boundary">
<h2>Add e-Book to repository</h2>
<hr/>

	<form action="/dogeobookadd" method="POST">
		<table class="manage-data table-size-big">
			<tr>
				<td>Title:</td>
				<th colspan="2">
					<input type="text" maxlength="80" name="title" id="title" value="${book.title}" class="form-control veryBigInput" Placeholder="Enter book title" required="required">
				</th>
			</tr>
			<tr>
				<td>e-Book File:</td>
				<th>
					<input type="text" maxlength="200" readonly="readonly" name="filename" value="${book.filename}" id="filename" class="form-control veryBigInput" required="required">
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
					<input type="submit" name="manage-book-add-submit" id="manage-book-add-submit" class="btn btn-default" value="Add Book">
				</td>
			</tr>
		</table>
	</form>
</div>