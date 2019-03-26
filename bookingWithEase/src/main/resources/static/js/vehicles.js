findVehicles();

function findVehicles() {
	$.ajax({
		type : 'GET',
		url : "/vehicles",
		dataType : "json",
		success : fillTable,
		error : function(data) {
			alert(data);
		}
	});
}

function fillTable(data) {
	var veh_list = data == null ? []
			: (data instanceof Array ? data : [ data ]);
	var table = $('#tab-vehicles');
	$('#tab-vehicles').empty();
	$('#tab-vehicles')
			.append(
					'<tr><th>Id</th><th>Color</th><th>Type</th><th>Gear</th><th>Registration number</th></tr>');
	$.each(veh_list, function(index, vehicle) {
		console.log("each");
		var form = $('<td><form class="formsedit" id="form' + vehicle.id
				+ '"><input name="id" value=' + vehicle.id
				+ ' disabled></form></td><td><input form="form' + vehicle.id
				+ '" name="color" value=' + vehicle.color
				+ '></td><td><input form="form' + vehicle.id
				+ '" name="type" value=' + vehicle.type
				+ '></td><td><input form="form' + vehicle.id
				+ '" name="gear" value=' + vehicle.gear
				+ '></td><td><input form="form' + vehicle.id
				+ '" name="registrationNumber" value=' + vehicle.registrationNumber
				+ '></td><td><button type="submit" id="bform'+ vehicle.id +'" form="form' + vehicle.id
				+ '" class="editforms">Edit</td>');
		var tr = $('<tr></tr>');

		tr.append(form);
		table.append(tr);
	}
	
	);
	
$('.formsedit').on('submit', function(e) {
		e.preventDefault();
		var iden = this.id;
		var formData = getFormData(iden);
		console.log(iden);
		var jsonData = JSON.stringify(formData);
		console.log(jsonData);
		$.ajax({
			type : 'post',
			url : "/vehicles/edit",
			contentType: 'application/json',
			dataType: 'json',
			success : findVehicles,
			error : function(data) {
				alert(data);
			}
		});
	});
	

}


$(document).on('click', '#init-vehs', function(e) {
	$.ajax({
		type : 'GET',
		url : "/vehicles/initial",
		success : fillTable,
		error : function(data) {
			alert(data);
		}
	});
});

