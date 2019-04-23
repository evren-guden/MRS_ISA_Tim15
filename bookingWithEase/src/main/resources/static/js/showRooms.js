getRooms(100);

function getRooms(hotelId) {
	$.ajax({
		url : "/hotels/" + hotelId + "/rooms",
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

function fillTable(data) {
	var veh_list = data == null ? []
	: (data instanceof Array ? data : [ data ]);

var roomsDiv = $('#vehsDiv');
roomsDiv.empty();
var counter = 0;

$
	.each(
			veh_list,
			function(index,veh) {
				var vehDiv = $('<div class="vehDiv" id="vehDiv_'
						+ counter
						+ '" style="bottom:'
						+ (60 - counter * 40)
						+ '%; top:'
						+ (3 + counter * 40)
						+ '%;"'
						+ '>'
						+ '<img src="../images/hotel_room.jpg" height = 150 width= 150>'
						+ '<h3>Room number: ' + veh.roomNumber + '</h3>');

				vehDiv
						.append('<p> Capacity: '
								+ veh.capacity
								+ '</p>' + '<p>Capacity: ' + veh.capacity + '</p>'
								+ '</div>');
				vehDiv
				.append('<p style="position: absolute;top:65%;left:25%;">Price per night: '
						+ veh.pricePerNight
						+ ' &#8364;</p>');
				vehDiv
				.append('<button class="reservationBtn">Make reservation</button>');

				counter++;
				roomsDiv.append(vehDiv);

			});

}
