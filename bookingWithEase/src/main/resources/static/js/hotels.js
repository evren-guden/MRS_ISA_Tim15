$(document).ready(function() {
	getHotels(fillHotelsTable);
});

$(document).on('click', '.new_room', function(e) {
	e.preventDefault();

	window.location.href = "rooms.html";

});

$(document).on('click', '#edit_room_btn', function(e) {
	e.preventDefault();

	var formData = getFormData('#editRoomForm');
	var currentUser = JSON.parse(localStorage.getItem('currentUser'));
	var roomId = localStorage.getItem('updateRoomId');
	formData["id"] = roomId;
	formData["hotelId"] = currentUser.company.id;
	
	var jsonData = JSON.stringify(formData);	
	
	updateRoom(currentUser.company.id, roomId, jsonData, function() {
		alert("Saved :)");
		getRooms(currentUser.company.id);
		openCity(event, 'allRooms');
	});

});

$(document).on('click', '#cancel_edit_room_btn', function(e) {
	e.preventDefault();
	openCity(event, 'allRooms');
});

function showRooms(data) {
	var hotelId = sessionStorage.getItem('hotelId');
	var rooms = data == null ? [] : (data instanceof Array ? data : [ data ]);

	$('#hotelsTable').empty();
	$('#hotelsTable')
			.append(
					'<tr><th>Id</th><th>Room number</th><th>Floor</th><th>Capacity</th><th>Price per night</th><th>&nbsp;</th><th>&nbsp;</th></tr>');

	$.each(rooms, function(index, room) {
		localStorage.setItem("room_" + room.id, JSON.stringify(room));

		var tr = $('<tr></tr>');
		var roomTr = $('<td>' + room.id + '</td>' + '<td id="troom_' + room.id
				+ '">' + room.roomNumber + '</td>' + '<td>' + room.floorNumber
				+ '</rd>' + '<td>' + room.capacity + '</td>' + '<td>'
				+ room.pricePerNight
				+ '</td><td><button class="edit_room_btn" id="edit_room_'
				+ room.id + '">Edit</button></td>'
				+ '</td><td><button class="delete_room_btn" id="delete_room_'
				+ room.id + '">Delete</button></td>');

		tr.append(roomTr);
		$('#hotelsTable').append(tr);
	});

	$('#hotelsDiv').append(
			'<input type="button" class="new_room" id="' + hotelId
					+ '"name="new_room" value = "New room" align="center">');

	$('.delete_room_btn').on('click', function(e) {
		e.preventDefault();

		var currentUser = JSON.parse(localStorage.getItem('currentUser'));
		var roomId = this.id.substring(12);
		var hotelId = currentUser.company.id;

		deleteRoom(hotelId, roomId, function() {
			getRooms(hotelId);
			alert("Room deleted!");
		});
	});

	$('.edit_room_btn').on('click', function(e) {
		e.preventDefault();

		var currentUser = JSON.parse(localStorage.getItem('currentUser'));
		var roomId = this.id.substring(10);
		var hotelId = currentUser.company.id;
		openCity(event, 'editRoom');

		var room = JSON.parse(localStorage.getItem('room_' + roomId));
		localStorage.setItem("updateRoomId", roomId);

		$('#room_number').val(room.roomNumber);
		$('#floor_number').val(room.floorNumber);
		$('#room_capacity').val(room.capacity);
		$('#price_per_night').val(room.pricePerNight);

	});

}

function getHotels(successFunction) {
	$.ajax({
		url : "/hotels",
		type : "GET",
		dataType : 'json',
		beforeSend : function(xhr) {
			/* Authorization header */
			xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		},
		success : successFunction,
		error : function(response) {
			alert("Something went wrong! :(");
		}
	});
}

var currentUser = JSON.parse(localStorage.getItem('currentUser'));
if (currentUser != null)
	getRooms(currentUser.company.id);

function getRooms(hotelId) {

	$.ajax({
		url : "/hotels/" + hotelId + "/rooms",
		type : "GET",
		dataType : 'json',
		beforeSend : function(xhr) {
			/* Authorization header */
			xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		},
		success : showRooms,
		error : function(response) {
			alert("Something went wrong while getting rooms! :(");
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
		success : fillHotelsTable
	});

});

function fillHotelsTable(data) {

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
						var hotelDiv = $('<div class="company-div" id="hotel-div-"'
								+ counter
								+ ' style="bottom:'
								+ (60 - counter * 40)
								+ '%; top:'
								+ (3 + counter * 40)
								+ '%;"'
								+ '>'
								+ '<img src="../images/background5.jpeg" height = 150 width= 150>'
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
								.append('<button class="show_details_btn" id="showr_'
										+ hotel.id + '">Show rooms</button>');

						counter++;
						hotelsDiv.append(hotelDiv);

					});

	$('.show_details_btn').on('click', function(e) {
		e.preventDefault();

		var iden = this.id.substring(6);

		if (localStorage.getItem("showRooms") === null) {
			localStorage.removeItem('showRooms');
		}
		localStorage.setItem('showRooms', iden);
		window.location.href = "rooms.html";
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
