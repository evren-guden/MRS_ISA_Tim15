$(document).on('click', '#logoutClicked', function(e) {
	e.preventDefault();
	window.location.href = "login.html";
})



$(document).on('submit', '#destRegForm', function(e) {
	
	e.preventDefault();
	
	var formData = getFormData("#destRegForm");
	alert(formData.nameAerodroms);
	var jsonData = JSON.stringify(formData);
	alert(jsonData);

	$.ajax({
		url : "/destination",
		type : "POST",
		contentType : "application/json",
		data : jsonData,
		dataType : 'json',
		success : function(response) {
			alert("Airport saved :)");
			window.location.href = "destination.html";
		},
		error : function(jqXHR, textStatus, errorThrown) {
		    alert(jqXHR.status);
		    alert(textStatus);
		    alert(errorThrown);
		}
	});

});



//ispis

findDestination();

function findDestination() {
	$.ajax({
		type : 'GET',
		url : "/destination",
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
	var d_list = data == null ? []
			: (data instanceof Array ? data : [ data ]);
	var table = $('#destinationTable');
	$('#destinationTable').empty();

	var cont = $('#help');
	cont.empty();
	var form = $('<form align =left><input value=ID><input value= Name><input value = Address></form>');
	cont.append(form);

	$.each(d_list, function(index, destination) {
	
		var cont2 = $('<tr></tr>');
		var form =  $('<td><form class="formsedit" id="form' + destination.idAerodromes
				+ '"><input name="ident" value=' + destination.idAerodromes
				+ ' readonly></form></td><td><input name="nameAerodroms" form="form'
				+ destination.idAerodromes + '" value="' + destination.nameAerodroms
				+ '"></td><td><input name="address" form="form' + destination.idAerodromes
				+ '" value="' + destination.address
				
				+ '"></td><td><input type="submit" form="form' + destination.idAerodromes
				+ '" id="bform' + destination.idAerodromes
				+ '"></td><td><button class="delBtns" id="delBtn' + destination.idAerodromes
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
			beforeSend: function (xhr) {
		        /* Authorization header */
		        xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		    },
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


	
	

