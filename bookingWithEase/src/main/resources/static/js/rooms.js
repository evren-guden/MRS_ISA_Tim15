$(document).on('click', '#addNewRoom', function(e) {
	e.preventDefault();
	registration();
});

$(document).on('click', '#cancelNewRoom', function(e) {
	e.preventDefault();
	window.location.href = "hotels.html";
});

function registration() {
	var formData = getFormData("#addRoomForm");
	var currentUser = JSON.parse(localStorage.getItem('currentUser'));

	formData['hotelId'] = currentUser.company.id;

	var validData = Boolean(validateNewRoomData(formData));

	if (validData) {
		var jsonData = JSON.stringify(formData);
		console.log(formData);
		$.ajax({
			url : "/hotels/" + formData['hotelId'] + "/rooms",
			type : "POST",
			contentType : "application/json",
			data : jsonData,
			dataType : 'json',
			success : function(response) {
				alert("Room added :)");
				//window.location.href = "hotels.html";
			},
			error : function(response) {
				alert("Something went wrong! :(");
			}
		});
	} else {

	}
}

function validateNewRoomData(formData) {
	var roomNumber = formData["roomNumber"];
	var floorNumber = formData["floorNumber"];
	var capacity = formData["capacity"];
	var pricePerNight = formData["pricePerNight"];

	if (roomNumber === "") {
		alert("Please enter a room number");
		return false;
	}
	if (!isInt(roomNumber)) {
		alert("Please enter a valid number for room number");
		return false;
	}
	if (floorNumber === "") {
		alert("Please enter a floor number");
		return false;
	}
	if (!isInt(floorNumber)) {
		alert("Please enter a valid number for floor number");
		return false;
	}
	if (capacity === "") {
		alert("Please enter a capacity");
		return false;
	}
	if (!isInt(capacity)) {
		alert("Please enter a valid number for capacity");
		return false;
	}
	if (!isDouble(pricePerNight)) {
		alert("Please enter a valid price");
		return false;
	}

	return true;

}

function isInt(value) {
	var er = /^-?[0-9]+$/;
	return er.test(value);
}

function isDouble(value) {
	var er = /^[+-]?\d+(\.\d+)?$/;
	return er.test(value);
}
