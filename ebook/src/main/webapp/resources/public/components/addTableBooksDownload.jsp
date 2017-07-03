<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${download_error != null}">
	<tr>
		<td colspan="2" class="error-field" role="alert">
			<div class="alert alert-danger">
				<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
				${download_error}
			</div>
		</td>
	</tr>
</c:if>	
	
<table class="tableBooks">

	<c:choose>
		<c:when test="${books.size() > 0}">
		
			<c:forEach items="${books}" var="book">
			
				<tr>
					<th class="info">
						${book.title}, 
						<i>${book.author}</i>, 
						${book.publicationYear}
					</th>
					<td rowspan="3" class="add">
						<c:choose>
							<c:when test="${sessionUser.category == null || sessionUser.category.id == book.category.id}">
								<a href="/bookdownload?bookId=${book.id}">
									<button class="btn btn-default">Download</button>
								</a>
							</c:when>
							<c:otherwise>
								Category not available for you
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td>
						<i>Category: ${book.category.name}; 
						Language: ${book.language.name}; 
						Tags: ${book.keywords}</i>
					</td>
				</tr>
				<tr>
		 			<td>
		 				${book.summary}
		 			</td>
				</tr>	
				<tr><td>&nbsp;</td></tr>
			</c:forEach>
		</c:when>
		
		<c:otherwise>
			<h3>There are no books to display</h3>
		</c:otherwise>
	</c:choose>
			
</table>	