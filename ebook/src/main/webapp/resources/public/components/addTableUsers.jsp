<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="tableUsers">

	<tr>
		<td><i>1</i></td>
		<th class="info">Milicevic Darko (mili123)</th>
		<td class="account"><i>Type:&nbsp;</i></td>
		<td>Subscriber</td>
		<td rowspan="2" class="add">
			<a href="/userupdate?userId=0">
				<button class="btn btn-default">Update</button>
			</a>
			<form action="/userdelete" method="post">
				<input name="userId" value="1" style="display: none;" />
				<input type="submit" class="btn btn-default" Value="Delete"/>
			</form>
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td><i>Category:&nbsp;</i></td>
		<td>All</td>
	</tr>
	
	<tr><td>&nbsp;</td></tr>
		
</table>