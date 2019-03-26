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
	// $('#tab-vehicles')
	// .append(
	// '<tr><th>Id</th><th>Color</th><th>Type</th><th>Gear</th><th>Registration
	// number</th></tr>');
	var cont = $('#help');
	cont.empty();
	$.each(veh_list,
			function(index, vehicle) {
				console.log("each");
				var cont2 = $('<div></div>');
				var form = $('<form class="formsedit" id="form' + vehicle.id
						+ '"><input name="ident" value=' + vehicle.id
						+ ' readonly><input name="color" value='
						+ vehicle.color + '><input name="type" value='
						+ vehicle.type + '><input name="gear" value='
						+ vehicle.gear
						+ '><input name="registrationNumber" value='
						+ vehicle.registrationNumber
						+ '><input type="submit" id="bform' + vehicle.id
						+ '"></form>');

				cont2.append(form);
				cont.append(cont2);
			}

	);

	$('.formsedit').on('submit', function(e) {
		e.preventDefault();
		console.log(this.id);
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

		console.log(iden);
		var jsonData = JSON.stringify(formData);
		console.log(jsonData);
		$.ajax({
			type : 'post',
			url : "/vehicles/edit",
			contentType : 'application/json',
			dataType : 'json',
			data: jsonData,
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
