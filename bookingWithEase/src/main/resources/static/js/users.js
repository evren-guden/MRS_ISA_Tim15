$(document).ready(function() {
	getUsers();
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

function getMyRoomReservations() {

	$.ajax({
		url : "/users/" + JSON.parse(localStorage.getItem('currentUser')).id
				+ "/roomReservations",
		type : "GET",
		dataType : 'json',
		success : function(data) {
			alert(JSON.stringify(data));
		},
		error : function(response) {
			alert("Something went wrong while getting room reservations! :(");
		}
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