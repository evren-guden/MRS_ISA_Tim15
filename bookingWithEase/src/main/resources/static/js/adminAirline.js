$(document).on('click', '#logoutClicked', function(e) {
	e.preventDefault();
	window.location.href = "index.html";
})

$(document).on('submit', '#destRegForm', function(e) {
	e.preventDefault();
	var formData = getFormData("#destRegForm");
	formData["airlineId"] = localStorage.getItem('companyId');
	var jsonData = JSON.stringify(formData);
	
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
			alert("Airport saved :)");
			window.location.href = "homePageAirline.html";
		},
		error : function(response) {
			alert("Something went wrong! :(");
		}
	});

});

//find all airlines
findAirlines();

function findAirlines() {
	$.ajax({
		type : 'GET',
		url : "/airlines",
		dataType : "json",
		beforeSend: function (xhr) {
	        /* Authorization header */
	        xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
	    },
		success : fillDropdown,
		error : function(data) {
			alert(data);
		}
	});
}

function fillDropdown(data) {
	var s = '<option value="-1">Please Select Airline Company</option>';  
	for (var i = 0; i < data.length; i++) {  
    	s += '<option value="' + data[i].id + '">' + data[i].name + '</option>';  
   	}  
   	$("#airlinesDropdown").html(s); 
   	
   	$('#airlinesDropdown').on('change', function(e) {
		e.preventDefault();
		var iden = $(this).children("option:selected").val();

		$.ajax({
			type : 'GET',
			url : "/airlines/" + iden,
			beforeSend: function (xhr) {
		        /* Authorization header */
		        xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		    },
			success : fillAirlineData,
			error : function(data) {
				alert(data);
			}
		});
		
		findDestinationByFlightId(iden);

	});
	
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
				alert(data);
			},
			error : function(data) {
				alert(data);
			}
		});
	});
}

function fillAirlineData(data) {
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
		url : "/destination",
		dataType : "json",
		beforeSend: function (xhr) {
	        /* Authorization header */
	        xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
	    },
		success : fillTable,
		error : function(data) {
			alert(data);
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
	$('#destinationTable').append('<tr><br><br><th>ID</th><th>Name</th><th>Address</th></tr>');
	//cont.append(form);

	$.each(d_list, function(index, destination) {
	
		var tr = $('<tr></tr>');
		var form =  $('<td><form class="formsedit" id="form' + destination.id
				+ '"><input name="ident" value=' + destination.id
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
			if (record.name === "ident") {
				formData["id"] = record.value;
			} else {
				formData[record.name] = record.value;
			}
		}

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
	formData["startDestinationId"] = $("#startDestinationDropdown option:selected").val();
	formData["endDestinationId"] = $("#endDestinationDropdown option:selected").val();
	formData["airlineId"] = localStorage.getItem('companyId');
	var jsonData = JSON.stringify(formData);

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
		var form = $('<td><form class="formsedit" id="form' + flight.id
				+ '"><input name="ident" value=' + flight.number
				+ ' readonly></form></td><td><input name="startD" form="form'
				+ flight.id + '" value="' + flight.startD
				+ '"></td><td><input name="finalD" form="form' + flight.id
				+ '" value="' + flight.finalD
				+ '"></td><td><input name="dateFligh" form="form' + flight.id
				+ '" value="' + flight.dateFligh.substring(0, 10)
				+ '"></td><td><input name="dateLand" form="form'
				+ flight.id + '" value="' + flight.dateLand.substring(0, 10)
				+ '"></td><td><input name="lengthTravel" form="form' + flight.id
				+ '" value="' + flight.lengthTravel
				+ '"></td><td><input name="timeTravel" form="form'+ flight.id
				+ '" value="' + flight.timeTravel
				+ '"></td><td><input name="priceTicket" form="form'+ flight.id
				+ '" value="' + flight.priceTicket
				+ '"></td><td><button class="delBtns1" id="delBtn' + flight.id
				+ '">Delete</button></td>');
		tr.append(form);
		table.append(tr);
	}

	);
	$('.delBtns1').on('click', function(e) {
		e.preventDefault();
		var iden = this.id.substring(6);
		console.log(iden);

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

	$('.formsedit').on('submit', function(e) {
		e.preventDefault();
		var iden = this.id;
		// var formData = getFormData(iden);

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

		var jsonData = JSON.stringify(formData);
		$.ajax({
			type : 'post',
			url : "/flights/edit",
			contentType : 'application/json',
			dataType : 'json',
			
			data : jsonData,
			success : findFlights,
			error : function(data) {
				alert(data);
			}
		});
	});

}


function findDestinationByFlightId(airlineId) {
	$.ajax({
		type : 'GET',
		url : "/destination/company/" + airlineId,
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

function fillDestinationSelect(data) {
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