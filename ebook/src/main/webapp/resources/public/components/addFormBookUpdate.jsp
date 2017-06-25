<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-boundary">
<h2>Update e-Book</h2>
<hr/>

	<form action="/bookupdate" method="POST">
		<table class="manage-data table-size-big">
			<tr>
				<td>Title:</td>
				<th colspan="2">
					<input type="text" maxlength="80" name="title" id="title" class="form-control veryBigInput" value="${book.title}" Placeholder="Enter book title" required="required">
					<input type="text" maxlength="80" name="id" id="id" class="form-control veryBigInput" value="${book.id}" readonly="readonly" required="required" style="display:none;">
				</th>
			</tr>
			<tr>
				<td>Author:</td>
				<th colspan="2">
					<input type="text" maxlength="120" name="author" id="author" class="form-control veryBigInput" value="${book.author}" Placeholder="Enter book author">
				</th>
			</tr>
			<tr>
				<td>Publication year:</td>
				<th colspan="2">
					<input type="number" name="publicationYear" id="publicationYear" min="1" max="2017" class="form-control smallInput" value="${book.publicationYear}">
				</th>
			</tr>
			<tr>
				<td>Keywords:</td>
				<th colspan="2">
					<input type="text" maxlength="120" name="keywords" id="keywords" class="form-control veryBigInput" value="${book.keywords}" Placeholder="List book keywords">
				</th>
			</tr>
			<tr>
				<td>MIME:</td>
				<th colspan="2">
					<input type="text" maxlength="100" name="MIME" id="MIME" class="form-control veryBigInput" value="${book.MIME}" Placeholder="Enter book MIME">
				</th>
			</tr>
			<tr>
				<td>Language</td>
				<th colspan="2">
					<select name="language" id="language" class="form-control mediumInput" required="required">
						<c:forEach items="${languages}" var="lang">
							<option 
								<c:if test="${lang.id == book.language.id}">
									selected
								</c:if> value="${lang.id}">
								${lang.name}
							</option>
						</c:forEach>
					</select>
				</th>
			</tr>
			<tr>
				<td>Category:</td>
				<th colspan="2">
					<select name="category" id="category" class="form-control mediumInput" required="required">
						<c:forEach items="${categories}" var="cat">
							<option
								<c:if test="${cat.id == book.category.id}">
									selected
								</c:if> value="${cat.id}">							
								${cat.name}
							</option>
						</c:forEach>
					</select>
				</th>
			</tr>
			<tr>
				<td>File location:</td>
				<th>
					<input type="text" maxlength="200" name="filename" id="filename" class="form-control fileInput" value="${book.filename}" Placeholder="Browse for book" required="required">
				</th>
				<td>
					<input type="button" name="manage-book-add-submit" id="manage-book-add-submit" class="btn btn-default" value="Browse">
				</td>
			</tr>
			
			<!-- pile te voli -->
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
					<input type="submit" name="manage-book-update-submit" id="manage-book-update-submit" class="btn btn-default" value="Update Book">
				</td>
			</tr>
		</table>
	</form>
</div>