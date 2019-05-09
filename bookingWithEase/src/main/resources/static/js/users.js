$(document).ready(function() {
	getUsers();
	alertify.set('notifier', 'position', 'top-right');
});

$(document).on('click', '.cancelRR', function(e) {
	var rrId = $(this).attr('id').substring(8);
	var userId = JSON.parse(localStorage.getItem('currentUser')).id;
	
	alertify.confirm('Cancel reservation', 'Are you sure?', function() {
		cancelRoomReservation(userId, rrId, function() {
			alertify.notify('Reservation canceled');
			getMyRoomReservations(fillRoomReservations);
		});
	}, function() {
		alertify.notify("Canceled");
	});

});

function getUsers() {
	$.ajax({
		url : "/users",
		type : "GET",
		dataType : 'json',
		success : fillUsersTable,
		error : function(response) {
			alert("Something went wrong! :(");
		}
	});
}

function cancelRoomReservation(userId, rrId, callback) {
	$
			.ajax({
				url : "users/" + userId + "/roomReservations/" + rrId,
				type : "DELETE",
				contentType : 'application/json',
				dataType : 'json',
				success : callback,
				statusCode : {
					422 : function() {
						alertify
								.error("The deadline for canceling the reservation has expired");
					}
				}
			});
}

function getMyRoomReservations(callback) {

	$.ajax({
		url : "/users/" + JSON.parse(localStorage.getItem('currentUser')).id
				+ "/roomReservations",
		type : "GET",
		dataType : 'json',
		success : callback,
		error : function(response) {
			alert("Something went wrong while getting room reservations! :(");
		}
	});
}

function fillRoomReservations(data) {
	// alert(JSON.stringify(data));

	var reservation_list = data == null ? [] : (data instanceof Array ? data
			: [ data ]);

	var rrDiv = $('#roomReservationsDiv');
	rrDiv.empty();
	var counter = 0;

	$.each(reservation_list, function(index, reservation) {
		// alert(JSON.stringify(reservation));
		var reservationDiv = $('<div class="rrDiv" id="rrDiv_' + counter
				+ '" style="bottom:' + (55 - counter * 45) + '%; top:'
				+ (3 + counter * 45) + '%;"' + '></div>');
		reservationDiv.append('Hotel: ' + reservation.room.hotelName + ', '
				+ reservation.room.hotelAddress + ' </br>');
		reservationDiv.append('Room number: ' + reservation.room.roomNumber
				+ ' </br>');
		reservationDiv.append('Room capacity: ' + reservation.room.roomCapacity
				+ ' </br>');
		reservationDiv.append('Reservation date: '
				+ reservation.reservationDate.substring(0, 10) + ' </br>');
		reservationDiv.append('Check in: '
				+ reservation.checkInDate.substring(0, 10) + ' </br>');
		reservationDiv.append('Check out: '
				+ reservation.checkOutDate.substring(0, 10) + ' </br></br>');
		reservationDiv.append('Price: ' + reservation.totalPrice
				+ '&#8364;</br>');

		var today = new Date();
		today.setDate(today.getDate() + 5);
		var checkIn = new Date(reservation.checkInDate);
		if (today.getTime() <= checkIn.getTime())
			reservationDiv.append('<button class="cancelRR" id="cancelRR'
					+ reservation.id + '">Cancel reservation</button>');

		counter++;
		rrDiv.append(reservationDiv);
	});

}

function fillUsersTable(data) {
	var users = data == null ? [] : (data instanceof Array ? data : [ data ]);
	$('#usersTable').empty();
	$('#usersTable')
			.append(
					'<tr><th>Username</th><th>First name</th><th>Last name</th><th>Email</th><th>User type</th></tr>');
	$.each(users, function(index, user) {

		var tr = $('<tr></tr>');
		tr.append('<td>' + user.username + '</td>' + '<td>' + user.firstName
				+ '</td>' + '<td>' + user.lastName + '</td>' + '<td>'
				+ user.email + '</td>');

		if (user.type == undefined) {
			tr.append('<td>' + 'registered user' + '</td>');
		} else if (user.type !== null) {
			tr.append('<td>' + user.type.toLowerCase() + ' admin' + '</td>');
		} else {
			tr.append('<td>' + ' registered user' + '</td>');
		}

		$('#usersTable').append(tr);

	});

}