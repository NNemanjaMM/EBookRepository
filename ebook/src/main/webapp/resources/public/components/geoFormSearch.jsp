<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-boundary">
<h2>Search Books by Location</h2>
<hr/>

	<form action="/dogeobooksearch" method="POST">
		<table class="manage-data pick-panel">
			<tr class="pick-type-row">
				<td colspan="4">
					<a href="#" class="pick-type-link active" id="map-search-link">Map Search</a>
					<a href="#" class="pick-type-link" id="name-search-link">Name Search</a>
				</td>
			</tr>
			
		
			<tr class="map-form empty-row"><td colspan="4"></td></tr>
			<tr class="map-form">
				<td colspan="4">
					<div id="map"></div>
				    <script>
					    var map;
					    var marker;
					    
					    
					    function placeMarker(location) {
					        if (marker) {
					            marker.setPosition(location);
					        } else {
					    	    marker = new google.maps.Marker({
					    	        position: location, 
					    	        map: map,
							        title: 'Your reference',
						            draggable: true
					    	    });
					        }
				    	    $("#latval").val(location.lat);
				    	    $("#lonval").val(location.lng);
					    }
					    
				      	function initMap() {
				        	var myLatlng = {lat: 44.1951996, lng: 21.0859884};
				
					        map = new google.maps.Map(document.getElementById('map'), {
					        	zoom: 7,
					        	center: myLatlng
					        });

						    placeMarker(myLatlng);
					        map.addListener('click', function(e) { 
					        	placeMarker(e.latLng);
					        });
				      	}				      
				    </script>
				    <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC8ZhnvBEQU6dT6rbTWqO9c-p0ve_aoN98&callback=initMap"></script>
				</td>
			</tr>	
			
			<tr class="map-form">
				<td>Latitude:</td>
				<td>
					<input id="latval" type="text" name="latval" class="form-control mediumInput" readonly="readonly"/>
				</td>
				<td>Longitude:</td>
				<td>
					<input id="lonval" type="text" name="lonval" class="form-control mediumInput" readonly="readonly"/>
				</td>
			</tr>
		
		
			<tr class="name-form empty-row" style="display:none;"><td colspan="4"></td></tr>		
			<tr class="name-form" style="display:none;">
				<td id="spread" colspan="3">Place Name:</td>
				<th>
					<input type="text" maxlength="60" name="place" id="place" tabindex="1" class="form-control bigInput" Placeholder="Enter geographic reference">
				</th>
			</tr>	
			
			<c:if test="${error != null}">
				<tr>
					<td colspan="4" class="error-field" role="alert">
						<div class="alert alert-danger">
							<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
							${error}
						</div>
					</td>
				</tr>
			</c:if>	
			
			<tr><td colspan="4"><hr/></td></tr>		
			
			<tr>
				<td colspan="4" class="button-field">
					<input type="text" name="type" id="type" class="form-control" readonly="readonly" value="map" style="display:none;">
					<input type="submit" name="manage-user-info-submit" id="manage-user-info-submit" tabindex="4" class="btn btn-default" value="Search">
				</td>
			</tr>
		</table>
	</form>
	
</div>