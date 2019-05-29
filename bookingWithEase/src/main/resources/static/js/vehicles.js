$( document ).ready(function() {
	findVehicles();
});


function findVehicles() {
	var value = localStorage.getItem('showVeh');
	if(localStorage.getItem('vehicleBegin') != null){
		$('#racIdHidden').val(value);
		$('#vehpickup').val(localStorage.getItem('vehicleBegin'));
		$('#vehdropoff').val(localStorage.getItem('vehicleEnd'));
		
		var formData = getFormData("#formsrcvehs");
		var jsonData = JSON.stringify(formData);
		$.ajax({
			type : 'POST',
			url : '/rentacars/vehicles/search',
			contentType : 'application/json',
			dataType : 'json',
			data : jsonData,
			beforeSend : function(xhr) {
				/* Authorization header */
				xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
			},
			success : fillTable
		});
		
		localStorage.removeItem('vehicleBegin');
		localStorage.removeItem('vehicleEnd');
	}else{
	
	
	$.ajax({
		type : 'GET',
		url : "/rentacars/" + value + "/vehicles",
		dataType : "json",
		beforeSend : function(xhr) {
			xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		},
		success : fillTable,
		error : function(data) {
			alert(data);
		}
	});}
}

function fillTable(data) {
	var veh_list = data == null ? []
			: (data instanceof Array ? data : [ data ]);

	var vehsDiv = $('#vehsDiv');
	vehsDiv.empty();
	var counter = 0;

	$
			.each(
					veh_list,
					function(index, veh) {
						var vehDiv = $('<div class="company-div" id="vehDiv_'
								+ counter
								+ '" style="bottom:'
								+ (60 - counter * 40)
								+ '%; top:'
								+ (3 + counter * 40)
								+ '%;"'
								+ '>'
								+ '<img src="../images/cars.jpg" height = 150 width= 150>'
								+ '<h3>' + veh.registrationNumber + '</h3>');

						vehDiv.append('<p>' + veh.type + '</p>' + '</div>');
						vehDiv
								.append('<p style="position: absolute;top:65%;left:25%;">'
										+ veh.gear + '</p>');
						if (veh.pricePerDay != undefined && veh.pricePerDay != 0) {
							localStorage.setItem('totalPriceVeh' + veh.id,
									veh.pricePerDay);
							vehDiv.append('<p class="totalPrice">'
									+ veh.pricePerDay + '&#8364;</p>');
						}
						
						vehDiv
								.append('<button class="show_details_btn reserve_veh" id="reserve_veh' + veh.id + '">Make reservation</button>');

						counter++;
						vehsDiv.append(vehDiv);

					});
	$('.reserve_veh').on('click', function(e) {
		e.preventDefault();
		
		if(localStorage.getItem('currentUser') === null){
			alertify.error('Please, log in');
			return;
		}
		
		var vehId = this.id.substring(11);
		var vrData = collectVehicleReservationData(vehId);
		
		if(vrData == null){
			return;
		}
		
		if(!validateVehicleSearchData()){
			return;
		}
		
		localStorage.setItem('vrData',JSON.stringify(vrData) )

		//reserveVehicle(vrData);
		openFormRSO();
	});
}


$(document).on('submit', '#formsrcvehs', function(e) {
	e.preventDefault();
	
	if(!validateVehicleSearchData()){
		return;
	}
	
	$('#racIdHidden').val(localStorage.getItem('showVeh'));
	var formData = getFormData("#formsrcvehs");
	var jsonData = JSON.stringify(formData);
	$.ajax({
		type : 'POST',
		url : '/rentacars/vehicles/search',
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

function validateVehicleSearchData(){
	var date1 = $('#vehpickup').val();
	var date2 = $('#vehdropoff').val();
	var now = new Date();
	if(new Date(date1).getTime() < now.getTime()){
		alertify.alert("Date not valid", "Pick up date is past");
		return false;
	}
	
	if(date2 < date1){
		alertify.alert("Date not valid", "Drop off date must be greater than pick up date");
		return false;
	}
	return true;
}


// reservations
function reserveVehicle(vrData) {

	alertify.set('notifier', 'position', 'top-right');

	var racId = localStorage.getItem('showVeh');
	
	if(vrData['checkInDate'] === "" || vrData['checkOutDate'] === ""){
		alertify.error('Please, choose dates.');
		return;
	}
	
	//vrData['totalPrice'] = 

	var jsonData = JSON.parse(vrData);
	// alert('rr ' + jsonData);
	$.ajax({
		type : 'POST',
		url : '/vehicles/' + jsonData['vehicle_id'] + '/reservations',
		contentType : 'application/json',
		dataType : 'json',
		data : vrData,
		beforeSend : function(xhr) {
			/* Authorization header */
			xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		},
		success : function() {
			alertify.notify('Vehicle reserved!');
			window.location.replace("vehicles.html");
		},
		statusCode : {
			403 : function() {

				alertify.error('You must first log in!');
			}
		}
	});
}

function collectVehicleReservationData(vehicleId) {

	var vrData = {};
	vrData['vehicle_id'] = vehicleId;
	vrData['racId'] = localStorage.getItem('showVeh');
	vrData['checkInDate'] = $('#vehpickup').val();
	if(!vrData['checkInDate']){
		alertify.alert("", "Please, enter pick up date");
		return;
	}
	vrData['checkOutDate'] = $('#vehdropoff').val();
	
	if(!vrData['checkOutDate']){
		alertify.alert("", "Please, enter drop off date");
		return null;
	}
	
	if (JSON.parse(localStorage.getItem('currentUser')) != null)
		vrData['user_id'] = JSON.parse(localStorage.getItem('currentUser')).id;

	var vehPrice = localStorage.getItem('totalPriceVeh' + vehicleId);
	vrData['totalPrice'] = vehPrice * differenceBetweenDates(vrData['checkInDate'], vrData['checkOutDate']);

	return vrData;

}

function getRACSpecialOffers(racId) {

	$.ajax({
		url : "/rentacars/" + racId + "/specialOffers",
		type : "GET",
		dataType : 'json',
		beforeSend : function(xhr) {
			xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		},
		success : fillRACPopupForm,
		error : function(response) {
			alert("Something went wrong! :(");
		}
	});
}



function fillRACPopupForm(data) {

	var specialOffers = data == null ? [] : (data instanceof Array ? data
			: [ data ]);

	var formContainer = $('#check_container');
	formContainer.empty();
	var form = $('<form id="so_form"></form>');
	var vrData = JSON.parse(localStorage.getItem('vrData'));
	var price = localStorage.getItem('totalPriceVeh' + vrData['vehicleId']);
	$('#p_price').empty().append(price + '&#8364;');
	localStorage.setItem('currentPrice', price);
	$.each(specialOffers, function(index, so) {
		form.append('<input type="checkbox" class="so_check" name="' + so.name
				+ '" value="' + so.id + '"price=' + so.price + '" id="' + so.id
				+ '"><label for="' + so.id + '">' + so.name + '&nbsp;&nbsp;+'
				+ so.price + '&#8364</label>');
	});

	formContainer.append(form);

}

function openFormRSO() {

	var racId = localStorage.getItem('showVeh');
	getRACSpecialOffers(racId);

	document.getElementById("myVehResForm").style.display = "block";
}

function closeForm() {
	document.getElementById("myVehResForm").style.display = "none";
}


$(document).on(
		'click',
		'#so_rac_confirm_btn',
		function(e) {
			e.preventDefault();
			var soData = getFormData("#so_form");

			var so_list = [];
			$.each(soData, function(index, so) {
				so_list.push(so);
			});
			var vrData = JSON.parse(localStorage.getItem('vrData'));
			vrData['specialOffers'] = so_list;
			closeForm();
			var message = "Check in: " + vrData['checkInDate'] + "</br></br>";
			message += "Check out: " + vrData['checkOutDate'] + "</br></br>";
			message += "Total price: " + vrData['totalPrice'] 
					+ "</br></br>"

			alertify.confirm('Booking confirmation', message, function() {
				reserveVehicle(JSON.stringify(vrData));
			}, function() {
				alertify.notify("Booking canceled");
			});

		});

function differenceBetweenDates(date1, date2){

	// end - start returns difference in milliseconds 
	var diff = new Date(new Date(date2).getTime() - new Date(date1).getTime());

	// get days
	var days = diff/1000/60/60/24;
	
	return days;
}