$(document).ready(function() {	

	function moveMarker(placeName, latlng) {
	    marker.setPosition(latlng);
	    infowindow.setContent(placeName);
	    infowindow.open(map, marker);

	    $("#latval").val(latlng.lat);
	    $("#lonval").val(latlng.lng);
	}
	
	function dragMarker(marker) {
	    $("#latval").val(marker.latLng.lat);
	    $("#lonval").val(marker.latLng.lng);
	}


    var latlng = new google.maps.LatLng(44.1951996, 21.0859884); 
    $("#latval").val(latlng.lat);
    $("#lonval").val(latlng.lng);
     
	var mapOptions = {           
        center: latlng,           
        zoom: 7      
    },
    
    map = new google.maps.Map(document.getElementById('map'), mapOptions),
    
    marker = new google.maps.Marker({
        position: latlng,
        map: map,
        draggable: true
    });
 
	map.addListener('click', function(e) { 
		marker.setPosition(e.latLng);
	    $("#latval").val(e.latLng.lat);
	    $("#lonval").val(e.latLng.lng);
	    infowindow.close(map, marker);
	});
	
	google.maps.event.addListener(marker, 'dragend', dragMarker);
	
    var input = document.getElementById('place');         
    var autocomplete = new google.maps.places.Autocomplete(input, {
        types: ["geocode"]
    });          

    autocomplete.bindTo('bounds', map); 
    var infowindow = new google.maps.InfoWindow(); 
 
    google.maps.event.addListener(autocomplete, 'place_changed', function() {
        infowindow.close();
        var place = autocomplete.getPlace();
        if (place.geometry.viewport) {
            map.fitBounds(place.geometry.viewport);
        } else {
            map.setCenter(place.geometry.location);
            map.setZoom(17);  
        }
        
        moveMarker(place.name, place.geometry.location);
    });  

    $("#place").focusin(function () {
        $(document).keypress(function (e) {
            if (e.which == 13) {
                infowindow.close();
                var firstResult = $(".pac-container .pac-item:first").text();
                
                var geocoder = new google.maps.Geocoder();
                geocoder.geocode({"address":firstResult }, function(results, status) {
                    if (status == google.maps.GeocoderStatus.OK) {
                        var lat = results[0].geometry.location.lat(),
                            lng = results[0].geometry.location.lng(),
                            placeName = results[0].address_components[0].long_name,
                            latlng = new google.maps.LatLng(lat, lng);
                        
                        moveMarker(placeName, latlng);
                        $("input").val(firstResult);
                    }
                });
            }
        });
    });
 
    
});
