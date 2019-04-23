$(document).ready(function() {
	getHotels();
});

$(document).on('click', '.new_room', function(e) {
	e.preventDefault();

	window.location.href = "rooms.html";

});

function showHideSearch() {
	var x = document.getElementById("div-hotels-search");
	if (x.style.display === "none") {
		x.style.display = "block";
		document.getElementById("showHideBtn").innerText = "Hide search";
	} else {
		x.style.display = "none";
		document.getElementById("showHideBtn").innerText = "Show search";
	}
}

function showRooms(data) {
	var hotelId = sessionStorage.getItem('hotelId');
	var rooms = data == null ? [] : (data instanceof Array ? data : [ data ]);

	$('#h1_').empty();
	$('#h1_').append("Rooms");
	console.log(data);

	$('#div-show-search').hide();
	$('#div-hotels-search').hide();
	$('#hotelsTable').empty();
	$('#hotelsTable')
			.append(
					'<tr><th>Id</th><th>Room number</th><th>Floor</th><th>Capacity</th><th>Price per night</th></tr>');

	$.each(rooms, function(index, room) {

		var tr = $('<tr></tr>');
		var roomTr = $('<td>' + room.id + '</td>' + '<td>' + room.roomNumber
				+ '</td>' + '<td>' + room.floorNumber + '</rd>' + '<td>'
				+ room.capacity + '</td>' + '<td>' + room.pricePerNight
				+ '</td>');

		tr.append(roomTr);
		$('#hotelsTable').append(tr);
	});

	$('#hotelsDiv').append(
			'<input type="button" class="new_room" id="' + hotelId
					+ '"name="new_room" value = "New room" align="center">');

}

function getHotels() {
	$.ajax({
		url : "/hotels",
		type : "GET",
		dataType : 'json',
		beforeSend : function(xhr) {
			/* Authorization header */
			xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		},
		success : fillTable,
		error : function(response) {
			alert("Something went wrong! :(");
		}
	});
}

function getRooms(hotelId) {
	$.ajax({
		url : "/hotels/" + sessionStorage.getItem('hotelId') + "/rooms",
		type : "GET",
		dataType : 'json',
		beforeSend : function(xhr) {
			/* Authorization header */
			xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		},
		success : showRooms,
		error : function(response) {
			alert("Something went wrong! :(");
		}
	});
}

$(document).on('click', '#search_hotel_btn', function(e) {
	e.preventDefault();

	var formData = getFormData("#form-hotels-search");
	var jsonData = JSON.stringify(formData);
	$.ajax({
		type : 'POST',
		url : '/hotels/search',
		contentType : 'application/json',
		dataType : 'json',
		data : jsonData,
		beforeSend : function(xhr) {
			/* Authorization header */
			xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		},
		success : fillTable
	});

});

function fillTable(data) {

	var hotels = data == null ? [] : (data instanceof Array ? data : [ data ]);
	/*
	 * $('#hotelsTable').empty(); $('#hotelsTable').append('<tr><th>Id</th><th>Name</th><th>Address</th><th>Description</th></tr>');
	 */

	var hotelsDiv = $('#hotels-div');
	hotelsDiv.empty();
	var counter = 0;

	$
			.each(
					hotels,
					function(index, hotel) {
						var stars = hotel.stars;
						var stars_str = '';

						for (var i = 0; i != stars; i++) {
							stars_str += '<img class="star" src="../images/hotel_star.png" height = 17 width= 18 style="top:15%; left:'
									+ (120 + i * 30) + '%;" >';
						}
						var hotelDiv = $('<div class="hotel-div" id="hotel-div-"'
								+ counter
								+ ' style="bottom:'
								+ (60 - counter * 40)
								+ '%; top:'
								+ (3 + counter * 40)
								+ '%;"'
								+ '>'
								+ '<img src="../images/background5.jpeg" height = 90% width= 18%>'
								+ '<h3>' + hotel.name + stars_str + '</h3>');

						hotelDiv
								.append('<p>'
										+ hotel.address
										+ '</p>'
										+ '<a href=""><img class="show_on_map" src="../images/show_on_map.png" height = 17 width= 18 ><div class="show_on_map">Show on map</div></a>'
										+ '</div>');
						hotelDiv
								.append('<div class="guest_ratings"> Guest ratings: '
										+ (hotel.rating == null ? 0
												: hotel.rating) + ' / 5 </div>');
						hotelDiv
								.append('<button id="show_rooms_btn">Show rooms</button>');

						counter++;
						hotelsDiv.append(hotelDiv);

					});

	$('.roomsBtn').on('click', function(e) {
		e.preventDefault();
		var iden = this.id.substring(5);
		sessionStorage.setItem('hotelId', iden);
		console.log(iden);

		getRooms(iden);

	});

	$('.formsedit').on(
			'submit',
			function(e) {
				e.preventDefault();

				var iden = this.id;

				var formData = {};
				var s_data = $('#' + this.id).serializeArray();

				for (var i = 0; i < s_data.length; i++) {
					var record = s_data[i];
					if (record.name === "ident") {
						formData["id"] = record.value;
					} else {
						formData[record.name] = record.value;
					}
				}

				if (formData["name"] === "") {
					alert("Please enter a hotel name");
					return;
				}

				var jsonData = JSON.stringify(formData);
				$.ajax({
					type : 'put',
					url : "/hotels/" + this.id,
					contentType : 'application/json',
					dataType : 'json',
					data : jsonData,
					beforeSend : function(xhr) {
						/* Authorization header */
						xhr.setRequestHeader("Authorization", "Bearer "
								+ getJwtToken());
					},
					success : getHotels,
					error : function(data) {
						alert(data);
					}
				});

				alert("Saved");
			});

}
