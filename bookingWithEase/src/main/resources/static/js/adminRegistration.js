$(document).on('click', '#cancelAdminReg', function(e) {
	e.preventDefault();
	$('.transbox_admin_reg').css('opacity', '0');
});

$(document).on('click', '#registerAdmin', function(e) {
	e.preventDefault();
	registration();
});

function adminRegistrationChoosen() {
	$('.transbox_company_reg').css('opacity', '0');
	$('.transbox_admin_reg').css('opacity', '0.9');
}

function companyRegistrationChoosen() {
	$('.transbox_admin_reg').css('opacity', '0');
	$('.transbox_company_reg').css('opacity', '0.9');
}

function registration() {
	var formData = getFormData("#adminRegForm");

	var validData = Boolean(validateAdminRegData(formData));
	if (validData) {
		var jsonData = JSON.stringify(formData);

		$.ajax({
			url : "/users",
			type : "POST",
			contentType : "application/json",
			data : jsonData,
			dataType : 'json',
			success : function(response) {
				alert("Admin user saved :)");
				window.location.href = "index.html";
			},
			error : function(response) {
				if (response.status == 409) {
					alert("Username is already taken");
				} else {
					alert("Admin user saved :)");
				}

			}
		});
	} else {

	}
}

function validateAdminRegData(formData) {

	var username = formData["username"];
	var firstName = formData["firstName"];
	var lastName = formData["lastName"];
	var email = formData["email"];
	var password = formData["password"];
	var confirmedPassword = formData["confirmPassword"];
	var type = formData["type"];

	if (username === "" || firstName === "" || lastName === "" || email === ""
			|| email === "" || type === undefined) {
		alert("Please fill in all fields");
		return false;
	}

	if (!validateEmail(email)) {
		alert("Please insert a valid email address");
		return false;
	}

	if (password !== confirmedPassword) {
		alert("Entered passwords are not equal");
		return false;
	}

	if (password.length < 8) {
		alert("Password must contain at least 8 characters");
		return false;
	}

	return true;
}

function validateEmail(email) {
	var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return re.test(email);
}