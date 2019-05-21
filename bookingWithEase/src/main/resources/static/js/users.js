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


function getMyProfileData(){
	$("#myprofilediv").empty();
	
	$
	.ajax({
		type : 'GET',
		url : "/users/myprofile",
		dataType : "json",
		beforeSend : function(xhr) {
			/* Authorization header */
			xhr.setRequestHeader("Authorization", "Bearer "
					+ getJwtToken());
		},
		success : fillProfileData,
		error : function(data) {
			alert(data);
		}
	});
}

function fillProfileData(data){
	$("#myprofilediv").empty();
	var cont = $('#myprofilediv');
	
	var form = $('<form id="formChangeProfile"></form>');
	var tab = $('<table id="profileTable" align="center" style="padding-top:10%;"></table>');
	
	tab.append('<tr><td>Username:</td><td><input name="username" value='+ data.username +' readonly></td></tr>');
	tab.append('<tr><td>First Name:</td><td><input name="name" class="changefield" value='+ data.firstName +' readonly></td></tr>' );
	tab.append('<tr><td>Last Name:</td><td><input name="lastName" class="changefield" value='+ data.lastName +' readonly></td></tr>');
	tab.append('<tr><td>Email:</td><td><input name="email" value='+ data.email +' readonly></td></tr>');
	tab.append('<tr><td>Phone number:</td><td><input name="phoneNumber" class="changefield" value='+ data.telephoneNumber +' readonly></td></tr>');
	tab.append('<tr><td>City:</td><td><input name="city" class="changefield" value='+ data.city +' readonly></td></tr>');
	tab.append('<tr><td> </td><td><button type="button" id="editProfile">Edit</button></td></tr>');
	form.append(tab);
	cont.append(form);
	cont.append('<button id="changepswbtn">Change password</button>')
	
	$('#editProfile').click(function(e){
		e.preventDefault();
		$("#editProfile").attr("disabled", true);
		$('.changefield').removeAttr('readonly');;
		editBtn();
		
	});
	
	$('#changepswbtn').click(function(e){
		openCity(event, 'changepswdiv');
	});
	
	function editBtn(){
		form.append('<input type="submit" id="doEdit" value="Save changes">');

		$(document).on('submit', '#formChangeProfile', function(e){
			
			e.preventDefault();
			var formData = getFormData("#formChangeProfile");
			var jsonData = JSON.stringify(formData);
			$
			.ajax({
				type : 'PUT',
				url : "/users",
				dataType : "json",
				contentType: "application/json",
				data: jsonData,
				beforeSend : function(xhr) {
					/* Authorization header */
					xhr.setRequestHeader("Authorization", "Bearer "
							+ getJwtToken());
				},
				success : getMyProfileData,
				error : function(data) {
					alert(data);
				}
			});
		});
	}
}


$(document).on('submit', '#changepswform', function(e){
	
	e.preventDefault();
	var formData = getFormData("#changepswform");
	var jsonData = JSON.stringify(formData);
	$
	.ajax({
		type : 'POST',
		url : "/auth/change-password",
		dataType : "json",
		contentType: "application/json",
		data: jsonData,
		beforeSend : function(xhr) {
			/* Authorization header */
			xhr.setRequestHeader("Authorization", "Bearer "
					+ getJwtToken());
		},
		success : function(){
			alert("success");
		},
		error : function(data) {
			alert(data);
		}
	});
});
