$(document).on('submit', '#addVehicleForm', function(e) {
	
	e.preventDefault();
	
	var formData = getFormData("#addBranchForm");
	var jsonData = JSON.stringify(formData);
	console.log("Token sent " + getJwtToken());
	$.ajax({
		url : "/rentacars",
		type : "POST",
		contentType : "application/json",
		data : jsonData,
		dataType : 'json',
		beforeSend: function (xhr) {
	        /* Authorization header */
	        xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
	    },
		success : function(response) {
			alert("Vehicle saved :)");
			window.location.href = "vehicles.html";
		},
		error : function(response) {
			alert("Something went wrong! :(");
		}
	});

});