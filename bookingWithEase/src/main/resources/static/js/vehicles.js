findVehicles();

function findVehicles() {
	var value = localStorage.getItem('showVeh');
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
	});
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
