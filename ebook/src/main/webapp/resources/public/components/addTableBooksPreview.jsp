<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
						Register to download
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
				</tr>	
				<tr><td>&nbsp;</td></tr>
			</c:forEach>
		</c:when>
		
		<c:otherwise>
			<h3>There are no books to display</h3>
		</c:otherwise>
	</c:choose>
</table>	