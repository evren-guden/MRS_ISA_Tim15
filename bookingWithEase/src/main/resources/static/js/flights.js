findFlights();

function findFlights() {
	$.ajax({
		type : 'GET',
		url : localStorage.getItem('showFlt') != null?"/flights/airline/" + localStorage.getItem('showFlt'): "/flights",
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
	var flights_list = data == null ? []
			: (data instanceof Array ? data : [ data ]);

	var airsDiv = $('#airsDiv');
	airsDiv.empty();
	var counter = 0;

	$.each(flights_list, function(index, flight) {
				var airDiv = $('<div class="airDiv" id="airDiv'
						+ counter
						+ '" style="bottom:'
						+ (60 - counter * 40)
						+ '%; top:'
						+ (3 + counter * 40)
						+ '%;"'
						+ '>'
						+ '<img src="../images/air.jpg" height = 90% width= 18%>'
						+ '<h3>' + flight.startD + ' - ' + flight.finalD + '</h3>');

				airDiv.append('<p>' + flight.airline.name + '</p></div>');
				airDiv.append('<div class="guest_ratings"> Price: ' + flight.priceTicket + '€' + '</div>');
				airDiv.append('<div class="flight_duration"> Duration: ' + flight.lengthTravel + 'min' + '</div>');
				airDiv.append('<button class="show_flights_btn" id="shows_' + flight.id + '">Show seats</button>');

				counter++;
				airsDiv.append(airDiv);

			});
					
	$('.show_flights_btn').on('click', function(e) {
		e.preventDefault();
		var iden = this.id.substring(6);
		if (localStorage.getItem("showSeats") === null) {
			  localStorage.removeItem('showSeats');
			}
		localStorage.setItem('showSeats', iden);
		window.location.href = "seats.html";
	});
	
}

$(document).on('submit', '#formsrc', function(e) {
	e.preventDefault();
	var formData = getFormData("#formsrc");
	var jsonData = JSON.stringify(formData);
	$.ajax({
		type : 'POST',
		url : '/flights/search',
		contentType : 'application/json',
		dataType : 'json',
		data : jsonData,
		beforeSend : function(xhr) {
			/* Authorization header */
			xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		},
		success : fillTable
	});

});
