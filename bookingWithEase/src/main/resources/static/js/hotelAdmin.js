$(document).ready(function() {
	var currentUser = JSON.parse(localStorage.getItem('currentUser'));

	getHotel(currentUser);
});

$(document).on('click', '#addNewRoom', function(e) {
	e.preventDefault();
	var currentUser = JSON.parse(localStorage.getItem('currentUser'));
	getRooms(currentUser.company.id);
});

$(document).on('click', '#special_offers_btn', function(e) {
	e.preventDefault();

	getSpecialOffers(fillSpecialOffers);

});

$(document).on('click', '#addSpecialOfferBtn', function(e) {
	e.preventDefault();

	addSpecialOffer(function() {
		alert("Special offer added");
		getSpecialOffers(fillSpecialOffers);
	});

});

$(document).on('click', '#cancelSpecialOfferBtn', function(e) {
	e.preventDefault();

	openCity(event, 'allSpecialOffers');
	getSpecialOffers(fillSpecialOffers);
});

$(document).on('click', '#edit_so_btn', function(e) {
	e.preventDefault();

	var formData = getFormData('#editSpecialOfferForm');
	var currentUser = JSON.parse(localStorage.getItem('currentUser'));
	var soId = localStorage.getItem('updateSoId');
	formData["id"] = soId;
	formData["hotelId"] = currentUser.company.id;
	var jsonData = JSON.stringify(formData);	
	
	updateSpecialOffer(currentUser.company.id, soId, jsonData, function(data) {
		alert("Saved :)");	
		openCity(event, 'allSpecialOffers');
		fillSpecialOffers(data);

	});

});

function addSpecialOffer(callback) {
	var currentUser = JSON.parse(localStorage.getItem('currentUser'));
	var hotelId = currentUser.company.id;

	var formData = getFormData('#addSpecialOfferForm');
	var jsonData = JSON.stringify(formData);

	$.ajax({
		url : "/hotels/" + hotelId + "/specialOffers",
		type : "POST",
		dataType : 'json',
		contentType : 'application/json',
		data : jsonData,
		beforeSend : function(xhr) {
			/* Authorization header */
			xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		},
		success : callback,
		error : function(response) {
			alert("Something went wrong! :(");
		}
	});

}

function getSpecialOffers(callback) {
	var currentUser = JSON.parse(localStorage.getItem('currentUser'));
	var hotelId = currentUser.company.id;

	$.ajax({
		url : "/hotels/" + hotelId + "/specialOffers",
		type : "GET",
		dataType : 'json',
		beforeSend : function(xhr) {
			/* Authorization header */
			xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		},
		success : callback,
		error : function(response) {
			alert("Something went wrong! :(");
		}
	});
}

function deleteSpecialOffer(hotelId, soId,callback)
{	
	$.ajax({
		url : "/hotels/" + hotelId + "/specialOffers/" + soId,
		type : "DELETE",
		dataType : 'json',
		beforeSend : function(xhr) {
			/* Authorization header */
			xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		},
		success : callback,
		error : function(response) {
			alert("Something went wrong! :(");
		}
	});
}

function updateSpecialOffer(hotelId, soId, jsonData, callback)
{
	$.ajax({
		type : 'put',
		url : "/hotels/" + hotelId + "/specialOffers/" + soId,
		contentType : 'application/json',
		dataType : 'json',
		data : jsonData,
		beforeSend : function(xhr) {
			xhr.setRequestHeader("Authorization", "Bearer "
					+ getJwtToken());
		},
		success : callback,
		error : function(data) {
			alert(data);
		}
	});
}

function getHotel(user) {
	var hotelId = user.company.id;

	$.ajax({
		url : "/hotels/" + hotelId,
		type : "GET",
		dataType : 'json',
		beforeSend : function(xhr) {
			/* Authorization header */
			xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		},
		success : fillHotelInfo,
		error : function(response) {
			alert("Something went wrong! :(");
		}
	});
}

function fillHotelInfo(data) {
	$('#hotel_name').empty().append(data.name);
	$('#hotel_address').empty().append(data.address);
	$('#hotel_description').empty().append(data.description);
	$('#hotel_stars').empty().append(data.stars);
	$('#hotel_rating').empty().append(data.rating);

	$('#edit_hotel_name').val(data.name);
	$('#edit_hotel_address').val(data.address);
	$('#edit_hotel_description').val(data.description);
	$('#edit_hotel_stars').val(data.stars);

	$('#edit_hotel_form').on(
			'submit',
			function(e) {

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

				var currentUser = JSON.parse(localStorage
						.getItem('currentUser'));

				formData['id'] = currentUser.company.id;

				if (formData["name"] === "") {
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
					beforeSend : function(xhr) {
						/* Authorization header */
						xhr.setRequestHeader("Authorization", "Bearer "
								+ getJwtToken());
					},
					success : getHotels,
					error : function(data) {
						alert(data);
					}
				});

				alert("Saved");
				getHotel(JSON.parse(localStorage.getItem('currentUser')));
			});

}

function fillSpecialOffers(data) {
	var specialOffers = data == null ? [] : (data instanceof Array ? data
			: [ data ]);

	$('#specialOffersTable').empty();
	$('#specialOffersTable')
			.append(
					'<tr><th>Id</th><th>Name</th><th>Description</th><th>Additional payment</th><th>&nbsp;</th><th>&nbsp;</th></tr>');

	$.each(specialOffers, function(index, specialOffer) {
		localStorage.setItem("specialOffer_" + specialOffer.id, JSON
				.stringify(specialOffer));

		var tr = $('<tr></tr>');
		var soTr = $('<td>' + specialOffer.id + '</td>' + '<td id="tso_'
				+ specialOffer.id + '">' + specialOffer.name + '</td>' + '<td>'
				+ specialOffer.description + '</td>' + '<td>'
				+ specialOffer.price + '</td>'
				+ '<td><button class="edit_so_btn" id="edit_so_'
				+ specialOffer.id + '">Edit</button></td>'
				+ '</td><td><button class="delete_so_btn" id="delete_so_'
				+ specialOffer.id + '">Delete</button></td>');

		tr.append(soTr);
		$('#specialOffersTable').append(tr);
	});
	
	$('.delete_so_btn').on('click', function(e) {
		e.preventDefault();

		var currentUser = JSON.parse(localStorage.getItem('currentUser'));
		var soId = this.id.substring(10);

		var hotelId = currentUser.company.id;

		deleteSpecialOffer(hotelId, soId, function(data) {
			
			alert("Special offer deleted!");
			fillSpecialOffers(data);

		});
	});

	$('.edit_so_btn').on('click', function(e) {
		e.preventDefault();

		var currentUser = JSON.parse(localStorage.getItem('currentUser'));
		var soId = this.id.substring(8);
		var hotelId = currentUser.company.id;
		openCity(event, 'editSpecialOffer');

		var so = JSON.parse(localStorage.getItem('specialOffer_' + soId));
		localStorage.setItem("updateSoId", soId);

		$('#so_name').val(so.name);
		$('#so_description').val(so.description);
		$('#so_price').val(so.price);

	});

}
