<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-boundary">
<script type="text/javascript" src="resources/static/script/add_book.js"></script>
<h2>Add e-Book to repository</h2>
<hr/>

	<form action="/dogeobookadd" method="POST" id="add-book-form">
		<table class="manage-data table-size-big">
			<tr>
				<td>Title:</td>
				<th colspan="2">
					<input type="text" maxlength="80" name="title" id="title" value="${book.title}" class="form-control veryBigInput" Placeholder="Enter book title" required="required">
				</th>
			</tr>
			<tr>
				<td>Author:</td>
				<th colspan="2">
					<input type="text" maxlength="80" name="author" id="author" value="${book.author}" class="form-control veryBigInput" Placeholder="Enter book author" required="required">
				</th>
			</tr>
			<tr>
				<td>e-Book File:</td>
				<th>
					<input type="text" maxlength="200" name="filename" id="filename" value="${book.filename}" class="form-control veryBigInput" required="required" readonly="readonly">
				</th>
			</tr>	
			
			<tr><td colspan="2"><hr/></td></tr>		
		</table>
		
		<table id="locations-list" class="manage-data table-size-big">
			<!-- 
			<tr class="location1 location-name">
				<td colspan="1">Location:</td>
				<td colspan="3"><label class="place-name">Novi Sad, Serbia</label></td>
				<td colspan="1" class="locatio-link delete-link"><a class="delete-location" tag="2">Delete</a></td>
			</tr>
			<tr class="location1 location-value">
				<td>&nbsp;</td>
				<td>Latitude:</td>
				<th>
					<input type="text" name="latitude" id="latitude" class="form-control smallInput" required="required" readonly="readonly">
				</th>
				<td>Longitude:</td>
				<th>
					<input type="text" name="longitude" id="longitude" class="form-control smallInput" required="required" readonly="readonly">
				</th>
			</tr>
				 -->			
		</table>
		
		
		<div id="do-add-location-view" class="manage-data location-panel">	
			<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC8ZhnvBEQU6dT6rbTWqO9c-p0ve_aoN98&libraries=places"></script>
			<script type="text/javascript" src="resources/static/script/maps.js"></script>		
			
			<hr/>
			
			<div class="row-div">
				<div class="row-content">Place Name:</div>
				<div class="row-content"><input type="text" maxlength="60" name="place" id="place" tabindex="1" class="form-control bigInput" Placeholder="Type geographic reference"></div>
			</div>
				
			<div id="map"></div>
			
			<div class="row-div" style="display:none;">			
				<div class="row-content">&nbsp;&nbsp;Latitude:</div>
				<div class="row-content"><input id="latval" type="text" name="latval" class="form-control mediumInput" readonly="readonly"/></div>
				<div class="row-content">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Longitude:</div>
				<div class="row-content"><input id="lonval" type="text" name="lonval" class="form-control mediumInput" readonly="readonly"/></div>
			</div>
				
			<br/>
			
 			<div class="row-div button-field">
				<input type="button" name="add-location" id="do-add-location" class="btn btn-default" value="Add Location">
			</div>
		</div>
		
		<table class="manage-data table-size-big">
			<tr id="add-location-view" style="display:none;">			
				<td class="locatio-link add-link"><a id="add-location">Add New Location</a></td>
			</tr>
			
			<c:if test="${error != null}">
				<tr><td><hr/></td></tr>		
				<tr>
					<td class="error-field" role="alert">
						<div class="alert alert-danger">
							<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
							${error}
						</div>
					</td>
				</tr>
			</c:if>		
			
			<tr><td><hr/></td></tr>	
			
			<tr>
				<td class="button-field">
					<input type="submit" name="add-book" id="add-book" class="btn btn-default" value="Add Book" disabled="disabled">
				</td>
			</tr>
		</table>
		
		
		
	</form>
</div>