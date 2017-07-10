<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

				    
<div class="form-boundary">
<h2>Search Books by Location</h2>
<hr/>

	<form action="/dogeobooksearch" method="POST">
		<div class="manage-data location-panel">	
			<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC8ZhnvBEQU6dT6rbTWqO9c-p0ve_aoN98&libraries=places"></script>
			<script type="text/javascript" src="resources/static/script/maps.js"></script>		
			
			<div class="row-div">
				<div class="row-content">Place Name:</div>
				<div class="row-content"><input type="text" maxlength="60" name="place" id="place" tabindex="1" class="form-control bigInput" Placeholder="Type geographic reference"></div>
			</div>
				
			<div id="map"></div>
			
			<div class="row-div">			
				<div class="row-content">&nbsp;&nbsp;Latitude:</div>
				<div class="row-content"><input id="latval" type="text" name="latval" class="form-control mediumInput" readonly="readonly"/></div>
				<div class="row-content">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Longitude:</div>
				<div class="row-content"><input id="lonval" type="text" name="lonval" class="form-control mediumInput" readonly="readonly"/></div>
			</div>	
			
			<hr/>
			
 			<div class="row-div button-field">
				<input type="submit" name="geo-search" id="geo-search" class="btn btn-default" value="Search">
			</div>
		</div>
	</form>
	
</div>