<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>${selectBy}</h2>

<br/><br/>
<a class="create-new-block" href="/bookadd">
	Add New Book
</a>
<br/><br/>

<table class="tableBooks">

	<c:forEach items="${books}" var="book">

		<tr>
			<th class="info">
				${book.title}, 
				<i>${book.author}</i>, 
				${book.publicationYear}
			</th>
			<td rowspan="2" class="add">
				<a href="/bookupdate?bookId=${book.id}">
					<button class="btn btn-default">Update</button>
				</a>
				<form action="/bookdelete" method="post">
					<input name="bookId" value="${book.id}" style="display: none;" />
					<input type="submit" class="btn btn-default" Value="Delete"/>
				</form>
			</td>
		</tr>
		<tr>
			<td>
				<i>Category: ${book.category.name}; 
				Language: ${book.language.name}; 
				Tags: ${book.keywords}</i>
			</td>
		</tr>
		<tr><td>&nbsp;</td></tr>	
	</c:forEach>
</table>	