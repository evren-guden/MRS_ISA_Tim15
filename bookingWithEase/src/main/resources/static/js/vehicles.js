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
	$('#tab-vehicles').append('<tr><th>Id</th><th>Color</th><th>Type</th><th>Gear</th><th>Registration number</th></tr>');
	$.each(veh_list, function(index, vehicle) {
        var form = $('<form method="put" class="vehiclesform"></form>');
        var tr = $('<tr></tr>');
        tr.append(form);
        form.append('<td><input type="text" size="3" name="count"></td>');
        table.append(tr);
        
	});

}

$(document).on('click', '#init-vehs', function(e){
	$.ajax({
		type : 'GET',
		url : "/vehicles/initial",
		error : function(data) {
			alert(data);
		}
	});
});