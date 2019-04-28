$(document).ready(function() {
	var currentUser = JSON.parse(localStorage.getItem('currentUser'));

	getHotel(currentUser);
});

$(document).on('click', '#addNewRoom', function(e) {
	getRooms(100);
});

function getHotel(user) {
	var hotelId = 100;
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

				formData["id"] = 100;

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
