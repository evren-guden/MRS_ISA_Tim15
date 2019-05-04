getRooms(localStorage.getItem("showRooms"));
$(document).ready(function() {
	var checkIn = localStorage.getItem('latestHSearchCheckIn');
	var checkOut = localStorage.getItem('latestHSearchCheckOut');

	$('#src-hotel-checkIn').val(checkIn);
	$('#src-hotel-checkOut').val(checkOut);
	if (checkIn != "" && checkOut != "") {
		searchRooms();
	} else {
		getHotels(fillHotelsTable);
	}
});

function getRooms(hotelId) {

	var checkIn = localStorage.getItem('latestHSearchCheckIn');
	var checkOut = localStorage.getItem('latestHSearchCheckOut');

	$.ajax({
		url : "/hotels/" + hotelId + "/rooms",
		type : "GET",
		dataType : 'json',
		beforeSend : function(xhr) {
			/* Authorization header */
			xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		},
		success : fillTableRooms,
		error : function(response) {
			alert("Something went wrong! :(");
		}
	});
}

function searchRooms() {
	var formData = getFormData('#room_search_form');
	if (formData["floorNumber"] === "")
		formData["floorNumber"] = -11;
	formData["hotelId"] = localStorage.getItem('showRooms');

	localStorage.setItem('latestHSearchCheckIn', formData['checkIn']);
	localStorage.setItem('latestHSearchCheckOut', formData['checkOut']);

	var jsonData = JSON.stringify(formData);
	// alert(jsonData);
	$.ajax({
		url : "/hotels/rooms",
		type : "POST",
		dataType : 'json',
		contentType : "application/json",
		data : jsonData,
		beforeSend : function(xhr) {
			/* Authorization header */
			xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		},
		success : function(data) {
			// alert(JSON.stringify(data));
			fillTableRooms(data);
		},
		error : function(response) {
			alert("Something went wronggg! :(");
		}
	});
}

function fillTableRooms(data) {

	var veh_list = data == null ? []
			: (data instanceof Array ? data : [ data ]);

	var roomsDiv = $('#vehsDiv');
	roomsDiv.empty();
	var counter = 0;

	$
			.each(
					veh_list,
					function(index, veh) {
						// alert(JSON.stringify(veh));
						var vehDiv = $('<div class="company-div" id="vehDiv_'
								+ counter
								+ '" style="bottom:'
								+ (60 - counter * 40)
								+ '%; top:'
								+ (3 + counter * 40)
								+ '%;"'
								+ '>'
								+ '<img src="../images/hotel_room.jpg" height = 150 width= 150>'
								+ '<h3>Room number: ' + veh.roomNumber
								+ '</h3>');

						vehDiv.append('<p> Floor: ' + veh.floorNumber + '</p>');
						vehDiv
								.append('<p style="position: absolute;top:45%;left:22%;"> Capacity: '
										+ veh.capacity + '</p></div>');

						vehDiv
								.append('<p style="position: absolute;top:65%;left:22%;">Price per night: '
										+ veh.pricePerNight + ' &#8364;</p>');
						vehDiv
								.append('<p style="position: absolute;top:2%;left:80%;">Guest ratings: '
										+ (veh.rating == null ? 0 : veh.rating)
										+ ' / 5 </p>');

						if (veh.totalPrice != undefined && veh.totalPrice != 0) {
							vehDiv.append('<p class="totalPrice">'
									+ veh.totalPrice + '&#8364</p>');
						}

						vehDiv
								.append('<button class="show_details_btn book_room_btn" id="book_room_' + veh.id + '">Book now</button>');

						var slideShowContainer = $('<div class="slideshow-container"></div>');
						$
								.each(
										veh.prices,
										function(index, price) {

											slideShowContainer
													.append('<div class="mySlides fade mySlides'
															+ veh.id
															+ '"><p>'
															+ price.startDate
																	.substring(
																			0,
																			10)
															+ ' &nbsp;-&nbsp; '
															+ price.endDate
																	.substring(
																			0,
																			10)
															+ '&nbsp;&nbsp;<b>'
															+ price.price
															+ '&#8364;</b></p></div>');
										});

						slideShowContainer
								.append('<a class="prev" onclick="plusSlides(-1,'
										+ veh.id
										+ ')" style="font-size:15px;">&#10094;</a>'
										+ '<a class="next" onclick="plusSlides(1,'
										+ veh.id
										+ ')" style="font-size:15px;">&#10095;</a>');

						if (veh.prices.length > 0) {
							vehDiv.append(slideShowContainer);
						}
						counter++;
						roomsDiv.append(vehDiv);

					});
	$.each(veh_list, function(index, veh) {
		if (veh.prices.length > 0)
			showSlides(1, veh.id);
	});

	$('.book_room_btn').on('click', function(e) {
		e.preventDefault();
		var roomId = this.id.substring(10);

		bookRoom(roomId);

	});
}

function bookRoom(roomId) {
	alertify.set('notifier','position', 'top-right');
	
	var hotelId = localStorage.getItem('showRooms');
	var rrData = collectRoomReservationData();
	rrData['roomId'] = roomId;
	
	if(!validateRRData(rrData))
	{
		return;
	}
	
	var jsonData = JSON.stringify(rrData);
	//alert('rr ' + jsonData);
	$.ajax({
		type : 'POST',
		url : '/hotels/' + hotelId + '/roomReservations',
		contentType : 'application/json',
		dataType : 'json',
		data : jsonData,
		beforeSend : function(xhr) {
			/* Authorization header */
			xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		},
		success : function() {
			alertify.notify('Room booked!');
		},
		statusCode : {
			403 : function() {
				
				alertify.error('You must first log in!');
			}
		}
	});
}

function collectRoomReservationData()
{
	var rrData = {};
	rrData['hotelId'] = localStorage.getItem('showRooms');
	rrData['checkIn'] = localStorage.getItem('latestHSearchCheckIn');
	rrData['checkOut'] = localStorage.getItem('latestHSearchCheckOut');
	if(JSON.parse(localStorage.getItem('currentUser')) != null)
		rrData['userId'] = JSON.parse(localStorage.getItem('currentUser')).id;
	// TODO change this
	rrData['totalPrice'] = 13;
	
	return rrData;
	
}

function validateRRData(rrData)
{	
	alertify.set('notifier','position', 'top-right');
	if(rrData['userId'] === undefined || rrData['userId'] === null)
	{
		alertify.error('You must first log in!');
		return false;
	}
	else if(rrData['checkIn'] === "" || rrData['checkOut'] === "")
	{
		alertify.error('You must choose check in and check out dates!');
		return false;
	}
	else if(rrData['checkIn'] >= rrData['checkOut'])
	{	
		// TODO fix bug
		alertify.error('Invalid dates!');
		return false;
	}
	
	return true;
}

var slideIndex = 1;

// Next/previous controls
function plusSlides(n, id) {
	showSlides(slideIndex += n, id);
}

// Thumbnail image controls
function currentSlide(n, id) {
	showSlides(slideIndex = n, id);
}

function showSlides(n, id) {
	var i;
	var slides = document.getElementsByClassName("mySlides" + id);
	if (n > slides.length) {
		slideIndex = 1
	}
	if (n < 1) {
		slideIndex = slides.length;
	}
	for (i = 0; i < slides.length; i++) {
		slides[i].style.display = "none";
	}

	slides[slideIndex - 1].style.display = "block";

}
