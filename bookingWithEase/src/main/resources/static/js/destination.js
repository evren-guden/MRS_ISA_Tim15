$(document).ready(function() {
    getAirlines();
});




function getDestination() {
	$.ajax({
		type : 'GET',
		url : "/destination",
		dataType : "json",
		success : fillTable,
		error : function(data) {
			alert(data);
		}
	});
}

function fillTable(data) {
	var destination = data == null ? []
			: (data instanceof Array ? data : [ data ]);
	var table = $('#destinationTable');
	$('#destinationTable').empty();

	var cont = $('#help');
	cont.empty();
	var form = $('<form align =left><input value=ID><input value= NAME><input value = ADDRESS></form>');
	cont.append(form);

	$.each(destination, function(index, destination) {
	
		var cont2 = $('<div></div>');
		var form = $('<form align=left class="formsedit" id="form' + destination.id
				+ '"><input name="ident" value=' + destination.id
				+ ' readonly><input name="name" value=' + destination.name
				+ '><input name="address" value=' + destination.address
				
				+ '><input type="submit" id="bform'+ destination.id +'"></form>');

		cont2.append(form);
		cont.append(cont2);
	}
	
	);

	
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
		
		console.log(iden);
		var jsonData = JSON.stringify(formData);
		console.log("ovde" + jsonData);
		$.ajax({
			type : 'POST',
			url : "/destination/edit",
			contentType: 'application/json',
			dataType: 'json',
			data: jsonData,
			success : getDestination,
			error : function(data) {
				alert(data);
			}
		});
	});
	

}