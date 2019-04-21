$(document).on('click', '#registerDestination', function(e){
    e.preventDefault();
    window.location.href = "RegistrationDestination.html";
});



$(document).on('click', '#destinationTable', function(e){
    e.preventDefault();
    window.location.href = "destination.html";
});

$(document).ready(function() {
    getAirlines();
});




function getAirlines() {
	$.ajax({
		type : 'GET',
		url : "/airlines",
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
	var airlines = data == null ? []
			: (data instanceof Array ? data : [ data ]);
	var table = $('#airlinesTable');
	$('#airlinesTable').empty();

	
	$('#airlinesTable').append('<form align =left><ID><NAME><ADDRESS><DESCRIPTION></form>');
	$.each(airlines, function(index, airline) {
	
		var tr = $('<tr></tr>');
		tr.append = $('<td>' + airline.id+<'/td'>+'<td>'
				+ airline.name+'</td>'+'<td>'
				+ airline.address+'</td>'+'<td>'
				+  airline.description+'</td>'+'<td>');
				

		$('#airlinesTable').append(tr);
	});

	
$('.formsedit').on('submit', function(e) {
		
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
		
		var jsonData = JSON.stringify(formData);
		console.log("ovde" + jsonData);
		$.ajax({
			type : 'POST',
			url : "/airlines/edit",
			contentType: 'application/json',
			dataType: 'json',
			data: jsonData,
			beforeSend: function (xhr) {
		        /* Authorization header */
		        xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		    },
			success : getAirlines,
			error : function(data) {
				alert(data);
			}
		});
	});
	

}