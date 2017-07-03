<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-boundary">
<h2>Searh Repository</h2>
<hr/>

	<form action="/searchresult" method="POST">
		<table class="manage-data">
			<tr>
				<td>Search by:</td>
				<th>
					<select name="criteria" id="criteria" class="form-control mediumInput" required="required">
						<option value="title">Title</option>
						<option value="author">Author</option>
						<option value="keywords">Keywords</option>
						<option value="language">Language</option>
						<option value="content">Content</option>
					</select>
				</th>
			</tr>
			<tr>
				<td>Search for:</td>
				<th>
					<input type="text" maxlength="60" name="textvalue" id="textvalue" tabindex="1" class="form-control mediumInput" Placeholder="Enter search parameter">
					<select name="languagevalue" id="languagevalue" class="form-control mediumInput" required="required" style="display:none;">
						<c:forEach items="${languages}" var="lang">
							<option value="${lang.name}">
								${lang.name}
							</option>
						</c:forEach>
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
				<td colspan="2" class="button-field"><input type="submit" name="manage-user-info-submit" id="manage-user-info-submit" tabindex="4" class="btn btn-default" value="Search"></td>
			</tr>
		</table>
	</form>
</div>