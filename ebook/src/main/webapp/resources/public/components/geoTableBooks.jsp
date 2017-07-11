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
						<i>${book.author}</i>
					</th>
					<td rowspan="2" class="add">
						<a href="/bookdownload?bookId=${book.id}">
							<button class="btn btn-default">Download</button>
						</a>
					</td>
				</tr>
				<tr>
					<td>
						<i>Book locations: 
							<c:forEach items="${book.locations}" var="loc">
								(${loc.latitude}, ${loc.longitude}) 
							</c:forEach>
						</i>
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