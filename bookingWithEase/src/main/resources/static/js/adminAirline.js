$(document).on('click', '#logoutClicked', function(e) {
	e.preventDefault();
	window.location.href = "index.html";
})



$(document).on('submit', '#destRegForm', function(e) {
	
	e.preventDefault();
	
	var formData = getFormData("#destRegForm");
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



//ispis

findDestination();

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

	var cont = $('#help');
	cont.empty();
	var form = $('<form align =left><input value=ID><input value= Name><input value = Address></form>');
	cont.append(form);

	$.each(d_list, function(index, destination) {
	
		var cont2 = $('<tr></tr>');
		var form =  $('<td><form class="formsedit" id="form' + destination.idAerodromes
				+ '"><input name="ident" value=' + destination.idAerodromes
				+ ' readonly></form></td><td><input name="nameAerodroms" form="form'
				+ destination.idAerodromes + '" value="' + destination.nameAerodroms
				+ '"></td><td><input name="address" form="form' + destination.idAerodromes
				+ '" value="' + destination.address
				
				+ '"></td><td><input type="submit" form="form' + destination.idAerodromes
				+ '" id="bform' + destination.idAerodromes
				+ '"></td><td><button class="delBtns" id="delBtn' + destination.idAerodromes
				+ '">Delete</button></td>');

		cont2.append(form);
		cont.append(cont2);
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

	
findFlights();

function findFlights() {
	$.ajax({
		type : 'GET',
		url : "/flights",
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
	var f_list = data == null ? []
			: (data instanceof Array ? data : [ data ]);
	var table = $('#tab-flights');
	$('#tab-flights').empty();
	$('#tab-flights')
			.append(
					'<tr><th>ID</th><th>Start destination</th><th>End destination</th><th>Date flight</th><th>Date landing</th><th>Length travel</th><th>Price ticket</th></tr>');

	$.each(f_list, function(index, flight) {

		var tr = $('<tr></tr>');
		var form = $('<td><form class="formsedit" id="form' + flight.id
				+ '"><input name="ident" value=' + flight.id
				+ ' readonly></form></td><td><input name="startD" form="form'
				+ flight.id + '" value="' + flight.startD
				+ '"></td><td><input name="finalD" form="form' + flight.id
				+ '" value="' + flight.finalD
				+ '"></td><td><input name="dateFligh" form="form' + flight.id
				+ '" value="' + flight.dateFligh
				+ '"></td><td><input name="dateLand" form="form'
				+ flight.id + '" value="' + flight.dateLand
				+ '"></td><td><input name="lengthTravel" form="form' + flight.id
				+ '" value="' + flight.lengthTravel
				+ '"></td><td><input name="priceTicket" form="form'+ flight.id
				+ '" value="' + flight.priceTicket
				+ '"></td><td><button class="delBtns" id="delBtn' + flight.id
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

