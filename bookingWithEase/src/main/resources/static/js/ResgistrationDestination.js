$(document).on('click', '#cancelDestReg', function(e) {
	e.preventDefault();
	window.location.href = "airlines.html";
});

$(document).on('click', '#registerDestination', function(e) {
	e.preventDefault();
	registration();
});



function registration() {
	var formData = getFormData("#destRegForm");

	var validData = Boolean(validateRegistrationData(formData));
	if (validData) {
		var jsonData = JSON.stringify(formData);

		$.ajax({
			url : "/destination",
			type : "POST",
			contentType : "application/json",
			data : jsonData,
			dataType : 'json',
			success : function(response) {
				alert("Airport saved ");
				window.location.href = "airlines.html";
			},
			error : function(response) {
				alert("Something went wrong! :(");
			}
		});
	} else {
		alert("Please fill in all fields");
	}
}

function validateRegistrationData(formData) {

	var id = formData["id"];
	var name = formData["name"];
	var address = formData["address"];
	var type = formData["cmpType"];
	
	if(id === "" || name === "" || address === "" || type === undefined)
	{
		return false;
	}

	return true;
}