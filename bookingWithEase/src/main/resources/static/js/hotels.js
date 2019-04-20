$(document).ready(function() {
    getHotels();
});

$(document).on('click', '.new_room', function(e){
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

$(document).on('click', '#search_hotel_btn', function(e) {
	e.preventDefault();

	var formData = getFormData("#form-hotels-search");
	var jsonData = JSON.stringify(formData);
	$.ajax({
		type : 'POST',
		url :'/hotels/search',
		contentType : 'application/json',
		dataType : 'json',
		data : jsonData,
		beforeSend: function (xhr) {
	        /* Authorization header */
	        xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
	    },
		success : fillTable
	});

});

function showRooms(data)
{	
	var hotelId = sessionStorage.getItem('hotelId');
	var rooms = data == null ? []
	: (data instanceof Array ? data : [ data ]);
	
	$('#h1_').empty();
	$('#h1_').append("Rooms");
	console.log(data);
	
	$('#hotelsTable').empty();
	$('#hotelsTable').append('<tr><th>Id</th><th>Room number</th><th>Floor</th><th>Capacity</th><th>Price per night</th></tr>');
	
	$.each(rooms, function(index, room) {

		var tr = $('<tr></tr>');
		var roomTr = $('<td>' + room.id + '</td>' + 
				     '<td>' + room.roomNumber + '</td>' +
				     '<td>' + room.floorNumber + '</rd>' +
				     '<td>' + room.capacity + '</td>' + 
				     '<td>' + room.pricePerNight + '</td>');
		
		tr.append(roomTr);
		$('#hotelsTable').append(tr);
	});
	
	$('#hotelsDiv').append('<input type="button" class="new_room" id="' + hotelId + '"name="new_room" value = "New room" align="center">');
	
}

function getHotels()
{	
	$.ajax({
		url : "/hotels",
		type : "GET",
		dataType : 'json',
		success : fillTable,
		error : function(response) {
			alert("Something went wrong! :(");
		}
	});
}

function getRooms(hotelId)
{	
	$.ajax({
		url : "/hotels/" + sessionStorage.getItem('hotelId') + "/rooms",
		type : "GET",
		dataType : 'json',
		success: showRooms,
		error : function(response) {
			alert("Something went wrong! :(");
		}
	});
}

function fillTable(data) {
	var hotels = data == null ? []
			: (data instanceof Array ? data : [ data ]);
	$('#hotelsTable').empty();
	$('#hotelsTable').append('<tr><th>Id</th><th>Name</th><th>Address</th><th>Description</th></tr>');
	
	$.each(hotels, function(index, hotel) {

		var tr = $('<tr></tr>');
		var form = $('<td><form class="formsedit" id="form' + hotel.id
				+ '"><input name="ident" value=' + hotel.id
				+ ' readonly></form></td><td><input name="name" form="form'
				+ hotel.id + '" value="' + hotel.name
				+ '"></td><td><input name="address" form="form' + hotel.id
				+ '" value="' + hotel.address
				+ '"></td><td><input name="description" form="form' + hotel.id
				+ '" value="' + hotel.description
				+ '"></td><td><button class="roomsBtn" id="rooms' + hotel.id  
				+ '">Rooms</button></td><td><input type="submit" form="form' + hotel.id
				+ '" id="bform' + hotel.id
				+ '"></td>');
		tr.append(form);
		$('#hotelsTable').append(tr);
	});
	
	$('.roomsBtn').on('click', function(e) {
		e.preventDefault();
		var iden = this.id.substring(5);
		sessionStorage.setItem('hotelId',iden);
		console.log(iden);

		getRooms(iden);

	});
	
	
	$('.formsedit').on('submit', function(e) {
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
		
		if(formData["name"] === ""){
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
			success : getHotels,
			error : function(data) {
				alert(data);
			}
		});
		
		alert("Saved");
	});

}