$(document).ready(function() {
    getHotels();
});


function getHotels()
{	
	$.ajax({
		url : "/hotels",
		type : "GET",
		dataType : 'json',
		success : fillTable,
		error : function(response) {
			alert("Something went wrong! :(");
		}
	});
}

function fillTable(data) {
	var hotels = data == null ? []
			: (data instanceof Array ? data : [ data ]);
	$('#hotelsTable').empty();
	$('#hotelsTable').append('<tr><th>Id</th><th>Name</th><th>Address</th><th>Description</th></tr>');
	
	$.each(hotels, function(index, hotel) {

		var tr = $('<tr></tr>');
		var form = $('<td><form class="formsedit" id="form' + hotel.id
				+ '"><input name="ident" value=' + hotel.id
				+ ' readonly></form></td><td><input name="name" form="form'
				+ hotel.id + '" value="' + hotel.name
				+ '"></td><td><input name="address" form="form' + hotel.id
				+ '" value="' + hotel.address
				+ '"></td><td><input name="description" form="form' + hotel.id
				+ '" value="' + hotel.description
				+ '"></td><td><input type="submit" form="form' + hotel.id
				+ '" id="bform' + hotel.id
				+ '"></td>');
		tr.append(form);
		$('#hotelsTable').append(tr);
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
		
		if(formData["name"] === ""){
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
			success : getHotels,
			error : function(data) {
				alert(data);
			}
		});
		
		alert("Saved");
	});

}