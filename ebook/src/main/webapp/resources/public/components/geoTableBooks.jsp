<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC8ZhnvBEQU6dT6rbTWqO9c-p0ve_aoN98&libraries=places"></script>
<script type="text/javascript" src="resources/static/script/set_markers.js"></script>
	
					
<h2>${viewTitle}</h2>
					
					
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


<c:if test="${markers != null}">
	<input type="hidden" id="markers" value='${markers}' />
</c:if>
<c:if test="${center != null}">
	<input type="hidden" id="center" value='${center}' />
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
						<i> 
							<c:forEach items="${book.places}" var="place">
								${place} 
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

<c:if test="${books.size() > 0}">

	<hr/>
	
	<div id="map"></div>
	
</c:if>
