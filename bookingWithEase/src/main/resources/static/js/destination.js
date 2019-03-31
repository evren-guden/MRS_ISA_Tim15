findDestination();

function findDestination() {
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
	var d_list = data == null ? []
			: (data instanceof Array ? data : [ data ]);
	var table = $('#destinationTable');
	$('#destinationTable').empty();

	var cont = $('#help');
	cont.empty();
	var form = $('<form align =left><input value=ID><input value= NAME><input value = ADDRESS></form>');
	cont.append(form);

	$.each(d_list, function(index, destination) {
	
		var cont2 = $('<tr></tr>');
		var form =  $('<td><form class="formsedit" id="form' + destination.id
				+ '"><input name="ident" value=' + destination.id
				+ ' readonly></form></td><td><input name="name" form="form'
				+ destination.id + '" value="' + destination.name
				+ '"></td><td><input name="address" form="form' + destination.id
				+ '" value="' + destination.address
				
				+ '"></td><td><input type="submit" form="form' + destination.id
				+ '" id="bform' + destination.id
				+ '"></td><td><button class="delBtns" id="delBtn' + destination.id
				+ '">Delete</button></td>');

		cont2.append(form);
		cont.append(cont2);
	}
	
	);
	
	$('.delBtns').on('click', function(e) {
		e.preventDefault();
		var iden = this.id.substring(6);
		console.log(iden);

		$.ajax({
			type : 'delete',
			url : "/destination/" + iden,
			success : function(response) {
				// alert("Vehicle deleted :)");
				window.location.href = "destination.html";
			},
			error : function(data) {
				alert(data);
			}
		});

	});
	
	
	
	

	
	$('.formsedit').on('submit', function(e) {
		e.preventDefault();
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

		var jsonData = JSON.stringify(formData);
		$.ajax({
			type : 'post',
			url : "/destination/edit",
			contentType : 'application/json',
			dataType : 'json',
			data : jsonData,
			success : findDestination,
			error : function(data) {
				alert(data);
			}
		});
	});
	

}