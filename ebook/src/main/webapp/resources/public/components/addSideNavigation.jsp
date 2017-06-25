<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="panel">
	<ul class="panel-heders nav nav-pills nav-stacked">
		<li class="enabled-header"><a href="/">All Books</a></li>
		<li class="enabled-header"><a href="/search">Search</a></li>
		<li class="disabled"><a href="#">Books by Category</a>
			<ul class="panel-categories nav nav-pills nav-stacked">					
				<c:forEach items="${categories}" var="cat">
		     		<li class=""><a href="/category?categoryId=${cat.id}">${cat.name}</a></li>
				</c:forEach>
			</ul>
		</li>
	</ul>
</div>