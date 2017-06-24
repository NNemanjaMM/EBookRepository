<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<br/><br/>
<a class="create-new-block" href="/bookadd">
	Add New Book
</a>
<br/><br/>

<table class="tableBooks">

	<tr>
		<th class="info">Duge noci i crne zastave, <i>Dejan Stojiljkovic</i>, 2011</th>
		<td rowspan="2" class="add">
			<a href="/bookupdate?bookId=0">
				<button class="btn btn-default">Update</button>
			</a>
			<form action="/bookdelete" method="post">
				<input name="bookId" value="1" style="display: none;" />
				<input type="submit" class="btn btn-default" Value="Delete"/>
			</form>
		</td>
	</tr>
	<tr><td><i>Category: History; Language: English; Tags: Serbia, Kosovo War, Milos Obilic</i></td></tr>
	<tr><td>&nbsp;</td></tr>
	
</table>	