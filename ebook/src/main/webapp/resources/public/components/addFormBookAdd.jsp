<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-boundary">
<h2>Add e-Book to repository</h2>
<hr/>

	<form action="/bookadd" method="POST">
		<table class="manage-data table-size-big">
			<tr>
				<td>Title:</td>
				<th colspan="2">
					<input type="text" maxlength="80" name="title" id="title" class="form-control veryBigInput" Placeholder="Enter book title" required="required">
				</th>
			</tr>
			<tr>
				<td>Author:</td>
				<th colspan="2">
					<input type="text" maxlength="120" name="author" id="author" class="form-control veryBigInput" Placeholder="Enter book author">
				</th>
			</tr>
			<tr>
				<td>Publication year:</td>
				<th colspan="2">
					<input type="number" name="publicationYear" id="publicationYear" min="1" max="2017" class="form-control smallInput">
				</th>
			</tr>
			<tr>
				<td>Keywords:</td>
				<th colspan="2">
					<input type="text" maxlength="120" name="keywords" id="keywords" class="form-control veryBigInput" Placeholder="List book keywords">
				</th>
			</tr>
			<tr>
				<td>MIME:</td>
				<th colspan="2">
					<input type="text" maxlength="100" name="MIME" id="MIME" class="form-control veryBigInput" Placeholder="Enter book MIME">
				</th>
			</tr>
			<tr>
				<td>Language</td>
				<th colspan="2">
					<select name="language" id="language" class="form-control mediumInput" required="required">
						<option value="1">Serbian</option>
						<option value="2">Russian</option>
					</select>
				</th>
			</tr>
			<tr>
				<td>Category:</td>
				<th colspan="2">
					<select name="category" id="category" class="form-control mediumInput" required="required">
						<option value="1">History</option>
						<option value="2">Novel</option>
					</select>
				</th>
			</tr>
			<tr>
				<td>File location:</td>
				<th>
					<input type="text" maxlength="200" name="filename" id="filename" class="form-control fileInput" required="required" Placeholder="Browse for book on your PC">
				</th>
				<td>
					<input type="button" name="manage-book-add-submit" id="manage-book-add-submit" class="btn btn-default" value="Browse">
				</td>
			</tr>
			
			<tr style="display:none;">
				<td colspan="3" class="error-field" role="alert">
					<div class="alert alert-danger">
						<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
						Enter a valid email address
					</div>
				</td>
			</tr>	
			
			<tr><td colspan="3"><hr/></td></tr>	
			
			<tr>
				<td colspan="3" class="button-field">
					<input type="submit" name="manage-book-add-submit" id="manage-book-add-submit" class="btn btn-default" value="Add Book">
				</td>
			</tr>
		</table>
	</form>
</div>