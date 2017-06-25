<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>${selectBy}</h2>

<table class="tableBooks">

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
						<a href="/bookdownload?bookId=0">
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
	
</table>	