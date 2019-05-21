alertify.set('notifier', 'position', 'top-right');
$(document).on('click', '#cancelCompReg', function(e) {
	e.preventDefault();
	$('.transbox_company_reg').css('opacity', '0');
});

$(document).on('click', '#registerCompany', function(e) {
	e.preventDefault();
	companyRegistration();
});

function companyRegistration() {
	var formData = getFormData("#companyRegForm");
	var checkboxes = $('.admins_checkbox');
	var adminsArray = [];
	$.each(checkboxes, function(index, checkbox) {
		if (checkbox.checked) {
			adminsArray.push(checkbox.value);
		}
	});

	formData["admins"] = adminsArray;

	// alert("registration " + formData["admins"] );
	var validData = Boolean(validateRegistrationData(formData));
	if (validData) {
		var jsonData = JSON.stringify(formData);

		$.ajax({
			url : "/companies",
			type : "POST",
			contentType : "application/json",
			data : jsonData,
			dataType : 'json',
			success : function(response) {
				alertify.notify("Company saved :)");
			},
			error : function(response) {
				alert("Something went wrong! :(");
			}
		});
	} else {
		alertify.notify("Please fill in all fields");
	}
}

function validateRegistrationData(formData) {

	var name = formData["name"];
	var address = formData["address"];
	var description = formData["description"];
	var type = formData["cmpType"];

	if (name === "" || address === "" || description === ""
			|| type === undefined) {
		return false;
	}

	return true;
}