$(document).ready(function() {

	geocoder = new google.maps.Geocoder();

	locations_count = 0;

	$('#add-location').click(function() {
		$("#add-book").prop('disabled', true);
		$("#do-add-location-view").show();
		$("#add-location-view").hide();
	});

	$('#do-add-location').click(function() {
		$("#add-book").prop('disabled', false);
		$("#do-add-location-view").hide();
		$("#add-location-view").show();
		doAddLocation();

		$('.delete-location').click(function() {
			var id = $(this).attr('tag');
			doDeleteLocation(id);
		});
	});

	$("#add-book-form").submit(function(e) {
		e.preventDefault();
		doAddBook();
	});

});

function doDeleteLocation(id) {
	$('.location' + id).remove();
}

function doAddLocation() {
	var latitude = $("#latval").val();
	var longitude = $("#lonval").val();
	var locationName = codeLatLng(latitude, longitude, locations_count);

	var content = '<tr class="location' + locations_count + ' location-name">'
					+ '<td colspan="1">Location:</td>'
					+ '<td colspan="3"><label class="place-name place-name' + locations_count + '">' + locationName + '</label></td>'
					+ '<td colspan="1" class="locatio-link delete-link"><a class="delete-location" tag="' + locations_count + '">Delete</a></td>'
				+ '</tr>'
				+ '<tr class="location' + locations_count + ' location-value">'
					+ '<td>&nbsp;</td>'
					+ '<td>Latitude:</td>'
					+ '<th>'
						+ '<input type="text" name="latitude" value="' + latitude + '" id="latitude" class="form-control smallInput" required="required" readonly="readonly">'
					+ '</th>'
					+ '<td>Longitude:</td>'
					+ '<th>'
						+ '<input type="text" name="longitude" value="' + longitude + '" id="longitude" class="form-control smallInput" required="required" readonly="readonly">'
					+ '</th>'
				+ '</tr>';

	locations_count++;

	$("#locations-list").append(content);
}

function doAddBook() {

	var titleValue = $("#title").val();
	var authorValue = $("#author").val();
	var fileNameValue = $("#filename").val();
	var placesValue = "";
	var locationsValue = '[';

	$('.location-value').each(
			function(i, obj) {
				var lat = $(obj).find('#latitude').val();
				var lon = $(obj).find('#longitude').val();
				locationsValue += '{' + '"id":"",' + '"latitude":"' + lat
						+ '",' + '"longitude":"' + lon + '"' + '},';
			});
	locationsValue = locationsValue.slice(0, -1);
	locationsValue += ']';

	$('.location-name').each(
			function(i, obj) {
				var place = $(obj).find('.place-name').text();
				placesValue += place + ';<br/>';
			});
	placesValue = placesValue.slice(0, -6);
	

	var form = document.createElement("form");
	form.setAttribute("method", "POST");
	form.setAttribute("action", "/dogeobookadd");

	var titleField = document.createElement("input");
	titleField.setAttribute("type", "hidden");
	titleField.setAttribute("name", "title");
	titleField.setAttribute("value", titleValue);
	form.appendChild(titleField);

	var authorField = document.createElement("input");
	authorField.setAttribute("type", "hidden");
	authorField.setAttribute("name", "author");
	authorField.setAttribute("value", authorValue);
	form.appendChild(authorField);

	var fileNameField = document.createElement("input");
	fileNameField.setAttribute("type", "hidden");
	fileNameField.setAttribute("name", "fileName");
	fileNameField.setAttribute("value", fileNameValue);
	form.appendChild(fileNameField);

	var locationsField = document.createElement("input");
	locationsField.setAttribute("type", "hidden");
	locationsField.setAttribute("name", "locations");
	locationsField.setAttribute("value", locationsValue);
	form.appendChild(locationsField);

	var placesField = document.createElement("input");
	placesField.setAttribute("type", "hidden");
	placesField.setAttribute("name", "places");
	placesField.setAttribute("value", placesValue);
	form.appendChild(placesField);

	document.body.appendChild(form);
	form.submit();
}

function codeLatLng(lat, lng, id) {
	var latlng = new google.maps.LatLng(lat, lng);
	geocoder.geocode({'latLng' : latlng}, function(results, status) {
		if (status === google.maps.GeocoderStatus.OK) {
			if (results[1]) {
				$(".place-name" + id).text(results[1].formatted_address);
			} else {
				$(".place-name" + id).text("(location not found)");
			}
		} else {
			$(".place-name" + id).text("(location not found)");
		}
	});
}
