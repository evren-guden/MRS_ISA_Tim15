$(document).on('click', '#cancelAdminReg', function(e) {
	e.preventDefault();
	$('.transbox_admin_reg').css('opacity', '0');
});

$(document).on('click', '#registerAdmin', function(e) {
	e.preventDefault();
	adminRegistration();
});

function allUsers() {
	window.location.href = "users.html";
}

function adminRegistrationChoosen() {

	$('.transbox_company_reg').css('opacity', '0');
	$('.transbox_admin_reg').css('opacity', '0.9');

	$('.transbox_admin_reg').css('z-index', '3');
	$('.transbox_company_reg').css('z-index', '2');

	$('input:radio[name="admin_type"]').change(function() {
		if ($(this).val() == 'airline') {
			getAirlines();
		} else if ($(this).val() == 'hotel') {
			getHotels();
		} else if ($(this).val() == 'rent-a-car') {
			getRentacars();
		}
	});
}

function fillAdmins(adminType) {
	getUsers();

}

function companyRegistrationChoosen() {
	$('.transbox_admin_reg').css('opacity', '0');
	$('.transbox_company_reg').css('opacity', '0.9');

	$('.transbox_company_reg').css('z-index', '3');
	$('.transbox_admin_reg').css('z-index', '2');

	$('input:radio[name="cmpType"]').change(function() {
		if ($(this).val() == 'airline') {
			adminType = "AIRLINE";
		} else if ($(this).val() == 'hotel') {
			adminType = "HOTEL";
		} else if ($(this).val() == 'rentacar') {
			adminType = "RENT_A_CAR";
		}

		fillAdmins($(this).val());
	});
}

function adminRegistration() {
	var formData = getFormData("#adminRegForm");
	var radiobtns = $('.companies_radio');

	$.each(radiobtns, function(index, btn){
		if(btn.checked)
		{
			formData["companyId"] = btn.value;
		}
	});

	var validData = Boolean(validateAdminRegData(formData));
	if (validData) {
		var jsonData = JSON.stringify(formData);

		$.ajax({
			url : "/users",
			type : "POST",
			contentType : "application/json",
			data : jsonData,
			dataType : 'json',
			beforeSend : function(xhr) {
				/* Authorization header */
				xhr
						.setRequestHeader("Authorization", "Bearer "
								+ getJwtToken());
			},
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
	var type = formData["admin_type"];

	//	alert("username: " + username + " first: " + firstName + " last: "
	//			+ lastName + " email: " + email + " pass: " + password + " pass2: "
	//			+ confirmedPassword + " type: " + type);

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

function saveUsers(data) {
	var users = data == null ? [] : (data instanceof Array ? data : [ data ]);

	$('#select_admins').empty();
	var counter = 0;
	$
			.each(
					users,
					function(index, user) {

						// alert(user.type + " " + user.username);
						if (user.type == adminType) {
							counter++;
							var newItem = $('<div align="left"></div');
							newItem
									.append('<input type="checkbox" class= "admins_checkbox" name="admins_checkbox" value="'
											+ user.username
											+ '" />'
											+ user.username);
							$('#select_admins').append(newItem);

						}
					});

	if (counter == 0)
		$('#select_admins').append("none available");
}

function getUsers() {
	$.ajax({
		url : "/users",
		type : "GET",
		dataType : 'json',
		beforeSend : function(xhr) {
			/* Authorization header */
			xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		},
		success : saveUsers,
		error : function(response) {
			alert("Something went wrong when trying to get users! :(");
		}
	});
}

function fillHotels(data) {
	var hotels = data == null ? [] : (data instanceof Array ? data : [ data ]);

	$('#select_company').empty();
	var counter = 0;
	$
			.each(
					hotels,
					function(index, hotel) {

						counter++;
						var newItem = $('<div align="left"></div');
						newItem
								.append('<input type="radio" class= "companies_radio" name="companies_radio" value="'
										+ hotel.id + '" />' + hotel.name + ", " + hotel.address);
						$('#select_company').append(newItem);

					});

	if (counter == 0)
		$('#select_company').append("none available");
}

function getHotels() {
	$.ajax({
		url : "/hotels",
		type : "GET",
		dataType : 'json',
		beforeSend : function(xhr) {
			/* Authorization header */
			xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
		},
		success : fillHotels,
		error : function(response) {
			alert("Something went wrong while trying to get hotels! :(");
		}
	});
}

function getAirlines() {
	$.ajax({
		type : 'GET',
		url : "/airlines",
		dataType : "json",
		beforeSend: function (xhr) {
	        /* Authorization header */
	        xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
	    },
		success : fillHotels,
		error : function(data) {
			alert(data);
		}
	});
}

function getRentacars() {
	$.ajax({
		type : 'GET',
		url : "/rentacars",
		dataType : "json",
		beforeSend: function (xhr) {
	        /* Authorization header */
	        xhr.setRequestHeader("Authorization", "Bearer " + getJwtToken());
	    },
		success : fillHotels,
		error : function(data) {
			alert(data);
		}
	});
}