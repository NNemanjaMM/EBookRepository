<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-boundary">
<h2>Advanced Searh of Repository</h2>
<hr/>

	<form action="/searchresultadvanced" method="POST">
		<table class="manage-data">
			<tr>
				<td>Title:</td>
				<th>
					<input type="text" maxlength="90" name="title" id="title" tabindex="1" class="form-control bigInput" Placeholder="Enter title to search for">
				</th>
			</tr>
			<tr>
				<td>Author:</td>
				<th>
					<input type="text" maxlength="90" name="author" id="author" tabindex="1" class="form-control bigInput" Placeholder="Enter author to search for">
				</th>
			</tr>
			<tr>
				<td>Keywords:</td>
				<th>
					<input type="text" maxlength="90" name="keywords" id="keywords" tabindex="1" class="form-control bigInput" Placeholder="Enter keywords to search for">
				</th>
			</tr>
			<tr>
				<td>Language:</td>
				<th>
					<select name="language" id="language" class="form-control bigInput">
						<option value=""></option>
						<c:forEach items="${languages}" var="lang">
							<option value="${lang.name}">
								${lang.name}
							</option>
						</c:forEach>
					</select>
				</th>
			</tr>
			<tr>
				<td>Content:</td>
				<th>
					<input type="text" maxlength="90" name="content" id="content" tabindex="1" class="form-control bigInput" Placeholder="Enter content to search for">
				</th>
			</tr>
			<tr>
				<td>Operation:</td>
				<th>
					<select name="operation" id="operation" class="form-control mediumInput" required="required">
						<option value="and">And</option>
						<option value="or">Or</option>
					</select>
				</th>
			</tr>
			<tr>
				<td>Query type:</td>
				<th>
					<select name="query" id="query" class="form-control mediumInput" required="required">
						<option value="standard">Standard</option>
						<option value="phrase">Phrase</option>
						<option value="fuzzy">Fuzzy</option>
					</select>
				</th>
			</tr>
			
			<tr><td colspan="2"><hr/></td></tr>		
			
			<tr>
				<td colspan="2" class="button-field"><input type="submit" name="manage-user-info-submit" id="manage-user-info-submit" tabindex="4" class="btn btn-default" value="Search Advanced"></td>
			</tr>
		</table>
	</form>
</div>