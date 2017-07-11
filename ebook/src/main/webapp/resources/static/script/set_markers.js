$(document).ready(function() {

	var markersFresh = $("#markers").val();
	var centerFresh = $("#center").val();
	var hasMarkers = false;
	var hasCenter = false;
	
	if (markersFresh != null) {
		var markers = JSON.parse(markersFresh);
		hasMarkers = true;
	}

	if (centerFresh != null) {
		var center = JSON.parse(centerFresh);
		hasCenter = true;
	}
	
	if (hasCenter) {
		var centerLatLng = new google.maps.LatLng(center.latitude, center.longitude);
	} else {
		var centerLatLng = new google.maps.LatLng(44.1951996, 21.0859884);
	}

	var mapOptions = {           
        center: centerLatLng,           
        zoom: 7      
    },     
    
    map = new google.maps.Map(document.getElementById('map'), mapOptions);

	if (hasCenter) {
		var searchRadius = new google.maps.Circle({
			strokeColor : '#28C8A0',
			strokeOpacity : 0.4,
			strokeWeight : 2,
			fillColor : '#28C8A0',
			fillOpacity : 0.1,
			map : map,
			center : centerLatLng,
			radius : 50000
		});
	}
	
	if (hasMarkers) {	
		for (var i in markers) {
			latlng = new google.maps.LatLng(markers[i].lat, markers[i].lon);
			bookTitle = markers[i].title;
			bookAuthor = markers[i].author;
			bookColor = markers[i].color;

		    var pinImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + bookColor,
		        new google.maps.Size(21, 34),
		        new google.maps.Point(0,0),
		        new google.maps.Point(10, 34));			
			
		    bookMmarker = new google.maps.Marker({
		        position: latlng,
		        map: map,
                icon: pinImage,
		        draggable: false,
		        title: bookTitle + " (" + bookAuthor +")"
		    });
		    var infowindow = new google.maps.InfoWindow(); 
		    infowindow.setContent(bookTitle);
		    //infowindow.open(map, bookMmarker);
		}
	}
	
});