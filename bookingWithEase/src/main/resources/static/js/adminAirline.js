$(document).ready(function(){ 
	
	
	fillAirlineData();
	
	$('#pageTitle').html('Booking With Ease - <i>' + JSON.parse(localStorage.currentUser).company.name) + "</i>";
	
	$('#editCompanyForm').on('submit', function(e) {
		e.preventDefault();
		var iden = this.id;

		var formData = {};
		formData["id"] = localStorage.getItem('companyId');
		var s_data = $('#' + this.id).serializeArray();

		for (var i = 0; i < s_data.length; i++) {
			var record = s_data[i];
			if (record.name === "companyId") {
				formData["id"] = record.value;
			} else {
				formData[record.name] = record.value;
			}
		}
		
		var jsonData = JSON.stringify(formData);
		
		$.ajax({
			type : 'post',
			url : "/airlines/edit",
			contentType : 'application/json',
			dataType : 'json',
			beforeSend: function (xhr) {
		        xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		    },
			data : jsonData,
			success : function(data) {
				console.log(data);
				
				var editedUser = JSON.parse(localStorage.currentUser);
				editedUser.company.name = data.name;
				editedUser.company.address = data.address;
				editedUser.company.description = data.description;
				localStorage.setItem("currentUser", JSON.stringify(editedUser));
				$('#pageTitle').html('Booking With Ease - <i>' + JSON.parse(localStorage.currentUser).company.name) + "</i>";
				window.location.href = "homePageAirline.html";
			},
			error : function(data) {
				console.log(data);
			}
		});
	});
});

$(document).on('click', '#logoutClicked', function(e) {
	e.preventDefault();
	window.location.href = "index.html";
})

$(document).on('submit', '#destRegForm', function(e) {
	e.preventDefault();
	var formData = getFormData("#destRegForm");
	formData["airlineId"] = localStorage.getItem('companyId');
	var jsonData = JSON.stringify(formData);
	console.log(jsonData);
	$.ajax({
		url : "/destination",
		type : "POST",
		contentType : "application/json",
		data : jsonData,
		dataType : 'json',
		beforeSend: function (xhr) {
	        /* Authorization header */
	        xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
	    },
		success : function(response) {
			
			window.location.href = "homePageAirline.html";
		},
		error : function(response) {
			console.log(response)
			console.log("Something went wrong! :(");
		}
	});

});


function findAirline() {

	var airlineId = JSON.parse(localStorage.currentUser).company.id;
	$.ajax({
		type : 'GET',
		url : "/airlines/" + airlineId,
		dataType : "json",
		beforeSend: function (xhr) {
	        // Authorization header 
	        xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
	    },
		success : fillAirlineData,
		error : function(data) {
			console.log(data);
		}
	});
}


function fillAirlineData() {
	var data = JSON.parse(localStorage.currentUser).company;
	if($('#companyName') != null) {
		$('#companyName').val(data.name);
		$('#companyAddress').val(data.address);
		$('#companyDesc').val(data.description);
	}
	
	localStorage.setItem('companyId', data.id);
}

//ispis


function findDestination() {
	$.ajax({
		type : 'GET',
		url : "/destination/company/" + localStorage.getItem("companyId"),
		dataType : "json",
		beforeSend: function (xhr) {
	        /* Authorization header */
	        xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
	    },
		success : fillTable,
		error : function(data) {
			console.log(data);
		}
	});
}

function fillTable(data) {
	var d_list = data == null ? []
			: (data instanceof Array ? data : [ data ]);
	var table = $('#destinationTable');
	$('#destinationTable').empty();

	//var cont = $('#help');
	//cont.empty();
	$('#destinationTable').append('<tr><br><br><th></th><th>Name</th><th>Address</th></tr>');
	//cont.append(form);

	$.each(d_list, function(index, destination) {
	
		var tr = $('<tr></tr>');
		var form =  $('<td><form class="formsedit" id="form' + destination.id
				+ '"><input hidden name="ident" value=' + destination.id
				+ ' readonly></form></td><td><input name="name" form="form'
				+ destination.id + '" value="' + destination.name
				+ '"></td><td><input name="address" form="form' + destination.id
				+ '" value="' + destination.address
				
				+ '"></td><td><input type="submit" form="form' + destination.id
				+ '" id="bform' + destination.id
				+ '"></td><td><button class="delBtns" id="delBtn' + destination.id
				+ '">Delete</button></td>');

		tr.append(form);
		table.append(tr);
	}
	
	);
	
	$('.delBtns').on('click', function(e) {
		e.preventDefault();
		var iden = this.id.substring(6);
		console.log(iden);

		$.ajax({
			type : 'delete',
			url : "/destination/" + iden,
			beforeSend: function (xhr) {
		        /* Authorization header */
		        xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		    },
			success : function(response) {
				// alert("Vehicle deleted :)");
				window.location.href = "homePageAirline.html";
			},
			error : function(data) {
				alert(data);
			}
		});

	});
	
	
	$('.formsedit').on('submit', function(e) {
		e.preventDefault();
		var iden = this.id;
		// var formData = getFormData(iden);

		var formData = {};
		var s_data = $('#' + this.id).serializeArray();

		for (var i = 0; i < s_data.length; i++) {
			var record = s_data[i];
			console.log(record);
			if (record.name === "ident") {
				formData["id"] = record.value;
			} else {
				formData[record.name] = record.value;
			}
		}
		formData["airlineId"] = localStorage.getItem('companyId');
		console.log(formData);
		var jsonData = JSON.stringify(formData);
		$.ajax({
			type : 'post',
			url : "/destination/edit",
			contentType : 'application/json',
			dataType : 'json',
			beforeSend: function (xhr) {
		        xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		    },
			data : jsonData,
			success : findDestination,
			error : function(data) {
				alert(data);
			}
		});
	});
	

}

//dodavanje letova

$(document).on('submit', '#addFlightForm', function(e) {
	
	e.preventDefault();
	
	var formData = getFormData("#addFlightForm");
	//formData["startDestinationId"] = $("#startDestinationDropdown option:selected").val();
	//formData["endDestinationId"] = $("#endDestinationDropdown option:selected").val();
	formData["airlineId"] = localStorage.getItem('companyId');
	
	var jsonData = JSON.stringify(formData);
	console.log(formData);
	$.ajax({
		url : "/flights",
		type : "POST",
		contentType : "application/json",
		data : jsonData,
		dataType : 'json',
		beforeSend: function (xhr) {
	        /* Authorization header */
	        xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
	    },
		success : function(response) {
			alert("Flight saved :)");
			window.location.href = "homePageAirline.html";
		},
		error : function(response) {
			alert("Something went wrong! :(");
		}
	});

});

function findFlights() {
	$.ajax({
		type : 'GET',
		url : "/flights/airline/" + localStorage.getItem('companyId'),
		dataType : "json",
		beforeSend: function (xhr) {
	        /* Authorization header */
	        xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
	    },
		success : fillTable1,
		error : function(data) {
			alert(data);
		}
	});
}

function fillTable1(data) {
	
	var f_list = data == null ? []
			: (data instanceof Array ? data : [ data ]);
	var table = $('#tab-flights');
	$('#tab-flights').empty();
	$('#tab-flights')
			.append(
					'<tr><th>Flight number</th><th>Start destination </th><th>End destination </th><th>Date flight </th><th>Date landing </th><th>Length travel</th><th>Flight duration</th><th> Price ticket</th></tr>');

	$.each(f_list, function(index, flight) {
		
		var tr = $('<tr></tr>');
		var form = $('<td><form class="formseditf" id="form' + flight.id
				+ '"><input name="ident" value=' + flight.number
				+ ' readonly><input hidden name="idenf" value=' + flight.id + ' readonly>'
				+ '</form></td><td><select id="startDestinationDropdownf' + flight.id 
				+ '"name ="startDestinationId"></select></td>'
				+ '<td><select id="endDestinationDropdownf' + flight.id
				+ '"name ="endDestinationId"></select></td>'
				+ '<td><input name="dateFligh" form="form' + flight.id
				+ '" value="' + flight.dateFligh.substring(0, 10)
				+ '"></td><td><input name="dateLand" form="form'
				+ flight.id + '" value="' + flight.dateLand.substring(0, 10)
				+ '"></td><td><input name="lengthTravel" form="form' + flight.id
				+ '" value="' + flight.lengthTravel
				+ '"></td><td><input name="timeTravel" form="form'+ flight.id
				+ '" value="' + flight.timeTravel
				+ '"></td><td><input name="priceTicket" form="form'+ flight.id
				+ '" value="' + flight.priceTicket
				+ '"></td><td><input type="submit" form="form' + flight.id
				+ '" id="bform' + flight.id
				+ '"></td><td><button class="delBtns1" id="delBtn' + flight.id
				+ '">Delete</button></td>');
		tr.append(form);
		table.append(tr);
		findDestinationByFlightId2(flight);
	});

	
	
	$('.delBtns1').on('click', function(e) {
		e.preventDefault();
		var iden = this.id.substring(6);
		
		$.ajax({
			type : 'delete',
			url : "/flights/" + iden,
			beforeSend: function (xhr) {
		        /* Authorization header */
		        xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		    },
			success : function(response) {
				// alert("Flight deleted :)");
				window.location.href = "homePageAirline.html";
			},
			error : function(data) {
				alert(data);
			}
		});

	});
	
	$('.formseditf').on('submit', function(e) {
	
		e.preventDefault();
		var iden = this.id;
	
		
		var formData = {};
		var s_data = $('#' + this.id).serializeArray();

		for (var i = 0; i < s_data.length; i++) {
			var record = s_data[i];
			if (record.name === "idenf") {
				formData["id"] = record.value;
			} else {
				formData[record.name] = record.value;
			}
		}
		var onlyId = iden.split("m")[1];
		formData["startDestinationId"] = $("#startDestinationDropdownf" + onlyId + " option:selected").val();
		formData["startDestination"] = $("#startDestinationDropdownf" + onlyId + " option:selected").text();
		formData["endDestinationId"] = $("#endDestinationDropdownf" + onlyId + " option:selected").val();
		formData["endDestination"] = $("#endDestinationDropdownf" + onlyId + " option:selected").text();
		console.log(formData);
		var jsonData = JSON.stringify(formData);
		$.ajax({
			type : 'post',
			url : "/flights/edit",
			contentType : 'application/json',
			dataType : 'json',
			
			data : jsonData,
			success : findFlights,
			error : function(data) {
				alert("Error while editing flight");
			}
		});
	});

}




function findDestinationByFlightId() {
	$.ajax({
		type : 'GET',
		url : "/destination/company/" + localStorage.getItem('companyId'),
		dataType : "json",
		beforeSend: function (xhr) {
	        /* Authorization header */
	        xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
	    },
		success : fillDestinationSelect,
		error : function(data) {
			alert(data);
		}
	});
}

function findDestinationByFlightId2(flight) {
	$.ajax({
		type : 'GET',
		url : "/destination/company/" + localStorage.getItem('companyId'),
		dataType : "json",
		beforeSend: function (xhr) {
	        /* Authorization header */
	        xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
	    },
		success : function(data) {
		fillDestinationSelect2(data, flight)
		},
		error : function(data) {
			alert(data);
		}
	});
}

function fillDestinationSelect(data) {
	console.log(data);
	var s = '<option value="-1">Please Select Start destination</option>';  
	for (var i = 0; i < data.length; i++) {  
    	s += '<option value="' + data[i].id + '">' + data[i].name + '</option>';  
   	}  
   	$("#startDestinationDropdown").html(s);
   	
   	$('#startDestinationDropdown').on('change', function(e) {
		e.preventDefault();
		var iden = $(this).children("option:selected").val();
		
		var endDestinationId = $("#endDestinationDropdown option:selected").val();
		
		if(endDestinationId != -1) {
			if(endDestinationId == iden) {
				alert('Start destination is same as end destination!');
				$(this).children("option:selected").change();
			}
		}

	});
   	
   	s = '<option value="-1">Please Select End destination</option>';  
	for (var i = 0; i < data.length; i++) {  
    	s += '<option value="' + data[i].id + '">' + data[i].name + '</option>';  
   	}  
   	$("#endDestinationDropdown").html(s); 
   	
   	$('#endDestinationDropdown').on('change', function(e) {
		e.preventDefault();
		var iden = $(this).children("option:selected").val();
		
		var startestinationId = $("#startDestinationDropdown option:selected").val();
		
		if(startestinationId != -1) {
			if(startestinationId == iden) {
				$(this).children("option:selected").val(-1).change();
				alert('End destination is same as start destination!');
			}
		}
	});
}

function fillDestinationSelect2(data, flight) {
	
	var s = '';  
	for (var i = 0; i < data.length; i++) {  
		if(data[i].address + ' ('+ data[i].name + ')' == flight.startD)
			s += '<option selected value="' + data[i].id + '">' + data[i].address + ' ('+ data[i].name + ')' + '</option>'; 
		else 
			s += '<option value="' + data[i].id + '">' + data[i].address + ' ('+ data[i].name + ')' + '</option>'; 
   	}  
   	$("#startDestinationDropdownf" + flight.id).html(s);
   	
   
   	
   	s = '';  
	for (var i = 0; i < data.length; i++) {  
		if(data[i].address + ' ('+ data[i].name + ')' == flight.finalD)
			s += '<option selected value="' + data[i].id + '">' + data[i].address + ' ('+ data[i].name + ')' + '</option>';  
		else 
			s += '<option value="' + data[i].id + '">' + data[i].address + ' ('+ data[i].name + ')' + '</option>';
   	}  
   	$("#endDestinationDropdownf"+ flight.id).html(s); 
   	
   	
}








function findQuickReservations() {
	var currentUser = JSON.parse(localStorage.getItem('currentUser'));
	var airlineId = currentUser.company.id;

	$.ajax({
		url: "/quickFlightReservation/" + airlineId + "/quickReservations",
		type: "GET",
		dataType: 'json',
		beforeSend: function(xhr) {
			/* Authorization header */
			xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		},
		success: fillQuickFlightReservations,
		error: function(response) {
			alert("Something went wrong while getting quick flight reservations! :(");
		}
	});
}

function fillQuickFlightReservations(data) {
	var qfrs = data == null ? [] : (data instanceof Array ? data : [data]);

	var qfrs_dict = arrayToObject(qfrs, "id");
	localStorage.setItem("qfrs", JSON.stringify(qfrs_dict));

	$('#allQfrTable').empty();
	$('#allQfrTable').append('<tr><th>Id</th><th>Flight number</th><th>Seat id</th><th>Date</th><th>Price</th><th>Discount</th><th>Final price</th><th>&nbsp;</th><th>&nbsp;</th></tr>');

	$.each(qfrs, function(index, qfr) {

		var tr = $('<tr></tr>');

		var qfrTr = $('<td>' + qfr.qfrId + '</td>' + '<td id="qfr_' + qfr.qfrId
			+ '">' + qfr.flightNumber + '</td>' + '<td>'
			+ qfr.seatId + '</td>' + '<td>'
			+ qfr.dateFligh.substring(0, 10) + '</td><td>'
			+ qfr.originalPrice + '&#8364;</td><td>' + qfr.discount + '%<td>'
			+ qfr.currentPrice + '&#8364;</td>'
			+ '<td><button class="edit_qfr_btn" id="edit_qfr_' + qfr.qfrId
			+ '">Edit</button></td>'
			+ '</td><td><button class="delete_qfr_btn" id="delete_qfr_'
			+ qfr.qfrId + '">Delete</button></td>');

		tr.append(qfrTr);

		$('#allQfrTable').append(tr);
	});

	$('.delete_qfr_btn').on('click',function(e) {
		e.preventDefault();

		var currentUser = JSON.parse(localStorage.getItem('currentUser'));
		var qfrId = this.id.substring(11);
		var airlineId = currentUser.company.id;
		
		alertify.confirm('Delete quick flight reservation', 'Are you sure?',
			function() {
				deleteQuickFlightReservation(airlineId, qfrId,
					function(data) {
						alertify.notify("Quick flight reservation deleted!");
						fillQuickFlightReservations(data);
					}
				);
			},
			function() {
				alertify.notify("Canceled");
			}
		);
	});
	
	$('.edit_qfr_btn').on('click', function(e) {
		e.preventDefault();

		var currentUser = JSON.parse(localStorage.getItem('currentUser'));
		var qfrId = this.id.substring(9);
		var airlineId = currentUser.company.id;

		//getSpecialOffers(hotelId, addSpecialOffersToEditQrr);
		fillEditQfrForm(qfrId);
		openCity(event, 'editQuickFlightReservation');

	});
}

function deleteQuickFlightReservation(airlineId, qfrId, callback) {
	$.ajax({
		url : "/quickFlightReservation/" + airlineId + "/quickReservations/" + qfrId,
		type : "DELETE",
		dataType : 'json',
		beforeSend : function(xhr) {
			/* Authorization header */
			xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		},
		success : callback,
		error : function(response) {
			alert("Something went wrong while deleting qfr!");
		}
	});
}

function fillEditQrrForm(qfrId) {
	var qrrInfo = JSON.parse(localStorage.getItem('qrrs'))[qrrId];
	// alert(JSON.stringify(qrrInfo));
	$('#qrr-id-edit').val(qrrInfo.id);
	$('#qrr-roomNumber-edit').val(qrrInfo.room.roomNumber);
	$('#qrr-checkIn-edit').val(qrrInfo.checkInDate.substring(0, 10));
	$('#qrr-checkOut-edit').val(qrrInfo.checkOutDate.substring(0, 10));
	$('#qrr-discount-edit').val(qrrInfo.discount);

	var checks = $('.qrr_so_checkbox');

	$.each(checks, function(index, check) {

		var sos = qrrInfo.specialOffers;
		for ( var v in sos) {

			if ($(check).val() == sos[v].id) {
				$('#qrr_so_checkbox_' + sos[v].id).prop('checked', true);
			}
		}
	});
}

function fillEditQfrForm(qfrId) {
	var qfrInfo = JSON.parse(localStorage.getItem('qfrs'))[qfrId];

	$('#qfr-id-edit').val(qfrInfo.id);
	$('#qfr-seatId-edit').val(qfrInfo.seatId);
	$('#qfr-discount-edit').val(qfrInfo.discount);
}

function findFlightsSelect() {
	var currentUser = JSON.parse(localStorage.getItem('currentUser'));
	var id = currentUser.company.id;
	
	$.ajax({
		url : "/flights/airline/" + id,
		type : "GET",
		dataType : 'json',
		beforeSend : function(xhr) {
			/* Authorization header */
			xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		},
		success : fillFlightsSelect,
		error : function(response) {
			alert("Something went wrong while deleting qfr!");
		}
	});
}

function fillFlightsSelect(data){
	var flights = data == null ? [] : (data instanceof Array ? data : [ data ]);
	
	var select = document.getElementById("qfrAdd-flight-select"); 

	$.each(flights, function(index, flight) {
	    var el = document.createElement("option");
	    el.textContent = flight.number + "(" + flight.id + ")";
	    el.value = flight.number + "(" + flight.id + ")";
		el.id = flight.id;
	    select.appendChild(el);
	});
}