<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<br/><br/>
<a class="create-new-block" href="/categoryadd">
	Create New Category
</a>
<br/><br/>

<table class="tableCategories">

	<tr>
		<td><i>1</i></td>
		<th class="info">Novel</th>
		<td class="add">
			<a href="/categoryupdate?categoryId=0">
				<button class="btn btn-default">Update</button>
			</a>
			<form action="/categorydelete" method="post">
				<input name="categoryId" value="1" style="display: none;" />
				<input type="submit" class="btn btn-default" Value="Delete"/>
			</form>
		</td>
	</tr>	
	<tr><td>&nbsp;</td></tr>
	
</table>