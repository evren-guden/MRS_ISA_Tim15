$( document ).ready(function() {
	findVehicles();
});


function findVehicles() {
	var value = localStorage.getItem('showVeh');
	if(localStorage.getItem('vehicleBegin') != null){
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
						vehDiv
								.append('<button class="show_details_btn">Make reservation</button>');

						counter++;
						vehsDiv.append(vehDiv);

					});

}


$(document).on('submit', '#formsrcvehs', function(e) {
	e.preventDefault();
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
